package aps.module_Solar.tileEntities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.gui.GuiScreen;
import aps.BuildcraftAPS;
import aps.core.tileEntities.TileEntityAPSMachine;
import aps.module_Solar.APSSolarManager;
import aps.module_Solar.module_Solar;
import buildcraft.api.core.Position;
import buildcraft.core.EntityLaser;
import buildcraft.core.network.PacketPayload;
import buildcraft.core.network.PacketUpdate;
import buildcraft.core.network.TileNetworkData;

public class TileEntitySolarReflector extends TileEntity
{
	boolean isConnected = false;
	public APSSolarManager manager = null;
	
	boolean Setup = false;
	float BiomePowerMult = 1.0f;
	
	//EntityLaser beam; removed for now
	String tex = BuildcraftAPS.imageFilesRoot + "SolarLaser.png";
	float[] beamFocus = new float[3];
	
	/*
	desert = 2.0
	hell = 1.5
	sky = 1.5
	hills = 1.5
	plains = 1.25
	ocean = 1.0
	river = 1.0
	mushroomIsland = 1.0
	mushroomIslandShore = 1.0
	forest = 0.75
	taiga = 0.75
	swampland = 0.75
	frozenOcean = 0.5
	frozenRiver = 0.5
	icePlains = 0.25
	iceMountains = 0.25
	*/
	
	public TileEntitySolarReflector()
	{
		super();
	}
	
	void setup()
	{
		if (worldObj.isRemote) return;
		
		BiomePowerMult = APSSolarManager.getBiomeLightMult(worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord));
		manager = module_Solar.controller.getNearestSolarManager(this);
		assignManager(manager);
		module_Solar.controller.addUnlinkedReflector(this);
		Setup = true;
	}
	
	public void updateEntity()
	{
		if (!Setup)
			setup();
		
		//if clientside, drop out
		if (worldObj.isRemote) return;

		if (manager == null)
		{
			manager = module_Solar.controller.getNearestSolarManager(this);
			module_Solar.controller.addUnlinkedReflector(this);
			return;
		}
		
		//server side, updating
		if(getIsExposed() && worldObj.isDaytime() && manager.collector.Active){
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}else{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public void assignManager(APSSolarManager Manager)
	{
		manager = Manager;
		if(Manager != null)
		{
			manager.addReflector(this);
			//beamFocus = manager.collector.getBeamFocus(xCoord, zCoord);
		}
	}
	
	public boolean getIsExposed() {return worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord);}
	
	public float getLightAmount()
	{
		if (!getIsExposed())
			return 0.0f; //returns 0 if the reflector is covered
		return BiomePowerMult;
	}
}
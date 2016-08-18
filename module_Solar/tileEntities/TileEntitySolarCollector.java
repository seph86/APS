package aps.module_Solar.tileEntities;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import aps.core.tileEntities.TileEntityAPS;
import aps.module_Solar.APSSolarManager;
import buildcraft.BuildCraftCore;
import buildcraft.api.core.Position;
import buildcraft.api.gates.IAction;
import buildcraft.api.gates.IActionReceptor;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import buildcraft.api.transport.IPipe;
import buildcraft.core.IMachine;
import buildcraft.core.network.PacketPayload;
import buildcraft.core.network.PacketUpdate;
import buildcraft.core.triggers.ActionMachineControl;

public class TileEntitySolarCollector extends TileEntityAPS implements ITriggerProvider, IActionReceptor, IPowerReceptor
{		
	APSSolarManager Manager;
	
	int powerRefreshCounter = 0;
	int powerRefreshDelay = 90;
	
	boolean Active = true;
	boolean lastActiveState;
	
	PowerHandler powerHandler;
	
	public TileEntitySolarCollector()
	{
		super();
		Manager = new APSSolarManager();
		powerHandler = new PowerHandler(this, Type.MACHINE);
	}
	
	public void updateEntity()
	{
		//client updates
		if (worldObj.isRemote)
		{
			if (worldObj.getWorldTime() % 50 == 0) {
				if (Active) {
					//setTexture(0, 65); setTexture(1, 65);
				}
				else{
					//setTexture(0, 64); setTexture(1, 64);
				}
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
			return;
		}
		
		//server updates
		if (worldObj.getWorldTime() % 50 == 0){
			sendNetworkUpdate();
			Manager.worldSetup(worldObj, this);
		}
		
		Active = !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && lastMode != ActionMachineControl.Mode.Off && worldObj.isDaytime();
		
		if (!Active || Manager == null) return;
		
		//calculate power then output
		powerHandler.setEnergy(0);
		powerHandler.addEnergy(Manager.getPowerOutput());
		for (int i = 0; i<6; i++) {
			ForgeDirection dir = ForgeDirection.getOrientation(i);
			TileEntity tile = worldObj.getBlockTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			if (tile instanceof IPowerReceptor) {
				((IPowerReceptor)tile).getPowerReceiver(dir.getOpposite()).receiveEnergy(Type.MACHINE, powerHandler.getEnergyStored(), dir.getOpposite());
				break;
			}
		}
	}
	
	
	/*  Probably redundant now
	public float[] getBeamFocus(float X, float Z)
	{
		X -= xCoord; Z -= zCoord;
		if(X >= 0)
		{
			if(Math.abs(X) >= Math.abs(Z))
				return new float[]{xCoord + 1.0f, yCoord + 0.5f, zCoord + 0.5f}; //V
			else if(Z >= 0)
				return new float[]{xCoord + 0.5f, yCoord + 0.5f, zCoord + 1.0f}; //V
			else
				return new float[]{xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.0f};
		}
		else
		{
			if(Math.abs(X) >= Math.abs(Z))
				return new float[]{xCoord + 0.0f, yCoord + 0.5f, zCoord + 0.5f};
			else if(Z >= 0)
				return new float[]{xCoord + 0.5f, yCoord + 0.5f, zCoord + 1.0f};
			else
				return new float[]{xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.0f};
		}
	}*/

	@Override
	public void kill()
	{
		Manager.kill();
		Manager = null;
	}
	
	ActionMachineControl.Mode lastMode = ActionMachineControl.Mode.Unknown;
	@Override
	public void actionActivated(IAction action) {
		if (action == BuildCraftCore.actionOn) {
			lastMode = ActionMachineControl.Mode.On;
		} else if (action == BuildCraftCore.actionOff) {
			lastMode = ActionMachineControl.Mode.Off;
		} else if (action == BuildCraftCore.actionLoop) {
			lastMode = ActionMachineControl.Mode.Loop;
		}		
	}

	@Override
	public LinkedList<ITrigger> getPipeTriggers(IPipe pipe) {return null;}

	@Override
	public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) { return null; }

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doWork(PowerHandler workProvider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}
}
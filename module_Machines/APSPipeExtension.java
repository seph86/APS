package aps.module_Machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import aps.BuildcraftAPS;
import buildcraft.api.core.IIconProvider;
import buildcraft.core.DefaultProps;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransport;
import buildcraft.transport.PipeTransportPower;
import buildcraft.transport.pipes.PipeLogicWood;

public class APSPipeExtension extends Pipe {

	public boolean Enabled = true;
	public boolean oldState = !Enabled;
	
	public APSPipeExtension(int itemID) {
		super(new PipeTransportPower(), itemID);
	}
	
	public void updateEntity() {
		super.updateEntity();
		
		this.Enabled = !this.container.worldObj.isBlockIndirectlyGettingPowered(this.container.xCoord, this.container.yCoord, this.container.zCoord);
		
		if (oldState != Enabled) {
			this.updateNeighbors(true);
			oldState = Enabled;
		}
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		Enabled = nbt.getBoolean("extensionEnabled");
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setBoolean("extensionEnabled", Enabled);
	}
	
	@Override
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side) {
		return super.canPipeConnect(tile,side) && Enabled;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIconProvider getIconProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIconIndex(ForgeDirection direction) {
		// TODO Auto-generated method stub
		return 0;
	}
}

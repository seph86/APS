package aps.module_Environment.tileEntities;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import aps.core.tileEntities.TileEntityAPS;
import aps.core.tileEntities.TileEntityAPSMachine;
import aps.module_Environment.module_Environment;

public class TileEntityLifeVoid extends TileEntityAPS implements  IPowerReceptor {
	
	private boolean active; 
	
	private PowerHandler powerHandler;
	
	public TileEntityLifeVoid(){
		active = false;
	}
	
	//TODO: reduce process time
	public void updateEntity(){
		
		if (worldObj.isRemote) return;
		
		if (isActive() != active) {
			if (isActive()) {
				module_Environment.manager.add(this);
			} else {
				module_Environment.manager.remove(this);
			}
			
			active = isActive();
		}
		
		float energyToUse = powerHandler.getEnergyStored();
		
		powerHandler.useEnergy(energyToUse, energyToUse, true);
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbttags){
		super.readFromNBT(nbttags);
		
		active = nbttags.getBoolean("active");
		
		//if (active) {
			//module_Environment.manager.add(this);
		//}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbttags){
		super.writeToNBT(nbttags);
		
		nbttags.setBoolean("active", active);
	}
	
	@Override
	public void kill() {
		module_Environment.manager.remove(this);
	}
	
	public boolean isActive() {
		return this.powerHandler.getEnergyStored() > 0 ? true : false;
	}

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

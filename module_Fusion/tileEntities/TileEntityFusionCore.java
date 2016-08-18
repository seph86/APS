package aps.module_Fusion.tileEntities;

import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import aps.core.inventory.InventoryAPS;
import aps.core.tileEntities.TileEntityAPSMachine;
import aps.core.tileEntities.TileEntityAPSMachine;

public class TileEntityFusionCore extends TileEntityFusionMultiblock implements IInventory {
	
	//Static  variables
	static private int processingTimeout = 10; //Number of ticks the reactor doesnt process anything but produces power
	static private int maxHeat = 100000;
	static private int minHeat = (int)(maxHeat * 0.8);
	static private int coolRate = 100;
	
	
	//Core variables
	private InventoryAPS inventory;// = new InventoryAPS(4, "fusionreactorinventory");
	private int targetOutput;
	private FluidTank tank;
	
	private enum machineStates {Idle, PreProcess, Processing, Off};
	private machineStates machineState = machineStates.Off;
	
	private byte currentState; 
	private int[] states = new int[4]; //User defined states
	
	private int tempreture = 0;
	
	public TileEntityFusionCore() {
		super();
		inventory = new InventoryAPS(4, "fusionreactorinventory");
	}
	
	//###################### TileEntityFusionCore ####################
	
	//main process function
	private void process() {
		
	}
	
	private void processFuel() {
		
	}
	
	
	
	//######################### TileEntity ###########################
	
	@Override
	public void readFromNBT(NBTTagCompound read) {
		if (read.getInteger("LiquidID") > 0) 
			tank.fill(new FluidStack(read.getInteger("LiquidID"), read.getInteger("LiquidVolume")), true);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound write) {
		
	}
	
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (this.worldObj.isRemote) return; 
		
		if (!this.isStructure) discoverStructure();
		
		process();
	}

	//######################## IInventory ############################
	
	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return inventory.getStackInSlot(var1);
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		return inventory.decrStackSize(var1, var2);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return inventory.getStackInSlotOnClosing(var1);
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		inventory.setInventorySlotContents(var1, var2);
	}

	@Override
	public String getInvName() {
		return inventory.getInvName();
	}

	@Override
	public int getInventoryStackLimit() {
		return inventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openChest() {
		inventory.openChest();
	}

	@Override
	public void closeChest() {
		inventory.openChest();
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
}

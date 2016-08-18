package aps.core.tileEntities;

//Java
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
//vanilla minecraft
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import buildcraft.api.core.Position;
import buildcraft.api.gates.IAction;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
//Buildcraft
import buildcraft.core.DefaultProps;
import buildcraft.core.IMachine;
import buildcraft.core.fluids.Tank;
import buildcraft.core.network.ISynchronizedTile;
import buildcraft.core.network.PacketCoordinates;
import buildcraft.core.network.PacketPayload;
import buildcraft.core.network.PacketTileUpdate;
import buildcraft.core.network.PacketUpdate;
import buildcraft.core.network.TilePacketWrapper;
import buildcraft.core.proxy.CoreProxy;
import buildcraft.core.utils.Utils;

//APS
import aps.BuildcraftAPS;
import aps.core.module_Core;
import aps.core.inventory.APSMachineInventory;
import aps.core.inventory.InventoryAPS;
import aps.core.blocks.APSBlockMachine;


public class TileEntityAPSMachine extends TileEntityAPS implements IPowerReceptor, IMachine, ISidedInventory, IFluidHandler
{	
	public static enum SideType {
		Items, //0
		Power, //1
		Liquid, //2
		Disabled;  //3
		
		public static int total = 4;
	}
	
	//### Core Settings ###
	public int coreProcessSpeed = 20; //how often a heavy process occurs (eg, Fusion Generator math)
	
	public SideType[] sideSettings = {null,null,null,null,null,null};
	
	private boolean initalized = false;
	
	private int[] values = {0,0,0,0}; // 4 Misc values used for machines. EG progress
	
	//## IPowerReceptor settings ##
	
	protected PowerHandler powerHandler;
	private boolean[] poweredNeighbours = {false,false,false,false,false,false};
	
	//## IInventory settings
	
	public APSMachineInventory inventory;
	private boolean[] inventoryNeighbors = {false,false,false,false,false,false};
	
	//## IFluidHandler settings
	
	public Tank tank;
	private boolean[] tankNeighbours = {false,false,false,false,false,false};
	
	//## Constructor
	
	public TileEntityAPSMachine() 
	{
		this.inventory = new APSMachineInventory();
		this.powerHandler = new PowerHandler(this, Type.MACHINE);
		this.powerHandler.configure(1, 100, 1, 5000); //TODO Change this
		this.tank = new Tank("tank", 4000); //TODO: Change this too
		
		for (int i = 0; i<6; i++) {
			sideSettings[i] = SideType.Disabled;
		}
		
		//Temp
		
		sideSettings[0] = SideType.Power;
		sideSettings[1] = SideType.Power;
		sideSettings[4] = SideType.Power;
	}
	
	//## TILE ENTITY ##
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		//quit out client side
		if (worldObj.isRemote) return;
		
		//start up init
		if (!initalized) {
			//TODO Remove or add this
			setPoweredNeighbours();
			//setInventoryNeighbours();
			//setTankNeighbours();
			initalized = true;
		}
		
		if (worldObj.getWorldTime() % coreProcessSpeed == 0) {
			inventory.runKitCoreProcess(this);
		}
		
		inventory.runKitSubProcess(this);
	}
	
	//## APS Tile Entity ##
	
	@Override
	public void onNeighbourChange() {
		setPoweredNeighbours();
		//setInventoryNeighbours();
		//setTankNeighbours();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound read) {
		super.readFromNBT(read);
		Utils.readInvFromNBT(inventory, "inventory", read);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound write) {
		super.writeToNBT(write);
		Utils.writeInvToNBT(inventory, "inventory", write);
	}
	
	//break
	public void kill() {
		//TODO: add this
	}
	
	//#### IPowerReceptor ####
	
	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		if (APSBlockMachine.getFacing(worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) == side.ordinal() || sideSettings[side.ordinal()] != SideType.Power) return null;
		return powerHandler.getPowerReceiver();
	}
	
	@Override
	public void doWork(PowerHandler handler) { }
	
	@Override
	public World getWorld() {
		return this.worldObj;
	}
	
	public boolean isTilePowerReceptor (TileEntity tile, ForgeDirection from)
	{
		if (tile == null) return false; //fail safe
		
		if (tile instanceof IPowerReceptor)
		{
			return ((IPowerReceptor)tile).getPowerReceiver(from) != null;
		}
		return false;
	}

	public float outputPowerToNeighbours(float energy) 
	{	
		float out = 0;
		
		for (int i = 0; i < 6; i++) {
			if ( sideSettings[i] == SideType.Power && poweredNeighbours[i] == true )
				out += outputPowerToNeighbor(ForgeDirection.VALID_DIRECTIONS[i].getOpposite(), ForgeDirection.VALID_DIRECTIONS[i], energy);
		}
		
		return out;
		
	}
	
	public float outputPowerToNeighbours()
	{
		float out = 0;
		int div = 0;
		
		for (int i = 0; i < 6; i++) {
			if ( sideSettings[i] == SideType.Power && poweredNeighbours[i] == true ) div++;
		}
		
		for (int i = 0; i < 6; i++) {
			if ( sideSettings[i] == SideType.Power && poweredNeighbours[i] == true )
				out += outputPowerToNeighbor(ForgeDirection.VALID_DIRECTIONS[i].getOpposite(), ForgeDirection.VALID_DIRECTIONS[i], powerHandler.getEnergyStored() / div);
		}
		
		return out;
	}
	
	public void outputPowerToNeighboursFromStorage() {
		powerHandler.useEnergy(0, outputPowerToNeighbours(), true);
	}
	
	public float outputPowerToNeighbor(ForgeDirection from, int posX, int posY, int posZ, float quantity) 
	{	
		TileEntity tile = this.worldObj.getBlockTileEntity(posX, posY, posZ);
		if (tile == null) return 0;
		
		return ((IPowerReceptor)tile).getPowerReceiver(from).receiveEnergy(Type.STORAGE, quantity, from);
	}
	
	public float outputPowerToNeighbor(ForgeDirection from, ForgeDirection too, float quantity) {
		return outputPowerToNeighbor(from, this.xCoord + too.offsetX, this.yCoord + too.offsetY, this.zCoord + too.offsetZ, quantity);	
	}
	
	public void setPoweredNeighbours(){
		for (int i = 0; i < 6; i++) {
			poweredNeighbours[i] = false; //reset
			ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[i];
			Position pos = new Position(xCoord,yCoord,zCoord, dir);
			pos.moveForwards(1);
			if (isTilePowerReceptor(worldObj.getBlockTileEntity((int)pos.x,(int)pos.y,(int)pos.z), dir.getOpposite())) {
				poweredNeighbours[i] = true;
			}
		}
	}
	
	//#### IMachine ####
	
	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public boolean manageFluids() {
		return false;
	}

	@Override
	public boolean manageSolids() {
		return false;
	}

	@Override
	public boolean allowAction(IAction action) {
		return false;
	}
	
	//#### IInventory ####
	
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
		return inventory.isUseableByPlayer(var1);
	}

	@Override
	public void openChest() {
		inventory.openChest();
	}

	@Override
	public void closeChest() {
		inventory.closeChest();
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if (i >= 5 && i <= 12) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		if (sideSettings[var1] == SideType.Items) {
			return getInteractiveSlots();
		} else {
			return new int[] {};
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		if (slot >= 5 && slot <= 12 && sideSettings[side] == SideType.Items) {
			return true;
		} else { 
			return false;
		}
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return false;
	}
	
	public static int[] getInteractiveSlots() {
		return new int[] {5,6,7,8,9,10,11,12};
	}
	
	public void outputItems(ItemStack itemStack) {
		
		LinkedList<Integer> output = new LinkedList<Integer>();
		
		for (int i = 0; i < 6; i++) {
			//is block facing not side and does the side support outputing items
			if (i != worldObj.getBlockMetadata(xCoord, yCoord, zCoord) && sideSettings[i] == SideType.Items) {
				output.add(i);
			}
		}
		
		if (output.size() == 0) return; //nothing to export 
		
		int division = 0;
		int mod = itemStack.stackSize % output.size();
		
		/*if (items > sides) {
			if (mod == 0) {
				division = items / sides;
			} else { 
				division = (items - mod) / sides;
			}
		}

		for each side {
			if (mod > 0) {
				outputToSide(side, division + 1);
			} else {
				outputToSide(side, division);
			}
			mod--;
		}*/
	}
	
	public void outputItemsTo(ForgeDirection too, ItemStack itemStack) {
		
	}
	
	//#### IFluidHandler ###
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return this.drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if ((from.ordinal() == APSBlockMachine.getFacing(worldObj.getBlockMetadata(xCoord,yCoord,zCoord)) || sideSettings[from.ordinal()] != SideType.Liquid)) return null;
		return new FluidTankInfo[]{tank.getInfo()};
	}
}
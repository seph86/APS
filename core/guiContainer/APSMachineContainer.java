package aps.core.guiContainer;

import java.util.LinkedList;

import buildcraft.core.gui.slots.IPhantomSlot;

import aps.core.module_Core;
import aps.core.items.ItemAPSKit;
import aps.core.tileEntities.TileEntityAPSMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class APSMachineContainer extends APSContainer {

	
	public static int kitSlot = 0;
	public static int upgradeSlot1 = 1;
	public static int upgradeSlot2 = 2;
	public static int upgradeSlot3 = 3;
	public static int upgradeSlot4 = 4;
	public static int machineSlot1 = 5;
	public static int machineSlot2 = 6;
	public static int machineSlot3 = 7;
	public static int machineSlot4 = 8;
	public static int machineSlot5 = 9;
	public static int machineSlot6 = 10;
	public static int machineSlot7 = 11;
	public static int machineSlot8 = 12;
	private static int total = 13;

	
	TileEntityAPSMachine machineTile;
	private LinkedList<Slot> slots = new LinkedList<Slot>(); 
	
	public APSMachineContainer(TileEntityAPSMachine tile, InventoryPlayer playerInv) {
		super(playerInv, 10);
		machineTile = tile;
		int blockMeta = tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) + 1; //+ 1 so that block metadata translates to block grade because block IDs start from 0. Not 1
		
		//add kit slot
		slots.add(addSlotToContainer(new KitSlot(tile, 0, 1000, 10, blockMeta))); 
		
		//upgrade slots
		for (int i = 1; i<5; i++) 
			slots.add(addSlotToContainer(new UpgradeSlot(tile, i, 1000, 10)));
		
		//machine slots
		for (int i = 5; i<total; i++) {
			slots.add(addSlotToContainer(new Slot(tile, i, 1000, 10)));
		}
	}
	
	public void setSlotsForTab(int tab) {
		hideSlots();
		
		//main
		if (tab == 0) {
			ItemStack kit = machineTile.inventory.getStackInSlot(kitSlot);
			if (kit != null) {
				if (kit.getItem() instanceof ItemAPSKit) {
					//TODO: add custom slot positions here
				}
			}
		}
		
		//kit and block upgrades
		if (tab == 1) {
			positionSlot(upgradeSlot1, 150,  8);
			positionSlot(upgradeSlot2, 150, 26);
			positionSlot(upgradeSlot3, 150, 44);
			positionSlot(upgradeSlot4, 150, 62);
			//positionSlot(kitSlot, 10, 10); //debugging
		}
	}
	
	private void hideSlots() { //hide all slots (effectively reset)
		for (int i = 0; i<total; i++) 
			slots.get(i).xDisplayPosition = 1000;
	}
	
	private void positionSlot(int id, int x, int y){
		slots.get(id).xDisplayPosition = x;
		slots.get(id).yDisplayPosition = y;
	}
}

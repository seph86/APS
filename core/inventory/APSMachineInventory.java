package aps.core.inventory;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import aps.core.items.ItemAPSKit;
import aps.core.tileEntities.TileEntityAPSMachine;

public class APSMachineInventory extends InventoryAPS {

	public static int kit = 0;
	public static int upgrade1 = 1;
	public static int upgrade2 = 2;
	public static int upgrade3 = 3;
	public static int upgrade4 = 4;
	public static int misc1 = 5;
	public static int misc2 = 6;
	public static int misc3 = 7;
	public static int misc4 = 8;
	public static int misc5 = 9;
	public static int misc6 = 10;
	public static int misc7 = 11;
	public static int misc8 = 12;
	
	
	// slot 0       -  Kit
	// slot 1 to 4  -  Machine Upgrades
	// slot 5 to 12 -  Misc item slots (8 slots)
	
	public APSMachineInventory() {
		super(13, "inventory");
	}

	public Item getKit() {
		
		ItemStack stack = getStackInSlot(kit);
		
		return stack != null ? stack.getItem() : null;
	}
	
	public void runKitCoreProcess(TileEntityAPSMachine tile) {
		
		Item kit = getKit();
		
		if (kit instanceof ItemAPSKit) {
			((ItemAPSKit)kit).mainProcess(tile);
		}
	}
	
	public void runKitSubProcess(TileEntityAPSMachine tile) {
		
		Item kit = getKit();
		
		if (kit instanceof ItemAPSKit) {
			((ItemAPSKit)kit).subProcess(tile);
		}
		
	}
	
}

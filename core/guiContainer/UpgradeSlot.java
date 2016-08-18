package aps.core.guiContainer;

import aps.core.items.ItemAPSMachineUpgrade;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class UpgradeSlot extends Slot {

	public UpgradeSlot(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack item) {
		return item.getItem() instanceof ItemAPSMachineUpgrade;
	}
	
}

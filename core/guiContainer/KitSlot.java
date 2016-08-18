package aps.core.guiContainer;

import aps.core.items.ItemAPSKit;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class KitSlot extends Slot {

	private int grade;
	
	public KitSlot(IInventory par1iInventory, int par2, int par3, int par4, int grade) {
		super(par1iInventory, par2, par3, par4);
		this.grade = grade;
	}

	@Override
	public boolean isItemValid(ItemStack item) {
		if (item.getItem() instanceof ItemAPSKit) {
			return ((ItemAPSKit)item.getItem()).kitGrade <= grade;
		} else {
			return false;
		}
	}
	
}

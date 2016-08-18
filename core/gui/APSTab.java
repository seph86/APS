package aps.core.gui;

import aps.core.module_Core;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class APSTab extends CreativeTabs {

	public APSTab(String label) {
		super(label);
	}

	public ItemStack getIconItemStack(){
		return new ItemStack(module_Core.APSPowerCoreItem,1,3);
	}
	
}

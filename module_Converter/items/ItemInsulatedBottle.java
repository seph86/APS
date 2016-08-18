package aps.module_Converter.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import buildcraft.core.ItemBuildCraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import aps.BuildcraftAPS;

public class ItemInsulatedBottle extends ItemBuildCraft {

	public ItemInsulatedBottle(int id) {
		super(id);
		this.maxStackSize = 64;
		//this.iconIndex = 22;
		this.setCreativeTab(BuildcraftAPS.customTab);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.itemIcon = register.registerIcon("APS:insulatedBottleEmpty");
	}
}

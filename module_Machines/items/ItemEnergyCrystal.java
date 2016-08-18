package aps.module_Machines.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import buildcraft.core.ItemBuildCraft;
import aps.BuildcraftAPS;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemEnergyCrystal extends ItemBuildCraft {
	
	public ItemEnergyCrystal(int par1) {
		super(par1);
		this.setCreativeTab(BuildcraftAPS.customTab);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.itemIcon = register.registerIcon("APS:energyCrystal");
	}
	
}

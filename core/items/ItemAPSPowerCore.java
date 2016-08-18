package aps.core.items;

//Vanilla minecraft
import java.util.List;

import buildcraft.core.ItemBuildCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

//APS
import aps.BuildcraftAPS;

public class ItemAPSPowerCore extends ItemBuildCraft
{
	
	public static final int Redstone_Core = 0;
	public static final int Iron_Core = 1;
	public static final int Gold_Core = 2;
	public static final int Daimond_Core = 3;
	public static final int Total = 4;
	
	@SideOnly(Side.CLIENT)
	Icon[] icons;
	
	public ItemAPSPowerCore(int i) {
		super(i);

        setHasSubtypes(true);
        setMaxDamage(0);	
        
        this.setCreativeTab(BuildcraftAPS.customTab);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append(itemstack.getItemDamage()).toString();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[ItemAPSPowerCore.Total];
		icons[Redstone_Core] = iconRegister.registerIcon("APS:redstonePowercore");
		icons[Iron_Core] = iconRegister.registerIcon("APS:ironpowercore");
		icons[Gold_Core] = iconRegister.registerIcon("APS:goldpowercore");
		icons[Daimond_Core] = iconRegister.registerIcon("APS:daimondpowercore");
	}
	
	@Override
	public Icon getIconFromDamage(int i)
	{
		return icons[i];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List itemList)
    {
		for (int i = 0; i < this.Total; i++)
			itemList.add(new ItemStack(this, 1, i));
    }
}
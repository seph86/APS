package aps.core.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import aps.BuildcraftAPS;
import buildcraft.core.ItemBuildCraft;

public class ItemAPSMachineUpgrade extends ItemBuildCraft {

	public static final int efficiency = 0; //power required for process
	public static final int productivity = 1; //output from process
	public static final int speed = 2; //speed of process
	public static final int smallBattery = 3;
	public static final int mediumBattery = 4;
	public static final int largeBattery = 5;
	public static final int LEinput = 6; //Liquid Energy input
	public static final int total = 7;
	
	@SideOnly(Side.CLIENT)
	Icon[] icons;
	
	public ItemAPSMachineUpgrade(int i) {
		super(i);
		setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(1);
        
        this.setCreativeTab(BuildcraftAPS.customTab);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append(itemstack.getItemDamage()).toString();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[total];
		icons[efficiency] = iconRegister.registerIcon("APS:efficiencyUpgrade");
		icons[productivity] = iconRegister.registerIcon("APS:productivityUpgrade");
		icons[speed] = iconRegister.registerIcon("APS:speedUpgrade");
		icons[smallBattery] = iconRegister.registerIcon("APS:smallBatteryUpgrade");
		icons[mediumBattery] = iconRegister.registerIcon("APS:mediumBatteryUpgrade");
		icons[largeBattery] = iconRegister.registerIcon("APS:largeBatteryUpgrade");
		icons[LEinput] = iconRegister.registerIcon("APS:LEinputyUpgrade");
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
		for (int i = 0; i < this.total; i++)
			itemList.add(new ItemStack(this, 1, i));
    }
}

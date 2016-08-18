package aps.module_Fusion.items;

//Vanilla Minecraft
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

//APS
import aps.BuildcraftAPS;

public class ItemTokamakControl extends Item
{
	public ItemTokamakControl(int i)
    {
        super(i);
        maxStackSize = 64;
        //iconIndex = 2;
        this.setCreativeTab(BuildcraftAPS.customTab);
	}
}
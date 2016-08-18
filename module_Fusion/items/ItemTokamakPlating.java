package aps.module_Fusion.items;

//Vanilla Minecraft
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

//APS
import aps.BuildcraftAPS;

public class ItemTokamakPlating extends Item
{
	public ItemTokamakPlating(int i)
    {
        super(i);
        maxStackSize = 64;
        //iconIndex = 0;
        this.setCreativeTab(BuildcraftAPS.customTab);
	}
}
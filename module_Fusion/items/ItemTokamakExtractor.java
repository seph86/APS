package aps.module_Fusion.items;

//Vanilla Minecraft
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

//APS
import aps.BuildcraftAPS;

public class ItemTokamakExtractor extends Item
{
	public ItemTokamakExtractor(int i)
    {
        super(i);
        maxStackSize = 64;
        //iconIndex = 3;
        this.setCreativeTab(BuildcraftAPS.customTab);
    }
}
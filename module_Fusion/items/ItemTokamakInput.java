package aps.module_Fusion.items;

//Vanilla Minecraft
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

//APS
import aps.BuildcraftAPS;

public class ItemTokamakInput extends Item
{
	public ItemTokamakInput(int i)
    {
        super(i);
        maxStackSize = 64;
        //iconIndex = 4;
        this.setCreativeTab(BuildcraftAPS.customTab);
    }
}
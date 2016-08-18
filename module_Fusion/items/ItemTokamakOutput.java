package aps.module_Fusion.items;

//Vanilla Minecraft
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

//APS
import aps.BuildcraftAPS;

public class ItemTokamakOutput extends Item
{
	public ItemTokamakOutput(int i)
    {
        super(i);
        maxStackSize = 64;
        //iconIndex = 5;
        this.setCreativeTab(BuildcraftAPS.customTab);
    }
}
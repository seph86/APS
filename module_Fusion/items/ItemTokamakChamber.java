package aps.module_Fusion.items;

//Vanilla Minecraft
import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;

//APS
import aps.BuildcraftAPS;

public class ItemTokamakChamber extends Item
{
	public ItemTokamakChamber(int i)
    {
        super(i);
        maxStackSize = 64;
        //iconIndex = 1;
        this.setCreativeTab(BuildcraftAPS.customTab);
	}
}
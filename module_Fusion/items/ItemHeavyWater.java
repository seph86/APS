package aps.module_Fusion.items;

import net.minecraft.item.Item;
import aps.BuildcraftAPS;

public class ItemHeavyWater extends Item
{
	public ItemHeavyWater(int i)
    {
        super(i);
        //iconIndex = 8;
    }
	
	public String getTextureFile()
	{
		return BuildcraftAPS.imageFilesRoot + "APSItemTexes.png";
	}
}
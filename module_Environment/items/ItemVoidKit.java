package aps.module_Environment.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aps.core.tileEntities.TileEntityAPSMachine;

public class ItemVoidKit extends Item {
	
	public ItemVoidKit(int i) {
		super(i);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		iconRegister.registerIcon("APS:itemVoidKit");
	}

}

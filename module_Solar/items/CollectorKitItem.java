package aps.module_Solar.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class CollectorKitItem extends Item {

	public CollectorKitItem(int par1) {
		super(par1);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		iconRegister.registerIcon("APS:collectorKit");
	}
}

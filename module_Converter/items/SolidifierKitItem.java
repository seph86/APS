package aps.module_Converter.items;

import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aps.core.items.ItemAPSKit;
import aps.core.tileEntities.TileEntityAPSMachine;

public class SolidifierKitItem extends ItemAPSKit {

	public static int itemGrade = 3;
	
	public SolidifierKitItem(int i) {
		super(i, itemGrade);
	}

	@Override
	public void mainProcess(TileEntityAPSMachine tile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void subProcess(TileEntityAPSMachine tile) {
		// TODO Auto-generated method stub

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		iconRegister.registerIcon("APS:solidifierKit");
	}

}

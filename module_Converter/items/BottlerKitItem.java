package aps.module_Converter.items;

import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aps.core.items.ItemAPSKit;
import aps.core.tileEntities.TileEntityAPSMachine;

public class BottlerKitItem extends ItemAPSKit {

	public static int itemGrade = 1;
	
	public BottlerKitItem(int i) {
		super(i, itemGrade);
		// TODO Auto-generated constructor stub
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
		iconRegister.registerIcon("APS:bottlerKit");
	}

}

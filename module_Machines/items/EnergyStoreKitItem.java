package aps.module_Machines.items;

import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aps.core.items.ItemAPSKit;
import aps.core.tileEntities.TileEntityAPSMachine;

public class EnergyStoreKitItem extends ItemAPSKit {

	public static int itemGrade = 2;
	
	public EnergyStoreKitItem(int i) {
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
		iconRegister.registerIcon("APS:energyStoreKit");
	}

}

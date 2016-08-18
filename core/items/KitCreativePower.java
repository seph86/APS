package aps.core.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import aps.core.tileEntities.TileEntityAPSMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KitCreativePower extends ItemAPSKit {

	public static int itemGrade = 1;
	
	private static void SubProcess(TileEntityAPSMachine tile) {
		tile.outputPowerToNeighbours(100);
	}
	
	public KitCreativePower(int i) {
		super(i, itemGrade);
	}

	@Override
	public void mainProcess(TileEntityAPSMachine tile) {
		//Nothing
	}

	@Override
	public void subProcess(TileEntityAPSMachine tile) {
		SubProcess(tile);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		iconRegister.registerIcon("aps:freeenergykit");
		blockIcon = iconRegister.registerIcon("aps:freeenergyface");
	}

}

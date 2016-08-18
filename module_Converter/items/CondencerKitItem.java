package aps.module_Converter.items;

import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aps.core.items.ItemAPSKit;
import aps.core.tileEntities.TileEntityAPSMachine;

public class CondencerKitItem extends ItemAPSKit {

	private static void SubProcess(TileEntityAPSMachine tile) {
		System.out.println("woot");
	}
	
	private static void CoreProcess(TileEntityAPSMachine tile){
		
	}
	
	private static int itemGrade = 3;
	
	public CondencerKitItem(int i) {
		super(i, itemGrade);
	}
	
	@Override
	public void subProcess(TileEntityAPSMachine tile) {
		SubProcess(tile);
	}
	
	@Override
	public void mainProcess(TileEntityAPSMachine tile) {
		CoreProcess(tile);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		iconRegister.registerIcon("APS:condenserKit");
	}
}

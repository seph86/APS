package aps.core.gui;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import buildcraft.api.core.IIconProvider;

public class APSPipeIconProvider implements IIconProvider {
	
	public enum icons {
		//cobblestone
		WhiteCobblestonePipe("whiteCobblestonePipe"),
		RedCobblestonePipe("redCobblestonePipe"),
		BlueCobblestonePipe("blueCobblestonePipe"),
		GreenCobblestonePipe("greenCobblestonePipe"),
		//stone
		WhiteStonePipe("whiteStonePipe"),
		RedStonePipe("redStonePipe"),
		BlueStonePipe("blueStonePipe"),
		GreenStonePipe("greenStonePipe"),
		//quartz
		WhiteQuartzPipe("whiteQuartzPipe"),
		RedQuartzPipe("redQuartzPipe"),
		BlueQuartzPipe("blueQuartzPipe"),
		GreenQuartzPipe("greenQuartzPipe"),
		//gold
		WhiteGoldPipe("whiteGoldPipe"),
		RedGoldPipe("redGoldPipe"),
		BlueGoldPipe("blueGoldPipe"),
		GreenGoldPipe("greenGoldPipe"),
		//diamond
		WhiteDiamondPipe("whiteDiamondPipe"),
		RedDiamondPipe("redDiamondPipe"),
		BlueDiamondPipe("blueDiamondPipe"),
		GreenDiamondPipe("greenDiamondPipe");
		
		public static final icons[] VALUES = values();
		private final String iconTag;
		private Icon icon;
		
		private icons(String iconTag) {
			this.iconTag = iconTag;
		}
		
		private void registerIcon(IconRegister iconRegister) {
			//TODO: Uncomment this
			//icon = iconRegister.registerIcon("APS:"+iconTag);
		}
		
		public Icon getIcon(){
			return icon;
		}
	}
	

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int iconIndex) {
		if (iconIndex < 0 || iconIndex > icons.VALUES.length)
			return null;
		return icons.VALUES[iconIndex].icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for (icons icon : icons.VALUES) {
			//icon.registerIcon(iconRegister);
		}
	}

}

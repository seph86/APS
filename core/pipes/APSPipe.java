package aps.core.pipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import aps.BuildcraftAPS;
import aps.core.module_Core;
import buildcraft.api.core.IIconProvider;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransport;
import buildcraft.transport.PipeTransportPower;
import buildcraft.transport.TileGenericPipe;

public class APSPipe extends Pipe<PipeTransportPower> {
	
	public enum Colours {White, Red, Blue, Green}
	public enum Types {CobbleStone, Stone, Quartz, Gold, Diamond}
	
	
	private Colours pipeColor;
	private Types type;
	
	
	public APSPipe(int itemID, Colours color, Types type) {
		super(new PipeTransportPower(), itemID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIconProvider getIconProvider() {
		return module_Core.apsPipeIconProvider;
	}

	@Override
	public int getIconIndex(ForgeDirection direction) {
		return (5 * type.ordinal()) + pipeColor.ordinal();
	}
	
	@Override
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side) {
		
		if (tile instanceof TileGenericPipe) {
			Pipe pipe = ((TileGenericPipe)tile).pipe;
			if (pipe instanceof APSPipe) {
				return ((APSPipe)pipe).pipeColor == this.pipeColor;
			}
		}
		
		return super.canPipeConnect(tile, side);
	}
	
	public Colours getColor() { return this.pipeColor; };
	
	public void setColor(Colours color) { this.pipeColor = color; }
}

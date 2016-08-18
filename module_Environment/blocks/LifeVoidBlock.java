package aps.module_Environment.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import aps.core.blocks.APSBlock;
import aps.module_Environment.tileEntities.TileEntityLifeVoid;

public class LifeVoidBlock extends APSBlock {

	public LifeVoidBlock(int id, Material par3Material) {
		super(id, par3Material);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityLifeVoid();
	}

	@Override
	public int getGuiID() {
		return 0;
	}

}

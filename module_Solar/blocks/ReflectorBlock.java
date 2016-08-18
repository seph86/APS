package aps.module_Solar.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import aps.core.blocks.APSBlock;
import aps.module_Solar.tileEntities.TileEntitySolarReflector;

public class ReflectorBlock extends APSBlock {

	public ReflectorBlock(int id, Material par3Material) {
		super(id, par3Material);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntitySolarReflector();
	}

	@Override
	public int getGuiID() {
		// TODO Auto-generated method stub
		return 0;
	}

}

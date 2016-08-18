package aps.core.blocks;

import aps.BuildcraftAPS;
import aps.core.staticVariables.GuiIDs;
import aps.core.tileEntities.TileEntityAPS;
import aps.core.tileEntities.TileEntityAPSMachine;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class APSBlock extends BlockContainer {
	
	protected APSBlock(int id, Material par3Material) {
		super(id, par3Material);
	}
	
	
	@Override
	public void onNeighborBlockChange(World world, int posX, int posY, int posZ, int blockID) {
		super.onNeighborBlockChange(world, posX, posY, posZ, blockID);
		
		TileEntity tile = world.getBlockTileEntity(posX, posY, posZ);
		if (tile != null && tile instanceof TileEntityAPS) {
			((TileEntityAPS)tile).onNeighbourChange();
		}
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		
		if (par5EntityPlayer.isSneaking()) return false;
		
		TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
		
		if (tile instanceof TileEntityAPSMachine && getGuiID() != 0) {
			
			TileEntityAPSMachine apsTile = (TileEntityAPSMachine)tile;
			
			if (!par1World.isRemote) 
				par5EntityPlayer.openGui(BuildcraftAPS.instance, getGuiID(), par1World, par2, par3, par4);
			
			return true;
			
		} else {
			
			return false;
			
		}
	}
	
	public abstract int getGuiID();
}

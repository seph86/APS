package aps.module_Converter.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import aps.BuildcraftAPS;
import aps.module_Converter.module_Converter;
import buildcraft.core.ItemBuildCraft;
import buildcraft.factory.TileTank;

public class ItemBottledEnergy extends ItemBuildCraft
{
	public ItemBottledEnergy(int id)
	{
		super(id);
		this.maxStackSize = 1;
		//this.iconIndex = 23;
		this.setCreativeTab(BuildcraftAPS.customTab);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.itemIcon = register.registerIcon("APS:insulatedBottleFull");
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int par7, float par8, float par9, float par10)
	{
		if (player.capabilities.isCreativeMode) {
			TileEntity tile = world.getBlockTileEntity(posX, posY, posZ);
			
			if (tile instanceof TileTank)
			{
				return ((TileTank)tile).fill(ForgeDirection.UNKNOWN, new FluidStack(module_Converter.liquidEnergy, FluidContainerRegistry.BUCKET_VOLUME), true) > 0;
			}
		}
		
		return false;
	}
}
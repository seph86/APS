package aps.core.blocks;

import java.util.List;

import buildcraft.builders.TileFiller;
import buildcraft.core.inventory.InvUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import buildcraft.api.tools.IToolWrench;
import aps.BuildcraftAPS;
import aps.core.items.ItemAPSKit;
import aps.core.staticVariables.GuiIDs;
import aps.core.tileEntities.TileEntityAPSMachine;



public class APSBlockMachine extends APSBlock {
	
	//Metadata info:
	/*
	 *   8 4 2 1
	 *   1 1 0 0  -- Block Facing
	 *   0 0 1 1  -- Block Grade
	 */
	
	//textures
	public static int Items = 0;
	public static int Power = 1;
	public static int Liquid = 2;
	public static int DisabledNSEW = 3;
	public static int DisabledUD = 4;
	
	private static int total = 5;
	
	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

	public APSBlockMachine(int id, Material par3Material) {
		super(id, par3Material);
		this.setCreativeTab(BuildcraftAPS.customTab);
	}

	
	public static int getGrade(int meta) {
		return (meta & 3) + 1;
	}
	
	public static int getGradeRaw(int meta) {
		System.out.println(meta + " " + Integer.toBinaryString(meta) + " " + (meta & 3));
		return (meta & 3);
	}
	
	public static int getFacing(int meta) {
		return ((meta >> 2) & 3) + 2;
	}
	
	public static int setFacing(int meta, int facing) {
		if (facing < 2 || facing > 5) facing = 2;  //fail safe
		facing -= 2;
		System.out.println((meta & 3) ^ (facing << 2));
		return (meta & 3) ^ (facing << 2);
	}
	
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityAPSMachine();
	}
	
	@Override
	public int getGuiID() {
		return GuiIDs.GUI_MACHINE;
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for (int i = 0; i < 3; i++)
        par3List.add(new ItemStack(par1, 1, i));
    }
	
	@Override
	public int damageDropped(int damage) {
		return getGrade(damage);
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
		ItemStack stack = super.getPickBlock(target, world, x, y, z);
		stack.setItemDamage(getGradeRaw(world.getBlockMetadata(x, y, z)));
		
		return stack;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		icons = new Icon[this.total];
		icons[Items] = register.registerIcon("aps:machineitem1"); //TODO Fix these
		icons[Power] = register.registerIcon("aps:machineitem1");
		icons[Liquid] = register.registerIcon("aps:machineitem1");
		icons[DisabledNSEW] = register.registerIcon("aps:machineblank2");
		icons[DisabledUD] = register.registerIcon("aps:machineblank1");	
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int blockMeta) {
		if (side >= 0 && side <= 1) {
			return icons[DisabledUD];
		} else {
			return icons[DisabledNSEW];
		}
	}
	
	@SuppressWarnings({ "all" })
	public Icon getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z, int side) {

		int meta = iblockaccess.getBlockMetadata(x, y, z);
		
		TileEntity tile = iblockaccess.getBlockTileEntity(x, y, z);
		
		if (tile != null && tile instanceof TileEntityAPSMachine) 
		{
			
			TileEntityAPSMachine machine = (TileEntityAPSMachine)tile;
			
			if (side == this.getFacing(meta)) 
			{
				
				if (machine.inventory.getKit() == null) {
					return icons[DisabledUD];
				} else {
					return icons[Liquid]; //TODO: Test
				}

			} else {
				
			}
		}
		
		
		//fallback
		return getIcon(side, meta);
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) 
	{
		
		System.out.println(par6 + " " + par7 + " " + par8 + " " + par9 + " " + getGradeRaw(par1World.getBlockMetadata(par2, par3, par4)));
		
		if (par1World.isRemote) return false;
		
		Item equipped = par5EntityPlayer.getCurrentEquippedItem() != null ? par5EntityPlayer.getCurrentEquippedItem().getItem() : null;
		
		TileEntityAPSMachine tile = (TileEntityAPSMachine)par1World.getBlockTileEntity(par2, par3, par4);
		
		if (equipped instanceof ItemAPSKit) {
			
			ItemAPSKit kititem = (ItemAPSKit)par5EntityPlayer.getCurrentEquippedItem().getItem();
			
			int grade = this.getGrade(par1World.getBlockMetadata(par2, par3, par4));
			
			if (this.getGrade(par1World.getBlockMetadata(par2, par3, par4)) >= kititem.kitGrade) {
				
				if (tile.inventory.getKit() == null) { //nothing in slot?
					tile.inventory.setInventorySlotContents(tile.inventory.kit, par5EntityPlayer.getCurrentEquippedItem().copy()); //insert kit
					par5EntityPlayer.setCurrentItemOrArmor(0, null); //delete item from player inventory
				}
			}
			
			par1World.markBlockForUpdate(par2, par3, par4);
			par1World.markBlockForRenderUpdate(par2, par3, par4);
			return false;
			
		} else if (equipped instanceof IToolWrench) {
			
			if (par5EntityPlayer.isSneaking()) {
				
				if (par6 == 0 || par6 == 1) return false; //dont apply to top and bottom facing of the block
				
				par1World.setBlockMetadataWithNotify(par2, par3, par4, setFacing(par1World.getBlockMetadata(par2, par3, par4), par6), 3);
				//System.out.println(Integer.toBinaryString(par1World.getBlockMetadata(par2, par3, par4)) + " " + par1World.getBlockMetadata(par2, par3, par4));
				
			} else if (tile.inventory.getKit() != null) {
			
				InvUtils.dropItems(par1World, tile.inventory.getStackInSlot(tile.inventory.kit).copy(), par2, par3, par4); //drop onto floor
				tile.inventory.setInventorySlotContents(tile.inventory.kit, null); //delete item out of inventory
			
			}
			return true;
		}
		
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}
}

package aps.core.items;

//Vanilla Minecraft
import java.util.List;

import buildcraft.core.ItemBuildCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

//forge
import net.minecraft.src.ModLoader;

//APS
import aps.BuildcraftAPS;
import aps.core.module_Core;
import aps.core.tileEntities.TileEntityAPSMachine;

public abstract class ItemAPSKit extends ItemBuildCraft
{	
	//Kit grade
	public int kitGrade;
	
	@SideOnly(Side.CLIENT)
	public Icon blockIcon;
	
	public ItemAPSKit(int i, int grade) {
		super(i);
		this.kitGrade = grade;
        setMaxDamage(0);
        this.setCreativeTab(BuildcraftAPS.customTab);
	}
	
	public abstract void mainProcess(TileEntityAPSMachine tile);
	
	public abstract void subProcess(TileEntityAPSMachine tile);
	
	public Icon getBlockFaceIcon() {
		return blockIcon;
	}
	
	@SideOnly(Side.CLIENT)
	public abstract void registerIcons(IconRegister iconRegister);
	
	public void drawGui() {
		
	}
}
package aps.module_Converter.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class LiquidEnergyBlock extends BlockFluidClassic {

	@SideOnly(Side.CLIENT)
	protected Icon[] icon;
	
	public LiquidEnergyBlock(int id, Fluid fluid, Material material) {
		super(id, fluid, material);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return side != 0 && side != 1 ? this.icon[1] : this.icon[0];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.icon = new Icon[]{iconRegister.registerIcon("aps:" + fluidName + "_still"), iconRegister.registerIcon("aps:" + fluidName + "_flow")};
	}
}

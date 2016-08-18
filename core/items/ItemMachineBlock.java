package aps.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aps.BuildcraftAPS;
import aps.core.blocks.APSBlockMachine;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMachineBlock extends ItemBlock {

	public ItemMachineBlock(int par1) {
		super(par1);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append(APSBlockMachine.getGrade(itemstack.getItemDamage())).toString();
	}
	
	@Override
	public int getMetadata(int par1)
    {
        return par1;
    }
}

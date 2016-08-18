package aps.module_Environment;

import buildcraft.core.proxy.CoreProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import aps.BuildcraftAPS;
import aps.module;
import aps.core.module_Core;
import aps.core.staticVariables.IDs;
import aps.module_Environment.blocks.LifeVoidBlock;
import aps.module_Environment.items.ItemVoidKit;
import aps.module_Environment.items.VoidMachineBlockItem;
import aps.module_Environment.tileEntities.TileEntityLifeVoid;

public class module_Environment extends module {	
	public static LifeVoidManager manager = new LifeVoidManager();
	
	public static int voidMachineBlockID;
	public static Block voidMachineBlock;
	public static int itemVoidMachineKitID;
	public static Item itemVoidMachineKit;
	
	@Override
	public void preInit() {
		voidMachineBlockID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_BLOCK, "VoidMachineBlockID", IDs.blockVoid).getInt();
		itemVoidMachineKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "itemVoidMachine.ID", IDs.itemVoidKit).getInt();
	}

	@Override
	public void clientInit() {
		//Nothing
	}

	@Override
	public void initialize() {
		
		//Add Life void machine
		voidMachineBlock = new LifeVoidBlock(voidMachineBlockID, Material.iron);
		voidMachineBlock.setUnlocalizedName("voidmachine");
		CoreProxy.proxy.registerBlock(voidMachineBlock, VoidMachineBlockItem.class);
		CoreProxy.proxy.registerTileEntity(TileEntityLifeVoid.class, "tileentitylifevoidmachine");
		itemVoidMachineKit = new ItemVoidKit(itemVoidMachineKitID);
		
		
		
		// Add event hook
		MinecraftForge.EVENT_BUS.register(new EntitySpawnHook());
	}

	@Override
	public void postInit() {

	}

}

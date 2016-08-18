package aps.module_Solar;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import aps.BuildcraftAPS;
import aps.module;
import aps.core.module_Core;
import aps.core.items.ItemAPSKit;
import aps.core.staticVariables.IDs;
import aps.module_Solar.blocks.CollectorBlock;
import aps.module_Solar.blocks.ReflectorBlock;
import aps.module_Solar.items.CollectorKitItem;
import aps.module_Solar.items.ReflectorKitItem;
import aps.module_Solar.tileEntities.TileEntitySolarCollector;
import aps.module_Solar.tileEntities.TileEntitySolarReflector;
import buildcraft.BuildCraftCore;
import buildcraft.BuildCraftTransport;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.recipes.AssemblyRecipe;

public class module_Solar extends module
{
	public static APSSolarManagerController controller;
	
	public static ITrigger solarPowerOutputTrigger;
	
	public static Block blockCollector;
	public static int blockCollectorID;
	public static Item collectorKitItem;
	public static int collectorKitItemID;
	
	public static Block blockReflector;
	public static int blockReflectorID;
	public static Item reflectorKitItem;
	public static int reflectorKitItemID;
	
	public module_Solar() {}
	
	@Override
	public void preInit() {
		blockCollectorID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_BLOCK, "CollectorBlockID", IDs.blockCollector).getInt();
		collectorKitItemID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "CollectorKit.ID", IDs.itemCollectorKit).getInt();
		blockReflectorID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_BLOCK, "ReflectorBlockID", IDs.blockReflector).getInt();
		reflectorKitItemID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "CollectorKit.ID", IDs.itemReflectorKit).getInt();
	}
	
	@Override
	public void initialize()
	{
		controller = new APSSolarManagerController();
		
		solarPowerOutputTrigger = new TriggerSolar(103, TriggerSolar.TriggerType.SolarPowerOutput);
				
		//####################Solar Reflector
			// -- Kit Item
			reflectorKitItem = new ReflectorKitItem(reflectorKitItemID);
			// -- kit
	    	ModLoader.addRecipe(new ItemStack(reflectorKitItem, 1), new Object[]{ "GGG", "iIi", "oCo",
	                                                                Character.valueOf('G'), Block.glass,
	                                                                Character.valueOf('I'), Item.ingotIron,
	                                                                Character.valueOf('o'), Block.obsidian,
	                                                                Character.valueOf('i'), Item.ingotIron,
	                                                                Character.valueOf('C'), new ItemStack(module_Core.APSPowerCoreItem, 1, 1)
	                                                                });
	    	
	    	//ModLoader.addShapelessRecipe(new ItemStack(module_Core.APSMetaBlockEnergy, 1, ReflectorID), new Object[] { new ItemStack(BuildCraftCore.wrenchItem), new ItemStack(module_Core.APSKitItem,1,ReflectorID + ItemEnergy) });
	    	
	    	// -- Block
	    	blockReflector = new ReflectorBlock(blockReflectorID, Material.iron);
	    	AssemblyRecipe.assemblyRecipes.add(new AssemblyRecipe(new ItemStack[] {
	                                                                new ItemStack(reflectorKitItem, 1)},
	                                                                2500, 
	                                                                new ItemStack(blockReflector, 1)
	                                                                ));
    	
    	//####################Solar Collector
	    	// -- Kit Item
	    	collectorKitItem = new CollectorKitItem(collectorKitItemID);
	    	// -- kit
	    	ModLoader.addRecipe(new ItemStack(collectorKitItem, 1), new Object[]{ "oCo", "IBI", "owo",
	                                                                Character.valueOf('o'), Block.obsidian,
	                                                                Character.valueOf('C'), new ItemStack(module_Core.APSPowerCoreItem, 1, 1),
	                                                                Character.valueOf('w'), BuildCraftTransport.pipePowerWood,
	                                                                Character.valueOf('B'), Item.bucketWater,
	                                                                Character.valueOf('I'), BuildCraftCore.ironGearItem
	                                                                }); 
	    	
	    	//ModLoader.addShapelessRecipe(new ItemStack(module_Core.APSMetaBlockEnergy, 1, CollectorID), new Object[] { new ItemStack(BuildCraftCore.wrenchItem), new ItemStack(module_Core.APSKitItem,1,CollectorID + ItemEnergy) });
	    	
	    	// -- Blocks
	    	blockCollector = new CollectorBlock(blockCollectorID, Material.iron);
	    	AssemblyRecipe.assemblyRecipes.add(new AssemblyRecipe(new ItemStack[] {
		                                                            new ItemStack(collectorKitItem, 1)},
		                                                            7500, 
		                                                            new ItemStack(blockCollector, 1)
		                                                            ));
	}

	@Override
	public void clientInit() { }

	@Override
	public void postInit() { }
	
}

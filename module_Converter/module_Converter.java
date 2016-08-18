package aps.module_Converter;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import aps.BuildcraftAPS;
import aps.module;
import aps.core.module_Core;
import aps.core.staticVariables.IDs;
import aps.module_Converter.blocks.LiquidEnergyBlock;
import aps.module_Converter.items.BottlerKitItem;
import aps.module_Converter.items.CondencerKitItem;
import aps.module_Converter.items.ConduitKitItem;
import buildcraft.BuildCraftFactory;
import buildcraft.BuildCraftTransport;
import cpw.mods.fml.common.Loader;

public class module_Converter extends module {
	public static int ICEnergyOutput;
	
	static boolean[] modsInstalled = new boolean[] { false, false, false }; // Ids = Industrial Craft, universal electricity, factorization   
	
	//Static constants
	public static float energyLoss = 0.9F; //%10 energy loss when converting
	public static float energyToLiquidRatio = 0.05F;
	public static float minimumEnergyToConvert = 100.0F; //minimum amount of energy needed to convert to liquid
	public static int maxConduitOutput = 200;
	
	public static Fluid liquidEnergy = null;
	public static Block liquidEnergyBlock;
	public static int liquidEnergyBlockID;
	
	public static Item itemCondenserKit;
	public static int itemCondenserKitID;
	
	public static Item itemConduitKit;
	public static int itemConduitKitID;
	
	public static Item itemBottlerKit;
	public static int itemBottlerKitID;
	
	public static Item itemSolidifierKit;
	public static int itemSolidifierKitID;

	
	@Override
	public void preInit() {
		//ICEnergyOutput = Integer.parseInt(BuildcraftAPS.Conf.get("energy", "IC2ConversionRatio", 20).value);
		
		itemCondenserKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "kitCondencer.ID", IDs.itemCondenserKit).getInt();
		itemConduitKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "kitConduit.ID", IDs.itemConduitKit).getInt();
		itemBottlerKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "kitBottler.ID", IDs.itemBottlerKit).getInt();
		itemSolidifierKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "kitSolidifier.ID", IDs.itemSolidifierKit).getInt();
		
		
		liquidEnergyBlockID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_BLOCK, "LiquidEnergyBlock.ID", IDs.LiquidEnergy).getInt();
		liquidEnergy = new Fluid("liquidEnergy");
		FluidRegistry.registerFluid(liquidEnergy);
		liquidEnergyBlock = new LiquidEnergyBlock(liquidEnergyBlockID, liquidEnergy, Material.water);
	}

	@Override
	public void clientInit() {
		liquidEnergy.setIcons(liquidEnergyBlock.getBlockTextureFromSide(1));
	}

	@Override
	public void initialize() 
	{
		//Verify installed mods
		if (Loader.isModLoaded("IC2")) { modsInstalled[0] = true;}
		
		//debugging
		//modsInstalled[0] = true;
		
		//######################### Condenser
			
			itemCondenserKit = new CondencerKitItem(itemCondenserKitID);
			itemCondenserKit.setUnlocalizedName("itemCondenserKit");
		
			ModLoader.addRecipe(new ItemStack(itemCondenserKit, 1), new Object[]{ "oPo", "DBD", "oTo", 
				Character.valueOf('o'), Block.obsidian,
				Character.valueOf('P'), BuildCraftTransport.pipePowerGold,
				Character.valueOf('D'), new ItemStack(module_Core.APSPowerCoreItem,1,3),
				Character.valueOf('B'), Block.pistonBase,
				Character.valueOf('T'), BuildCraftFactory.tankBlock
				});
			
		//######################### Energy Transfer Conduit
			
			itemConduitKit = new ConduitKitItem(itemConduitKitID);
			itemConduitKit.setUnlocalizedName("itemConduitKit");
			
			ModLoader.addRecipe(new ItemStack(itemConduitKit, 1), new Object[]{ "oTo", "PGP", "ooo",
				Character.valueOf('o'), Block.obsidian,
				Character.valueOf('T'), BuildCraftFactory.tankBlock,
				Character.valueOf('P'), BuildCraftTransport.pipePowerGold,
				Character.valueOf('G'), new ItemStack(module_Core.APSPowerCoreItem,1,2)
				});
			
		//######################### Liquid Energy Bottler Machine
			
			itemBottlerKit = new BottlerKitItem(itemBottlerKitID);
			itemBottlerKit.setUnlocalizedName("itemBottlerKit");
			
			ModLoader.addRecipe(new ItemStack(itemBottlerKit, 1), new Object[]{ "oWo", "ITI", "oPo",
				Character.valueOf('o'), Block.obsidian,
				Character.valueOf('W'), BuildCraftTransport.pipeFluidsGold,
				Character.valueOf('I'), new ItemStack(module_Core.APSPowerCoreItem,1,1),
				Character.valueOf('T'), BuildCraftFactory.tankBlock,
				Character.valueOf('P'), BuildCraftTransport.pipePowerGold
				});
			
		//######################### Solidifier
			
			
		//######################### Buildcraft MJ -> IC2 EU converter
			/*if (modsInstalled[0]) //if industrialcraft in installed
			{
				ModLoader.addRecipe(new ItemStack(module_Core.APSKitItem, 1, ICOutputID + ItemMachine), new Object[] { "ss ", "   ", "   ",
																													   Character.valueOf('s'), Block.stone
																													   });
				AssemblyRecipe.assemblyRecipes.add(new AssemblyRecipe(new ItemStack[] {
																		new ItemStack(module_Core.APSKitItem,1,ICOutputID + ItemMachine)},
																		7500,
																		new ItemStack(module_Core.APSMetaBlockMachine,1,ICOutputID)
																		));
			}
			*/
			//module_Core.AddMetaSubblock(ICOutputID, TileEntityICEProduction.class, "ic2production", "IC2 Energy Converter", APSBlockTypes.Machine);
	}
	
	@Override
	public void postInit()  {}
}

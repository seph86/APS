package aps.module_Machines;

//Java
import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import aps.BuildcraftAPS;
import aps.module;
import aps.core.createPipeUtil;
import aps.core.module_Core;
import aps.core.items.ItemAPSKit;
import aps.core.staticVariables.IDs;
import aps.module_Machines.items.EnergyDirectorKitItem;
import aps.module_Machines.items.EnergyStoreKitItem;
import aps.module_Machines.items.ExtractorKitItem;
import aps.module_Machines.items.FurnaceKitItem;
import aps.module_Machines.items.GrinderKitItem;
import aps.module_Machines.items.MagmafierKitItem;
import aps.module_Machines.items.MaterializerKitItem;
import aps.module_Machines.triggers.APSMachineActionProvider;
import aps.module_Machines.triggers.APSMachineTriggerProvider;
import buildcraft.BuildCraftCore;
import buildcraft.BuildCraftEnergy;
import buildcraft.BuildCraftTransport;
import buildcraft.api.gates.ActionManager;
import buildcraft.transport.ItemPipe;
//Vanilla Minecraft
//Buildcraft
//APS



public class module_Machines extends module
{	
	/*public static Trigger energyStoreFullTrigger;
	public static Trigger energyStoreEmptyTrigger;
	public static Trigger energyStorePartFilledTrigger;
	public static Trigger BlastFurnaceFullTrigger;
	public static Trigger BlastFurnaceEmptyTrigger;
	public static Trigger BlastFurnacePartFilledTrigger;
	
	public static Action directorDisableNorth;
	public static Action directorDisableSouth;
	public static Action directorDisableEast;
	public static Action directorDisableWest;
	public static Action directorEnableNorth;
	public static Action directorEnableSouth;
	public static Action directorEnableEast;
	public static Action directorEnableWest;*/
	
	public static LinkedList<GrinderRecipe> grinderRecipes = new LinkedList<GrinderRecipe>();
	public static LinkedList<Block> MeltableBlocks = new LinkedList<Block>();
	public static GrinderRecipe ExtractorProducts;
	
	int ItemIDPipeExtension;
	ItemPipe pipeExtension;
	
	int energyStoreKitID;
	ItemAPSKit energyStoreKit;
	int energyDirectorKitID;
	ItemAPSKit energyDirectorKit;
	int grinderKitID;
	ItemAPSKit grinderKit;
	int magmafierKitID;
	ItemAPSKit magmafierKit;
	int extractorKitID;
	ItemAPSKit extractorKit;
	int furnaceKitID;
	ItemAPSKit furnaceKit;
	int materializerKitID;
	ItemAPSKit materializerKit;
	
	public module_Machines() {}

	@Override
	public void preInit() {
		ItemIDPipeExtension = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "APSPipeExtension.ID", IDs.PipeExtension).getInt();
		pipeExtension = createPipeUtil.createPipe(ItemIDPipeExtension, APSPipeExtension.class, "Extension Power Pipe");
		
		energyStoreKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "energyStoreKit.ID", IDs.itemStoreKit).getInt();
		energyDirectorKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "energyDirectorKit.ID", IDs.itemStoreKit).getInt();
		grinderKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "grinderKit.ID", IDs.itemStoreKit).getInt();
		magmafierKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "magmafierKit.ID", IDs.itemStoreKit).getInt();
		furnaceKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "furnaceKit.ID", IDs.itemStoreKit).getInt();
		materializerKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "materializerKit.ID", IDs.itemStoreKit).getInt();
	}
	
	@Override
	public void initialize()
	{	
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Block.gravel), new ItemStack(Item.flint), 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.flint), new ItemStack(Item.gunpowder), .75f, 25));
		
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Block.sandStone), new ItemStack(Block.sand, 4), 100, 50));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Block.brick), new ItemStack(Item.brick, 4), 100, 75));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Block.planks), new ItemStack(Item.stick, 5), 100, 50));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Block.cloth), new ItemStack[]{
																new ItemStack(Item.silk, 1),
																new ItemStack(Item.silk, 2),
																new ItemStack(Item.silk, 4)
																}, new float[]{
																25,
																25,
																25
																}, 50));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Block.dirt), new ItemStack[]{
																new ItemStack(Block.cobblestone),
																new ItemStack(Block.stone),
																new ItemStack(Item.coal),
																new ItemStack(Block.oreIron)
																}, new float[]{
																20,
																4,
																0.75f,
																0.25f
																}, 50));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Block.cobblestone), new ItemStack[]{
																new ItemStack(Block.gravel),
																new ItemStack(Item.coal),
																new ItemStack(Block.oreIron),
																new ItemStack(Item.redstone)
																}, new float[]{
																4.5f,
																2.5f,
																2,
																0.5f
																}, 75));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Block.stone), new ItemStack[]{
			                                                    new ItemStack(Block.gravel),
																new ItemStack(Item.ingotIron),
																new ItemStack(Item.redstone)
																}, new float[]{
																25,
																0.5f,
																0.5f
																}, 75));
		
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.pickaxeWood), new ItemStack[]{new ItemStack(Block.planks, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.axeWood), new ItemStack[]{new ItemStack(Block.planks, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.swordWood), new ItemStack[]{new ItemStack(Block.planks, 1)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.hoeWood), new ItemStack[]{new ItemStack(Block.planks, 1)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.pickaxeStone), new ItemStack[]{new ItemStack(Block.cobblestone, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.axeStone), new ItemStack[]{new ItemStack(Block.cobblestone, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.swordStone), new ItemStack[]{new ItemStack(Block.cobblestone, 1)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.hoeStone), new ItemStack[]{new ItemStack(Block.cobblestone, 1)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.pickaxeIron), new ItemStack[]{new ItemStack(Item.ingotIron, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.axeIron), new ItemStack[]{new ItemStack(Item.ingotIron, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.swordIron), new ItemStack[]{new ItemStack(Item.ingotIron, 1)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.hoeIron), new ItemStack[]{new ItemStack(Item.ingotIron, 1)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.pickaxeGold), new ItemStack[]{new ItemStack(Item.ingotGold, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.axeGold), new ItemStack[]{new ItemStack(Item.ingotGold, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.swordGold), new ItemStack[]{new ItemStack(Item.ingotGold, 1)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.hoeGold), new ItemStack[]{new ItemStack(Item.ingotGold, 1)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.pickaxeDiamond), new ItemStack[]{new ItemStack(Item.diamond, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.axeDiamond), new ItemStack[]{new ItemStack(Item.diamond, 2)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.swordDiamond), new ItemStack[]{new ItemStack(Item.diamond, 1)}, 100, 25));
		grinderRecipes.add(new GrinderRecipe(new ItemStack(Item.hoeDiamond), new ItemStack[]{new ItemStack(Item.diamond, 1)}, 100, 25));
		ExtractorProducts = new GrinderRecipe(null, new ItemStack[]{
																new ItemStack(Item.ingotIron),
																new ItemStack(Item.redstone),
																new ItemStack(Block.obsidian),
																new ItemStack(Item.ingotGold),
																new ItemStack(Item.diamond)
																}, new float[]{
																0.046875f,
																0.025f,
																0.009375f,
																0.00625f,
																0.003125f
																}, 0);
		
		MeltableBlocks.add(Block.stone);
		MeltableBlocks.add(Block.cobblestone);
		MeltableBlocks.add(Block.gravel);
		MeltableBlocks.add(Block.dirt);
		//MeltableBlocks.add(Block.netherrack);
		//MeltableBlocks.add(Block.slowSand);
		
		/*energyStoreFullTrigger = new TriggerMachines(90, TriggerMachines.TriggerType.EnergyStoreFull);
		energyStoreEmptyTrigger = new TriggerMachines(91, TriggerMachines.TriggerType.EnergyStoreEmpty);
		energyStorePartFilledTrigger = new TriggerMachines(92, TriggerMachines.TriggerType.EnergyStorePartFilled);
		BlastFurnaceFullTrigger = new TriggerMachines(93, TriggerMachines.TriggerType.BlastFurnaceBlockFull);
		BlastFurnaceEmptyTrigger = new TriggerMachines(94, TriggerMachines.TriggerType.BlastFurnaceBlockEmpty);
		BlastFurnacePartFilledTrigger = new TriggerMachines(95, TriggerMachines.TriggerType.BlastFurnaceBlockPartFilled);
		
		directorDisableNorth = new ActionDirector(110, ActionDirector.Mode.disableNorth);
		directorDisableSouth = new ActionDirector(111, ActionDirector.Mode.disableSouth);
		directorDisableEast = new ActionDirector(112, ActionDirector.Mode.disableEast);
		directorDisableWest = new ActionDirector(113, ActionDirector.Mode.disableWest);
		directorEnableNorth = new ActionDirector(114, ActionDirector.Mode.enableNorth);
		directorEnableSouth = new ActionDirector(115, ActionDirector.Mode.enableSouth);
		directorEnableEast = new ActionDirector(116, ActionDirector.Mode.enableEast);
		directorEnableWest = new ActionDirector(117, ActionDirector.Mode.enableWest);*/
		
		
		MaterializerWhitelist.whitelist.add(new MaterializerWhitelist(Item.ingotIron, 1500, 1.0f));
		
		
		ActionManager.registerTriggerProvider(new APSMachineTriggerProvider());
		ActionManager.registerActionProvider(new APSMachineActionProvider());
		
		
		//####################Energy Store
			// -- Kit item
			energyStoreKit = new EnergyStoreKitItem(energyStoreKitID);
		
	    	// -- Kit recipe
	    	ModLoader.addRecipe(new ItemStack(energyStoreKit, 1), new Object[]{ "owo", "ICI", "oco",
	                                                                Character.valueOf('o'), Block.obsidian,
	                                                                Character.valueOf('w'), BuildCraftTransport.pipePowerWood,
	                                                                Character.valueOf('C'), new ItemStack(module_Core.APSPowerCoreItem, 1, 1),
	                                                                Character.valueOf('c'), BuildCraftTransport.pipePowerGold,
	                                                                Character.valueOf('I'), Item.ingotIron
	                                                                });
    	                                                                
    	//####################Energy Director
	    	// -- Kit item
	    	energyDirectorKit = new EnergyDirectorKitItem(energyDirectorKitID);
	    	
	    	// -- Kit
	    	ModLoader.addRecipe(new ItemStack(energyDirectorKit, 1), new Object[]{ "owo", "wCw", "owo",
	                                                                Character.valueOf('o'), Block.obsidian,
	                                                                Character.valueOf('w'), BuildCraftTransport.pipePowerWood,
	                                                                Character.valueOf('C'), new ItemStack(module_Core.APSPowerCoreItem, 1, 3)
	                                                                });
    	                                       
    	//####################Mineral Extractor
	    	// -- Kit item
			extractorKit = new ExtractorKitItem(extractorKitID);
	    				
	    	// -- Kit
	    	ModLoader.addRecipe(new ItemStack(extractorKit, 1), new Object[]{ "oEo", "BGB", "oCo",
	                                                                Character.valueOf('o'), Block.obsidian,
	                                                                Character.valueOf('E'), new ItemStack(BuildCraftEnergy.engineBlock, 1, 2),
	                                                                Character.valueOf('B'), Item.bucketEmpty,
	                                                                Character.valueOf('G'), BuildCraftCore.goldGearItem,
	                                                                Character.valueOf('C'), new ItemStack(module_Core.APSPowerCoreItem, 1, 2)
	                                                                });
    	
    	//####################Block Grinder
	    	// -- Kit item
	    	grinderKit = new GrinderKitItem(grinderKitID);
	    	
	    	// -- Kit
	    	ModLoader.addRecipe(new ItemStack(grinderKit, 1), new Object[]{ "oSo", "G W", "oCo",
	                                                                Character.valueOf('o'), Block.obsidian,
	                                                                Character.valueOf('S'), BuildCraftTransport.pipeItemsStone,
	                                                                Character.valueOf('G'), BuildCraftCore.ironGearItem,
	                                                                Character.valueOf('W'), BuildCraftTransport.pipeItemsWood,
	                                                                Character.valueOf('C'), new ItemStack(module_Core.APSPowerCoreItem, 1, 1)
	                                                                });
	    	
    	
    	//####################Blast Furnace / Magmafier
	    	// -- Kit item
	    	magmafierKit = new MagmafierKitItem(magmafierKitID);
	    	
	    	// -- Kit
	    	ModLoader.addRecipe(new ItemStack(magmafierKit, 1), new Object[]{ "oSo", "CFL", "owo",
	                                                                Character.valueOf('o'), Block.obsidian,
	                                                                Character.valueOf('S'), BuildCraftTransport.pipeItemsStone,
	                                                                Character.valueOf('C'), new ItemStack(module_Core.APSPowerCoreItem, 1, 2),
	                                                                Character.valueOf('F'), Block.furnaceIdle,
	                                                                Character.valueOf('L'), BuildCraftTransport.pipeFluidsGold,
	                                                                Character.valueOf('w'), BuildCraftTransport.pipePowerGold
	                                                                });
    	
    	//####################Powered Furnace
	    	// -- Kit item
	    	furnaceKit = new FurnaceKitItem(furnaceKitID);
	    				
	    	// -- Kit
	    	ModLoader.addRecipe(new ItemStack(furnaceKit, 1), new Object[]{ "oIo", "iFW", "owo",
	                                                                Character.valueOf('o'), Block.obsidian,
	                                                                Character.valueOf('w'), BuildCraftTransport.pipePowerWood,
	                                                                Character.valueOf('i'), BuildCraftCore.ironGearItem,
	                                                                Character.valueOf('F'), Block.furnaceIdle,
	                                                                Character.valueOf('W'), BuildCraftTransport.pipeItemsWood,
	                                                                Character.valueOf('I'), Item.ingotIron
	                                                                });
    	
    	//##################### Materialiser
	    	// -- Kit item
	    	materializerKit = new MaterializerKitItem(materializerKitID);
	    	
	    	// -- Kit
	    	ModLoader.addRecipe(new ItemStack(materializerKit, 1), new Object[]{ "d  ", "   ", "   ",
	    		Character.valueOf('d'), Block.dirt
	    	});
	}
	
	@Override
	public void clientInit() {
		//MinecraftForgeClient.registerItemRenderer(pipeExtension.itemID, TransportProxyClient.pipeItemRenderer);
	}
	
	public void postInit() {}
	
	public static boolean isMeltable(ItemStack stack)
	{
		if(stack.itemID > 255)
			return false;
		return MeltableBlocks.contains(Block.blocksList[stack.itemID]);
	}
}

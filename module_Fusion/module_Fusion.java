package aps.module_Fusion;

//open gl
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.Icon;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import aps.BuildcraftAPS;
import aps.module;
import aps.core.createPipeUtil;
import aps.core.module_Core;
import aps.core.staticVariables.IDs;
import aps.module_Fusion.items.ItemHeavyWater;
import aps.module_Fusion.items.ItemHeavyWaterBucket;
import aps.module_Fusion.items.ItemTokamakChamber;
import aps.module_Fusion.items.ItemTokamakControl;
import aps.module_Fusion.items.ItemTokamakExtractor;
import aps.module_Fusion.items.ItemTokamakInput;
import aps.module_Fusion.items.ItemTokamakOutput;
import aps.module_Fusion.items.ItemTokamakPlating;
import aps.core.proxy.APSCoreProxy;
import aps.module_Fusion.tileEntities.TileEntityFusionBlock;
import aps.module_Fusion.tileEntities.TileEntityFusionCore;
import buildcraft.BuildCraftCore;
import buildcraft.BuildCraftEnergy;
import buildcraft.BuildCraftFactory;
import buildcraft.BuildCraftTransport;
import buildcraft.api.gates.ActionManager;
import buildcraft.api.recipes.AssemblyRecipe;
import buildcraft.core.proxy.CoreProxy;
import buildcraft.core.utils.Utils;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.ItemPipe;
import buildcraft.transport.Pipe;
import buildcraft.transport.TransportProxyClient;

public class module_Fusion extends module
{	
	int ItemIdTokamakPlating;
	int ItemIdTokamakChamber;
	int ItemIdTokamakControl;
	int ItemIdTokamakExtractor;
	int ItemIdTokamakInput;
	int ItemIdTokamakOutput;
	int ItemIdPipeLapis;
	int ItemIdHeavyWaterBucket;
	int ItemIdHeavyWater;
	int ItemIdEnergyReader;
	
	int ItemIDPipe;
	
	public static Item tokamakPlatingItem = null;
	public static Item tokamakChamberItem = null;
	public static Item tokamakControlItem = null;
	public static Item tokamakExtractorItem = null;
	public static Item tokamakInputItem = null;
	public static Item tokamakOutputItem = null;
	
	public static Item heavyWaterBucketItem = null;
	public static Item heavyWater = null;
	public static Fluid heavyWaterFluid;
	
	public static ItemPipe advancedPowerOutput = null;
	
	public static int FusionMultiblockID;
	public static Block FusionMultiblock;
	
	/*public static Trigger tokamakIdlingTrigger;
	public static Trigger tokamakTempReachedTrigger;
	public static Trigger tokamakOutputQuaterTrigger;
	public static Trigger tokamakOutputHalfTrigger;
	public static Trigger tokamakOutput3QuatersTrigger;
	public static Trigger tokamakOutputMaxTrigger;
	
	public static Trigger pipeCloggedTrigger;
	public static Trigger pipeFreeTrigger;
	
	public static Action fusionActionIdle;*/
	
	public static int MaxTemp;
	public static int CoolRate;
	public static float FusionFraction;
	
	public module_Fusion() {}
	
	@Override
	public void preInit()
	{
		//config
		MaxTemp = BuildcraftAPS.Conf.get("Fusion", "MaxTemperature", 10000000).getInt();
		CoolRate = BuildcraftAPS.Conf.get("Fusion", "CoolRate", 1000).getInt();
		FusionFraction = (float)BuildcraftAPS.Conf.get("Fusion", "TokamakFusionTemperaturePercentage", 60).getInt() / (float) 100;
		
		ItemIdTokamakPlating = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "TokamakPlating.ID", IDs.TokamakPlating).getInt();
		ItemIdTokamakChamber = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "TokamakChamber.ID", IDs.TokamakChamber).getInt();
		ItemIdTokamakControl = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "TokamakControl.ID", IDs.TokamakControl).getInt();
		ItemIdTokamakExtractor = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "TokamakExtractor.ID", IDs.TokamakExtractor).getInt();
		ItemIdTokamakInput = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "TokamakInput.ID", IDs.TokamakInput).getInt();
		ItemIdTokamakOutput = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "TokamakOutput.ID", IDs.TokamakOutput).getInt();
		ItemIDPipe = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "AdvancedOutputPipeGrade1.ID", IDs.AdvancedOutputPipe).getInt();
		ItemIdHeavyWaterBucket = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "HeavyWaterBucket.ID",IDs.HeavyWaterBucket).getInt();
		ItemIdHeavyWater = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "HeavyWater.ID", IDs.HeavyWater).getInt();
		
		//advancedPowerOutput = createPipeUtil.createPipe(ItemIDPipe, APSPipePower.class, "Advanced Output Pipe");
		
		FusionMultiblockID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_BLOCK, "BlockFusion.ID", IDs.blockFusion).getInt();
	}
	
	@Override
	public void initialize()
	{	
		tokamakPlatingItem = (new ItemTokamakPlating(ItemIdTokamakPlating));
		tokamakChamberItem = (new ItemTokamakChamber(ItemIdTokamakChamber));
		tokamakControlItem = (new ItemTokamakControl(ItemIdTokamakControl));
		tokamakExtractorItem = (new ItemTokamakExtractor(ItemIdTokamakExtractor));
		tokamakInputItem = (new ItemTokamakInput(ItemIdTokamakInput));
		tokamakOutputItem = (new ItemTokamakOutput(ItemIdTokamakOutput));
		
		heavyWaterBucketItem = (new ItemHeavyWaterBucket(ItemIdHeavyWaterBucket)).setContainerItem(Item.bucketEmpty);
		heavyWater = (new ItemHeavyWater(ItemIdHeavyWater));
		
		/*tokamakIdlingTrigger = new TriggerTokamak(99, TriggerTokamak.TriggerType.TokamakIdling);
		tokamakTempReachedTrigger = new TriggerTokamak(100, TriggerTokamak.TriggerType.TokamakTempReached);
		tokamakOutputQuaterTrigger = new TriggerTokamak(101, TriggerTokamak.TriggerType.TokamakOutputQuater);
		tokamakOutputHalfTrigger = new TriggerTokamak(102, TriggerTokamak.TriggerType.TokamakOutputHalf);
		tokamakOutput3QuatersTrigger = new TriggerTokamak(103, TriggerTokamak.TriggerType.TokamakOutput3Quaters);
		tokamakOutputMaxTrigger = new TriggerTokamak(104, TriggerTokamak.TriggerType.TokamakOutputMax);
		
		pipeCloggedTrigger = new TriggerAdvancedPipe(105, TriggerAdvancedPipe.TriggerType.pipeClogged);
		pipeFreeTrigger = new TriggerAdvancedPipe(106, TriggerAdvancedPipe.TriggerType.pipeFree);
		
		fusionActionIdle = new ActionFusion(107, ActionFusion.FusionMode.Idle);*/
		
		ActionManager.registerTriggerProvider(new APSFusionTriggerProvider());
		ActionManager.registerActionProvider(new APSFusionActionProvider());
		
		//####################Tokamak Plating
		ModLoader.addName(tokamakPlatingItem, "Tokamak Plating");
    	ModLoader.addRecipe(new ItemStack(tokamakPlatingItem), new Object[]{ "DIO", "DIO", "DIO",
    															Character.valueOf('D'), Item.diamond,
    															Character.valueOf('I'), Item.ingotIron,
    															Character.valueOf('O'), Block.obsidian
    															});
    	
    	//####################Tokamak Chamber
    	ModLoader.addName(tokamakChamberItem, "Tokamak Chamber");
    	ModLoader.addRecipe(new ItemStack(tokamakChamberItem), new Object[]{ "IPI", "P P", "IPI",
    															Character.valueOf('I'), Item.ingotIron,
    															Character.valueOf('P'), tokamakPlatingItem
    															});
    	
    	//####################Control Module
    	ModLoader.addName(tokamakControlItem, "Control Module");
    	ModLoader.addRecipe(new ItemStack(tokamakControlItem), new Object[]{ "IrI", "rCr", "IrI",
    	                                                        Character.valueOf('I'), Item.ingotIron,
    	                                                        Character.valueOf('C'), new ItemStack(module_Core.APSPowerCoreItem, 1, 3),
    	                                                        Character.valueOf('r'), Item.redstoneRepeater
    	                                                        });
    	
    	//####################Deuterium Extractor
    	ModLoader.addName(tokamakExtractorItem, "Deuterium Extractor");
    	ModLoader.addRecipe(new ItemStack(tokamakExtractorItem), new Object[]{ "ClC", "ere", "clc",
    															Character.valueOf('C'), new ItemStack(module_Core.APSPowerCoreItem, 1, 1),
    															Character.valueOf('l'), BuildCraftTransport.pipeFluidsGold,
    															Character.valueOf('e'), new ItemStack (BuildCraftEnergy.engineBlock, 1, 2),
    															Character.valueOf('r'), BuildCraftFactory.refineryBlock,
    															Character.valueOf('c'), BuildCraftTransport.pipePowerGold
    															});
    	
    	//####################Input Module
    	ModLoader.addName(tokamakInputItem, "Input Module");
    	ModLoader.addRecipe(new ItemStack(tokamakInputItem), new Object[]{ "DcD", "Rcl", "Rcl",
    															Character.valueOf('D'), Item.diamond,
    															Character.valueOf('c'), BuildCraftTransport.pipePowerGold,
    															Character.valueOf('R'), Item.redstone,
    															Character.valueOf('l'), BuildCraftTransport.pipeFluidsGold
    															});
    	
    	//####################Output Module
    	ModLoader.addName(tokamakOutputItem, "Output Module");
    	ModLoader.addRecipe(new ItemStack(tokamakOutputItem), new Object[]{ "Rcd", "Rcd", "DwD",
    															Character.valueOf('R'), Item.redstone,
    															Character.valueOf('c'), BuildCraftTransport.pipePowerGold,
    															Character.valueOf('d'), BuildCraftCore.diamondGearItem,
    															Character.valueOf('w'), BuildCraftTransport.pipePowerWood,
    															Character.valueOf('D'), Item.diamond
    															});
    	
    	//####################Heavy Water Bucket
    	ModLoader.addName(heavyWaterBucketItem, "Heavy Water Bucket");
    	
    	
    	//####################Heavy Water
    	heavyWaterFluid = new Fluid("heavyWater");
    	FluidRegistry.registerFluid(heavyWaterFluid);
    	
    	
    	
		//#################### Power Pipes
    	
    	
    	//#################### Fusion Generator Multiblock
    	
    	
	}
	
	@Override
	public void clientInit()
	{
		//Pipe renderer
		//MinecraftForgeClient.registerItemRenderer(pipePowerLapis.shiftedIndex, TransportProxyClient.pipeItemRenderer);
		//MinecraftForgeClient.registerItemRenderer(advancedPowerOutput.itemID, TransportProxyClient.pipeItemRenderer);
	}
	
	public void renderInventory(RenderBlocks renderblocks, int itemID,int meta) {
		Tessellator tessellator = Tessellator.instance;

		Block block = BuildCraftTransport.genericPipeBlock;
		Icon icon = ((ItemPipe) Item.itemsList [itemID]).getIconFromDamage(0);
		
		block.setBlockBounds(Utils.pipeMinPos, 0.0F, Utils.pipeMinPos,
				Utils.pipeMaxPos, 1.0F, Utils.pipeMaxPos);
		block.setBlockBoundsForItemRender();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		
	}
	
	public void postInit() {}
}

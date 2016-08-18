package aps.core;

//forge
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import aps.BuildcraftAPS;
import aps.module;
import aps.core.blocks.APSBlockMachine;
import aps.core.gui.APSPipeIconProvider;
import aps.core.gui.GuiHandler;
import aps.core.items.ItemAPSKit;
import aps.core.items.ItemAPSMachineUpgrade;
import aps.core.items.ItemAPSPowerCore;
import aps.core.items.ItemMachineBlock;
import aps.core.items.KitCreativePower;
import aps.core.proxy.APSCoreProxy;
import aps.core.staticVariables.IDs;
import aps.core.tileEntities.TileEntityAPSMachine;
import aps.module_Converter.items.ItemBottledEnergy;
import aps.module_Converter.items.ItemInsulatedBottle;
import aps.module_Machines.items.ItemEnergyCrystal;
import buildcraft.BuildCraftCore;
import buildcraft.BuildCraftSilicon;
import buildcraft.api.core.IIconProvider;
import buildcraft.api.recipes.AssemblyRecipe;
import buildcraft.core.DefaultProps;
import buildcraft.core.proxy.CoreProxy;
import buildcraft.core.utils.Localization;
import buildcraft.transport.ItemPipe;
import buildcraft.transport.TransportProxyClient;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class module_Core extends module
{
	public static int ItemIdPowerCore;
	public static Item APSPowerCoreItem = null;
	public static int ItemIdMachineUpgrade;
	public static Item APSMachineUpgradeItem = null;
	
	
	public static Item insulatedBottle_empty = null;
	public static int insulatedBottle_emptyID;
	public static Item insulatedBottle_full = null;
	public static int insulatedBottle_fullID;
	
	public static int ItemIDPipeColoured;
	
	public static Item energyReader = null;
	public static int ItemIdEnergyReader;
	
	int energyCrystalID;
	public static Item EnergyCrystal = null;
	
	public static Block blockMachine;
	public static int blockMachineID;
	
	public static Item freeEnergyKit;
	public static int freeEnergyKitID;
	
	//public static GuiEnergyReader guiEnergyReader;
	
	public static IIconProvider apsPipeIconProvider;
	
	public module_Core() {}
	
	@Override
	public void preInit()
	{
		ItemIdPowerCore = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "PowerCore.ID", IDs.PowerCore).getInt();
		ItemIdMachineUpgrade = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "MachineUpgrade.ID", IDs.MachineUpgrade).getInt();
		
		insulatedBottle_emptyID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "InsulatedBottle.ID", IDs.InsulatedBottle).getInt();
		insulatedBottle_fullID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "BottledEnergy.ID", IDs.BottledEnergy).getInt();
		
		energyCrystalID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "EnergyCrystal.ID", IDs.EnergyCrystal).getInt();
		
		ItemIdEnergyReader = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "EnergyReader.ID", IDs.EnergyReader).getInt();
		
		ItemIDPipeColoured = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "APSPipeColoured", IDs.PipeColoured).getInt();
		
		blockMachineID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_BLOCK, "MachineBlock.ID", IDs.MachineBlock).getInt();
		
		freeEnergyKitID = BuildcraftAPS.Conf.get(Configuration.CATEGORY_ITEM, "FreeEnergyKit.ID", IDs.itemFreeEnergyKit).getInt();
		
		apsPipeIconProvider = new APSPipeIconProvider();
	}
	
	@Override
	public void initialize()
	{
		//Initialize GUIs for all modules (too lazy to convert all modules to individual loadable modules through FML)
		NetworkRegistry.instance().registerGuiHandler(BuildcraftAPS.instance, new GuiHandler());
		
		APSPowerCoreItem = new ItemAPSPowerCore(ItemIdPowerCore);
		APSPowerCoreItem.setUnlocalizedName("powerCore");
		
		APSMachineUpgradeItem = new ItemAPSMachineUpgrade(ItemIdMachineUpgrade);
		APSMachineUpgradeItem.setUnlocalizedName("machineUpgrade");
		
		blockMachine= new APSBlockMachine(blockMachineID, Material.iron);
		blockMachine.setUnlocalizedName("apsblockMachine");
		CoreProxy.proxy.registerBlock(blockMachine, ItemMachineBlock.class);
		
		GameRegistry.registerTileEntity(TileEntityAPSMachine.class, "net.minecraft.src.aps.core.tileEntities.TileEntityAPSMachine");
		
		freeEnergyKit = new KitCreativePower(freeEnergyKitID);
		freeEnergyKit.setUnlocalizedName("itemFreeEnergyKit");
		
		//####################Redstone Power Core
    	ModLoader.addRecipe(new ItemStack(APSPowerCoreItem, 1, 0), new Object[]{ "IrI", "GRG", "IrI",
    	                                                        Character.valueOf('I'), Item.ingotIron,
    	                                                        Character.valueOf('G'), BuildCraftCore.woodenGearItem,
    	                                                        Character.valueOf('r'), Item.redstone,
    	                                                        Character.valueOf('R'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 0)
    	                                                        });
    	
    	AssemblyRecipe.assemblyRecipes.add(new AssemblyRecipe(new ItemStack[] {
    																new ItemStack(Item.redstone,2),
    																new ItemStack(BuildCraftSilicon.redstoneChipset)},
    																3000,
    																new ItemStack(APSPowerCoreItem,1)
    																));                                                           
    	
    	
    	 //####################Iron Power Core
    	AssemblyRecipe.assemblyRecipes.add(new AssemblyRecipe(new ItemStack[] {
                                                                new ItemStack(APSPowerCoreItem, 1, 0), 
                                                                new ItemStack(Item.ingotIron, 2)}, 
                                                                5000, 
                                                                new ItemStack(APSPowerCoreItem, 1, 1)
                                                                ));
    	ModLoader.addRecipe(new ItemStack(APSPowerCoreItem, 1, 1), new Object[]{ "IrI", "rRr", "IGI",
                                                                Character.valueOf('I'), Item.ingotIron,
                                                                Character.valueOf('G'), BuildCraftCore.ironGearItem,
                                                                Character.valueOf('r'), Item.redstone,
                                                                Character.valueOf('R'), new ItemStack(BuildCraftSilicon.redstoneChipset, 1, 1)
                                                                });
    	
    	//####################Gold Power Core
    	AssemblyRecipe.assemblyRecipes.add(new AssemblyRecipe(new ItemStack[] {
                                                                new ItemStack(APSPowerCoreItem, 1, 1), 
                                                                new ItemStack(Item.ingotGold, 2)}, 
                                                                7500, 
                                                                new ItemStack(APSPowerCoreItem, 1, 2)
                                                                ));
    	                                                                
    	//####################Diamond Power Core
    	AssemblyRecipe.assemblyRecipes.add(new AssemblyRecipe(new ItemStack[] {
                                                                new ItemStack(APSPowerCoreItem, 1, 2), 
                                                                new ItemStack(Item.diamond, 2)}, 
                                                                10000, 
                                                                new ItemStack(APSPowerCoreItem, 1, 3)
                                                                ));
    	
    	//################### liquid energy containers
		insulatedBottle_empty = new ItemInsulatedBottle(insulatedBottle_emptyID).setUnlocalizedName("insulatedBottleEmpty");
		ModLoader.addRecipe(new ItemStack(insulatedBottle_empty, 1), new Object[]{ " o ", "ipi", " r ",
            Character.valueOf('i'), Item.ingotIron,
            Character.valueOf('o'), Block.obsidian,
            Character.valueOf('r'), Item.redstone,
            Character.valueOf('p'), Block.glass
            });
		
		insulatedBottle_full = new ItemBottledEnergy(insulatedBottle_fullID).setUnlocalizedName("insulatedBottleFull");
    	
		//################### Energy crystal
		EnergyCrystal = new ItemEnergyCrystal(energyCrystalID).setUnlocalizedName("energyCrystal");
    	
		//################### Energy Reader item
		//energyReader = (new ItemEnergyReader(ItemIdEnergyReader));
    	//APSCoreProxy.proxy.registerClientRenderer(); //used for the energy reader GUI
    	//TODO: Add recipe
	}
	
	@Override
	public void clientInit()
	{
		Localization.addLocalization("/aps/lang/", DefaultProps.DEFAULT_LANGUAGE);
		
		//Energy reader gui
		//guiEnergyReader = new GuiEnergyReader();
	}
	
	public void postInit() {}
	
}

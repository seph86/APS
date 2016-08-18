package aps;

import java.io.File;
import java.util.LinkedList;

import buildcraft.core.DefaultProps;
import buildcraft.core.utils.Localization;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import aps.core.module_Core;
import aps.core.gui.APSTab;
import aps.core.network.APSPacketHandler;
import aps.module_Converter.module_Converter;
import aps.module_Environment.module_Environment;
import aps.module_Fusion.module_Fusion;
import aps.module_Machines.module_Machines;
import aps.module_Solar.module_Solar;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

//forge loading
@Mod(name="Advanced Power Systems", version=Version.VERSION, modid="BuildcraftAPS", dependencies = "required-after:BuildCraft|Transport")
@NetworkMod(channels = {"APS"}, packetHandler = APSPacketHandler.class, clientSideRequired = true, serverSideRequired = true)
public class BuildcraftAPS  //update to FML, removed "mod_z" because of redundancy -Seph
{
	public static String imageFilesRoot = "/aps/gfx/"; //The APS graphics directory, basically where all the textures/GUIs are
	public static Configuration Conf; //Creates the config object to interact with the file
	
	public static boolean clientSide = false;
	
	public static CreativeTabs customTab = new APSTab("apsTab");
	
	LinkedList<module> modules = new LinkedList<module>(); //A linked-list used to hold instances of all enabled modules, see the 'APS Systems Info' file
	
	@Instance("BuildcraftAPS")
	public static BuildcraftAPS instance;
	
	public String getPriorities() {
		return "after:Forestry;after:Buildcraft|Transport";
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent evt)
	{
		Conf = new Configuration(new File(evt.getModConfigurationDirectory(), "buildcraft/APS.cfg"));
		
		load();
		preinitModules();
	}
	
	@Init
	public void initialize(FMLInitializationEvent env) // this is the new constructor required by FML
	{
		initializeModules();
		
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT){
			ClientInit();
		}
		
		Localization.addLocalization("/lang/APS/", DefaultProps.DEFAULT_LANGUAGE);
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent env)
	{
		postInitalizeModules();
	}
	
	public void ClientInit()
	{
		clientSide = true;
		for (module M : modules){
			System.out.println(M.getClass().getName()+": Initializing clientside");
			M.clientInit();
		}
	}
	
	/*public boolean onTickInGame(float f, Minecraft minecraft)
	{
		for(module M : modules)
		{
			M.OnTick(f, minecraft);
		}
		return true;
	}*/
	
	//run the pre-init methods to each module
	public void preinitModules()
	{
		for (module M : modules) {
			System.out.println(M.getClass().getName()+": Pre initializing");
			M.preInit();
		}
		Conf.save();
	}
	
	public void initializeModules()
	{
		//Conf.load();
		for(module M : modules)
		{
			System.out.println(M.getClass().getName() + ": Initializing");
			M.initialize();
		}
		//Conf.save();
	}
	
	public void postInitalizeModules()
	{
		for (module M : modules)
			M.postInit();
	}
	
	public void load()
	{
		Conf.load();
		
		modules.add(new module_Converter());
		/*modules.add(new module_Machines());
		modules.add(new module_Fusion());
		modules.add(new module_Solar());
		modules.add(new module_Environment());*/
		
		modules.addFirst(new module_Core());
		
		Conf.save();
	}
	
}
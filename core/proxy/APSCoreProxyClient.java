package aps.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class APSCoreProxyClient extends APSCoreProxy {

	@Override
	public void registerClientRenderer()
	{		
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}	
	
	@Override
	public void addName(Object obj, String s) {
		LanguageRegistry.instance().addName(obj, s);
	}
}

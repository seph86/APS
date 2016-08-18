package aps.core.proxy;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;

import aps.core.module_Core;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

	public Minecraft mc;
	
	public ClientTickHandler()
	{
		this.mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) { }

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.contains(TickType.RENDER))
		{
			//module_Core.guiEnergyReader.render();
			//mc.ingameGUI.drawString(mc.fontRenderer, "test string", mc.displayWidth / 2, mc.displayHeight / 2, 0xFFFFFF);
			//System.out.println("smomething");
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return null;
	}

}

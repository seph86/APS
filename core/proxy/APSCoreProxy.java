package aps.core.proxy;

import aps.BuildcraftAPS;
import aps.core.gui.GuiHandler;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.SidedProxy;

public class APSCoreProxy {
	@SidedProxy(clientSide = "aps.core.proxy.APSCoreProxyClient", serverSide="aps.core.proxy.APSCoreProxy")
	public static APSCoreProxy proxy;

	public void registerClientRenderer() 
	{
		
	}
	
	public void openGui(EntityPlayer player, int GuiID, TileEntity tile){
		player.openGui(BuildcraftAPS.instance, GuiID, tile.worldObj, tile.xCoord, tile.yCoord, tile.zCoord);
	}
	
	//###### Localization #####
	public void addName(Object obj, String s) {
	}
}

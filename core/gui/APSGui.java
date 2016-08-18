package aps.core.gui;

import org.lwjgl.opengl.GL11;

import buildcraft.core.DefaultProps;

import aps.core.module_Core;
import aps.core.guiContainer.APSContainer;
import aps.core.guiContainer.APSMachineContainer;
import aps.core.tileEntities.TileEntityAPSMachine;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class APSGui extends GuiContainer {

	public ResourceLocation TEXTURE = new ResourceLocation("aps", DefaultProps.TEXTURE_PATH_GUI + "/BlankGui.png");

	public APSGui(Container container) {
		super(container);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		setTexture();
        drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
	}

	//########## Custom code ##################
	
	public void setTexture() {
		if (TEXTURE == null) {
			System.out.println("APS: Error - texture not set for gui, forgot to set TEXTURE?");
			return;
		}
		setTexture(TEXTURE);
	}
	
	public void setTexture(ResourceLocation texture){
		mc.renderEngine.func_110577_a(texture);
	}
}

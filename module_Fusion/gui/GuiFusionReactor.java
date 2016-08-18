package aps.module_Fusion.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import aps.BuildcraftAPS;
import aps.core.guiContainer.APSContainer;
import aps.module_Fusion.containers.ContainerFusionReactor;
import aps.module_Fusion.tileEntities.TileEntityFusionCore;
import aps.module_Fusion.tileEntities.TileEntityFusionMultiblock;
import buildcraft.core.gui.BuildCraftContainer;
import buildcraft.core.gui.GuiBuildCraft;

public class GuiFusionReactor extends GuiContainer {

	String guiFile = BuildcraftAPS.imageFilesRoot + "MagmafierGUI.png";
	
	public GuiFusionReactor(TileEntityFusionCore tile, InventoryPlayer inventory) {
		super(new ContainerFusionReactor(tile, inventory));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		//int guiTex = mc.renderEngine.getTexture(guiFile);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //mc.renderEngine.bindTexture(guiTex);
        int topLeftX = (width - xSize) / 2;
        int topLeftY = (height - ySize) / 2;
        drawTexturedModalRect(topLeftX, topLeftY, 0, 0, xSize, ySize);
	}
}

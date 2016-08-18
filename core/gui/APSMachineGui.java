package aps.core.gui;

import java.util.LinkedList;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabSelectionHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import buildcraft.core.DefaultProps;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import aps.core.blocks.APSBlockMachine;
import aps.core.guiContainer.APSMachineContainer;
import aps.core.tileEntities.TileEntityAPSMachine;
import aps.core.tileEntities.TileEntityAPSMachine.SideType;

public class APSMachineGui extends APSGui {

	//y coords for tab on and off
	private static int tabOn = 0;
	private static int tabOff = 15;
	
	enum modes {main, operationConfig };
	private modes currentMode = modes.main;
	private GuiAPSButton[] tabs = {null,null};  ///Main, block config, kit / upgrades
	private APSMachineContainer container;
	
	public APSMachineGui(TileEntityAPSMachine tile, InventoryPlayer PInventory) {
		super(new APSMachineContainer(tile, PInventory));
		container = (APSMachineContainer)inventorySlots;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		//GuiAPSButton(int id, int xPos, int yPos, int texXPos, int texYPos, int texWidth, int texHeight, String tex, String label)
		int topLeftX = (width - xSize) / 2;
		int topLeftY = (height - ySize) / 2;
		
		
		for (int i = 0; i<2; i++) {
			tabs[i] = new GuiAPSButton(100 + i, topLeftX + ((47 * i) + 10), topLeftY - 12, 0, i == 0 ? tabOn : tabOff, 47, 15, DefaultProps.TEXTURE_PATH_GUI + "/APSmisc.png", "");
			buttonList.add(tabs[i]);
		}
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int x, int y) {
		if (currentMode == modes.main) {
			renderMain(x,y);
		} else if (currentMode == modes.operationConfig) {
			renderOperationConfig(x,y);
		}
	}
	
	
	private void renderMain(int x, int y) {
		
	}
	
	private void renderOperationConfig(int x, int y) {
		mc.renderEngine.func_110577_a(TextureMap.field_110575_b);
		
		//Get textures from tile entity
		this.drawTexturedModelRectFromIcon(40,32, APSBlockMachine.icons[0], 20, 20); //Front face
		this.drawTexturedModelRectFromIcon(10,32, APSBlockMachine.icons[0], 20, 20); //Left face
		this.drawTexturedModelRectFromIcon(70,32, APSBlockMachine.icons[0], 20, 20); //Right face
		this.drawTexturedModelRectFromIcon(70,58, APSBlockMachine.icons[0], 20, 20); //Back face
		this.drawTexturedModelRectFromIcon(40, 8, APSBlockMachine.icons[0], 20, 20); //Top face
		this.drawTexturedModelRectFromIcon(40,58, APSBlockMachine.icons[0], 20, 20); //Bottom face
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		super.actionPerformed(button);
		
		//tabs
		if (button.id >= 100 && button.id <= 101) {
			for (int i = 0;i<2;i++) {
				if ((button.id - 100) == i) {
					tabs[i].setTexYPos(tabOn);
					currentMode = modes.values()[i];
				} else {
					tabs[i].setTexYPos(tabOff);
				}
			}
			container.setSlotsForTab(currentMode.ordinal());
			
		}
	}

}

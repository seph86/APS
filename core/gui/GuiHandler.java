package aps.core.gui;

//Minecraft
import buildcraft.transport.TileGenericPipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

//Forge
import cpw.mods.fml.common.network.IGuiHandler;

import aps.core.guiContainer.APSContainer;
import aps.core.guiContainer.APSMachineContainer;
import aps.core.staticVariables.GuiIDs;
import aps.core.tileEntities.TileEntityAPSMachine;
//APS
import aps.module_Fusion.containers.ContainerFusionReactor;
import aps.module_Fusion.gui.GuiFusionReactor;
import aps.module_Fusion.tileEntities.TileEntityFusionCore;
import aps.module_Fusion.tileEntities.TileEntityFusionMultiblock;


public class GuiHandler implements IGuiHandler{

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if (!world.blockExists(x,y,z))
			return null;
		
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if (ID == GuiIDs.GUI_MACHINE) {
			if(!(tile instanceof TileEntityAPSMachine))
				return null;
			return new APSMachineGui((TileEntityAPSMachine)tile, player.inventory);
			
		} else if ( ID == GuiIDs.GUI_FUSIONREACTOR ) {
			if(!(tile instanceof TileEntityFusionMultiblock)) 
				return null;
			
			TileEntityFusionMultiblock multiblock = (TileEntityFusionMultiblock)tile;
			
			if (multiblock.isStructure && multiblock.controller != null) {		
				return new GuiFusionReactor(multiblock.controller, player.inventory);
			} else { 
				return null;
			}
		} else {
			
			return null;
			
		}
		
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if (!world.blockExists(x,y,z)) //&& !world.blockHasTileEntity(x, y, z))
			return null;
		
		
		TileEntity tile = world.getBlockTileEntity(x,y,z);
		
		
		if (ID == GuiIDs.GUI_MACHINE) {
			
			if (tile instanceof TileEntityAPSMachine)
				return new APSMachineContainer((TileEntityAPSMachine)tile, player.inventory);
			else
				return null;
			
		} else if ( ID == GuiIDs.GUI_FUSIONREACTOR ) {
			
			if (!(tile instanceof TileEntityFusionMultiblock) && !((TileEntityFusionMultiblock)tile).isStructure)
				return null;
			
			TileEntityFusionMultiblock multiblock = (TileEntityFusionMultiblock)tile;
			
			if (multiblock.controller instanceof TileEntityFusionCore) {
				
				return new ContainerFusionReactor(multiblock.controller, player.inventory);
				
			} else {
				
				return null;
				
			}
			
		} else {
			
			return null;
			
		}

	}
}

package aps.module_Fusion.tileEntities;

import java.util.LinkedList;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.core.network.PacketPayload;
import buildcraft.core.network.PacketUpdate;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import aps.core.module_Core;
import aps.core.staticVariables.GuiIDs;
import aps.core.tileEntities.TileEntityAPS;
import aps.core.tileEntities.TileEntityAPSMachine;
import aps.module_Fusion.module_Fusion;

public class TileEntityFusionMultiblock extends TileEntityAPS {
	
	// Metadata
	public static int multiblock = 0;
	public static int core = 1;
	public static int liquidInput = 2;
	public static int powerInput = 3;
	public static int powerOutput = 4;
	public static int data;
	
	// Core local variables
	public boolean isStructure = false;
	public boolean isController = false;
	public TileEntityFusionCore controller;
	public int multiblockID;
	public int orientation;
	
	//protected IPowerProvider powerProvider;
	
	
	public LinkedList<TileEntityFusionMultiblock>blocks;// = new LinkedList<TileEntityFusionMultiblock>();
	
	public TileEntityFusionMultiblock() {
		super();
		//this.hasGUI = true;
		
		//powerProvider = PowerFramework.currentFramework.createPowerProvider();
		//powerProvider.configure(1, 0, 100, 1, 100);
	}
	
	
	// ##################### Tile Entity #######################
	
	public void updateEntity() {
		
		if (worldObj.isRemote) return;
		
		sendNetworkUpdate();
	}
	
	
	public void readFromNBT(NBTTagCompound read) {
		super.readFromNBT(read);
		multiblockID = read.getInteger("multiblockID");
		orientation = read.getInteger("orientation");
	}
	
	public void writeToNBT(NBTTagCompound write) {
		super.writeToNBT(write);
		write.setInteger("multiblockID", multiblockID);
		write.setInteger("orientation", orientation);
	}
	
	// ################# Tile Entity APS #########################
	
	public void onTilePlaced(World world, int x, int y, int z) {
		if (world.isRemote) return;
		discoverStructure();
	}
	
	@Override
	public void kill() {
		
		if (this.worldObj.isRemote) return;
		
		if (this.isStructure) {
			if (this.isController) 
				this.breakStructure();
			else if (this.controller != null) 
				this.controller.breakStructure();
				
		}
	}
	
	public PacketPayload getPacketPayload(){
		return null;
	}
	
	public void handleUpdatePacket(PacketUpdate packet){
		
	}
	
	// ################ Multiblock functions #####################
	
	public boolean discoverStructure() {
		//Where to start discovering the structure from
		TileEntityFusionMultiblock startingBlock;
		
		//find lowest most south west connected block from this one. (assuming +Z direction is north)
		for (int x = 0 ; x < 4 ; x++) {
			if (!checkBlock(xCoord - (x + 1), yCoord, zCoord)){
				for (int y = 0 ; y < 4 ; y++) {
					if (!checkBlock(xCoord - x, yCoord - (y + 1), zCoord)) {
						for (int z = 0 ; z < 4 ; z++) {
							if (!checkBlock(xCoord - x, yCoord - y, zCoord - (z + 1))) {
								TileEntity tile = worldObj.getBlockTileEntity(xCoord - x, yCoord - y, zCoord - z); 
								if (tile instanceof TileEntityFusionMultiblock) {
									startingBlock = (TileEntityFusionMultiblock)tile;
									//System.out.println(startingBlock.xCoord + ", " + startingBlock.yCoord + ", " + startingBlock.zCoord);
									startingBlock.testAndCreateStructure();
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	private boolean checkBlock(int x, int y, int z) { //standard check
		return checkBlock(x,y,z,module_Fusion.FusionMultiblockID);
	}
	
	private boolean checkBlock(int x, int y, int z, int ID, int Meta) {
		return (worldObj.getBlockId(x, y, z) == ID && worldObj.getBlockMetadata(x, y, z) == Meta);
	}
	
	private boolean checkBlock(int x, int y, int z, int ID) {
		return (worldObj.getBlockId(x,y,z) == ID && worldObj.getBlockMetadata(x,y,z) != core);
	}
	
	private void testAndCreateStructure(){
		// Find orientation 
		if (checkBlock(xCoord, yCoord, zCoord + 3)) {//north south facing
			this.orientation = 1;
		} else if (checkBlock(xCoord + 3, yCoord, zCoord)) { //east west
			this.orientation = 2;
		} else if (checkBlock(xCoord, yCoord + 3, zCoord)) { //up down
			this.orientation = 3;
		} else {
			return; //invalid layout
		}
		
		// Find Struture
		
		int count = 0;
		
		LinkedList<TileEntityFusionMultiblock> temp = new LinkedList<TileEntityFusionMultiblock>();
		
		
		for (int y = 0; y < (this.orientation == 3 ? 4 : 3); y++){ //loop vertically  -Y-
			for (int z = 0; z < (this.orientation == 1 ? 4 : 3); z++) { //loop row (in the X+/- direction) x- <==|==> x+  -Z-
				for (int x = 0 ; x < (this.orientation == 2 ? 4 : 3); x++) { //loop column (Z+/-) x- <||||||> x+   -X-
					
					int posX = this.xCoord + x;
					int posY = this.yCoord + y;
					int posZ = this.zCoord + z;
					
					TileEntityFusionMultiblock tile = (TileEntityFusionMultiblock)worldObj.getBlockTileEntity(posX,posY,posZ);
					
					if ((this.orientation == 1 && (count == 16 || count == 19)) ||
						(this.orientation == 2 && (count == 17 || count == 18)) ||
						(this.orientation == 3 && (count == 13 || count == 22))
					) {
						if (!checkBlock(posX,posY,posZ,module_Fusion.FusionMultiblockID,core)) {
							return; //invalid block at place
						}
					}else{
						if (!checkBlock(posX,posY,posZ)) { 
							return; //invalid block
						}
					}
					
					if (tile.isStructure) return; //found block is already part of a structure
					
					tile.multiblockID = count;
					tile.orientation = this.orientation;
					temp.add(tile);
					count++;
				}
			}
		}
		
		//Everything is correct. Assemble multiblock.
		
		//this.blocks = new LinkedList<TileEntityFusionMultiblock>(temp);
		
		int controllerID;
		if (this.orientation == 1) controllerID = 16;
		else if (this.orientation == 2) controllerID = 17;
		else if (this.orientation == 3) controllerID = 13;
		else return;
		
		//set controller and everything else point to controller
		for (int i = 0; i < temp.size();i++) {
			TileEntityFusionMultiblock tile = temp.get(i);
			if (i == controllerID) {
				tile.isController = true;
			} else {
				tile.controller = (TileEntityFusionCore)temp.get(controllerID);
			}
			tile.isStructure = true;
			this.worldObj.notifyBlocksOfNeighborChange(tile.xCoord, tile.yCoord, tile.zCoord, 0);
			
		}
		
		this.controller.blocks = new LinkedList<TileEntityFusionMultiblock>(temp);
		
		//System.out.println("Fusion Generator constructed!");
	}
	
	public void breakStructure() {
		
		if (blocks == null) return;
		
		for (int i = 0; i < blocks.size(); i++) {
			TileEntityFusionMultiblock tile = blocks.get(i);
			if (tile != null) {
				tile.isStructure = false;
				tile.controller = null;
				this.worldObj.notifyBlocksOfNeighborChange(tile.xCoord, tile.yCoord, tile.zCoord, 0);
			}
		}
		
		this.isController = false;
		blocks.clear();
		//System.out.println("Fusion Generator Deconstructed");
	}
}

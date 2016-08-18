package aps.module_Environment;

//Java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import aps.module_Environment.tileEntities.TileEntityLifeVoid;
import net.minecraft.block.Block;
//Minecraft
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.Chunk;


public class LifeVoidManager {

	//List of all Life Void Machines and controllers
	public static LinkedList<LifeVoidTileData> machines = new LinkedList<LifeVoidTileData>();
	
	public static void add(TileEntityLifeVoid tile){
		LifeVoidTileData data = new LifeVoidTileData(tile);
		
		for (LifeVoidTileData machine : machines) {
			if (machine.equals(data)) return;
		}
		
		//Havent found a duplicate, adding to list
		machines.add(data);
	}
	
	public static void remove(TileEntityLifeVoid tile){
		LifeVoidTileData data = new LifeVoidTileData(tile);
		
		for (LifeVoidTileData machine : machines) {
			if (machine.equals(data)) {
				
				//removing item from list
				//int pos = machines.indexOf(machine);
				machines.remove(machine);
				return;
			}
		}
		
		//end of the function, nothing to remove
	}
	
	
	//#####################################  Main function
	public boolean isEntityInsideVoidChunk(Entity entity)
	{
		//System.out.println("Testing");
		//cycle through each tile
		
		//int loop = 0;
		
		//System.out.println(entity.posX + " " + entity.posZ);
		
		for (LifeVoidTileData tile : machines) {
			
			//make sure the entity isnt below the tile
			if (entity.posY < tile.posY) continue;
			
			//check if the entity is inside the chunk boundaries
			if (isCoordsInsideChunk(tile, entity)) {
				//System.out.println("Entity Inside");
				return true;
			}	
		}
		return false;
	}
	
	//##################################### Sub functions
	
	private static boolean isCoordsInsideChunk(LifeVoidTileData tile, Entity entity) {
		int[] value1 = {tile.posX >> 4, tile.posZ >> 4};
		int[] value2 = {Integer.valueOf(MathHelper.floor_double(entity.posX)) >> 4, Integer.valueOf(MathHelper.floor_double(entity.posZ)) >> 4};
		
		//System.out.println(tile.posX + " " + tile.posZ + " " + entity.posX + " " + entity.posZ);
		//System.out.println(value1[0] + " " + value1[1] + " " + value2[0] + " " + value2[1]);
		
		if (value1[0] == value2[0] && value1[1] == value2[1]) {
			//System.out.println("Found");
			return true;
		}
		
		return false;
	}
}

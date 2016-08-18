package aps.module_Environment;

import aps.module_Environment.tileEntities.TileEntityLifeVoid;

public class LifeVoidTileData {

	public int posX;
	public int posY;
	public int posZ;
	public int worldID;
	
	public LifeVoidTileData(TileEntityLifeVoid tile)
	{
		this.posX = tile.xCoord;
		this.posY = tile.yCoord;
		this.posZ = tile.zCoord;
		
		this.worldID = tile.worldObj.provider.dimensionId;
	}
	
	public boolean equals(Object object)
	{
		LifeVoidTileData data;
		
		if (object instanceof TileEntityLifeVoid) {
			data = new LifeVoidTileData((TileEntityLifeVoid)object);
		} else if (object instanceof LifeVoidTileData) {
			data = (LifeVoidTileData)object;
		} else {
			return false;
		}
		
		if (this.posX == data.posX && this.posY == data.posY && this.posZ == data.posZ)
			if (this.worldID == data.worldID) 
				return true;
		
		return false;
	}
}

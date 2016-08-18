package aps.core.tileEntities;

import java.io.IOException;

import buildcraft.core.DefaultProps;
import buildcraft.core.network.ISynchronizedTile;
import buildcraft.core.network.PacketCoordinates;
import buildcraft.core.network.PacketPayload;
import buildcraft.core.network.PacketTileUpdate;
import buildcraft.core.network.PacketUpdate;
import buildcraft.core.proxy.CoreProxy;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityAPS extends TileEntity implements ISynchronizedTile {

	private Packet oldPacket;
	private int playerCount = 0;
	
	public TileEntityAPS() { }

	// When tile entity is removed call this
	public void kill() {}
	
	// called when neighbour changes
	public void onNeighbourChange() {}
	
	public void sendNetworkUpdate()
	{
		if(CoreProxy.proxy.isSimulating(worldObj)){
			Packet packetToSend = getUpdatePacket();
			
			if (!isPacketDataSame(packetToSend, oldPacket) || worldObj.playerEntities.size() != playerCount){  //send packet update only on change detect
				CoreProxy.proxy.sendToPlayers(getUpdatePacket(), worldObj, xCoord, yCoord, zCoord, DefaultProps.NETWORK_UPDATE_RANGE);
				oldPacket = packetToSend;
				playerCount = worldObj.playerEntities.size();
			} 
		}
	}
	
	public boolean isPacketDataSame(Packet var1, Packet var2) {
		if (var1 instanceof Packet250CustomPayload && var2 instanceof Packet250CustomPayload ) {
			Packet250CustomPayload packet = (Packet250CustomPayload)var1;
			Packet250CustomPayload oldPacket = (Packet250CustomPayload)var2;
			
			if (packet.data.length == oldPacket.data.length) {
				for (int i = 0; i < packet.data.length; i++) {
					if (packet.data[i] != oldPacket.data[i]) {
						return false;
					}
				}
				
				//Final check complete, at this stage all data is verified to be same
				return true;
				
			} else {
				return false;
			}
			
		} else {
			return false;
		}
		
	}
	
	public PacketPayload getPacketPayload()
	{
		return null;
	}
	
	public Packet getUpdatePacket()
	{
		return new PacketTileUpdate(this).getPacket();
	}
	
	@Override
	public void handleDescriptionPacket(PacketUpdate packet) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleUpdatePacket(PacketUpdate packet) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postPacketHandling(PacketUpdate packet) {
		
	}
}

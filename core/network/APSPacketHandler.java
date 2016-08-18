package aps.core.network;

//Java
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.LinkedList;

//Minecraft
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.INetworkManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

//FML
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import aps.core.staticVariables.APSPacketIDs;
import aps.core.tileEntities.TileEntityAPSMachine;
import aps.module_Machines.network.PacketDirectorUpdate;

//Buildcraft
import buildcraft.core.network.PacketUpdate;
import buildcraft.transport.Pipe;
import buildcraft.transport.TileGenericPipe;

public class APSPacketHandler implements IPacketHandler {

	public static LinkedList<Handler> list = new LinkedList<Handler>();
	
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		//get data
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		//process
		try {
			int packetID = data.read();
			//TODO: Add this
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	public void registerPacketHandler(int packetID, TileEntityAPSMachine tile) {
		list.add(new Handler(packetID, tile.getClass()));
	}
	
	
	public class Handler {
		int packetID;
		Class<? extends TileEntityAPSMachine> tile;
		
		public Handler(int packetID, Class<? extends TileEntityAPSMachine> tile) {
			this.packetID = packetID;
			this.tile = tile;
		}
	}
}

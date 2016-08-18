package aps.module_Machines.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import buildcraft.core.network.PacketCoordinates;

public class PacketDirectorUpdate extends PacketCoordinates {

	public byte side;
	public float value;
	public byte priority;
	
	public PacketDirectorUpdate() {}
	
	public PacketDirectorUpdate(int packetId, int x, int y, int z) {
		super(packetId, x, y, z);
	}
	
	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeByte(side);
		data.writeFloat(value);
		data.writeByte(priority);
		super.writeData(data);
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException {
		side = data.readByte();
		value = data.readFloat();
		priority = data.readByte();
		super.readData(data);
	}
	
}

package aps.module_Fusion;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.transport.ITriggerPipe;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportPower;
import aps.core.triggers.TriggerAPS;

public class TriggerAdvancedPipe extends TriggerAPS implements ITriggerPipe {

	enum TriggerType {pipeClogged, pipeFree};
	
	TriggerType type;
	
	public TriggerAdvancedPipe(int id, TriggerType trigger) {
		super(id);
		type = trigger;
	}

	@Override
	public String getDescription() {
		switch(type)
		{
		case pipeClogged:
			return "Pipe Overloading";
		case pipeFree:
			return "Pipe running";
		default:
			return "";
		}
	}

	@Override
	public boolean hasParameter() {
		return false;
	}

	@Override
	public boolean isTriggerActive(Pipe pipe, ITriggerParameter parameter) {
		if (pipe.transport instanceof PipeTransportPower) {}
		return false;
	}

	@Override
	public int getIconIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTriggerActive(ForgeDirection side, TileEntity tile,
			ITriggerParameter parameter) {
		// TODO Auto-generated method stub
		return false;
	}
}

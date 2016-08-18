package aps.module_Fusion;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import buildcraft.BuildCraftTransport;
import buildcraft.api.gates.IOverrideDefaultTriggers;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerProvider;
import buildcraft.api.transport.IPipe;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.PipeTransportPower;

public class APSFusionTriggerProvider implements ITriggerProvider {

	@Override
	public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) {
		//if (tile instanceof IOverrideDefaultTriggers)
			//return ((IOverrideDefaultTriggers) tile).getTriggers();

		LinkedList<ITrigger> result = new LinkedList<ITrigger>();

		return result;
	}

	@Override
	public LinkedList<ITrigger> getPipeTriggers(IPipe pipe) {
		
		LinkedList<ITrigger> result = new LinkedList<ITrigger>();
		
		if (pipe instanceof APSPipePower){
		}
		
		return result;
	}

}

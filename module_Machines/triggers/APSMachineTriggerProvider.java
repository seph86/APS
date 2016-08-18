package aps.module_Machines.triggers;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import aps.module_Machines.module_Machines;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerProvider;
import buildcraft.api.transport.IPipe;

public class APSMachineTriggerProvider implements ITriggerProvider {

	@Override
	public LinkedList<ITrigger> getPipeTriggers(IPipe pipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) {
		LinkedList<ITrigger> triggers = new LinkedList<ITrigger>();
		
		/*if (tile instanceof TileEntityEnergyStore)
		{
			triggers.add(module_Machines.energyStoreFullTrigger);
			triggers.add(module_Machines.energyStoreEmptyTrigger);
			triggers.add(module_Machines.energyStorePartFilledTrigger);
		}
		
		if (tile instanceof TileEntityMagmafier)
		{
			triggers.add(module_Machines.BlastFurnaceFullTrigger);
			triggers.add(module_Machines.BlastFurnaceEmptyTrigger);
			triggers.add(module_Machines.BlastFurnacePartFilledTrigger);
		}
		*/
		return triggers;
	}

}

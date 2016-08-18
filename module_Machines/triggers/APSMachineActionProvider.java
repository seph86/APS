package aps.module_Machines.triggers;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import aps.module_Machines.module_Machines;
import buildcraft.api.gates.IAction;
import buildcraft.api.gates.IActionProvider;

public class APSMachineActionProvider implements IActionProvider {

	@Override
	public LinkedList<IAction> getNeighborActions(Block block, TileEntity tile) {
		LinkedList<IAction> actions = new LinkedList<IAction>();
		
		/*if (tile instanceof TileEntityEnergyDirector){
			actions.add(module_Machines.directorDisableNorth);
			actions.add(module_Machines.directorDisableSouth);
			actions.add(module_Machines.directorDisableEast);
			actions.add(module_Machines.directorDisableWest);
			actions.add(module_Machines.directorEnableNorth);
			actions.add(module_Machines.directorEnableSouth);
			actions.add(module_Machines.directorEnableEast);
			actions.add(module_Machines.directorEnableWest);
		}*/
		
		return actions;
	}

}

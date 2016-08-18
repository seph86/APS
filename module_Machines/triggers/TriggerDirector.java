package aps.module_Machines.triggers;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.gates.ITriggerParameter;
import aps.core.triggers.TriggerAPS;

public class TriggerDirector extends TriggerAPS {

	public TriggerDirector(int id) {
		super(id);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasParameter() {
		// TODO Auto-generated method stub
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

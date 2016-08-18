package aps.core.triggers;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import aps.BuildcraftAPS;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.core.triggers.BCTrigger;

public abstract class TriggerAPS extends BCTrigger {
	
	boolean active;
	
	public TriggerAPS (int id) {
		super (id, "APS.trigger");
	}
	
	@Override
	public abstract int getIconIndex();
	
	@Override
	public abstract String getDescription ();
	
	@Override
	public abstract boolean hasParameter();
	
	@Override
	public abstract boolean isTriggerActive (ForgeDirection side, TileEntity tile, ITriggerParameter parameter);
}

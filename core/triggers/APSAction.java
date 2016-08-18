package aps.core.triggers;

import buildcraft.core.triggers.BCAction;
import aps.BuildcraftAPS;

public class APSAction extends BCAction {

	public enum MachineModes {Off, On, Idle};
	
	private MachineModes mode;
	
	public APSAction(int id) {
		super(id, "APS.actions");
		mode = MachineModes.Off;
	}
	
	@Override
	public int getIconIndex() {
		return mode.ordinal();
	}
	
	@Override
	public String getDescription() {
		return "nothing";
	}
}

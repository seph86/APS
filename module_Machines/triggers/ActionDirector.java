package aps.module_Machines.triggers;

import aps.BuildcraftAPS;

public class ActionDirector {

	public enum Mode { disableNorth, disableSouth, disableEast, disableWest, enableNorth, enableSouth, enableEast, enableWest }
	
	Mode mode;
	
	public ActionDirector(int id, Mode type) {
		//super(id);
		this.mode = type;
	}

	public int getIndexInTexture()
	{
		return 6 + mode.ordinal();
	}
	
	public String getDescription(){
		switch(mode){
			case disableNorth:
				return "Disable North Face";
			case disableSouth:
				return "Disable South Face";
			case disableEast:
				return "Disable East Face";
			case disableWest:
				return "Disable West Face";
			case enableNorth:
				return "Enable North Face";
			case enableSouth:
				return "Enable South Face";
			case enableEast:
				return "Enable East Face";
			case enableWest:
				return "Enable West Face";
			default:
				return "";
		}
	}
	
	
	
}

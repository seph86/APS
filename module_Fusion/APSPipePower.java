package aps.module_Fusion;

import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.PowerHandler;
import buildcraft.transport.pipes.PipePowerWood;

public class APSPipePower extends PipePowerWood {
	
	private PowerHandler powerHandler;
	
	public APSPipePower(int itemID) {
		super(itemID);
		initPowerHandler();
	}
	
	private void initPowerHandler() {
		powerHandler.configure(2,1000,1,1500);
		powerHandler.configurePowerPerdition(1,10);  //TODO: Maybe fix this?
	}
}

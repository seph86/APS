package aps.module_Fusion.tileEntities;

import java.util.LinkedList;

import buildcraft.api.power.IPowerReceptor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import aps.core.module_Core;

public class TileEntityFusionBlock extends TileEntityFusionMultiblock {

	// Static variables
	private static int TokamakMaxEnergyReceive = 25;
	private static int EnergyToHeatingScalar = 11;
	
	//local variables
	
	public TileEntityFusionBlock() {
		super();
	}
}

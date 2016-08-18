package aps.module_Solar;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import aps.core.triggers.TriggerAPS;
import aps.module_Solar.tileEntities.TileEntitySolarCollector;
import buildcraft.api.gates.ITriggerParameter;

public class TriggerSolar extends TriggerAPS {

	enum TriggerType {SolarPowerOutput}
	
	TriggerType type;
	
	public TriggerSolar(int id, TriggerType triggerType)
	{
		super(id);
		type = triggerType;
	}

	@Override
	public String getDescription()
	{
		switch(type)
		{
			case SolarPowerOutput:
				return "Power Output";
			default:
				return "";
		}
	}

	@Override
	public boolean hasParameter()
	{
		return (type == TriggerType.SolarPowerOutput);
	}

	@Override
	public boolean isTriggerActive(ForgeDirection from, TileEntity tile, ITriggerParameter parameter)
	{
		if (!(tile instanceof TileEntitySolarCollector)) return false;
		switch(type)
		{
			case SolarPowerOutput:
				//if(parameter == null || parameter.stack == null) return false;
				//return ((TileEntitySolarCollector) tile).Manager.getPowerOutput() > (parameter.stack.stackSize * 5);
				return false;
			default:
				return false;
		}
	}

	@Override
	public int getIconIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

}

package aps.module_Fusion;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import aps.core.tileEntities.TileEntityAPSMachine;
import aps.core.triggers.TriggerAPS;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.api.gates.TriggerParameter;

public class TriggerTokamak extends TriggerAPS {

	enum TriggerType {TokamakIdling, TokamakTempReached, TokamakOutputQuater, TokamakOutputHalf, TokamakOutput3Quaters, TokamakOutputMax}
	
	TriggerType type;
	
	public TriggerTokamak(int id, TriggerType triggerType)
	{
		super(id);
		type = triggerType;
	}

	@Override
	public String getDescription()
	{
		switch(type)
		{
			case TokamakIdling:
				return "Idling";
			case TokamakTempReached:
				return "Temperature reached";
			case TokamakOutputQuater:
				return "Power output 25% of max";
			case TokamakOutputHalf:
				return "Power output 50% of max";
			case TokamakOutput3Quaters:
				return "Power output 75% of max";
			case TokamakOutputMax:
				return "Power output max reached";
			default:
				return "";
		}
	}

	@Override
	public boolean hasParameter()
	{
		//return (type == TriggerType.TokamakTempReached || type == TriggerType.TokamakOutputReached);
		return false;
	}

	/*@Override
	public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter)
	{
		if (!(tile instanceof TileEntityTokamakGenerator)) return false;
		switch(type)
		{
			case TokamakIdling:
				return ((TileEntityTokamakGenerator) tile).isIdling();
			case TokamakTempReached:
				TileEntityTokamakGenerator tokamak = (TileEntityTokamakGenerator)tile; 
				return tokamak.TokamakTemp > tokamak.FusionStartTemp;
			case TokamakOutputQuater:
				return ((TileEntityTokamakGenerator) tile).getScaledPower(true, 100) >= 25;
			case TokamakOutputHalf:
				return ((TileEntityTokamakGenerator) tile).getScaledPower(true, 100) >= 50;
			case TokamakOutput3Quaters:
				return ((TileEntityTokamakGenerator) tile).getScaledPower(true, 100) >= 75;
			case TokamakOutputMax:
				return ((TileEntityTokamakGenerator) tile).getScaledPower(true, 100) == 100;
			default:
				return false;
		}
	}*/

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

package aps.module_Machines.triggers;

//Minecrft
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import buildcraft.api.gates.ITriggerParameter;
//Buildcraft
import buildcraft.api.gates.TriggerParameter;

//APS
import aps.core.tileEntities.TileEntityAPSMachine;
import aps.core.triggers.TriggerAPS;


public class TriggerMachines extends TriggerAPS {

	public enum TriggerType {EnergyStoreFull, EnergyStoreEmpty, EnergyStorePartFilled,
					BlastFurnaceBlockEmpty, BlastFurnaceBlockFull, BlastFurnaceBlockPartFilled
					}
	
	TriggerType type;
	
	public TriggerMachines(int id, TriggerType triggerType)
	{
		super(id);
		type = triggerType;
	}

	@Override
	public String getDescription()
	{
		switch(type)
		{
			case EnergyStoreFull:
				return "Store Full";
			case EnergyStoreEmpty:
				return "Store Empty";
			case EnergyStorePartFilled:
				return "Store Part Filled";
			case BlastFurnaceBlockFull:
				return "Blast Furnace Full";
			case BlastFurnaceBlockEmpty:
				return "Blast Furnace Empty";
			case BlastFurnaceBlockPartFilled:
				return "Blast Furnace Half Filled";
			default:
				return "";
		}
	}

	@Override
	public boolean hasParameter()
	{
		return false;
	}

	@Override
	public int getIconIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTriggerActive(ForgeDirection side, TileEntity tile, ITriggerParameter parameter) {
		/*if (tile instanceof TileEntityEnergyStore) {
			switch(type)
			{
				case EnergyStoreFull:
					return ((TileEntityEnergyStore) tile).getPowerStored() == ((TileEntityEnergyStore) tile).MaxPowerStored;
				case EnergyStoreEmpty:
					return ((TileEntityEnergyStore) tile).getPowerStored() == 0;
				case EnergyStorePartFilled:
					return ((TileEntityEnergyStore) tile).getScaledPowerStored(100) >= 50; 
				default:
					return false;
			}
		} else if (tile instanceof TileEntityMagmafier){
			switch(type)
			{
				case BlastFurnaceBlockFull:
					return ((TileEntityMagmafier) tile).BlockLevel == ((TileEntityMagmafier) tile).BlockCapacity;
				case BlastFurnaceBlockEmpty:
					return ((TileEntityMagmafier) tile).BlockLevel == 0;
				case BlastFurnaceBlockPartFilled:
					return ((TileEntityMagmafier) tile).getScaledBlockQuantity(100) >= 50;
				default: 
					return false;
			}
		}*/
		return false;
	}

}

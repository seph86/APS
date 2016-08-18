package aps.module_Solar;

import java.util.LinkedList;

import aps.module_Solar.tileEntities.TileEntitySolarCollector;
import aps.module_Solar.tileEntities.TileEntitySolarReflector;

public class APSSolarManagerController
{
	public LinkedList<APSSolarManager> SolarManagers = new LinkedList<APSSolarManager>();
	public void addSolarManager(APSSolarManager Manager) {SolarManagers.add(Manager); checkUnlinkedReflectors();}
	public void remSolarManager(APSSolarManager Manager) {SolarManagers.remove(Manager);}
	
	public LinkedList<TileEntitySolarReflector> UnlinkedReflectors = new LinkedList<TileEntitySolarReflector>();
	public void addUnlinkedReflector(TileEntitySolarReflector Reflector) {if(!UnlinkedReflectors.contains(Reflector)) UnlinkedReflectors.add(Reflector);}
	void remUnlinkedReflector(TileEntitySolarReflector Reflector) {UnlinkedReflectors.remove(Reflector);}
	void checkUnlinkedReflectors()
	{
		TileEntitySolarReflector[] UnlinkedRefs = UnlinkedReflectors.toArray(new TileEntitySolarReflector[0]);
		for(TileEntitySolarReflector Reflector : UnlinkedRefs)
		{
			APSSolarManager Manager = getNearestSolarManager(Reflector);
			if(Manager != null)
			{
				Reflector.assignManager(Manager);
				remUnlinkedReflector(Reflector);
			}
		}
	}
	
	public APSSolarManager getNearestSolarManager(TileEntitySolarReflector Reflector)
	{
		APSSolarManager Nearest = null;
		double Distance = 1000000;
		for(APSSolarManager Manager : SolarManagers)
		{
			double Dist = getDistance(Reflector, Nearest);
			if (Nearest == null || Dist < Distance)
			{
				Nearest = Manager;
				Distance = Dist;
			}
		}
		
		if (Nearest != null)
		{
			TileEntitySolarCollector Col = Nearest.collector;
			if (Reflector.xCoord >= (Col.xCoord  - 16) && Reflector.xCoord <= (Col.xCoord  + 16) &&
				Reflector.yCoord >= (Col.yCoord  - 16) && Reflector.yCoord <= Col.yCoord &&
				Reflector.zCoord >= (Col.zCoord  - 16) && Reflector.zCoord <= (Col.zCoord  + 16))
				return Nearest;
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	double getDistance(TileEntitySolarReflector Ref, APSSolarManager Manager)
	{
		if (Manager != null && Manager.collector != null && Ref != null) {
			TileEntitySolarCollector Col = Manager.collector;
			return Ref.getDistanceFrom(Col.xCoord, Col.yCoord, Col.zCoord);
		} else return 1000000;
	}
}
package aps.module_Machines;

import java.util.LinkedList;

import net.minecraft.item.Item;

public class MaterializerWhitelist {
	public static LinkedList<MaterializerWhitelist> whitelist = new LinkedList<MaterializerWhitelist>();
	
	public final Item item;
	public final int liquidEnergy; //Number of buckets of Liquid Energy needed to make item
	public final float timeMultiplier; //progression speed 
	
	public MaterializerWhitelist(Item item, int energy, float time) {
		for (MaterializerWhitelist object : whitelist) {
			if (object.item == item) {
				whitelist.remove(object);
				break;
			}
		}
		
		this.item = item;
		this.liquidEnergy = energy;
		this.timeMultiplier = time;
	}
	
	static public MaterializerWhitelist getMaterializerInfo(Item item) {
		for (MaterializerWhitelist object : whitelist) {
			if (object.item == item) {
				return object;
			}
		}
		return null;
	}
}

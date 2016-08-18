package aps.module_Solar;

import java.util.LinkedList;

import aps.module_Solar.tileEntities.TileEntitySolarCollector;
import aps.module_Solar.tileEntities.TileEntitySolarReflector;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.World;

public class APSSolarManager
{		
	LinkedList<TileEntitySolarReflector> Reflectors = new LinkedList<TileEntitySolarReflector>();
	float ReflectorPower = 0.125f;
	//float ReflectorPower = 1.0f;
	
	
	World w = null;
	public TileEntitySolarCollector collector = null;
	int X = 0;
	int Y = 0;
	int Z = 0;
	
	public APSSolarManager()
	{}
	
	public void worldSetup(World W, TileEntitySolarCollector c)
	{
		if (w == null)
		{
			w = W;
			X = c.xCoord;
			Y = c.yCoord;
			Z = c.zCoord;
			collector = c;
			module_Solar.controller.addSolarManager(this);
		}
	}
	
	public void addReflector(TileEntitySolarReflector Reflector) {if(!Reflectors.contains(Reflector)) Reflectors.add(Reflector);}
	
	public void remReflector(TileEntitySolarReflector Reflector) {Reflectors.remove(Reflector);}
	
	public int getLightInput()
	{
		int Light = 0;
		for(TileEntitySolarReflector ref : Reflectors)
		{
			Light += ref.getLightAmount();
		}
		return (int) (Light * getTimeLightMult(w));
	}
	
	public float getPowerOutput() { return getLightInput() * ReflectorPower; }
	
	public static float getTimeLightMult(World w)
	{
		if(w != null && w.isDaytime()) return 1; else return 0;
		/*float time = w.getWorldTime() % 24000L;
		if (time > 12000)
			return 0.0f;
		time /= 1500f;
		time -= 4;
		if (time < 0)
			time *= -1;
		time = 4 - time;
		if (time > 1)
			time = 1;
		return time;*/
	}
	
	public static float getBiomeLightMult(BiomeGenBase biome)
	{
		if (biome instanceof BiomeGenDesert)
			return 2.0f;
		else if (biome instanceof BiomeGenHills)
			return 1.5f;
		else if (biome instanceof BiomeGenPlains)
			return 1.25f;
		else if (biome instanceof BiomeGenForest ||
				 biome instanceof BiomeGenTaiga ||
				 biome instanceof BiomeGenSwamp)
			return 0.75f;
		else if (biome instanceof BiomeGenSnow)
			return 0.5f;
		else if (biome instanceof BiomeGenEnd ||
				biome instanceof BiomeGenHell)
			return 0.0F; //Nether and The End do not have enough natural light to gen energy
		else
			return 1.0f; //inc ocean, river, mushroom
	}
	
	public void kill()
	{
		module_Solar.controller.remSolarManager(this);
		for(TileEntitySolarReflector Ref : Reflectors)
		{
			Ref.manager = null;
		}
	}
}
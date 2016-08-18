package aps;

import net.minecraft.client.Minecraft;

public abstract class module
{
	public module() {}
	
	public abstract void preInit();
	
	public abstract void clientInit();
	
	public abstract void initialize();
	
	public abstract void postInit();
	
	public void OnTick(float f, Minecraft minecraft) {}
}
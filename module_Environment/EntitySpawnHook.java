package aps.module_Environment;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class EntitySpawnHook {

	@ForgeSubscribe
	public void onEntitySpawn(LivingSpawnEvent.CheckSpawn event){
		if (checkSpawn(event)){
			//System.out.println("Blocking");
			event.setResult(Result.DENY);
		}
	}
	
	@ForgeSubscribe
	public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
		if (checkSpawn(event)){
			event.setResult(Result.DENY);
		}
	}
	
	private boolean checkSpawn(LivingSpawnEvent event) {
		return module_Environment.manager.isEntityInsideVoidChunk(event.entity);
	}
}

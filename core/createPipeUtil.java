package aps.core;

import buildcraft.core.proxy.CoreProxy;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.ItemPipe;
import buildcraft.transport.Pipe;

public class createPipeUtil {
	public static ItemPipe createPipe(int defaultID, Class <? extends Pipe> clazz, String description){
		String name = clazz.getSimpleName();
		
		ItemPipe res = BlockGenericPipe.registerPipe(defaultID, clazz);
		//res.setItemName(name);
		
		CoreProxy.proxy.addName(res, description);
		
		return res;
	}
}

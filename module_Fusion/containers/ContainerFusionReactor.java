package aps.module_Fusion.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import aps.core.guiContainer.APSContainer;
import aps.module_Fusion.tileEntities.TileEntityFusionCore;

public class ContainerFusionReactor extends APSContainer {

	public ContainerFusionReactor(TileEntityFusionCore tile, InventoryPlayer playerInv) {
		super(playerInv);
		addSlotToContainer(new Slot(tile, 0, 80, 10)); //Controller
		addSlotToContainer(new Slot(tile, 1, 60, 35)); //fuel input
		addSlotToContainer(new Slot(tile, 2, 100, 40)); //output
		addSlotToContainer(new Slot(tile, 3, 80, 60)); //thermal controller
	}
}

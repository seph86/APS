package aps.core.guiContainer;

//buildcraft
import java.util.LinkedList;

import aps.core.tileEntities.TileEntityAPSMachine;
import buildcraft.core.gui.BuildCraftContainer;
import buildcraft.energy.TileEngine;

//Vanilla Minecraft
import net.minecraft.inventory.Container;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class APSContainer extends BuildCraftContainer
{
    public APSContainer(InventoryPlayer playerInv)
    {
    	super(0);
    	addPlayerInventorySlots(playerInv);
    }
    
    public APSContainer(InventoryPlayer playInv, int inventorySize) {
    	super(inventorySize);
    	addPlayerInventorySlots(playInv);
    }
    
    public void addPlayerInventorySlots(InventoryPlayer playerInv)
    {
    	for(int i = 0; i < 3; i++)
            for(int k = 0; k < 9; k++)
                    addSlotToContainer(new Slot(playerInv, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
    	for(int j = 0; j < 9; j++)
            addSlotToContainer(new Slot(playerInv, j, 8 + j * 18, 142));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
            return true;
    }
    
	/*@Override	
	public ItemStack transferStackInSlot(EntityPlayer pl, int i)
	{
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i < inventorySize)
            {
                if(!mergeItemStack(itemstack1, invSize, inventorySlots.size(), true))
                {
                    return null;
                }
            } else
            if(!mergeItemStack(itemstack1, 0, invSize, false))
            {
                return null;
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
	}*/
}
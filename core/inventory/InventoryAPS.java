package aps.core.inventory;

//Vanilla minecraft
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.entity.player.EntityPlayer;

//Java
import java.util.List;

public class InventoryAPS implements IInventory
{
	public ItemStack[] InputSlot;
	public boolean inventoryChanged;
    public int currentItem;
    String invName;
	
	public InventoryAPS(int InvSize, String InvName)
	{
		InputSlot = new ItemStack[InvSize];
        currentItem = 0;
        inventoryChanged = false;
        invName = InvName;
	}
	
	public boolean addItemStackToInventory(ItemStack itemstack)
    {
        if(!itemstack.isItemDamaged())
        {
            int StackSize;
            do
            {
                StackSize = itemstack.stackSize;
                itemstack.stackSize = storePartialItemStack(itemstack);
            }
            while(itemstack.stackSize > 0 && itemstack.stackSize < StackSize);
            
            return itemstack.stackSize < StackSize;
        }
        int j = getFirstEmptyStack();
        if(j >= 0)
        {
            InputSlot[j] = ItemStack.copyItemStack(itemstack);
            InputSlot[j].animationsToGo = 5;
            itemstack.stackSize = 0;
            return true;
        } else
        {
            return false;
        }
    }
	
	private int storeItemStack(ItemStack itemstack)
    {
        for(int i = 0; i < InputSlot.length; i++)
        {
            if(InputSlot[i] != null &&
            	InputSlot[i].itemID == itemstack.itemID &&
            	InputSlot[i].isStackable() &&
            	InputSlot[i].stackSize < InputSlot[i].getMaxStackSize() &&
            	InputSlot[i].stackSize < getInventoryStackLimit() &&
            	(!InputSlot[i].getHasSubtypes() ||
            	InputSlot[i].getItemDamage() == itemstack.getItemDamage()))
            {
                return i;
            }
        }

        return -1;
    }
	
	private int storePartialItemStack(ItemStack itemstack)
    {
        int i = itemstack.itemID;
        int j = itemstack.stackSize;
        int k = storeItemStack(itemstack);
        if(k < 0)
        {
            k = getFirstEmptyStack();
        }
        if(k < 0)
        {
            return j;
        }
        if(InputSlot[k] == null)
        {
            InputSlot[k] = new ItemStack(i, 0, itemstack.getItemDamage());
        }
        int l = j;
        if(l > InputSlot[k].getMaxStackSize() - InputSlot[k].stackSize)
        {
            l = InputSlot[k].getMaxStackSize() - InputSlot[k].stackSize;
        }
        if(l > getInventoryStackLimit() - InputSlot[k].stackSize)
        {
            l = getInventoryStackLimit() - InputSlot[k].stackSize;
        }
        if(l == 0)
        {
            return j;
        } else
        {
            j -= l;
            InputSlot[k].stackSize += l;
            InputSlot[k].animationsToGo = 5;
            return j;
        }
    }
	
	private int getFirstEmptyStack()
    {
        for(int i = 0; i < InputSlot.length; i++)
        {
            if(InputSlot[i] == null)
            {
                return i;
            }
        }

        return -1;
    }
	
	public ItemStack getStackInSlot(int i)
    {
        if (i < InputSlot.length)
        	return InputSlot[i];
        else
        	return null;
    }
	
	public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        if (i < InputSlot.length)
        	InputSlot[i] = itemstack;
    }
	
	public void onInventoryChanged()
    {
        inventoryChanged = true;
    }
	
	public int getSizeInventory()
    {
        return InputSlot.length;
    }
	
	public int getInventoryStackLimit()
    {
        return 64;
    }

	public ItemStack decrStackSize(int i, int j)
    {
        ItemStack aitemstack[] = InputSlot;
        if(aitemstack[i] != null)
        {
            if(aitemstack[i].stackSize <= j)
            {
                ItemStack itemstack = aitemstack[i];
                aitemstack[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = aitemstack[i].splitStack(j);
            if(aitemstack[i].stackSize == 0)
            {
                aitemstack[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
    }

	public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        if(entityplayer.isDead)
        {
            return false;
        }
        return entityplayer.getDistanceSqToEntity(entityplayer) <= 64D;
    }

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public String getInvName() {
		return invName;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
}
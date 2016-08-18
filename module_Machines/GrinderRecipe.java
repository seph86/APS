package aps.module_Machines;

import java.util.Random;

import net.minecraft.item.ItemStack;

public class GrinderRecipe
{
	ItemStack ingredient;
	ItemStack[] products;
	public float[] chances;
	float energyRequiredPerBlock;
	
	public GrinderRecipe(ItemStack Ingredient, ItemStack[] Products, float[] Chances, float EnergyRequired)
	{
		ingredient = Ingredient;
		products = Products;
		if(Chances.length != Products.length)
		{
			String s1 = "";
			for(ItemStack I : Products) s1 += I.getItem().getLocalizedName(I);
			
			String s2 = "";
			for(float F : Chances) s2 += (F + ":");
			
			new Exception("APS GrinderRecipe: Product array and Chance array not same length! \n Info:\n" + Ingredient.getItem().getItemDisplayName(null) + s1 + s2).printStackTrace();
		}
		chances = Chances;
		energyRequiredPerBlock = EnergyRequired;
	}
	
	public GrinderRecipe(ItemStack Ingredient, ItemStack[] Products, float Chance, float EnergyRequired)
	{
		ingredient = Ingredient;
		products = Products;
		chances = new float[products.length];
		for(int i = 0; i < products.length; i++) chances[i] = Chance;
		energyRequiredPerBlock = EnergyRequired;
	}
	
	public GrinderRecipe(ItemStack Ingredient, ItemStack Product, float Chance, float EnergyRequired)
	{
		ingredient = Ingredient;
		products = new ItemStack[1];
		products[0] = Product;
		chances = new float[1];
		chances[0] = Chance;
		energyRequiredPerBlock = EnergyRequired;
	}
	
	public boolean isIngredient(ItemStack I) {if(I == null) return false; else return ingredient.itemID == I.itemID;}
	public boolean isProduct(ItemStack P)
	{
		if(P == null) return false;
		for(ItemStack product : products)
		{
			if(product.itemID == P.itemID) return true;
		}
		return false;
	}
	
	public ItemStack getIngredient() {return ingredient;}
	public float getEnergyRequired() {return energyRequiredPerBlock;}
	public ItemStack[] getAllProducts() {return products;}
	public ItemStack getRandomProduct()
	{
		float R = ((new Random()).nextFloat() * 160);
		float C = 0;
		for(int i = 0; i < products.length; i++)
		{
			if (products.length > 1)
			{
				if (chances.length > 1)
				{
					if(R < C + chances[i])
						return products[i].copy();
					else C += chances[i];
				}
				else
				{
					if(R < C + chances[0])
						return products[i].copy();
					else C += chances[0];
				}
			}
			else return products[0].copy();
		}
		return null;
	}	
}
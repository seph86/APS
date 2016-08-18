package aps.core.gui;

//GL11
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
//Vanilla Minecraft
//APS

public class GuiAPSSlider extends GuiButton
{
    private float sliderValue;
    private int maxValue;
    public boolean dragging;
    
    int[] notchTexInfo = new int[4];
    ResourceLocation Texture;
    
    public GuiAPSSlider(int id, int xPos, int yPos, int width, int height, int notchTexXPos, int notchTexYPos, int notchTexWidth, int notchTexHeight, String notchTex, String label, int maxValue, int startingValue)
    {
        //Not that kind of Notch.
    	super(id, xPos, yPos, width, height, label);
        dragging = false;
        sliderValue = startingValue;
        this.maxValue = maxValue;
        Texture = new ResourceLocation("APS", notchTex);
        
        notchTexInfo[0] = notchTexXPos;
        notchTexInfo[1] = notchTexYPos;
        notchTexInfo[2] = notchTexWidth;
        notchTexInfo[3] = notchTexHeight;
    }

    public int getSliderValue()
    {
    	return (int)(maxValue * sliderValue);
    }
    
    public float getSliderPosition() 
    {
    	return sliderValue;
    }
    
    public void setSliderValue(int Value)
    {
    	if (Value < 0)
    		Value = 0;
    	if (Value > maxValue)
    		Value = maxValue;
    	
    	float position = ((float)Value / (float)maxValue);
    	
    	sliderValue = position;
    }
    
    protected int getHoverState(boolean flag)
    {
        return 0;
    }
    
    @Override
    public void drawButton(Minecraft minecraft, int i, int j)
    {
        if (!drawButton)
        {
            return;
        }
        FontRenderer fontrenderer = minecraft.fontRenderer;
        minecraft.renderEngine.func_110577_a(Texture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        boolean isOverButton = i >= xPosition && j >= yPosition && i < xPosition + width && j < yPosition + height;
        int k = getHoverState(isOverButton);
        //drawTexturedModalRect(xPosition, yPosition, 0, 46 + k * 20, width / 2, height);
        //drawTexturedModalRect(xPosition + width / 2, yPosition, 200 - width / 2, 46 + k * 20, width / 2, height);
        //Slider Background Halves
        mouseDragged(minecraft, i, j);
    }
    
    protected void mouseDragged(Minecraft minecraft, int i, int j)
    {
        if (!drawButton)
        {
            return;
        }
        if (dragging)
        {
            sliderValue = (float)(i - (xPosition)) / width;
            if (sliderValue < 0)
            {
                sliderValue = 0;
            }
            if (sliderValue > 1)
            {
                sliderValue = 1;
            }
            this.setSliderValue(rounding((int)(maxValue * sliderValue)));
        }
        minecraft.renderEngine.func_110577_a(Texture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect((int) (xPosition + (sliderValue * width) - (notchTexInfo[2] / 2)), yPosition + 3, notchTexInfo[0], notchTexInfo[1], notchTexInfo[2], notchTexInfo[3]);
    }

    public boolean mousePressed(Minecraft minecraft, int i, int j)
    {
        if (super.mousePressed(minecraft, i, j))
        {
            sliderValue = (float)(i - (xPosition)) / width;
            if (sliderValue < 0.0F)
            {
                sliderValue = 0;
            }
            if (sliderValue > maxValue)
            {
                sliderValue = maxValue;
            }
            dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void mouseReleased(int i, int j)
    {
        dragging = false;
    }
    
    private int rounding(int value) {
    	int resto = value % 5;
        if (resto <= 2.5) { 
            return value-resto;
        } else {
            return value + (5 - resto);
        }
    }
}

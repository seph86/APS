package aps.core.gui;

//GL11
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
//Vanilla Minecraft


public class GuiAPSButton extends GuiButton
{
    int[] texInfo = new int[4];
	ResourceLocation Texture;
    
	public GuiAPSButton(int id, int xPos, int yPos, int texXPos, int texYPos, int texWidth, int texHeight, String tex, String label)
    {
        super(id, xPos, yPos, texWidth, texHeight, label);
        texInfo[0] = texXPos;
        texInfo[1] = texYPos;
        texInfo[2] = texWidth;
        texInfo[3] = texHeight;
        
        Texture = new ResourceLocation("aps", tex);
    }
	
	public void setTexXPos(int xPos) {texInfo[0] = xPos;}
	public void setTexYPos(int yPos) {texInfo[1] = yPos;}
    
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
        drawTexturedModalRect(xPosition, yPosition, texInfo[0], texInfo[1], texInfo[2], texInfo[3]);
        mouseDragged(minecraft, i, j);
        if (!enabled)
        {
            drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, 0xffa0a0a0);
        }
        else if (isOverButton)
        {
            drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, 0xffffa0);
        }
        else
        {
            drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, 0xe0e0e0);
        }
    }
}

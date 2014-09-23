import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * THIS CREATES A COOLDOWN BAR THAT DISPLAYS COOLDOWN
 * 
 * @author (Mr. Cohen)
 * THIS IS HIS EXAMPLE ON HOW TO MAKE "Health bars" with some slight changes 
 * made by mr to fit the game
 * 
 * @version (2.2)
 */
public class CooldownBar extends Actor
{
    private GreenfootImage bar;
    private Color backColor = Color.DARK_GRAY;
    private Color regainColor = Color.GREEN;
    private int fullBar;
    private int barWidth = 98;
    public CooldownBar(int fullBar)
    {
        this.fullBar = fullBar;
        bar = new GreenfootImage (100, 25);
        bar.setColor (backColor);
        bar.fill();
        bar.setColor (regainColor);
        bar.fillRect(1,1,barWidth,23);
        this.setImage (bar);
    }

    public void changeBar (int currentCd)
    {
        bar.clear();
        bar.setColor (backColor);
        bar.fill();
        bar.setColor (regainColor);
        // use precise division to find percentage of CD, then
        // part of the CD bar should be:
        int cd = (int)(((double)currentCd / (double)fullBar) * 98);
        bar.fillRect (1, 1, cd, 23);
    }
}
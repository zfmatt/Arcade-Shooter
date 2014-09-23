import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * THIS CLASS DISPLAYS THE SHIELD BAR 
 * IT CAN ALSO RECHARGE
 * 
 * @author (Mr Cohen's EXAMPLE) 
 * @version (2.2)
 */
public class ShieldBar extends Actor
{
    private GreenfootImage bar;
    private Color backColor = Color.DARK_GRAY;
    private Color regainColor = Color.CYAN;
    private int fullShield;
    private int barWidth = 98;
    public ShieldBar(int fullShield)
    {
        this.fullShield = fullShield;
        bar = new GreenfootImage (100, 25);
        bar.setColor (backColor);
        bar.fill();
        bar.setColor (regainColor);
        bar.fillRect(1,1,barWidth,23);
        this.setImage (bar);
    }

    public void changeBar (int currentShield)
    {
        bar.clear();
        bar.setColor (backColor);
        bar.fill();
        bar.setColor (regainColor);
        // use precise division to find percentage of health, then
        // multiply that by the size of the bar, and finally cast it
        // back into an integer for how many pixels wide the green
        // part of the HP bar should be:
        int shieldSize = (int)(((double)currentShield / (double)fullShield) * 98);
        bar.fillRect (1, 1, shieldSize, 23);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * THIS IS THE BOSS'S HP BAR THAT DISPLAYS ITS HP. IT CAN ALSO REGENERATE. 
 * 
 * @author (Mr. Cohen). This is his example of hp bar that he posted 
 * @version (2.2)
 */
public class HealthBar extends Actor
{
    //declare variables
    private GreenfootImage bar;
    private Color backColor = Color.DARK_GRAY;
    private Color regainColor = Color.RED;
    private int fullHealth;
    private int barWidth = 396;
    //constructor
    public HealthBar(int fullHealth)
    {
        this.fullHealth= fullHealth;
        bar = new GreenfootImage (400, 25);
        bar.setColor (backColor);
        bar.fill();
        bar.setColor (regainColor);
        bar.fillRect(1,1,barWidth,23);
        this.setImage (bar);
    }

    public void changeBar (int currentHealth)
    {
        bar.clear();
        bar.setColor (backColor);
        bar.fill();
        bar.setColor (regainColor);
        // use precise division to find percentage of health
        int shieldSize = (int)(((double)currentHealth / (double)fullHealth) * 396);
        bar.fillRect (1, 1, shieldSize, 23);
    }
}

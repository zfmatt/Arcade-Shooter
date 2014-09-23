import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ONE OF THE BOSSES ATTACKS
 * 
 * @author (Matthew Ngai) 
 * Credits for the image taken from the Mofunzone website, the game, Etherena Beta, and their creators.
 * @version (2.2)
 */
public class SideBall extends Boss
{

    /**
     * Act - do whatever the SideBall wants to do. 
     */
    public void act() 
    {
        setLocation(getX() + 9, getY());
        //if it hits the world edge then it removes itself
        if (edgeWall()) {
            getWorld().removeObject(this);
        }
    }    

    /**
     * method to see if it touches the world's edge
     */    
    public boolean edgeWall()
    {
        //check if it hits the X axis'
        if (getX() < 10 || getX() > getWorld().getWidth() -10)  {
            return true;
        }
        //check if it hits the Y axis'
        if (getY() < 10 || getY() > getWorld().getHeight()-10)  {
            return true;
        }
        else    {
            return false;
        }
    }
}

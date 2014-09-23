import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ONE OF THE BOSSES ATTACKS
 * 
 * IMAGE BORROWED FROM: http://bestclipartblog.com/19-bat-clipart.html
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class Bat extends Enemies
{
    /**
     * Act - do whatever the Bat wants to do. 
     */
    public void act() 
    {
        //set location with speed
        setLocation(getX(), getY() + 12);
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ONE OF THE BOSS'S ATTACKS
 * 
 * IMAGE BORROWED FROM: http://media.photobucket.com/image/fireball+/WWJDINPA/fireball.jpg
 * and to the respective owner: WWJDINPA
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class Orange extends Boss
{
    //declare variables
    private int speed = 5;
    /**
     * Act - do whatever the Orange wants to do. 
     */
    public void act() 
    {
        // set movement of the fireball so that it moves down
        setLocation (getX(), getY() + speed);
        //remove orange if it touches the world's edge
        if (edgeFire()) {
            //remove object
            getWorld().removeObject(this);
        }
    }    

    /**
     * method to see if the orange touces the world's edge
     */
    public boolean edgeFire()
    {
        //check if it hits the Y axis'
        if (getY() < 10 || getY() > getWorld().getHeight()-10)  {
            return true;
        }
        else    {
            return false;
        }
    }

}

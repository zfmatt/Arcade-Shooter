import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THIS IS WHAT EXPLODES ONCE DEATH DIES
 * 
 * IMAGE BORROWED FROM: http://www.ecs.gov.bd/images/PertySymble/Sickle.jpg
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class Sickle extends Death
{
    //declare variables
    private int pVelocity;
    /**
     * constructor with 2 parameters
     */
    public Sickle (int pVelocity, int rotating)
    {
        setRotation(rotating);
        this.pVelocity = pVelocity;    
    }

    /**
     * Act - do whatever the Sickle wants to do.
     */
    public void act() 
    {
        //move
        move(pVelocity);
        //check collision at the walls
        if (edgeWall()) {
            //remove object once at the walls
            getWorld().removeObject(this);
        }
    }    
    //method to see if the sickle touches the world's edge
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

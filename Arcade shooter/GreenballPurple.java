import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *THIS IS THE EXPLOSION CLASS WHICH EXPLODES AFTER GREENBALL HITS THE TOP
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class GreenballPurple extends Actor
{
    //declare variables
    private int pVelocity;
    private Trailer trailer;
    private int trailValue = 1;
    /**
     * constructor that takes in two parameters
     */
    public GreenballPurple (int pVelocity, int purpleBoom)
    {
        setRotation(purpleBoom);
        this.pVelocity = pVelocity;    
    }

    /**
     * Act - do whatever the GreenballPurple wants to do. 
     */
    public void act() 
    {
        //move the object
        move(pVelocity); 
        //if not at edge of the world then use trail
        if (!edgeGreen())   {
            startTrail();       
        }
        //remove GreenballPurple if it touches the world's edge
        if (edgeGreen()) {
            //remove object
            getWorld().removeObject(this);
        }       

    }       
    
    /**
     * //method to see if the greenballPurple touces the world's edge
     */
    public boolean edgeGreen()
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

    /**
     *method for starting the trail
     */
    private void startTrail()
    {
        //once a trail particle disappears, subtract value from trailValue
        trailValue--;
        //start new smoke once one has disappeared
        if (trailValue == 0)  {
            //add trail object where the gold star is
            getWorld().addObject(new Trailer(), getX(), getY());
            trailValue = 1;
        }
    }

    /**
     * method to remove object after touching enemy
     */
    public void explodeTouch()
    {
        //remove object
        getWorld().removeObject(this);
    }
}
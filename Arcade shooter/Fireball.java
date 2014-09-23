import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * FIREBALL BY SHOOTING LEFT CLICK
 * 
 * @author (Matthew Ngai) 
 * @author (Mr.Cohen) for turning the object to cursor location, Michael Kolling for his tutorial on smoke
 * @version (2.2)
 */
public class Fireball extends Attack
{
    //declare variables
    private int scene = 0;
    private int speed = 10;
    private int trailValue = 1;
    //by Mr Cohen
    private int endX, endY;
    private int distance;
    private int timer = 0;
    //end here
    /**
     * By Mr cohen
     * Constructor of a Marble includes parameters to tell it where to move towards
     */
    public Fireball (int endX, int endY)
    {
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * By: Mr. Cohen
     * When added to world, Marble will turn towards it's end point. It will then calculate
     * how far it should move.
     */
    public void addedToWorld (World w)
    {
        //turn to these coordinates
        this.turnTowards(endX, endY);
        // Use pythagorean theorum to find distance between start and end
        distance = (int)(Math.sqrt( Math.pow(Math.abs(getX() - endX), 2) + Math.pow(Math.abs(getY() - endY), 2)));
    }

    /**
     * Act - do whatever the Fireball wants to do. 
     */
    public void act() 

    {
        //increase timer
        timer++;
        //increase speed every 2 acts after it spawns
        if (timer%2 == 0)   {
            speed++;
        }
        //*******By: Mr cohen
        if (distance > 0)
        {
            move(speed);
            //distance -= speed;
        }
        //if the fireball is not at world's edge, then add trails
        if (!atWorldEdge())   {
            startTrail();       
        }
        if (atWorldEdge()|| distance <= 0)
        {
            getWorld().removeObject(this);
        }
        //***
    }  

    /**
     * By Mr. Cohen
     * removes object if at world edge
     */
    public boolean atWorldEdge ()
    {
        int maxX = getWorld().getBackground().getWidth();
        int maxY = getWorld().getBackground().getHeight();
        if (getX() <= 0 || getX() >= maxX - 1)  {
            return true; 
        }
        if (getY() <= 0 || getY() >= maxY - 1)  {
            return true;
        }
        return false;
    }

    /**
     * method to remove the object after touching enemy.
     */
    public void fireTouch()
    {
        //remove the object
        getWorld().removeObject(this);
    }

    /**
     *method for starting the trail.
     */
    private void startTrail()
    {
        //once a trail particle disappears, subtract value from trailValue
        trailValue--;
        //start new smoke once one has disappeared
        if (trailValue == 0)  {
            //add trail object where the gold star is
            getWorld().addObject(new Blur(), getX(), getY());
            trailValue = 1;
        }

    }
}

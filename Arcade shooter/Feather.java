import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ATTACK THAT SPAWNS WHEN ENEMY DIES. IT FOLLOWS PLAYER.
 * 
 * ********Mr. Cohen helped debug turn towards problem
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class Feather extends Enemies
{
    //declare variables
    private Player player;
    private int timer;
    /**
     * constructor for class feather
     */
    public Feather(Player player)
    {
        this.player = player;
    }

    /**
     * Act - do whatever the Feather wants to do. 
     */
    public void act() 
    {
        //move it
        move(4);
        //increase timer 
        timer++;
        //make sure player is in the world

        //turn towards and follow the object player  
        turnTowards(player.getX(), player.getY());
        //set the location of feather
        setLocation(getX(), getY());
        //remove after certain time
        if (timer == 200)   {
            getWorld().removeObject(this);
        }
        //check collision at the walls
        else if (edgeWall()) {
            //remove object once at the walls
            getWorld().removeObject(this);
        }
    } 

    /**
     *method to see if the death touches the world's edge
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

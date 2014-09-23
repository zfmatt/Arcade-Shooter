import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A BALL THAT IS SHOT BY CERTAIN ENEMIES
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class ShootBall extends Enemies
{
    //declare variables
    private int bounce = 0;
    /**
     * Act method
     */
    public void act() 
    {

        //move it
        move(8);
        //call bounce back method
        bounceBack();

        //set the location of the attack
        setLocation(getX(), getY());
        //after it bounces few times then remove it
        if (bounce >= 5)    {
            getWorld().removeObject(this);
        }
    }   

    /**
     * method to see if the death touches the world's edge
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

    /**
     * method to bounce the ball
     */
    public void bounceBack()
    {
        //if it reaches the x coordinate of the world's edge then turn it
        if (getX() < 10)  {
            //move deathmask out of <10 range
            setLocation(getX() + 22, getY());
            turn(155);
            bounce++;//increase bounce
        }
        //if it reaches the right side of the wall then bounce it back
        if (getX() > getWorld().getWidth() -10)   {
            //move deathmask out of >790 range
            setLocation(getX() - 22, getY());
            turn(151);
            bounce++;//increase bounce

        }
        //if it reaches the Y coordinate of world's edge, then turn it
        if (getY() < 10)  {
            //move deathmask out of top
            setLocation(getX(), getY() + 22);
            turn(155);
            bounce++;//increase bounce
        }
        //limit the Y coordinates
        if (getY() > getWorld().getHeight()-10)    {
            //set the location
            setLocation(getX(), getY() -22);
            turn(151);
            bounce++;//increase bounce
        }
    }
}

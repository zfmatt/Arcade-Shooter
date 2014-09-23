import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THIS CLASS IS WHAT AN ENEMY MAY SHOOT AS AN ATTACK
 * 
 * @author (Matthew Ngai) 
 * @version (a version number or a date)
 */
public class BlueBall extends Eye
{
    //declare variables
    private int blueVelocity;
    private int scene = 0;
    /**
     * CONSTRUCTOR with parameters
     */
    public BlueBall (int blueVelocity, int rotating)
    {
        setRotation(rotating);
        this.blueVelocity = blueVelocity;
    }

    /**
     *Act method
     */
    public void act() 
    {
        //increase timer of scene
        scene++;
        //swap images every few acts to make animation
        if (scene == 0) {
            setImage("blueDot.png");
        }
        if (scene == 15) {
            setImage("yellowDot.png");
        }
        if (scene == 30) {
            setImage("greenDot1.png");
        }
        if (scene == 45)    {
            setImage("blueDot.png");
            scene = 0;
        }
        //move the object
        move(blueVelocity);
        //if it hits the world edge then it removes itself
        if (edgeWall()) {
            getWorld().removeObject(this);
        }
    }    
    //method to see if it touches the world's edge
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

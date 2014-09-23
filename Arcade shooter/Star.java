import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * STAR IS DROPPED BY ENEMIES UPON DYING AND GIVE COINS AND SCORE
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class Star extends Actor
{
    //declare variables
    private int scene;
    /**
     * Act - do whatever the Star wants to do. 
     */
    public void act() 
    {
        //increase scene
        scene++;        
        //swap images every few acts
        if (scene == 0) {
            setImage("blueStar.png");
        }
        if (scene == 15) {
            setImage("purpleStar.png");
        }
        if (scene == 30) {
            setImage("greenStar.png");
        }
        if (scene == 45) {
            setImage("blueStar.png");
            scene = 0;
        }
        //set the location
        setLocation(getX(), getY() + 5);
        //if it hits the world edge then it removes itself
        if (edgeWall()) {
            getWorld().removeObject(this);
        }
    }    

    /**
     * remove method
     */
    public void remove()
    {
        //remove object
        getWorld().removeObject(this);
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

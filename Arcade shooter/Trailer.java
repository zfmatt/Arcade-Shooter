import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TRAILER THAT FOLLOWS EXPLOSION SHOTS
 * 
 * @author (Michael Kolling for his tutorial)
 * IT IS ALSO EDITED BY ME
 * @version (2.2)
 */
public class Trailer extends Actor
{
    //declare variables
    private GreenfootImage image;
    private int vanish;
    /**
     * constructor 
     */
    public Trailer()
    {
        image = getImage();
        vanish = Greenfoot.getRandomNumber(4) +1;
        //vanish is the amount in which the smoke particle will shrink by
        if (vanish > 3)  {
            vanish = vanish -2;
        }
    }    
    //act method
    public void act() 
    {
        //call the smaller() method
        smaller();
    }      
    //the method makes the trail particle decrease in size
    private void smaller()
    {
        //if the trail particle is small enough, remove object
        if (getImage().getWidth() < 10)    {            
            getWorld().removeObject(this);
        }
        //otherwise, make it smaller by "vanish" amount
        else {
            GreenfootImage img = new GreenfootImage(image);
            //shrink the trail particle by its width and height
            img.scale (getImage().getWidth()-vanish, getImage().getHeight()-vanish);
            //set the image after the changes
            setImage (img);            
        }
    }

}
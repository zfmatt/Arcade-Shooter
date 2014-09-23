import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Blur here.
 * 
 * @author (Matthew Ngai) slightly edited by me
 * @author (Michael Kolling) for his tutorial
 */
public class Blur extends Actor
{
    //declare variables
    private GreenfootImage image;
    private int vanish;
    private Blur blur;
    
    //Smoke constructor
    public Blur()
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

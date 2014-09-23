import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THIS CLASS IS AN ENEMY ATTACK WHICH APPEARS WHEN ENEMY DIES
 * IT GETS BIGGER EVERY ACT.
 * 
 * @author MATTHEW NGAI WITH HELP FROM MICHAEL KOLLING
 * 
 * ThIS IMAGE WAS BORROW FROM MOFUNZONE GAME: Eterena Beta 
 * and their respective creators
 * 
 * @version (2.2)
 */
public class GrowBall extends Eye
{
    //declare variables
    private GreenfootImage image;
    private int increase = 1;
    private int scene = 0;
    /**
     * Constructor for the class GrowBall
     */
    public GrowBall()
    {
        //increase scene counter
        scene++;
        //swap images every few acts to create animation
        if (scene == 0) {
            setImage("GrowBall.png");
        }
        if(scene == 10) {
            setImage("GrowBall2.png");
        }
        if (scene == 20)    {
            setImage("GrowBall.png");
            scene = 0;
        }
        image = getImage();
    }

    /**
     *Act method
     */
    public void act() 
    {
        //call method for bigger
        bigger();
        //set location at the x and y coordinates with speed of 5
        setLocation(getX(), getY() + 9);
        //if it hits the world edge then it removes itself
        if (edgeWall()) {
            getWorld().removeObject(this);
        }
    }    

    /**
     * make image bigger
     * CODE SLIGHTLY adapted from Michael Kolling
     * most of editing this code was done by me
     */
    private void bigger()
    {
        //make it bigger
        GreenfootImage img = new GreenfootImage(image);
        //shrink the trail particle by its width and height
        img.scale (getImage().getWidth() + increase, getImage().getHeight() + increase);
        //set the image after the changes
        setImage (img);            
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

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * CENTRE CLICK SCROLL BUTTON. ULTIMATE ATTACK WITH COOLDOWN.
 * 
 * @author (Matthew Ngai) 
 * 
 * image borrowed from: Castlevania: Dawn of Sorrow for Nintendo DS
 * company: KONAMI
 * 
 * @version (2.2)
 */
public class StrongFire extends Attack
{
    //declare variables
    private int time = 0;
    private int speed = -7;
    /**
     * Act - do whatever the StrongFire wants to do. 
     */
    public void act() 
    {
        //increase timer
        time++;
        //spin it
        turn(1);
        //set different image every few acts
        //the following image were borrow from KONAMI, Castlevania: Dawn of Sorrow for Nintendo DS
        if (time == 0)  {
            setImage("ult1.png");
        }
        if (time == 8)  {
            setImage("ult2.png");
        }
        if (time == 16)  {
            setImage("ult3.png");
        }
        if (time == 22) {
            setImage("ult1.png");
            time = 0;
        }
        // set movement of the fireball so that it moves up
        setLocation (getX(), getY() + speed);
        //remove fireball if it touches the world's edge
        if (edgeFire()) {
            getWorld().removeObject(this);
        }
    } 
    //method to see if the fireball touces the world's edge
    public boolean edgeFire()
    {

        //check if it hits the Y axis'
        if (getY() < 10 || getY() > getWorld().getHeight()-10)  {
            return true;
        }
        else    {
            return false;
        }
    }
    //method to remove object if it touches something
    public void strongTouch()
    {
        //remove object
        getWorld().removeObject(this);    
    }
}

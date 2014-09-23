import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THIS IS WHAT DEATH USES TO ATTACK
 * 
 * IMAGE BORROWED FROM: 
 * Castlevania: Dawn of Sorrow Nintendo DS - By: KONAMI
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class DeathAttack extends Death
{

    /**
     * Act - do whatever the SideBall wants to do. 
     */
    public void act() 
    {
        //set its location with a speed of 5 
        setLocation(getX(), getY() + 5);
        //if it hits the world edge then it removes itself
        if (edgeWall()) {
            getWorld().removeObject(this);
        }
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

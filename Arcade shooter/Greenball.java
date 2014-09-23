import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * RIGHT CLICK SHOOT GREEN BALL
 * 
 * @author (Matthew Ngai) 
 * 
 *
 * image taken from: http://ds.mmgn.com/Forums/MMGN-com/New-Medal-Ideas
 * USER: viperz
 * 
 * @author: Mr cohen's missile asset example
 * part of the concept was thought of by me and part by his example
 * 
 * @version (2.2)
 */
public class Greenball extends Attack
{
    //declare variables
    private int time = 0;
    private int speed = -5;
    private int xPlace = 0;
    private int pVelocity = 5;
    private GreenballPurple[] g = new GreenballPurple [12];
    private boolean hit = false;
    /**
     * act method.
     */
    public void act() 
    {
        //set movement of the greenball moving up
        setLocation(getX(), getY() +speed);
        //for every act, increase time so that images can be switched
        time++;
        //rotate the greenball every few acts
        if (time == 1)  {
            turn(7);
            time = 0;
        }
        //check if the greenball is lower than 120 at Y
        if (getY() <= 120)  {
            hit= true;
            //spawn explosion
            if (hit)    { 
                //set for loop to create the purple things to explode
                for (int i = 0; i < 12; i++)    {
                    //each purple will have specific velocity and angle
                    //credit to mr. cohen for example of i*30
                    g[i] = new GreenballPurple (pVelocity, i*30);
                    //add object at position of Greenball            
                    getWorld().addObject (g[i], getX(), 120);  

                }
                hit = false;
            }
            //remove the greenball
            getWorld().removeObject(this);
        }  

    } 

    /**
     * remove object method when hit enemy
     */
    public void greenTouch()
    {
        getWorld().removeObject(this);
    }
}
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THIS IS THE PLAYER THE USER CONTROLS
 * IT DOES NOT DO ANYTHING. ALL THE USER CAN DO IS DRAG IT AROUND AND AVOID ENEMIES
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class Player extends Actor
{
    //declare variables
    private int time;
    private int shield = 100;
    private int shieldRecover = 0;
    private int recharge = 5;
    private Feather feather;
    private boolean touching = true;
    private Score store;
    private Coins addCoins;

    /**
     * constructor to hold the score
     */
    public Player(Score storeScore, Coins keepCoins)
    {
        store = storeScore;
        addCoins = keepCoins; 
    }

    /**
     * Act - do whatever the Player wants to do.
     */
    public void act() 
    {
        //use time to make the square spin
        time++;

        //make the square turn  so every few acts to make a spinning effect
        if (time == 4)  {
            //turn it
            turn(30);
            //reset time
            time = 0; 
        }

        //call invinsible square
        invincibleSquare();

    }   

    /**
     * invincible square method
     */
    public void invincibleSquare()
    {
        //check if w key is down
        if (Greenfoot.isKeyDown("b") && shield >= 0)
        {
            //decrease shield strength
            shield--;
            Star s = (Star)getOneIntersectingObject(Star.class);
            if (s != null)  {

                //add score
                store.add(90);
                //add the coins to coins class
                addCoins.add(100);
                //go to remove method from Star class
                s.remove();

            }

        }
        //check if press z 
        else if (Greenfoot.isKeyDown("z"))    {
            //do nothing
            touching = false;
        }
        //check if pressed x
        else if (Greenfoot.isKeyDown("x"))  {
            touching = true;
        }
        //otherwise, recover shield and allow touching enemy
        else  {          
            //increase shieldRecover
            shieldRecover++;
            //recover shield every multiple of 3 until max allowed
            if (shieldRecover%recharge == 0 && shield <= 100)   {
                //increase shield;
                shield++;
            }
            Star s = (Star)getOneIntersectingObject(Star.class);
            if (s != null)  {
                //add score
                store.add(90);
                //add the coins to coins class
                addCoins.add(100);
                //go to remove method from Star class
                s.remove();

            }
            //check if invincibility is off
            if (touching)   {
                //if it touches any enemies, then end the game
                Enemies e = (Enemies)getOneIntersectingObject(Enemies.class);
                if (e != null)  {
                    MainWorld m = (MainWorld)getWorld();
                    //call method in main world to display score
                    m.finalScore();
                }
            }
        }
    }

    /**
     * return the value of shield so it can be accessed by main world
     */
    public int shieldCount()
    {
        return shield;
    }

    /**
     * increase the recharge speed method
     */
    public void rechargeUp()
    {
        //make sure recharge has a value greater than 0
        if (recharge > 1)  {
            //increase reacharge by decresing the value of recharge
            recharge--;
        }
    }

}
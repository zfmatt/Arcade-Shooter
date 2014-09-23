import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Medusa here.
 * IMAGE CREDITS to: 
 * 
 * http://www.giantbomb.com/castlevania/61-6673/all-images/52-178327/dra032/51-1315387/
 * and whomever created this image
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class Medusa extends Enemies
{
    //declare variables    
    private int medusaHealth = 1300;
    //attack values
    private int fire;
    private int green;
    private int strong;
    private int explode;
    //more variables
    private int scene;
    private boolean hit = false;
    private Player player;
    private Star star;

    /**
     * constructor with player
     */
    public Medusa(Player player)
    {
        this.player = player;
    }

    /**
     * Act - do whatever the Medusa wants to do.
     */
    public void act() 
    {
        //move it
        move(2);
        //increase scene
        scene++;
        //swap image every few acts
        if (scene == 0) {
            setImage("Medusa.png");
        }
        if (scene == 5)  {
            setImage("Medusa.png");
        }
        if (scene == 10)    {
            setImage("Medusa.png");
            scene = 0;
        }
        //call check collision method
        checkCollision();

        //turn towards method
        turnTowards(player.getX(), player.getY());

        //call medusa zero method
        medusaZero();

    }  

    /**
     * If medusa's health is below zero, remove it
     */
    public void medusaZero()
    {
        //if medusa health is below zero
        if (medusaHealth <= 0)  {
            hit = true;

            MainWorld m = (MainWorld)getWorld();
            //call method from main world
            m.killInfo();
            m.medusaPoints();
            //if hit is true 
            if (hit)    {
                star = new Star();

                //add a star into the world
                getWorld().addObject(star, getX(), getY());

                hit = false;
            }
            //remove medusa
            getWorld().removeObject(this);
        }
    }

    /**
     * method to check for collision
     */
    public void checkCollision()
    {
        MainWorld m = (MainWorld)getWorld();
        //get info from methods from main world
        fire = m.storeFireAttack();
        green = m.storeGreenAttack();
        strong = m.storeStrongAttack();
        explode = m.storeExplodeAttack();
        //single object collision detection
        //*********************************
        Fireball a = (Fireball)getOneIntersectingObject(Fireball.class);
        if  (a != null) {
            medusaHealth -= fire;//decrease health
            setImage("medusaWhite.png");
            a.fireTouch();//remove attack once it hits enemy
        }
        Greenball g = (Greenball)getOneIntersectingObject(Greenball.class);
        if  (g != null) {
            medusaHealth -= green;//decrease health
            setImage("medusaWhite.png");
            g.greenTouch();//remove attack once it hits enemy
        }
        StrongFire f = (StrongFire)getOneIntersectingObject(StrongFire.class);
        if (f != null)  {
            medusaHealth -= strong;//decrease health
            setImage("medusaWhite.png");
            f.strongTouch();//remove attack once it hits enemy
        }
        GreenballPurple p = (GreenballPurple)getOneIntersectingObject(GreenballPurple.class);
        if (p != null)  {
            medusaHealth -= explode;//decrease health
            setImage("medusaWhite.png");
            p.explodeTouch();//remove attack once it hits enemy
        }
    }

}

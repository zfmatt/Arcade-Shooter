import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eye here.
 * Author: Matthew Ngai and help from MR COHEN
 * 
 * 
 * THIS PHOTO WAS TAKEN FROM:
 * Castlevania: Order of Ecclesia - By: KONAMI
 * @version (2.2)
 */
public class Eye extends Enemies
{
    //declare variables
    private int scene = 0;
    private int timer = 0;
    private int eyeHealth = 900;
    private int blueVelocity = 5;
    private int speed = 6;
    private int xMove = 0;
    private boolean hit = false;
    private GrowBall growBall;
    private BlueBall[] b = new BlueBall [8];
    private Star star;
    //variables for attacking weapons
    private int fire;
    private int green;
    private int strong;
    private int explode;
    /**
     * constructor for Eye
     */
    public Eye()
    {
        //initialize start x speed
        xMove = 1;
    }

    /**    
    Act Method
     */
    public void act() 
    {
        //counter for scene to swap picture
        scene++;  
        //timer for attacks
        timer++;
        //swap images every few acts
        if (scene == 0)     {
            setImage("Eye.png");
        }
        if (scene == 45)    {
            setImage("EyeClosed.png");
        }
        if (scene == 60)    {
            setImage("Eye.png");
            scene = 0;
        }
        //call eye Check collision method
        eyeCheckCollision();
        //call method deathZero
        eyeZero();

        //call method for eye's attack        
        if (eyeHealth > 0)    {
            //set a row of explosions with a timer
            if (timer == 10)    {
                //spawn an explosion
                blueBallAttack();
            }
            if (timer == 20)    {
                blueBallAttack();
            }
            if (timer == 30)    {
                blueBallAttack();
            }
            if (timer == 200)    {
                //reset timer
                timer = 0;
            }
        }
    } 

    /**
     *  eye check collision method
     */

    public void eyeCheckCollision()
    {
        MainWorld m = (MainWorld)getWorld();
        //call method from main world
        fire = m.storeFireAttack();
        green = m.storeGreenAttack();
        strong = m.storeStrongAttack();
        explode = m.storeExplodeAttack();
        //using single collision detection method, chech it it hits enemy. 
        //Change image to White if it does
        //also decrease health and remove the attacking object
        Fireball a = (Fireball)getOneIntersectingObject(Fireball.class);
        if  (a != null) {
            //decrease health
            eyeHealth -= fire;
            setImage("EyeWhite.png");
            a.fireTouch();//remove attacking object once it hits enemy
        }
        Greenball g = (Greenball)getOneIntersectingObject(Greenball.class);
        if  (g != null) {
            eyeHealth -= green;//decrease health
            setImage("EyeWhite.png");
            g.greenTouch();//remove attacking object once it hits enemy
        }
        StrongFire f = (StrongFire)getOneIntersectingObject(StrongFire.class);
        if (f != null)  {
            eyeHealth -= strong;//decrease health
            setImage("EyeWhite.png");
            f.strongTouch();//remove attacking object once it hits enemy
        }
        GreenballPurple p = (GreenballPurple)getOneIntersectingObject(GreenballPurple.class);
        if (p != null)  {
            eyeHealth -= explode;//decrease health
            setImage("EyeWhite.png");
            p.explodeTouch();//remove attacking object once it hits enemy
        }

    }

    /**
     * If Eye's health is <= 0, then remove Eye
     */
    public void eyeZero()
    {
        //check if eye's health is lower than 0
        if (eyeHealth <= 0)   {
            hit = true;

            MainWorld m = (MainWorld)getWorld();
            //call method from main world
            m.killInfo();
            m.eyePoints();
            if (hit)    {
                growBall = new GrowBall();
                star = new Star();
                //add a red giant ball into the world
                getWorld().addObject(growBall, getX(), getY());
                //add a star into the world
                getWorld().addObject(star, getX(), getY());

                hit = false;
            }
            //remove this object
            getWorld().removeObject(this);
        }
        else {
            //call random movement method
            randomMovement();
        }
    }

    /** 
     * method for spawning blue ball attacks
     */
    public void blueBallAttack()
    {
        //set for loop to create the objects to explode
        //ideas slightly adapted from Mr Cohen's Missile Asset example
        for (int i = 0; i < 8; i++)    {
            //each object will have specific velocity and angle
            b[i] = new BlueBall(blueVelocity, i*45);
            //add object at position of death           
            getWorld().addObject (b[i], getX(), getY());  
        }
    }

    /**
     * Generate random movement for death.
     * Mr Cohen helped with this piece of code**
     */
    public void randomMovement()
    {
        //move opposite direction if the object hits left wall
        if (getX() <= 10 ) {
            xMove = 1;
        }
        //move opposite direction if the object hits right wall
        else if (getX() >= getWorld().getWidth() - 10) {
            setLocation(getX() - 22, getY());
            xMove = -1;
        }
        //set location and movement based on collision
        setLocation(getX() + (xMove*speed), getY());
    }
  
}
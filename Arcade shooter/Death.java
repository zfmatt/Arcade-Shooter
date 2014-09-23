import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THIS CLASS IS AN ENEMY THAT ATTACKS AND SPAWNS EXPLOSIONS UPON DYING 
 * 
 * THIS IMAGE WAS BORROWED FROM:
 * Castlevania: Dawn of Sorrow Nintendo DS - By: KONAMI
 * @author (Matthew Ngai) and some help from Mr Cohen for debugging
 * @version (2.2)
 */
public class Death extends Enemies
{
    //declare instance variables
    private int scene = 0;
    private int deathHealth = 600;
    private int pVelocity = 4;
    private int xMove = 0;
    private int xDir = 0;
    private int yDir = 0;
    private int  speed = 5;
    private DeathAttack deathAttack;
    private int [] countX = new int[8];
    private int [] countY = new int[8];
    private boolean hit = false;
    private Sickle[] s = new Sickle [8];
    private Star star;
    //attack values
    private int fire;
    private int green;
    private int strong;
    private int explode;
    /**
     * constructor for death 
     */
    public Death()
    {
        xMove = 1;
    }

    /**
     * Act - do whatever the Death wants to do. 
     */
    public void act() 
    {
        //scene counter for changing images
        scene++;
        //swap image every few acts
        if (scene == 0) {
            setImage("death.png");
        }
        if (scene == 5)  {
            setImage("death2.png");
        }
        if (scene == 10)    {
            setImage("death.png");
            scene = 0;
        }
        //call death check collision method
        deathCheckCollision();
        //call deathZero method
        deathZero();
        //call method for death's attack
        deathWillAttack();
    }  

    /**
     * If death's health is <= 0, then add explosions and remove death
     */
    public void deathZero()
    {
        //check if death's health is lower than 0
        if (deathHealth <= 0)   {
            hit = true;

            MainWorld m = (MainWorld)getWorld();

            //call method from main world 
            m.killInfo();
            //add points for kill
            m.deathPoints();
            if (hit)    {
                //set for loop to create the objects (sickle) to explode
                for (int i = 0; i < 8; i++)    {
                    //each purple will have specific velocity and angle
                    s[i] = new Sickle(pVelocity, i*45);
                    //add object at position of death           
                    getWorld().addObject (s[i], getX(), getY());  
                }
                //create another one
                for (int e = 0; e < 8; e++)  {
                    //each purple will have specific velocity and angle
                    s[e] = new Sickle(pVelocity + 2, e*45);
                    //add object at position of death           
                    getWorld().addObject (s[e], getX(), getY());  
                }
                //create another one
                for (int w = 0; w < 8; w++)  {
                    //each purple will have specific velocity and angle
                    s[w] = new Sickle(pVelocity +4, w*45);
                    //add object at position of death           
                    getWorld().addObject (s[w], getX(), getY());  
                }
                star = new Star();
                //add a star into the world
                getWorld().addObject(star, getX(), getY());

                hit = false;
            }
            //remove this object
            getWorld().removeObject(this);
        }
        else{
            //call random movement method
            randomMovement();
        }
    }

    /**
     * See if attacks collide with death
     */
    public void deathCheckCollision()
    {
        MainWorld m = (MainWorld)getWorld();
        //get method from main world class
        fire = m.storeFireAttack();
        green = m.storeGreenAttack();
        strong = m.storeStrongAttack();
        explode = m.storeExplodeAttack();
        //single object collision detection 
        //********************************
        Fireball a = (Fireball)getOneIntersectingObject(Fireball.class);
        if  (a != null) {
            deathHealth -= fire;//decrease health
            setImage("deathWhite.png");
            a.fireTouch();//remove attacking object after it touches enemy
        }
        Greenball g = (Greenball)getOneIntersectingObject(Greenball.class);
        if  (g != null) {
            deathHealth -= green;//decrease health
            setImage("deathWhite.png");
            g.greenTouch();//remove attacking object after it touches enemy
        }
        StrongFire f = (StrongFire)getOneIntersectingObject(StrongFire.class);
        if (f != null)  {
            deathHealth -= strong;//decrease health
            setImage("deathWhite.png");
            f.strongTouch();//remove attacking object after it touches enemy
        }
        GreenballPurple p = (GreenballPurple)getOneIntersectingObject(GreenballPurple.class);
        if (p != null)  {
            deathHealth -= explode;//decrease health
            setImage("deathWhite.png");
            p.explodeTouch();//remove attacking object after it touches enemy
        }

    }

    /**
     * method for spawning death's attacks at the specified locations
     */
    public void deathWillAttack()
    {
        //using random greenfoot integer, spawn random attacks
        if (Greenfoot.getRandomNumber(100) == 1 && deathHealth > 0) {
            deathAttack = new DeathAttack();
            //add the attack
            getWorld().addObject(deathAttack, getX(), getY());
        }    
    }

    /**
     * Generate random movement for death.
     * Mr Cohen helped with this piece of code**
     */
    public void randomMovement()
    {
        //move opposite direction if the object hits the left wall
        if (getX() <=10) {
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
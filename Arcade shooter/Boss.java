import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THIS IS THE BOSS MODE. IT CAN REGENERATE HEALTH AND ATTACKS. GOOD LUCK!
 * 
 * IMAGE BORROWED FROM: http://img.neoseeker.com/v_concept_art.php?caid=2274
 * originally created by: Fluidity
 * 
 * @author (Matthew Ngai) 
 * @version (2.2)
 */
public class Boss extends Enemies
{
    //declare variables
    private int bossHealth = 6000;
    //attack values
    private int fire;
    private int green;
    private int strong;
    private int explode;
    private int scene;
    private Orange[] s = new Orange[12];
    private int ballTimer = 0;
    private SideBall sideBall;
    private SideBallRight sideBallRight;
    private Bat bat;
    private int xMove = 0;
    private int  speed = 6;
    private int healthRegen = 0;
    /**
     * constructor for Boss
     */
    public Boss()
    {
        xMove = 1;
    }

    /**
     * Act - do whatever the Boss wants to do.
     */
    public void act() 
    {
        //increase timers for image, shooting attacks and health regen
        scene++;
        ballTimer++;
        healthRegen++;
        //swap images every few acts
        if (scene == 0) {
            setImage("Dracula.png");
        }

        if (scene == 5)    {
            setImage("Dracula.png");
            scene = 0;
        }
        //call methods
        checkCollision();
        bossZero();
        //add side balls at certain intervals         
        if (bossHealth > 0) {
            //call method
            shootOrange();
            //set time for shooting attacks on the side
            if (ballTimer == 500)   {
                addSideBall();
            }
            if (ballTimer == 550)   {
                addSideBallRight();
            }
            if (ballTimer == 600)   {
                addSideBall();
            }
            if (ballTimer == 650)   {
                addSideBallRight();
            }
            if (ballTimer == 700)   {
                addSideBall();
            }
            if (ballTimer == 750)   {
                addSideBallRight();
            }
            if (ballTimer == 800)   {
                addSideBall();
                ballTimer = 0;
            }

            if (ballTimer > 300 && ballTimer < 460)    {
                addBat();
            }
            randomMovement();
        }

    }    

    /**
     * If boss's health is below zero, remove it
     */
    public void bossZero()
    {
        //if boss health is below zero
        if (bossHealth <= 0)  {
            //remove object
            getWorld().removeObject(this);
            //tell user they did great
            System.out.println("Congratulations!!!, you defeated Dracula!!!");
            //stop program to end
            Greenfoot.stop();
        }
        else {
            //regen health for boss every 25 acts
            if (healthRegen%25 == 0) {
                //regen health
                bossHealth+= 2;
            }
        }

    }

    /**
     * method to check for collision
     */
    public void checkCollision()
    {
        MainWorld m = (MainWorld)getWorld();
        //get info from main world methods
        fire = m.storeFireAttack();
        green = m.storeGreenAttack();
        strong = m.storeStrongAttack();
        explode = m.storeExplodeAttack();
        //single object collision detection
        //**********************************
        Fireball a = (Fireball)getOneIntersectingObject(Fireball.class);
        if  (a != null) {
            bossHealth -= fire;//decrease health
            setImage("bossWhite.png");
            a.fireTouch();//remove attacking object once it touches enemy
        }
        Greenball g = (Greenball)getOneIntersectingObject(Greenball.class);
        if  (g != null) {
            bossHealth -= green;//decrease health
            setImage("bossWhite.png");
            g.greenTouch();//remove attacking object once it touches enemy
        }
        StrongFire f = (StrongFire)getOneIntersectingObject(StrongFire.class);
        if (f != null)  {
            //only takes 1/3 of that damage
            bossHealth -= strong/3;//decrease health
            setImage("bossWhite.png");
            f.strongTouch();//remove attacking object once it touches enemy
        }
        GreenballPurple p = (GreenballPurple)getOneIntersectingObject(GreenballPurple.class);
        if (p != null)  {
            bossHealth -= explode;//decrease health
            setImage("bossWhite.png");
            p.explodeTouch();//remove attacking object once it touches enemy
        }
    }

    /**
     * shoot orange attacks
     */
    public void shootOrange()
    {
        //shoot attacks based on random number
        if (Greenfoot.getRandomNumber(200) == 1)    {
            //for loop to make several attacks
            for (int i = 0; i < 12; i++) {
                s[i] = new Orange();
                getWorld().addObject(s[i], i*170, 130);
            }
        }
        //shoot attacks based on random number
        if (Greenfoot.getRandomNumber(250) == 1)    {
            //for loop to make several attacks
            for (int i = 0; i < 12; i++) {
                s[i] = new Orange();
                getWorld().addObject(s[i], i*170 +40, 50);
            }
        }
    }

    /**
     * method to shoot a ball sideways
     */
    public void addSideBall()
    {
        sideBall = new SideBall();
        //add a ball
        getWorld().addObject(sideBall, 50, Greenfoot.getRandomNumber(850));
    }

    /**
     * method for side ball right
     */
    public void addSideBallRight()
    {
        sideBallRight = new SideBallRight();
        //add a ball
        getWorld().addObject(sideBallRight, 850, Greenfoot.getRandomNumber(850));
    }

    /**
     * add bats
     */
    public void addBat()
    {
        bat = new Bat();
        //add bats based on random number
        if (Greenfoot.getRandomNumber(2) == 1) {
            getWorld().addObject(bat, Greenfoot.getRandomNumber(160) + 200, Greenfoot.getRandomNumber(50));
        }
        //add bats based on random number
        if (Greenfoot.getRandomNumber(2) == 1) {
            getWorld().addObject(bat, Greenfoot.getRandomNumber(160) + 450, Greenfoot.getRandomNumber(50));
        }
        //add bats based on random number
        if (Greenfoot.getRandomNumber(2) == 1) {
            getWorld().addObject(bat, Greenfoot.getRandomNumber(160) + 700, Greenfoot.getRandomNumber(50));
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

    /**
     * return info for boss health
     */
    public int returnHealth()
    {
        return bossHealth;
    }
}

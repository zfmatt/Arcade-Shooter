import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THIS CLASS IS AN ENEMY THAT MOVES AND ATTACKS
 * 
 * IMAGE BORROWED FROM: Castlevania: Portrait of Ruin - By: KONAMI
 * @author (Matthew Ngai) with help from Mr Cohen for At worlds edge bug
 * @version (2.2)
 */
public class Deathmask extends Enemies
{
    //declare variables
    private int deathmaskHealth = 1600;
    private boolean hit = false;
    private int scene = 0;
    private Feather feather;
    private Player player;
    private ShootBall shootBall;
    private Star star;
    //variables for attack values
    private int fire;
    private int green;
    private int strong;
    private int explode;
    /**
     * constructor for Deathmask that has player
     */
    public Deathmask (Player player)
    {
        this.player = player;
    }

    /**
     * Act - do whatever the Deathmask wants to do. 
     */
    public void act() 
    {
        //move
        move(5);
        //call methods
        spin();
        deathmaskCollision();
        deathmaskZero();
        //call attack method
        deathmaskAttack();
        //scene counter for changing images
        scene++;
        //swap image every few acts
        if (scene == 0) {
            setImage("Deathmask.png");
        }
        if (scene == 5)  {
            setImage("Deathmask.png");
        }
        if (scene == 10)    {
            setImage("Deathmask.png");
            scene = 0;
        }

    }  

    /**
     *  eye check collision method
     */

    public void deathmaskCollision()
    {
        MainWorld m = (MainWorld)getWorld();
        //get info from methods in main world
        fire = m.storeFireAttack();
        green = m.storeGreenAttack();
        strong = m.storeStrongAttack();
        explode = m.storeExplodeAttack();
        //*****single object collision detection
        
        Fireball a = (Fireball)getOneIntersectingObject(Fireball.class);
        if  (a != null) {
            deathmaskHealth -= fire;//decrease health
            setImage("DeathmaskWhite.png");
            a.fireTouch();//remove attacking object once it hits enemy
        }
        Greenball g = (Greenball)getOneIntersectingObject(Greenball.class);
        if  (g != null) {
            deathmaskHealth -= green;//decrease health
            setImage("DeathmaskWhite.png");
            g.greenTouch();//remove attacking object once it hits enemy
        }
        StrongFire f = (StrongFire)getOneIntersectingObject(StrongFire.class);
        if (f != null)  {
            deathmaskHealth -= strong;//decrease health
            setImage("DeathmaskWhite.png");
            f.strongTouch();//remove attacking object once it hits enemy
        }
        GreenballPurple p = (GreenballPurple)getOneIntersectingObject(GreenballPurple.class);
        if (p != null)  {
            deathmaskHealth -= explode;//decrease health
            setImage("DeathmaskWhite.png");
            p.explodeTouch();//remove attacking object once it hits enemy
        }
    }

    /**
     * If deathmask's health is <= 0, then remove it
     */
    public void deathmaskZero()
    {
        //check if deathmask's health is lower than 0
        if (deathmaskHealth <= 0)   {
            hit = true;

            MainWorld m = (MainWorld)getWorld();
            //call method from main world
            m.killInfo();
            m.deathmaskPoints();
            if (hit)    {
                feather = new Feather(player);
                star = new Star();
                //add the feather object once deathmask dies
                getWorld().addObject(feather, getX(), getY());
                //add the star to the world when deathmask dies
                getWorld().addObject(star, getX(), getY());

                hit = false;
            }
            //remove this object
            getWorld().removeObject(this);
        }
    }

    /**
     * the primary attack from deathmask
     */
    public void deathmaskAttack()
    {
        //add random attacks at random times
        if (Greenfoot.getRandomNumber(150) == 1 && deathmaskHealth > 0)  {
            shootBall = new ShootBall();
            //add the attack
            getWorld().addObject(shootBall, getX(), getY());
        }
    }

    /**
     * method for making deathmask spin in random directions
     */
    public void spin()
    {
        //if it reaches the x coordinate of the world's edge then turn it
        if (getX() < 10)  {
            //move deathmask out of <10 range
            setLocation(getX() + 22, getY());
            //bounce it
            turn(155);
        }
        if (getX() > getWorld().getWidth() -10)   {
            //move deathmask out of >790 range
            setLocation(getX() - 22, getY());
            //bounce it
            turn(151);
        }
        //if it reaches the Y coordinate of world's edge, then turn it
        if (getY() < 10)  {
            //move deathmask out of top
            setLocation(getX(), getY() + 22);
            //bounce it
            turn(155);
        }
        //limit the Y coordinates
        if (getY() > getWorld().getHeight()-400)    {
            //move it out a bit
            setLocation(getX(), getY() -22);
            turn(151);//bounce it
        }
        //set the location of deathmask
        setLocation(getX(), getY());
    }

}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * THIS IS THE MAIN WORLD CLASS
 * It controls the main flow of the game such as adding levels and adding/removing objects 
 * as well as regulating how game works and MUCH MORE. LEVEL 1-4 HAVE NEW ENEMIES. 5-9 JUST REPEATS AND GETS HARDER.
 * 
 * @author (Matthew Ngai), Mr.Cohen, Michael Kolling and polle 
 *** Assisted Help with respective example code: Mr. Cohen for the "Hp bar" example, "Shooting ball with StartX, endX",
 *** and anything else I may forgot to mention.
 *** as well as Greenfoot user "polle" for the scrolling background.
 *** and also to Michael Kolling for his tutorials
 *** 
 *** CREDITS FOR BACKGROUN PICTURE OF HONG KONG VICTORIA HARBOUR PICTURE:
 *** Photo Courtesy of: Hong Kong Tourism Board 
 *** 
 *** *Credits for borrowed music to:
 *** *League of Legends Champion Select Music -- By: Riot Inc.
 *** *Rainbow road music from Mario Kart 7 -- By: Nintendo EAD, Retro Studios and Nintendo
 *** *Castlevania Lament of Innocence  Leons Theme -- Developed and published by: KONAMI . Music by: Michiru Yamane
 *** *GUNSHOT NOISE. By -- : http://soundbible.com/1395-Silencer.html
 *** *Thunder noise -- by:  http://soundbible.com/2015-Thunder-Strike-1.html
 *** *Greenball noise-- By: http://soundjax.com/laser-1.html
 *** *Chaching noise-- by: http://soundbible.com/333-Cash-Register-Cha-Ching.html
 *
 *@version 2.2
 */
public class MainWorld extends World
{
    //declare variables
    private Player player;
    private ShieldWord shieldWord;
    private Fireball fireball;
    private Greenball greenball;
    private Medusa medusa;
    private Enemies enemies;
    private Blur blur;
    //more variables
    private Boss boss;
    private Deathmask deathmask;
    private ShieldBar shieldBar;
    private CooldownBar cooldownBar;
    private CooldownWord cooldownWord;
    private HealthBar healthBar;
    private StrongFire[] s = new StrongFire[7];
    private Death death = new Death();
    private Eye eye;
    private Level level;
    private Score score;
    private Coins coins;

    //variables for the shop
    private Shop shop;
    private int shopTimer = 0;
    private boolean shopTimerControl = false;
    private boolean updatedCoins = false;
    private boolean controlEndLevel = false;
    private int clickCharge = 0;
    private BuyFire buyFire;
    private BuyGreen buyGreen;
    private BuyRecharge buyRecharge;
    private BuyUlt buyUlt;
    private Cross cross;
    private MainMenu mainMenu;
    //ult cooldown variable
    private int ultCooldown = 1200;
    private int ultCooldownTime = 1200;
    private boolean ultCooldownTrue = false;
    //counting number of spawn variables
    private int medusaSpawn = 1;
    private int deathSpawn = 1;
    private int deathmaskSpawn = 1;    
    private int eyeSpawn = 1;
    //attack variables
    private int fireAttack = 25;
    private int greenAttack = 50;
    private int strongAttack = 500;
    private int explodeAttack = greenAttack/12;
    //other variables
    private int levelCounter = 1;
    private int totalEnemy = 0;   
    private int currentShield;
    private int currentCd;
    private int currentHealth;
    private int points = 0;
    private int coinCounter = 0;
    private int validCoin;
    //clickable button variables
    private StartGame startGame;
    private BossMode bossMode;
    private Credits credits;
    private Instructions instructions;
    private Back back;
    private InstructionText instructionText;
    private CreditText creditText;
    //boolean variables to control whether or not to start game
    private boolean gameStartControl = false;
    private boolean bossStartControl = false;
    //**By:Mr Cohen
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    //**
    //timer to regulate how many greenshots can be shot
    private int gTimer = 0;
    //create instance of greenfoot sound
    //GreenfootSound menuSelection = new GreenfootSound("League of Legends - Champion Select.mp3");

    ////////////////////////////////////////////////////////////////
    //This piece of code was borrowed from "polle", a Greenfoot user.
    //it is the scrolling background effect
    private static final GreenfootImage bgImage = new GreenfootImage("ScrollBackground.png");
    private static final int scrollSpeed = 1;

    private GreenfootImage scrollingImage;
    private int scrollPosition = 0;
    //end here
    //////////////////////////////////////////////////////////////////////
    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 850, 1, false);
        //set order in which they appear
        setPaintOrder( CreditText.class, InstructionText.class, BuyFire.class, BuyGreen.class, BuyRecharge.class, BuyUlt.class, Cross.class, Back.class, StartGame.class, BossMode.class, Instructions.class, Credits.class, MainMenu.class, Shop.class, Player.class);
        //initialize level
        level = new Level ("Level: ");
        addObject (level, 38, 24);

        ///////////////////////////////////////////////////////////////////////////////////
        //This piece of code was borrowed from "polle", a Greenfoot user.
        //it is the scrolling background effect
        GreenfootImage background = new GreenfootImage(900, 850);
        scrollingImage = getScrollingImage(900, 850);
        background.drawImage(scrollingImage, 0, 0);
        setBackground(background);
        //end here
        ///////////////////////////////////////////////////////////////////////////////////
        /**
         * Constructor to initialize the objects as required. Includes Heads up display as well
         * as any menu icons and other objects needed in this game
         */
        //add the main menu screen
        mainMenu = new MainMenu();
        addObject(mainMenu, 450, 425);

        startGame = new StartGame();
        addObject(startGame, 450, 404);

        bossMode = new BossMode();
        addObject(bossMode, 450, 507);

        instructions = new Instructions();
        addObject(instructions, 450, 609);

        credits = new Credits();
        addObject(credits, 450, 711);

        back = new Back();

        instructionText = new InstructionText();
        
        creditText = new CreditText();
        //initialize score
        score = new Score("Score: ");
        addObject(score, 141, 24);

        coins = new Coins("Coins: ");
        addObject(coins, 270, 24);

        player = new Player(score, coins);
        addObject(player, 409, 779);

        shieldBar = new ShieldBar (100);
        addObject (shieldBar, 49, 91);

        shieldWord = new ShieldWord();
        addObject(shieldWord, 31, 64);

        cooldownBar = new CooldownBar (1200);
        addObject(cooldownBar, 50, 148);

        cooldownWord = new CooldownWord();
        addObject(cooldownWord,52, 123);

        healthBar = new HealthBar(6000);
        addObject(healthBar, 450 , 50);

        //set the level counter
        level.setValue(levelCounter);
        //set coin counter
        coins.add(coinCounter);
        //set point adder
        score.add(points);

        //play music when game is open
        //menuSelection.play();
        //loop it
        //menuSelection.playLoop();
    }

    /**
     * This is the act() method in MainWorld
     */

    public void act()
    {
        //timer for greenball
        gTimer++;
        //regulate it so that user can only shoot Greenball at zero
        if (gTimer == 1)    {
            gTimer = 0;
        }
        ///////////////////////////////////////////////////////////////////////////////////
        //This piece of code was borrowed from "polle", a Greenfoot user.
        //it is the scrolling background effect
        if(scrollSpeed > 0 && scrollPosition <= 0) {
            scrollPosition = getWidth();
        }
        if(scrollSpeed < 0 && scrollPosition >= getWidth()) {
            scrollPosition = 0;
        }
        scrollPosition -= scrollSpeed;
        paint(scrollPosition);
        //end here
        ///////////////////////////////////////////////////////////////////////////////////
        //call control method
        control();
        //call screen selection method
        screenSelection();
        //call buy shop method to buy from shop
        buyShop();
        //notify user that the game has ended
        if (levelCounter == 10){
            //display message
            System.out.println("Game finished! Score: " + score.returnScore());
            //stop the game
            Greenfoot.stop();
        }
    }

    /**
     * method for boolean control for starting game and opening shop
     */
    public void control()
    {
        //use boolean to start the game
        if (gameStartControl)   {
            //start the game
            startTheGame();
            //boss health bar is not required
            removeObject(healthBar);
        }
        //use boolean to start boss mode
        if (bossStartControl)   {
            //start boss level
            bossStartGame();
            //no level bar is required
            removeObject(level);
        }
        //use boolean to allow shop to be opened
        if (shopTimerControl)   {
            //increase shop timer after level is cleared 
            shopTimer++;
            //225 acts after level is cleared, open the shop
            if (shopTimer == 225)   {  
                //open shop after 225 delay
                levelShop();
                shopTimer = 0;
                //change booleans to make shop work
                shopTimerControl = false;
                updatedCoins = true;
                controlEndLevel = true;
            }
        }
    }

    /**
     * screenSelection method to select stuff from screen
     */
    public void screenSelection()
    {
        //stop music after game has started
        if (Greenfoot.mouseClicked(startGame) || Greenfoot.mouseClicked(bossMode))
        {
            //stop the main menu music once game starts
            //menuSelection.stop();
        }
        //if the player clicks start game, then start the game
        if (Greenfoot.mouseClicked(startGame)) {
            //allow main game to start
            gameStartControl = true;
            //remove objects as necessary
            removeObject(mainMenu);
            removeObject(startGame);
            removeObject(bossMode);
            removeObject(instructions);
            removeObject(credits);
            //set a short delay
            Greenfoot.delay(50);
            //create instance of the sound
            GreenfootSound startMusic = new GreenfootSound("Mario.mp3");
            //play music for starting normal game
            startMusic.play();
            //replay it
            startMusic.playLoop();
        }
        //if player clicks boss mode
        if (Greenfoot.mouseClicked(bossMode)) {
            //remove necessary objects
            removeObject(mainMenu);
            removeObject(startGame);
            removeObject(bossMode);
            removeObject(instructions);
            removeObject(credits);
            removeObject(score);
            removeObject(coins);
            //delay once game starts
            Greenfoot.delay(50);
            boss = new Boss();
            addObject(boss, 450, 200);
            //allow boss level to start
            bossStartControl = true;
            //create instance of the sound
            GreenfootSound bossMusic = new GreenfootSound("Castlevania Lament of Innocence  Leons Theme.mp3");
            //play music for boss mode
            bossMusic.play();
            //replay it
            bossMusic.playLoop();
        }
        //if player clicks instructions
        if (Greenfoot.mouseClicked(instructions)) {
            //remove respective objects
            removeObject(startGame);
            removeObject(bossMode);
            removeObject(instructions);
            removeObject(credits);
            //add the back option
            addObject(back, 812, 804);
            //add the instructions
            addObject(instructionText, 462, 391);
        }
        //if player clicks credits
        if (Greenfoot.mouseClicked(credits)){
            //remove certain objects
            removeObject(startGame);
            removeObject(bossMode);
            removeObject(instructions);
            removeObject(credits); 
            //add a back button
            addObject(back, 812, 804);
            addObject(creditText, 491, 412);
        }
        //if player clicks back
        if (Greenfoot.mouseClicked(back))   {
            //add and remove respective objects
            addObject(mainMenu, 450, 425);
            addObject(startGame, 450, 404);
            addObject(bossMode, 450, 507);
            addObject(instructions, 450, 609);
            addObject(credits, 450, 711);
            removeObject(back);
            removeObject(instructionText);
            removeObject(creditText);
        }
    }

    /**
     * start boss mode
     */
    public void bossStartGame()
    {
        //call shoot method to shoot
        shootInput();
        /**
         * all health bar related coding belongs to mr Cohen.
         */
        //send to shield class
        shieldBar.changeBar(currentShield);
        currentShield = player.shieldCount();
        //current cooldown is = to ult cooldown
        currentCd = ultCooldown;
        cooldownBar.changeBar(currentCd);
        //send current health of boss to Boss class
        currentHealth = boss.returnHealth();
        healthBar.changeBar(currentHealth);
    }

    /**
     * start the game with running several methods
     */
    public void startTheGame()
    {
        //call shoot method to shoot
        shootInput(); 
        //start new level once level counter is increased
        if (levelCounter == 1)  
            startLevel1();
        if (levelCounter == 2)  
            startLevel2();
        if (levelCounter == 3)  
            startLevel3();
        if (levelCounter == 4)  
            startLevel4();
        if (levelCounter == 5)  
            startLevel5();
        if(levelCounter == 6)
            startLevel6();
        if(levelCounter == 7)
            startLevel7();
        if(levelCounter == 8)
            startLevel8();
        if(levelCounter == 9)
            startLevel9();
        //send the current shield to shield bar class
        shieldBar.changeBar(currentShield);        
        currentShield = player.shieldCount();
        //current cooldown is = to ult cooldown
        currentCd = ultCooldown;
        //send the current cooldown to the cooldown bar class so it can display
        cooldownBar.changeBar(currentCd);
    }
    //check kills per level
    public void killInfo()
    {
        //add to total enemy to see how many enemies have been killed as per level
        totalEnemy++;
        //reset level if total enemies per level is max
        if (totalEnemy == 4 && levelCounter <= 5)    {
            shopTimerControl = true;           
            totalEnemy = 0;
        }
        //if it is level 6 or higher
        if (levelCounter >= 6 && totalEnemy == levelCounter)  {
            //allow shop to be open at end of each level
            shopTimerControl = true;
            totalEnemy = 0;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //This piece of code was borrowed from "polle", a Greenfoot user.
    //it is the scrolling background effect
    /**
     * Paint scrolling image at given position and make sure the rest of
     * the background is also painted with the same image.
     */
    private void paint(int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(scrollingImage, position, 0);
        bg.drawImage(scrollingImage, position - scrollingImage.getWidth(), 0);
    }

    /**
     * Returns an image with the given dimensions.
     */
    private GreenfootImage getScrollingImage(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for(int x = 0; x < width; x += bgImage.getWidth()) {
            for(int y = 0; y < height; y += bgImage.getHeight()) {
                image.drawImage(bgImage, x, y);
            }
        }
        return image;
    }
    //end here
    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * method to increase the recharge speed of the shield
     */
    public void increaseRechargeShield()
    {
        //call the method to increase shield recharge speed
        player.rechargeUp();
    }

    /**
     *mouse control for shooting attacks 
     */
    public void shootInput()
    {
        //check if the ultimate reaches full cooldown
        if (ultCooldown == ultCooldownTime)    {
            //boolean to allow player to shoot once cooldown is finished
            ultCooldownTrue = true;
        }
        //set ultimate on cooldown
        ultCooldown++;
        MouseInfo a = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseDragged(null) && a.getButton() == 1)   {
            addObject(new Blur(), a.getX(), a.getY());
        }
        MouseInfo m = Greenfoot.getMouseInfo();
        // Mouse has been let go after dragging
        /**
         * Mr Cohen example from code. It takes 2 points and shoots 
         * in that direction.
         */
        if (Greenfoot.mouseDragEnded(null) && m.getButton() == 1)
        {
            endX = m.getX();
            endY = m.getY();
            // Add an object at the startX,Y location and feed it the
            // endX,Y locations for its constructor
            addObject (new Fireball(endX, endY), startX, startY);
            //play the sound
            Greenfoot.playSound("Gunshot.wav");
        }
        else if (Greenfoot.mouseDragEnded(player))    {

        }
        // Signifies the mouse being "pressed" (but not released - that would be a "click")
        if (Greenfoot.mousePressed(null) && m.getButton() == 1)
        {
            startX = m.getX();
            startY = m.getY();
        }
        else if (Greenfoot.mouseDragEnded(player))    {

        }
        /**
         * Borrowed code ends here
         */
        //drag the blue square
        if (Greenfoot.mouseDragged(player))   {
            //create instance click of mouseinfo
            MouseInfo click = Greenfoot.getMouseInfo();
            //if the user left clicks 
            if (click.getButton() == 1) {
                //set the location of the player to wherever the user wishes to drag it
                player.setLocation(click.getX(), click.getY());
            }
        }
        else if (Greenfoot.mouseDragEnded(player))    {
            //do nothing if player is dragged
            //in otherwards don't shoot if the player is dragged
        }
        //Check it mouse is clicked
        else if (Greenfoot.mouseClicked(null))   {
            //create variable click from MouseInfo
            MouseInfo click = Greenfoot.getMouseInfo();
            //use boolean to set if ultimate has reached full cooldown
            if (ultCooldownTrue)  {
                //if the player uses the centre scroll button then shoot
                if (click.getButton() == 2) {
                    //create an array of objects (Strongfire)
                    for (int i = 0; i < 7; i++) {
                        s[i] = new StrongFire();
                        //add the objects with a bit of spacing in between them
                        addObject(s[i], i*150, 750);
                        //add the sound effects
                        Greenfoot.playSound("Thunder.wav");
                    }
                    //set boolean to false so it won't set off until it reaches full cooldown again
                    ultCooldownTrue= false;
                    //set ultcooldown back to zero to reset
                    ultCooldown = 0;
                }
            }
            //if the player uses the right click mouse button then shoot green attacks
            if (click.getButton() == 3 && gTimer == 0) {
                greenball = new Greenball();
                //add object at mouse pointing
                addObject(greenball, click.getX(), click.getY());
                //play sound
                Greenfoot.playSound("GREENBALL.mp3");

            }
        } 
        //press s for cheats 
        if (Greenfoot.isKeyDown("s"))  {
            fireAttack = 100;
            greenAttack = 800;
            strongAttack = 1000;
        }
    }

    /**
     * start level one
     */
    public void startLevel1()
    {
        //spawn random Death's
        if (Greenfoot.getRandomNumber(50) == 1 && deathSpawn <= 4)  {
            death = new Death();
            //add the death's
            addObject(death, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(120)+70);
            //increase counter of number of Death's on screen
            deathSpawn++;
        }
    }

    /**
     * start level 2
     */
    public void startLevel2()
    {
        //spawn random Eye's
        if (Greenfoot.getRandomNumber(100) == 1 && eyeSpawn <= 4  )    {
            eye = new Eye();
            //add the eye
            addObject(eye, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(100) + 100);
            //increase number on eyeSpawn
            eyeSpawn++;
        }
    }

    /**
     * start level three
     */
    public void startLevel3()
    {
        //spawn random Deathmask's
        if (Greenfoot.getRandomNumber(100) == 1 && deathmaskSpawn <= 4 ) {
            deathmask = new Deathmask(player);
            //add the deathmask
            addObject(deathmask, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(120) + 100);
            //increase number on deathmaskSpawn
            deathmaskSpawn++;
        }
    }

    /**
     * start level 4
     */
    public void startLevel4()
    {
        //spawn random Medusa's
        if (Greenfoot.getRandomNumber(100) == 1 && medusaSpawn <= 4)    {
            medusa = new Medusa(player);
            //add the medusa
            addObject(medusa, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(120) + 100);
            // increase medusa spawns
            medusaSpawn++;
        }
    }

    /**
     * start level 5
     */
    public void startLevel5()
    {
        //spawn random Death's
        if (Greenfoot.getRandomNumber(50) == 1 && deathSpawn <= 1)  {            
            death = new Death();
            //add the death's
            addObject(death, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(120)+70);
            //increase counter of number of Death's on screen
            deathSpawn++;
        }
        //spawn random Eye's
        if (Greenfoot.getRandomNumber(100) == 1 && eyeSpawn <= 1  )    {           
            eye = new Eye();
            //add the eye
            addObject(eye, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(100) + 100);
            //increase number on eyeSpawn
            eyeSpawn++;
        }
        //spawn random Deathmask's
        if (Greenfoot.getRandomNumber(100) == 1 && deathmaskSpawn <= 1 ) {            
            deathmask = new Deathmask(player);
            //add the deathmask
            addObject(deathmask, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(120) + 100);
            //increase number on deathmaskSpawn
            deathmaskSpawn++;
        }
        //spawn random Medusa's
        if (Greenfoot.getRandomNumber(100) == 1 && medusaSpawn <= 1)    {            
            medusa = new Medusa(player);
            //add the medusa
            addObject(medusa, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(120) + 100);
            // increase medusa spawns
            medusaSpawn++;
        }
    }

    /**
     * start level 6
     */
    public void startLevel6()
    {
        //spawn random Death's
        if (Greenfoot.getRandomNumber(50) == 1 && deathSpawn <= 6)  {            
            death = new Death();
            //add the death's
            addObject(death, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(120)+70);
            //increase counter of number of Death's on screen
            deathSpawn++;
        }
    }

    /**
     * start level 7
     */
    public void startLevel7()
    {
        //spawn random Eye's
        if (Greenfoot.getRandomNumber(100) == 1 && eyeSpawn <= 7)    {
            eye = new Eye();
            //add the eye
            addObject(eye, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(100) + 100);
            //increase number on eyeSpawn
            eyeSpawn++;
        }
    }

    /**
     * start level 8
     */
    public void startLevel8()
    {
        //spawn random Deathmask's
        if (Greenfoot.getRandomNumber(100) == 1 && deathmaskSpawn <= 8) {
            deathmask = new Deathmask(player);
            //add the deathmask
            addObject(deathmask, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(180) + 200);
            //increase number on deathmaskSpawn
            deathmaskSpawn++;
        }
    }

    /**
     * start level 9
     */
    public void startLevel9()
    {
        //spawn random Death's
        if (Greenfoot.getRandomNumber(50) == 1 && deathSpawn <= 2)  {            
            death = new Death();
            //add the death's
            addObject(death, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(120)+70);
            //increase counter of number of Death's on screen
            deathSpawn++;
        }
        //spawn random Eye's
        if (Greenfoot.getRandomNumber(100) == 1 && eyeSpawn <= 2)    {
            eye = new Eye();
            //add the eye
            addObject(eye, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(100) + 100);
            //increase number on eyeSpawn
            eyeSpawn++;
        }
        //spawn random Deathmask's
        if (Greenfoot.getRandomNumber(100) == 1 && deathmaskSpawn <= 3) {
            deathmask = new Deathmask(player);
            //add the deathmask
            addObject(deathmask, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(180) + 200);
            //increase number on deathmaskSpawn
            deathmaskSpawn++;
        }
        //spawn random Medusa's
        if (Greenfoot.getRandomNumber(100) == 1 && medusaSpawn <= 2)    {            
            medusa = new Medusa(player);
            //add the medusa
            addObject(medusa, Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(120) + 100);
            // increase medusa spawns
            medusaSpawn++;
        }
    }

    /**
     * method to end level and change the counter
     */
    public void endLevel()
    {
        //increase level after previous ended
        levelCounter++;
        //set the display in the Level class
        level.setValue(levelCounter);
        //change the spawn numbers to 1 so they can be reused in future levels
        deathSpawn = 1;
        eyeSpawn = 1;
        deathmaskSpawn = 1;
        medusaSpawn = 1;
    }

    /**
     * return the value for the fireball attack
     */
    public int storeFireAttack()
    {
        //return this value so it can be used in other classes
        return fireAttack;
    }

    /**
     * return the value for the greenball attack
     */
    public int storeGreenAttack()
    {
        //return this value so it can be used in other classes
        return greenAttack;   
    }

    /**
     * return the value for the strongfire attack
     */
    public int storeStrongAttack()
    {
        //return this value so it can be used in other classes
        return strongAttack;
    }

    /**
     * return the value for explodeAttack
     */
    public int storeExplodeAttack()
    {
        //return this value so it can be used in other classes
        return explodeAttack;
    }

    /**
     * add points for killing enemy
     */
    public void deathPoints()
    {
        //add 10 points for killing death
        points = 10;
        //display
        score.add(points);
    }

    /**
     * add points for killing enemy
     */
    public void eyePoints()
    {
        //add 20 points for killing eye
        points = 20;
        //display
        score.add(points);
    }

    /**
     * add points for killing enemy
     */

    public void deathmaskPoints()
    {
        //add 30 points for killing deathmask
        points = 30; 
        //display
        score.add(points);
    }

    /**
     * add points for killing enemy
     */
    public void medusaPoints()
    {
        //add 50 points for killing medusa
        points = 50;
        //display
        score.add(points);
    }

    /**
     * add all the shop objects and set it up 
     */
    public void levelShop()
    {
        //add a new shop
        shop = new Shop();
        //add the shop screen in game
        addObject(shop, 450, 400);
        //remove player so remaining debris does not hit payer while in shop
        removeObject(player);
        //add all objects necessary to run the shop
        buyFire = new BuyFire();
        addObject(buyFire, 325, 412);//buy button
        buyGreen = new BuyGreen();
        addObject(buyGreen, 592, 411);//buy button
        buyRecharge = new BuyRecharge();
        addObject(buyRecharge, 315, 702);//buy button
        buyUlt = new BuyUlt();
        addObject(buyUlt, 574, 700);//buy button
        cross = new Cross();
        addObject(cross, 695, 82);//exit shop button
    }

    /**
     * buy from the shop
     */
    public void buyShop()
    {   
        //use boolean to set when the updated coins will start getting the value from the Coins class
        if (updatedCoins)   {
            //set valid coin with a value
            validCoin = coins.returnCoin();
        }
        //if the player clicks buyFire button, then increase attack damage of fireball
        if (Greenfoot.mouseClicked(buyFire)){
            //check if player has enough coins to buy it
            if (validCoin >= 300)   {
                //increase attack damage by 10
                fireAttack += 10; 
                //subtract 300 from coins
                coinCounter = -300;
                //display new coins count
                coins.add(coinCounter);
                //play cha ching!!
                Greenfoot.playSound("Cash Register Cha Ching-SoundBible.com-184076484.wav");
            }
        }
        //if the player clicks buyGreen then increase the damage of it
        if (Greenfoot.mouseClicked(buyGreen)){
            //check if player has enough coins to buy it
            if (validCoin >= 600)   {
                //increase attack damage of greenball by 20
                greenAttack += 20;
                //subtract 600 from coins
                coinCounter = -600;
                //display new coins count
                coins.add(coinCounter);
                //play cha ching!!
                Greenfoot.playSound("Cash Register Cha Ching-SoundBible.com-184076484.wav");
            }
        }
        //if player clicks buy recharge, then increase recharge rate of the shield
        if (Greenfoot.mouseClicked(buyRecharge)){
            //if it is not sold out and the player has enough money then buy
            if (validCoin >= 150 && clickCharge < 5)   {
                //increase stock by 1
                clickCharge++;
                //check if player bought all of this already
                if(clickCharge >= 4)    {
                    //change image to sold out if player did
                    buyRecharge.changeImage();
                }
                //call method from player class to increase recharge rate
                player.rechargeUp();
                //subtract 150 coins
                coinCounter = -150;
                //display new coins count
                coins.add(coinCounter);
                //play cha ching!!
                Greenfoot.playSound("Cash Register Cha Ching-SoundBible.com-184076484.wav");
            }
        }
        //if player clicks on ult upgrade then upgrade it
        if (Greenfoot.mouseClicked(buyUlt)){
            //check that the player has 2000 coins
            if (validCoin >= 1000)   {
                //increase ultimate damage by 30%
                strongAttack += strongAttack * 0.3; 
                //subtract 1000 coins
                coinCounter = -1000;
                //display new coins count
                coins.add(coinCounter);
                //play cha ching!!
                Greenfoot.playSound("Cash Register Cha Ching-SoundBible.com-184076484.wav");
            }
        }
        //if the player exits the shop screen then resume game
        if (Greenfoot.mouseClicked(cross)){
            //remove all objects associated with the shop
            removeObject(shop);
            removeObject(buyFire);
            removeObject(buyGreen);
            removeObject(buyRecharge);
            removeObject(buyUlt);
            removeObject(cross);
            //don't update coin count (buying option) until new shop screen 
            updatedCoins = false;
            //boolean to make sure that endLevel can only be called once per level. Otherwise it will loop in the act().
            if (controlEndLevel)    {
                //start new level
                endLevel();
                //add the player back into game after shop menu closed
                addObject(player, 409, 779);
            }
            //turn off control level
            controlEndLevel = false;
        }
    }

    /**
     * final score method when player dies
     */
    public void finalScore()
    {
        //display score
        System.out.println("Score: " + score.returnScore());
        //stop the game
        Greenfoot.stop();
    }
}
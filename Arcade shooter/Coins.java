import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * THIS IS THE CLASS THAT IS ABLE TO COUNT UP COINS AND DISPLAY IT
 * @author (Mr Cohen's code example)**** 
 * @version (2.2)
 */
public class Coins extends Actor
{
    //declare variables
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    private int value;
    private int target;
    private String word;
    /**
     * constructor
     */
    public Coins()
    {
        this(new String());
    }

    /**
     * Create a new counter, initialised to 0.
     */
    public Coins(String word)
    {
        background = getImage();  // get image from class
        value = 0;
        target = 0;
        this.word = word;
        updateImage();

    }

    /**
     * Animate the display to count up (or down) to the current target value.
     */
    public void act() 
    {
        if (value < target) {
            value++;
            updateImage();
        }
        else if (value > target) {
            value--;
            updateImage();
        }
    }

    /**
     * Add a new score to the current counter value.
     */
    public void add(int score)
    {
        target += score;
    }

    /**
     * Return the current counter value.
     */
    public int getValue()
    {
        return value;
    }

    public void setWord(String word)
    {
        this.word = word;
        updateImage();
    }

    /**
     * Set a new counter value.
     */
    public void setValue(int newValue)
    {
        target = newValue;
        value = newValue;
        updateImage();
    }

    /**
     * Update the image on screen to show the current value.
     */
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(background);
        GreenfootImage text = new GreenfootImage(word + value, 18, Color.CYAN, transparent);
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }   

    /**
     * return coin value to main world for shop
     */
    public int returnCoin()
    {
        return target;   
    }
}
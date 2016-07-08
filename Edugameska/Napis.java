import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class Napis here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Napis extends Actor
{
    /**
     * Act - do whatever the Napis wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    String text;
    
    public Napis(String jaky) {
        
        
        nastavText(jaky);
    }
    public void nastavText(String jaky) {
        text = jaky;
        GreenfootImage o = new GreenfootImage(text,30, Color.BLACK, Color.YELLOW);
        GreenfootImage o1 = new GreenfootImage(200,100);
        o1.setColor(Color.YELLOW);o1.fill();
        o1.drawImage(o, 10, 50);
        setImage(o1);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}

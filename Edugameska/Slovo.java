import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.util.*;

/**
 * Write a description of class Slovo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slovo extends Actor
{
    GreenfootImage obr,obr2;
    static Pozadi pozadi;
    String text;
    static boolean hrdinaIn;
    Color aktBarva;
    
    public Slovo () {
        Font font = new Font("Dialog", Font.PLAIN, 35);

        text="ahoj";
        hrdinaIn=false;
        obr = new GreenfootImage("ahoj", 40, Color.BLACK, Color.WHITE, Color.RED);
        int w=obr.getWidth();
        int h=obr.getHeight();
        
        obr2 = new GreenfootImage(w+40,h);
        obr2.setColor(Color.WHITE);obr2.fill();
        obr2.drawImage(obr, 20,0);
        
        obr2.drawRect(0, 0, w+40-1, h-1);
        setImage(obr2);
        aktBarva=Color.WHITE;
        
    }
    
    
    public Slovo (String jake) {
        text=jake;
        obr = new GreenfootImage(jake, 40, Color.BLACK, Color.WHITE, Color.RED);
        int w=obr.getWidth();
        int h=obr.getHeight();
        
        obr2 = new GreenfootImage(w+40,h);
        obr2.setColor(Color.WHITE);obr2.fill();
        obr2.drawImage(obr, 20,0);
        
        obr2.drawRect(0, 0, w+40-1, h-1);
        setImage(obr2);
        
        
    }
    
        public void nastavObrazek (String jake, Color c) {
        aktBarva=c;
        obr = new GreenfootImage(jake, 40, Color.BLACK, c, Color.RED);
        int w=obr.getWidth();
        int h=obr.getHeight();
        
        obr2 = new GreenfootImage(w+40,h);
        obr2.setColor(c);obr2.fill();
        obr2.drawImage(obr, 20,0);
        
        obr2.drawRect(0, 0, w+40-1, h-1);
        setImage(obr2);
        
        
    }
    
    
    protected void addedToWorld(World svet) {
        Random rand = new Random();
        while (this.isTouching(null)) {
            this.setLocation(rand.nextInt(800),rand.nextInt(600));
        }
            
        //nastavObrazek(text,Color.RED);
    }
    
    
    
    
    /**
     * Act - do whatever the Slovo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (aktBarva!=Color.GREEN) {
            if (this.isTouching(Hrdina.class)) {
                hrdinaIn=true;
                if (pozadi.kontrola(text)) {
                
                    
                    nastavObrazek(text,Color.GREEN);
                } else {
                    nastavObrazek(text,Color.RED);
                }
            } else {
                hrdinaIn=false;
            }
        }
        
        // Add your action code here.
    }    
}

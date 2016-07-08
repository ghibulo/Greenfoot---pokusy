import greenfoot.*;
import java.util.Random;
/**
 * Write a description of class Plocha here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plocha extends World
{

    float kpruznosti = 1;
    float pomerPohybRychlost = 0.2f;
    int pocetMicku = 7;
    
    /**
     * Constructor for objects of class Plocha.
     * 
     */
    public Plocha()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800,600, 1); 
        Random rand = new Random();
        
        Teleso.nasSvet = this;
        for (int x=50;x<750;x+=70) {
            addObject(new Teleso(100, true), x, 10);
            addObject(new Teleso(100, true), x, 590);
        }
        
        for (int y=50;y<600;y+=70) {
            addObject(new Teleso(100, true), 10, y);
            addObject(new Teleso(100, true), 790, y);
        }
        
        for (int i=0;i<pocetMicku;i++) {// m, v, s
            addObject(new Teleso(rand.nextInt(30)+35,rand.nextInt(90)+40,rand.nextInt(360), false),rand.nextInt(600)+100,rand.nextInt(400)+100);
        }
        //addObject(new Teleso(30,30,150, false),200,300);
        //addObject(new Teleso(20,10,150, false),100,300);
        //addObject(new Teleso(10 ,0,100, false),400,200);
    }
}

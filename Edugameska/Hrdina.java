import greenfoot.*;
import java.awt.Point;

/**
 * Write a description of class Hrdina here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hrdina extends Actor
{

    public Hrdina() {
        

       
    }
    
    public void act() 
    {

        
        //String klavesa; 
        /*if ((klavesa=Greenfoot.getKey())!=null) {
            smer = klavesa;
        }*/
        if (Greenfoot.isKeyDown("left")) {posun(180);}
        if (Greenfoot.isKeyDown("right")) {posun(0);}
        if (Greenfoot.isKeyDown("up")) {posun(-90);}
        if (Greenfoot.isKeyDown("down")) {posun(90);}
        
    }    
    
    private Point getVektor(int nasobek, int uhel) {
        double uh = uhel*Math.PI/180;
        return new Point((int)(nasobek*Math.cos(uh)),(int)(nasobek*Math.sin(uh)));
        
    }

    
    private void posun(int kam) {
        Point v = getVektor(28,kam);
        Actor z = getOneObjectAtOffset(v.x,v.y, Zed.class);
        if (z==null) {
            v = getVektor(10,kam);
            setLocation(getX()+v.x,getY()+v.y);
        }

         
    }
    
}

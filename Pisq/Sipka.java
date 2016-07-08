import greenfoot.*;
import java.util.*;

/**
 * Write a description of class Sipka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sipka extends Actor
{

    char smer;
    int sx, sy;
    static private NaKameny nasSvet;
    private boolean isGrabbed;
    List<Kamen> vybraneKameny;
    
        public Sipka(char sm)
    {
        smer = sm;sx=0;sy=0;
        switch (sm) {
            case 'v': setImage(new GreenfootImage("sipkaRight.png"));break;
            case 'z': setImage(new GreenfootImage("sipkaLeft.png"));break;
            case 's': setImage(new GreenfootImage("sipkaUp.png"));break;
            case 'j': setImage(new GreenfootImage("sipkaDown.png"));break;
        }
            
        vybraneKameny = new ArrayList<Kamen>();
        isGrabbed=false;
    }
   
    
    public void addedToWorld(World world) {
        if ((sx==0)&&(sy==0)) {sx = getX(); sy = getY();}
    }
    
    public static void nastavVlastnika(NaKameny jakeho) {    
        nasSvet = jakeho;
    }

    private void vyberKameny() {
        List<Kamen> k;
        for (int i=50;i<450;i=i+1) {
            k = getObjectsAtOffset(-i,0, Kamen.class);
            for (Kamen j:k) {
                if (j!=null) {
                  j.nastavVyber();
                  vybraneKameny.add(j);
                }
            }
        }
    }
    
    private void odvyberKameny() {
        for (Kamen k: vybraneKameny) {
            k.nastavNevyber();
        }
        vybraneKameny.clear();
        
    }
    
    /**
     * Act - do whatever the Sipka wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
            
             
            /* in the 'act' method in the class of the Actor object to be dragged */
            // check for initial pressing down of mouse button
            if (Greenfoot.mousePressed(this) && !isGrabbed)
            {
                // grab the object
                isGrabbed = true;
 
                MouseInfo mi = Greenfoot.getMouseInfo();
                //nasSvet.removeObject(this);

                //nasSvet.addObject(this, mi.getX(), mi.getY());

                return;
            }
            // check for actual dragging of the object
            if ((Greenfoot.mouseDragged(this)) && isGrabbed)
            {
                // follow the mouse
                MouseInfo mi = Greenfoot.getMouseInfo();
                setLocation(sx, mi.getY());
                //odvyberKameny();
                //vyberKameny();
                nasSvet.odvyberKamenu();
                nasSvet.vyberRaduKamenu(nasSvet.invsy(mi.getY()));
                //System.out.println(mi.getY());
                return;
            }
            // check for mouse button release
            if (Greenfoot.mouseDragEnded(this) && isGrabbed)
            {
                // release the object
                isGrabbed = false;
                nasSvet.posledniVyberVpravo();
                return;
            }
        
        

    }    
}












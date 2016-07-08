import greenfoot.*;
import java.util.Random;
import java.awt.Point;

/**
 * Write a description of class Potvora here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Potvora extends Actor
{

    int smer, pocetKroku;
    Random rand;
    static Pozadi pozadi;
    
    public Potvora() {
        
        rand = new Random();
        nastavPlan();
    }
    
    private void nastavPlan() {
        
        smer = rand.nextInt(360);
        pocetKroku= rand.nextInt(150)+1;
    }
    
    private void jdi() {
        Point offset;
        Actor z;
        
            if (pocetKroku==0) nastavPlan();
            do {
                offset=getVektor(40);
                if ((z = getOneObjectAtOffset(offset.x,offset.y,Zed.class))!=null) {
                    smer++;
                }
            }while (z!=null);
                
            
            
            //if (isTouching(Zed.class)) {smer-=180;turn(180);}

        move(1);pocetKroku--;
        if (isTouching(Hrdina.class)) {
            pozadi.konecHry();
        }
            
    }
    
    private Point getVektor(int velikost) {
        double uh = smer*Math.PI/180;
        double velVekt = velikost;
        return new Point((int)(velVekt*Math.cos(uh)),(int)(velVekt*Math.sin(uh)));
        
    }
    
    public void act() 
    {
        
        setRotation(smer);
        jdi();
        //System.out.println(isTouching(Zed.class));

    }    
}

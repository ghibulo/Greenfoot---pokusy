import greenfoot.*;
import java.util.*;

/**
 * Write a description of class Teleso here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teleso extends Actor
{

    float rychlost;
    float vzasobnik; // budu si schovavat desetinky rychlosti z minuleho kroku
    float hmotnost;
    float smer;
    static Plocha nasSvet;
    boolean nehybny;
    Set<Teleso> nedavnoKontakt;
    
    public Teleso(int m, int v, int s, boolean nehybn) {
        rychlost=v;
        hmotnost=m;
        smer=s;
        nehybny=nehybn;
        nedavnoKontakt = new HashSet();
        vzasobnik=0f;
        if (!nehybny) {
          GreenfootImage o = this.getImage();
          o.scale(m,m);
        }
    }
    
    public Teleso(int m, boolean nehybn) {
        this(m,0,0, nehybn);
    }
    
    private void pohyb() {
        //System.out.println(rychlost);
        setRotation((int)smer);
        float presnyKrok = rychlost*nasSvet.pomerPohybRychlost + vzasobnik;
        int skutecnyKrok = (int) presnyKrok;
        if (skutecnyKrok>=1) {
          move(skutecnyKrok);
          vzasobnik = presnyKrok-skutecnyKrok;
          
        } else {
          vzasobnik = presnyKrok;
          nedavnoKontakt = new HashSet();
        }
            
        //if (vzasobnik<0) System.out.println("chyba"+vzasobnik+"m="+hmotnost);
        
    }
    
    public float dejRychlost() {
      return rychlost;
    }
    
    public  boolean jeNehybny() {
        
        return nehybny;
    }
    
    private float upravPohyb(float[] h) {
        float v = (hmotnost*rychlost+h[1]*h[0])/(hmotnost + h[1]);
        rychlost = Math.abs((v-(h[1]*nasSvet.kpruznosti*(rychlost-h[0]))/(hmotnost + h[1])));
        smer = (-180+2*getRotation()-smer)%360;
        smer = (smer<0)?smer+360:smer;
        return v; //spolecny vypocet vracen pro druhe teleso
    }
    
    public void upravSiPohyb(float v, float m, float d, Teleso od, float spolv) {
        rychlost = Math.abs((spolv + m*nasSvet.kpruznosti*(v-rychlost))/(m+hmotnost));
        turnTowards(od.getX(), od.getY());
        smer = (-180+2*getRotation()-smer)%360;
        smer = (smer<0)?smer+360:smer;
        setRotation((int)smer);
        nedavnoKontakt.add(this);
        
    }
    
    
    private void vyresOdraz() {
        List<Teleso> telesa =  getIntersectingObjects(Teleso.class);
        Set<Teleso> prunik = new HashSet(nedavnoKontakt); //copy construktor
        prunik.retainAll(telesa); //o ty uz nestojim
        telesa.removeAll(prunik); // tak je nebudeme brat v uvahu
        nedavnoKontakt = prunik;  // a priste taky ne
        if (telesa.isEmpty())     //zustal nekdo?
            
            return;
        else {
            Teleso t=null,x;
            int dist, min=1000, i=0;
            do {     // z tech co zbude, ten ktery je bliz
               x = telesa.get(i++);
            //if (telesa.size()>1) {System.out.println(telesa.size());}
               dist = Math.abs(this.getX()-x.getX()) + Math.abs(this.getY()-x.getY());
               if (min>dist) {
                   min = dist;
                   t = x;
                }
               
            } while (i<telesa.size());
            
                
          
            turnTowards(t.getX(), t.getY());
            if (t.jeNehybny()) {
                upravPohyb(t.getVMD());
            } else {
                t.upravSiPohyb(rychlost,hmotnost,smer,this, upravPohyb(t.getVMD()));
            }
            nedavnoKontakt.add(t);
        }
        
    }
    
    public void act() 
    {   if (!nehybny) {
            if (rychlost>nasSvet.pomerPohybRychlost) {
                pohyb();
                if (isTouching(Teleso.class)) {
                    vyresOdraz();
                }
            }
        }
    }    
    
    
    public float[] getVMD() {
        float[] ret = new float[3];
        ret[0]=rychlost;ret[1]=hmotnost;ret[2]=smer;
        return ret;
    }
}

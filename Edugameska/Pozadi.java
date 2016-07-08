import greenfoot.*;
import java.util.*;
import java.awt.*;
/**
 * Write a description of class Pozadi here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pozadi extends World
{

    /**
     * Constructor for objects of class Pozadi.
     * 
     */
    
    
    Map<String, String> dvojice;
    Napis oznameni;
    Iterator<String> it;
    String otazka, odpoved;
    boolean dalsiOtazka;
    public Pozadi()
    {    
        super(800, 600, 1); 
        dalsiOtazka=true;
        Random rand = new Random();
        oznameni = new Napis("Ahoj");
        addObject(oznameni,120,50);
        dvojice = new HashMap<String, String>();
        
        dvojice.put("Stůl","Table");
        dvojice.put("Ahoj","Hallo");
        dvojice.put("Voda","Water");
        dvojice.put("Dům","House");
        
        addObject(new Hrdina(),100,200);
        for (int i=0;i<800;i+=40) {
            addObject(new Zed(),i,0);
            addObject(new Zed(),i,600);
        }
        for (int i=0;i<800;i+=40) {
            addObject(new Zed(),0,i);
            addObject(new Zed(),800,i);
        }
            
        addObject(new Potvora(),400,500);
        addObject(new Potvora(),400,500);
        
        Potvora.pozadi=Slovo.pozadi = this;
        
        /*for (String key : dvojice.keySet())
           addObject(new Slovo(key),rand.nextInt(800),rand.nextInt(600));
        */
     
        for (String h: dvojice.values()) {
            addObject(new Slovo(h),rand.nextInt(800),rand.nextInt(600));
        }
        
           it = dvojice.keySet().iterator();
           /*otazka = it.next();
           odpoved = dvojice.get(it.next());
           oznameni.nastavText(otazka);
             */        
    }
    
    public boolean kontrola(String ceho) {
        if (ceho.equals(dvojice.get(otazka))) {
            dalsiOtazka=true;
            if (!it.hasNext()) {

                konecHry();
            }
            return true;
        } 
        return false;
        
    }
    
    public void konecHry() {
                oznameni.nastavText("Konec !");
                Greenfoot.stop();
        
    }
    public void act() {
        if ((dalsiOtazka) && (!Slovo.hrdinaIn)) {
            dalsiOtazka=false;
            if (it.hasNext()) {
                otazka = it.next();
                oznameni.nastavText(otazka);
            } 
        }

        
    }
    
    
}

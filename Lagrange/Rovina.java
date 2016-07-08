import greenfoot.*;
import java.util.*;
import java.awt.Color;


public class Rovina extends World
{


    Lagrange mojeCara;
    GreenfootImage obrazCary;
    List<Bod> body;
    graf g;
    boolean konec;
    
    public Rovina()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(80, 60, 10, true); 

        g=new graf();
        
        Bod.r=this;
        konec = false;
        //addObject(new Bod(), 40,30);
        //setBackground( dejPozadi());
        
    }
    
    private GreenfootImage dejPozadi() {
        GreenfootImage p = new GreenfootImage(800,600);
        for (int x=0;x<=800;x++) {
            p.drawLine(x*10,10*(int)mojeCara.dejFcniHodnotu(x),10*(x+1), 10*(int)mojeCara.dejFcniHodnotu(x+1));
        }
        return p;
    }
    
    private GreenfootImage dejPozadi2() {
        GreenfootImage p = new GreenfootImage(80,60);
        for (int x=0;x<=800;x++) {
            p.drawLine(x,(int)mojeCara.dejFcniHodnotu(x),(x+1), (int)mojeCara.dejFcniHodnotu(x+1));
        }
        return p;
    }
    
    public void act() {
 
        //System.out.println(this.getObjects(Bod.class).size());
        
        if (!konec) {
        
                MouseInfo m = Greenfoot.getMouseInfo();
                
        
                if ((m!=null) && (Greenfoot.mouseClicked(null))) {
                    if(m.getButton()==1) {
                        addObject(new Bod(m.getX(),m.getY()), m.getX(),m.getY());
                        //obrazCary.clear();
                        //setBackground(dejPozadi());
                        //obrazCary.drawLine(m.getX(),m.getY(),m.getX()+50,m.getY()+50);
                  
                        //setBackground(dejPozadi());
                    }
                    if(m.getButton()==2) {

                        addObject(g,40,30);
                        predelejCaru();
                        konec=true;
                        //System.out.println("Hotovo");
                    }
                }
        
        }
    }
    
    public void predelejCaru() {
        pocitej();
        GreenfootImage o = dejPozadi();
        g.setImage(o);
        
        removeObjects(body);
        for (Bod b:body) {
            addObject(b,b.myx,b.myy);
        }
 
        
        
    }
    
    
    
    public void pocitej() {
       body = getObjects(Bod.class);
       mojeCara = new Lagrange();
       for (Bod bod :body ) {
           mojeCara.pridejBod(bod.getX(), bod.getY());
        }
        
        
    }
    /*
    public float clenProBod(Bod jaky, int xsouradnice) {
        
        
    }*/
    
    
    
}

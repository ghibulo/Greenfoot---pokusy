import greenfoot.*;

/**
 * Write a description of class Kamen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kamen extends Actor
{
    static private GreenfootImage imNic=new GreenfootImage("yellow.png");
    static private GreenfootImage imKr=new GreenfootImage("red.png");
    static private GreenfootImage imKo=new GreenfootImage("blue.png");
    static private GreenfootImage imNicSel=new GreenfootImage("yellowselected.png");
    static private GreenfootImage imKrSel=new GreenfootImage("redselected.png");
    static private GreenfootImage imKoSel=new GreenfootImage("blueselected.png");

    private GreenfootImage imAktualni;
    private boolean zamereno;
    static public Symbol ocekavanySym;
    public Symbol mujSym;
    static private NaKameny nasSvet;
    int pozX,pozY,pozZ;
    String plan;
    static public int splneno; //pocitadlo kolik kamenu splnilo plan
    public Kamen(int x, int y, int z) {
        //nasSvet = (NaKameny)getWorld();
        pozX=x;pozY=y;pozZ=z;
        aktualizujObrazek(nasSvet.poleSymbolu[x][y][z]);ocekavanySym=Symbol.krizek;
        zamereno = false;
        plan="";
    }
    
    public static void nastavVlastnika(NaKameny jakeho) {    
        nasSvet = jakeho;
    }
    
    public void posunVlevo() {
        if (pozX>0) {
           // setLocation(this.getX()-nasSvet.DELTAX, getY());
            pozX--;
        } else {
          //  setLocation(getX()+(nasSvet.MRIZKA-1)*nasSvet.DELTAX,getY());
            pozX=nasSvet.MRIZKA-1;
        }
        
        zapisZmenuDoMrizky(mujSym);nasSvet.poleKamenu[pozX][pozY][pozZ] = this;splneno--;
        nastavNevyber();nasSvet.prerovnejSloupek(pozX,pozY);zmenaOcekavanySymbol();
    }
    
    public void otockaPoSmeru() {
        int npozX = nasSvet.MRIZKA-1-pozY;
        int npozY = pozX;
        pozX = npozX;pozY=npozY;
        zapisZmenuDoMrizky(mujSym);nasSvet.poleKamenu[pozX][pozY][pozZ] = this;
        nastavNevyber();
    }
    
    public void otockaProtiSmeru() {
        int npozX = pozY;   
        int npozY = nasSvet.MRIZKA-1-pozX;
        pozX = npozX;pozY=npozY;
        zapisZmenuDoMrizky(mujSym);nasSvet.poleKamenu[pozX][pozY][pozZ] = this;
        nastavNevyber();
    }
    
    public void aktualizujObrazek(Symbol jaky) {
        
        switch (jaky) {       
            case krizek: setImage(imKr);zapisZmenuDoMrizky(Symbol.krizek);break;
            case kolecko:setImage(imKo);zapisZmenuDoMrizky(Symbol.kolecko);break;
            case nic:    setImage(imNic);zapisZmenuDoMrizky(Symbol.nic);break;
            case nicSel: setImage(imNicSel);break;
            case krizekSel: setImage(imKrSel);break;
            case koleckoSel: setImage(imKoSel);break;
        }    
        mujSym=jaky.neSel();
    }
    
    public void preklopVyber() {
        if (zamereno) {
          nastavNevyber();
        } else {
          nastavVyber();           
        }      
    }
    
    public void nastavVyber() {
        if (!zamereno) {
            switch (aktSymbol()) {
                case nic:        setImage(imNicSel);break;
                case krizek:     setImage(imKrSel);break;
                case kolecko:    setImage(imKoSel);break;
            }
            zamereno=true;mujSym=aktSymbol();
        }               
    }
    
    public void nastavNevyber() {
        if (zamereno) {
            switch (aktSymbol()) {
                case nic:        setImage(imNic);break;
                case krizek:     setImage(imKr);break;
                case kolecko:    setImage(imKo);break;
            }
            zamereno=false;
        }               
    }
    
    private void aktualizujObrazek() { //po imZam vratit zpet
        aktualizujObrazek(nasSvet.poleSymbolu[pozX][pozY][pozZ]);
    }
    
    private void zapisZmenuDoMrizky(Symbol jakou) {
        nasSvet.poleSymbolu[pozX][pozY][pozZ] = jakou;
    }
    
    private Symbol aktSymbol() {
        return nasSvet.poleSymbolu[pozX][pozY][pozZ];
    }
    private void zmenaOcekavanySymbol() {
        ocekavanySym=(ocekavanySym==Symbol.krizek)?Symbol.kolecko:Symbol.krizek;
    }
 
    public void act() 
    {
        MouseInfo m = Greenfoot.getMouseInfo();
        if (m!=null) {
            if(Greenfoot.mouseClicked(this) && aktSymbol()==Symbol.nic) {
                aktualizujObrazek(ocekavanySym);zmenaOcekavanySymbol();
            }
        }
        kontrolaHover();
        kontrolaPlanu();
            
    }    
    
    public void kontrolaPlanu() {
        if (plan.equals("doleva")) {
            plan="";
            posunVlevo();
        }
        if (plan.equals("otockaPo")) {
            plan="";
            this.otockaPoSmeru();
            splneno++;
        }
        if (plan.equals("otockaProti")) {
            plan="";
            this.otockaProtiSmeru();
            splneno++;
        }
        if ((splneno==nasSvet.MRIZKA*nasSvet.MRIZKA*nasSvet.MRIZKA)||(splneno==(-nasSvet.MRIZKA)))
            nasSvet.prekreslitPlan();
        
    }
    public void kontrolaHover()
    {
        if (Greenfoot.mouseMoved(null)) {
            if (Greenfoot.mouseMoved(this) && !zamereno && aktSymbol()==Symbol.nic) {
                aktualizujObrazek(Symbol.nicSel); zamereno =true;
            }
            if (!Greenfoot.mouseMoved(this) && zamereno) {
                aktualizujObrazek(); zamereno = false;
            }
        }
        
    }
    
    
}




















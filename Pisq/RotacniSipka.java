import greenfoot.*;

/**
 * Write a description of class RotacniSipka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RotacniSipka extends Actor
{
    
    char smer;
    static NaKameny nasSvet;
    private GreenfootImage imRed, imBlue;
    private Symbol ocekavanySym;
    static private GreenfootImage imPoRed=new GreenfootImage("rotacePoRed.png");
    static private GreenfootImage imProtiRed=new GreenfootImage("rotaceProtiRed.png");
    static private GreenfootImage imPoBlue=new GreenfootImage("rotacePoBlue.png");
    static private GreenfootImage imProtiBlue=new GreenfootImage("rotaceProtiBlue.png");

    
    public RotacniSipka(char sm) {
        switch (sm) {
            case '+': setImage(imRed=imPoRed);imBlue=imPoBlue;break;
            case '-': setImage(imRed=imProtiRed);imBlue=imProtiBlue;break;
        }
        smer = sm;
        ocekavanySym=Symbol.krizek;
        
    }
    
    public void nastavBarvu() {
        ocekavanySym=Kamen.ocekavanySym;
        if (ocekavanySym==Symbol.krizek) {
          setImage(imRed);
        } else {
          setImage(imBlue);
        }
    }
    
    /**
     * Act - do whatever the RotacniSipka wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (ocekavanySym!=Kamen.ocekavanySym) {
            nastavBarvu();
        }
        MouseInfo m = Greenfoot.getMouseInfo();
        if (m!=null) {
            if(Greenfoot.mouseClicked(this) ) {
                nasSvet.otocitDesku(smer);
            }
        }
    }    
}

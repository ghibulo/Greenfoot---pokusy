import greenfoot.*;

/**
 * Write a description of class Bod here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bod extends Actor
{
    
    boolean isGrabbed;
    int myx,myy;
    static Rovina r ;
    
    public Bod(int x, int y) {
        
        myx=x;myy=y;
        
    }
    
    /**
     * Act - do whatever the Bod wants to do. This method is called whenever
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
                //getWorld().removeObject(this);

                //getWorld().addObject(this, mi.getX(), mi.getY());

                return;
            }
            // check for actual dragging of the object
            if ((Greenfoot.mouseDragged(this)) && isGrabbed)
            {
                // follow the mouse
                MouseInfo mi = Greenfoot.getMouseInfo();
                myx=mi.getX();myy=mi.getY();
                setLocation(myx, myy);

                //System.out.println(mi.getY());
                return;
            }
            // check for mouse button release
            if (Greenfoot.mouseDragEnded(this) && isGrabbed)
            {
                // release the object
                isGrabbed = false;
                r.predelejCaru();

                return;
            }
        
        
        
        
        // Add your action code here.
    }    
}

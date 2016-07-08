import java.util.*;
/**
 * @author ghibulo 
 * @150526
 */
public class Lagrange
{

    ArrayList<Integer> sx = new ArrayList<Integer>(5);
    ArrayList<Integer> sy = new ArrayList<Integer>(5);
    ArrayList<Zlomek> clen = new ArrayList<Zlomek>(5);

    public Lagrange()
    {

    }

    public void pridejBod(int x, int y) {
        if (!sx.contains(x)) {
          sx.add(x);
          sy.add(y);
          if (sx.size()>1) spocitejCleny();
        }
    }
    
    public boolean uberBod (int x) {
        int poradi = sx.indexOf(x);
        if (poradi!=-1) {
          sx.remove(poradi);sy.remove(poradi);spocitejCleny();
          return true;
        }
        return false;
    }
    
    public boolean prepisBod (int x, int y) {
        int poradi = sx.indexOf(x);
        if (poradi!=-1) {
          sy.remove(poradi);sy.add(poradi,y);spocitejCleny();
          return true;
        }
        return false;
    }
    
    private void spocitejCleny() {   
        int x,soucin=1;
        clen.clear();
        for (int i=0;i<sx.size();i++) {
            x=sx.get(i);
            for (int j=0;j<sx.size();j++) {
                if (j!=i) soucin*=(x-sx.get(j));
            }
            clen.add(new Zlomek(sy.get(i),soucin));soucin=1;
        }
        
    }
    
    public double dejFcniHodnotu(int x) {
        Zlomek v=new Zlomek(0,1);
        int soucin=1;        
        for (int i=0;i<clen.size();i++) {
            for (int j=0;j<sx.size();j++) {
                if (j!=i) soucin*=(x-sx.get(j));
            }
            v.prictiZlomek( clen.get(i).krat(soucin));soucin=1;
        }
        return v.citatel/v.jmenovatel;   
    }
    
    public String toString() {
        StringBuilder fce= new StringBuilder("y=");
        for (int i=0;i<clen.size();i++) { 
            fce.append(((i==0)?"":"+")).append(clen.get(i));
            for (int j=0;j<sx.size();j++) {
                 if (j!=i) fce.append("*(x-").append(sx.get(j)).append(")");
            }
        }
        return fce.toString();
    }
    

}



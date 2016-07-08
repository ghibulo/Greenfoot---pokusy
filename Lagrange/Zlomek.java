public class Zlomek
{
    // instance variables - replace the example below with your own
    public long citatel, jmenovatel;

    /**
     * Constructor for objects of class Zlomek
     */
    public Zlomek(long cit, long jm)
    {
        citatel = cit;
        jmenovatel = jm;  
        zkratZlomek();
    }

    public void prictiCislo(long jake)
    {
       citatel += jmenovatel*jake;
       zkratZlomek();
       
    }
    
    public void prictiZlomek(Zlomek jaky) {
        citatel = citatel*jaky.jmenovatel+jaky.citatel*jmenovatel;
        jmenovatel *=jaky.jmenovatel;
        zkratZlomek();
    }
    
    public Zlomek plus(long jake) {
        return new Zlomek(citatel+jmenovatel*jake,jmenovatel);
    }
    
    public void nasobCislem(double jakym) {
        citatel *=jakym;
    }
    
    public Zlomek krat(long cim) {
        return new Zlomek(citatel*cim,jmenovatel);
    }
    
    public void zkratZlomek() {
        
        long a=citatel,b=jmenovatel,c;
        do {
        c = a % b;
        a = b;
        b = c;
        } while ( c!=0   );
        // a ... je spolecny delitel
        citatel = citatel/a;
        jmenovatel /= a;
        
        
    }
    
    public String toString() {
        return "("+citatel+"/"+jmenovatel+")";
    }
}

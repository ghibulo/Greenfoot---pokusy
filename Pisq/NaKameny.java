import greenfoot.*;
import greenfoot.GreenfootImage.*;
import javax.swing.*;
import java.util.List;


public class NaKameny extends World
{

    public Symbol[][][] poleSymbolu;
    public Kamen[][][] poleKamenu;
    public final int MRIZKA = 5;
    //konstanty pro zobrazeni
    public final int POSUNY = 100;
    public final int POSUNX = 90;
    public final int DELTAX = 80;
    public final int DELTAY = 120;
    public final int DELTAZ = 15;
    
    int posledniVyberX, posledniVyberY, posledniVyberZ;
    public NaKameny()
    {    
        super(500, 650, 1); 
        poleSymbolu = new Symbol[MRIZKA][MRIZKA][MRIZKA];
        poleKamenu = new Kamen[MRIZKA][MRIZKA][MRIZKA];
        vynulujPole();
        vygenerujKameny();
        addObject(new Sipka('v'),460,100);
        addObject(new RotacniSipka('+'),25,25);
        addObject(new RotacniSipka('-'),25,600);
        
    }

    public void vynulujPole() {
        Kamen.nastavVlastnika(this);Sipka.nastavVlastnika(this);RotacniSipka.nasSvet=this;
        for (int a=0;a<MRIZKA;a++)
            for (int b=0;b<MRIZKA;b++)
                for (int c=0;c<MRIZKA;c++)
                    poleSymbolu[a][b][c]=Symbol.nic;
    }

    public void vygenerujKameny() {
        for (int a=0;a<MRIZKA;a++)
            for (int b=0;b<MRIZKA;b++)
                for (int c=0;c<MRIZKA;c++) {
                    poleKamenu[a][b][c] = new Kamen(a,b,c);
                    addObject(poleKamenu[a][b][c],sx(a) ,sy(b,c));
                }

    }

    public void vyberRaduKamenu(int kolikata) { //pocitano na obrazovce shora
        if (kolikata>=0) {
            posledniVyberY = kolikata / MRIZKA;
            posledniVyberZ = MRIZKA-(kolikata % MRIZKA)-1;
            posledniVyberX = -1;
            for(int x=0;x<MRIZKA;x++) {
                poleKamenu[x][posledniVyberY][posledniVyberZ].nastavVyber();
            }    
        }
    }
    
    public void posledniVyberVpravo() {
        Kamen.splneno=0;
            for(int x=0;x<MRIZKA;x++) {
                poleKamenu[x][posledniVyberY][posledniVyberZ].plan="doleva";
            }            
        
        
    }
    
    public void odvyberKamenu() {
        for (int x=0;x<MRIZKA;x++)
            for(int y=0;y<MRIZKA;y++) 
               for(int z=0;z<MRIZKA;z++) {
                        poleKamenu[x][y][z].nastavNevyber();
               }    
        }
    
    public void otocitDesku(char smer) {
        Kamen.splneno=0;    
        for (int x=0;x<MRIZKA;x++)
            for(int y=0;y<MRIZKA;y++) 
               for(int z=0;z<MRIZKA;z++)
                    poleKamenu[x][y][z].plan=(smer=='+')?"otockaPo":"otockaProti";
        
    }
       
    public void prekreslitPlan() {
        List<Kamen> k = getObjects(Kamen.class);
        this.removeObjects(k);
        for (int a=0;a<MRIZKA;a++)
            for (int b=0;b<MRIZKA;b++)
                for (int c=0;c<MRIZKA;c++) {
                      addObject(poleKamenu[a][b][c],sx(a) ,sy(b,c));
                }
   
    }
        
    public void prerovnejSloupek(int px, int py) { //vyresi viditelnost sloupku, vstupem index pole
        //Kamen[] k = new Kamen[MRIZKA];
        for (int i=0;i<MRIZKA;i++) {
            removeObject(poleKamenu[px][py][i]);
        }
        for (int i=0;i<MRIZKA;i++) {
            addObject(poleKamenu[px][py][i],sx(px) ,sy(py,i));
        }       
    }
    
    public void act() {
        Symbol kdo = kontrolaKonce();
        //vyberRaduKamenu(16);

        if (kdo!=Symbol.nic) {
            if (kdo==Symbol.krizek) {
                JOptionPane.showMessageDialog(null, "Red player won!");
            } else {
                JOptionPane.showMessageDialog(null, "Blue player won!");  
            }
            Greenfoot.stop();
        };
    }

    public Symbol kontrolaKonce() { // vrati kdo vyhral
        Symbol [] v = new Symbol[7];
        //rovne smery po vrstvách
        for (int a=0;a<MRIZKA;a++)    
            for (int b=0;b<MRIZKA;b++) {
                v[1] = poleSymbolu[0][a][b]; v[2] = poleSymbolu[a][0][b];v[3] = poleSymbolu[a][b][0];
                v[4] = poleSymbolu[0][b][a]; v[5] = poleSymbolu[b][0][a];v[6] = poleSymbolu[b][a][0];
                for (int c=1;c<MRIZKA;c++) {
                    if (v[1]!=Symbol.nic) {v[1] = (v[1]==poleSymbolu[c][a][b])?v[1]:Symbol.nic;};
                    if (v[2]!=Symbol.nic) {v[2] = (v[2]==poleSymbolu[a][c][b])?v[2]:Symbol.nic;};
                    if (v[3]!=Symbol.nic) {v[3] = (v[3]==poleSymbolu[a][b][c])?v[3]:Symbol.nic;};
                    if (v[4]!=Symbol.nic) {v[4] = (v[4]==poleSymbolu[c][b][a])?v[4]:Symbol.nic;};
                    if (v[5]!=Symbol.nic) {v[5] = (v[5]==poleSymbolu[b][c][a])?v[5]:Symbol.nic;};
                    if (v[6]!=Symbol.nic) {v[6] = (v[6]==poleSymbolu[b][a][c])?v[6]:Symbol.nic;};
                }
                for (int i=1;i<=6;i++) {
                    if (v[i]!=Symbol.nic) return v[i];
                }     
        }

        //uhlopricky ve vrstvach
        for (int a=0;a<MRIZKA;a++) { 
            v[1] = poleSymbolu[0][0][a];        v[2] = poleSymbolu[a][0][0];       v[3] = poleSymbolu[0][a][0];
            v[4] = poleSymbolu[0][MRIZKA-1][a]; v[5] = poleSymbolu[a][0][MRIZKA-1];  v[6] = poleSymbolu[0][a][MRIZKA-1];
            for (int b=1;b<MRIZKA;b++) {
                if (v[1]!=Symbol.nic) {v[1] = (v[1]==poleSymbolu[b][b][a])?v[1]:Symbol.nic;};
                if (v[2]!=Symbol.nic) {v[2] = (v[2]==poleSymbolu[a][b][b])?v[2]:Symbol.nic;};
                if (v[3]!=Symbol.nic) {v[3] = (v[3]==poleSymbolu[b][a][b])?v[3]:Symbol.nic;};
                if (v[4]!=Symbol.nic) {v[4] = (v[4]==poleSymbolu[b][MRIZKA-b-1][a])?v[4]:Symbol.nic;};
                if (v[5]!=Symbol.nic) {v[5] = (v[5]==poleSymbolu[a][b][MRIZKA-b-1])?v[5]:Symbol.nic;};
                if (v[6]!=Symbol.nic) {v[6] = (v[6]==poleSymbolu[b][a][MRIZKA-b-1])?v[6]:Symbol.nic;};
            }
            for (int i=1;i<=6;i++) {
                if (v[i]!=Symbol.nic) return v[i];
            } 
        }
        //telesove uhlopricky
        v[1] = poleSymbolu[0][0][0];        v[2] = poleSymbolu[MRIZKA-1][0][0];     
        v[3] = poleSymbolu[0][0][MRIZKA-1]; v[4] = poleSymbolu[MRIZKA-1][0][MRIZKA-1];
        for (int a=1;a<MRIZKA;a++) {  
            if (v[1]!=Symbol.nic) {v[1] = (v[1]==poleSymbolu[a][a][a])?v[1]:Symbol.nic;};
            if (v[2]!=Symbol.nic) {v[2] = (v[2]==poleSymbolu[MRIZKA-a-1][a][a])?v[2]:Symbol.nic;};
            if (v[3]!=Symbol.nic) {v[3] = (v[3]==poleSymbolu[a][a][MRIZKA-1-a])?v[3]:Symbol.nic;};
            if (v[4]!=Symbol.nic) {v[4] = (v[4]==poleSymbolu[MRIZKA-a-1][a][MRIZKA-a-1])?v[4]:Symbol.nic;};               
        }
        for (int i=1;i<=4;i++) {
            if (v[i]!=Symbol.nic) return v[i];
        } 

        return Symbol.nic;
    }

    public int sx(int index) {
        return POSUNX+index*DELTAX;
    }

    public int sy(int iy, int iz) {
        return POSUNY+iy*DELTAY - DELTAZ*iz;
    }
    
    public int invsy(int iy) { //prevede souradnici ukazatele na poradi rady
        int k = (iy>POSUNY)?(iy-POSUNY)/DELTAY+1:0;
        int j = MRIZKA-1-(k*DELTAY+POSUNY - iy)/DELTAZ;
        //System.out.println("k="+k+",j="+j+"k*5+j="+(k*5+j));
        return (j>=0)?k*MRIZKA+j:-1;
    }


}

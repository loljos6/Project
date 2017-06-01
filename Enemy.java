
package toby2;

import java.awt.image.BufferedImage;


public class Enemy {
    private int HP;
    private int ATK;
    private int DEF;
    private int MDEF; 
    private String name;
    private String picture;
    
    Enemy(int hp, int atk, int ef, int mdef, String name){
        
    }
    public int getHP(){
        return HP;
    }
    public int getATK(){
        return ATK;
    }
    public int getDEF(){
        return DEF;
    }
    public int getMDEF(){
        return MDEF;
    }
    public String getName(){
        return name;
    }
    public String getPicture(){
        return picture;
    }
}



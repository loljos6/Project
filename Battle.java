
package toby2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Battle {
    EnemyList enemyList = new EnemyList();
    public Battle Slimebob1X = new Battle(enemyList.Slimebob);
    Battle Slimebab2X = new Battle(enemyList.Slimebob,enemyList.Slimebob);
    //
    public static BufferedImage enemy1 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);
    public static int x;
    public static int y;
    //
    Battle(Enemy enemy1){

    }
    Battle(Enemy enemy1, Enemy enemy2){
        
    }
    Battle(Enemy enemy1, Enemy enemy2, Enemy enemy3){
        
    }



}

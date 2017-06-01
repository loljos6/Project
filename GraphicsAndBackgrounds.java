
package toby2;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;


public class GraphicsAndBackgrounds extends JPanel implements KeyListener{
    
    
    
    //BATTLE STUFF
    public static int opacityBattle = 0;
    public static int opacityBattleBox = 0;
    
    public static BufferedImage BBox = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);   
    public static BufferedImage infoBox = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);   
    public static BufferedImage battleground = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB); 
    public static BufferedImage BBoxText = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB); 
    public static BufferedImage infoBoxText = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);       
    public static boolean inBattle = false;
    public static int battleChoice = 1;
    public static BufferedImage enemy1 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);
    public static int enemy1x = 0;
    public static int enemy1y = 0;
    public static BufferedImage enemy2 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);
    public static int enemy2x = 0;
    public static int enemy2y = 0;
    public static BufferedImage enemy3 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);    
    public static int enemy3x = 0;
    public static int enemy3y = 0;   
    public static BufferedImage deathScreen = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB); 
    
    public static BufferedImage tobyBattle = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);   
    public static int tobyX = 0;
    public static int tobyY = 0;
    public static BufferedImage quintisBattle = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);   
    public static int quintisX = 0;
    public static int quintisY = 0;
 
    
    
    public static int tobyAction = 0;
    public static String battleTextTop = "";
    public static String option1 = "";
    public static String option2 = "";
    public static String option3 = "";
    public static int selectedMenu = 0;
    public static int battleCursorY = 825;
    public static int keyPressedProtectorTimer = 0;   
    public static int battleTimer = 0;
    public static int selected = 0;
    

    public void setDeathScreen(String location){
        try{
            deathScreen = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }        
    }
    public void setBattleground(String location){
        try{
            battleground = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }          
    }
    public void setEnemy1(String location){
        try{
            enemy1 = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }        
    }
    
    public void placeEnemy1(int x, int y){
        enemy1x = x;
        enemy1y = y;
    }
    public void setEnemy2(String location){
        try{
            enemy2 = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }        
    }
    
    public void placeEnemy2(int x, int y){
        enemy2x = x;
        enemy2y = y;
    }
    public void setEnemy3(String location){
        try{
            enemy3 = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }        
    }
    
    public void placeEnemy3(int x, int y){
        enemy3x = x;
        enemy3y = y;
    }
    public void setToby(String location){
        try{
            tobyBattle = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }        
    }
    
    public void placeToby(int x, int y){
        tobyX = x;
        tobyY = y;
    }
    public void setQuintis(String location){
        try{
            quintisBattle = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }        
    }
    
    public void placeQuintis(int x, int y){
        quintisX = x;
        quintisY = y;
    }
    
    
    
    
    public void setBBox(){
        Graphics2D g = (Graphics2D)BBox.getGraphics(); 
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(99, 99, 1101, 201);
        g.drawRect(98, 98, 1103, 203);
        g.drawRect(97,97,1105,205);
        g.setColor(Color.black);
        g.fillRect(100, 100, 1100, 200);           
    }
    public void setInfoBox(){
        Graphics2D g = (Graphics2D)infoBox.getGraphics(); 
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(849, 749, 401, 401);
        g.drawRect(848, 748, 403, 403);
        g.drawRect(847,747,405,405);
        g.setColor(Color.black);
        g.fillRect(850, 750, 400, 400);          
    }
    //I would have made a class for battles, but, I'm running out of time. It doesn't need to be optimal, right?
    public void setInfoBoxText(){
        if(PlayerStats.tcurrHP <= 0){
            if(OPTimer > 0){
                Sound.stopTrack();
                Sound.playSE("Audio Resources/_P_Powerup_2.wav");
                Sound.playBattleTrack("Audio Resources/Nape_Mango_flew_into_a_meme_wormhole_and_hasn_39_t.wav");
                setDeathScreen("Resources/death screen.png");
                OPTimer = -999999999;                
            }

        }
else{
        if(slimebob1HP < 0){
            selectedMenu = -5;
        }
        if(selectedMenu == 0){
            option1 = "ATTACK";
            option2 = "SKILL";
            option3 = "ITEM";
            if(battleTextTop != "A hostile slimebob approaches!"){
                battleTextTop = "Toby: All right... What next?";
            }
            
            if(selected == 0){
                if(keyPressedProtectorTimer >0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;  
                    keyPressedProtectorTimer = 11;
                    selected = selected+2; 
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                    keyPressedProtectorTimer = 15;
                    selectedMenu = -4;
                }
            }
            if(selected == 1){
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;
                    keyPressedProtectorTimer = 11;
                    selected++;
                } 
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 15;
                    selected = 0;
                    selectedMenu = 3;
                }
            }
            if(selected == 2){
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }

                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected = selected - 2;
                }      
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }   
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 15;
                    selected = 0;
                    selectedMenu = 1;
                }                
            }
        }
    
        if(selectedMenu == 1){
            option1 = "HEALTH POT";
            option2 = "MANA POT";
            option3 = "BACK";
            if(selected == 0){
                battleTextTop = "Heal 30 HP. You have "+numberOfHealthPots+" left.";
                if(keyPressedProtectorTimer >0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;  
                    keyPressedProtectorTimer = 11;
                    selected = selected+2; 
                }  
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    if(numberOfHealthPots ==0){
                        battleTextTop = "Toby: Rats! I'm out of health pots!";
                    }
                    if(numberOfHealthPots >=1){
                        Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                        keyPressedProtectorTimer = 15;
                        selectedMenu = -2;
                    }
                    
                }                 
            }            
            if(selected == 1){
                battleTextTop = "Heal 30 MP. You have "+numberOfManaPots+" left.";
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;
                    keyPressedProtectorTimer = 11;
                    selected++;
                } 
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    if(numberOfManaPots ==0){
                        battleTextTop = "Toby: Rats! I'm out of mana pots!";
                    }
                    if(numberOfManaPots >= 1){
                        Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                        keyPressedProtectorTimer = 15;
                        selectedMenu = -3;
                    }
                }
            }
            if(selected == 2){
                battleTextTop = "Go back.";
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }

                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected = selected - 2;
                }      
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }   
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2L.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;                  
                    selected = 0;
                    selectedMenu = 0;
                }                
            }            
            
        }
        //skill list
        if(selectedMenu == 3){
            option1 = "TOBY DRIVER";
            option2 = "BACK";
            option3 = "";
            if(selected == 0){
                battleTextTop = "Toby's special attack. Does bonus dmg.  Costs 4 MP.";
                if(keyPressedProtectorTimer >0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                //toby's skill
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    if(PlayerStats.tcurrMP < 4){
                        battleTextTop = "Toby: Not enough MP!";
                    }
                    else{
                        Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                        keyPressedProtectorTimer = 15;
                        selectedMenu = -6;
                    }
                }
            }
            if(selected == 1){
                battleTextTop = "Go back.";
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                } 
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2L.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;                  
                    selected = 0;
                    selectedMenu = 0;
                } 
            }       
            
        }
        //enemy's turn.
        if(selectedMenu == -1){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "Slimebob engages!";
                setEnemy1("Resources/slimebob attack.png");
            }
            if(battleTimer == 120){
                Sound.playBattleSE("Audio Resources/sword sound.wav");                
            }            
                if(battleTimer == 75){
                setToby("Resources/Toby Hit.png");
                Sound.playBattleSE("Audio Resources/spell1.wav");
                battleTextTop = "Toby took "+(slimebob1DMG-PlayerStats.tDEF)+" damage!";
            }
            if(battleTimer == 1){
                setEnemy1("Resources/slimebob.png");
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrHP = PlayerStats.tcurrHP-(slimebob1DMG -PlayerStats.tDEF);
                selectedMenu = 0;
                battleCursorY = 825;
                selected = 0;
            }
        }
        //topy using health pot.
        if(selectedMenu == -2){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "Toby drank a health pot!";
            }
            if(battleTimer == 150){
                setToby("Resources/Toby Health Pot.png");
            }
            if(battleTimer == 75){
                Sound.playBattleSE("Audio Resources/[P] heal.wav");
                battleTextTop = "Toby regained "+(30)+" health points!";
            }
            if(battleTimer == 1){
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrHP = PlayerStats.tcurrHP + 30;
                if(PlayerStats.tcurrHP > PlayerStats.tHP){
                    PlayerStats.tcurrHP = PlayerStats.tHP;
                }
                numberOfHealthPots --;
                selectedMenu = -1;
            }
        }      
        //toby using a mana pot.
        if(selectedMenu == -3){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "Toby drank a mana pot!";
            }
            if(battleTimer == 150){
                setToby("Resources/Toby Mana Pot.png");
            }
            if(battleTimer == 75){
                Sound.playBattleSE("Audio Resources/[P] heal.wav");
                battleTextTop = "Toby regained "+(10)+" mana points!";
            }
            if(battleTimer == 1){
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrMP = PlayerStats.tcurrMP + 10;
                if(PlayerStats.tcurrMP > PlayerStats.tMP){
                    PlayerStats.tcurrMP = PlayerStats.tMP;
                }
                numberOfManaPots --;
                selectedMenu = -1;
            }
        }
        //Toby attack
        if(selectedMenu == -4){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                setToby("Resources/Toby Attack.png");
                battleTextTop = "Toby attacks the slimebob!";
            }
            if(battleTimer == 120){
                Sound.playBattleSE("Audio Resources/sword sound.wav");                
            }
            if(battleTimer == 75){
                setEnemy1("Resources/slimebob hit.png");
                Sound.playBattleSE("Audio Resources/Spell1.wav");
                battleTextTop = "Toby hit the slimebob for "+(PlayerStats.tATK-slimebob1DEF)+" damage!";
            }
            if(battleTimer == 1){
                setEnemy1("Resources/slimebob.png");
                setToby("Resources/Toby1.png");
                slimebob1HP = slimebob1HP-(PlayerStats.tATK-slimebob1DEF);
                selectedMenu = -1;
            }
        }
        //ending a fight
        if(selectedMenu == -5){
            if(battleTimer ==0){
                battleTimer = 100000300;
            }
            if(battleTimer == 100000300){
                PlayerStats.tEXP += 3;
                battleTextTop = "Toby defeated the slimebob and got 3 EXP!";
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;        
                setEnemy1("Resources/slimebob dead.png"); 
            }
            if(PlayerStats.tEXP >= PlayerStats.tEXPThreshold && battleTimer == 100000185){
                Sound.playBattleSE("Audio Resources/_P_save.wav");
                battleTextTop = "No way!? Toby Leveled up!";
                levelUp();
            }
            if(battleTimer < 100000150){
                
                Sound.fadeOutTrack();
                slimebob1HP = 15;
                slimebob1DEF = 6;
                slimebob1DMG = 13;                
            }
            if(battleTimer == 100000000){
                Sound.stopTrack();
                battleTimer = 0;
                battleCursorY = 825;
                selectedMenu = 0;
                selected = 0;
                OPTimer = 0;
                currentText++;
                currentLetter = 0;
                inBattle = false;
            }
           
        }
        //toby driver
        if(selectedMenu == -6){
             if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "TOBY DRIVER!";
            }
            if(battleTimer >1){
                if(battleTimer %9 <3){
                    setBattleground("Resources/skill background3.png");
                }
                if(battleTimer %9 >=3 && battleTimer %9 <6){
                    setBattleground("Resources/skill background1.png");
                }
                if(battleTimer %9 >=6){
                    setBattleground("Resources/skill background2.png");
                }                
            }
            if(battleTimer == 110){
                Sound.playBattleSE("Audio Resources/sword sound.wav"); 
                setToby("Resources/Toby Attack.png");
            }
            if(battleTimer == 100){
                Sound.playBattleSE("Audio Resources/sword sound.wav"); 
                setToby("Resources/Toby1.png");
            }   
            if(battleTimer == 90){
                Sound.playBattleSE("Audio Resources/sword sound.wav");   
                setToby("Resources/Toby Attack.png");
            }
            if(battleTimer == 75){
                setEnemy1("Resources/slimebob hit.png");
                Sound.playBattleSE("Audio Resources/Spell1.wav");
                battleTextTop = "WHAM! the slimebob took "+((PlayerStats.tATK+PlayerStats.tATK/2)-(slimebob1DEF))+" damage!";
            }
            if(battleTimer == 1){
                setEnemy1("Resources/slimebob.png");
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrMP = PlayerStats.tcurrMP-4;
                slimebob1HP = slimebob1HP-((PlayerStats.tATK+PlayerStats.tATK/2)-(slimebob1DEF));
                setBattleground("Resources/Battleground.png");
                selectedMenu = -1;
            }           
            
        }
}        
    }
    public void setInfoBoxText2(){
        if(PlayerStats.tcurrHP <= 0){
            if(OPTimer > 0){
                Sound.stopTrack();
                Sound.playSE("Audio Resources/_P_Powerup_2.wav");
                Sound.playBattleTrack("Audio Resources/Nape_Mango_flew_into_a_meme_wormhole_and_hasn_39_t.wav");
                setDeathScreen("Resources/death screen.png");
                OPTimer = -999999999;                
            }

        }
else{
        if(goldenSlimebobHP < 0){
            selectedMenu = -5;
        }
        if(selectedMenu == 0){
            option1 = "ATTACK";
            option2 = "SKILL";
            option3 = "ITEM";
            if(battleTextTop != "A golden slimebob approaches!"){
                battleTextTop = "Toby: All right... What next?";
            }
            
            if(selected == 0){
                if(keyPressedProtectorTimer >0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;  
                    keyPressedProtectorTimer = 11;
                    selected = selected+2; 
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                    keyPressedProtectorTimer = 15;
                    selectedMenu = -4;
                }
            }
            if(selected == 1){
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;
                    keyPressedProtectorTimer = 11;
                    selected++;
                } 
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 15;
                    selected = 0;
                    selectedMenu = 3;
                }
            }
            if(selected == 2){
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }

                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected = selected - 2;
                }      
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }   
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 15;
                    selected = 0;
                    selectedMenu = 1;
                }                
            }
        }
    
        if(selectedMenu == 1){
            option1 = "HEALTH POT";
            option2 = "MANA POT";
            option3 = "BACK";
            if(selected == 0){
                battleTextTop = "Heal 30 HP. You have "+numberOfHealthPots+" left.";
                if(keyPressedProtectorTimer >0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;  
                    keyPressedProtectorTimer = 11;
                    selected = selected+2; 
                }  
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    if(numberOfHealthPots ==0){
                        battleTextTop = "Toby: Rats! I'm out of health pots!";
                    }
                    if(numberOfHealthPots >=1){
                        Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                        keyPressedProtectorTimer = 15;
                        selectedMenu = -2;
                    }
                    
                }                 
            }            
            if(selected == 1){
                battleTextTop = "Heal 30 MP. You have "+numberOfManaPots+" left.";
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;
                    keyPressedProtectorTimer = 11;
                    selected++;
                } 
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    if(numberOfManaPots ==0){
                        battleTextTop = "Toby: Rats! I'm out of mana pots!";
                    }
                    if(numberOfManaPots >= 1){
                        Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                        keyPressedProtectorTimer = 15;
                        selectedMenu = -3;
                    }
                }
            }
            if(selected == 2){
                battleTextTop = "Go back.";
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }

                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected = selected - 2;
                }      
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }   
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2L.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;                  
                    selected = 0;
                    selectedMenu = 0;
                }                
            }            
            
        }
        //skill list
        if(selectedMenu == 3){
            option1 = "TOBY DRIVER";
            option2 = "BACK";
            option3 = "";
            if(selected == 0){
                battleTextTop = "Toby's special attack. Does bonus dmg.  Costs 4 MP.";
                if(keyPressedProtectorTimer >0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                //toby's skill
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    if(PlayerStats.tcurrMP < 4){
                        battleTextTop = "Toby: Not enough MP!";
                    }
                    else{
                        Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                        keyPressedProtectorTimer = 15;
                        selectedMenu = -6;
                    }
                }
            }
            if(selected == 1){
                battleTextTop = "Go back.";
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                } 
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2L.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;                  
                    selected = 0;
                    selectedMenu = 0;
                } 
            }       
            
        }
        //enemy's turn.
        if(selectedMenu == -1){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "The golden slimebob engages!";
                setEnemy1("Resources/golden slimebob attack.png");
            }
            if(battleTimer == 120){
                Sound.playBattleSE("Audio Resources/sword sound.wav");                
            }            
                if(battleTimer == 75){
                setToby("Resources/Toby Hit.png");
                Sound.playBattleSE("Audio Resources/spell1.wav");
                battleTextTop = "Toby took "+(goldenslimebobDMG-PlayerStats.tDEF)+" damage!";
            }
            if(battleTimer == 1){
                setEnemy1("Resources/golden slimebob.png");
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrHP = PlayerStats.tcurrHP-(goldenslimebobDMG -PlayerStats.tDEF);
                selectedMenu = 0;
                battleCursorY = 825;
                selected = 0;
            }
        }
        //topy using health pot.
        if(selectedMenu == -2){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "Toby drank a health pot!";
            }
            if(battleTimer == 150){
                setToby("Resources/Toby Health Pot.png");
            }
            if(battleTimer == 75){
                Sound.playBattleSE("Audio Resources/[P] heal.wav");
                battleTextTop = "Toby regained "+(30)+" health points!";
            }
            if(battleTimer == 1){
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrHP = PlayerStats.tcurrHP + 30;
                if(PlayerStats.tcurrHP > PlayerStats.tHP){
                    PlayerStats.tcurrHP = PlayerStats.tHP;
                }
                numberOfHealthPots --;
                selectedMenu = -1;
            }
        }      
        //toby using a mana pot.
        if(selectedMenu == -3){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "Toby drank a mana pot!";
            }
            if(battleTimer == 150){
                setToby("Resources/Toby Mana Pot.png");
            }
            if(battleTimer == 75){
                Sound.playBattleSE("Audio Resources/[P] heal.wav");
                battleTextTop = "Toby regained "+(10)+" mana points!";
            }
            if(battleTimer == 1){
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrMP = PlayerStats.tcurrMP + 10;
                if(PlayerStats.tcurrMP > PlayerStats.tMP){
                    PlayerStats.tcurrMP = PlayerStats.tMP;
                }
                numberOfManaPots --;
                selectedMenu = -1;
            }
        }
        //Toby attack
        if(selectedMenu == -4){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                setToby("Resources/Toby Attack.png");
                battleTextTop = "Toby attacks the golden slimebob!";
            }
            if(battleTimer == 120){
                Sound.playBattleSE("Audio Resources/sword sound.wav");                
            }
            if(battleTimer == 75){
                setEnemy1("Resources/golden slimebob hit.png");
                Sound.playBattleSE("Audio Resources/Spell1.wav");
                battleTextTop = "Toby hit the golden slimebob for "+(PlayerStats.tATK-goldenSlimebobDEF)+" damage!";
            }
            if(battleTimer == 1){
                setEnemy1("Resources/golden slimebob.png");
                setToby("Resources/Toby1.png");
                goldenSlimebobHP = goldenSlimebobHP-(PlayerStats.tATK-goldenSlimebobDEF);
                selectedMenu = -1;
            }
        }
        //ending a fight
        if(selectedMenu == -5){
            if(battleTimer ==0){
                battleTimer = 100000300;
            }
            if(battleTimer == 100000300){
                PlayerStats.tEXP += 7;
                battleTextTop = "Toby defeated the golden slimebob and got 7 EXP!";
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;        
                setEnemy1("Resources/golden slimebob dead.png"); 
            }
            if(PlayerStats.tEXP >= PlayerStats.tEXPThreshold && battleTimer == 100000185){
                Sound.playBattleSE("Audio Resources/_P_save.wav");
                battleTextTop = "No way!? Toby Leveled up!";
                levelUp();
            }
            if(battleTimer < 100000150){
                
                Sound.fadeOutTrack();
                goldenSlimebobHP = 40;
                goldenSlimebobDEF = 9;
                goldenslimebobDMG = 15;                
            }
            if(battleTimer == 100000000){
                Sound.stopTrack();
                battleTimer = 0;
                battleCursorY = 825;
                selectedMenu = 0;
                selected = 0;
                OPTimer = 0;
                currentText++;
                currentLetter = 0;
                inBattle = false;
            }
           
        }
        //toby driver
        if(selectedMenu == -6){
             if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "TOBY DRIVER!";
            }
            if(battleTimer >1){
                if(battleTimer %9 <3){
                    setBattleground("Resources/skill background3.png");
                }
                if(battleTimer %9 >=3 && battleTimer %9 <6){
                    setBattleground("Resources/skill background1.png");
                }
                if(battleTimer %9 >=6){
                    setBattleground("Resources/skill background2.png");
                }                
            }
            if(battleTimer == 110){
                Sound.playBattleSE("Audio Resources/sword sound.wav"); 
                setToby("Resources/Toby Attack.png");
            }
            if(battleTimer == 100){
                Sound.playBattleSE("Audio Resources/sword sound.wav"); 
                setToby("Resources/Toby1.png");
            }   
            if(battleTimer == 90){
                Sound.playBattleSE("Audio Resources/sword sound.wav");   
                setToby("Resources/Toby Attack.png");
            }
            if(battleTimer == 75){
                setEnemy1("Resources/golden slimebob hit.png");
                Sound.playBattleSE("Audio Resources/Spell1.wav");
                battleTextTop = "WHAM! the golden slimebob took "+((PlayerStats.tATK+PlayerStats.tATK/2)-(goldenSlimebobDEF))+" damage!";
            }
            if(battleTimer == 1){
                setEnemy1("Resources/golden slimebob.png");
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrMP = PlayerStats.tcurrMP-4;
                goldenSlimebobHP = goldenSlimebobHP-((PlayerStats.tATK+PlayerStats.tATK/2)-(goldenSlimebobDEF));
                setBattleground("Resources/Puppy Cave.png");
                selectedMenu = -1;
            }           
            
        }
}        
    }
    //
    //
    //
    public void setInfoBoxText3(){
        if(PlayerStats.tcurrHP <= 0){
            if(OPTimer > 0){
                Sound.stopTrack();
                Sound.playSE("Audio Resources/_P_Powerup_2.wav");
                Sound.playBattleTrack("Audio Resources/Nape_Mango_flew_into_a_meme_wormhole_and_hasn_39_t.wav");
                setDeathScreen("Resources/death screen.png");
                OPTimer = -999999999;                
            }

        }
else{
        if(gropigHP < 0){
            selectedMenu = -5;
        }
        if(selectedMenu == 0){
            option1 = "ATTACK";
            option2 = "SKILL";
            option3 = "ITEM";
            if(battleTextTop != "Toby: The gropig is dangerous! Careful, Quintis!"){
                battleTextTop = "Toby: This won't be easy...";
            }
            
            if(selected == 0){
                if(keyPressedProtectorTimer >0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;  
                    keyPressedProtectorTimer = 11;
                    selected = selected+2; 
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                    keyPressedProtectorTimer = 15;
                    selectedMenu = -4;
                }
            }
            if(selected == 1){
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;
                    keyPressedProtectorTimer = 11;
                    selected++;
                } 
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 15;
                    selected = 0;
                    selectedMenu = 3;
                }
            }
            if(selected == 2){
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }

                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected = selected - 2;
                }      
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }   
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 15;
                    selected = 0;
                    selectedMenu = 1;
                }                
            }
        }
    
        if(selectedMenu == 1){
            option1 = "HEALTH POT";
            option2 = "MANA POT";
            option3 = "BACK";
            if(selected == 0){
                battleTextTop = "Heal 30 HP. You have "+numberOfHealthPots+" left.";
                if(keyPressedProtectorTimer >0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;  
                    keyPressedProtectorTimer = 11;
                    selected = selected+2; 
                }  
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    if(numberOfHealthPots ==0){
                        battleTextTop = "Toby: Rats! I'm out of health pots!";
                    }
                    if(numberOfHealthPots >=1){
                        Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                        keyPressedProtectorTimer = 15;
                        selectedMenu = -2;
                    }
                    
                }                 
            }            
            if(selected == 1){
                battleTextTop = "Heal 30 MP. You have "+numberOfManaPots+" left.";
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 945;
                    keyPressedProtectorTimer = 11;
                    selected++;
                } 
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    if(numberOfManaPots ==0){
                        battleTextTop = "Toby: Rats! I'm out of mana pots!";
                    }
                    if(numberOfManaPots >= 1){
                        Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                        keyPressedProtectorTimer = 15;
                        selectedMenu = -3;
                    }
                }
            }
            if(selected == 2){
                battleTextTop = "Go back.";
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }

                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected = selected - 2;
                }      
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }   
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2L.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;                  
                    selected = 0;
                    selectedMenu = 0;
                }                
            }            
            
        }
        //skill list
        if(selectedMenu == 3){
            option1 = "TOBY DRIVER";
            option2 = "BACK";
            option3 = "";
            if(selected == 0){
                battleTextTop = "Toby's special attack. Does bonus dmg.  Costs 4 MP.";
                if(keyPressedProtectorTimer >0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 885;  
                    keyPressedProtectorTimer = 11;
                    selected++; 
                }
                //toby's skill
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    if(PlayerStats.tcurrMP < 4){
                        battleTextTop = "Toby: Not enough MP!";
                    }
                    else{
                        Sound.playBattleSE("Audio Resources/_P_back_2H.wav");
                        keyPressedProtectorTimer = 15;
                        selectedMenu = -6;
                    }
                }
            }
            if(selected == 1){
                battleTextTop = "Go back.";
                if(keyPressedProtectorTimer>0){
                    keyPressedProtectorTimer --;
                }
                if(keyPressed == 3 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                } 
                if(keyPressed == 2 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;
                    selected--;
                }
                if(keyPressed == 1 && keyPressedProtectorTimer == 0){
                    Sound.playBattleSE("Audio Resources/_P_back_2L.wav");
                    battleCursorY = 825;
                    keyPressedProtectorTimer = 11;                  
                    selected = 0;
                    selectedMenu = 0;
                } 
            }       
            
        }
        //enemy's turn.
        if(selectedMenu == -1){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "The gropig tackles!";
                setEnemy1("Resources/gropig attack.png");
            }
            if(battleTimer == 120){
                Sound.playBattleSE("Audio Resources/sword sound.wav");                
            }            
                if(battleTimer == 75){
                setToby("Resources/Toby Hit.png");
                Sound.playBattleSE("Audio Resources/spell1.wav");
                battleTextTop = "Toby took "+(gropigDMG-PlayerStats.tDEF)+" damage!";
            }
            if(battleTimer == 1){
                setEnemy1("Resources/gropig.png");
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrHP = PlayerStats.tcurrHP-(gropigDMG -PlayerStats.tDEF);
                selectedMenu = 0;
                battleCursorY = 825;
                selected = 0;
            }
        }
        //topy using health pot.
        if(selectedMenu == -2){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "Toby drank a health pot!";
            }
            if(battleTimer == 150){
                setToby("Resources/Toby Health Pot.png");
            }
            if(battleTimer == 75){
                Sound.playBattleSE("Audio Resources/[P] heal.wav");
                battleTextTop = "Toby regained "+(30)+" health points!";
            }
            if(battleTimer == 1){
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrHP = PlayerStats.tcurrHP + 30;
                if(PlayerStats.tcurrHP > PlayerStats.tHP){
                    PlayerStats.tcurrHP = PlayerStats.tHP;
                }
                numberOfHealthPots --;
                selectedMenu = -7;
            }
        }      
        //toby using a mana pot.
        if(selectedMenu == -3){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "Toby drank a mana pot!";
            }
            if(battleTimer == 150){
                setToby("Resources/Toby Mana Pot.png");
            }
            if(battleTimer == 75){
                Sound.playBattleSE("Audio Resources/[P] heal.wav");
                battleTextTop = "Toby regained "+(10)+" mana points!";
            }
            if(battleTimer == 1){
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrMP = PlayerStats.tcurrMP + 10;
                if(PlayerStats.tcurrMP > PlayerStats.tMP){
                    PlayerStats.tcurrMP = PlayerStats.tMP;
                }
                numberOfManaPots --;
                selectedMenu = -7;
            }
        }
        //Toby attack
        if(selectedMenu == -4){
            if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                setToby("Resources/Toby Attack.png");
                battleTextTop = "Toby attacks the gropig!";
            }
            if(battleTimer == 120){
                Sound.playBattleSE("Audio Resources/sword sound.wav");                
            }
            if(battleTimer == 75){
                setEnemy1("Resources/gropig hit.png");
                Sound.playBattleSE("Audio Resources/Spell1.wav");
                battleTextTop = "Toby hit the gropig for "+(PlayerStats.tATK-gropigDEF)+" damage!";
            }
            if(battleTimer == 1){
                setEnemy1("Resources/gropig.png");
                setToby("Resources/Toby1.png");
                gropigHP = gropigHP-(PlayerStats.tATK-gropigDEF);
                selectedMenu = -7;
            }
        }
        //ending a fight
        if(selectedMenu == -5){
            if(battleTimer ==0){
                setToby("Resources/Toby12.png");
                setQuintis("Resources/Quintis Vexed Battle.png");
                battleTimer = 100000300;
            }
            if(battleTimer == 100000300){
                battleTextTop = "Toby: This guy is too tough!";
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;        
                setEnemy1("Resources/gropig dead.png"); 
            }
            if(PlayerStats.tEXP >= PlayerStats.tEXPThreshold && battleTimer == 100000185){
                Sound.playBattleSE("Audio Resources/_P_save.wav");
                battleTextTop = "No way!? Toby Leveled up!";
                levelUp();
            }
            if(battleTimer < 100000150){

                gropigHP = 40;
                gropigDEF = 9;
                gropigDMG = 15;                
            }
            if(battleTimer == 100000000){
                battleTimer = 0;
                battleCursorY = 825;
                selectedMenu = 0;
                selected = 0;
                OPTimer = 0;
                currentText++;
                currentLetter = 0;
                inBattle = false;
            }
           
        }
        //toby driver
        if(selectedMenu == -6){
             if(battleTimer == 0){
                battleTimer = 150;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                battleTextTop = "TOBY DRIVER!";
            }
            if(battleTimer >1){
                if(battleTimer %9 <3){
                    setBattleground("Resources/skill background3.png");
                }
                if(battleTimer %9 >=3 && battleTimer %9 <6){
                    setBattleground("Resources/skill background1.png");
                }
                if(battleTimer %9 >=6){
                    setBattleground("Resources/skill background2.png");
                }                
            }
            if(battleTimer == 110){
                Sound.playBattleSE("Audio Resources/sword sound.wav"); 
                setToby("Resources/Toby Attack.png");
            }
            if(battleTimer == 100){
                Sound.playBattleSE("Audio Resources/sword sound.wav"); 
                setToby("Resources/Toby1.png");
            }   
            if(battleTimer == 90){
                Sound.playBattleSE("Audio Resources/sword sound.wav");   
                setToby("Resources/Toby Attack.png");
            }
            if(battleTimer == 75){
                setEnemy1("Resources/gropig hit.png");
                Sound.playBattleSE("Audio Resources/Spell1.wav");
                battleTextTop = "WHAM! the gropig took "+((PlayerStats.tATK+PlayerStats.tATK/2)-(gropigDEF))+" damage!";
            }
            if(battleTimer == 1){
                setEnemy1("Resources/gropig.png");
                setToby("Resources/Toby1.png");
                PlayerStats.tcurrMP = PlayerStats.tcurrMP-4;
                gropigHP = gropigHP-((PlayerStats.tATK+PlayerStats.tATK/2)-(gropigDEF));
                setBattleground("Resources/Puppy Cave.png");
                selectedMenu = -7;
            }           
            
        }
        if(selectedMenu == -7){
            if(battleTimer == 0){
                battleTimer = 160;
                option1 = "";
                option2 = "";
                option3 = ""; 
                battleCursorY = battleCursorY - 1280;
                setQuintis("Resources/Quintis Spell.png");
                battleTextTop = "Quintis casts a spell!";
            }
            if(battleTimer == 155){
                Sound.playLoudBattleSE("Audio Resources/Magic Missiles.wav");                
            }
            if(battleTimer == 75){
                setEnemy1("Resources/gropig hit.png");
                Sound.playBattleSE("Audio Resources/Spell1.wav");
                battleTextTop = "The gropig took "+(19-gropigDEF)+" damage!";
            }
            if(battleTimer == 2){
                setEnemy1("Resources/gropig.png");
                setQuintis("Resources/Quintis 4.png");
                gropigHP = gropigHP-(20-gropigDEF);
                battleTimer = 0;
                selectedMenu = -1;
            }
        }
}        
    }
    //END OF BATTLE STUFF
    
    
    //Story Variables
    public static int textSpeed = 4;
    public static int talkingSE = 1;
    public static int talker = 1;
    public static long OPTimer = 0;
    //these are where the gamestates will be
    public static boolean intro = true;
    public static boolean chapter1A = false;
    public static boolean chapter1B = false;
    //
    public static int currentLetter = 0;
    public static int currentText = 0;
    
    
    public static double opacity1 = 0;
    public static double opacity2 = 0;
    public static double opacity3 = 1;
    public static double opacity4 =.8;
    
  
    public static KeyEvent e;
    
    public static int x = 0;
    public static int y = 0;
    
    public static int x2 = 0;
    public static int y2 = 0;
    
    public static int x3 = 0;
    public static int y3 = 0;
    
    public static int x4 = 0;
    public static int y4 = 0;
    //I do not need a 5 because it uses the same as 4, because the text box and the text are linked... sorta.
    
    public static int x6 = 1150;
    public static int y6 = 1150;
    


////////////////////////// pics 4, 5, and 6 are all for the text 
    public static BufferedImage currentPic6 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);
    public static BufferedImage currentPic5 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);//this one will be like currentPic 4, but for the text in the box.
    public static BufferedImage currentPic4 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);//currentPic4 will always be the text box
///////////
    public static BufferedImage currentPic3 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);
    public static BufferedImage currentPic2 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);
    public static BufferedImage currentPic = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);

    //this is how i am going to make the key pressed thing.
    //if space bar is pressed keyPressed = 1.
    //i will write code for the other keys as the time comes. 
    public static int keyPressed = 0;
    
    public GraphicsAndBackgrounds(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    public void startTimer(){
        OPTimer++;
    }
    public void fadeIn1(){
        if(opacity1<=.99)
            opacity1 +=.02;
    }
    public void fadeIn2(){
           if(opacity2<=.99)
            opacity2 +=.02;     
    }
    public void fadeIn3(){
             if(opacity3<=.99)
            opacity3 +=.02; 
    }
    public void fadeIn4(){
             if(opacity4<=.8)
            opacity4 +=.02; 
    }
    public void fadeOut1(){
        if(opacity1>=.05)
            opacity1-=.02;
    }
    public void fadeOut2(){
        if(opacity2>=.05)
            opacity2-=.02;
    }
    public void fadeOut3(){
        if(opacity3>=.05)
            opacity3-=.02;
    }
    public void fadeOut4(){
        if(opacity4>=.05)
            opacity4-=.02;        
    }
    public void clearPic1(){
        currentPic = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);
    }
    public void clearPic2(){
        currentPic2 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);        
    }
    public void clearPic3(){
        currentPic3 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);        
    }
    public void clearPic4(){
        currentPic4 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);  
    }
    public void clearPic5(){
        currentPic5 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);
        currentPic6 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);         
    }


    public void placePic1(int newX, int newY){
        x = newX;
        y = newY;
    }
    public void placePic2(int newX, int newY){
        x2 = newX;
        y2 = newY;
    }
    public void placePic3(int newX, int newY){
        x3 = newX;
        y3 = newY;
    }
    public void placePic4(int newX, int newY){
        x4 = newX;
        y4 = newY;        
    }
    
    
    public void movePic1(int minusX, int minusY){
        y= y-minusY;
        x= x-minusX;
    }
    public void movePic2(int minusX, int minusY){
        y2 = y2-minusY;
        x2 = x2-minusX;
    }
    public void movePic3(int minusX, int minusY){
        y3 = y3-minusY;
        x3 = x3-minusX;
    }
    public void movePic4(int minusX, int minusY){
        y4 = y4-minusY;
        x4 = x4-minusX;
    }
    public void setPic1(String location){
        try{
            currentPic = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }
    }
    public void setPic2(String location){
        try{
            currentPic2 = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }
    }
    public void setPic3(String location){
        try{
            currentPic3 = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }        
    }
    //this one is a little different. It will be drawn using java stuff
    public void setPic4(){
        Graphics2D g = (Graphics2D)currentPic4.getGraphics(); 
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(99, 99, 1101, 201);
        g.drawRect(98, 98, 1103, 203);
        g.drawRect(97,97,1105,205);
        g.setColor(Color.black);
        g.fillRect(100, 100, 1100, 200); 
        placePic4(0,900);
    }
    public void setPic5(){
        Graphics g = currentPic5.getGraphics();
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.BOLD, 40));
        g.drawString(AllStoryText.talking[talker], 120, 145);

        if(OPTimer%textSpeed ==1 && currentLetter <= AllStoryText.text[currentText].length() && currentLetter<=59){
            g.drawString(AllStoryText.text[currentText].substring(0,currentLetter), 120, 185);
            if(currentLetter > 0){
                if(AllStoryText.text[currentText].substring(currentLetter-1,currentLetter).equals(" ")){   
                    
                }
                else{
                Sound.playSE(AllStoryText.talkSE[talkingSE]);                
                }
            }
            currentLetter++;
        }
        if(OPTimer%textSpeed ==1 && currentLetter <= AllStoryText.text[currentText].length() && currentLetter>59){
            g.drawString(AllStoryText.text[currentText].substring(59,currentLetter), 120, 225);
            if(currentLetter > 0){
                if(AllStoryText.text[currentText].substring(currentLetter-1,currentLetter).equals(" ")){   
                    
                }
                else{
                Sound.playSE(AllStoryText.talkSE[talkingSE]);                
                }
            }
            currentLetter++;
        }
        if(currentLetter == AllStoryText.text[currentText].length()+1){
            setPic6();
        }    
    }
    // this is the text box, but up higher for certain scenes.
    public void setPic4H(){
        Graphics2D g = (Graphics2D)currentPic4.getGraphics(); 
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(99, 99, 1101, 201);
        g.drawRect(98, 98, 1103, 203);
        g.drawRect(97,97,1105,205);
        g.setColor(Color.black);
        g.fillRect(100, 100, 1100, 200); 
        placePic4(0,0);
    }
        
    public void setPic6(){
        try{
            currentPic6 = ImageIO.read(new File("Resources/arrow.png")); 
        }
        catch(IOException e){
            System.out.println("image at" + "Resources/arrow" + "could not be found. :(");
        }        
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);        
        if(inBattle){
            g2d.setColor(Color.black);
            g2d.fill3DRect(0, 0, 1280, 1280, true);
            g2d.drawImage(battleground, 0,0,null);
            g2d.drawImage(enemy1,enemy1x,enemy1y,null);
            g2d.drawImage(quintisBattle, quintisX, quintisY, null);
            g2d.drawImage(tobyBattle, tobyX, tobyY, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float).8));
            g2d.drawImage(BBox, 0, 0, null);
            g2d.drawImage(infoBox, 0, 0, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)1));
            g2d.drawImage(BBoxText, 0, 0, null);
            g2d.drawImage(infoBoxText, 0, 0, null);
            
            try{
                g.drawImage(ImageIO.read(new File("Resources/battle arrow.png")), 800, battleCursorY,null);
                g.setColor(Color.white);
                g.setFont(new Font("Snell Roundhand", Font.BOLD, 40));
                g.drawString(battleTextTop, 125, 200);
                g.setFont(new Font("Snell Roundhand", Font.BOLD, 40));
                g.drawString(option1, 860, 860);
                g.drawString(option2, 860, 920);
                g.drawString(option3, 860, 980);
                g.setFont(new Font("Snell Roundhand", Font.BOLD, 23));
                g.drawString(PlayerStats.tNAME+" LVL "+PlayerStats.tLVL+" "+":  HP "+PlayerStats.tcurrHP+"/"+PlayerStats.tHP+"  MP  "+PlayerStats.tcurrMP+"/"+PlayerStats.tMP, 855, 800);      
                g2d.drawImage(deathScreen, 0, 0,null);
            }
            catch(Exception e){
                
            }
            
        }
        else{
            g2d.setColor(Color.black);
            g2d.fill3DRect(0, 0, 1280, 1280, true);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)opacity1));
            g2d.drawImage(currentPic,x,y,null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)opacity2));
            g2d.drawImage(currentPic2, x2, y2,null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)opacity3));
            g2d.drawImage(currentPic3, x3, y3,null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)opacity4));
            g2d.drawImage(currentPic4, x4, y4, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)1));
            g2d.drawImage(currentPic5, x4, y4, null);
            g2d.drawImage(currentPic6, x6, y6, null);            
        }

        

    }

    public void intro() throws InterruptedException{
        if(OPTimer == 0){
                    Sound.playTrack("Audio Resources/To_Zanarkand_Final_Fantasy_X_.wav");
        }
            moveCloud();
            moveBird();
        
        if(OPTimer < 200){
            fadeIn1();
            this.setPic1("Resources/Sponser 1.png");
        }
        if(OPTimer < 270 && OPTimer >= 200){
            fadeOut1();
        }
        if(OPTimer < 470 && OPTimer> 270){
            this.setPic1("Resources/Sponser 2.png");
            fadeIn1();

        }
        if(OPTimer <540 && OPTimer >=470){
            fadeOut1();
        }
        
                if(OPTimer%3 == 0 && OPTimer<687 && OPTimer>=540){
                                        setPic1("Resources/T1.png");
                    fadeIn1();
                    fadeIn1();
                    fadeIn1();                    

                    movePic1(0,11);
                    Thread.sleep(0); 
                }
                else if(OPTimer%3 ==1 && OPTimer<687 && OPTimer>=540){
                    fadeIn1();
                    fadeIn1();  
                    fadeIn1();
                    setPic1("Resources/T2.png");
                    movePic1(0,12);
                    Thread.sleep(0);
                }
                else if(OPTimer%3 ==2 && OPTimer<687 && OPTimer>=540){
                    fadeIn1();    
                    fadeIn1();
                    fadeIn1();                    
                    setPic1("Resources/T3.png");
                    movePic1(0,12);
                    Thread.sleep(0);                
                } 
                if(OPTimer%3 == 0 && OPTimer>=687){
                    setPic1("Resources/T1.png");
                    Thread.sleep(0); 
                }
                else if(OPTimer%3 ==1 && OPTimer>=687){
                    setPic1("Resources/T2.png");
                    Thread.sleep(0);
                }
                else if(OPTimer%3 ==2 && OPTimer>=687){
                    setPic1("Resources/T3.png");
                    Thread.sleep(0);                
                }
                OPTimer++;                
    }
    
    public void moveCloud() throws InterruptedException{

        if(OPTimer>=540 && OPTimer<=600){
        fadeIn2();
                fadeIn2();
                        fadeIn2();
        setPic2("Resources/CLOUD A.png");

        movePic2(-6,9);
        if(OPTimer == 600){
            opacity2 = 0;
        }
        }

    }
    public void moveBird() throws InterruptedException{
        if(OPTimer >= 600 && OPTimer % 5 == 4){
            setPic3("Resources/BIRD1.png");        
        }
        if(OPTimer >= 600 && OPTimer %5 == 1){
            setPic3("resources/BIRD2.png");
        }
        if(OPTimer>=600)movePic3(18,10);
    }
    public void start(){
        if(OPTimer>= 687){
            currentPic2 = new BufferedImage(1280,1280,BufferedImage.TYPE_INT_ARGB);
            Graphics g = currentPic2.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, -70, 600, 150);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial Black", Font.BOLD, 40));
            g.drawString("PRESS SPACE TO START", 10, 45);
            placePic2(600,800);
        }
        if(OPTimer % 50 <=25 && OPTimer >=700){
            fadeIn2();
            fadeIn2();            
        }
        if(OPTimer % 50 >=25 && OPTimer % 50 <=50 && OPTimer >=700){
            fadeOut2();
            fadeOut2();          
        }
    }

    
    public void update(){ 
       try{
           if(intro == true){
             intro();   
             start(); 
             if(keyPressed == 1 && OPTimer >= 687){
                 chapter1A = true;
                 OPTimer = 0;
                 Sound.playSE("Audio Resources/_P_save.wav");
                 intro = false;
             }
           }
           ////////////////////////////////////////////////////////////////////////////////////
           
           
           
           ////////////////////////////////////////////////////////////////////////////////////
           if(chapter1A == true){
               chapter1A();
           }
           if(chapter1B == true){
               chapter1B();
           }
       }

       catch(InterruptedException e){
           
       }
       
    }
    public void draw(){
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
     }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            keyPressed = 1; 
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            keyPressed = 2; 
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){           
            keyPressed = 3; 
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){      
            keyPressed = 4; 
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){         
            keyPressed = 5; 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed = 0;
    }
    
    public void slimebobBattle(){
        if(OPTimer == 50){
            Sound.playSE("Audio Resources/_P_Powerup_2.wav");
            opacity1 = 0;
            opacity2 = 0;
            opacity3 = 0;
        }
        if(OPTimer < 100 && OPTimer > 0){
            fadeOut1();
            fadeOut2();
            fadeOut3();
            fadeOut4();
            Sound.fadeOutTrack();
        }
        if(OPTimer == 99){
            battleTextTop = "A hostile slimebob approaches!";
            Sound.stopTrack();
            Sound.playBattleTrack("Audio Resources/Baccano - Guns  Roses.wav");     
            setBattleground("Resources/battleground.png");
            setToby("Resources/Toby1.png");
            setEnemy1("Resources/slimebob.png");           
        }
        if(OPTimer >100){
            inBattle = true;
            placeEnemy1(300,300);       
            placeToby(10,700);
            setBBox();
            setInfoBox();
            setInfoBoxText();
            if(battleTimer > 0){
                battleTimer --;
            }
        }
     
    }
    
    public void goldenSlimebobBattle(){
        if(OPTimer == 50){
            Sound.playSE("Audio Resources/_P_Powerup_2.wav");
            opacity1 = 0;
            opacity2 = 0;
            opacity3 = 0;
        }
        if(OPTimer < 100 && OPTimer > 0){
            fadeOut1();
            fadeOut2();
            fadeOut3();
            fadeOut4();
            Sound.fadeOutTrack();
        }
        if(OPTimer == 99){
            battleTextTop = "A golden slimebob approaches!";
            Sound.stopTrack();
            Sound.playBattleTrack("Audio Resources/Skullgirls OST 06 - Moonlit Melee.wav");     
            setBattleground("Resources/Puppy Cave.png");
            setToby("Resources/Toby1.png");
            setEnemy1("Resources/golden slimebob.png");           
        }
        if(OPTimer >100){
            inBattle = true;
            placeEnemy1(300,300);       
            placeToby(10,700);
            setBBox();
            setInfoBox();
            setInfoBoxText2();
            if(battleTimer > 0){
                battleTimer --;
            }
        }
     
    }
    public void gropigBattle(){
        if(OPTimer == 50){
            Sound.playSE("Audio Resources/_P_Powerup_2.wav");
            opacity1 = 0;
            opacity2 = 0;
            opacity3 = 0;
        }
        if(OPTimer < 100 && OPTimer > 0){
            fadeOut1();
            fadeOut2();
            fadeOut3();
            fadeOut4();
        }
        if(OPTimer == 99){
            battleTextTop = "Toby: The gropig is dangerous! Careful, Quintis!"; 
            setBattleground("Resources/Puppy Cave Close.png");
            setToby("Resources/Toby1.png");
            setQuintis("Resources/Quintis 4.png");
            setEnemy1("Resources/gropig.png");           
        }
        if(OPTimer >100){
            inBattle = true;
            placeEnemy1(200,0);       
            placeToby(10,700);
            placeQuintis(300,630);
            setBBox();
            setInfoBox();
            setInfoBoxText3();
            if(battleTimer > 0){
                battleTimer --;
            }
        }
     
    }
    
    
    
    
    /////////////////////////////////////////
    //
    //
    //
    // SINCE I AM TERRIBLE AT USING MULTIPLE CLASSES, ILL PUT THE ITEM INFO DOWN HERE.
    //
    //
    //
/////////////////////////////////////////////////////////////
    
    public int numberOfHealthPots = 2;
    public int numberOfManaPots = 1;
    
    
    
    
    
    
    
    
    
    // Enemy stuff down here
    public int slimebob1HP = 15;
    public int slimebob1DEF = 6;
    public int slimebob1DMG = 13;
    
    public int goldenSlimebobHP = 40;
    public int goldenSlimebobDEF = 9;
    public int goldenslimebobDMG = 15;
    
    public int gropigHP = 100;
    public int gropigDEF = 11;
    public int gropigDMG = 13;
    
    
    //level up is method is down here.
    public void levelUp(){
        if(PlayerStats.tLVL == 1){
            PlayerStats.tHP = 36;
            PlayerStats.tMP = 12;
            PlayerStats.tEXP = PlayerStats.tEXP - PlayerStats.tEXPThreshold;
            PlayerStats.tDEF = 7;
            PlayerStats.tATK = 17;
            PlayerStats.tEXPThreshold = 25; 
            PlayerStats.tcurrHP = PlayerStats.tHP;
            PlayerStats.tcurrMP = PlayerStats.tMP; 
            
        }
        if(PlayerStats.tLVL == 2){
            PlayerStats.tHP = 40;
            PlayerStats.tMP = 15;
            PlayerStats.tEXP = PlayerStats.tEXP - PlayerStats.tEXPThreshold;
            PlayerStats.tDEF = 8;
            PlayerStats.tATK = 18;
            PlayerStats.tEXPThreshold = 25;  
            PlayerStats.tcurrHP = PlayerStats.tHP;
            PlayerStats.tcurrMP = PlayerStats.tMP;
        }        
        PlayerStats.tLVL++;
    }
    
    //these are where i will put all the chapters.
    //chapter1A has texts 0 - 46.
    public void chapter1A(){
        OPTimer++;
               if(OPTimer <100 && currentText == 0){
                   //gets out of the last scene
                   Sound.fadeOutTrack();
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
               }
               
               if(OPTimer == 100 && currentText == 0){
                   //starts music and places things
                   Sound.playTrack("Audio Resources/The Vanishment of Haruhi Suzumiya OST - 01 - Itsumo no Fuukei Kara Hajimaru Monogatari.wav");
                   placePic1(0,0);
                   clearPic2();
                   clearPic3();
                   setPic1("Resources/Liveley Vernes.png");
               }
               
               if(OPTimer >= 100 && currentText == 0){
                   //makes everything move
                   setPic4();
                   setPic5();
                   fadeIn1();

                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       OPTimer = 0;
                       currentLetter = 0;
                   }
               }
               //
               //
               //
               if(currentText == 1 && OPTimer < 100){
                   fadeOut1();
                   fadeOut4();
               }
               if(currentText == 1 && OPTimer >= 100){
                   textSpeed = 7;                   
                   talker = 3;
                   fadeIn4();
                   setPic4();
                   setPic5();  
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               }
               //
               //
               //
               if(currentText == 2){
                   textSpeed = 7;
                   talker = 3;
                   setPic4();
                   setPic5();
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               }
               //
               //
               //
               if(currentText == 3){
                   textSpeed = 7;
                   talker = 3;
                   setPic4();
                   setPic5();
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               } 
               //
               //
               //
               if(currentText == 4){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 4;
                   setPic4();
                   setPic5();
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               }
               //
               //
               //
               if(OPTimer < 100 && currentText == 5){
                   setPic1("Resources/Scene 1-1.png");
                   fadeIn4();
                   fadeIn1();
               } 
               if(OPTimer >= 100 && currentText == 5){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;
                   setPic4();
                   setPic5();
                   //
                   //TAKLING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 10 == 1){
                           setPic1("Resources/Scene 1-1.png");
                       }
                       if(OPTimer % 10 == 6){
                           setPic1("Resources/Scene 1-3.png");                          
                       }
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               } 
               //
               //
               //
               if(currentText == 6){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 2;
                   setPic4();
                   setPic5();
                   //
                   //TAKLING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 10 == 1){
                           setPic1("Resources/Scene 1-1.png");
                       }
                       if(OPTimer % 10 == 6){
                           setPic1("Resources/Scene 1-2.png");                          
                       }
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic1("Resources/Scene 1-1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               }
               //
               //
               //
               if(currentText == 7){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;
                   setPic4();
                   setPic5();
                   //
                   //TAKLING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 10 == 1){
                           setPic1("Resources/Scene 1-1.png");
                       }
                       if(OPTimer % 10 == 6){
                           setPic1("Resources/Scene 1-3.png");                          
                       }
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic1("Resources/Scene 1-1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               }
               //
               //
               //
               if(currentText == 8 && OPTimer < 100){
                   fadeOut1();
                   fadeOut4();
               }
               if(currentText == 8 && OPTimer >= 100 && OPTimer < 150){
                   setPic1("Resources/Toby's Room.png");
                   setPic2("Resources/Toby5.png");
                   setPic3("Resources/Ruby1R.png");
                   placePic3(650,350);
                   placePic2(100,200);
                   fadeIn1();
                   fadeIn1();
                   fadeIn2();
                   fadeIn2();
                   fadeIn4();
                   fadeIn4();
                   fadeIn3();
                   fadeIn3();
                   
                   //setPic4();
                   //setPic5();                   
               }
               if(currentText == 8 && OPTimer >=150){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby5.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 9){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 2;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Ruby4R.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Ruby2R.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Ruby3R.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Ruby2R.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Ruby1R.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 10){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 11){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 2;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Ruby4R.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Ruby2R.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Ruby3R.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Ruby2R.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Ruby1R.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 12){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 13){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 2;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Ruby4R.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Ruby2R.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Ruby3R.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Ruby2R.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Ruby1R.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 14){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 15){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 16){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 4;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby9.png");                      
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 17){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 2;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Ruby4R.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Ruby2R.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Ruby3R.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Ruby2R.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Ruby1R.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }  
               //
               //
               //
               if(currentText == 18){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 2;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Ruby4R.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Ruby2R.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Ruby3R.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Ruby2R.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Ruby1R.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 19 && OPTimer<50){
                   fadeOut3();
               }

               if(currentText == 19 && OPTimer>= 50){
                   
                   opacity3 = 0;
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 5;   
                   setPic4();
                   setPic5();
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 20 && OPTimer < 100){
                   fadeOut1();
                   fadeOut2();
                   fadeOut4();
               }
               if(currentText == 20 && OPTimer >= 100 && OPTimer < 150){
                   setPic1("Resources/Toby's House.png");
                   setPic2("Resources/Toby1.png");
                   setPic3("Resources/Gramps1.png");
                   placePic3(650,350);
                   placePic2(100,200);
                   fadeIn1();
                   fadeIn1();
                   fadeIn2();
                   fadeIn2();
                   fadeIn4();
                   fadeIn4();
                   fadeIn3();
                   fadeIn3();
                   
               }
               if(currentText == 20 && OPTimer >=150){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 21){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 22){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 23){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby3 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 24){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 25){
                   talkingSE = 0;
                   textSpeed = 20;
                   talker = 3;  
                   setPic2("Resources/Toby Stunned.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;     
                    }                   
               }
               //
               //
               //
               if(currentText == 26){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps4.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 27){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby12.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 28){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 29){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 30){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby5.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 31){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 32){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby5.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 33){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps4.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 34){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby12.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 35){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 36){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 37){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby5.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 38){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps5.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 39){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 40){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 41){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 42){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gramps1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gramps2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gramps3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gramps2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gramps5.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                 
               }
               //
               //
               //
               if(currentText == 43 && OPTimer < 100){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();
               }
               if(currentText == 43 && OPTimer == 100){
                   setPic4H();
                   y6 = 250;
               }
               if(currentText == 43 && OPTimer >= 100 && OPTimer < 150){
                   setPic1("Resources/leaving house 1.png");
                   clearPic2();
                   clearPic3();
                   fadeIn1();
                   fadeIn1();
                   fadeIn4();
                   fadeIn4();
                   
               }
               if(currentText == 43 && OPTimer >=150){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4H();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 10 == 1){
                           setPic1("Resources/leaving house 1.png");
                       }
                       if(OPTimer % 10 == 6){
                           setPic1("Resources/leaving house 2.png");                          
                       }
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic1("Resources/leaving house 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 44){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4H();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 10 == 1){
                           setPic1("Resources/leaving house 1.png");
                       }
                       if(OPTimer % 10 == 6){
                           setPic1("Resources/leaving house 3.png");                          
                       }
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic1("Resources/leaving house 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 45){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4H();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 10 == 1){
                           setPic1("Resources/leaving house 1.png");
                       }
                       if(OPTimer % 10 == 6){
                           setPic1("Resources/leaving house 2.png");                          
                       }
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic1("Resources/leaving house 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 46){
                   talkingSE = 0;
                   textSpeed = 3;
                   talker = 6;  
                   setPic4H();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 10 == 1){
                           setPic1("Resources/leaving house 1.png");
                       }
                       if(OPTimer % 10 == 6){
                           setPic1("Resources/leaving house 3.png");                          
                       }
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic1("Resources/leaving house 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       //Sound.playTrack("Audio Resources/NO NOISE.wav");
                       currentLetter = 0;
                       OPTimer = 0;    
                       chapter1B = true;
                       chapter1A = false;
                    }                   
               }
               //
               //
               //
           }
    
    public void chapter1B(){
        OPTimer++;
               if(currentText == 47 && OPTimer < 100){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();
                   Sound.fadeOutTrack();
               }
               if(currentText == 47 && OPTimer == 100){
                   setPic4();
                   Sound.stopTrack();
                   placePic2(100,200);
                   placePic3(1600,200);
                   Sound.playBattleTrack("Audio Resources/Persona 4 ost - Your Affection [Extended].wav");
                   
                   y6 = 1150;
               }
               if(currentText == 47 && OPTimer >= 100 && OPTimer < 200){
                   setPic1("Resources/Liveley Vernes.png");
                   setPic2("Resources/Toby1.png");
                   setPic3("Resources/Gee 1.png");
                   fadeIn1();
                   fadeIn2();
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4();
                   
               }
               if(currentText == 47 && OPTimer >=150){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 7;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 48){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 8;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 49){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
               }
               if(currentText == 49 && OPTimer < 110){
                   movePic3(13,0);
               }
               if(currentText == 49 && OPTimer > 110){
                   setPic2("Resources/Toby Hit.png");
                   setPic3("Resources/Gee shocked.png");
                   setPic4();
                   setPic5();
               }
               if(currentText == 49 && OPTimer < 135 && OPTimer >110){
                   movePic3(-13,0);
               }
               //this line is written so bad. I would change it, but im lazy.
               if(currentText == 49 && currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1 && OPTimer >135){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
               }
               //
               //
               //
               if(currentText == 50){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 51){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 4;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gee 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gee 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gee 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gee 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gee 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 52){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 4;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Gee 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Gee 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Gee 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Gee 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Gee 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 53 && OPTimer < 100){
                   movePic3(13, 0);
               }
               if(currentText == 53 && OPTimer >50){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4R.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2R.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3R.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2R.png");                          
                       }                       
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 54){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4R.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2R.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3R.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2R.png");                          
                       }                       
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 55){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 9;  
                   setPic2("Resources/Toby Health Pot R.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 56){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 10;  
                   setPic2("Resources/Toby1.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 57){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4R.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2R.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3R.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby4R.png");                          
                       }                       
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 58){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 11;  
                   setPic2("Resources/Toby1.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText = 148;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 148){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 12;  
                   setPic2("Resources/Toby1.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText = 149;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 149){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 13;  
                   setPic2("Resources/Toby1.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText = 147;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 147 && OPTimer == 1){
                   //Sound.playTrack("Audio Resources/NO NOISE.wav");
               }
               if(currentText == 147){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 16;  
                   setPic2("Resources/Toby1.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText = 59;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 59 && OPTimer < 90){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();
                   Sound.fadeOutTrack();
               }
               if(currentText == 59 && OPTimer == 50){
                   opacity1 = 0;
                   opacity2 = 0;
                   opacity3 = 0;
                   
               }
               if(currentText == 59 && OPTimer == 100){
                   Sound.stopTrack();
                   placePic2(150,200);
                   placePic3(1800,300);
                   Sound.playTrack("Audio Resources/Epic Battle Fantasy IV Music Weshdoor Concert [Extended].wav");
                   
               }
               if(currentText == 59 && OPTimer >= 100 && OPTimer < 160){
                   setPic1("Resources/battleground.png");
                   setPic2("Resources/Toby1.png");
                   setPic3("Resources/slimebob.png");
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4();
                   
               }
               if(currentText == 59 && OPTimer >=170){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 60){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 61){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
               }
               if(currentText == 61 && OPTimer < 90){
                   movePic3(13,0);
               }

               //this line is written so bad. I would change it, but im lazy.
               if(currentText == 61 && currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1 && OPTimer >90){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
               }
               //
               //
               //
               if(currentText == 62){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 63){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby8.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 64){
                   slimebobBattle();
               }
               //
               //
               //
               if(currentText == 65 && OPTimer == 0){
                   Sound.playTrack("Audio Resources/Epic Battle Fantasy IV Music Weshdoor Concert [Extended].wav");
                   placePic3(2000,300);
               }
               if(currentText == 65 && OPTimer < 80){
                   setPic1("Resources/battleground.png");
                   setPic2("Resources/Toby1.png");
                   setPic3("Resources/slimebob.png");
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4(); 
               }
               if(currentText == 65 && OPTimer > 80){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby8.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 66){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   placePic3(800,300);
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby8.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 67){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby8.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 68){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby8.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 69){
                   slimebobBattle();
               }
               //
               //
               //
               if(currentText == 70 && OPTimer == 0){
                   Sound.playTrack("Audio Resources/Epic Battle Fantasy IV Music Weshdoor Concert [Extended].wav");
                   clearPic3();
               }
               if(currentText == 70 && OPTimer < 80){
                   setPic1("Resources/battleground.png");
                   setPic2("Resources/Toby1.png");
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4(); 
               }
               if(currentText == 70 && OPTimer > 80){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 71){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 72){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 73){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 74){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 75 && OPTimer < 90){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();
                   Sound.fadeOutTrack();
               }
               if(currentText == 75 && OPTimer == 50){
                   opacity1 = 0;
                   opacity2 = 0;
                   opacity3 = 0;
                   
               }
               if(currentText == 75 && OPTimer == 100){
                   Sound.stopTrack();
                   placePic2(100,200);
                   placePic3(700,200);
                   Sound.playBattleTrack("Audio Resources/Persona 4 OST Shoji Meguro - Heartbeat Heartbreak Extended HD.wav");
                   
               }
               if(currentText == 75 && OPTimer >= 100 && OPTimer < 160){
                   setPic1("Resources/Puppy Cave.png");
                   setPic2("Resources/Toby1.png");
                   setPic3("Resources/Quintis 4.png");
                   fadeIn1();
                   fadeIn2();
                   fadeIn4();
                   
               }
               if(currentText == 75 && OPTimer >=170){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 14;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 76){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 4; 
                   setPic2("Resources/Toby Impressed.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 77 && OPTimer <50){
                   fadeIn3();
                   fadeIn3();
               }
               if(currentText == 77 && OPTimer > 70){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 4;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 78){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 79){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 4;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 80){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 4;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 81){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 82){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 83){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 84){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby Impressed.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 85){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Happy 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Happy 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Happy 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 86){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 87){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }if(currentText == 88){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Vexed 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Vexed 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Vexed 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 89){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Vexed 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Vexed 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Vexed 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 90){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby12.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 91){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic3("Resources/Quintis 1.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 92){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 93){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 94){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 95){
                   talkingSE = 4;
                   textSpeed = 4;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 2.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 96){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic3("Resources/Quintis Danger.png");
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby5.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 97){
                   goldenSlimebobBattle();
               }
               //
               //
               //
               if(currentText == 98 && OPTimer == 0){
                   Sound.playTrack("Audio Resources/Epic Battle Fantasy IV Music Weshdoor Concert [Extended].wav");
               }
               if(currentText == 98 && OPTimer < 80){
                   setPic1("Resources/Puppy Cave.png");
                   setPic2("Resources/Toby1.png");
                   setPic3("Resources/Quintis Surprised 1.png");
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4(); 
               }
               if(currentText == 98 && OPTimer > 80){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Surprised 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Surprised 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Surprised 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 99){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Surprised 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Surprised 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Surprised 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 100){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               //
               //
               //
               if(currentText == 101){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 102){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 103){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Happy 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Happy 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Happy 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 104){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               //
               //
               //
               if(currentText == 105 && OPTimer < 90){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();
               }
               if(currentText == 105 && OPTimer == 50){
                   opacity1 = 0;
                   opacity2 = 0;
                   opacity3 = 0;
                   
               }
               if(currentText == 105 && OPTimer == 100){
                   placePic2(50,200);
                   placePic3(500,200);
                   
               }
               if(currentText == 105 && OPTimer >= 100 && OPTimer < 160){
                   setPic1("Resources/Puppy Cave Close.png");
                   setPic2("Resources/Toby1.png");
                   setPic3("Resources/Quintis 1.png");
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4();
                   
               }
               if(currentText == 105 && OPTimer >=170){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 15;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 106){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               //
               //
               //
               if(currentText == 107){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 108){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Happy 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Happy 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Happy 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 109){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 110){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 111){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 112){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 113){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 114){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Vexed 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Vexed 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Vexed 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 115){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Happy 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Happy 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Happy 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 116){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23; 
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Happy 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Happy 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Happy 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 117){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 118){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby1 Happy.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3 Happy.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2 Happy.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4 Happy.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 119){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Vexed 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Vexed 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Vexed 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 120){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby9.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 121 && OPTimer < 90){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();
                   Sound.fadeOutTrack();
               }
               if(currentText == 121 && OPTimer == 50){
                   opacity1 = 0;
                   opacity2 = 0;
                   opacity3 = 0;
                   
               }
               if(currentText == 121 && OPTimer == 100){
                   clearPic2();
                   clearPic3();
                   Sound.playTrack("Audio Resources/Life Will Change [With Lyrics] - Persona 5.wav");
                   
               }
               if(currentText == 121 && OPTimer >= 100 && OPTimer < 160){
                   setPic1("Resources/final boss.png");
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4();
                   
               }
               if(currentText == 121 && OPTimer >=170){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 122){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 123){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 124){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 125){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 126){
                   gropigBattle();
               }
               if(currentText == 127 && OPTimer ==0){
                   setPic1("Resources/Puppy Cave Close Gropig.png");
                   setPic2("Resources/Toby12.png");
                   setPic3("Resources/Quintis Vexed 1.png");
                   placePic2(50,200);
                   placePic3(500,200);
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4(); 
               }
               if(currentText == 127 && OPTimer < 80){
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4(); 
               }
               if(currentText == 127 && OPTimer > 80){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Vexed 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Vexed 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Vexed 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 128){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 129){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Vexed 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Vexed 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Vexed 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               //
               //
               //
               if(currentText == 130){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby12.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 131){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby12.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 132){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Surprised 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Surprised 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Surprised 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 133){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby12.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 134 && OPTimer < 90){
                   fadeOut2();
                   
                   Sound.fadeOutTrack();
               }
               if(currentText == 134 && OPTimer == 50){
                   Sound.playSE("Audio Resources/_P_Powerup.wav");
                   opacity2 = 0;
               }
               if(currentText == 134 && OPTimer == 90){
                   opacity1 = 0;
                   opacity2 = 0;
                   opacity3 = 0;
                   
               }
               if(currentText == 134 && OPTimer == 100){
                   placePic2(50,200);
                   Sound.stopTrack();
                   placePic3(700,200);
                   
               }
               if(currentText == 134 && OPTimer >= 100 && OPTimer < 160){
                   clearPic1();
                   setPic3("Resources/Sigma 1.png");
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4();
                   
               }
               if(currentText == 134 && OPTimer >=170){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby12.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 135){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 136){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 4;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 137){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 138){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 18;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 139){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 140){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 24;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 141){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby5.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 142){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 24;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 143){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 24;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 144){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 145){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 24;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 146){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 24;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText = 150;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 150){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby4.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 151){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 24;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma Mad 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma Mad 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma Mad 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma Mad 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma Mad 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText ++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 152){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby12.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 153){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby12.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 154){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 24;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText ++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 155){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 24;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText ++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 156){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 157){
                   talkingSE = 2;
                   textSpeed = 3;
                   talker = 24;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Sigma 4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Sigma 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Sigma 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Sigma 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Sigma 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText ++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 158 && OPTimer <100){
                   movePic3(+6,0);
               }
               if(currentText == 158 && OPTimer >100 && OPTimer <150){
                   fadeOut3();
                   fadeOut3();
               }
               if(currentText == 158 && OPTimer == 140){
                   opacity3 = 0;
               }
               if(currentText == 158 && OPTimer > 150){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;   
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby9.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby10.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby11.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby10.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby9.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 159 && OPTimer < 90){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();

               }
               if(currentText == 159 && OPTimer == 50){
                   opacity1 = 0;
                   opacity2 = 0;
                   opacity3 = 0;
                   Sound.playSE("Audio Resources/_P_Powerup.wav");
                   
               }
               if(currentText == 159 && OPTimer == 100){
                   setPic1("Resources/Puppy Cave Close Gropig.png");
                   setPic2("Resources/Toby8.png");
                   placePic2(50,200);
                   setPic3("Resources/Quintis Surprised 1.png");
                   placePic3(500,200);
                   
               }
               if(currentText == 159 && OPTimer >= 100 && OPTimer < 160){
                   
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4();
                   
               }
               if(currentText == 159 && OPTimer >=170){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 160){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Vexed 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Vexed 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Vexed 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Vexed 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }  
               }
               if(currentText == 161){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Surprised 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Surprised 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Surprised 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 162){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 163){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Toby8.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Toby6.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Toby7.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Toby6.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Toby8.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 164 && OPTimer < 50){
                   fadeOut2();
               }
               if(currentText == 164 && OPTimer == 50){
                   setPic2("Resources/Sigma Toby1.png");
               }
               if(currentText == 164 && OPTimer >50 && OPTimer < 100){
                   fadeIn2();
               }
               if(currentText == 164 && OPTimer > 100){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 3;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Sigma Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Sigma Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Sigma Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Sigma Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Sigma Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && OPTimer == 160){
                       Sound.playLoudTrack("Audio Resources/Persona 5 -  Wake up Get up Get CUT.wav");
                    } 
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && OPTimer > 220){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 165 && OPTimer < 90){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();

               }
               if(currentText == 165 && OPTimer == 50){
                   opacity1 = 0;
                   opacity2 = 0;
                   opacity3 = 0;
                   opacity4 = 0;

                   
               }
               if(currentText == 165 && OPTimer == 100){
                   placePic1(0,0);
                   placePic2(120,400);
                   placePic3(300,100);
                   setPic1("Resources/battleground.png");
                   setPic2("Resources/Flying Toby.png");
                   setPic3("Resources/gropig.png");
                   
               }
               if(currentText == 165 && OPTimer >= 100 && OPTimer < 160){
                   
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();

                   
               }
               if(currentText == 165 && OPTimer >170 && OPTimer <360){
                   movePic2(0,2);
               }
               if(currentText == 165 && OPTimer == 365){
                   clearPic2();
                   clearPic3();
                   setPic1("Resources/final 1.png");
               }
               if(currentText == 165 && OPTimer > 376 && OPTimer < 600){
                   if(OPTimer % 30 == 1){
                       
                       setPic1("Resources/final 2.png");
                   }
                   if(OPTimer % 30 == 20){
                       Sound.playLoudBattleSE("Audio Resources/sword sound.wav");
                       setPic1("Resources/final 3.png");                          
                   }
                   if(OPTimer %30 == 25){
                       Sound.playLoudBattleSE("Audio Resources/Spell1.wav");
                   }
               }
               if(currentText == 165 && OPTimer == 610){
                   clearPic2();
                   clearPic3();
                   setPic1("Resources/final 2.png");
               }
               if(currentText == 165 && OPTimer == 620){
                   clearPic2();
                   clearPic3();
                   setPic1("Resources/final 1.png");
               }
               if(currentText == 165 && OPTimer == 630){
                   placePic1(0,0);
                   placePic2(120,100);
                   placePic3(300,100);
                   setPic1("Resources/battleground.png");
                   setPic2("Resources/Flying Toby.png");
                   setPic3("Resources/gropig.png");
               }
               if(currentText == 165 && OPTimer >640 && OPTimer <850){
                   movePic2(0,-2);
               }
               if(currentText == 165 && OPTimer == 860){
                   Sound.playLoudBattleSE("Audio Resources/shing.wav");
                   setPic2("Resources/Flying Toby 2.png");
               }
               if(currentText == 165 && OPTimer == 870){
                   setPic2("Resources/Flying Toby 3.png");
               }
               if(currentText == 165 && OPTimer ==950){
                   Sound.playLoudBattleSE("Audio Resources/fatal.wav");
                   setPic3("Resources/gropig really dead.png");
               }
               
               if(currentText == 165 && OPTimer < 1290 && OPTimer > 1200){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();

               }
               if(currentText == 165 && OPTimer == 1260){
                   opacity1 = 0;
                   opacity2 = 0;
                   opacity3 = 0;
                   opacity4 = 0;

                   
               }
               if(currentText == 165 && OPTimer == 1300){
                   setPic1("Resources/Puppy Cave Close.png");
                   setPic2("Resources/Sigma Toby1.png");
                   placePic2(50,200);
                   setPic3("Resources/Quintis Surprised 1.png");
                   placePic3(500,200);
                   
               }
               if(currentText == 165 && OPTimer >= 1300 && OPTimer < 1360){
                   
                   fadeIn1();
                   fadeIn2();
                   fadeIn3();
                   fadeIn4();
               }
               if(currentText == 165 && OPTimer >= 1320 && OPTimer < 1330){
                   
                   Sound.fadeOutTrack();
               }
               if(currentText == 165 && OPTimer > 1380){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Surprised 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Surprised 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Surprised 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               }
               if(currentText == 166){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 17;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Sigma Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Sigma Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Sigma Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Sigma Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Sigma Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 167){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Surprised 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Surprised 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Surprised 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Surprised 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               }
               if(currentText == 168){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 17;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Sigma Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Sigma Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Sigma Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Sigma Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Sigma Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 169){
                   talkingSE = 4;
                   textSpeed = 3;
                   talker = 23;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic3("Resources/Quintis Happy 1.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic3("Resources/Quintis Happy 3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic3("Resources/Quintis Happy 2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic3("Resources/Quintis Happy 1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }
               }
               if(currentText == 170){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 17;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   if(currentLetter < AllStoryText.text[currentText].length()+1){
                       if(OPTimer % 16 >= 0 && OPTimer % 16 < 4){
                           setPic2("Resources/Sigma Toby4.png");
                       }
                       if(OPTimer % 16 >= 4 && OPTimer % 16 < 8){
                           setPic2("Resources/Sigma Toby2.png");                          
                       }
                       if(OPTimer % 16 >= 8 && OPTimer % 16 < 12){
                           setPic2("Resources/Sigma Toby3.png");                          
                       }
                       if(OPTimer % 16 >= 12){
                           setPic2("Resources/Sigma Toby2.png");                          
                       }                       
                   }
                   if(currentLetter == AllStoryText.text[currentText].length()+1){
                       setPic2("Resources/Sigma Toby1.png");
                   }
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 171){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 19;  
                   setPic4();
                   setPic3("Resources/Quintis 4.png");
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 172){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 20;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 173){
                   talkingSE = 1;
                   textSpeed = 3;
                   talker = 21;  
                   setPic4();
                   setPic5();
                   //
                   //TALKING BONUS
                   
                   //
                   if(currentLetter == AllStoryText.text[currentText].length()+1 && keyPressed == 1){
                      Sound.playSE("Audio Resources/_P_cursor_2G.wav");
                       clearPic5();
                       currentText++;
                       currentLetter = 0;
                       OPTimer = 0;                       
                    }                   
               }
               if(currentText == 174 && OPTimer < 90){
                   fadeOut1();
                   fadeOut2();
                   fadeOut3();
                   fadeOut4();
                   Sound.fadeOutTrack();

               }
               if(currentText == 174 && OPTimer == 60){
                   opacity1 = 0;
                   opacity2 = 0;
                   opacity3 = 0;
                   opacity4 = 0;

                   
               }
               if(currentText == 174 && OPTimer == 95){
                   setPic1("Resources/ending.png");
                   clearPic2();
                   clearPic3();
                   
               }
               if(currentText == 174 && OPTimer >= 100){
                   
                   fadeIn1();

               }
               
                   
               
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
            
}

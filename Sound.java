
package toby2;
import sun.audio.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;



public class Sound extends Thread{
    public static Clip clip;
    public static Clip clipSE;

    @Override 
    public void run(){ 
        playTrack("Audio Resources/Nape_Mango_flew_into_a_meme_wormhole_and_hasn_39_t.wav");

            
    }
    
    public static void playSE(String s){

        try{

            File file = new File(s);
            clipSE = AudioSystem.getClip();
            clipSE.open(AudioSystem.getAudioInputStream(file));
            FloatControl gainControl = (FloatControl) clipSE.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            clipSE.start();

            
            //Thread.sleep(clip.getMicrosecondLength());
        }
        catch(Exception e){
            System.out.println("could not find " + s);
        }
                
    }
    public static void playTrack(String s){

        try{

            File file = new File(s);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);

            
            //Thread.sleep(clip.getMicrosecondLength());
        }
        catch(Exception e){
            System.out.println("could not find " + s);
        }
        
    }
    public static void stopTrack(){
        clip.stop();
    }
    
    public static void fadeOutTrack(){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        if(gainControl.getValue()>=-79)
        gainControl.setValue(gainControl.getValue()-1);
    }
    
    
    public static void playBattleTrack(String s){

        try{

            File file = new File(s);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);

            
            //Thread.sleep(clip.getMicrosecondLength());
        }
        catch(Exception e){
            System.out.println("could not find " + s);
        }
        
    }   
    
    public static void playBattleSE(String s){

        try{

            File file = new File(s);
            clipSE = AudioSystem.getClip();
            clipSE.open(AudioSystem.getAudioInputStream(file));
            FloatControl gainControl = (FloatControl) clipSE.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clipSE.start();

            
            //Thread.sleep(clip.getMicrosecondLength());
        }
        catch(Exception e){
            System.out.println("could not find " + s);
        }
                
    }
    public static void playLoudBattleSE(String s){

        try{

            File file = new File(s);
            clipSE = AudioSystem.getClip();
            clipSE.open(AudioSystem.getAudioInputStream(file));
            FloatControl gainControl = (FloatControl) clipSE.getControl(FloatControl.Type.MASTER_GAIN);
            clipSE.start();

            
            //Thread.sleep(clip.getMicrosecondLength());
        }
        catch(Exception e){
            System.out.println("could not find " + s);
        }
                
    }
    public static void playLoudTrack(String s){

        try{

            File file = new File(s);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);
            gainControl.setValue(-5.0f);

            
            //Thread.sleep(clip.getMicrosecondLength());
        }
        catch(Exception e){
            System.out.println("could not find " + s);
        }
        
    } 

}


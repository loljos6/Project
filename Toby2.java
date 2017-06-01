
//JUST SO YOU KNOW THIS IS NOT FINISHED WHATSOEVER.
//NOT EVEN CLOSE
//THIS IS JUST TO HAVE SOMETHING ON THE FILE SHARE THING.
package toby2;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;


public class Toby2{
    //this is how i am going to make the key pressed thing.
    //if space bar is pressed keyPressed = 1.
    //i will write code for the other keys as the time comes. 

    
    public Toby2(){
     
    }

    public static void main(String[] args)throws InterruptedException{
        AllStoryText initText = new AllStoryText();
        initText.setText();
        GraphicsAndBackgrounds introBackground = new GraphicsAndBackgrounds();
        JFrame frame = new JFrame("Toby");
        frame.add(introBackground);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(1280,1280);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        //add(canvas);
        //canvas.createBufferStrategy(3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Sound sound = new Sound();

        introBackground.placePic3(1280,600);

     
        long lastTime = System.nanoTime();
        double nanoSecondConversion = 1000000000.0/60;
        double changeInSeconds = 0;
        //Sound.playTrack("Audio Resources/NO NOISE.wav");
        while(true){
            long now = System.nanoTime();
            changeInSeconds += (now-lastTime)/nanoSecondConversion;
            while(changeInSeconds >= 1){
                introBackground.update();
                //System.out.println(changeInSeconds);
                //System.out.println(introBackground.battleTimer);
                changeInSeconds = 0;                
            }
            introBackground.draw();
            lastTime = now;
        } 

    }
    


    public void run(){
        //BufferStrategy bufferStrategy= canvas.getBufferStrategy();
        int i = 0;
        int x = 0;
        GraphicsAndBackgrounds introBackground = new GraphicsAndBackgrounds();   
        long lastTime = System.nanoTime();
        double nanoSecondConversion = 1000000000.0/60;
        double changeInSeconds = 0;
        
        while(true){
            long now = System.nanoTime();
            changeInSeconds += (now-lastTime)/nanoSecondConversion;
            while(changeInSeconds >= 1){
                introBackground.update();
                System.out.println(changeInSeconds);
                changeInSeconds = 0;                
            }
            introBackground.draw();
            lastTime = now;

        }
        
            
        }
}

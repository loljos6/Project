
package toby2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Render {
    private BufferedImage view;
    
    public Render(int width, int height){
        view = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
    }
    
    public void setPic(String location){
        try{
            view = ImageIO.read(new File(location)); 
        }
        catch(IOException e){
            System.out.println("image at" + location + "could not be found. :(");
        }
    }
    
    public void doRender(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(view,0,0,null);
    }
  
    
    
}

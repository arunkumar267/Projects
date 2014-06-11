
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Receive1 implements Runnable
{
    Thread t;
    boolean flag=false;
    Receive1()
    {
        t=new Thread(this);
        t.start();
    }
    public void run()
    {
        while(true)
        {
        try {
            ServerSocket listener = new ServerSocket(8888);
                    try {
                while (true) {
                    Socket socket = listener.accept();
                    if(flag==false)
                    {
                                 flag=true;
                         Desktop desktop = null;
      if (Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }
       desktop.open(new File("D:\\screenShot.png"));
                    }                 
                    try {
    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    byte[] buffer = (byte[])ois.readObject();
    FileOutputStream fos = new FileOutputStream("D:/screenShot.png");
    fos.write(buffer);
    fos.close();
                        
    System.out.println("After Call");
    //image=null;

            }               catch (ClassNotFoundException ex) {
    
            } finally {
                        socket.close();
                    }
                }
            }
            finally {
                listener.close();
            }
        }
        catch (IOException ex) {
             }
    }
    }
}
public class Server {
       
    public static void main(String args[]) 
    {
    Receive1 R=new Receive1();     
    }
}

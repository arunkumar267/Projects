/* ShowImage.java

  This program loads and displays an image from a file.

  mag-13May2008
  updated 20Feb2009 by mag to incorporate suggestions
  by mazing and iofthestorm on digg.
*/

// Import the basic graphics classes.
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class ShowImag extends JPanel{
  Image image; // Declare a name for our Image object.
int i=10,j=20,k=200,l=200;
// Create a constructor method
 
// The following methods are instance methods.

/* Create a paintComponent() method to override the one in
JPanel.
This is where the drawing happens.
We don't have to call it in our program, it gets called
automatically whenever the panel needs to be redrawn,
like when it it made visible or moved or whatever.
*/
  public void paint(Graphics g){

   // Draw our Image object.

      
image = Toolkit.getDefaultToolkit().getImage("D:\\screenShot.jpg");
   g.drawImage(image,i,j,k,l, this); // at location 50,10
   System.out.print("inside");
      g.drawString("hello",500,500);
      
     // 200 wide and high
  }
  public void getFile() throws IOException, ClassNotFoundException
  {
       ServerSocket listener = new ServerSocket(9093);
        	try {
            while (true) {
                Socket socket = listener.accept();
                
                try {
                   // File f=new File("D:/screenShot.jpg");
                    //f.delete();
                    //PrintWriter out =
                      //new PrintWriter(socket.getOutputStream(), true);
                    //out.println("hello");
ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
byte[] buffer = (byte[])ois.readObject();


FileOutputStream fos = new FileOutputStream("D:/screenShot.jpg",true);
fos.write(buffer);

System.out.println("After Call");
//image=null;
image = Toolkit.getDefaultToolkit().getImage("D:/screenShot.jpg");
removeAll();
this.invalidate();
repaint();

				} finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
       
  }

  public static void main(String arg[]) throws IOException, ClassNotFoundException{
   JFrame frame = new JFrame("ShowImage");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setSize(1000,800);

   ShowImag panel = new ShowImag();
   frame.setContentPane(panel); 
   
   frame.setVisible(true); 
   panel.getFile();
  }
}
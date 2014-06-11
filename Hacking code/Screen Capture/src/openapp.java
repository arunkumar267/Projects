
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elcot
 */
public class openapp {
           
    public static void main(String arg[]) throws IOException
    {
      Desktop desktop = null;
      if (Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }
   desktop.open(new File("C:\\Users\\Elcot\\Desktop\\bala hacking\\Client.exe"));
   System.out.println("outttttt");
    }
            
}

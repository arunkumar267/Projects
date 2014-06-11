import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class JFileChooserSelectionOption {

  public static void main(String[] a) {
    JFileChooser fileChooser = new JFileChooser(".");

    fileChooser.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Action");

      }
    });
    
    int status = fileChooser.showOpenDialog(null);



    if (status == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      System.out.println(selectedFile.getParent());
      System.out.println(selectedFile.getName());
    } else if (status == JFileChooser.CANCEL_OPTION) {
      System.out.println("calceled");

    }
  }

}
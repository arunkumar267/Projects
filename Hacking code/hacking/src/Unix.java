/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elcot
 */
public class Unix {
    
  public static void main(String arg[])
    {
  
        for(int i=0;i<=10;i++)
        {
            for(int j=0;j<=70;j++)
            {
                if(i==0||i==7||i==10)
                {
                    System.out.print("!");
                            
                }
                else if(j==0||j==70)
                {
                    System.out.print("$");
                            
                }
                else
                   System.out.print(" ");
                     
            }
            System.out.println("");
        }
        
    }
    
}


import java.io.BufferedReader;
import java.io.InputStreamReader;


class listprocess
{
    
public static void main(String arg[])
{
try {
        String line;
        String t="netstat.exe -noa";
        String j="kk";
        
        System.out.println("String"+j);
        Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+t);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line); //<-- Parse data here.
        }
        input.close();
    } catch (Exception err) {
        err.printStackTrace();
    }
}   
}

//to display the proceees which using TCP/UDP
//netstat -noa  
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;  
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ClientSend {  
  Socket sock; 
  boolean flag=true,con=false;          
   DataInputStream clientData;  
    InputStream in;  
 InetAddress ia,ib;
        
    /** 
     * @param args 
     */  
    
public void connect()throws IOException, InterruptedException
 {
 
    try
    {
ia=InetAddress.getByName("Bala");      
sock = new Socket(ia.getHostAddress(),21);  //replace with your remote host static IP address.  
System.out.println("Connecting.........");   
    port();
    con=true;

chooseOpt();
    }
    catch(Exception j)
    {
     System.out.println("Exception Inside Connect..\n\n");   
     connect();
    }
}       


public void port() throws IOException
{
        try {
               System.out.println("Port Assigning..\n\n");   
              ib=InetAddress.getLocalHost();
               OutputStream os = sock.getOutputStream();    
              DataOutputStream dos = new DataOutputStream(os);   
              dos.writeUTF(ib.getHostName());
                dos.writeUTF(ib.getHostAddress());
        } catch (Exception ex) {
            try {
                connect();
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex1) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
   
}

public void chooseOpt() throws IOException, InterruptedException
{
    try
    {
    in = sock.getInputStream(); //used    
    clientData = new DataInputStream(in); //use     
String ch=clientData.readUTF();  
 //System.out.println("received: "+ch);
  
 if(ch.startsWith("List Drives",0))
{
    listDrives();
    sock.close();
    connect();
}

else if(ch.startsWith("List Directory",0))
{
    
    String ss[]=ch.split(" ");
   // System.out.println("lllllll: "+ss[2]);  
    String nam = ss[2];
    File aFile = new File(nam);
     listDirectory(aFile);
    sock.close();
    connect();
}
else if(ch.startsWith("List Files",0))
{
    
    String ss[]=ch.split(" ");
    //System.out.println("ffffff: "+ss[2]);  
    String nam = ss[2];
    File aFile = new File(nam);
     listFiles(aFile);
    sock.close();
    connect();
}

else if(ch.startsWith("Receive Files",0))
{
    char a[]=ch.toCharArray();
    int len=a.length;
    String cat="";   
//System.out.println("len"+len);

    for(int i=14;i<=len-1;i++)
    {
    System.out.println(a[i]);
    cat=cat+""+a[i];
    }
    
    //System.out.println("nnnn: "+cat);  
    sock.close();
    sendFile(cat);
    connect();
}


else if(ch.startsWith("Modify File",0))
{
      
char a[]=ch.toCharArray();
int len=a.length;
String cat="";   
//System.out.println("len"+len);
for(int i=12;i<=len-1;i++)
{
    System.out.println(a[i]);
    cat=cat+""+a[i];
}
    
System.out.println("nnnn: "+cat);  
//    sock.close();
    modifyFile(cat);
    connect();
}


else if(ch.startsWith("Disconnect",0))
{
System.exit(0);    
}



    }
    catch(Exception f)
    {
  //      System.out.println("Exception Inside Option..");   
        connect();

    }
    
    
    
    
}

public void listDrives() throws IOException, InterruptedException
{
    try
    {
            OutputStream os = sock.getOutputStream();    
            DataOutputStream dos = new DataOutputStream(os);   
            File[] Files = File.listRoots();
       //     System.out.println("NO of files: "+Files.length);
            dos.writeInt(Files.length);  

            for (int i = 0; i < Files.length; i++)
            {
                 dos.writeUTF(Files[i].toString());  
         //        System.out.println("Drive:" + Files[i]);
            }
    }
    catch(Exception l)
     {
   // System.out.println("Exception inside listDrives\n\n");   
    connect();
    }
    
            
}

public void listDirectory(File aFile) 
{
    try
    {
    
    String path[]=new String[200];
    
    
     OutputStream os = sock.getOutputStream();    
     DataOutputStream dos = new DataOutputStream(os);   
        
       
    
   int spc_count=-1;
    spc_count++;
	int pathcount=0;
	String files;
	String spcs = "";
    for (int i = 0; i < spc_count; i++)
      spcs += " ";
    if(aFile.isFile())
	{
     // System.out.println(spcs + "[FILE] " + aFile.getName());
	  
	  files = aFile.getName();
       if (files.endsWith(".docx") || files.endsWith(".txt"))
       {
       // System.out.println("doc file name: "+ files);
	  path[pathcount]=aFile.getPath();
	 //System.out.println("\tPath " + path[pathcount]);
	  System.out.println();
          pathcount++;
	  
	  }
   
	  }
	  
    else if (aFile.isDirectory()) {
      //System.out.println(spcs + "[DIR] " + aFile.getName());
               dos.writeUTF(aFile.getName());  
      
      File[] listOfFiles = aFile.listFiles();
      if(listOfFiles!=null) {
        for (int i = 0; i < listOfFiles.length; i++)
          listDirectory(listOfFiles[i]);
      } else {
        //System.out.println(spcs + " [ACCESS DENIED]");
      }
    }
    spc_count--;

    }
    catch(Exception e)
    {
   // System.out.println("Exception occured..");
            try {
                connect();
            } catch (UnknownHostException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}


public void listFiles(File aFile)
{
    
    try
    {
    String path[]=new String[200];
    
    
     OutputStream os = sock.getOutputStream();    
     DataOutputStream dos = new DataOutputStream(os);   
        
       
    
   int spc_count=-1;
    spc_count++;
	int pathcount=0;
	String files;
	String spcs = "";
    for (int i = 0; i < spc_count; i++)
      spcs += " ";
    if(aFile.isFile())
	{
      //System.out.println(spcs + "[FILE] " + aFile.getName());
	  
	  files = aFile.getName();
                
          
          if (files.endsWith(".docx") || files.endsWith(".txt"))
       {
        //System.out.println("doc file name: "+ files);
	  path[pathcount]=aFile.getPath();
          dos.writeUTF(aFile.getPath());  
      
	 //System.out.println("\tPath " + path[pathcount]);
	  System.out.println();
          pathcount++;
	  
	  }
   
	  }
	  
    else if (aFile.isDirectory()) {
      //System.out.println(spcs + "[DIR] " + aFile.getName());
      
      File[] listOfFiles = aFile.listFiles();
      if(listOfFiles!=null) {
        for (int i = 0; i < listOfFiles.length; i++)
          listFiles(listOfFiles[i]);
      } else {
        System.out.println(spcs + " [ACCESS DENIED]");
      }
    }
    spc_count--;
    }
    catch(Exception e)
    {
        System.out.println("Exception inside list files");
            try {
                connect();
            } catch (UnknownHostException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
}



public void sendFile(String cat) {
    
    try
    {
    
   sock = new Socket(ia.getHostAddress(),21);  //replace with your remote host static IP address.  
            System.out.println("Connecting.........");  
              
            File myFile = new File(cat);
            File[] list=myFile.listFiles();
            File[] Files=new File[100];
            String endwith;
            int inc=0;
            for (int count=0;count<list.length;count ++){  
                 // dos.writeUTF(Files[count].getName());  
                  endwith=list[count].getName();
                  if(endwith.endsWith(".docx")||endwith.endsWith(".txt"))
                  {
                      System.out.println(".docx ff: "+endwith);
                       Files[inc]=list[count]; 
                       inc++;
                  }
                  else
                      System.out.println("not ff: "+endwith);
                      
                  
                System.out.println("Document Sending..");
            }  
           
            
            
              
               
            OutputStream os = sock.getOutputStream();    
            DataOutputStream dos = new DataOutputStream(os);   
             
            dos.writeInt(inc);  
              
            for (int count=0;count<inc;count ++)
            {  
            dos.writeUTF(Files[count].getName());  
            }  
            for (int count=0;count<inc;count ++)
            {  
             int filesize = (int) Files[count].length();  
             dos.writeInt(filesize);  
            System.out.println("file Send..");
            }  
              
            for (int count=0;count<inc;count ++){  
            int filesize = (int) Files[count].length();  
            byte [] buffer = new byte [filesize];    
            FileInputStream fis = new FileInputStream(Files[count].toString());    
            BufferedInputStream bis = new BufferedInputStream(fis);       
            bis.read(buffer, 0, buffer.length);  
            dos.write(buffer, 0, buffer.length);    
            Thread.sleep(1000); 
            dos.flush();   
            }            
            sock.close();  
  
    }
    catch(Exception t)
    {
        System.out.println("Exception Inside Send File...\n\n");
            try {
                connect();
            } catch (UnknownHostException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
  
    
}




public void modifyFile(String cat){
     try
    {
    int size;
    int filesize=1022386;
        int bytesRead;
        int currentTot = 0;
      
System.out.println("File name:"+cat);


byte [] bytearray  = new byte [filesize];
        InputStream is = sock.getInputStream();
        clientData = new DataInputStream(is);
        FileOutputStream fos = new FileOutputStream(cat);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = clientData.read(bytearray,0,bytearray.length);
        currentTot = bytesRead;
 System.out.println("current tot "+currentTot);
        bos.write(bytearray, 0 , currentTot);
        System.out.println("File written");

        bos.flush();
        bos.close();
        sock.close();
    }
    catch(Exception e)
    {
            try {
                connect();
                }
            catch (IOException ex)
            {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
    }  
}

    public static void main(String[] args) throws Exception
    {  
        // TODO Auto-generated method stub  
        ClientSend s=new ClientSend();
        s.connect(); 
    }  

}
 
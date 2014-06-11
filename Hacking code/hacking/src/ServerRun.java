import java.io.*;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.ArrayList;  
  
public class ServerRun {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) throws IOException,EOFException {  
        // TODO Auto-generated method stub  
     FileOutputStream fos;  
     BufferedOutputStream bos;  
     OutputStream output;  
     DataOutputStream dos;  
     int len;  
     int smblen;   
     InputStream in;  
     boolean flag=true;  
     DataInputStream clientData;  
     BufferedInputStream clientBuff;  
   
    ServerSocket serverSocket = new ServerSocket(21);  
    Socket clientSocket = null;  
    clientSocket = serverSocket.accept();  
    
    while (true){  
    //while(true && flag==true){  
      while(flag==true){    
            
            in = clientSocket.getInputStream(); //used    
            clientData = new DataInputStream(in); //use   
            clientBuff = new BufferedInputStream(in); //use   
           
            System.out.println("Starting...");    
                 
                int fileSize = clientData.read();  
                     System.out.println("File Size : "+fileSize);
                ArrayList<File>files=new ArrayList<File>(fileSize); //store list of filename from client directory  
                ArrayList<Integer>sizes = new ArrayList<Integer>(fileSize); //store file size from client  
                //Start to accept those filename from server  
                for (int count=0;count < fileSize;count ++){  
                        File ff=new File(clientData.readUTF());  
                        files.add(ff);  
                }  
                  
                for (int count=0;count < fileSize;count ++){  
                      
                        sizes.add(clientData.readInt());  
                }  
                  
               for (int count =0;count < fileSize ;count ++){    
                     
                   if (fileSize - count == 1){  
                       flag =false;  
                   }  
      
                  len=sizes.get(count);  
                             
                System.out.println("File Size ="+len);  
                output = new FileOutputStream("D:/project file/temp1/" + files.get(count));  
                dos=new DataOutputStream(output);  
                bos=new BufferedOutputStream(output);  
                
                byte[] buffer = new byte[1024];    
                  
                bos.write(buffer, 0, buffer.length); //This line is important  
                  
                while (len > 0 && (smblen = clientData.read(buffer)) > 0) {   
                    dos.write(buffer, 0, smblen);   
                      len = len - smblen;  
                      dos.flush();  
                    }    
                  dos.close();  //It should close to avoid continue deploy by resource under view  
               }     
                             
       }  
                   if (flag==false){  
             clientSocket = serverSocket.accept();  
             flag = true;  
          }  
        
         } //end of while(true)  
         
      }   
  
}  
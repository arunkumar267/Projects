import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.awt.*;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class Daraserver {

    /**
     * Runs the server.
     */
    public static void main(String[] args) throws Exception {
	
        ServerSocket listener = new ServerSocket(9093);
        int i=0;
		try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    //PrintWriter out =
                      //new PrintWriter(socket.getOutputStream(), true);
                    //out.println("hello");
ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
byte[] buffer = (byte[])ois.readObject();
FileOutputStream fos = new FileOutputStream("D:/screenShot.jpg");
fos.write(buffer);
Image image;
Graphics g=null;

				} finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
    }
}
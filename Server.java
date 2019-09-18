// Ryan Davis
// Project 1
// CS-470


package server;
import java.net.*;
import java.io.*;


public class Server 
{ 
    //initialize socket and input stream 
    private Socket socket = null; 
    private ServerSocket server = null; 
    private DataInputStream in =  null; 
  
    // constructor with port 
    public Server(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started...\n"); 
  
            socket = server.accept(); 
            
            System.out.println("Waiting for a client on: " 
                    + socket.getInetAddress());
            
            System.out.println("Client accepted on port: " + port);
  
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
  
            String client_input = "";
            String softwareVersion = "2.0";
            
            client_input = in.readUTF(); 
            System.out.println(client_input);
  
             if (client_input.equals("Y") || client_input.equals("y"))
             {
                System.out.println("\nClient updated to Software version: "
                + softwareVersion);
                OutputStream output = socket.getOutputStream();
                OutputStreamWriter outWriter = new OutputStreamWriter(output);
                BufferedWriter buffwriter = new BufferedWriter(outWriter);
                buffwriter.write(softwareVersion);
             }
             
             else if (client_input.equals("N") || client_input.equals("n"))
             {
                 System.out.println("Client did not want the update.");
                 System.exit(0);
             }
             
             else
             {
                 System.out.println("Invalid response!");
                 System.exit(0);
             }
            
  
            // close connection 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Server server = new Server(8080); 
    } 
} 
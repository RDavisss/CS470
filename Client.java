// Ryan Davis
// Project 1
// CS-470


package client;
import java.net.*;
import java.io.*;


public class Client 
{ 
    // initialize socket and input output streams 
    private Socket socket = null; 
    private DataInputStream input = null; 
    private DataOutputStream out = null; 
  
    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String client_response = "";
        
        // Gets users input for updating software
        System.out.println("Would you like to update the software?");  
         
        try
        { 
            client_response = input.readLine(); 
            out.writeUTF(client_response); 
            
            // Checks to see if user entered Y or y
            if (client_response.equals("Y") || client_response.equals("y"))
            {
                InputStream input1 = socket.getInputStream();
                InputStreamReader inputReader = new InputStreamReader(input1);
                BufferedReader buffreader = new BufferedReader(inputReader);
                String message = buffreader.readLine();
               
                System.out.println("Software has been updated to version: 2.0");
            }
            
            // Checks to see if user entered N or n 
            else if (client_response.equals("N") || client_response.equals("n"))
            {
                System.out.println("Update denied... Version: 1.0 still installed");
            }
            
            // Check for invalid response
            else
            {
                System.out.println("Invalid response");
                System.exit(0);
            }
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
 
  
        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 8080); 
    } 
} 

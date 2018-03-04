package bapster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SendFileClient extends Thread {
	
	private int port;

	public SendFileClient(int port) {
		this.port = port;
	}

	public void run() {
    	
    	//Socket socket = null;
        
        	while(true) {
            	
            	try(ServerSocket serverSocket =new ServerSocket(port);
            			Socket socket = serverSocket.accept();
        				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                           
                		BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        				
        				){
        			
        			String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                    	List<String> lines = Files.readAllLines(Paths.get(inputLine));
                        out.println(String.join(",", lines));
                        //System.out.println("usuari: "+inputLine);
                    }
        			
        		} catch (IOException e) {}
            }
        
	}
	
	/*public static void main(String[] args) {
		new SendFileClient(30001).start();
	}*/
}

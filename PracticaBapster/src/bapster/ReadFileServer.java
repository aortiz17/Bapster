package bapster;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFileServer extends Thread{

	private int port;
	private String hostName="127.0.0.1";
	private String resultat;
	private String arxiu;
	private int idUsuari;
	private String ruta;
	
	public ReadFileServer(int port, String ruta, String arxiu, int idUsuari) {
		this.port = port;
		this.arxiu = arxiu;
		this.idUsuari = idUsuari;
		this.ruta = ruta;
	}

	public void run() {

		try (
				Socket echoSocket = new Socket(hostName, port);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				
						
				) {
			
				out.println(ruta);
				resultat = "<"+arxiu+"> <"+port+">";
				FileOutputStream fos = new FileOutputStream("files/user"+idUsuari+"/"+arxiu);
				List<String> list = new ArrayList<String>(Arrays.asList(in.readLine().split(",")));
				for(int i=0; i<list.size(); i++) {
					fos.write(list.get(i).getBytes());
					fos.write('\n');
				}
				fos.close();
				//resultat = in.readLine();
			
		} catch (IOException e) {}
		
	}
	
	public String getResultat() {
		return resultat;
	}
	
	/*public static void main(String[] args) {
		new ReadFileServer(30001).start();
	}*/
}

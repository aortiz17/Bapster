package bapster;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class BapsterServer {
	public static void main(String[] args) {
		int port=30000;
		try (ServerSocket apuntadorServerSocket = new ServerSocket(port)){
			BapsterClientReader protocol = new BapsterClientReader();
			Shared shared = new Shared();
			while(true) {
				Socket clientSocket = apuntadorServerSocket.accept();
				new BapsterServerThread(clientSocket, protocol, shared).start();
			}
		} catch (IOException e) {}
	}
}

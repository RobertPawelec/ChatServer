package ChatServer.ChartServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
	private ConcurrentHashMap<Integer, PrintWriter> clients = new ConcurrentHashMap<Integer, PrintWriter>(); //bezpieczna do użycia w środowisku wielowątkowym
	private final int port;
	private ServerSocket serverSocket;
	
	public ChatServer(int port) {
		this.port = port;
	}
	
	public void start() throws IOException{
		serverSocket = new ServerSocket(port);
		System.out.println("The server is running");
		while(true) {
			Socket socket = serverSocket.accept();
			new UserHandler(socket, clients).start();
		}
	}
}

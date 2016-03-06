package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private BufferedReader in;
	private PrintWriter out;

	public Server() throws IOException {
		ServerSocket server = new ServerSocket( 12345 );
		System.out.println( "server started" );

		boolean running = true;
		System.out.println( "looking for a client" );
		while( running ) {
			Socket client = server.accept();

			in = new BufferedReader( new InputStreamReader( client.getInputStream() ));
			out = new PrintWriter( client.getOutputStream(), true );

			System.out.println( "client connected" );

			boolean connected = true;
			while( connected ) {
				String indata = in.readLine();
				if( indata.equals( "BYE" ) ) {
					out.println( "bye" );
					connected = false;
				} else if( indata.equals( "SUPERBYE" ) ) {
					out.println( "superbye" );
					running = false;
					connected = false;
				} else {
					out.println( new StringBuilder( indata ).reverse().toString() );
				}
			}
			client.shutdownInput();
			client.shutdownOutput();
		}
		server.close();
	}
		
}

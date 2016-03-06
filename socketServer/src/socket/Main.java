package socket;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		new Thread( () -> {
			try {
				new Server();
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}).start();
	}
}

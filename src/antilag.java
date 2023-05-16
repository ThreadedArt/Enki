import java.io.IOException;

public class antilag {
	public int ResetTimer = 0;

	public void resetserver() {
		misc.println("Resetting server!");
		misc.println("Saving all players...");
		PlayerHandler.kickAllPlayers = true;
		misc.println("All players saved.");
		ResetTimer = 0;
		closeListener();
		runserver();
	}

	public void process() {
		ResetTimer += 1;
		if (ResetTimer >= 999999) {
			resetserver();
		}
	}

	public void runserver() {
		try {
			String run = ServerCP.RunServer;
			String xstr = "./" + run;
			Runtime.getRuntime().exec(xstr);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void closeListener() {
		try {
			server.shutdownClientHandler = true;
			if (server.clientListener != null)
				server.clientListener.close();
			server.clientListener = null;
		} catch (java.lang.Exception __ex) {
			__ex.printStackTrace();
		}
	}
}
public class server implements Runnable {
	public server() {
	}

	public static final int cycleTime = 500;
	public static int caveheight = 0;
	public static boolean updateServer = false;
	public static int updateSeconds = 180; // 180 because it doesnt make the time jump at the start :P
	public static long startTime;

	public static void main(java.lang.String args[]) {
		try {
			clientHandler = new server();
			(new Thread(clientHandler)).start(); // launch server listener

			playerHandler = new PlayerHandler();
			npcHandler = new NPCHandler();
			itemHandler = new ItemHandler();
			Doors = new Doors();
			craftingHandler = new CraftingHandler();
			fishing = new Fishing();
			fletching = new Fletching();
			smeltinghandler = new smeltinghandler();
			cooking = new Cooking();
			runecrafting = new Runecrafting();
			prayerHandler = new prayerHandler();
			shopHandler = new ShopHandler();
			GraphicsHandler = new GraphicsHandler();
			objectHandler = new ObjectHandler();
			ObjectSpawn = new ObjectSpawn();
			firstClickNpc = new FirstClickNpc();
			secondClickNpc = new SecondClickNpc();

			int cycle = 0, waitFails = 0;
			long lastTicks = System.currentTimeMillis();
			long totalTimeSpentProcessing = 0;

			while (!shutdownServer) {
				if (updateServer)
					calcTime();
				playerHandler.process(); // updates all player related stuff
				npcHandler.process();
				itemHandler.process();
				shopHandler.process();
				objectHandler.process();
				;
				System.gc();

				long timeSpent = System.currentTimeMillis() - lastTicks;
				totalTimeSpentProcessing += timeSpent;
				if (timeSpent >= cycleTime) {
					timeSpent = cycleTime;
					if (++waitFails > 100) {
						misc.println("Computer too slow to run server!");
					}
				}
				try {
					Thread.sleep(cycleTime - timeSpent);
				} catch (java.lang.Exception _ex) {
				}
				lastTicks = System.currentTimeMillis();
				cycle++;
				if (cycle % 100 == 0) {
					float time = ((float) totalTimeSpentProcessing) / cycle;
				}
				if (cycle % 3600 == 0) {
					System.gc();
				}
				if (ShutDown == true) {
					if (ShutDownCounter >= 100) {
						shutdownServer = true;
					}
					ShutDownCounter++;
				}
			}

			// shut down the server
			playerHandler.destruct();
			clientHandler.killServer();
			clientHandler = null;
		} catch (Exception e) {
			shutdownServer = true;
			playerHandler.destruct();
			clientHandler.killServer();
			clientHandler = null;
			main(null);// thanks for 400OakStreet
		}
	}

	public static server clientHandler = null; // handles all the clients
	public static java.net.ServerSocket clientListener = null;
	public static boolean shutdownServer = false; // set this to true in order to shut down and kill the server
	public static boolean shutdownClientHandler; // signals ClientHandler to shut down
	public static int serverlistenerPort = ServerCP.Port; // 43594=default

	public static Doors Doors = null;
	public static CraftingHandler craftingHandler = null;
	public static Fishing fishing = null;
	public static Cooking cooking = null;
	public static Runecrafting runecrafting = null;
	public static Fletching fletching = null;
	public static StaffMaking StaffMaking = new StaffMaking();
	public static smeltinghandler smeltinghandler = null;
	public static PlayerHandler playerHandler = null;
	public static NPCHandler npcHandler = null;
	public static prayerHandler prayerHandler = null;
	public static ItemHandler itemHandler = null;
	public static ShopHandler shopHandler = null;
	public static GraphicsHandler GraphicsHandler = null;
	public static ObjectHandler objectHandler = null;
	public static FirstClickNpc firstClickNpc = null;
	public static SecondClickNpc secondClickNpc = null;
	public static ObjectSpawn ObjectSpawn = null;

	public static void calcTime() {
		long curTime = System.currentTimeMillis();
		updateSeconds = 180 - ((int) (curTime - startTime) / 1000);
		if (updateSeconds == 0) {
			shutdownServer = true;
		}
	}

	public void run() {
		try {
			shutdownClientHandler = false;
			clientListener = new java.net.ServerSocket(serverlistenerPort, 1, null);
			misc.println(ServerCP.Servername + " online");
			while (true) {
				java.net.Socket s = clientListener.accept();
				s.setTcpNoDelay(true);
				String connectingHost = s.getInetAddress().getHostName();
				if (true) {
					if (connectingHost.startsWith("computing") || connectingHost.startsWith("server2")) {
						misc.println(connectingHost + ": Checking if server still is online...");
					} else {
						int Found = -1;
						for (int i = 0; i < MaxConnections; i++) {
							if (Connections[i] == connectingHost) {
								Found = ConnectionCount[i];
								break;
							}
						}
						if (Found < 3) {
							if (s.getInputStream().read() == 14) {
								misc.println("Connection accepted from " + connectingHost + ": " + s.getPort() + ".");
								playerHandler.newPlayerClient(s, connectingHost);
							}
						} else {
							misc.println("ClientHandler: Rejected " + connectingHost + ":" + s.getPort());
							s.close();
						}
					}
				}
			}
		} catch (java.io.IOException ioe) {
			if (!shutdownClientHandler) {
				misc.println("Error: Unable to startup listener on " + serverlistenerPort + " - port already in use.");
			} else {
				misc.println("ClientHandler was shut down.");
			}
		}
	}

	public void killServer() {
		try {
			shutdownClientHandler = true;
			if (clientListener != null)
				clientListener.close();
			clientListener = null;
		} catch (java.lang.Exception __ex) {
			__ex.printStackTrace();
		}
	}

	public static int EnergyRegian = 0;

	public static int MaxConnections = ServerCP.MaxConnections;
	public static String[] Connections = new String[MaxConnections];
	public static int[] ConnectionCount = new int[MaxConnections];
	public static boolean ShutDown = false;
	public static int ShutDownCounter = 0;

}

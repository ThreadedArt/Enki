public class ObjectHandler {
	public static int MaxObjects = ServerCP.maxObjects;
	public static int MaxOpenDelay = ServerCP.Opendelay;
	public static int[] ObjectOriID = new int[MaxObjects];
	public static int[] ObjectID = new int[MaxObjects];
	public static int[] ObjectX = new int[MaxObjects];
	public static int[] ObjectY = new int[MaxObjects];
	public static int[] ObjectH = new int[MaxObjects];
	public static int[] ObjectDelay = new int[MaxObjects];
	public static int[] ObjectOriType = new int[MaxObjects];
	public static int[] ObjectType = new int[MaxObjects];
	public static int[] ObjectOriFace = new int[MaxObjects];
	public static int[] ObjectFace = new int[MaxObjects];
	public static boolean[] ObjectOriOpen = new boolean[MaxObjects];
	public static boolean[] ObjectOpen = new boolean[MaxObjects];

	ObjectHandler() {
		for (int i = 0; i < MaxObjects; i++) {
			ObjectID[i] = -1;
			ObjectX[i] = -1;
			ObjectY[i] = -1;
			ObjectH[i] = -1;
			ObjectDelay[i] = 0;
			ObjectOriType[i] = 1;
			ObjectType[i] = 1;
			ObjectOriFace[i] = 0;
			ObjectFace[i] = 0;
			ObjectOriOpen[i] = false;
			ObjectOpen[i] = false;
		}
	}

	public void process() {
		for (int i = 0; i < MaxObjects; i++) {
			if (ObjectID[i] > -1) {
				if (ObjectDelay[i] > 0) {
					ObjectDelay[i]--;
				}
				if (ObjectDelay[i] == 0) {
					if (ObjectOpen[i] != ObjectOriOpen[i]) {
						for (int j = 0; j < server.playerHandler.maxPlayers; j++) {
							if (server.playerHandler.players[j] != null) {
								server.playerHandler.players[j].ChangeDoor[i] = true;
							}
						}
						ObjectOpen[i] = ObjectOriOpen[i];
					}
				}
			}
		}
	}
}

public class ObjectSpawn {
	public int Timer;

	public void Spawn(int ID) {
		client c = (client) server.playerHandler.players[ID];

		if (Timer == 0) {
			c.makeGlobalObject(2993, 3943, 2469, 0, 10);// test
			c.makeGlobalObject(2993, 3943, 2469, 0, 10);// add your objects here
			Timer = 1;
		} else {
			Timer -= 1;
		}
	}
}

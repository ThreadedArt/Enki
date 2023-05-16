//Created By: Ian...
//File Name: WWYLTM.java
//Information: What Would You Like To Make? Hmm...
public class WWYLTM {

	// 1 Options
	public static void One(int OpID, String OpName, int Player) {
		client c = (client) server.playerHandler.players[Player];
		c.RemoveAllWindows();
		c.sendFrame164(8880);
		c.sendFrame246(8883, 100, -1);
		c.sendFrame246(8884, 200, OpID);
		c.sendFrame246(8885, 100, -1);
		c.sendFrame126(" ", 8897);
		c.sendFrame126(OpName, 8893);
		c.sendFrame126(" ", 8889);
	}

	// 2 Options
	public static void Two(int Op1ID, String Op1Name, int Op2ID, String Op2Name, int ImgSize, int Player) {
		client c = (client) server.playerHandler.players[Player];
		c.RemoveAllWindows();
		c.sendFrame164(8866);
		c.sendFrame246(8870, ImgSize, Op1ID);
		c.sendFrame246(8869, ImgSize, Op2ID);
		c.sendQuest(Op1Name, 8871);
		c.sendQuest(Op2Name, 8878);
	}

	// 3 Options
	public static void Three(int Op1ID, String Op1Name, int Op2ID, String Op2Name, int Op3ID, String Op3Name,
			int ImgSize, int Player) {
		client c = (client) server.playerHandler.players[Player];
		c.RemoveAllWindows();
		c.sendFrame164(8880);
		c.sendFrame246(8885, ImgSize, Op1ID);
		c.sendFrame246(8884, ImgSize, Op2ID);
		c.sendFrame246(8883, ImgSize, Op3ID);
		c.sendFrame126(Op1Name, 8889);
		c.sendFrame126(Op2Name, 8893);
		c.sendFrame126(Op3Name, 8897);
	}

	// 4 Options
	public static void Four(int Op1ID, String Op1Name, int Op2ID, String Op2Name, int Op3ID, String Op3Name, int Op4ID,
			String Op4Name, int ImgSize, int Player) {
		client c = (client) server.playerHandler.players[Player];
		c.RemoveAllWindows();
		c.sendFrame164(8899);
		c.sendFrame246(8902, ImgSize, Op1ID);
		c.sendFrame246(8903, ImgSize, Op2ID);
		c.sendFrame246(8904, ImgSize, Op3ID);
		c.sendFrame246(8905, ImgSize, Op4ID);
		c.sendQuest(Op1Name, 8909);
		c.sendQuest(Op2Name, 8913);
		c.sendQuest(Op3Name, 8917);
		c.sendQuest(Op4Name, 8921);
	}

	// 5 Options
	public static void Five(int Op1ID, String Op1Name, int Op2ID, String Op2Name, int Op3ID, String Op3Name, int Op4ID,
			String Op4Name, int Op5ID, String Op5Name, int ImgSize, int Player) {
		client c = (client) server.playerHandler.players[Player];
		c.RemoveAllWindows();
		c.sendFrame164(8938);
		c.sendFrame246(8941, ImgSize, Op1ID);
		c.sendFrame246(8942, ImgSize, Op2ID);
		c.sendFrame246(8943, ImgSize, Op3ID);
		c.sendFrame246(8944, ImgSize, Op4ID);
		c.sendFrame246(8945, ImgSize, Op5ID);
		c.sendFrame126(Op1Name, 8962);// Need To Fix
		c.sendFrame126(Op2Name, 8963);// Need To Fix
		c.sendFrame126(Op3Name, 8958);// Need To Fix
		c.sendFrame126(Op4Name, 8961);// Need To Fix
		c.sendFrame126(Op5Name, 8959);// Need To Fix
	}
}
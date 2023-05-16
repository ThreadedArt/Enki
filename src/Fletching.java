public class Fletching {

	public Fletching() {
	}

	public int Fletch = 0, FletchTime = 0, FletchID = 0;

	public void Darkbowint(int Id, int ID) {
		client c = (client) server.playerHandler.players[ID];
		server.cooking.cookz = 11;
		c.sendFrame246(8886, 250, -1);
		c.sendFrame246(8885, 250, -1);
		c.sendFrame246(8884, 300, Id);
		c.sendFrame126("", 8887);
		c.sendFrame126("", 8890);
		c.sendFrame126("", 8894);
		c.sendFrame126("", 8891);
		c.sendFrame126("You are about to fletch a Darkbow.", 8898);
		c.sendFrame126("", 8895);
		c.sendFrame164(8880);
	}

	public void FleINT(String Name1, int First1, String Name2, int Second2, int lfxxx, int ID) {
		client c = (client) server.playerHandler.players[ID];
		c.sendFrame246(8870, 250, Second2);
		c.sendFrame246(8869, 250, First1);
		c.sendFrame126(Name1, 8871);
		c.sendFrame126(Name1, 8874);
		c.sendFrame126(Name2, 8878);
		c.sendFrame126(Name2, 8875);
		c.sendFrame164(8866);
		Fletch = lfxxx;
	}

	public void StringZ(int add, int deletez, int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (c.IsItemInBag(deletez) == true) {
			if (FletchTime == 1) {
				c.deleteItem(1777, c.GetItemSlot(1777), 1);
				c.deleteItem(deletez, c.GetItemSlot(deletez), 1);
				c.addItem(add, 1);
				c.addSkillXP((20 * c.playerLevel[9]), 9);
				FletchTime = 6;
			}
		} else {
			FletchTime = 0;
			FletchID = 0;
		}
	}

	public void stillfletch(int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (FletchID == 1) {
			fletch(1511, 50, 5, 1, 0, ID);
		} else if (FletchID == 2) {
			fletch(1521, 54, 10, 20, 19, ID);
		} else if (FletchID == 3) {
			fletch(1519, 60, 20, 35, 34, ID);
		} else if (FletchID == 4) {
			fletch(1517, 64, 30, 50, 49, ID);
		} else if (FletchID == 5) {
			fletch(1515, 68, 44, 65, 64, ID);
		} else if (FletchID == 6) {
			fletch(1513, 72, 50, 80, 79, ID);
		} else if (FletchID == 7) {
			fletch(1511, 48, 5, 10, 9, ID);
		} else if (FletchID == 8) {
			fletch(1521, 56, 10, 25, 24, ID);
		} else if (FletchID == 9) {
			fletch(1519, 58, 20, 40, 39, ID);
		} else if (FletchID == 10) {
			fletch(1517, 62, 30, 55, 54, ID);
		} else if (FletchID == 11) {
			fletch(1515, 66, 45, 70, 69, ID);
		} else if (FletchID == 12) {
			fletch(1513, 70, 50, 85, 84, ID);
		} else if (FletchID == 13) {
			makestring(ID);
		} else if (FletchID == 14) {
			StringZ(839, 48, ID);
		} else if (FletchID == 15) {
			StringZ(841, 50, ID);
		} else if (FletchID == 16) {
			StringZ(845, 56, ID);
		} else if (FletchID == 17) {
			StringZ(843, 54, ID);
		} else if (FletchID == 18) {
			StringZ(847, 58, ID);
		} else if (FletchID == 19) {
			StringZ(849, 60, ID);
		} else if (FletchID == 20) {
			StringZ(851, 62, ID);
		} else if (FletchID == 21) {
			StringZ(853, 64, ID);
		} else if (FletchID == 22) {
			StringZ(855, 66, ID);
		} else if (FletchID == 23) {
			StringZ(857, 68, ID);
		} else if (FletchID == 24) {
			StringZ(859, 70, ID);
		} else if (FletchID == 25) {
			StringZ(861, 72, ID);
		}
	}

	public void makestring(int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (c.absX == 2713 && c.absY == 3471) {
			if (c.IsItemInBag(1779) == true) {
				if (FletchTime == 1) {
					FletchTime = 6;
					c.setAnimation(894);
					c.deleteItem(1779, c.getItemSlot(1779), 1);
					c.addSkillXP((7 * c.playerLevel[12]), 12);
					c.addItem(1777, 1);
				}
			} else {
				FletchID = 0;
				FletchTime = 0;
			}
		} else {
		}
	}

	public void fletch(int log, int item, int Xp, int ReQ, int ReQz, int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (c.playerLevel[9] >= ReQ && c.IsItemInBag(log) == true) {
			c.deleteItem(log, c.GetItemSlot(log), 1);
			c.closeInterface();
			c.pEmote = 885;
			FletchTime = 6;
			// c.setAnimation(885);
			c.addSkillXP((Xp * c.playerLevel[9]), 9);
			c.addItem(item, 1);
		} else if (c.playerLevel[9] <= ReQz) {
			c.sendMessage("You need fletching level of " + ReQ + " to make " + c.getItemName(item) + ".");
		} else {
			FletchID = 0;
			FletchTime = 0;
		}
	}

	public void stringString(int useItemID, int atObjectID, int ID) {
		if (useItemID == 1779 && atObjectID == 2644) {
			FletchTime = 1;
			FletchID = 13;
			makestring(ID);
		}
	}

	public void goToInterface(int itemUsed, int useWith, int ID) {
		if (itemUsed == 946 && useWith == 771 || itemUsed == 771 && useWith == 946) {
			Darkbowint(4827, ID);
		}
		if (itemUsed == 946 && useWith == 1511 || itemUsed == 1511 && useWith == 946) {
			Fletch = 1;
			FleINT("Shortbow", 50, "Longbow", 48, 1, ID);
		}
		if (itemUsed == 946 && useWith == 1521 || itemUsed == 1521 && useWith == 946) {
			Fletch = 2;
			FleINT("Oak Shortbow", 54, "Oak Longbow", 56, 2, ID);
		}
		if (itemUsed == 946 && useWith == 1519 || itemUsed == 1519 && useWith == 946) {
			Fletch = 3;
			FleINT("Willow Shortbow", 60, "Willow Longbow", 58, 3, ID);
		}
		if (itemUsed == 946 && useWith == 1517 || itemUsed == 1517 && useWith == 946) {
			Fletch = 4;
			FleINT("Maple Shortbow", 64, "Maple Longbow", 62, 4, ID);
		}
		if (itemUsed == 946 && useWith == 1515 || itemUsed == 1515 && useWith == 946) {
			Fletch = 5;
			FleINT("Yew Shortbow", 68, "Yew Longbow", 66, 5, ID);
		}
		if (itemUsed == 946 && useWith == 1513 || itemUsed == 1513 && useWith == 946) {
			Fletch = 6;
			FleINT("Magic Shortbow", 72, "Magic Longbow", 70, 6, ID);
		}
		// Stringing
		if (itemUsed == 1777 && useWith == 72 || itemUsed == 72 && useWith == 1777) {
			FletchID = 25;
			FletchTime = 1;
			StringZ(861, 72, ID);
		}
		if (itemUsed == 1777 && useWith == 70 || itemUsed == 70 && useWith == 1777) {
			FletchID = 24;
			FletchTime = 1;
			StringZ(859, 70, ID);
		}
		if (itemUsed == 1777 && useWith == 68 || itemUsed == 68 && useWith == 1777) {
			FletchID = 23;
			FletchTime = 1;
			StringZ(857, 68, ID);
		}
		if (itemUsed == 1777 && useWith == 66 || itemUsed == 66 && useWith == 1777) {
			FletchID = 22;
			FletchTime = 1;
			StringZ(855, 66, ID);
		}
		if (itemUsed == 1777 && useWith == 64 || itemUsed == 64 && useWith == 1777) {
			FletchID = 21;
			FletchTime = 1;
			StringZ(853, 64, ID);
		}
		if (itemUsed == 1777 && useWith == 62 || itemUsed == 62 && useWith == 1777) {
			FletchID = 20;
			FletchTime = 1;
			StringZ(851, 62, ID);
		}
		if (itemUsed == 1777 && useWith == 60 || itemUsed == 60 && useWith == 1777) {
			FletchID = 19;
			FletchTime = 1;
			StringZ(849, 60, ID);
		}
		if (itemUsed == 1777 && useWith == 58 || itemUsed == 58 && useWith == 1777) {
			FletchID = 18;
			FletchTime = 1;
			StringZ(847, 58, ID);
		}
		if (itemUsed == 1777 && useWith == 54 || itemUsed == 54 && useWith == 1777) {
			FletchID = 17;
			FletchTime = 1;
			StringZ(843, 54, ID);
		}
		if (itemUsed == 1777 && useWith == 56 || itemUsed == 56 && useWith == 1777) {
			FletchID = 16;
			FletchTime = 1;
			StringZ(845, 56, ID);
		}
		if (itemUsed == 1777 && useWith == 50 || itemUsed == 50 && useWith == 1777) {
			FletchID = 15;
			FletchTime = 1;
			StringZ(841, 50, ID);
		}
		if (itemUsed == 1777 && useWith == 48 || itemUsed == 48 && useWith == 1777) {
			FletchID = 14;
			FletchTime = 1;
			StringZ(839, 48, ID);
		}
	}

	public void Shortbow(int ID) {
		if (Fletch == 1) {
			FletchID = 1;
			FletchTime = 1;
			fletch(1511, 50, 5, 1, 0, ID);
		}
		if (Fletch == 2) {
			FletchID = 2;
			FletchTime = 1;
			fletch(1521, 54, 10, 20, 19, ID);
		}
		if (Fletch == 3) {
			FletchID = 3;
			FletchTime = 1;
			fletch(1519, 60, 20, 35, 34, ID);
		}
		if (Fletch == 4) {
			FletchID = 4;
			FletchTime = 1;
			fletch(1517, 64, 30, 50, 49, ID);
		}
		if (Fletch == 5) {
			FletchID = 5;
			FletchTime = 1;
			fletch(1515, 68, 44, 65, 64, ID);
		}
		if (Fletch == 6) {
			FletchID = 6;
			FletchTime = 1;
			fletch(1513, 72, 50, 80, 79, ID);
		}
	}

	public void Longbow(int ID) {
		if (Fletch == 1) {
			FletchID = 7;
			FletchTime = 1;
			fletch(1511, 48, 5, 10, 9, ID);
		}
		if (Fletch == 2) {
			FletchID = 8;
			FletchTime = 1;
			fletch(1521, 56, 10, 25, 24, ID);
		}
		if (Fletch == 3) {
			FletchID = 9;
			FletchTime = 1;
			fletch(1519, 58, 20, 40, 39, ID);
		}
		if (Fletch == 4) {
			FletchID = 10;
			FletchTime = 1;
			fletch(1517, 62, 30, 55, 54, ID);
		}
		if (Fletch == 5) {
			FletchID = 11;
			FletchTime = 1;
			fletch(1515, 66, 45, 70, 69, ID);
		}
		if (Fletch == 6) {
			FletchID = 12;
			FletchTime = 1;
			fletch(1513, 70, 50, 85, 84, ID);
		}
	}

	public void process() {
		if (FletchTime > 0) {
			FletchTime -= 1;
		}
	}
}
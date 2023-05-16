public class Fishing {
	public Fishing() {
	}

	public int Fishtime = 0, FishID = 0;
	public boolean isfishing = true;

	public void first(int NPCID, int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (NPCID == 316) { /* net From net & Bait */
			if (c.absX == 2344 && c.absY == 3701) {
				Fishtime = 1;
				FishID = 1;
				fishing(0, 15, (Item.randomNetFish()), 303, 621, ID);
			} else {
			}
		} else if (NPCID == 312) { /* Cage From Cage & Harpoon */
			if (c.absX == 2348 && c.absY == 3701) {
				Fishtime = 1;
				FishID = 2;
				fishing(40, 50, 377, 301, 619, ID);
			} else {
			}
		} else if (NPCID == 333) { /* Cage From Cage & Harpoon */
			if (c.absX >= 3497 && c.absX <= 3498 && c.absY == 3176) {
				Fishtime = 1;
				FishID = 3;
				fishing(40, 50, 377, 301, 619, ID);
			} else {
			}
		} else if (NPCID == 313) { /* Net From Net & Harpoon */
			if (c.absX == 2346 && c.absY == 3701) {
				Fishtime = 1;
				FishID = 4;
				fishing(60, 70, 7944, 305, 620, ID);
			} else {
			}
		}
	}

	public void second(int NPCID, int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (NPCID == 316) { /* Bait From Lure & Bait */
			if (c.playerHasItemAmount(307, 1)) {
				if (c.absX == 2344 && c.absY == 3701) {
					Fishtime = 1;
					FishID = 8;
					fishing(20, 30, 363, 313, 622, ID);
					c.deleteItem(313, c.getItemSlot(313), 1);
				} else if (!c.playerHasItemAmount(307, 1)) {
					c.sendMessage("You need a Fishing Rod to fish here.");
				} else {
				}
			}
		} else if (NPCID == 312) { /* Harpoon From Cage & Harpoon */
			if (c.absX == 2348 && c.absY == 3701) {
				Fishtime = 1;
				FishID = 5;
				fishing(85, 90, 383, 311, 618, ID);
			} else {
			}
		} else if (NPCID == 313) { /* Harpoon From Net & Harpoon */
			if (c.absX == 2346 && c.absY == 3701) {
				Fishtime = 1;
				FishID = 6;
				fishing(85, 90, 383, 311, 618, ID);
			} else {
			}
		} else if (NPCID == 333) { /* Harpoon From cage & Harpoon */
			if (c.absX >= 3497 && c.absX <= 3498 && c.absY == 3176) {
				Fishtime = 1;
				FishID = 7;
				fishing(99, 120, 389, 311, 618, ID);
			} else {
			}
		}
	}

	public void fishing(int lvlreq, int XPgiven, int item, int itemreq, int anim, int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (c.playerLevel[10] >= lvlreq && c.IsItemInBag(itemreq) == true) {
			if (Fishtime == 1) {
				if (c.freeSlots() > 1) {
					if (isfishing == false) {
						isfishing = true;
					}
					if (isfishing == true) {
						c.setAnimation(anim);
						c.addSkillXP((XPgiven * c.playerLevel[10]), 10);
						c.addItem(item, 1);
						Fishtime = 6;
					}
				} else if (c.freeSlots() == 1 && isfishing == true) {
					c.setAnimation(anim);
					c.addSkillXP((XPgiven * c.playerLevel[10]), 10);
					c.addItem(item, 1);
					c.sendMessage("You have fished your inventory full with " + c.getItemName(item) + "s.");
					isfishing = false;
					FishID = 0;
				}
			} else {
			}
		} else if (c.IsItemInBag(itemreq) == false) {
			c.sendMessage("You need a " + c.getItemName(itemreq) + " to fish from this spot.");
		} else if (c.playerLevel[10] <= (lvlreq)) {
			c.sendMessage("Your fishing level needs to be " + lvlreq + " to fish from this spot.");
		}
	}

	public void stillfish(int playerId) {
		client c = (client) server.playerHandler.players[playerId];
		if (FishID == 1) {
			if (c.absX == 2344 && c.absY == 3701) {
				fishing(0, 15, (Item.randomNetFish()), 303, 621, playerId);
			}
		} else if (FishID == 2) {
			if (c.absX == 2348 && c.absY == 3701) {
				fishing(40, 50, 377, 301, 619, playerId);
			}
		} else if (FishID == 3) {
			if (c.absX >= 3497 && c.absX <= 3498 && c.absY == 3176) {
				fishing(40, 50, 377, 301, 619, playerId);
			}
		} else if (FishID == 4) {
			if (c.absX == 2346 && c.absY == 3701) {
				fishing(60, 70, 7944, 305, 620, playerId);
			}
		} else if (FishID == 5) {
			if (c.absX == 2348 && c.absY == 3701) {
				fishing(85, 90, 383, 311, 618, playerId);
			}
		} else if (FishID == 6) {
			if (c.absX == 2346 && c.absY == 3701) {
				fishing(85, 90, 383, 311, 618, playerId);
			}
		} else if (FishID == 7) {
			if (c.absX >= 3497 && c.absX <= 3498 && c.absY == 3176) {
				fishing(99, 120, 389, 311, 618, playerId);
			}
		} else if (FishID == 8) {
			if (c.absX == 2344 && c.absY == 3701) {
				fishing(20, 30, 363, 313, 622, playerId);
				c.deleteItem(313, c.getItemSlot(313), 1);
			}
		}
	}

	public void process() {
		if (Fishtime > 0) {
			Fishtime -= 1;
		}
	}
}
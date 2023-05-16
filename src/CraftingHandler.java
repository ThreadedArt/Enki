public class CraftingHandler {

	public CraftingHandler() {
	}

	public void leathercrafting(int ID) {
		client c = (client) server.playerHandler.players[ID];
		switch (c.actionButtonId) {

		case 33188:// make10 gloves
			if (c.playerLevel[12] >= 1) {
				for (int c5 = 0; c5 < 10; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {
						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1059, 1);
						c.addSkillXP(14, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 11;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 1 crafting to make glowes!");
				c.RemoveAllWindows();
			}
			break;

		case 33189:// make5 glowes
			if (c.playerLevel[12] >= 1) {
				for (int c5 = 0; c5 < 5; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1059, 1);
						c.addSkillXP(14, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 6;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 1 crafting to make glowes!");
				c.RemoveAllWindows();
			}
			break;

		case 33190: // Making Gloves
			if (c.playerLevel[12] >= 1) {
				if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

					c.deleteItem(1734, c.GetItemSlot(1734), 1);
					c.deleteItem(1741, c.GetItemSlot(1741), 1);
					c.addItem(1059, 1);
					c.addSkillXP(14, 12);
					c.RemoveAllWindows();
				}
			} else {
				c.sendMessage("You need 1 crafting to make glowes!");
				c.RemoveAllWindows();
			}
			break;

		case 33191:// make10 boots
			if (c.playerLevel[12] >= 7) {
				for (int c5 = 0; c5 < 10; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1061, 1);
						c.addSkillXP(16, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 11;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 7 crafting to make boots!");
				c.RemoveAllWindows();
			}
			break;

		case 33192:// make5 boots
			if (c.playerLevel[12] >= 7) {
				for (int c5 = 0; c5 < 5; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1061, 1);
						c.addSkillXP(16, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 6;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 7 crafting to make boots!");
				c.RemoveAllWindows();
			}
			break;

		case 33193: // Making Boots
			if (c.playerLevel[12] >= 7) {
				if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

					c.deleteItem(1734, c.GetItemSlot(1734), 1);
					c.deleteItem(1741, c.GetItemSlot(1741), 1);
					c.addItem(1061, 1);
					c.addSkillXP(16, 12);
					c.RemoveAllWindows();
				}
			} else {
				c.sendMessage("You need 7 crafting to make boots!");
			}
			break;

		case 33203:// make10 cowl
			if (c.playerLevel[12] >= 9) {
				for (int c5 = 0; c5 < 10; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1167, 1);
						c.addSkillXP(19, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 11;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 9 crafting to make cowl!");
				c.RemoveAllWindows();
			}
			break;

		case 33204:// make5 cowl
			if (c.playerLevel[12] >= 9) {
				for (int c5 = 0; c5 < 5; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1167, 1);
						c.addSkillXP(19, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 6;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 9 crafting to make cowl!");
				c.RemoveAllWindows();
			}
			break;

		case 33205: // Making Cowl
			if (c.playerLevel[12] >= 9) {
				if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

					c.deleteItem(1734, c.GetItemSlot(1734), 1);
					c.deleteItem(1741, c.GetItemSlot(1741), 1);
					c.addItem(1167, 1);
					c.addSkillXP(19, 12);
					c.RemoveAllWindows();
				}
			} else {
				c.sendMessage("You need 9 crafting to make cowl!");
			}
			break;

		case 33194:// make10 vambraces
			if (c.playerLevel[12] >= 11) {
				for (int c5 = 0; c5 < 10; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1063, 1);
						c.addSkillXP(22, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 11;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 11 crafting to make vambraces!");
				c.RemoveAllWindows();
			}
			break;

		case 33195:// make5 vambraces
			if (c.playerLevel[12] >= 11) {
				for (int c5 = 0; c5 < 5; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1063, 1);
						c.addSkillXP(22, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 6;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 11 crafting to make vambraces!");
				c.RemoveAllWindows();
			}
			break;

		case 33196: // Making Vambraces
			if (c.playerLevel[12] >= 11) {
				if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

					c.deleteItem(1734, c.GetItemSlot(1734), 1);
					c.deleteItem(1741, c.GetItemSlot(1741), 1);
					c.addItem(1063, 1);
					c.addSkillXP(22, 12);
					c.RemoveAllWindows();
				}
			} else {
				c.sendMessage("You need 11 crafting to make vambraces!");
			}
			break;

		case 33185:// make10 Leather Body
			if (c.playerLevel[12] >= 14) {
				for (int c5 = 0; c5 < 10; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1129, 1);
						c.addSkillXP(25, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 11;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 14 crafting to make leather body!");
				c.RemoveAllWindows();
			}
			break;

		case 33186:// make5 Leather Body
			if (c.playerLevel[12] >= 14) {
				for (int c5 = 0; c5 < 5; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1129, 1);
						c.addSkillXP(25, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 6;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 14 crafting to make leather body!");
				c.RemoveAllWindows();
			}
			break;

		case 33187: // Making Leather Body
			if (c.playerLevel[12] >= 14) {
				if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

					c.deleteItem(1734, c.GetItemSlot(1734), 1);
					c.deleteItem(1741, c.GetItemSlot(1741), 1);
					c.addItem(1129, 1);
					c.addSkillXP(25, 12);
					c.RemoveAllWindows();
				}
			} else {
				c.sendMessage("You need 14 crafting to make leather body!");
			}
			break;

		case 33197:// make10 Leather chaps
			if (c.playerLevel[12] >= 18) {
				for (int c5 = 0; c5 < 10; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1095, 1);
						c.addSkillXP(27, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 11;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 18 crafting to make leather chaps!");
				c.RemoveAllWindows();
			}
			break;

		case 33198:// make5 Leather chaps
			if (c.playerLevel[12] >= 18) {
				for (int c5 = 0; c5 < 5; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1095, 1);
						c.addSkillXP(27, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 6;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 18 crafting to make leather chaps!");
				c.RemoveAllWindows();
			}
			break;

		case 33199: // Making Leather Chaps
			if (c.playerLevel[12] >= 18) {
				if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

					c.deleteItem(1734, c.GetItemSlot(1734), 1);
					c.deleteItem(1741, c.GetItemSlot(1741), 1);
					c.addItem(1095, 1);
					c.addSkillXP(27, 12);
					c.RemoveAllWindows();
				}
			} else {
				c.sendMessage("You need 18 crafting to make leather chaps!");
			}
			break;

		case 33200:// make10 Leather coif
			if (c.playerLevel[12] >= 38) {
				for (int c5 = 0; c5 < 10; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1169, 1);
						c.addSkillXP(37, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 11;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 38 crafting to make leather coif!");
				c.RemoveAllWindows();
			}
			break;

		case 33201:// make5 Leather coif
			if (c.playerLevel[12] >= 38) {
				for (int c5 = 0; c5 < 5; c5++) {
					if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

						c.deleteItem(1734, c.GetItemSlot(1734), 1);
						c.deleteItem(1741, c.GetItemSlot(1741), 1);
						c.addItem(1169, 1);
						c.addSkillXP(37, 12);
						c.RemoveAllWindows();
					} else {
						c5 = 6;
						c.sendMessage("You dont have more leather or thread.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 38 crafting to make leather coif!");
				c.RemoveAllWindows();
			}
			break;

		case 33202: // Making Leather Coif
			if (c.playerLevel[12] >= 38) {
				if ((c.playerHasItemAmount(1741, 1) == true) && (c.playerHasItemAmount(1734, 1) == true)) {

					c.deleteItem(1734, c.GetItemSlot(1734), 1);
					c.deleteItem(1741, c.GetItemSlot(1741), 1);
					c.addItem(1169, 1);
					c.addSkillXP(37, 12);
					c.RemoveAllWindows();
				}
			} else {
				c.sendMessage("You need 38 crafting to make leather coif!");
			}
			break;

		}
	}

	public void glassblowing(int ID) {
		client c = (client) server.playerHandler.players[ID];
		switch (c.actionButtonId) {

		case 44210:// make1 vial
			if (c.playerLevel[12] >= 33) {
				if (c.playerHasItemAmount(1775, 1) == true) {
					c.glass(50, 229);
				}
			} else {
				c.sendMessage("You need 33 crafting to make vial!");
			}
			break;

		case 44209:// make5 vial
			if (c.playerLevel[12] >= 33) {
				for (int v5 = 0; v5 < 5; v5++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(50, 229);
					} else {
						v5 = 6;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 33 crafting to make vial.");
				c.RemoveAllWindows();
			}
			break;

		case 44208:// make10 vial
			if (c.playerLevel[12] >= 33) {
				for (int v10 = 0; v10 < 10; v10++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(50, 229);
					} else {
						v10 = 11;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 33 crafting to make vial.");
				c.RemoveAllWindows();
			}
			break;

		case 48108:// make1 orb
			if (c.playerLevel[12] >= 46) {
				if (c.playerHasItemAmount(1775, 1) == true) {
					c.glass(52, 567);
				}
			} else {
				c.sendMessage("You need 46 crafting to make orb!");
			}
			break;

		case 44107:// make5 orb
			if (c.playerLevel[12] >= 46) {
				for (int o5 = 0; o5 < 5; o5++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(52, 567);
					} else {
						o5 = 6;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 46 crafting to make orb!");
				c.RemoveAllWindows();
			}
			break;

		case 44106:// make10 orb
			if (c.playerLevel[12] >= 46) {
				for (int o10 = 0; o10 < 10; o10++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(52, 567);
					} else {
						o10 = 11;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 46 crafting to make orb!");
				c.RemoveAllWindows();
			}
			break;

		case 48112:// make1 beer c.glass
			if (c.playerLevel[12] >= 1) {
				if (c.playerHasItemAmount(1775, 1) == true) {
					c.glass(17, 1919);
				}
			} else {
				c.sendMessage("You need 1 crafting to make beer glass!");
			}
			break;

		case 44111:// make5 beer c.glass
			if (c.playerLevel[12] >= 1) {
				for (int b5 = 0; b5 < 5; b5++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(17, 1919);
					} else {
						b5 = 6;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 1 crafting to make beer glass!");
				c.RemoveAllWindows();
			}
			break;

		case 44110:// make10 beer c.glass
			if (c.playerLevel[12] >= 1) {
				for (int b10 = 0; b10 < 10; b10++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(17, 1919);
					} else {
						b10 = 11;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 1 crafting to make beer glass!");
				c.RemoveAllWindows();
			}
			break;

		case 48116:// make1 candle lantern
			if (c.playerLevel[12] >= 4) {
				if (c.playerHasItemAmount(1775, 1) == true) {
					c.glass(19, 4527);
				}
			} else {
				c.sendMessage("You need 4 crafting to make candle lantern!");
			}
			break;

		case 48115:// make5 candle lantern
			if (c.playerLevel[12] >= 4) {
				for (int c5 = 0; c5 < 5; c5++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(19, 4527);
					} else {
						c5 = 6;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 4 crafting to make candle lantern!");
				c.RemoveAllWindows();
			}
			break;

		case 48114:// make10 candle lantern
			if (c.playerLevel[12] >= 4) {
				for (int c10 = 0; c10 < 10; c10++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(19, 4527);
					} else {
						c10 = 11;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 4 crafting to make candle lantern!");
				c.RemoveAllWindows();
			}
			break;

		case 48120:// make1 oil lamp
			if (c.playerLevel[12] >= 12) {
				if (c.playerHasItemAmount(1775, 1) == true) {
					c.glass(25, 4522);
				}
			} else {
				c.sendMessage("You need 12 crafting to make oil lamp!");
			}
			break;

		case 48119:// make5 oil lamp
			if (c.playerLevel[12] >= 12) {
				for (int c5 = 0; c5 < 5; c5++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(25, 4522);
					} else {
						c5 = 6;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 12 crafting to make oil lamp!");
				c.RemoveAllWindows();
			}
			break;

		case 48118:// make10 oil lamp
			if (c.playerLevel[12] >= 12) {
				for (int c10 = 0; c10 < 10; c10++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(25, 4522);
					} else {
						c10 = 11;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 12 crafting to make oil lamp!");
				c.RemoveAllWindows();
			}
			break;

		case 24059:// make1 fishbowl
			if (c.playerLevel[12] >= 42) {
				if (c.playerHasItemAmount(1775, 1) == true) {
					c.glass(42, 6667);
				}
			} else {
				c.sendMessage("You need 42 crafting to make fishbowl!");
			}
			break;

		case 24058:// make5 fishbowl
			if (c.playerLevel[12] >= 42) {
				for (int c5 = 0; c5 < 5; c5++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(42, 6667);
					} else {
						c5 = 6;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 42 crafting to make fishbowl!");
				c.RemoveAllWindows();
			}
			break;

		case 24057:// make10 fishbowl
			if (c.playerLevel[12] >= 42) {
				for (int c10 = 0; c10 < 10; c10++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(42, 6667);
					} else {
						c10 = 11;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 42 crafting to make fishbowl!");
				c.RemoveAllWindows();
			}
			break;

		case 48124:// make1 lantern lens
			if (c.playerLevel[12] >= 49) {
				if (c.playerHasItemAmount(1775, 1) == true) {
					c.glass(55, 4542);
				}
			} else {
				c.sendMessage("You need 49 crafting to make lantern lens!");
			}
			break;

		case 48123:// make5 lantern lens
			if (c.playerLevel[12] >= 49) {
				for (int c5 = 0; c5 < 5; c5++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(55, 4542);
					} else {
						c5 = 6;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 49 crafting to make lantern lens!");
				c.RemoveAllWindows();
			}
			break;

		case 48122:// make10 lantern lens
			if (c.playerLevel[12] >= 49) {
				for (int c5 = 0; c5 < 10; c5++) {
					if (c.playerHasItemAmount(1775, 1) == true) {
						c.glass(55, 4542);
					} else {
						c5 = 11;
						c.sendMessage("You dont have more molten glass.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need 49 crafting to make lantern lens!");
				c.RemoveAllWindows();
			}
			break;

		}
	}
}
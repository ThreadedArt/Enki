public class StaffMaking {

	public void chargeOrb(int useItemID, int atObjectID, client c) {
		if (c.IsItemInBag(1438) && (c.IsItemInBag(567))) { /* Air Staff */
			if (useItemID == 567 && atObjectID == 879) {
				c.sendMessage("You charge the orb.");
				c.deleteItem(567, c.getItemSlot(567), 1);
				c.addItem(573, 1);
				c.addSkillXP(50, 12);
				c.stillgfx(150, c.absY, c.absX);
			}
		}
		if (c.IsItemInBag(1444) && (c.IsItemInBag(567))) { /* Water Staff */
			if (useItemID == 567 && atObjectID == 879) {
				c.sendMessage("You charge the orb.");
				c.deleteItem(567, c.getItemSlot(567), 1);
				c.addItem(571, 1);
				c.addSkillXP(50, 12);
				c.stillgfx(149, c.absY, c.absX);
			}
		}
		if (c.IsItemInBag(1440) && (c.IsItemInBag(567))) { /* Earth Staff */
			if (useItemID == 567 && atObjectID == 879) {
				c.sendMessage("You charge the orb.");
				c.deleteItem(567, c.getItemSlot(567), 1);
				c.addItem(575, 1);
				c.addSkillXP(50, 12);
				c.stillgfx(151, c.absY, c.absX);
			}
		}
		if (c.IsItemInBag(1442) && (c.IsItemInBag(567))) { /* Fire Staff */
			if (useItemID == 567 && atObjectID == 879) {
				c.sendMessage("You charge the orb.");
				c.deleteItem(567, c.getItemSlot(567), 1);
				c.addItem(569, 1);
				c.addSkillXP(50, 12);
				c.stillgfx(152, c.absY, c.absX);
			}
		}
	}

	public void makeOrb(int useItemID, int atObjectID, client c) {
		if (useItemID == 1925 && atObjectID == 2645) {
			c.deleteItem(1925, c.getItemSlot(1925), 1);
			c.addItem(1783, 1);
			c.sendMessage("You Scoop up some sand");
		}
		if (useItemID == 401 && atObjectID == 2728) {
			c.deleteItem(401, c.getItemSlot(401), 1);
			c.addItem(1781, 1);
			c.setAnimation(883);
			c.addSkillXP(10, 12);
			c.sendMessage("Use these ashes on the furnace.");
		}
		if (c.IsItemInBag(1781) && (c.IsItemInBag(1783))) {
			if (useItemID == 1781 && atObjectID == 2781) {
				c.deleteItem(1781, c.getItemSlot(1781), 1);
				c.deleteItem(1783, c.getItemSlot(1783), 1);
				c.addItem(1775, 1);
				c.setAnimation(899);
				c.addSkillXP(50, 12);
				c.sendMessage("You made Molten Glass");
			}
		}
	}

	public void assembleStaff(int itemUsed, int useWith, client c) {
		if ((itemUsed == 946 && useWith == 1511))
			if (c.playerLevel[12] >= 3) {
				c.deleteItem(1511, c.getItemSlot(1511), 1);
				c.addItem(1379, 1);
				c.sendMessage("You Cut the Logs into a Nicely Crafted Wooden Staff. ");
				c.addSkillXP(15, 12);
				c.setAnimation(885);
			} else {
				c.sendMessage("You need a Crafting level of 3 To Craft Staffs.");
			}
		if ((itemUsed == 569 && useWith == 1379))
			if (c.playerLevel[12] >= 65) {
				c.deleteItem(569, c.getItemSlot(569), 1);
				c.deleteItem(1379, c.getItemSlot(1379), 1);
				c.addItem(1387, 1);
				c.sendMessage("You Place the Fire Orb on the Staff, Making a Fire Staff.");
				c.setAnimation(887);
				c.addSkillXP(85, 12);
			} else {
				c.sendMessage("You need a Crafting level of 65 To craft Fire Staffs.");
			}
		if ((itemUsed == 571 && useWith == 1379))
			if (c.playerLevel[12] >= 30) {
				c.deleteItem(571, c.getItemSlot(571), 1);
				c.deleteItem(1379, c.getItemSlot(1379), 1);
				c.addItem(1383, 1);
				c.sendMessage("You Place the Water Orb on the Staff, Making a Water Staff.");
				c.setAnimation(887);
				c.addSkillXP(40, 12);
			} else {
				c.sendMessage("You need a Crafting level of 30 to Craft Water Staffs.");
			}
		if ((itemUsed == 573 && useWith == 1379))
			if (c.playerLevel[12] >= 12) {
				c.deleteItem(573, c.getItemSlot(573), 1);
				c.deleteItem(1379, c.getItemSlot(1379), 1);
				c.addItem(1381, 1);
				c.sendMessage("You Place the Air Orb on the Staff, Making a Air Staff.");
				c.setAnimation(887);
				c.addSkillXP(30, 12);
			} else {
				c.sendMessage("You need a Crafting level of 12 to Craft Air Staffs.");
			}
		if ((itemUsed == 575 && useWith == 1379))
			if (c.playerLevel[12] >= 45) {
				c.deleteItem(575, c.getItemSlot(575), 1);
				c.deleteItem(1379, c.getItemSlot(1379), 1);
				c.addItem(1385, 1);
				c.sendMessage("You Place the Earth Orb on the Staff, Making a Earth Staff.");
				c.setAnimation(887);
				c.addSkillXP(65, 12);
			} else {
				c.sendMessage("You need a Crafting level of 45 To Craft Earth Staffs.");
			}
		if ((itemUsed == 1785 && useWith == 1775)) {
			c.deleteItem(1775, c.getItemSlot(1775), 1);
			c.addItem(567, 1);
			c.sendMessage("You make an uncharged orb");
			c.setAnimation(884);
			c.addSkillXP(50, 12);
		}
	}

	public void staffMaking(client c) {
		c.sendQuest("Staff Making", 8144); // Title
		c.clearQuestInterface();
		c.sendQuest("Cut a log into a staff", 8145);
		c.sendQuest("Scoop up some sand, use some seaweed on a stove", 8147);
		c.sendQuest("Use the soda ash on a furnace wid a bucket of sand", 8148);
		c.sendQuest("Use a glassblowing pipe on the molten", 8149);
		c.sendQuest("Get a talisman and use the uncharged orb on a fountain", 8150);
		c.sendQuest("Use the charged orb on a staff.", 8152);
		c.sendQuest("", 8153);
		c.sendQuest("Credits:", 8154);
		c.sendQuest("5blacksmith5", 8155);
		c.sendQuest("Deathdroid", 8156);
		c.sendQuest("Dunea", 8157);
		c.sendQuest("And others that helped :D", 8158);
		c.sendQuestSomething(8143);
		c.showInterface(8134);
		c.flushOutStream();
	}

	public void OrbMakin(client c) {
		if (c.actionTimer == 0) {
			if (c.IsItemInBag(1775))
				c.RemoveAllWindows();
			c.deleteItem(1775, c.getItemSlot(1775), 1);
			c.sendMessage("You make an uncharged orb");
			c.addItem(567, 1);
			c.setAnimation(884);
			c.addSkillXP(68, 12);
			c.actionTimer = 5;
		}
	}
}
//Created By: Ian
//File Name: Runecrafting.java
//Information: Handles The Runecrafting Skill
public class Runecrafting {

	public Runecrafting() {
		/** Nothing **/
	}

	public void Runecraft(int LevelToCraft, double ExpGained, int Rune, int x2, int x3, int x4, int x5, int x6, int x7,
			int x8, int x9, int x10, int ID) {
		client c = (client) server.playerHandler.players[ID];
		int AmmountOfEssence = 0;
		if (c.playerHasItem(1436)) {
			if (c.playerLevel[20] >= LevelToCraft) {
				if (c.playerLevel[20] >= 0 && c.playerLevel[20] < x2) {
					AmmountOfEssence = c.amountOfItem(1436);
				}
				if (c.playerLevel[20] >= x2 && c.playerLevel[20] < x3) {
					AmmountOfEssence = c.amountOfItem(1436) * 2;
				}
				if (c.playerLevel[20] >= x3 && c.playerLevel[20] < x4) {
					AmmountOfEssence = c.amountOfItem(1436) * 3;
				}
				if (c.playerLevel[20] >= x4 && c.playerLevel[20] < x5) {
					AmmountOfEssence = c.amountOfItem(1436) * 4;
				}
				if (c.playerLevel[20] >= x5) {
					AmmountOfEssence = c.amountOfItem(1436) * 5;
				}
				for (int i = 0; i < 29; i++) {
					c.deleteItem(1436, c.getItemSlot(1436), i);
				}
				c.addSkillXP(ExpGained * AmmountOfEssence, 20);
				c.addItem(Rune, AmmountOfEssence);
				c.sendMessage("You craft " + AmmountOfEssence + " " + c.GetItemName(Rune) + "s!");
				c.gfx100(186);
				c.setAnimation(791);
			} else if (c.playerLevel[20] <= LevelToCraft - 1) {
				c.sendMessage(
						"You need a Runecrafting level of " + LevelToCraft + " to make " + c.GetItemName(Rune) + "s!");
			}
		} else if (!c.playerHasItem(1436)) {
			c.sendMessage("You need some Rune essence to craft runes!");
		}
	}

	public void RuinLocation(int RuinX, int RuinY, int ID) {
		client c = (client) server.playerHandler.players[ID];
		int TurnX = RuinX, TurnY = RuinY, East = c.absX - RuinX, West = RuinX - c.absX, North = c.absY - RuinY,
				South = RuinY - c.absY;
		boolean IsEast = false, IsWest = false, IsNorth = false, IsSouth = false;
		c.viewTo(TurnX, TurnY);
		if (East >= 2) {
			IsEast = true;
		}
		if (West >= 2) {
			IsWest = true;
		}
		if (North >= 2) {
			IsNorth = true;
		}
		if (South >= 2) {
			IsSouth = true;
		}
		// West
		if (IsEast == true && IsWest == false && IsNorth == false && IsSouth == false) {
			c.sendMessage("The talisman pulls towards the west.");
		} else
		// East
		if (IsWest == true && IsEast == false && IsNorth == false && IsSouth == false) {
			c.sendMessage("The talisman pulls towards the east.");
		} else
		// North
		if (IsSouth == true && IsEast == false && IsNorth == false && IsWest == false) {
			c.sendMessage("The talisman pulls towards the north.");
		} else
		// South
		if (IsNorth == true && IsEast == false && IsSouth == false && IsWest == false) {
			c.sendMessage("The talisman pulls towards the south.");
		} else
		// South West
		if (IsNorth == true && IsEast == true) {
			c.sendMessage("The talisman pulls towards the south-west.");
		} else
		// North West
		if (IsSouth == true && IsEast == true) {
			c.sendMessage("The talisman pulls towards the north-west.");
		} else
		// South East
		if (IsNorth == true && IsWest == true) {
			c.sendMessage("The talisman pulls towards the south-east.");
		} else
		// North East
		if (IsSouth == true && IsWest == true) {
			c.sendMessage("The talisman pulls towards the north-east.");
		}
	}

	public void RuinLocation2(int RuinX, int RuinY, int ID) {
		client c = (client) server.playerHandler.players[ID];
		int TurnX = RuinX, TurnY = RuinY, East = c.absX - RuinX, West = RuinX - c.absX, North = c.absY - RuinY,
				South = RuinY - c.absY;
		boolean IsEast = false, IsWest = false, IsNorth = false, IsSouth = false;
		c.viewTo(TurnX, TurnY);
		if (East >= 4) {
			IsEast = true;
		}
		if (West >= 1) {
			IsWest = true;
		}
		if (North >= 2) {
			IsNorth = true;
		}
		if (South >= 3) {
			IsSouth = true;
		}
		if (c.absX >= 3202 && c.absX <= 3205 && c.absY >= 3167 && c.absY <= 3170) {
			c.sendMessage("You can feel power flowing through the talisman.");
			c.sendMessage("There must be soemthing special about this place.");
		}
		// West
		if (IsEast == true && IsWest == false && IsNorth == false && IsSouth == false) {
			c.sendMessage("The talisman pulls towards the west.");
		} else
		// East
		if (IsWest == true && IsEast == false && IsNorth == false && IsSouth == false) {
			c.sendMessage("The talisman pulls towards the east.");
		} else
		// North
		if (IsSouth == true && IsEast == false && IsNorth == false && IsWest == false) {
			c.sendMessage("The talisman pulls towards the north.");
		} else
		// South
		if (IsNorth == true && IsEast == false && IsSouth == false && IsWest == false) {
			c.sendMessage("The talisman pulls towards the south.");
		} else
		// South West
		if (IsNorth == true && IsEast == true) {
			c.sendMessage("The talisman pulls towards the south-west.");
		} else
		// North West
		if (IsSouth == true && IsEast == true) {
			c.sendMessage("The talisman pulls towards the north-west.");
		} else
		// South East
		if (IsNorth == true && IsWest == true) {
			c.sendMessage("The talisman pulls towards the south-east.");
		} else
		// North East
		if (IsSouth == true && IsWest == true) {
			c.sendMessage("The talisman pulls towards the north-east.");
		}
	}

	public void TeleTo(int X, int Y, int ID) {
		client c = (client) server.playerHandler.players[ID];
		c.teleportToX = X;
		c.teleportToY = Y;
	}

	public void TalismanLocate(int item2ID3, int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (item2ID3 == 1438) {// Air Talisman
			RuinLocation(2984, 3291, ID);
		}
		if (item2ID3 == 1440) {// Earth Talisman
			RuinLocation(3306, 3474, ID);
		}
		if (item2ID3 == 1442) {// Fire Talisman
			RuinLocation(3313, 3255, ID);
		}
		if (item2ID3 == 1444) {// Water Talisman
			RuinLocation(3185, 3165, ID);
		}
		if (item2ID3 == 1446) {// Body Talisman
			RuinLocation(3053, 3445, ID);
		}
		if (item2ID3 == 1448) {// Mind Talisman
			RuinLocation(2982, 3514, ID);
		}
		if (item2ID3 == 1450) {// Blood Talisman
			c.sendMessage("The whereabouts of this is unknown!");
		}
		if (item2ID3 == 1460) {// Soul Talisman
			c.sendMessage("The whereabouts of this is unknown!");
		}
		if (item2ID3 == 1452) {// Chaos Talisman
			RuinLocation(3060, 3591, ID);
		}
		if (item2ID3 == 1454) {// Cosmic Talisman
			RuinLocation2(3202, 3169, ID);
		}
		if (item2ID3 == 1456) {// Death Talisman
			c.sendMessage("The talisman doesn't seem to work, maybe the ruins are deep underground.");
		}
		if (item2ID3 == 1458) {// Law Talisman
			RuinLocation(2858, 3381, ID);
		}
		if (item2ID3 == 1462) {// Nature Talisman
			RuinLocation(2869, 3019, ID);
		}
	}

	public void TalismanTeleport(int useItemID, int atObjectID, int ID) {
		client c = (client) server.playerHandler.players[ID];
		// Using Talismans On Mysterious Ruins

		// Air Talisman
		if (useItemID == 1438 && atObjectID == 2452) {
			TeleTo(2841, 4828, ID);
		}

		// Mind Talisman
		if (useItemID == 1448 && atObjectID == 2453) {
			TeleTo(2793, 4827, ID);
		}

		// Body Talisman
		if (useItemID == 1446 && atObjectID == 2457) {
			TeleTo(2523, 4825, ID);
		}

		// Water Talisman
		if (useItemID == 1444 && atObjectID == 2454) {
			TeleTo(2727, 4832, ID);
		}

		// Fire Talisman
		if (useItemID == 1442 && atObjectID == 2456) {
			TeleTo(2574, 4850, ID);
		}

		// Earth Talisman
		if (useItemID == 1440 && atObjectID == 2455) {
			TeleTo(2655, 4830, ID);
		}

		// Cosmic Talisman
		if (useItemID == 1454 && atObjectID == 2458) {
			TeleTo(2142, 4854, ID);
		}

		// Choas Talisman
		if (useItemID == 1452 && atObjectID == 2459) {
			TeleTo(2282, 4837, ID);
		}

		// Nature Talisman
		if (useItemID == 1462 && atObjectID == 2460) {
			TeleTo(2400, 4834, ID);
		}

		// Law Talisman
		if (useItemID == 1458 && atObjectID == 2461) {
			TeleTo(2464, 4817, ID);
		}

		// Death Talisman
		if (useItemID == 1456 && atObjectID == 2462) {
			TeleTo(2208, 4829, ID);
		}
	}

	public void CraftRunes(int useItemID, int atObjectID, int ID) {
		client c = (client) server.playerHandler.players[ID];
		// Using Essence On Alters

		// Air Alter
		if (useItemID == 1436 && atObjectID == 2478) {
			Runecraft(1, 5, 556, 11, 22, 33, 44, 55, 66, 77, 88, 99, ID);
		}

		// Mind Alter
		if (useItemID == 1436 && atObjectID == 2479) {
			Runecraft(2, 5.5, 558, 14, 28, 42, 56, 70, 84, 98, 98, 98, ID);
		}

		// Water Alter
		if (useItemID == 1463 && atObjectID == 2480) {
			Runecraft(5, 6, 555, 19, 38, 57, 76, 95, 95, 95, 95, 95, ID);
		}

		// Earth Alter
		if (useItemID == 1463 && atObjectID == 2481) {
			Runecraft(9, 7.5, 559, 26, 52, 78, 78, 78, 78, 78, 78, 78, ID);
		}

		// Fire Alter
		if (useItemID == 1463 && atObjectID == 2482) {
			Runecraft(14, 7, 554, 35, 70, 70, 70, 70, 70, 70, 70, 70, ID);
		}

		// Body Alter
		if (useItemID == 1463 && atObjectID == 2483) {
			Runecraft(20, 6.5, 557, 46, 92, 92, 92, 92, 92, 92, 92, 92, ID);
		}

		// Cosmic Alter
		if (useItemID == 1463 && atObjectID == 2484) {
			Runecraft(27, 8, 564, 59, 59, 59, 59, 59, 59, 59, 59, 59, ID);
		}

		// Chaos Alter
		if (useItemID == 1463 && atObjectID == 2485) {
			Runecraft(35, 8.5, 562, 74, 74, 74, 74, 74, 74, 74, 74, 74, ID);
		}

		// Nature Alter
		if (useItemID == 1463 && atObjectID == 2486) {
			Runecraft(45, 9, 561, 91, 91, 91, 91, 91, 91, 91, 91, 91, ID);
		}

		// Law Alter
		if (useItemID == 1463 && atObjectID == 2487) {
			Runecraft(54, 9.5, 557, 54, 54, 54, 54, 54, 54, 54, 54, 54, ID);
		}

		// Death Alter
		if (useItemID == 1463 && atObjectID == 2488) {
			Runecraft(60, 10, 560, 65, 65, 65, 65, 65, 65, 65, 65, 65, ID);
		}
	}

	public void ClickToCraft(int objectID, int ID) {
		client c = (client) server.playerHandler.players[ID];
		// Click The Alter To Craft Runes

		switch (objectID) {
		case 2478: // Air Alter
			Runecraft(1, 5, 556, 11, 22, 33, 44, 55, 66, 77, 88, 99, ID);
			break;

		case 2479: // Mind Alter
			Runecraft(2, 5.5, 558, 14, 28, 42, 56, 70, 84, 98, 98, 98, ID);
			break;

		case 2480: // Water Alter
			Runecraft(5, 6, 555, 19, 38, 57, 76, 95, 95, 95, 95, 95, ID);
			break;

		case 2483: // Earth Alter
			Runecraft(9, 7.5, 559, 26, 52, 78, 78, 78, 78, 78, 78, 78, ID);
			break;

		case 2482: // Fire Alter
			Runecraft(14, 7, 554, 35, 70, 70, 70, 70, 70, 70, 70, 70, ID);
			break;

		case 2481: // Body Alter
			Runecraft(20, 6.5, 557, 46, 92, 92, 92, 92, 92, 92, 92, 92, ID);
			break;

		case 2484: // Cosmic Alter
			Runecraft(27, 8, 564, 59, 59, 59, 59, 59, 59, 59, 59, 59, ID);
			break;

		case 2485: // Chaos Alter
			Runecraft(35, 8.5, 562, 74, 74, 74, 74, 74, 74, 74, 74, 74, ID);
			break;

		case 2486: // Nature Alter
			Runecraft(45, 9, 561, 91, 91, 91, 91, 91, 91, 91, 91, 91, ID);
			break;

		case 2487: // Law Alter
			Runecraft(54, 9.5, 557, 54, 54, 54, 54, 54, 54, 54, 54, 54, ID);
			break;

		case 2488: // Death Alter
			Runecraft(60, 10, 560, 65, 65, 65, 65, 65, 65, 65, 65, 65, ID);
			break;
		}
	}

	public void AbyssRift(int objectID, int ID) {
		client c = (client) server.playerHandler.players[ID];
		// Walking Through Rifts To Get To An Alter.

		switch (objectID) {
		case 7139: // Air Rift
			c.sendMessage("You step into the mysterious rift and end up in the air temple");
			TeleTo(2841, 4828, ID);
			break;

		case 7137: // Water Rift
			c.sendMessage("You step into the mysterious rift and end up in the water temple");
			TeleTo(2727, 4832, ID);
			break;

		case 7130: // Earth Rift
			c.sendMessage("You step into the mysterious rift and end up in the earth temple");
			TeleTo(2655, 4830, ID);
			break;

		case 7129: // Fire Rift
			c.sendMessage("You step into the mysterious rift and end up in the fire temple");
			TeleTo(2574, 4850, ID);
			break;

		case 7131: // Body Rift
			c.sendMessage("You step into the mysterious rift and end up in the body temple");
			TeleTo(2523, 4825, ID);
			break;

		case 7135: // Law Rift
			c.sendMessage("You step into the mysterious rift and end up in the law temple");
			TeleTo(2464, 4817, ID);
			break;

		case 7133: // Nature Rift
			c.sendMessage("You step into the mysterious rift and end up in the nature temple");
			TeleTo(2400, 4834, ID);
			break;

		case 7132: // Cosmic Rift
			c.sendMessage("You step into the mysterious rift and end up in the cosmic temple");
			TeleTo(2142, 4854, ID);
			break;

		case 7134: // Chaos Rift
			c.sendMessage("You step into the mysterious rift and end up in the chaos temple");
			TeleTo(2282, 4837, ID);
			break;

		case 7136: // Death Rift
			c.sendMessage("You step into the mysterious rift and end up in the death temple");
			TeleTo(2208, 4829, ID);
			break;
		}
	}
}
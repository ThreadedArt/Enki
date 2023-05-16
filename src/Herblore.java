//Created By: Ian Corrected by X5
//File Name: Herblore.java
//Information: Handles The Herblore Skill
public class Herblore {

	public static void IdentifyHerb(int LevelNeeded, int Grimy, int Clean, double ExpGained, int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (c.playerLevel[15] >= LevelNeeded) {
			c.addItem(Clean, 1);
			c.deleteItem(Grimy, 1);
			c.addSkillXP(ExpGained, 15);
		} else {
			c.sendMessage("You need a Herblore level of " + LevelNeeded + " to identify this herb.");
		}
	}

	public static void GrindIngredient(int UngroundItem, int GroundItem, int ID) {
		client c = (client) server.playerHandler.players[ID];
		c.deleteItem(UngroundItem, 1);
		c.addItem(GroundItem, 1);
		c.startAnimation(364);
	}

	public static void UnfinishedPotion(int Herb, int UnfPotion, int ID) {
		client c = (client) server.playerHandler.players[ID];
		c.addSkillXP(5, 15);
		c.deleteItem(227, 1);
		c.deleteItem(Herb, 1);
		c.addItem(UnfPotion, 1);
	}

	public static void FinishPotion(int LevelNeeded, int Ingredient, int UnfPotion, int FinishedPotion,
			double EXPGained, int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (c.playerLevel[15] >= LevelNeeded) {
			c.deleteItem(Ingredient, 1);
			c.deleteItem(UnfPotion, 1);
			c.addItem(FinishedPotion, 1);
			c.addSkillXP(EXPGained, 15);
		} else {
			c.sendMessage("You need a Herblore level of " + LevelNeeded + " to make this potion.");
		}
	}

	public static void IdentifyHerb(int HerbID, int ID) {
		client c = (client) server.playerHandler.players[ID];
		// Note: All Unidentified/Grimy Herbs Turn Into Clean Herbs, Wich Are Silabsoft
		// v6.
		switch (HerbID) {

		// Guam Herbs
		case 7744:
			IdentifyHerb(0, HerbID, 7769, 2.5, ID);
			break;

		// Marrentill Herbs
		case 7745:
			IdentifyHerb(5, HerbID, 7770, 3.8, ID);
			break;

		// Tarromin Herbs
		case 7746:
			IdentifyHerb(11, HerbID, 7771, 5, ID);
			break;

		// Harralander Herbs
		case 7747:
			IdentifyHerb(20, HerbID, 7772, 6.3, ID);
			break;

		// Ranaar Herbs
		case 7748:
			IdentifyHerb(25, HerbID, 7773, 7.5, ID);
			break;

		// Toadflax Herbs
		case 9547:
			IdentifyHerb(30, HerbID, 9522, 8, ID);
			break;

		// Irit Herbs
		case 7749:
			IdentifyHerb(40, HerbID, 7774, 8.8, ID);
			break;

		// Avantoe Herbs
		case 7750:
			IdentifyHerb(48, HerbID, 7775, 10, ID);
			break;

		// Kwuarm Herbs
		case 7751:
			IdentifyHerb(54, HerbID, 7776, 11.3, ID);
			break;

		// Snapdragon Herbs
		case 9548:
			IdentifyHerb(59, HerbID, 9523, 11.3, ID);
			break;

		// Cadantine Herbs
		case 7752:
			IdentifyHerb(65, HerbID, 7777, 12.5, ID);
			break;

		// Dwarf Weed Herbs
		case 7753:
			IdentifyHerb(70, HerbID, 7778, 13.8, ID);
			break;

		// Torstol Herbs
		case 7754:
			IdentifyHerb(75, HerbID, 7779, 15, ID);
			break;

		// Lantadyme Herbs
		case 9143:
			IdentifyHerb(0, HerbID, 9141, 13.1, ID);
			break;
		}
	}

	public static void PotionMaking(int itemUsed, int useWith, int ID) {
		// Note: * = Silabsoft v6 Needed

		// Grind Unicorn Horns
		if (itemUsed == 233 && useWith == 237 || itemUsed == 237 && useWith == 233) {
			GrindIngredient(237, 235, ID);
		}

		// Grind Blue Dragon Scales
		if (itemUsed == 233 && useWith == 243 || itemUsed == 243 && useWith == 233) {
			GrindIngredient(234, 241, ID);
		}

		// Grind Chocolate Bars
		if (itemUsed == 233 && useWith == 1973 || itemUsed == 1973 && useWith == 233) {
			GrindIngredient(1973, 1975, ID);
		}

		// Grind Bird Nests*
		if (itemUsed == 233 && useWith == 5075 || itemUsed == 5075 && useWith == 233) {
			GrindIngredient(5075, 11918, ID);
		}

		// Grind Gorak Claws*
		if (itemUsed == 233 && useWith == 13714 || itemUsed == 13714 && useWith == 233) {
			GrindIngredient(13714, 13716, ID);
		}

		// Grind Desert Goat Horns*
		if (itemUsed == 233 && useWith == 14067 || itemUsed == 14067 && useWith == 233) {
			GrindIngredient(14067, 14068, ID);
		}

		// Make Guam Potion (unf)*
		if (itemUsed == 7769 && useWith == 227 || itemUsed == 227 && useWith == 7769) {
			UnfinishedPotion(7769, 7690, ID);
		}

		// Make Marrentill Potion (unf)*
		if (itemUsed == 7770 && useWith == 227 || itemUsed == 227 && useWith == 7770) {
			UnfinishedPotion(7770, 7691, ID);
		}

		// Make Tarromin Potion (unf)*
		if (itemUsed == 7771 && useWith == 227 || itemUsed == 227 && useWith == 7771) {
			UnfinishedPotion(7771, 7692, ID);
		}

		// Make Harralander Potion (unf)*
		if (itemUsed == 7772 && useWith == 227 || itemUsed == 227 && useWith == 7772) {
			UnfinishedPotion(7772, 7693, ID);
		}

		// Make Ranarr Potion (unf)*
		if (itemUsed == 7773 && useWith == 227 || itemUsed == 227 && useWith == 7773) {
			UnfinishedPotion(7773, 7694, ID);
		}

		// Make Irit Potion (unf)*
		if (itemUsed == 7774 && useWith == 227 || itemUsed == 227 && useWith == 7774) {
			UnfinishedPotion(7774, 7695, ID);
		}

		// Make Avantoe Potion (unf)*
		if (itemUsed == 7775 && useWith == 227 || itemUsed == 227 && useWith == 7775) {
			UnfinishedPotion(7775, 7696, ID);
		}

		// Make Kwuarm Potion (unf)*
		if (itemUsed == 7776 && useWith == 227 || itemUsed == 227 && useWith == 7776) {
			UnfinishedPotion(7776, 7697, ID);
		}

		// Make Cadantine Potion (unf)*
		if (itemUsed == 7777 && useWith == 227 || itemUsed == 227 && useWith == 7777) {
			UnfinishedPotion(7777, 7698, ID);
		}

		// Make Dwarf Weed Potion (unf)*
		if (itemUsed == 7778 && useWith == 227 || itemUsed == 227 && useWith == 7778) {
			UnfinishedPotion(7778, 7699, ID);
		}

		// Make Torstol Potion (unf)*
		if (itemUsed == 7779 && useWith == 227 || itemUsed == 227 && useWith == 7779) {
			UnfinishedPotion(7779, 7700, ID);
		}

		// Make Lantadyme Potion (unf)*
		if (itemUsed == 9141 && useWith == 227 || itemUsed == 227 && useWith == 9141) {
			UnfinishedPotion(9141, 9142, ID);
		}

		// Make Toadflax Potion (unf)*
		if (itemUsed == 9522 && useWith == 227 || itemUsed == 227 && useWith == 9522) {
			UnfinishedPotion(9522, 9524, ID);
		}

		// Make Snapdragon Potion (unf)*
		if (itemUsed == 9523 && useWith == 227 || itemUsed == 227 && useWith == 9523) {
			UnfinishedPotion(9523, 9525, ID);
		}

		// Make Magic Essence (unf)*
		if (itemUsed == 13715 && useWith == 227 || itemUsed == 227 && useWith == 13715) {
			UnfinishedPotion(13715, 13717, ID);
		}

		// Make Attack Potion (3)*
		if (itemUsed == 7690 && useWith == 221 || itemUsed == 221 && useWith == 7690) {
			FinishPotion(1, 221, 7690, 121, 25, ID);
		}

		// Make Antipoison Potion (3)*
		if (itemUsed == 7691 && useWith == 235 || itemUsed == 235 && useWith == 7691) {
			FinishPotion(5, 235, 7691, 175, 37.5, ID);
		}

		// Make Strength Potion (3)*
		if (itemUsed == 7692 && useWith == 225 || itemUsed == 225 && useWith == 7692) {
			FinishPotion(12, 225, 7692, 115, 50, ID);
		}

		// Make Restore Potion (3)*
		if (itemUsed == 7693 && useWith == 223 || itemUsed == 223 && useWith == 7693) {
			FinishPotion(22, 223, 7693, 127, 62.5, ID);
		}

		// Make Energy Potion (3)*
		if (itemUsed == 7693 && useWith == 1975 || itemUsed == 1975 && useWith == 7693) {
			FinishPotion(26, 1975, 7693, 3010, 67.5, ID);
		}

		// Make Defence Potion (3)*
		if (itemUsed == 7694 && useWith == 239 || itemUsed == 239 && useWith == 7694) {
			FinishPotion(30, 239, 7694, 113, 75, ID);
		}

		// Make Agility Potion (3)*
		if (itemUsed == 9524 && useWith == 2152 || itemUsed == 2152 && useWith == 9524) {
			FinishPotion(30, 2152, 9524, 3034, 80, ID);
		}

		// Make Combat Potion (3)*
		if (itemUsed == 7694 && useWith == 14068 || itemUsed == 14068 && useWith == 7694) {
			FinishPotion(36, 14068, 7693, 14070, 84, ID);
		}

		// Make Prayer Potion (3)*
		if (itemUsed == 7694 && useWith == 231 || itemUsed == 231 && useWith == 7694) {
			FinishPotion(38, 231, 7694, 139, 85.5, ID);
		}

		// Make Super Attack Potion (3)*
		if (itemUsed == 7695 && useWith == 221 || itemUsed == 221 && useWith == 7695) {
			FinishPotion(45, 221, 7695, 145, 100, ID);
		}

		// Make Superantipoison Potion (3)*
		if (itemUsed == 7695 && useWith == 235 || itemUsed == 235 && useWith == 7695) {
			FinishPotion(48, 235, 7695, 181, 106, ID);
		}

		// Make Fishing Potion (3)*
		if (itemUsed == 7696 && useWith == 231 || itemUsed == 231 && useWith == 7696) {
			FinishPotion(50, 231, 7696, 151, 112.5, ID);
		}

		// Make Super Energy Potion(3)*
		if (itemUsed == 7696 && useWith == 2970 || itemUsed == 2970 && useWith == 7696) {
			FinishPotion(52, 2970, 7696, 3018, 117.5, ID);
		}

		// Make Super Strength Potion (3)*
		if (itemUsed == 7696 && useWith == 225 || itemUsed == 225 && useWith == 7696) {
			FinishPotion(55, 225, 7696, 157, 125, ID);
		}

		// Make Magic Essence Potion (3)*
		if (itemUsed == 13717 && useWith == 13716 || itemUsed == 13716 && useWith == 13717) {
			FinishPotion(57, 13716, 13717, 13720, 130, ID);
		}

		// Make Weapon Poison*
		if (itemUsed == 7697 && useWith == 241 || itemUsed == 241 && useWith == 7697) {
			FinishPotion(57, 241, 7697, 187, 137.5, ID);
		}

		// Make Antifire Potion (3)*
		if (itemUsed == 2483 && useWith == 241 || itemUsed == 221 && useWith == 2483) {
			FinishPotion(69, 221, 9142, 2454, 157.5, ID);
		}

		// Make Ranging Potion (3)*
		if (itemUsed == 7699 && useWith == 245 || itemUsed == 245 && useWith == 7699) {
			FinishPotion(72, 245, 7699, 169, 162.5, ID);
		}

		// Make Magic Potion (3)*
		if (itemUsed == 2483 && useWith == 3138 || itemUsed == 3138 && useWith == 2483) {
			FinishPotion(76, 3138, 2483, 3042, 172.5, ID);
		}

		// Make Zamorak Brew (3)*
		if (itemUsed == 7700 && useWith == 247 || itemUsed == 247 && useWith == 7700) {
			FinishPotion(78, 247, 7700, 189, 175, ID);
		}
	}
}
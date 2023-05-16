//Created By: Jukk (Edited By Ian...)
//File Name: Cooking.java
//Information: Handles The Cooking Skill.

public class Cooking {

	public Cooking() {
	}

	public int Cook = 0,
			CookID = 0, Cookers = 20, CookTime = 0, cookz = 0, CookerID = 0, Preperation = 0, CanCookOn[] = { 114, 2728,
					2729, 2730, 2731, 2859, 3039, 4172, 5375, 8750, 9682, /**/2732, 11404, 11405, 11406 },
			WaterFilled = 10, WaterSource[] = { 1912, 1929, 1937, 6712 }, Fermenting[] = new int[28];

	public boolean BadWine = false, IsCooking = false, DoneFermenting = false;

	public void Cook(int Level, int Level2, int Raw, int Cooked, int Burned, double Exp, String Name, boolean Burnable,
			int ID) {
		client c = (client) server.playerHandler.players[ID];
		int Percent = 25 + (c.playerLevel[7] - (Level / 2));
		int Chance = (int) (java.lang.Math.random() * 100);
		if (c.playerLevel[7] >= Level && c.IsItemInBag(Raw) == true) {
			if (c.playerLevel[7] == Level * 2.5) {
				Burnable = false;
			} else {
				if (CookTime == 1) {
					c.setAnimation(Emote());
					c.deleteItem(Raw, c.GetItemSlot(Raw), 1);
					CookTime = 6;
					c.closeInterface();
					IsCooking = true;
					if (!Burnable) {
						Chance = Percent;
					}
					if (Chance <= Percent) {
						c.addItem(Cooked, 1);
						c.addSkillXP(Exp, 7);
						c.sendMessage("You successfully cook the " + c.GetItemName(Raw) + ".");
					} else {
						c.addItem(Burned, 1);
						c.addSkillXP(Exp / 5, 7);
						c.sendMessage("You accidentally burn the " + c.GetItemName(Raw) + ".");
					}
				}
			}
		} else if (c.playerLevel[7] <= Level2) {
			c.sendMessage("You need a Cooking level of " + Level + " to cook " + Name + ".");
		} else {
			CookTime = 0;
		}
	}

	public void StartCooking(int useItemID, int atObjectID, int ID) {
		client c = (client) server.playerHandler.players[ID];
		CookerID = atObjectID;
		for (int i = 0; i < Cookers; i++) {
			if (IsCooking == false) {
				// Raw Anchovies
				if (useItemID == 321 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Anchovies", ID);
					CookID = 1;
					Cook = 1;

					// Raw Shrimp
				} else if (useItemID == 317 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Shrimps", ID);
					CookID = 2;
					Cook = 2;

					// Raw Tuna
				} else if (useItemID == 359 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Tuna", ID);
					CookID = 3;
					Cook = 3;

					// Raw Lobster
				} else if (useItemID == 377 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Lobster", ID);
					CookID = 4;
					Cook = 4;

					// Raw Bass
				} else if (useItemID == 363 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Bass", ID);
					CookID = 5;
					Cook = 5;

					// Raw Swordfish
				} else if (useItemID == 371 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Swordfish", ID);
					CookID = 6;
					Cook = 6;

					// Raw Monkfish
				} else if (useItemID == 7944 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Monkfish", ID);
					CookID = 7;
					Cook = 7;

					// Raw Shark
				} else if (useItemID == 383 && atObjectID == CanCookOn[i]) {
					CookID = 8;
					WWYLTM.One(useItemID, "Raw Shark", ID);
					Cook = 8;

					// Raw Sea Turtle
				} else if (useItemID == 395 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Sea Turtle", ID);
					CookID = 9;
					Cook = 9;

					// Raw Manta Ray
				} else if (useItemID == 389 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Manta Ray", ID);
					CookID = 10;
					Cook = 10;

					// Raw Chicken
				} else if (useItemID == 2138 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Chicken", ID);
					CookID = 11;
					Cook = 11;

					// Bread Dough
				} else if (useItemID == 2307 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Bread Dough", ID);
					CookID = 12;
					Cook = 12;

					// Raw Sardine
				} else if (useItemID == 327 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Sardine", ID);
					CookID = 13;
					Cook = 13;

					// Raw Herring
				} else if (useItemID == 345 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Herring", ID);
					CookID = 14;
					Cook = 14;

					// Potato
				} else if (useItemID == 1942 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Potato", ID);
					CookID = 15;
					Cook = 15;

					// Raw Mackerel
				} else if (useItemID == 353 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Mackerel", ID);
					CookID = 16;
					Cook = 16;

					// Uncooked Berry Pie
				} else if (useItemID == 2321 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Uncooked Berry Pie", ID);
					CookID = 17;
					Cook = 17;

					// Raw Trout
				} else if (useItemID == 335 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Trout", ID);
					CookID = 18;
					Cook = 18;

					// Raw Cod
				} else if (useItemID == 341 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Cod", ID);
					CookID = 19;
					Cook = 19;

					// Raw Pike
				} else if (useItemID == 349 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Pike", ID);
					CookID = 20;
					Cook = 20;

					// Uncooked Meat Pie
				} else if (useItemID == 2319 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Uncooked Meat Pie", ID);
					CookID = 21;
					Cook = 21;

					// Raw Salmon
				} else if (useItemID == 331 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Salmon", ID);
					CookID = 22;
					Cook = 22;

					// Raw Mud Pie
				} else if (useItemID == 7168 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Mud Pie", ID);
					CookID = 23;
					Cook = 23;

					// Uncooked Apple Pie
				} else if (useItemID == 2317 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Uncooked Apple Pie", ID);
					CookID = 24;
					Cook = 24;

					// Raw Garden Pie
				} else if (useItemID == 7176 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Garden Pie", ID);
					CookID = 25;
					Cook = 25;

					// Uncooked Pizza
				} else if (useItemID == 2287 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Uncooked Pizza", ID);
					CookID = 26;
					Cook = 26;

					// Uncooked Cake
				} else if (useItemID == 1889 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Uncooked Cake", ID);
					CookID = 27;
					Cook = 27;

					// Raw Fish Pie
				} else if (useItemID == 7186 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Fish Pie", ID);
					CookID = 28;
					Cook = 28;

					// Raw Admiral Pie
				} else if (useItemID == 7196 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Admiral Pie", ID);
					CookID = 29;
					Cook = 29;

					// Raw Wild Pie
				} else if (useItemID == 7206 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Wild Pie", ID);
					CookID = 30;
					Cook = 30;

					// Raw Summer Pie
				} else if (useItemID == 7216 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Raw Summer Pie", ID);
					CookID = 31;
					Cook = 31;

					// Uncooked Egg
				} else if (useItemID == 7076 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Uncooked Egg", ID);
					CookID = 32;
					Cook = 32;

					// Chopped Onion
				} else if (useItemID == 1871 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Chopped Onion", ID);
					CookID = 33;
					Cook = 33;

					// Sliced Mushrooms
				} else if (useItemID == 7080 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Sliced Mushrooms", ID);
					CookID = 34;
					Cook = 34;

					// Sliced Mushrooms
				} else if (useItemID == 7080 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Sliced Mushrooms", ID);
					CookID = 34;
					Cook = 34;

					// Sweetcorn
				} else if (useItemID == 5986 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Sweetcorn", ID);
					CookID = 35;
					Cook = 35;

					// Seaweed
				} else if (useItemID == 401 && atObjectID == CanCookOn[i]) {
					WWYLTM.One(useItemID, "Seaweed", ID);
					CookID = 36;
					Cook = 36;
				}
			}
		}
	}

	public void orMaybeNow(int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (Cook == 1) {
			CookTime = 1;
			Cook(1, 0, 321, 319, 323, 30, "Anchovies", true, ID);
		}
		if (Cook == 2) {
			CookTime = 1;
			Cook(1, 0, 317, 315, 323, 35, "Shrimps", true, ID);
		}
		if (Cook == 3) {
			CookTime = 1;
			Cook(30, 29, 359, 361, 367, 100, "Tuna", true, ID);
		}
		if (Cook == 4) {
			CookTime = 1;
			Cook(40, 39, 377, 379, 381, 120, "Lobster", true, ID);
		}
		if (Cook == 5) {
			CookTime = 1;
			Cook(43, 42, 363, 365, 367, 130, "Bass", true, ID);
		}
		if (Cook == 6) {
			CookTime = 1;
			Cook(45, 44, 371, 373, 375, 140, "Swordfish", true, ID);
		}
		if (Cook == 7) {
			CookTime = 1;
			Cook(62, 61, 7944, 7946, 7948, 150, "Monkfish", true, ID);
		}
		if (Cook == 8) {
			CookTime = 1;
			Cook(80, 79, 383, 385, 387, 210, "Shark", true, ID);
		}
		if (Cook == 9) {
			CookTime = 1;
			Cook(82, 81, 395, 397, 399, 212, "Sea Turtle", true, ID);
		}
		if (Cook == 10) {
			CookTime = 1;
			Cook(91, 98, 389, 391, 393, 216, "Manta Ray", true, ID);
		}
		if (Cook == 11) {
			CookTime = 1;
			Cook(1, 0, 2138, 2140, 2144, 30, "Chicken", true, ID);
		}
		if (Cook == 12) {
			CookTime = 1;
			Cook(1, 0, 2307, 2309, 2311, 40, "Bread", true, ID);
		}
		if (Cook == 13) {
			CookTime = 1;
			Cook(1, 0, 327, 335, 369, 40, "Sardine", true, ID);
		}
		if (Cook == 14) {
			CookTime = 1;
			Cook(5, 4, 345, 347, 357, 50, "Herring", true, ID);
		}
		if (Cook == 15) {
			CookTime = 1;
			Cook(7, 6, 1942, 6701, 6699, 15, "Baked Potato", true, ID);
		}
		if (Cook == 16) {
			CookTime = 1;
			Cook(10, 9, 353, 355, 357, 60, "Mackerel", true, ID);
		}
		if (Cook == 17) {
			CookTime = 1;
			Cook(10, 9, 2321, 2325, 2329, 78, "Redberry Pie", true, ID);
		}
		if (Cook == 18) {
			CookTime = 1;
			Cook(15, 14, 335, 333, 343, 70, "Trout", true, ID);
		}
		if (Cook == 19) {
			CookTime = 1;
			Cook(18, 17, 341, 339, 343, 75, "Cod", true, ID);
		}
		if (Cook == 20) {
			CookTime = 1;
			Cook(20, 19, 349, 351, 343, 80, "Pike", true, ID);
		}
		if (Cook == 21) {
			CookTime = 1;
			Cook(20, 19, 2319, 2327, 2329, 80, "Meat Pie", true, ID);
		}
		if (Cook == 22) {
			CookTime = 1;
			Cook(25, 24, 331, 329, 343, 90, "Salmon", true, ID);
		}
		if (Cook == 23) {
			CookTime = 1;
			Cook(29, 28, 7168, 7170, 2329, 128, "Mud Pie", true, ID);
		}
		if (Cook == 24) {
			CookTime = 1;
			Cook(30, 29, 2317, 2323, 2329, 130, "Apple Pie", true, ID);
		}
		if (Cook == 25) {
			CookTime = 1;
			Cook(34, 33, 7176, 7178, 2329, 138, "Garden Pie", true, ID);
		}
		if (Cook == 26) {
			CookTime = 1;
			Cook(35, 34, 2287, 2289, 2305, 143, "Pizza", true, ID);
		}
		if (Cook == 27) {
			CookTime = 1;
			Cook(40, 39, 1889, 1891, 1903, 180, "Cake", true, ID);
		}
		if (Cook == 28) {
			CookTime = 1;
			Cook(47, 46, 7186, 7188, 2329, 164, "Fish Pie", true, ID);
		}
		if (Cook == 29) {
			CookTime = 1;
			Cook(70, 69, 7196, 7198, 2329, 210, "Admiral Pie", true, ID);
		}
		if (Cook == 30) {
			CookTime = 1;
			Cook(85, 84, 7206, 7208, 2329, 240, "Wild Pie", true, ID);
		}
		if (Cook == 31) {
			CookTime = 1;
			Cook(95, 94, 7216, 7218, 2329, 260, "Summer Pie", true, ID);
		}
		if (Cook == 32) {
			CookTime = 1;
			Cook(1, 0, 7076, 7078, 7090, 50, "Scrambled Egg", true, ID);
		}
		if (Cook == 33) {
			CookTime = 1;
			Cook(1, 0, 1871, 7074, 7092, 60, "Fried Onion", true, ID);
		}
		if (Cook == 34) {
			CookTime = 1;
			Cook(1, 0, 7080, 7082, 7094, 60, "Fried Mushrooms", true, ID);
		}
		if (Cook == 34) {
			CookTime = 1;
			Cook(1, 0, 7080, 7082, 7094, 60, "Sliced Mushrooms", true, ID);
		}
		if (Cook == 35) {
			CookTime = 1;
			Cook(1, 0, 5986, 5988, 5990, 70, "Sweetcorn", true, ID);
		}
		if (Cook == 36) {
			CookTime = 1;
			Cook(1, 0, 401, 1781, -1, 15, "Soda Ash", false, ID);
		}
	}

	public void Repeat(int ID) {
		client c = (client) server.playerHandler.players[ID];
		if (CookID == 1) {
			Cook(1, 0, 321, 319, 323, 30, "Anchovies", true, ID);
		} else if (CookID == 2) {
			Cook(1, 0, 317, 315, 323, 35, "Shrimps", true, ID);
		} else if (CookID == 3) {
			Cook(30, 29, 359, 361, 367, 100, "Tuna", true, ID);
		} else if (CookID == 4) {
			Cook(40, 39, 377, 379, 381, 120, "Lobster", true, ID);
		} else if (CookID == 5) {
			Cook(43, 42, 363, 365, 367, 130, "Bass", true, ID);
		} else if (CookID == 6) {
			Cook(45, 44, 371, 373, 375, 140, "Swordfish", true, ID);
		} else if (CookID == 7) {
			Cook(62, 61, 7944, 7946, 7948, 150, "Monkfish", true, ID);
		} else if (CookID == 8) {
			Cook(80, 79, 383, 385, 387, 210, "Shark", true, ID);
		} else if (CookID == 9) {
			Cook(82, 81, 395, 397, 399, 212, "Sea Turtle", true, ID);
		} else if (CookID == 10) {
			Cook(91, 98, 389, 391, 393, 216, "Manta Ray", true, ID);
		} else if (CookID == 11) {
			Cook(1, 0, 2138, 2140, 2144, 30, "Chicken", true, ID);
		} else if (CookID == 12) {
			Cook(1, 0, 2307, 2309, 2311, 40, "Bread", true, ID);
		} else if (CookID == 13) {
			Cook(1, 0, 327, 335, 369, 40, "Sardine", true, ID);
		} else if (CookID == 14) {
			Cook(5, 4, 345, 347, 357, 50, "Herring", true, ID);
		} else if (CookID == 15) {
			Cook(7, 6, 1942, 6701, 6699, 15, "Baked Potato", true, ID);
		} else if (CookID == 16) {
			Cook(10, 9, 353, 355, 357, 60, "Mackerel", true, ID);
		} else if (CookID == 17) {
			Cook(10, 9, 2321, 2325, 2329, 78, "Redberry Pie", true, ID);
		} else if (CookID == 18) {
			Cook(15, 14, 335, 333, 343, 70, "Trout", true, ID);
		} else if (CookID == 19) {
			Cook(18, 17, 341, 339, 343, 75, "Cod", true, ID);
		} else if (CookID == 20) {
			Cook(20, 19, 349, 351, 343, 80, "Pike", true, ID);
		} else if (CookID == 21) {
			Cook(20, 19, 2319, 2327, 2329, 80, "Meat Pie", true, ID);
		} else if (CookID == 22) {
			Cook(25, 24, 331, 329, 343, 90, "Salmon", true, ID);
		} else if (CookID == 23) {
			Cook(29, 28, 7168, 7170, 2329, 128, "Mud Pie", true, ID);
		} else if (CookID == 24) {
			Cook(30, 29, 2317, 2323, 2329, 130, "Apple Pie", true, ID);
		} else if (CookID == 25) {
			Cook(34, 33, 7176, 7178, 2329, 138, "Garden Pie", true, ID);
		} else if (CookID == 26) {
			Cook(35, 34, 2287, 2289, 2305, 143, "Pizza", true, ID);
		} else if (CookID == 27) {
			Cook(40, 39, 1889, 1891, 1903, 180, "Cake", true, ID);
		} else if (CookID == 28) {
			Cook(47, 46, 7186, 7188, 2329, 164, "Fish Pie", true, ID);
		} else if (CookID == 29) {
			Cook(70, 69, 7196, 7198, 2329, 210, "Admiral Pie", true, ID);
		} else if (CookID == 30) {
			Cook(85, 84, 7206, 7208, 2329, 240, "Wild Pie", true, ID);
		} else if (CookID == 31) {
			Cook(95, 94, 7216, 7218, 2329, 260, "Summer Pie", true, ID);
		} else if (CookID == 32) {
			Cook(1, 0, 7076, 7078, 7090, 50, "Scrambled Egg", true, ID);
		} else if (CookID == 33) {
			Cook(1, 0, 1871, 7074, 7092, 60, "Fried Onion", true, ID);
		} else if (CookID == 34) {
			Cook(1, 0, 7080, 7082, 7094, 60, "Fried Mushrooms", true, ID);
		} else if (CookID == 35) {
			Cook(1, 0, 5986, 5988, 5990, 70, "Sweetcorn", true, ID);
		} else if (CookID == 36) {
			Cook(1, 0, 401, 1781, -1, 15, "Soda Ash", false, ID);
		}
	}

	public void Prepare(int itemUsed, int useWith, int ID) {
		// This Basicaly Using Item On Another Item (Packet 53)
		// I'm Using This So You Need To Make The Item, Say
		// Making An Apple Pie, You Need To Use An Cooking Apple
		// On A Pie Shell, To Make An Uncooked Apple Pie And So On...
		client c = (client) server.playerHandler.players[ID];

		// Making An Pie Shell.
		if ((itemUsed == 2313 && useWith == 1953) || (itemUsed == 1953 && useWith == 2313)) {
			MixTogether1(itemUsed, useWith, 2315, ID);
		}
		// Making An Redberry Pie.
		if ((itemUsed == 2315 && useWith == 1951) || (itemUsed == 1951 && useWith == 2315)) {
			MixTogether1(itemUsed, useWith, 2321, ID);
		}
		// Making An Apple Pie.
		if ((itemUsed == 2315 && useWith == 1955) || (itemUsed == 1955 && useWith == 2315)) {
			MixTogether1(itemUsed, useWith, 2319, ID);
		}
		// Making An Meat Pie.
		if ((itemUsed == 2315 && useWith == 2142) || (itemUsed == 2142 && itemUsed == 2315)) {
			MixTogether1(itemUsed, useWith, 2315, ID);
		}
		// Making An Mud Pie.
		if ((itemUsed == 2315 && useWith == 6032) || (itemUsed == 6032 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 1929) || (itemUsed == 1929 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 434) || (itemUsed == 434 && useWith == 2315)) {
			MixTogether3(2315, 6032, 1929, 434, 7168, ID);
		}
		// Making An Garden Pie.
		if ((itemUsed == 2315 && useWith == 1982) || (itemUsed == 1982 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 1957) || (itemUsed == 1957 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 1965) || (itemUsed == 1965 && useWith == 2315)) {
			MixTogether3(2315, 1982, 1957, 1965, 7176, ID);
		}
		// Making An Fish Pie.
		if ((itemUsed == 2315 && useWith == 333) || (itemUsed == 333 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 339) || (itemUsed == 339 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 1942) || (itemUsed == 1942 && useWith == 2315)) {
			MixTogether3(2315, 333, 339, 1942, 7186, ID);
		}
		// Making An Admiral Pie.
		if ((itemUsed == 2315 && useWith == 329) || (itemUsed == 329 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 361) || (itemUsed == 361 && itemUsed == 2315)
				|| (itemUsed == 2315 && useWith == 1942) || (itemUsed == 1942 && useWith == 2315)) {
			MixTogether3(2315, 329, 361, 1942, 7196, ID);
		}
		// Making An Wild Pie.
		if ((itemUsed == 2315 && useWith == 2136) || (itemUsed == 2136 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 2876) || (itemUsed == 2876 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 3226) || (itemUsed == 3226 && useWith == 2315)) {
			MixTogether3(2315, 2136, 2876, 3226, 7206, ID);
		}
		// Making An Summer Pie.
		if ((itemUsed == 2315 && useWith == 5504) || (itemUsed == 5504 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 5982) || (itemUsed == 5982 && useWith == 2315)
				|| (itemUsed == 2315 && useWith == 1955) || (itemUsed == 1955 && useWith == 2315)) {
			MixTogether3(2315, 5504, 5982, 1955, 7216, ID);
		}
		// Making A Cake.
		if ((itemUsed == 1887 && useWith == 1933) || (itemUsed == 1933 && useWith == 1887)
				|| (itemUsed == 1887 && useWith == 1944) || (itemUsed == 1944 && useWith == 1887)
				|| (itemUsed == 1887 && useWith == 1927) || (itemUsed == 1927 && useWith == 1887)) {
			MixTogether3(1887, 1933, 1944, 1927, 1889, ID);
		}
		// Making A Chocolate Cake.
		if ((itemUsed == 1891 && useWith == 1973) || (itemUsed == 1973 && useWith == 1891)) {
			MixTogether1(itemUsed, useWith, 1897, ID);
			c.addSkillXP(30, 7);
		}
		// Making Unfermented Wine.
		if ((itemUsed == 1987 && useWith == 1937) || (itemUsed == 1937 && useWith == 1987)) {
			MixTogether1(itemUsed, useWith, 1995, ID);
			for (int ii = 0; ii < 28; ii++) {
				Fermenting[ii] = 120;
			}
		}
		// Making An Incomplete Pizza.
		if ((itemUsed == 2283 && useWith == 1982) || (itemUsed == 1982 && useWith == 2283)) {
			MixTogether1(itemUsed, useWith, 2285, ID);
		}
		// Making An Uncooked Pizza.
		if ((itemUsed == 2285 && useWith == 1985) || (itemUsed == 1985 && useWith == 2285)) {
			MixTogether1(itemUsed, useWith, 2287, ID);
		}
		// Making An Meat Pizza.
		if ((itemUsed == 2289 && useWith == 2142) || (itemUsed == 2142 && useWith == 2289)) {
			MixTogether1(itemUsed, useWith, 2293, ID);
			c.addSkillXP(26, 7);
		}
		// Making An Anchovie Pizza.
		if ((itemUsed == 2289 && useWith == 319) || (itemUsed == 319 && useWith == 2289)) {
			MixTogether1(itemUsed, useWith, 2293, ID);
			c.addSkillXP(39, 7);
		}
		// Making An Pineapple Pizza.
		if ((itemUsed == 2289 && useWith == 2116) || (itemUsed == 2116 && useWith == 2289)) {
			MixTogether1(itemUsed, useWith, 2301, ID);
			c.addSkillXP(168, 7);
		}
		// Making Spicey Sauce.
		if ((itemUsed == 1923 && useWith == 2169) || (itemUsed == 2169 && useWith == 1923)
				|| (itemUsed == 1923 && useWith == 1550) || (itemUsed == 1550 && useWith == 1923)) {
			MixTogether2(1923, 2169, 1550, 7072, ID);
			c.addSkillXP(25, 7);
		}
		// Making Chilli-con-Carne.
		if ((itemUsed == 1923 && useWith == 7072) || (itemUsed == 7072 && useWith == 1923)
				|| (itemUsed == 1923 && useWith == 2142) || (itemUsed == 2142 && useWith == 1923)) {
			if (c.playerHasItem(946) == true) {
				MixTogether2(1923, 7072, 2142, 7062, ID);
			} else {
				c.sendMessage("You need a knife to chop in the ingredients.");
			}
		}
		// Making Uncooked Egg.
		if ((itemUsed == 1923 && useWith == 1944) || (itemUsed == 1944 && useWith == 1923)) {
			MixTogether1(itemUsed, useWith, 7076, ID);
		}
		// Making Chopped Tomato.
		if ((itemUsed == 1923 && useWith == 1982) || (itemUsed == 1982 && useWith == 1923)) {
			if (c.playerHasItem(946) == true) {
				MixTogether1(itemUsed, useWith, 1869, ID);
			} else {
				c.sendMessage("You need a knife to chop in the ingredients.");
			}
		}
		// Making Egg And Tomato.
		if ((itemUsed == 1869 && useWith == 7076) || (itemUsed == 7076 && useWith == 1869)) {
			MixTogether1(itemUsed, useWith, 7064, ID);
		}
		// Making Chopped Onion.
		if ((itemUsed == 1923 && useWith == 1957) || (itemUsed == 1957 && useWith == 1923)) {
			if (c.playerHasItem(946) == true) {
				MixTogether1(itemUsed, useWith, 1871, ID);
			} else {
				c.sendMessage("You need a knife to chop in the ingredients.");
			}
		}
		// Making Chopped Onion.
		if ((itemUsed == 1923 && useWith == 6004) || (itemUsed == 6004 && useWith == 1923)) {
			if (c.playerHasItem(946) == true) {
				MixTogether1(itemUsed, useWith, 7080, ID);
			} else {
				c.sendMessage("You need a knife to chop in the ingredients.");
			}
		}
		// Making Mushroom And Onion.
		if ((itemUsed == 7082 && useWith == 7084) || (itemUsed == 7084 && useWith == 7082)) {
			MixTogether1(itemUsed, useWith, 7066, ID);
		}
		// Making Chopped Tuna.
		if ((itemUsed == 361 && useWith == 1923) || (itemUsed == 1923 && useWith == 361)) {
			if (c.playerHasItem(946) == true) {
				MixTogether1(itemUsed, useWith, 7086, ID);
			} else {
				c.sendMessage("You need a knife to chop in the ingredients.");
			}
		}
		// Making A Bowl Of Sweetcorn.
		if ((itemUsed == 5988 && useWith == 1923) || (itemUsed == 1923 && useWith == 5988)) {
			if (c.playerHasItem(946) == true) {
				MixTogether1(itemUsed, useWith, 7088, ID);
			} else {
				c.sendMessage("You need a knife to chop in the ingredients.");
			}
		}
		// Making Tuna And Corn.
		if ((itemUsed == 7086 && useWith == 7088) || (itemUsed == 7088 && useWith == 7086)) {
			MixTogether1(itemUsed, useWith, 7068, ID);
		}
		// Making Potato With Butter.
		if ((itemUsed == 6701 && useWith == 6697) || (itemUsed == 6697 && useWith == 6701)) {
			MixTogether1(itemUsed, useWith, 6703, ID);
		}
		// Making Potato With Cheese.
		if ((itemUsed == 6703 && useWith == 1985) || (itemUsed == 1985 && useWith == 6703)) {
			MixTogether1(itemUsed, useWith, 6705, ID);
		}
		// Making Chilli Potato.
		if ((itemUsed == 6703 && useWith == 7062) || (itemUsed == 7062 && useWith == 6703)) {
			MixTogether1(itemUsed, useWith, 7054, ID);
			c.addItem(1923, 1);
		}
		// Making Egg Potato.
		if ((itemUsed == 6703 && useWith == 7064) || (itemUsed == 7064 && useWith == 6703)) {
			MixTogether1(itemUsed, useWith, 7056, ID);
			c.addItem(1923, 1);
		}
		// Making Mushroom Potato.
		if ((itemUsed == 6703 && useWith == 7066) || (itemUsed == 7066 && useWith == 6703)) {
			MixTogether1(itemUsed, useWith, 7058, ID);
			c.addItem(1923, 1);
		}
		// Making Tuna Potato.
		if ((itemUsed == 6703 && useWith == 7068) || (itemUsed == 7068 && useWith == 6703)) {
			MixTogether1(itemUsed, useWith, 7060, ID);
			c.addItem(1923, 1);
		}
		// Using Water + Flour Together.
		for (int i = 0; i < WaterFilled; i++) {
			if ((itemUsed == 1933 && useWith == WaterSource[i]) || (itemUsed == WaterSource[i] && useWith == 1933)) {
				/// Needed To Get Correct Sizes.
				c.sendFrame164(8880);
				c.sendFrame246(8883, 250, 1953);
				c.sendFrame246(8884, 100, 2307);
				c.sendFrame246(8885, 170, 2283);
				c.sendFrame126("Pastry dough", 8889);
				c.sendFrame126("Bread dough", 8893);
				c.sendFrame126("Pizza base", 8897);
			}
		}
	}

	public void MixTogether1(int one, int two, int made, int ID) {
		// Used For Using An Item On An Item To Gain A New Item.
		client c = (client) server.playerHandler.players[ID];
		c.deleteItem(one, c.GetItemSlot(one), 1);
		c.deleteItem(two, c.GetItemSlot(two), 1);
		c.addItem(made, 1);
	}

	public void MixTogether2(int one, int two, int three, int made, int ID) {
		// Used For Using 2 Items On An Item To Gain A New Item.
		client c = (client) server.playerHandler.players[ID];
		if (!c.playerHasItemAmount(two, 1) && !c.playerHasItemAmount(three, 1)) {
			c.sendMessage("You don't have all the required ingredients to make a " + c.GetItemName(made) + ".");
		} else {
			c.deleteItem(one, c.GetItemSlot(one), 1);
			c.deleteItem(two, c.GetItemSlot(two), 1);
			c.deleteItem(three, c.GetItemSlot(three), 1);
			c.addItem(made, 1);
		}
	}

	public void MixTogether3(int one, int two, int three, int four, int made, int ID) {
		// Used For Using 3 Items On An Item To Gain A New Item.
		client c = (client) server.playerHandler.players[ID];
		if (!c.playerHasItemAmount(two, 1) && !c.playerHasItemAmount(three, 1) && !c.playerHasItemAmount(four, 1)) {
			c.sendMessage("You don't have all the required ingredients to make a " + c.GetItemName(made) + ".");
		} else {
			c.deleteItem(one, c.GetItemSlot(one), 1);
			c.deleteItem(two, c.GetItemSlot(two), 1);
			c.deleteItem(three, c.GetItemSlot(three), 1);
			c.deleteItem(four, c.GetItemSlot(four), 1);
			c.addItem(made, 1);
		}
	}

	public int Emote() {
		// This Basicaly Checks The Emote
		// For What Your Cooking On...
		switch (CookerID) {
		// Ranges
		case 114:
			return 883;
		case 2728:
			return 883;
		case 2729:
			return 883;
		case 2730:
			return 883;
		case 2731:
			return 883;
		case 2859:
			return 883;
		case 3039:
			return 883;
		case 4172:
			return 883;
		case 5375:
			return 883;
		case 8750:
			return 883;
		case 9682:
			return 883;
		// Fires
		case 2732:
			return 897;
		case 11404:
			return 897;
		case 11405:
			return 897;
		case 11406:
			return 897;
		default:
			return 897;
		// If Not Stated Do Fire Emote.
		}
	}

	public void process() {
		if (CookTime > 0) {
			CookTime -= 1;
		}

		// Used For Fermenting Wine.
		for (int ii = 0; ii < 28; ii++) {
			if (Fermenting[ii] > 0) {
				Fermenting[ii] -= 1;
			}
		}
	}

}// EOF
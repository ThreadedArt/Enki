//Created By: Ian
//File Name: DestroyItem.java
//Information: Handles Destroyable Items
public class DestroyItem {

	public static int Items[] = { 775, // Cooking Gauntlets
			776, // Goldsmith Gauntlets
			777, // Chaos Gauntlets
			778, // Steel Gauntlets
			6817, // Slender Blade
			6818, // Bow-Sword
			6819, // Large Pouch
			6820, // Relic
			6821, // Orb
			6854, // Puppet Box
			6855, // Bauble Box
			6856, // Bobble Hat
			6857, // Bobble Scalf
			6858, // Jester Hat
			6859, // Jester Scalf
			6860, // Tri-jester Hat
			6861, // Tri-jester Scalf
			6862, // Wooly Hat
			6863, // Wooly Scalf
			6865, // Blue Marionette
			6866, // Green Marionette
			6867, // Red Marionette
			6885, // Progress Hat
			6886, // Progress Hat
			6887, // Progress Hat
			6891, // Arena Book
			6945, // Sandy Hand
			6946, // Beer Soaked Hand
			6947, // Bert's Rota
			6948, // Sandy's Rota
			6949, // A Magic Scroll
			6950, // Magical Orb
			6951, // Magical Orb(a)
			6952, // Truth Serum
			6953, // Bottled Water
			6954, // Redberry Juice
			6955, // Pink Dye
			6956, // Rose Tinted Lense
			6957, // Wizard's Head
			6958, // Sand
			7408, // Draynore Skull
			7409, // Magic Secateurs
			7470, // Cornflour Mixture
			7473, // Brulee
			7476, // Brulee Supreme
			7477, // Evil Chicken's Egg
			7478, // Dragon Token
			7498, // Antique Lamp
			7542, // Cake Of Guidence
			7543, // Raw Guide Cake
			7544, // Enchanted Egg
			7545, // Enchanted Milk
			7546, // Enchanted Flour
			7629, // Dusty Scroll
			7630, // Crate
			7632, // Temple Libary Key
			7633, // Ancient Book
			7634, // Battered Tomb
			7635, // Leather Book
			7649, // Rod Clay Mould
			7774, // Reward Token
			7775, // Reward Token
			7776, // Reward Token
	};

	public static boolean DestroyItem(int Item) {
		for (int i = 0; i < Items.length; i++) {
			if (Items[i] == Item)
				return true;
		}
		return false;
	}

	public static String Line1(int Item) {
		String i = "Are you sure you want to destroy this item?";
		switch (Item) {
		case 775: // Cooking Gauntlets
			return "" + i + "";
		case 776: // Goldsmith Gauntlets
			return "" + i + "";
		case 777: // Chaos Gauntlets
			return "" + i + "";
		case 778: // Steel Gauntlets
			return "" + i + "";
		case 6817: // Slender Blade
			return "" + i + "";
		case 6818: // Bow-Sword
			return "" + i + "";
		case 6819: // Large Pouch
			return "You can get another Large pouch from the edgevile";
		case 6820: // Relic
			return "" + i + "";
		case 6821: // Orb
			return "" + i + "";
		case 6854: // Puppet Box
			return "" + i + "";
		case 6855: // Bauble Box
			return "" + i + "";
		case 6856: // Bobble Hat
			return "" + i + "";
		case 6857: // Bobble Scalf
			return "" + i + "";
		case 6858: // Jester Hat
			return "" + i + "";
		case 6859: // Jester Scalf
			return "" + i + "";
		case 6860: // Tri-jester Hat
			return "" + i + "";
		case 6861: // Tri-jester Scalf
			return "" + i + "";
		case 6862: // Wooly Hat
			return "" + i + "";
		case 6863: // Wooly Scalf
			return "" + i + "";
		case 6865: // Blue Marionette
			return "" + i + "";
		case 6866: // Green Marionette
			return "" + i + "";
		case 6867: // Red Marionette
			return "" + i + "";
		case 6885: // Progress Hat
			return "" + i + "";
		case 6886: // Progress Hat
			return "" + i + "";
		case 6887: // Progress Hat
			return "" + i + "";
		case 6891: // Arena Book
			return "" + i + "";
		case 6945: // Sandy Hand
			return "" + i + "";
		case 6946: // Beer Soaked Hand
			return "" + i + "";
		case 6947: // Bert's Rota
			return "" + i + "";
		case 6948: // Sandy's Rota
			return "" + i + "";
		case 6949: // A Magic Scroll
			return "" + i + "";
		case 6950: // Magical Orb
			return "" + i + "";
		case 6951: // Magical Orb(a)
			return "" + i + "";
		case 6952: // Truth Serum
			return "" + i + "";
		case 6953: // Bottled Water
			return "" + i + "";
		case 6954: // Redberry Juice
			return "" + i + "";
		case 6955: // Pink Dye
			return "" + i + "";
		case 6956: // Rose Tinted Lense
			return "" + i + "";
		case 6957: // Wizard's Head
			return "" + i + "";
		case 6958: // Sand
			return "" + i + "";
		case 7408: // Draynore Skull
			return "" + i + "";
		case 7409: // Magic Secateurs
			return "" + i + "";
		case 7470: // Cornflour Mixture
			return "" + i + "";
		case 7473: // Brulee
			return "" + i + "";
		case 7476: // Brulee Supreme
			return "" + i + "";
		case 7477: // Evil Chicken's Egg
			return "" + i + "";
		case 7478: // Dragon Token
			return "" + i + "";
		case 7498: // Antique Lamp
			return "" + i + "";
		case 7542: // Cake Of Guidence
			return "" + i + "";
		case 7543: // Raw Guide Cake
			return "" + i + "";
		case 7544: // Enchanted Egg
			return "" + i + "";
		case 7545: // Enchanted Milk
			return "" + i + "";
		case 7546: // Enchanted Flour
			return "" + i + "";
		case 7629: // Dusty Scroll
			return "" + i + "";
		case 7630: // Crate
			return "" + i + "";
		case 7632: // Temple Libary Key
			return "" + i + "";
		case 7633: // Ancient Book
			return "" + i + "";
		case 7634: // Battered Tomb
			return "" + i + "";
		case 7635: // Leather Book
			return "" + i + "";
		case 7649: // Rod Clay Mould
			return "" + i + "";
		case 7774: // Reward Token
			return "" + i + "";
		case 7775: // Reward Token
			return "" + i + "";
		case 7776: // Reward Token
			return "" + i + "";
		}
		return "" + i + "";
	}

	public static String Line2(int Item) {
		switch (Item) {
		case 775: // Cooking Gauntlets
			return "";
		case 776: // Goldsmith Gauntlets
			return "";
		case 777: // Chaos Gauntlets
			return "";
		case 778: // Steel Gauntlets
			return "";
		case 6817: // Slender Blade
			return "";
		case 6818: // Bow-Sword
			return "";
		case 6819: // Large Pouch
			return "abbys.";
		case 6820: // Relic
			return "";
		case 6821: // Orb
			return "";
		case 6854: // Puppet Box
			return "";
		case 6855: // Bauble Box
			return "";
		case 6856: // Bobble Hat
			return "";
		case 6857: // Bobble Scalf
			return "";
		case 6858: // Jester Hat
			return "";
		case 6859: // Jester Scalf
			return "";
		case 6860: // Tri-jester Hat
			return "";
		case 6861: // Tri-jester Scalf
			return "";
		case 6862: // Wooly Hat
			return "";
		case 6863: // Wooly Scalf
			return "";
		case 6865: // Blue Marionette
			return "";
		case 6866: // Green Marionette
			return "";
		case 6867: // Red Marionette
			return "";
		case 6885: // Progress Hat
			return "";
		case 6886: // Progress Hat
			return "";
		case 6887: // Progress Hat
			return "";
		case 6891: // Arena Book
			return "";
		case 6945: // Sandy Hand
			return "";
		case 6946: // Beer Soaked Hand
			return "";
		case 6947: // Bert's Rota
			return "";
		case 6948: // Sandy's Rota
			return "";
		case 6949: // A Magic Scroll
			return "";
		case 6950: // Magical Orb
			return "";
		case 6951: // Magical Orb(a)
			return "";
		case 6952: // Truth Serum
			return "";
		case 6953: // Bottled Water
			return "";
		case 6954: // Redberry Juice
			return "";
		case 6955: // Pink Dye
			return "";
		case 6956: // Rose Tinted Lense
			return "";
		case 6957: // Wizard's Head
			return "";
		case 6958: // Sand
			return "";
		case 7408: // Draynore Skull
			return "";
		case 7409: // Magic Secateurs
			return "";
		case 7470: // Cornflour Mixture
			return "";
		case 7473: // Brulee
			return "";
		case 7476: // Brulee Supreme
			return "";
		case 7477: // Evil Chicken's Egg
			return "";
		case 7478: // Dragon Token
			return "";
		case 7498: // Antique Lamp
			return "";
		case 7542: // Cake Of Guidence
			return "";
		case 7543: // Raw Guide Cake
			return "";
		case 7544: // Enchanted Egg
			return "";
		case 7545: // Enchanted Milk
			return "";
		case 7546: // Enchanted Flour
			return "";
		case 7629: // Dusty Scroll
			return "";
		case 7630: // Crate
			return "";
		case 7632: // Temple Libary Key
			return "";
		case 7633: // Ancient Book
			return "";
		case 7634: // Battered Tomb
			return "";
		case 7635: // Leather Book
			return "";
		case 7649: // Rod Clay Mould
			return "";
		case 7774: // Reward Token
			return "";
		case 7775: // Reward Token
			return "";
		case 7776: // Reward Token
			return "";
		}
		return "";
	}
}
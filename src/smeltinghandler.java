public class smeltinghandler {

	public smeltinghandler() {
	}

	public void smelting(int ID) {
		client c = (client) server.playerHandler.players[ID];
		switch (c.actionButtonId) {

		case 29024:// make a1 rune bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 85) {
				for (int r1 = 0; r1 < 1; r1++) {
					if (c.coal >= 8) {
						if ((c.playerHasItemAmount(451, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2363, 1);
							c.addSkillXP(50, 13);
							c.deleteItem(451, c.getItemSlot(451), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							r1 = 2;
							c.sendMessage("You dont have any more coal or rune ore.");
							c.RemoveAllWindows();
						}
					} else {
						r1 = 2;
						c.sendMessage("You dont have any more coal or rune ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 85 smithing to make a rune bar.");
				c.RemoveAllWindows();
			}
			break;

		case 29025:// make a5 rune bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 85) {
				for (int r5 = 0; r5 < 5; r5++) {
					if (c.coal >= 8) {
						if ((c.playerHasItemAmount(451, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2363, 1);
							c.addSkillXP(50, 13);
							c.deleteItem(451, c.getItemSlot(451), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							r5 = 6;
							c.sendMessage("You dont have any more coal or rune ore.");
							c.RemoveAllWindows();
						}
					} else {
						r5 = 6;
						c.sendMessage("You dont have any more coal or rune ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 85 smithing to make a rune bar.");
				c.RemoveAllWindows();
			}
			break;

		case 29026:// make a10 rune bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 85) {
				for (int r10 = 0; r10 < 10; r10++) {
					if (c.coal >= 8) {
						if ((c.playerHasItemAmount(451, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2363, 1);
							c.addSkillXP(50, 13);
							c.deleteItem(451, c.getItemSlot(451), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							r10 = 11;
							c.sendMessage("You dont have any more coal or rune ore.");
							c.RemoveAllWindows();
						}
					} else {
						r10 = 11;
						c.sendMessage("You dont have any more coal or rune ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 85 smithing to make a rune bar.");
				c.RemoveAllWindows();
			}
			break;

		case 29022:// make a1 adamant bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 70) {
				for (int a1 = 0; a1 < 1; a1++) {
					if (c.coal >= 6) {
						if ((c.playerHasItemAmount(449, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2361, 1);
							c.addSkillXP(38, 13);
							c.deleteItem(449, c.getItemSlot(449), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							a1 = 2;
							c.sendMessage("You dont have any more coal or adamantite ore.");
							c.RemoveAllWindows();
						}
					} else {
						a1 = 2;
						c.sendMessage("You dont have any more coal or adamantite ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 70 smithing to make a adamantite bar.");
				c.RemoveAllWindows();
			}
			break;

		case 29020:// make a5 adamant bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 70) {
				for (int a5 = 0; a5 < 5; a5++) {
					if (c.coal >= 6) {
						if ((c.playerHasItemAmount(449, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2361, 1);
							c.addSkillXP(38, 13);
							c.deleteItem(449, c.getItemSlot(449), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							a5 = 6;
							c.sendMessage("You dont have any more coal or adamantite ore.");
							c.RemoveAllWindows();
						}
					} else {
						a5 = 6;
						c.sendMessage("You dont have any more coal or adamantite ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 70 smithing to make a adamantite bar.");
				c.RemoveAllWindows();
			}
			break;

		case 29019:// make a10 adamant bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 70) {
				for (int a10 = 0; a10 < 10; a10++) {
					if (c.coal >= 6) {
						if ((c.playerHasItemAmount(449, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2361, 1);
							c.addSkillXP(38, 13);
							c.deleteItem(449, c.getItemSlot(449), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							a10 = 11;
							c.sendMessage("You dont have any more coal or adamantite ore.");
							c.RemoveAllWindows();
						}
					} else {
						a10 = 11;
						c.sendMessage("You dont have any more coal or adamantite ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 70 smithing to make a adamantite bar.");
				c.RemoveAllWindows();
			}
			break;

		case 29017:// make a1 mithril bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 50) {
				for (int m1 = 0; m1 < 1; m1++) {
					if (c.coal >= 4) {
						if ((c.playerHasItemAmount(447, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2359, 1);
							c.addSkillXP(30, 13);
							c.deleteItem(447, c.getItemSlot(447), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							m1 = 2;
							c.sendMessage("You dont have any more coal or mithril ore.");
							c.RemoveAllWindows();
						}
					} else {
						m1 = 2;
						c.sendMessage("You dont have any more coal or mithril ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 50 smithing to make a mithril bar.");
				c.RemoveAllWindows();
			}
			break;

		case 29016:// make a5 mithril bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 50) {
				for (int m5 = 0; m5 < 5; m5++) {
					if (c.coal >= 4) {
						if ((c.playerHasItemAmount(447, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2359, 1);
							c.addSkillXP(30, 13);
							c.deleteItem(447, c.getItemSlot(447), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							m5 = 6;
							c.sendMessage("You dont have any more coal or mithril ore.");
							c.RemoveAllWindows();
						}
					} else {
						m5 = 6;
						c.sendMessage("You dont have any more coal or mithril ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 50 smithing to make a mithril bar.");
				c.RemoveAllWindows();
			}
			break;

		case 24253:// make a10 mithril bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 50) {
				for (int m10 = 0; m10 < 10; m10++) {
					if (c.coal >= 4) {
						if ((c.playerHasItemAmount(447, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2359, 1);
							c.addSkillXP(30, 13);
							c.deleteItem(447, c.getItemSlot(447), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							m10 = 11;
							c.sendMessage("You dont have any more coal or mithril ore.");
							c.RemoveAllWindows();
						}
					} else {
						m10 = 11;
						c.sendMessage("You dont have any more coal or mithril ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 50 smithing to make a mithril bar.");
				c.RemoveAllWindows();
			}
			break;

		case 15163:// make a1 gold bar
			if (c.playerLevel[13] >= 40) {
				for (int g1 = 0; g1 < 1; g1++) {
					if (c.playerHasItemAmount(444, 1) == true) {
						c.startAnimation(0x383);
						c.addItem(2357, 1);
						c.addSkillXP(23, 13);
						c.deleteItem(444, c.getItemSlot(444), 1);
						c.RemoveAllWindows();
					} else {
						g1 = 2;
						c.sendMessage("You dont have any more gold ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 40 smithing to make a gold bar.");
				c.RemoveAllWindows();
			}
			break;

		case 15162:// make a5 gold bar
			if (c.playerLevel[13] >= 40) {
				for (int g5 = 0; g5 < 5; g5++) {
					if (c.playerHasItemAmount(444, 1) == true) {
						c.startAnimation(0x383);
						c.addItem(2357, 1);
						c.addSkillXP(23, 13);
						c.deleteItem(444, c.getItemSlot(444), 1);
						c.RemoveAllWindows();
					} else {
						g5 = 6;
						c.sendMessage("You dont have any more gold ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 40 smithing to make a gold bar.");
				c.RemoveAllWindows();
			}
			break;

		case 15161:// make a10 gold bar
			if (c.playerLevel[13] >= 40) {
				for (int g10 = 0; g10 < 10; g10++) {
					if (c.playerHasItemAmount(444, 1) == true) {
						c.startAnimation(0x383);
						c.addItem(2357, 1);
						c.addSkillXP(23, 13);
						c.deleteItem(444, c.getItemSlot(444), 1);
						c.RemoveAllWindows();
					} else {
						g10 = 11;
						c.sendMessage("You dont have any more gold ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 40 smithing to make a gold bar.");
				c.RemoveAllWindows();
			}
			break;

		case 15159:// make a1 steel bar
			if (c.playerLevel[13] >= 30) {
				for (int s1 = 0; s1 < 1; s1++) {
					if (c.coal >= 2) {
						if ((c.playerHasItemAmount(440, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.deleteItem(440, c.getItemSlot(440), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.addItem(2353, 1);
							c.addSkillXP(18, 13);
							c.RemoveAllWindows();
						} else {
							s1 = 2;
							c.sendMessage("You dont have any more coal or iron ore.");
							c.RemoveAllWindows();
						}
					} else {
						s1 = 2;
						c.sendMessage("You dont have any more coal or iron ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 30 smithing to make a steel bar.");
				c.RemoveAllWindows();
			}
			break;

		case 15158:// make a5 steel bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 30) {
				for (int s5 = 0; s5 < 5; s5++) {
					if (c.coal >= 2) {
						if ((c.playerHasItemAmount(440, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2353, 1);
							c.addSkillXP(18, 13);
							c.deleteItem(440, c.getItemSlot(440), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							s5 = 6;
							c.sendMessage("You dont have any more coal or iron ore.");
							c.RemoveAllWindows();
						}
					} else {
						s5 = 6;
						c.sendMessage("You dont have any more coal or iron ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 30 smithing to make a steel bar.");
				c.RemoveAllWindows();
			}
			break;

		case 15157:// make a10 steel bar
			c.coal = c.amountOfItem(453);
			if (c.playerLevel[13] >= 30) {
				for (int s10 = 0; s10 < 10; s10++) {
					if (c.coal >= 2) {
						if ((c.playerHasItemAmount(440, 1) == true) && (c.playerHasItemAmount(453, 1) == true)) {
							c.startAnimation(0x383);
							c.addItem(2353, 1);
							c.addSkillXP(18, 13);
							c.deleteItem(440, c.getItemSlot(440), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.deleteItem(453, c.getItemSlot(453), 1);
							c.RemoveAllWindows();
						} else {
							s10 = 11;
							c.sendMessage("You dont have any more coal or iron ore.");
							c.RemoveAllWindows();
						}
					} else {
						s10 = 11;
						c.sendMessage("You dont have any more coal or iron ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 30 smithing to make a steel bar.");
				c.RemoveAllWindows();
			}
			break;

		case 15155:// make a1 silver bar
			if (c.playerLevel[13] >= 20) {
				for (int s1 = 0; s1 < 1; s1++) {
					if (c.playerHasItemAmount(442, 1) == true) {
						c.startAnimation(0x383);
						c.addItem(2355, 1);
						c.addSkillXP(14, 13);
						c.deleteItem(442, c.getItemSlot(442), 1);
						c.RemoveAllWindows();
					} else {
						s1 = 2;
						c.sendMessage("You dont have any more silver ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 20 smithing to make a silver bar.");
				c.RemoveAllWindows();
			}

			break;

		case 15154:// make a5 silver bar
			if (c.playerLevel[13] >= 20) {
				for (int s5 = 0; s5 < 5; s5++) {
					if (c.playerHasItemAmount(442, 1) == true) {
						c.startAnimation(0x383);
						c.addItem(2355, 1);
						c.addSkillXP(14, 13);
						c.deleteItem(442, c.getItemSlot(442), 1);
						c.RemoveAllWindows();
					} else {
						s5 = 6;
						c.sendMessage("You dont have any more silver ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 20 smithing to make a silver bar.");
				c.RemoveAllWindows();
			}

			break;

		case 15153:// make a10 silver bar
			if (c.playerLevel[13] >= 20) {
				for (int s10 = 0; s10 < 10; s10++) {
					if (c.playerHasItemAmount(442, 1) == true) {
						c.startAnimation(0x383);
						c.addItem(2355, 1);
						c.addSkillXP(14, 13);
						c.deleteItem(442, c.getItemSlot(442), 1);
						c.RemoveAllWindows();
					} else {
						s10 = 11;
						c.sendMessage("You dont have any more silver ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 20 smithing to make a silver bar.");
				c.RemoveAllWindows();
			}

			break;

		case 15149:// make a10 iron bar
			if (c.playerLevel[13] >= 15) {
				for (int i10 = 0; i10 < 10; i10++) {
					if (c.playerHasItemAmount(440, 1) == true) {
						c.startAnimation(0x383);
						c.addItem(2351, 1);
						c.addSkillXP(12, 13);
						c.deleteItem(440, c.getItemSlot(440), 1);
						c.RemoveAllWindows();
					} else {
						i10 = 11;
						c.sendMessage("You dont have any more iron ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 15 smithing to make a iron bar.");
				c.RemoveAllWindows();
			}

			break;

		case 15150:// make a5 iron bar
			if (c.playerLevel[13] >= 15) {
				for (int i5 = 0; i5 < 5; i5++) {
					if (c.playerHasItemAmount(440, 1) == true) {
						c.startAnimation(0x383);
						c.addItem(2351, 1);
						c.addSkillXP(12, 13);
						c.deleteItem(440, c.getItemSlot(440), 1);
						c.RemoveAllWindows();
					} else {
						i5 = 6;
						c.sendMessage("You dont have any more iron ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 15 smithing to make a iron bar.");
				c.RemoveAllWindows();
			}
			break;

		case 15151:// make a1 iron bar
			if (c.playerLevel[13] >= 15) {
				for (int i1 = 0; i1 < 1; i1++) {
					if (c.playerHasItemAmount(440, 1) == true) {
						c.startAnimation(0x383);
						c.addItem(2351, 1);
						c.addSkillXP(12, 13);
						c.deleteItem(440, c.getItemSlot(440), 1);
						c.RemoveAllWindows();
					} else {
						i1 = 2;
						c.sendMessage("You dont have any more iron ore.");
						c.RemoveAllWindows();
					}
				}
			} else {
				c.sendMessage("You need level 15 smithing to make a iron bar.");
				c.RemoveAllWindows();
			}
			break;

		case 15147:// make a1 bronze bar
			for (int b1 = 0; b1 < 1; b1++) {
				if ((c.playerHasItemAmount(438, 1) == true) && (c.playerHasItemAmount(436, 1) == true)) {
					c.startAnimation(0x383);
					c.addItem(2349, 1);
					c.addSkillXP(6, 13);
					c.deleteItem(436, c.getItemSlot(436), 1);
					c.deleteItem(438, c.getItemSlot(438), 1);
					c.RemoveAllWindows();
				} else {
					b1 = 2;
					c.sendMessage("You dont have any more cooper or tin ore.");
					c.RemoveAllWindows();
				}
			}
			break;

		case 15146:// make a5 bronze bar
			for (int b5 = 0; b5 < 5; b5++) {
				if ((c.playerHasItemAmount(438, 1) == true) && (c.playerHasItemAmount(436, 1) == true)) {
					c.startAnimation(0x383);
					c.addItem(2349, 1);
					c.addSkillXP(6, 13);
					c.deleteItem(436, c.getItemSlot(436), 1);
					c.deleteItem(438, c.getItemSlot(438), 1);
					c.RemoveAllWindows();
				} else {
					b5 = 6;
					c.sendMessage("You dont have any more cooper or tin ore.");
					c.RemoveAllWindows();
				}
			}
			break;

		case 10247:// make a10 bronze bar
			for (int b10 = 0; b10 < 10; b10++) {
				if ((c.playerHasItemAmount(438, 1) == true) && (c.playerHasItemAmount(436, 1) == true)) {
					c.startAnimation(0x383);
					c.addItem(2349, 1);
					c.addSkillXP(6, 13);
					c.deleteItem(436, c.getItemSlot(436), 1);
					c.deleteItem(438, c.getItemSlot(438), 1);
					c.RemoveAllWindows();
				} else {
					b10 = 11;
					c.sendMessage("You dont have any more cooper or tin ore.");
					c.RemoveAllWindows();
				}
			}
			break;

		}
	}
}
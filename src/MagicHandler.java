
/*
 *	Handle magic spells
 *
 *	Copyright� Mr. Brightsite 2007
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MagicHandler {
	public static int spellID, castAnimation, spellGFX, spellLevel, rune1, rune1Am, rune2, rune2Am, rune3, rune3Am,
			rune4, rune4Am, spellItemID;
	public static int npcX, npcY, npcHP, npcIndex, hitDiff, xp, hitDiff2, usesFirstGfx;
	public static int spellHeight, graphicHeight, endGFX;
	public static String message;
	public static boolean itHeals, itFreezes, itPoisons, itReducesAttack;

	public static int multiSpells[] = { 12963, 13011, 12919, 12881, 12975, 13023, 12929, 12891 };

	public static void addSpell(int id, int Y, int X) {
		int offsetY = (X - npcX) * -1;
		int offsetX = (Y - npcY) * -1;
		if (usesFirstGfx != 0) {
			if (spellID != 1572 && spellID != 1582 && spellID != 1592) {
				GraphicsHandler.createNormalSpell(id, Y, X, offsetY, offsetX, 50, 95, (id + 1), spellHeight,
						spellHeight, (npcIndex + 1), (id + 2), npcY, npcX);
			} else {
				if (spellID == 1572) {
					GraphicsHandler.createNormalSpell(id, Y, X, offsetY, offsetX, 50, 95, (id + 1), spellHeight,
							spellHeight, (npcIndex + 1), 181, npcY, npcX);
				}
				if (spellID == 1582) {
					GraphicsHandler.createNormalSpell(id, Y, X, offsetY, offsetX, 50, 95, (id + 1), spellHeight,
							spellHeight, (npcIndex + 1), 180, npcY, npcX);
				}
				if (spellID == 1592) {
					GraphicsHandler.createNormalSpell(id, Y, X, offsetY, offsetX, 50, 95, (id + 1), spellHeight,
							spellHeight, (npcIndex + 1), 179, npcY, npcX);
				}
			}
		} else {
			GraphicsHandler.createNormalSpell(366, Y, X, offsetY, offsetX, 50, 95, (id + 1), spellHeight, spellHeight,
					(npcIndex + 1), (id + 2), npcY, npcX);
		}
	}

	public static void MagicSpell(int id, int i, boolean multiAttack, boolean stillSpell, int lvl) {
		client p = (client) server.playerHandler.players[i];
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[20];
		boolean EndOfFile = false;
		int ReadMode = 0;
		int offsetY = (p.absX - npcX) * -1;
		int offsetX = (p.absY - npcY) * -1;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./CFG/magicSpells.cfg"));
		} catch (FileNotFoundException fileex) {
			misc.println("[MagicHandler]: magicSpells.cfg: file not found.");
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println("[MagicHandler]: magicSpells.cfg: error loading file.");
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("spell")) {
					spellID = Integer.parseInt(token3[0]);
					castAnimation = Integer.parseInt(token3[1]);
					spellGFX = Integer.parseInt(token3[2]);
					usesFirstGfx = Integer.parseInt(token3[3]);
					spellLevel = Integer.parseInt(token3[4]);
					rune1 = Integer.parseInt(token3[5]);
					rune1Am = Integer.parseInt(token3[6]);
					rune2 = Integer.parseInt(token3[7]);
					rune2Am = Integer.parseInt(token3[8]);
					rune3 = Integer.parseInt(token3[9]);
					rune3Am = Integer.parseInt(token3[10]);
					rune4 = Integer.parseInt(token3[11]);
					rune4Am = Integer.parseInt(token3[12]);
					spellItemID = Integer.parseInt(token3[13]);
					hitDiff = Integer.parseInt(token3[14]);
					xp = Integer.parseInt(token3[15]);
					spellHeight = Integer.parseInt(token3[16]);
					graphicHeight = Integer.parseInt(token3[17]);
					String spellName = token3[18];
					endGFX = (spellGFX + 1);
					if (id == spellID) {
						if (lvl >= spellLevel) {
							if (!p.playerHasItemAmount(rune1, rune1Am) || !p.playerHasItemAmount(rune2, rune2Am)
									|| !p.playerHasItemAmount(rune3, rune3Am)
									|| !p.playerHasItemAmount(rune4, rune4Am)) {
								p.sendMessage("You don't have enough runes to cast this spell.");
							} else {
								if (spellItemID != -1) {
									if (p.playerEquipment[p.playerWeapon] != spellItemID) {
										p.sendMessage(
												"You need a " + p.getItemName(spellItemID) + " to cast this spell.");
										return;
									}
								}
								if (p.playerHasItemAmount(rune1, rune1Am) && p.playerHasItemAmount(rune2, rune2Am)
										&& p.playerHasItemAmount(rune3, rune3Am)
										&& p.playerHasItemAmount(rune4, rune4Am)) {
									if (p.spellHitTimer <= 0) {
										p.startAnimation(castAnimation);
										if (!multiAttack) {
											if (!stillSpell) {
												addSpell(spellGFX, p.absY, p.absX);
												p.spellHitTimer = 4;
												p.castSpell = true;
											} else {
												p.isStillSpell = stillSpell;
												p.stillSpellGFX = spellGFX;
												p.spellHitTimer = 2;
												p.castSpell = true;
											}
										} else {
											p.multiTargetNPC(spellGFX, hitDiff, 10);
										}
										hitDiff2 = misc.random(hitDiff);
										if (spellID == 1160 || spellID == 1163 || spellID == 1166 || spellID == 1169) {
											if (p.playerEquipment[p.playerHands] == 777) {
												hitDiff = (misc.random(hitDiff + 3));
											}
										}
										if (spellID == 1190) {
											if (p.SaradominStrike) {
												hitDiff2 = 30;
											}
										}
										if (spellID == 1191) {
											if (p.GuthixClaws) {
												hitDiff2 = 30;
											}
										}
										if (spellID == 1192) {
											if (p.ZamorakFlames) {
												hitDiff2 = 30;
											}
										}
										p.spellXP = xp;
										p.rune1 = rune1;
										p.rune1Am = rune1Am;
										p.rune2 = rune2;
										p.rune2Am = rune2Am;
										p.rune3 = rune3;
										p.rune3Am = rune3Am;
										p.rune4 = rune4;
										p.rune4Am = rune4Am;
										if (spellID == 12901 || spellID == 12911 || spellID == 12919
												|| spellID == 12929) {
											itHeals = true;
										} else {
											itHeals = false;
										}
										if (spellID == 12861 || spellID == 12881 || spellID == 12871
												|| spellID == 12891) {
											itFreezes = true;
										} else {
											itFreezes = false;
										}
										if (spellID == 12939 || spellID == 12963 || spellID == 12951
												|| spellID == 12975) {
											itPoisons = true;
										} else {
											itPoisons = false;
										}
										if (spellID == 1153 || spellID == 12987 || spellID == 13011 || spellID == 12999
												|| spellID == 13023) {
											itReducesAttack = true;
										} else {
											itReducesAttack = false;
										}
									}
								}
							}
						} else {
							p.sendMessage("You need a magic level of " + spellLevel + " or better to cast this spell.");
						}
					}
				}
			} else {
				if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {

					}
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {

		}
	}
}
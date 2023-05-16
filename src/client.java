//  Source made by Winterlove, Added some stuff thanks to jukk, made by Mrs extro limited                           

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class client extends Player implements Runnable {
	public void PickAxeOwns() { // mining emotes , make a switch if you like.. I cba.
		if (playerEquipment[playerWeapon] == 1275) {
			setAnimation(629);
		} else {
			if (playerEquipment[playerWeapon] == 1271) {
				setAnimation(628);
			} else {
				if (playerEquipment[playerWeapon] == 1273) {
					setAnimation(627);
				} else {
					if (playerEquipment[playerWeapon] == 1269) {
						setAnimation(626);
					} else {
						if (playerEquipment[playerWeapon] == 1267) {
							setAnimation(625);
						} else {
							if (playerEquipment[playerWeapon] == 1265) {
								setAnimation(624);
							}
						}
					}
				}
			}
		}
	}

	public String lastlogin = "127.0.0.1";
	public byte buffer[] = null;
	public static final int bufferSize = 1000000;
	private long lastPickup = 0;
	public int DestroyedItem = -1;
	public long friend64 = 0;
	private java.net.Socket mySock;
	private java.io.InputStream in;
	private java.io.OutputStream out;
	public Calendar cal = new GregorianCalendar();
	public stream inStream = null, outStream = null;
	public Cryption inStreamDecryption = null, outStreamDecryption = null;
	public int prayerDrain, prayerTimer, newDrain, PrayerTimer, oldDrain;
	public boolean noPrayer, drainPray, defLow, strLow, atkLow, defMid, strMid, atkMid, defHigh, strHigh, atkHigh,
			rapidRest, rapidHeal, protItem, protMage, protRange, protMelee, ret, redempt, smite;
	public int coal = 0;
	public int comboAir[] = { 556, 4695, 4696, 4697 };
	public int fightmonster = 0;
	public int respawnneeded = 0;
	public int comboWater[] = { 555, 4694, 4695, 4698 };
	public int comboEarth[] = { 557, 4696, 4698, 4699 };
	public int comboFire[] = { 554, 4694, 4697, 4699 };
	public boolean correctAirRune = false;
	public boolean correctWaterRune = false;
	public boolean correctEarthRune = false;
	public boolean correctFireRune = false;
	public boolean correctRuneA = false;
	public boolean correctRuneB = false;
	public boolean correctRuneC = false;
	public boolean correctRuneD = false;
	public int subRuneA = 0;
	public int subRuneB = 0;
	public int subRuneC = 0;
	public int subRuneD = 0;
	public long lastAction = 0;

	public int iteleX = 0;
	public int iteleY = 0;
	public int iheight = 0;
	public int ilevel = 0;
	public int ixp = 0;
	public int ispace = 0;
	public boolean ancientstele = false;
	public boolean teleBlock = false;
	public boolean teleporting = false;
	public int teleBlockTimer = -1;
	public int teleportDelay = -1;
	public int teleToX = 0;
	public int teleToY = 0;
	public int newHeight = 0;

	public void teleportingProcess() {
		if (teleportDelay != -1) {
			if (teleportDelay > 0) {
				teleportDelay--;
				teleporting = true;
			}
			if (teleportDelay == 1) {
				/*
				 * if(teleToX == 2338 && teleToY == 4747){//apart of teleport matrix
				 * sideBarSetting = 1; sendFrame106(8);
				 * sendFrame126("There has been a fault in the teleportation matrix. Please operate the"
				 * ,360);
				 * sendFrame126("odd appendage out to be forwarded to your destination.",361);
				 * sendFrame253("There has been a fault in the teleportation matrix. Please operate the"
				 * ); sendFrame253("odd appendage out to be forwarded to your destination.");
				 * sendFrame164(359); }
				 */
				teleportToX = teleToX;
				teleportToY = teleToY;
				heightLevel = newHeight;
				teleporting = false;
				teleportDelay = -1;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
		}
	}

	public void teleport() {
		if (!teleporting && teleportDelay == -1) {
			if (ancientstele) {
				teleporting = true;
				teleportDelay = 5;
				emoteStarted = true;
				emoteStopTime = 14;
				sendFrame174(1048, 5, 0);// thanks to dust r i p (i think)
				startAnimation(1979);
				playerGfx(392, 0);
			} else if (!ancientstele) {
				teleporting = true;
				teleportDelay = 4;
				emoteStarted = true;
				emoteStopTime = 7;
				sendFrame174(202, 5, 0);
				startAnimation(714);
				playerGfx(111, 100);
			}
		}
	}

	public void teleport(int teleX, int teleY, int height, int level, int xp, int space, String runeA, int amountA,
			String runeB, int amountB, String runeC, int amountC, String runeD, int amountD, String type,
			boolean members) {
		if (System.currentTimeMillis() - lastAction > 4000) {
			if (teleBlock) {
				sendFrame253("A magical force stops you from teleporting.");
			} else if (!teleBlock) {
				if (isAboveTwentyWilderness()) {
					sendFrame253("You cannot teleport above level 20 wilderness.");
				} else if (!isAboveTwentyWilderness()) {
					if (members && playerIsMember <= 0) {
						sendFrame253("You must log in to a members server to use this spell.");
					} else if (members && playerIsMember >= 1 || !members) {
						if (playerLevel[6] < level) {
							sendFrame253("You need a magic level of " + level + " to cast this spell.");
						} else if (playerLevel[6] >= level) {
							if (runeA.startsWith("air") && !hasAirStaff()) {
								if ((playerHasItemAmount(comboAir[0], amountA) == false)) {
									if ((playerHasItemAmount(comboAir[1], amountA) == false)) {
										if ((playerHasItemAmount(comboAir[2], amountA) == false)) {
											if ((playerHasItemAmount(comboAir[3], amountA) == false)) {
												nextMessageRequest = true;
												runeRequest(runeA);
											}
										}
									}
								}
								if ((playerHasItemAmount(comboAir[0], amountA) == true)) {
									subRuneA = comboAir[0];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboAir[1], amountA) == true)) {
									subRuneA = comboAir[1];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboAir[2], amountA) == true)) {
									subRuneA = comboAir[2];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboAir[3], amountA) == true)) {
									subRuneA = comboAir[3];
									correctRuneA = true;
								}
							} else if (runeA.startsWith("air") && hasAirStaff()) {
								subRuneA = -1;
								correctRuneA = true;
							} else if (runeA.startsWith("water") && !hasWaterStaff()) {
								if ((playerHasItemAmount(comboWater[0], amountA) == false)) {
									if ((playerHasItemAmount(comboWater[1], amountA) == false)) {
										if ((playerHasItemAmount(comboWater[2], amountA) == false)) {
											if ((playerHasItemAmount(comboWater[3], amountA) == false)) {
												nextMessageRequest = true;
												runeRequest(runeA);
											}
										}
									}
								}
								if ((playerHasItemAmount(comboWater[0], amountA) == true)) {
									subRuneA = comboWater[0];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboWater[1], amountA) == true)) {
									subRuneA = comboWater[1];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboWater[2], amountA) == true)) {
									subRuneA = comboWater[2];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboWater[3], amountA) == true)) {
									subRuneA = comboWater[3];
									correctRuneA = true;
								}
							} else if (runeA.startsWith("water") && hasWaterStaff()) {
								subRuneA = -1;
								correctRuneA = true;
							} else if (runeA.startsWith("earth") && !hasEarthStaff()) {
								if ((playerHasItemAmount(comboEarth[0], amountA) == false)) {
									if ((playerHasItemAmount(comboEarth[1], amountA) == false)) {
										if ((playerHasItemAmount(comboEarth[2], amountA) == false)) {
											if ((playerHasItemAmount(comboEarth[3], amountA) == false)) {
												nextMessageRequest = true;
												runeRequest(runeA);
											}
										}
									}
								}
								if ((playerHasItemAmount(comboEarth[0], amountA) == true)) {
									subRuneA = comboEarth[0];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboEarth[1], amountA) == true)) {
									subRuneA = comboEarth[1];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboEarth[2], amountA) == true)) {
									subRuneA = comboEarth[2];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboEarth[3], amountA) == true)) {
									subRuneA = comboEarth[3];
									correctRuneA = true;
								}
							} else if (runeA.startsWith("earth") && hasEarthStaff()) {
								subRuneA = -1;
								correctRuneA = true;
							} else if (runeA.startsWith("fire") && !hasFireStaff()) {
								if ((playerHasItemAmount(comboFire[0], amountA) == false)) {
									if ((playerHasItemAmount(comboFire[1], amountA) == false)) {
										if ((playerHasItemAmount(comboFire[2], amountA) == false)) {
											if ((playerHasItemAmount(comboFire[3], amountA) == false)) {
												nextMessageRequest = true;
												runeRequest(runeA);
											}
										}
									}
								}
								if ((playerHasItemAmount(comboFire[0], amountA) == true)) {
									subRuneA = comboFire[0];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboFire[1], amountA) == true)) {
									subRuneA = comboFire[1];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboFire[2], amountA) == true)) {
									subRuneA = comboFire[2];
									correctRuneA = true;
								} else if ((playerHasItemAmount(comboFire[3], amountA) == true)) {
									subRuneA = comboFire[3];
									correctRuneA = true;
								}
							} else if (runeA.startsWith("fire") && hasFireStaff()) {
								subRuneA = -1;
								correctRuneA = true;
							} else if (runeA.startsWith("law")) {
								if ((playerHasItemAmount(563, amountA) == false)) {
									nextMessageRequest = true;
									runeRequest(runeA);
								}
								if ((playerHasItemAmount(563, amountA) == true)) {
									subRuneA = 563;
									correctRuneA = true;
								}
							} else if (runeA.startsWith("null")) {
								subRuneA = -1;
								correctRuneA = true;
							}
							if (correctRuneA) {
								if (runeB.startsWith("air") && !hasAirStaff()) {
									if ((playerHasItemAmount(comboAir[0], amountB) == false)) {
										if ((playerHasItemAmount(comboAir[1], amountB) == false)) {
											if ((playerHasItemAmount(comboAir[2], amountB) == false)) {
												if ((playerHasItemAmount(comboAir[3], amountB) == false)) {
													nextMessageRequest = true;
													runeRequest(runeB);
												}
											}
										}
									}
									if ((playerHasItemAmount(comboAir[0], amountB) == true)) {
										subRuneB = comboAir[0];
										correctRuneB = true;
									} else if ((playerHasItemAmount(comboAir[1], amountB) == true)) {
										subRuneB = comboAir[1];
										correctRuneB = true;
									} else if ((playerHasItemAmount(comboAir[2], amountB) == true)) {
										subRuneB = comboAir[2];
										correctRuneB = true;
									} else if ((playerHasItemAmount(comboAir[3], amountB) == true)) {
										subRuneB = comboAir[3];
										correctRuneB = true;
									}
								} else if (runeB.startsWith("air") && hasAirStaff()) {
									subRuneB = -1;
									correctRuneB = true;
								} else if (runeB.startsWith("water") && !hasWaterStaff()) {
									if ((playerHasItemAmount(comboWater[0], amountB) == false)) {
										if ((playerHasItemAmount(comboWater[1], amountB) == false)) {
											if ((playerHasItemAmount(comboWater[2], amountB) == false)) {
												if ((playerHasItemAmount(comboWater[3], amountB) == false)) {
													nextMessageRequest = true;
													runeRequest(runeB);
												}
											}
										}
									}
									if ((playerHasItemAmount(comboWater[0], amountB) == true)) {
										subRuneB = comboWater[0];
										correctRuneB = true;
									} else if ((playerHasItemAmount(comboWater[1], amountB) == true)) {
										subRuneB = comboWater[1];
										correctRuneB = true;
									} else if ((playerHasItemAmount(comboWater[2], amountB) == true)) {
										subRuneB = comboWater[2];
										correctRuneB = true;
									} else if ((playerHasItemAmount(comboWater[3], amountB) == true)) {
										subRuneB = comboWater[3];
										correctRuneB = true;
									}
								} else if (runeB.startsWith("water") && hasWaterStaff()) {
									subRuneB = -1;
									correctRuneB = true;
								} else if (runeB.startsWith("fire") && !hasFireStaff()) {
									if ((playerHasItemAmount(comboFire[0], amountB) == false)) {
										if ((playerHasItemAmount(comboFire[1], amountB) == false)) {
											if ((playerHasItemAmount(comboFire[2], amountB) == false)) {
												if ((playerHasItemAmount(comboFire[3], amountB) == false)) {
													nextMessageRequest = true;
													runeRequest(runeB);
												}
											}
										}
									}
									if ((playerHasItemAmount(comboFire[0], amountB) == true)) {
										subRuneB = comboFire[0];
										correctRuneB = true;
									} else if ((playerHasItemAmount(comboFire[1], amountB) == true)) {
										subRuneB = comboFire[1];
										correctRuneB = true;
									} else if ((playerHasItemAmount(comboFire[2], amountB) == true)) {
										subRuneB = comboFire[2];
										correctRuneB = true;
									} else if ((playerHasItemAmount(comboFire[3], amountB) == true)) {
										subRuneB = comboFire[3];
										correctRuneB = true;
									}
								} else if (runeB.startsWith("fire") && hasFireStaff()) {
									subRuneB = -1;
									correctRuneB = true;
								} else if (runeB.startsWith("law")) {
									if ((playerHasItemAmount(563, amountB) == false)) {
										nextMessageRequest = true;
										runeRequest(runeB);
									}
									if ((playerHasItemAmount(563, amountB) == true)) {
										subRuneB = 563;
										correctRuneB = true;
									}
								} else if (runeB.startsWith("blood")) {
									if ((playerHasItemAmount(565, amountB) == false)) {
										nextMessageRequest = true;
										runeRequest(runeB);
									}
									if ((playerHasItemAmount(565, amountB) == true)) {
										subRuneB = 565;
										correctRuneB = true;
									}
								} else if (runeB.startsWith("soul")) {
									if ((playerHasItemAmount(566, amountB) == false)) {
										nextMessageRequest = true;
										runeRequest(runeB);
									}
									if ((playerHasItemAmount(566, amountB) == true)) {
										subRuneB = 566;
										correctRuneB = true;
									}
								} else if (runeB.startsWith("null")) {
									subRuneB = -1;
									correctRuneB = true;
								}
								if (correctRuneB) {
									if (runeC.startsWith("air") && !hasAirStaff()) {
										if ((playerHasItemAmount(comboAir[0], amountC) == false)) {
											if ((playerHasItemAmount(comboAir[1], amountC) == false)) {
												if ((playerHasItemAmount(comboAir[2], amountC) == false)) {
													if ((playerHasItemAmount(comboAir[3], amountC) == false)) {
														nextMessageRequest = true;
														runeRequest(runeC);
													}
												}
											}
										}
										if ((playerHasItemAmount(comboAir[0], amountC) == true)) {
											subRuneC = comboAir[0];
											correctRuneC = true;
										} else if ((playerHasItemAmount(comboAir[1], amountB) == true)) {
											subRuneC = comboAir[1];
											correctRuneC = true;
										} else if ((playerHasItemAmount(comboAir[2], amountB) == true)) {
											subRuneC = comboAir[2];
											correctRuneC = true;
										} else if ((playerHasItemAmount(comboAir[3], amountB) == true)) {
											subRuneC = comboAir[3];
											correctRuneC = true;
										}
									} else if (runeC.startsWith("air") && hasFireStaff()) {
										subRuneC = -1;
										correctRuneC = true;
									} else if (runeC.startsWith("law")) {
										if ((playerHasItemAmount(563, amountC) == false)) {
											nextMessageRequest = true;
											runeRequest(runeC);
										}
										if ((playerHasItemAmount(563, amountC) == true)) {
											subRuneC = 563;
											correctRuneC = true;
										}
									} else if (runeC.startsWith("null")) {
										subRuneC = -1;
										correctRuneC = true;
									}
									if (correctRuneC) {
										if (runeD.startsWith("banana")) {
											if ((playerHasItemAmount(1963, amountD) == false)) {
												nextMessageRequest = true;
												sendFrame253("You do not have a " + runeD + " to cast this spell.");
											}
											if ((playerHasItemAmount(1963, amountD) == true)) {
												subRuneD = 1963;
												correctRuneD = true;
											}
										} else if (runeD.startsWith("null")) {
											subRuneD = -1;
											correctRuneD = true;
										}
										if (correctRuneA && correctRuneB && correctRuneC && correctRuneD
												|| runeA.startsWith("null")) {
											lastAction = System.currentTimeMillis();
											if (!runeA.startsWith("null")) {
												if (subRuneA == subRuneB) {
													if (amountA < amountB) {
														deleteItem(subRuneA, amountB);
													} else if (amountA > amountB) {
														deleteItem(subRuneA, amountA);
													} else if (amountA == amountB) {
														deleteItem(subRuneA, amountA);
													}
												} else if (subRuneA != subRuneB) {
													deleteItem(subRuneA, amountA);
												}
											}
											if (!runeB.startsWith("null")) {
												if (subRuneA != subRuneB) {
													deleteItem(subRuneB, amountB);
												}
											}
											if (!runeC.startsWith("null")) {
												if (subRuneB != subRuneC) {
													deleteItem(subRuneC, amountC);
												}
											}
											if (!runeD.startsWith("null")) {
												deleteItem(subRuneD, amountD);
											}
											correctRuneA = false;
											correctRuneB = false;
											correctRuneC = false;
											correctRuneD = false;
											/*
											 * if(misc.random2(40) == 1){//apart of teleport matix iteleX = teleX;
											 * iteleY = teleY; iheight = height; ilevel = level; ixp = xp; ispace =
											 * space; teleX = 2338; teleY = 4747; teleToX = teleX; teleToY = teleY; }
											 * else {
											 */
											teleToX = absX;
											teleToY = absY;
											teleToX = teleX + misc.random(space);
											teleToY = teleY - misc.random(space);
											// }
											newHeight = height;
											teleportToX = absX;
											teleportToY = absY;
											if (type == "ancient")
												ancientstele = true;
											else
												ancientstele = false;
											teleport();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public void sendFrame174(int soundID, int volume, int delay) {
		outStream.createFrame(174);
		outStream.writeWord(soundID);
		outStream.writeByte(volume);
		outStream.writeWord(delay);
	}

	public boolean isInWilderness_PVP() {// Player verses player
		if (isInArea(2944, 3967, 3327, 3520))
			return true;// Above ground wilderness
		if (!isInArea(2944, 3967, 3327, 3520))
			return false;
		return false;
	}

	public boolean isAboveTwentyWilderness() {
		if (isInArea(2944, 3967, 3327, 3672))
			return true;// Above 20 wilderness ABOVE Ground
		return false;
	}

	public int emoteStopTime = 0;
	public boolean emoteStarted = false;

	public void stopPlayerEmote() {
		if (emoteStopTime > 0) {
			emoteStopTime--;
		}
		if (emoteStarted == true && emoteStopTime == 0) {
			playerSEW = 819;
			playerSER = 824;
			resetAnimation();
			emoteStarted = false;
			outStream.createFrame(1);
			if (server.playerHandler.players.length > 0) {
				for (int i = 0; i < server.playerHandler.players.length; i++) {
					Player p = server.playerHandler.players[i];
					if (p != null) {
						client person = (client) p;
						if (person.playerName != null) {
							person.outStream.createFrame(1);
						}
					}
				}
			}
		}
	}

	public boolean hasAirStaff() {
		if (playerEquipment[playerWeapon] == 1381)
			return true;// Staff_of_air
		if (playerEquipment[playerWeapon] == 1397)
			return true;// Air_battlestaff
		if (playerEquipment[playerWeapon] == 1405)
			return true;// Mystic_air_staff
		return false;
	}

	public boolean hasWaterStaff() {
		if (playerEquipment[playerWeapon] == 1383)
			return true;// Staff_of_water
		if (playerEquipment[playerWeapon] == 1395)
			return true;// Water_battlestaff
		if (playerEquipment[playerWeapon] == 1403)
			return true;// Mystic_water_staff
		if (playerEquipment[playerWeapon] == 6562)
			return true;// Mud_battlestaff
		if (playerEquipment[playerWeapon] == 6563)
			return true;// mysitc_mud_staff
		if (playerEquipment[playerWeapon] == 6562)
			return true;// Mud_battlestaff
		if (playerEquipment[playerWeapon] == 6563)
			return true;// mysitc_mud_staff
		if (playerEquipment[playerWeapon] == 11984)
			return true;// Steam_battlestaff
		if (playerEquipment[playerWeapon] == 11985)
			return true;// Mystic_steam_staff
		return false;
	}

	public boolean hasEarthStaff() {
		if (playerEquipment[playerWeapon] == 1385)
			return true;// Staff_of_earth
		if (playerEquipment[playerWeapon] == 1399)
			return true;// Earth_battlestaff
		if (playerEquipment[playerWeapon] == 1407)
			return true;// Mystic_earth_staff
		if (playerEquipment[playerWeapon] == 6562)
			return true;// Mud_battlestaff
		if (playerEquipment[playerWeapon] == 6563)
			return true;// mysitc_mud_staff
		if (playerEquipment[playerWeapon] == 3053)
			return true;// Lava battlestaff
		if (playerEquipment[playerWeapon] == 3054)
			return true;// Mystic_lava_staff
		return false;
	}

	public boolean hasFireStaff() {
		if (playerEquipment[playerWeapon] == 1387)
			return true;// Staff of fire
		if (playerEquipment[playerWeapon] == 1393)
			return true;// Fire battlestaff
		if (playerEquipment[playerWeapon] == 1401)
			return true;// Mystic fire staff
		if (playerEquipment[playerWeapon] == 3053)
			return true;// Lava battlestaff
		if (playerEquipment[playerWeapon] == 3054)
			return true;// Mystic_lava_staff
		if (playerEquipment[playerWeapon] == 11984)
			return true;// Steam_battlestaff
		if (playerEquipment[playerWeapon] == 11985)
			return true;// Mystic_steam_staff
		return false;
	}

	public boolean nextMessageRequest = false;

	public void runeRequest(String runeType) {
		if (nextMessageRequest == true) {
			outStream.createFrameVarSize(253);
			outStream.writeString("You do not have enough " + runeType + " runes to cast this spell.");
			outStream.endFrameVarSize();
			nextMessageRequest = false;
		}
	}

	public void sendFrame253(String s) {
		outStream.createFrameVarSize(253);
		outStream.writeString(s);
		outStream.endFrameVarSize();
	}

	public boolean isInArea(int x, int y, int x2, int y2) {
		if ((absX >= x && absX <= x2) && (absY <= y && absY >= y2))
			return true;
		return false;
	}

	public boolean Poisoned = false, hasntLoggedin = false, wearing = false, InWildrange = false, strPot = true,
			AttPot = true, defPot = true, cast = false, fired = false, firingspell = false, HasArrows = false,
			DDS2Damg = false, newAnimRequired = false, usingSpecial = false, sbscan = false, sbloop = false;
	public int dots = 0, sbtimer = 0, start[] = { 0, 0, 0, 0 }, IPPart1 = 127, IPPart2 = 0, IPPart3 = 0, IPPart4 = 1,
			MageAttackIndex = -1, oldclick = 0, DDStimer = 0, SpecialDelay = 0, SpecDelay = 0,
			// Doubletimer = 0,
			// SpecialDelay2 = 0,
			// SpecDelay2 = 0,
			fcastid = 0, fcasterY = 0, fcasterX = 0, foffsetY = 0, foffsetX = 0, fangle = 0, fspeed = 0, fmgfxid = 0,
			fsh = 0, feh = 0, ffinishid = 0, fenemyY = 0, fenemyX = 0, fLockon = 0, actionButtonId = 0, friendslot = 0,
			playerItemAmountCount = 0, packetSize = 0, packetType = -1, smitimer = 300, resetanim = 8, sb = 0,
			attempts = 0, teleX = 0, teleY = 0, newheightLevel = 0, newAnimDelay = 0, newAnim = 0, specialAmount = 100,
			specialDelay = 0, specialDamage = 0, specialDamage2 = 0, keepItem = 0, keepItem2 = 0, keepItem3 = 0,
			keepItemAmount = 0, keepItemAmount2 = 0, keepItemAmount3 = 0, Tradecompete = 0, Publicchat = 0, spellID = 0,
			healTimer = 0, strPotTimer = 0, AttPotTimer = 0, defPotTimer = 0, oldX, oldY, oldHeight,
			SaveGameTimer = ServerCP.SaveGameTimer, pCHead, pCBeard, pCTorso, pCArms, pCHands, pCLegs, pCFeet, pColor,
			pGender, i, gender, newheadicon2 = 0,
			totalz = totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2])
					+ getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5])
					+ getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8])
					+ getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0])
					+ getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13])
					+ getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[16])
					+ getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19])
					+ getLevelForXP(playerXP[20])),
			apickupid = -1, apickupx = -1, apickupy = -1, Destroiitam = 0, lowMemoryVersion = 0, timeOutCounter = 0,
			returnCode = 2, readPtr, writePtr, OriginalWeapon = -1, OriginalShield = -1, lastlogintime, stairs = 0,
			stairDistance = 1, stairDistanceAdd = 0, doors = -1, skillX = -1, skillY = -1, PickUpID = 0,
			PickUpAmount = 0, PickUpDelete = 0, CombatExpRate = 1, SkillID = 0, abc, cba, aaa, abc2, LogoutDelay = 0,
			EntangleDelay = 0, PkingDelay = 0, LoopAttDelay = 0, PoisonDelay = 9999999, PoisonClear = 0,
			AnnouncementTimer = 5, Poison = 0, KillerId = playerId, heal = 0, leftwild = 0, ancients = 0, wildtele = 0,
			Crash = 0, muted = 0, playerLastLogin = 20060101;
	private int somejunk, healing[] = { 0, 0, 0, -1, -1 }, useitems[] = { -1, -1, -1, -1 },
			prayer[] = { 0, 1, 0, 1, -1, -1 }, XremoveSlot = 0, XinterfaceID = 0, XremoveID = 0, emotes = 0,
			WanneBank = 0, WanneShop = 0;
	public int firetimer = 0;
	public int firetimers = 0;
	public int firetimers1 = 0;
	public int firetimers2 = 0;
	public int firetimers3 = 0;
	public int firetimers4 = 0;
	public int firetimers5 = 0;
	public int firetimers6 = 0;
	public int firetimers7 = 0;
	public int firetimers8 = 0;
	public int firetimers9 = 0;
	public int firetimers10 = 0;
	public int firetimers11 = 0;
	public int firetimers12 = 0;
	public int firetimers13 = 0;
	public int firetimers14 = 0;
	public int firetimers15 = 0;
	public int firetimers16 = 0;
	public int firetimers17 = 0;
	public int firetimers18 = 0;
	public int firetimers19 = 0;
	public int previousX = 0;
	public int previousY = 0;
	public int previousX1 = 0;
	public int previousY1 = 0;
	public int previousX2 = 0;
	public int previousY2 = 0;
	public int previousX3 = 0;
	public int previousY3 = 0;
	public int previousX4 = 0;
	public int previousY4 = 0;
	public int previousX5 = 0;
	public int previousY5 = 0;
	public int previousX6 = 0;
	public int previousY6 = 0;
	public int previousX7 = 0;
	public int previousY7 = 0;
	public int previousX8 = 0;
	public int previousY8 = 0;
	public int previousX9 = 0;
	public int previousY9 = 0;
	public int previousX10 = 0;
	public int previousY10 = 0;
	public int previousX11 = 0;
	public int previousY11 = 0;
	public int previousX12 = 0;
	public int previousY12 = 0;
	public int previousX13 = 0;
	public int previousY13 = 0;
	public int previousX14 = 0;
	public int previousY14 = 0;
	public int previousX15 = 0;
	public int previousY15 = 0;
	public int previousX16 = 0;
	public int previousY16 = 0;
	public int previousX17 = 0;
	public int previousY17 = 0;
	public int previousX18 = 0;
	public int previousY18 = 0;
	public int previousX19 = 0;
	public int previousY19 = 0;
	public int xpgained = 1;// Change this and the fire xp will be times by that, so 40 time 1 = 40, 40
							// times 100 = 4000.
	public int stunDelay = 0;
	public int[] rockTimer = new int[7]; // 7 types of rocks this will allow mining for
	public int[] rockX = new int[7]; // 7 different rocks can be used at the same time by players
	public int[] rockY = new int[7]; // 7 different rocks can be used at the same time by players
	public int stunTimer = 0; // stunning players after random
	public int rockExplode = 0; // random explosion
	public boolean hasPick = false; // does the player have a pick
	public int bestTime = 0; // best time you can have when mining
	public int mineAnim = 0; // animation for mining
	public int barID = 0; // id of the bar that will be given
	public int mineXP = 0; // xp to be given from mining
	public int[] rockID = new int[7]; // id of the rock object being mined
	public int numTypes = 6; /* 6 basic trees (norm, oak, willow, maple, yew, magic) */
	public int[] treeTimer = new int[numTypes], /* timer for each tree type */
			treeX = new int[numTypes], /* tree's X coords */
			treeY = new int[numTypes], /* tree;s Y coords */
			treeID = new int[numTypes], /* ID of each tree to be replaced */
			stumpID = new int[numTypes]; /* ID of each stump to replace tree */
	public boolean hasAxe = false; /* does the player have an axe */
	public int bestWCTime = 0, /* best time you can have whenwcing */
			chopAnim = 0, /* animation for wcing */
			logID = 0, /* id of the log that will be given */
			chopXP = 0, /* xp to be given from wcing */
			numLogs = 0, /* number of logs user will get from a tree */
			wcRandom = 0; /* Does player get a tree spirit? */
	public int[] QuestInterface = { 8145, 8147, 8148, 8149, 8150, 8151, 8152, 8153, 8154, 8155, 8156, 8157, 8158, 8159,
			8160, 8161, 8162, 8163, 8164, 8165, 8166, 8167, 8168, 8169, 8170, 8171, 8172, 8173, 8174, 8175, 8176, 8177,
			8178, 8179, 8180, 8181, 8182, 8183, 8184, 8185, 8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193, 8194, 8195,
			12174, 12175, 12176, 12177, 12178, 12179, 12180, 12181, 12182, 12183, 12184, 12185, 12186, 12187, 12188,
			12189, 12190, 12191, 12192, 12193, 12194, 12195, 12196, 12197, 12198, 12199, 12200, 12201, 12202, 12203,
			12204, 12205, 12206, 12207, 12208, 12209, 12210, 12211, 12212, 12213, 12214, 12215, 12216, 12217, 12218,
			12219, 12220, 12221, 12222, 12223 };

	public void clearQuestInterface() {
		for (int x = 0; x < QuestInterface.length; x++) {
			sendFrame126("", QuestInterface[x]);
		}
	}

	private void setSidebarMage() {
		try {
			outStream.createFrame(106); // Writes the frame 106 out.
			outStream.writeByteC(6); // Tells client to switch to the magic interface
			updateRequired = true; // Updates
			appearanceUpdateRequired = true; // Updates
		} catch (Exception E) {
			sendMessage("Error switching sidebar back to the magic interface! ~ Called Enzo");
		}
	}

	public void sendQuestSomething(int id) {
		outStream.createFrame(79);
		outStream.writeWordBigEndian(id);
		outStream.writeWordA(0);
		flushOutStream();
	}

	public void loadObjects() {
		server.ObjectSpawn.Spawn(playerId);
	}

	public void frame36(int i1, int i2) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(i1);
		outStream.writeByte(i2);
	}

	public void stillgfx4(int id, int Y, int X) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);// Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);// Graphic id
		outStream.writeByte(0);// height of the spell above it's basic place, i think it's written in pixels
								// 100 pixels higher
		outStream.writeWord(0);// Time before casting the graphic
	}

	public void stillgfxz(int id, int Y, int X, int height, int time) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.stillgfxz2(id, Y, X, height, time);
					}
				}
			}
		}
	}

	public void stillgfxz2(int id, int Y, int X, int height, int time) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);// Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id);// Graphic id
		outStream.writeByte(height);// height of the spell above it's basic place, i think it's written in pixels
									// 100 pixels higher
		outStream.writeWord(time);// Time before casting the graphic
	}

	public void stillgfx(int id, int Y, int X) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.stillgfx4(id, Y, X);
					}
				}
			}
		}
	}

	public void stillgfx2(int id, int heightS, int Y, int X, int timerBCS) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.stillgfx3(id, heightS, Y, X, timerBCS);
					}
				}
			}
		}
	}

	public void stillgfx3(int id, int heightS, int Y, int X, int timeBCS) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);// Tiles away (X >> 4 + Y & 7) //Tiles Away From absX and absY
		outStream.writeWord(id);// Graphic id
		outStream.writeByte(heightS);// height of the spell above it's basic place, i think it's written in pixels
										// 100 pixels higher //100
		outStream.writeWord(timeBCS);// Time before casting the graphic
	}

	/*
	 * These are all the ancient spells that are multi-target ;)
	 */
	public boolean MultiTargetSpell(int i) {
		if (i == 12963 || i == 13011 || i == 12919 || i == 12881 || i == 12975 || i == 13023 || i == 12929
				|| i == 12891) {
			return true;
		}
		return false;
	}

	/*
	 * These are mainly god spells since they aren't multi-target
	 */
	public boolean StillSpell(int i) {
		if (i == 1190 || i == 1191 || i == 1192) {
			return true;
		}
		return false;
	}

	/*
	 * ========================================== Damage npc and delete runes when
	 * the last GFX has hit the npc, if the hit is succesful.
	 * ========================================== Copyright� Mr. Brightsite 2007
	 */
	public void appendHitToNpc(int index, int hitDiff, boolean stillSpell) // Used for MagicHandler
	{
		if (hitDiff != 0) {
			if (stillSpell) {
				isStillSpell = false;
				stillgfx2(stillSpellGFX, MagicHandler.graphicHeight, MagicHandler.npcY, MagicHandler.npcX, 2);
				stillSpellGFX = -1;
			}
			if ((MagicHandler.npcHP - hitDiff < 0)) {
				hitDiff = MagicHandler.npcHP;
			}
			if (MagicHandler.itHeals) {
				if (misc.random(2) == 1) {
					NewHP = (playerLevel[playerHitpoints] + (hitDiff / 4));
					if (NewHP >= getLevelForXP(playerXP[playerHitpoints])) {
						NewHP = getLevelForXP(playerXP[playerHitpoints]);
					}
					sendMessage("You drain the enemies health.");
					refreshSkills();
				}
			}
			if (MagicHandler.itFreezes) {
				sendMessage("" + getFrozenMessage(MagicHandler.spellID) + "");
				if (server.npcHandler.npcs[index].freezeTimer <= 0) {
					server.npcHandler.npcs[index].freezeTimer = getFreezeTimer(MagicHandler.spellID);
				}
			}
			if (MagicHandler.itReducesAttack) {
				if (misc.random(2) == 1) {
					if (server.npcHandler.npcs[index].reducedAttack <= 0) {
						sendMessage("You reduce the enemies attack power.");
						server.npcHandler.npcs[index].MaxHit -= (hitDiff / 2);
						if ((server.npcHandler.npcs[index].MaxHit
								- (hitDiff / 2)) < server.npcHandler.npcs[index].MaxHit) {
							server.npcHandler.npcs[index].MaxHit = 0;
						}
						server.npcHandler.npcs[index].reducedAttack = getAttackTimer(MagicHandler.spellID);
					}
				}
			}
			if (MagicHandler.itPoisons) {
				if (misc.random(2) == 1) {
					if (server.npcHandler.npcs[index].poisonTimer <= 0) {
						sendMessage("You poison the enemy.");
						server.npcHandler.npcs[index].poisonTimer = 120;
						server.npcHandler.npcs[index].poisonDmg = true;
						server.npcHandler.poisonNpc(index);
					}
				}
			}
			deleteItem(rune1, getItemSlot(rune1), rune1Am);
			deleteItem(rune2, getItemSlot(rune2), rune2Am);
			deleteItem(rune3, getItemSlot(rune3), rune3Am);
			deleteItem(rune4, getItemSlot(rune4), rune4Am);
			addSkillXP((spellXP), 6);
			addSkillXP((spellXP / 2), 3);
			server.npcHandler.npcs[index].hitDiff = hitDiff;
			server.npcHandler.npcs[index].Killing[playerId] += hitDiff;
			server.npcHandler.npcs[index].updateRequired = true;
			server.npcHandler.npcs[index].hitUpdateRequired = true;
		}
	}

	public String getFrozenMessage(int spell) {
		if (spell == 12861 || spell == 12881 || spell == 12871 || spell == 12891) {
			return "You freeze the enemy.";
		}
		if (spell == 1572) {
			return "You bind the enemy.";
		}
		if (spell == 1582) {
			return "You snare the enemy.";
		}
		if (spell == 1592) {
			return "You entangle the enemy.";
		}
		return "You freeze the enemy.";
	}

	public int getFreezeTimer(int spell) {
		if (spell == 12861) {
			return 10;
		}
		if (spell == 12881) {
			return 20;
		}
		if (spell == 12871) {
			return 30;
		}
		if (spell == 12891) {
			return 40;
		}
		if (spell == 1572) {
			return 10;
		}
		if (spell == 1582) {
			return 20;
		}
		if (spell == 1592) {
			return 30;
		}
		return 10;
	}

	public int getAttackTimer(int spell) {
		if (spell == 12987) {
			return 10;
		}
		if (spell == 13011) {
			return 20;
		}
		if (spell == 12999) {
			return 30;
		}
		if (spell == 13023) {
			return 40;
		}
		if (spell == 1153) {
			return 10;
		}
		return 10;
	}

	public void multiTargetNPC(int spellId, int maxDamage, int range) {
		for (int i = 0; i < server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (distanceToPoint(server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY) <= range
						&& !server.npcHandler.npcs[i].IsDead) {
					if (server.npcHandler.npcs[i].heightLevel == heightLevel) {
						int damage = misc.random(maxDamage);
						if (damage == 0) {
							stillgfx2(85, MagicHandler.graphicHeight, server.npcHandler.npcs[i].absY,
									server.npcHandler.npcs[i].absX, 2);
						} else {
							if (server.npcHandler.npcs[i].HP >= 0) {
								if (server.npcHandler.npcs[i].HP - damage < 0) {
									damage = server.npcHandler.npcs[i].HP;
								}
								int myHP = playerLevel[playerHitpoints];
								if (MagicHandler.itHeals) {
									if (misc.random(2) == 1) {
										NewHP = (playerLevel[playerHitpoints] + (hitDiff / 4));
										if (NewHP >= getLevelForXP(playerXP[playerHitpoints])) {
											NewHP = getLevelForXP(playerXP[playerHitpoints]);
										}
										sendMessage("You drain the enemies health.");
										refreshSkills();
									}
								}
								if (MagicHandler.itFreezes) {
									sendMessage("" + getFrozenMessage(MagicHandler.spellID) + "");
									if (server.npcHandler.npcs[i].freezeTimer <= 0) {
										server.npcHandler.npcs[i].freezeTimer = getFreezeTimer(MagicHandler.spellID);
									}
								}
								if (MagicHandler.itReducesAttack) {
									if (misc.random(2) == 1) {
										if (server.npcHandler.npcs[i].reducedAttack <= 0) {
											sendMessage("You reduce the enemies attack power.");
											server.npcHandler.npcs[i].MaxHit -= (hitDiff / 2);
											if ((server.npcHandler.npcs[i].MaxHit
													- (hitDiff / 2)) < server.npcHandler.npcs[i].MaxHit) {
												server.npcHandler.npcs[i].MaxHit = 0;
											}
											server.npcHandler.npcs[i].reducedAttack = getAttackTimer(
													MagicHandler.spellID);
										}
									}
								}
								if (MagicHandler.itPoisons) {
									if (misc.random(2) == 1) {
										if (server.npcHandler.npcs[i].poisonTimer <= 0) {
											sendMessage("You poison the enemy.");
											server.npcHandler.npcs[i].poisonTimer = 120;
											server.npcHandler.npcs[i].poisonDmg = true;
											server.npcHandler.poisonNpc(i);
										}
									}
								}
								stillgfx2(spellId, MagicHandler.graphicHeight, server.npcHandler.npcs[i].absY,
										server.npcHandler.npcs[i].absX, 2);
								server.npcHandler.npcs[i].hitDiff = damage;
								server.npcHandler.npcs[i].Killing[playerId] += damage;
								server.npcHandler.npcs[i].updateRequired = true;
								server.npcHandler.npcs[i].hitUpdateRequired = true;
							}
						}
					}
				}
			}
		}
	}

//Skillz

//checks for axe and set animation
	public void checkTime(int i) {
		double bestWCTimez = 0;
		if (playerEquipment[playerWeapon] == 1351) { // bronze axe
			chopAnim = 879;
			hasAxe = true;
			bestWCTimez = 40 - (0.1 * getLevelForXP(playerXP[8]));
		} else if (playerEquipment[playerWeapon] == 1349) { // iron axe
			chopAnim = 877;
			hasAxe = true;
			bestWCTimez = 38 - (0.1 * getLevelForXP(playerXP[8]));
		} else if (playerEquipment[playerWeapon] == 1353) { // steel axe
			chopAnim = 875;
			hasAxe = true;
			bestWCTimez = 36 - (0.1 * getLevelForXP(playerXP[8]));
		} else if (playerEquipment[playerWeapon] == 1355) { // mith axe
			chopAnim = 871;
			hasAxe = true;
			bestWCTimez = 34 - (0.1 * getLevelForXP(playerXP[8]));
		} else if (playerEquipment[playerWeapon] == 1357) { // addy axe
			chopAnim = 869;
			hasAxe = true;
			bestWCTimez = 32 - (0.1 * getLevelForXP(playerXP[8]));
		} else if (playerEquipment[playerWeapon] == 1359) { // rune axe
			chopAnim = 867;
			hasAxe = true;
			bestWCTimez = 30 - (0.1 * getLevelForXP(playerXP[8]));
		} else {
			hasAxe = false;
		}

		bestWCTimez += .5;
		bestWCTime = (int) bestWCTimez + i * 3;
		bestWCTime = misc.random(bestWCTime) + i * 2 + 1;
		if (bestWCTime < 10)
			bestWCTime = 10;
	}

//begins the wcing process, and sets up variables
	public void startChop(int i, int req, int logIDz, int multXP, int treeid, int stumpid) {
		checkTime(i);
		if (actionTimer == 0) {
			if (treeTimer[i] == 0) {
				if (playerLevel[8] >= req) {
					if (hasAxe) {
						if (numLogs == 0)
							numLogs = misc.random(14) + 1;
						treeTimer[i] = bestWCTime;
						setAnimation(chopAnim);
						logID = logIDz;
						chopXP = multXP * playerLevel[8];
						treeID[i] = treeid;
						stumpID[i] = stumpid;
						actionTimer = 4;
					} else {
						sendMessage("You need an axe equiped to chop this tree.");
					}
				} else {
					sendMessage("You need level " + req + "+ woodcutting to chop this tree.");
				}
			} else {
				sendMessage("You are already woodcutting!");
			}
		}
	}

//the actual wcing process
	public void doChop(int i) {
		// makes the animation for wcing keep going
		if (treeTimer[i] % 5 == 0) {
			setAnimation(chopAnim);
		}

		// timer for wcing/replacing the object
		if (treeTimer[i] > 0) {
			treeTimer[i]--;
		} else {
			treeTimer[i] = 0;
		}

		// tj007razor: tree spirit random
		if (treeTimer[i] > 6) {
			wcRandom = misc.random(2000);
		}
		if (wcRandom == 1) {
			stillgfx(184, absX, absY - 1);
			treeTimer[i] = 0;
			sendMessage("A tree spirit appears and attacks you!");
			wcRandom = 0;
			setAnimation(0x328); // reset anim
			spiritAttack(playerId);
		}

		// adds the log for multilog trees
		if (numLogs > 0) {
			if (i == 0) // normal trees get one log
				numLogs = 1;
			if (treeTimer[i] == 7) {
				addItem(logID, 1);
				addSkillXP(chopXP, 8);
				sendMessage("You get some " + GetItemName(logID) + ".");
				checkTime(i);
				treeTimer[i] = bestWCTime;
				numLogs--;
			}
		} else {
			// replaces the object
			if (treeTimer[i] == 6) { // once a multilog tree is out of logs, replace the object
				setAnimation(0x328); // reset anim
				ReplaceObject2(treeX[i], treeY[i], stumpID[i], -2, 10);
				sendMessage("The tree has run out of logs!");
			} else if (treeTimer[i] == 1) {
				ReplaceObject2(treeX[i], treeY[i], treeID[i], -2, 10);
			}
		}
	}

//make the tree spirit attack you
	public void spiritAttack(int idZ) {
		for (int i = 0; i < server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (server.npcHandler.npcs[i].npcType == 443) {
					if (playerId == idZ && playerName.length() > 0) {
						server.npcHandler.npcs[i].StartKilling = idZ;
						server.npcHandler.npcs[i].RandomWalk = true;
						server.npcHandler.npcs[i].IsUnderAttack = true;
						server.npcHandler.npcs[i].updateRequired = true;
					}
				}
			}
		}
	}
//----------end woodcutting

//checks for picks and set animation
	public void checkPick(int i) {
		double bestTimez = 0;
		if (playerEquipment[playerWeapon] == 1265) { // bronze pick
			mineAnim = 625;
			hasPick = true;
			bestTimez = 40 - (0.1 * getLevelForXP(playerXP[14]));
		} else if (playerEquipment[playerWeapon] == 1267) { // iron pick
			mineAnim = 626;
			hasPick = true;
			bestTimez = 38 - (0.1 * getLevelForXP(playerXP[14]));
		} else if (playerEquipment[playerWeapon] == 1269) { // steel pick
			mineAnim = 627;
			hasPick = true;
			bestTimez = 36 - (0.1 * getLevelForXP(playerXP[14]));
		} else if (playerEquipment[playerWeapon] == 1273) { // mith pick
			mineAnim = 629;
			hasPick = true;
			bestTimez = 34 - (0.1 * getLevelForXP(playerXP[14]));
		} else if (playerEquipment[playerWeapon] == 1271) { // addy pick
			mineAnim = 628;
			hasPick = true;
			bestTimez = 32 - (0.1 * getLevelForXP(playerXP[14]));
		} else if (playerEquipment[playerWeapon] == 1275) { // rune pick
			mineAnim = 624;
			hasPick = true;
			bestTimez = 30 - (0.1 * getLevelForXP(playerXP[14]));
		}
		bestTimez += .5;
		bestTime = (int) bestTimez;
		bestTime = misc.random(bestTime) + i * 2 + 1;
		if (bestTime <= 7)
			bestTime = 8;
	}

//begins the mining process, and sets up variables
	public void startMine(int i, int req, int barIDz, int multXP, int rockid, int objectX, int objectY) {
		checkPick(i);
		if (rockTimer[i] == 0) {
			if (stunTimer == 0) {
				if (playerLevel[14] >= req) {
					if (hasPick) {
						rockX[i] = objectX;
						rockY[i] = objectY;
						rockTimer[i] = bestTime;
						setAnimation(mineAnim);
						barID = barIDz;
						mineXP = multXP * playerLevel[14];
						rockID[i] = rockid;
					} else {
						sendMessage("You need a pick axe equiped to mine this rock.");
					}
				} else {
					sendMessage("You need level " + req + "+ mining to mine this rock.");
				}
			} else {
				sendMessage("You are stunned!");
			}
		} else {
			sendMessage("You are already mining!");
		}
	}

	public void doMine(int i) {
//process for mining rocks

		// makes the animation for mining keep going
		if ((rockTimer[i] == 35) || (rockTimer[i] == 30) || (rockTimer[i] == 25) || (rockTimer[i] == 20)
				|| (rockTimer[i] == 15) || (rockTimer[i] == 10) || (rockTimer[i] == 5)) {
			setAnimation(mineAnim);
		}

		// timer for mining/replacing the object
		if (rockTimer[i] > 0) {
			rockTimer[i]--;
		} else {
			rockTimer[i] = 0;
		}

		// tj007razor: random occasional rock explosion
		if (rockTimer[i] > 7) {
			rockExplode = misc.random(150);
		}
		if (rockExplode == 1) {
			stillgfx(189, rockY[i], rockX[i]);
			rockTimer[i] = 0;
			sendMessage("The rock explodes and forces you to stop mining!");
			setAnimation(0x328);
			rockExplode = 0;
			stillgfx(80, absY, absX);
			EntangleDelay = 30;
			stunTimer = 30;
		}

		// adds the bar and replaces the object
		if (rockTimer[i] == 7) {
			addItem(barID, 1);
			setAnimation(0x328);
			addSkillXP(mineXP, 14);
			ReplaceObject2(rockX[i], rockY[i], 450, -2, 10);
		} else if (rockTimer[i] == 1) {
			ReplaceObject2(rockX[i], rockY[i], rockID[i], -2, 10);
		}
	}
//----end mining

	public void pickPocket(int lev, int loot, int amount, int exp, String name, int hit) {
		int chance = 0;
		if (playerLevel[17] >= lev) {
			chance = 3 + (playerLevel[17] - lev);
			if (actionTimer == 0) {
				if (misc.random(chance) >= 3) {
					addSkillXP(exp, 17);
					sendMessage("You pickpocket the " + name);
					addItem(loot, amount);
					actionTimer = 5;
					startAnimation(881);
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					sendMessage("You fail to pickpocket the " + name);
					stunDelay = 10;
					actionTimer = 10;
					stillgfx(80, absY, absX);
					startAnimation(881);
					hitDiff = misc.random(hit);
					hitUpdateRequired = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
		} else if (playerLevel[17] < lev) {
			sendMessage("You need a thieving level of " + lev + " to pickpocket " + name);
		}
	}

	public void WalkTo(int x, int y) {
		newWalkCmdSteps = (Math.abs((x + y)));
		if (newWalkCmdSteps % 1 != 0)
			newWalkCmdSteps /= 1;
		if (++newWalkCmdSteps > walkingQueueSize) {
			println("Warning: WalkTo(" + packetType + ") command contains too many steps (" + newWalkCmdSteps + ").");
			newWalkCmdSteps = 0;
		}

		int firstStepX = absX;
		int tmpFSX = firstStepX;
		firstStepX -= mapRegionX * 8;
		for (i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
			tmpNWCX[i] = newWalkCmdX[i];
			tmpNWCY[i] = newWalkCmdY[i];
		}
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int firstStepY = absY;
		int tmpFSY = firstStepY;
		firstStepY -= mapRegionY * 8;
		newWalkCmdIsRunning = (inStream.readSignedByteC() == 1);
		for (i = 0; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] += firstStepX;
			newWalkCmdY[i] += firstStepY;
		}
	}

	public void glass(int XP, int item) {
		setAnimation(884);
		addItem(item, 1);
		addSkillXP(XP, 12);
		sendMessage("You make a " + getItemName(item) + ".");
		deleteItem(1775, getItemSlot(1775), 1);
		RemoveAllWindows();
	}

//End of Skillz

	public void frame174(int i1, int i2, int i3) // another thing, tested doesn't logout, looks like something to do
													// with music
	{
		outStream.createFrame(174);
		outStream.writeWord(i1);
		outStream.writeByte(i2);
		outStream.writeWord(i3);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void Object(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(objectX, objectY) <= 60) {
						person.createNewTileObject(objectX, objectY, NewObjectID, Face, ObjectType);
					}
				}
			}
		}
	}

	public void deleteItem(int id, int amount) {
		deleteItem(id, GetItemSlot(id), amount);
	}

	public void customCommand(String command) {
		actionAmount++;
		if (command.startsWith("pickup")) {
			try {
				int newItemID = Integer.parseInt(command.substring(7, 12));
				int newItemAmount = Integer.parseInt(command.substring(13));
				if (/* newItemID <= 200000 && */newItemID >= 0) {
					addItem(newItemID, newItemAmount);
				} else {
					sendMessage("No such item.");
				}
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as ::pickup 00995 100 - Try to make item id 5 digits!");
			}
		}
		if (command.equalsIgnoreCase("master") && playerRights >= 3) {
			addSkillXP(14910000, 0);
			addSkillXP(14910000, 1);
			addSkillXP(14910000, 2);
			addSkillXP(14910000, 3);
			addSkillXP(14910000, 4);
			addSkillXP(14910000, 5);
			addSkillXP(14910000, 6);
			addSkillXP(14910000, 7);
			addSkillXP(14910000, 8);
			addSkillXP(14910000, 9);
			addSkillXP(14910000, 10);
			addSkillXP(14910000, 11);
			addSkillXP(14910000, 12);
			addSkillXP(14910000, 13);
			addSkillXP(14910000, 14);
			addSkillXP(14910000, 15);
			addSkillXP(14910000, 16);
			addSkillXP(14910000, 17);
			addSkillXP(14910000, 18);
			addSkillXP(14910000, 19);
			addSkillXP(14910000, 20);
			addSkillXP(14910000, 21);
			addSkillXP(14910000, 22);
			addSkillXP(14910000, 23);
			sendMessage("" + playerName + " .. your a Master.");
		}
		if (command.startsWith("char")) {
			showInterface(3559);
		} else if (command.startsWith("tele")) {
			teleportToX = Integer.parseInt(command.substring(5, 9));
			teleportToY = Integer.parseInt(command.substring(10, 14));
		}
		if (command.equals("coords")) {
			sendMessage("X: " + absX);
			sendMessage("Y: " + absY);
			sendMessage("Height: " + heightLevel);
		}
		if (command.startsWith("switch")) {
			NpcDialogue = 0;
			NpcDialogueSend = false;
			animation(435, absY, absX);
			RemoveAllWindows();
			if (ancients == 1) {
				setSidebarInterface(6, 1151); // magic tab (ancient = 12855);
				ancients = 0;
				sendMessage("You convert to normal magic!");
			} else {
				setSidebarInterface(6, 12855); // magic tab (ancient = 12855);
				ancients = 1;
				sendMessage("You convert to ancient magics!");
			}
		}
		if (command.startsWith("start") && playerRights >= 2) {
			{
				teleportToX = 3551;
				teleportToY = 9711;
			}
		}
		if (command.startsWith("pk")) {
			teleportToX = 3243;
			teleportToY = 3521;
		}
		if (command.startsWith("dungeon")) {
			teleportToX = 2273;
			teleportToY = 4697;
		}
		if (command.startsWith("caves")) {
			teleportToX = 2439;
			teleportToY = 5169;
		}
//wealth
		if (command.startsWith("nethelp")) {
			clearQuestInterface();
			sendFrame126("Close Window", 8135);// Close Text
			sendFrame126("@dre@NET Wealth Guide", 8144);// NET Wealth Guide
			sendFrame126("@bla@--------------------------", 8145);
			sendFrame126("@bla@NET Wealth is a system which allows other", 8147);
			sendFrame126("@bla@players to see how much money you have.", 8148);
			sendFrame126("@bla@--------------------------", 8149);
			sendFrame126("@bla@To start NET Wealth you need at least", 8150);
			sendFrame126("@bla@10,000,000gp in your @red@INVENTORY@bla@.", 8151);
			sendFrame126("@bla@You recieve unique armour based on your NET Wealth", 8152);
			sendFrame126("@bla@--------------------------", 8153);
			sendFrame126("@bla@10,000,000 = Red Decorative.", 8154);
			sendFrame126("@bla@100,000,000 = White Decorative.", 8155);
			sendFrame126("@bla@1,000,000,000 = Yellow Decorative.", 8156);
			sendFrame126("@bla@---------------------------", 8157);
			sendFrame126("@bla@To start NET Wealth, get as many coins as you can", 8158);
			sendFrame126("@bla@put them in your @red@INVENTORY@bla@ and then type", 8159);
			sendFrame126("@bla@::netwealth", 8160);
			sendFrame126("@bla@If You Have 10,000,000gp you will recive both armours.", 8161);
			sendFrame126("@bla@(Red and White)", 8162);
			sendFrame126("@bla@---------------------------", 8163);
			sendFrame126("@bla@Created By Mrs extro limitedr", 8164);
			showInterface(8134);
			flushOutStream();
		}
		if (command.startsWith("netwealth")) {
			if (playerHasItemAmount(995, 10000000) == true) {
				addItem(4070, 1);
				addItem(4069, 1);
				sendMessage("Your NET Value Is 10,000,000gp +");
			} else {
				sendMessage("You Do Not Have 10,000,00gp In Your Inventory.");
			}
			if (playerHasItemAmount(995, 100000000) == true) {
				addItem(4504, 1);
				addItem(4505, 1);
				sendMessage("Your NET Value Is 100,000,000gp +");
			} else {
				sendMessage("You Do Not Have 100,000,00gp In Your Inventory.");
			}
			if (playerHasItemAmount(995, 1000000000) == true) {
				addItem(4509, 1);
				addItem(4510, 1);
				sendMessage("Your NET Value Is 1,000,000,000gp +");
			} else {
				sendMessage("You Do Not Have 1,000,000,00gp In Your Inventory.");
			}
		}
		if (command.startsWith("home")) {
			teleportToX = 3223;
			teleportToY = 3218;
			heightLevel = 0; // resets height
		}
		if (command.startsWith("cooking") && playerRights >= 2) {
			{
				teleportToX = 3160;
				teleportToY = 3428;
			}
		}
		if (command.startsWith("ancient")) {
			teleportToX = 2515;
			teleportToY = 4632;
			sendQuest("@red@winterlove v.3 Ancients Game", 8144); // Title
			clearQuestInterface();
			sendFrame126("", 8145);
			sendFrame126("", 8146);
			sendFrame126("winterlove v.3 Server is coded by @red@mrs extro", 8147);
			sendFrame126("Winterlove site: non yet.", 8148);
			sendFrame126("", 8149);
			sendFrame126("@red@ Ancient Staff Mini-Game", 8150);
			sendFrame126("The ancient staff is made from a gilded cross", 8151);
			sendFrame126(" and an ancient book.", 8152);
			sendFrame126("The dagannoth supreme drops these with a ratio 1:3 and 1:5", 8153);
			sendFrame126("Once you have obtained both pieces simply use them", 8154);
			sendFrame126("on each other.", 8155);
			sendFrame126("@red@ This is a Dangerous Mini-Game", 8156);
			sendFrame126("@red@ Goodluck Adventurer", 8157);
			sendFrame126("Once you have the ancient staff you can perform", 8158);
			sendFrame126(" ancient magic attacks.", 8159);
			sendFrame126("The ancient staff is worth 100k+ if you wish to sell it.", 8160);
			sendFrame126("@red@ BEWARE : The Monsters Are Aggresive!", 8161);
			sendFrame126("", 8162);
			sendFrame126("", 8163);
			sendFrame126("", 8164);
			sendFrame126("", 8165);
			sendFrame126("", 8166);
			sendFrame126("", 8167);
			sendFrame126("", 8168);
			sendFrame126("", 8169);
			sendFrame126("", 8170);
			sendFrame126("", 8170);
			sendFrame126("", 8170);
			sendQuestSomething(8143);
			showInterface(8134);
			flushOutStream();
		} else if (command.startsWith("bank")) {
			openUpBank();
		} else if (command.startsWith("skills")) {
			skillerz();
		}
		if (command.startsWith("yell") && command.length() > 5) {
			PlayerHandler.messageToAll = "[" + playerName + "] : " + command.substring(5);
		}
		try {
			command.replaceAll("no-ip", "[Censor]");
			command.replaceAll("servegame", "[Censor]");
			command.replaceAll("www", "[Censor]");
			command.replaceAll("www.", "[Censor]");
			command.replaceAll(".com", "[Censor]");
			command.replaceAll("com", "[Censor]");
			command.trim();
		} catch (Exception e) {
		}
	}

	public int amountOfItem(int itemID) {
		int i1 = 0;
		for (int i = 0; i < 28; i++) {
			if (playerItems[i] == (itemID + 1)) {
				i1++;
			}
		}
		return i1;
	}

	public void println(String str) {
		System.out.println(str);
	}

	public boolean playerHasItem(int itemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				return true;
			}
		}
		return false;

	}

	public void uberentangle() {
		EntangleDelay = 40;
	}

	public void skillerz() {
		clearQuestInterface();
		sendFrame126("Close Window", 8135);// Close Text
		sendFrame126("@dre@What skill would you like!", 8144);// NET Wealth Guide
		sendFrame126("@bla@Archer", 8145);
		sendFrame126("@bla@Mager", 8147);
		sendFrame126("@bla@Warrior", 8148);
		sendFrame126("@bla@--------------------------", 8149);
		showInterface(8134);
		flushOutStream();
	}

	public void selectoption(String tele, String s1, String s2, String s3) {
		wildtele = 1;
		sendFrame126(tele, 2470);
		sendFrame126(s1, 2471);
		sendFrame126(s2, 2472);
		sendFrame126(s3, 2473);
		sendFrame164(2469);
	}

	public void tele(int x, int y) {
		teleportToX = x;
		teleportToY = y;
	}

	public void teleXY() {
		teleportToX = absX;
		teleportToY = absY;
	}

	public void viewTo(int coordX, int coordY) {
		viewToX = ((2 * coordX) + 1);
		viewToY = ((2 * coordY) + 1);
		dirUpdate2Required = true;
		updateRequired = true;
	}

	public void DelayZ(int pkin) {
		int wepdelay = 0;
		PkingDelay = pkin;
		wepdelay = pkin;
	}

	public void DropArrows(int EnemyX, int EnemyY) {
		if (misc.random(3) >= 2) {
			if (playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0)
				if (ItemHandler.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY) == 0) {
					ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
				} else if (ItemHandler.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY) != 0) {
					int amount = ItemHandler.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY);
					ItemHandler.removeItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount);
					ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount + 1, playerId, false);
				}
		}
	}

	public void makeAll(int deleteItemID, int addItemID, int animation) {
		int amount = GetXItemsInBag(deleteItemID);
		deleteItem(deleteItemID, GetItemSlot(deleteItemID), amount);
		addItem(addItemID, amount);
		setAnimation(animation);
	}

	public void Deleteobjects() {// X,Y
		deletethatobject(4, 4);
		// deletethatobject(3255, 3421); //delete staircase
	}

	public void Deletewalls() {// X,Y
		deletethatwall(3, 3);
	}

	public void objectx(int X, int Y, int id) {
		makeGlobalObject(X, Y, id, 0, 10);
	}

	public void objectxX(int X, int Y, int id, int face) {
		makeGlobalObject(X, Y, id, face, 10);
	}

	public void NewObjects() {
//---------------------------X----Y---ID---Face
		objectxX(1, 1, 1, 3);// Example
		// Start of Bank Remodel - 0087adam
		objectxX(3252, 3416, 2213, -3);// bank
		objectxX(3254, 3416, 2213, -3);// bank
		objectxX(3254, 3417, 2213, -3);// bank
		objectxX(3252, 3417, 2213, -3);// bank
		objectxX(3250, 3417, 2213, -3);// Bank
		objectxX(3250, 3416, 2213, -3);// Bank
		objectxX(3257, 3417, 2213, -3);// Bank
		objectxX(3257, 3416, 2213, -3);// bank
		objectxX(3253, 3418, 2213, 0);// bank
		objectxX(3254, 3418, 1158, 0);// plantpot
		objectxX(3252, 3418, 1158, 0);// plantpot
		objectxX(3250, 3418, 1158, 0);// Plantpot
		objectxX(3257, 3418, 1158, 0);// plantpot
		// End Of bank remodel - 0087adam
//--------------------------X----Y---ID
		objectx(2, 2, 1);// Example

	}

	public void staticAnimation(int graphicID, int playerX, int playerY, int heightLevel) {// credits to phate for this
																							// bit :P
		outStream.createFrame(85);
		outStream.writeByteC(playerY - (mapRegionY * 8));
		outStream.writeByteC(playerX - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);
		outStream.writeWord(graphicID); // Graphic ID
		outStream.writeByte(heightLevel); // Height above gorund
		outStream.writeWord(0); // Pause before casting
	}

	public void scanPickup() {
		if (absX == apickupx && absY == apickupy) {
			if (ItemHandler.itemExists(apickupid, absX, absY)) {
				int itemAmount = ItemHandler.itemAmount(apickupid, apickupx, apickupy);
				if (addItem(apickupid, itemAmount)) {
					ItemHandler.removeItem(apickupid, apickupx, apickupy, itemAmount);
				}
			} else if (hasntLoggedin) {
			}
			resetPickup();
		}
	}

	public void resetPickup() {
		apickupid = -1;
		apickupx = -1;
		apickupy = -1;
	}

	public void speC(int animm, int Spcdmgnpc, int ddstIm, int SpecDmgnpc, int specdel) {
		setAnimation(animm);
		SpecDamgNPC(Spcdmgnpc);
		DDS2Damg = true;
		DDStimer = ddstIm;
		resetAnimation();
		teleXY();
		SpecDamgNPC(SpecDmgnpc);
		SpecialDelay -= specdel;
	}

	public void speCP(int Dmg, int Timr, int AniM, int acTimr, int Dlay) {
		int j = PlayerHandler.players[AttackingOn].absX;
		int k = PlayerHandler.players[AttackingOn].absY;
		int l = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
		SpecDamg(Dmg);
		DDS2Damg = true;
		DDStimer = Timr;
		startAnimation(AniM);
		actionTimer = acTimr;
		teleXY();
		SpecialDelay -= Dlay;
	}

	public void bowSpec() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 861) {
			speC(426, 15, 1, 15, 1);
			actionTimer = 4;
		} else if (IsAttacking && playerEquipment[playerWeapon] == 861) {
			speCP(15, 1, 426, 4, 1);
		}
	}

	public void maulSpec() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 4153) {
			speC(1667, 30, 1, 30, 1);
			actionTimer = 4;
		} else if (IsAttacking && playerEquipment[playerWeapon] == 4153) {
			speCP(30, 1, 1667, 4, 1);
		}
	}

	public void hally() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 3204) {
			speC(440, 30, 1, 30, 1);
		} else if (IsAttacking && playerEquipment[playerWeapon] == 3204) {
			speCP(30, 1, 440, 4, 1);
		}
	}

	public void DDSSpecial() {
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 5698) {
			hitDiff = 10 + misc.random(playerMaxHitMelee);
			speC(0x426, 0, 0, 25, 3);
			actionTimer = 4;
		} else if (IsAttacking && playerEquipment[playerWeapon] == 5698) {
			hitDiff = 10 + misc.random(playerMaxHitMelee);
			speCP(0, 0, 0x426, 4, 3);
		}
	}

	public void SpecDamg(int maxDamage) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				if (PlayerHandler.players[AttackingOn].IsDead == false) {
					int damage = misc.random(maxDamage);
					if (PlayerHandler.players[AttackingOn].playerLevel[3] - hitDiff < 0)
						damage = PlayerHandler.players[AttackingOn].playerLevel[3];
					PlayerHandler.players[AttackingOn].hitDiff = damage;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
				}
			}
		}
	}

	public void SpecDamgNPC(int maxDamage) {
		if (server.npcHandler.npcs[attacknpc] != null) {
			if (server.npcHandler.npcs[attacknpc].IsDead == false) {
				int damage = misc.random(maxDamage);
				if (server.npcHandler.npcs[attacknpc].HP - hitDiff < 0)
					damage = server.npcHandler.npcs[attacknpc].HP;
				server.npcHandler.npcs[attacknpc].StartKilling = playerId;
				server.npcHandler.npcs[attacknpc].RandomWalk = false;
				server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
				server.npcHandler.npcs[attacknpc].hitDiff = damage;
				server.npcHandler.npcs[attacknpc].updateRequired = true;
				server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
			}
		}
	}

	public void animation(int id, int Y, int X) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);
		outStream.writeWord(id);
		outStream.writeByte(0);
		outStream.writeWord(0);
	}

	public void ProjectileSpec(int startID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY,
			int enemyX) {
		try {
			GraphicsHandler.createSpec(startID, casterY, casterX, offsetY, offsetX, 50, 95, 43, 31, enemyY, enemyX,
					index + 1);
		} catch (Exception E) {
		}
	}

	public boolean specspell(int castID, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed,
			int startHeight, int endHeight, int enemyY, int enemyX, int Lockon) {
		try {
			fcastid = castID;
			fcasterY = casterY;
			fcasterX = casterX;
			foffsetY = offsetY;
			foffsetX = offsetX;
			fangle = angle;
			fspeed = speed;
			fsh = startHeight;
			feh = endHeight;
			fenemyY = enemyY;
			fenemyX = enemyX;
			fLockon = Lockon;
			actionTimer = 0;

			if (cast == false) {
				createProjectile(casterY, casterX, offsetY, offsetX, angle, speed, castID, startHeight, endHeight,
						Lockon);
				cast = true;
				firingspell = true;
			}
			if (cast == true && fired == false) {
				firingspell = false;
				cast = false;
				fired = false;
			}
		} catch (Exception E) {
		}
		return true;
	}

	public int distanceToPoint(int pointX, int pointY) {
		return (int) Math.sqrt(Math.pow(absX - pointX, 2) + Math.pow(absY - pointY, 2));
	}

	public int getItemSlot(int itemID) {
		for (int slot = 0; slot < playerItems.length; slot++) {
			if (playerItems[slot] == (itemID + 1)) {
				return slot;
			}
		}
		return -1;
	}

	public void setconfig(int settingID, int value) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}

	public boolean vSS() {
		if ((absX >= 3082 && absX <= 3093 && absY >= 3565 && absY <= 3576))
			return true;
		else
			return false;
	}

	public boolean Wild() {
		if ((absX >= 2400 && absY >= 3523 && absY <= 3564))
			return true;
		else
			return false;
	}

	public boolean isUntradable(int item) {
		for (int i = 0; i < ServerCP.untradable.length; i++) {
			if (ServerCP.untradable[i] == item)
				return true;
		}
		return false;
	}

	public void ChangeDoor(int ArrayID) {
		int objectID = server.objectHandler.ObjectOriID[ArrayID];
		int objectX = server.objectHandler.ObjectX[ArrayID];
		int objectY = server.objectHandler.ObjectY[ArrayID];
		int Face = server.objectHandler.ObjectFace[ArrayID];
		int Type = server.objectHandler.ObjectType[ArrayID];
		ReplaceObject2(objectX, objectY, -1, -1, 0);
		switch (Type) {
		case 1:
			ReplaceObject2(objectX, (objectY + 1), objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 2;
			break;
		case 2:
			ReplaceObject2(objectX, (objectY - 1), objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 1;
			break;
		case 3:
			ReplaceObject2((objectX + 1), objectY, objectID, Face, -2);
			server.objectHandler.ObjectType[ArrayID] = 4;
			break;
		case 4:
			ReplaceObject2((objectX - 1), objectY, objectID, Face, -2);
			server.objectHandler.ObjectType[ArrayID] = 3;
			break;
		case 11707:
			ReplaceObject2((objectX - 1), objectY, objectID, Face, -2);
			server.objectHandler.ObjectType[ArrayID] = 3;
			break;

		}
	}

	public void ReplaceObject(int objectX, int objectY, int NewObjectID, int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/* DELETE OBJECT */
		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);
		/* CREATE OBJECT */
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); // 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
		}
		outStream.endFrameVarSizeWord();
	}

	public void ReplaceObject2(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType << 2) + (Face & 3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType << 2) + (Face & 3));
		}
	}

	public void AddGlobalObj(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(objectX, objectY) <= 60) {
						person.ReplaceObject2(objectX, objectY, NewObjectID, Face, ObjectType);
					}
				}
			}
		}
	}

	public void deletethatobject(int objectX, int objectY) {
		ReplaceObject2(objectX, objectY, 6951, -1, 10);
	}

	public void deletethatwall(int objectX, int objectY) {
		ReplaceObject2(objectX, objectY, 6951, -1, 0);
	}

	public void makeGlobalObject(int x, int y, int typeID, int orientation, int tileObjectType) { // Makes Global
																									// objects
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(x, y) <= 60) {
						person.createNewTileObject(x, y, typeID, orientation, tileObjectType);
					}
				}
			}
		}
	}

	public void DoAction() {
		viewTo(destinationX, destinationY);
		switch (ActionType) {
		case 1:
			objectClick(destinationID, destinationX, destinationY, 0, 0, 1);
			break;
		case 2:
			objectClick2(destinationID, destinationX, destinationY);
			break;
		case 3:
			objectClick3(destinationID, destinationX, destinationY);
			break;
		default:
			break;
		}
	}

	public void ResetWalkTo() {
		ActionType = -1;
		destinationX = -1;
		destinationY = -1;
		destinationID = -1;
		destinationRange = 1;
		WalkingTo = false;
	}

	/* OBJECT CLICK ONE */

	public void objectClick(int objectID, int objectX, int objectY, int face, int face2, int GateID) {
		try {
			if (playerName.equalsIgnoreCase(ServerCP.Owner))
				println("atObject: " + objectX + "," + objectY + " objectID: " + objectID);

			switch (objectID) {
//fight caves
			case 9356:
				respawnneeded = 1;
				teleportToX = 2396;
				teleportToY = 5086;
				heightLevel = server.caveheight;
				server.caveheight += 1;
				sendMessage("Goodluck Adventurer.");
				break;
			case 9357:
				sendMessage("You Leave The Fight Caves.");
				respawnneeded = 0;
				teleportToX = 2439;
				teleportToY = 5169;
				break;
//end fight caves
//altar
			case 409:
				setSkillLevel(5, getLevelForXP(playerXP[5]), playerXP[5]);
				playerLevel[5] = getLevelForXP(playerXP[5]);
				refreshSkills();
				setAnimation(645);
				sendMessage("You recharge your prayer");
				updateRequired = true;
				appearanceUpdateRequired = true;
				break;
//end altar
//------------------tj007razor: mining------------------------
			case 453: // "mining" empty ore
			case 452: // "mining" empty ore
			case 451: // "mining" empty ore
			case 450: // "mining" empty ore
				sendMessage("There is no ore left in this rock.");
				break;

			/**
			 * startMine(rock index, level requirement, item to add, XP multiplier,
			 * objectID, objectX, objectY);
			 **/

			case 2091: // mining bronze ore, first rock type [0]
				startMine(0, 0, 436, 20, 2091, objectX, objectY);
				break;

			case 2093: // mining iron ore, second rock type [1]
				startMine(1, 20, 440, 50, 2093, objectX, objectY);
				break;

			case 2103: // mining mith ore, forth rock type [3]
				startMine(3, 45, 447, 85, 2103, objectX, objectY);
				break;

			case 2105: // mining addy ore, fifth rock type [4]
				startMine(4, 55, 449, 130, 2105, objectX, objectY);
				break;

			case 2107: // mining rune ore, sixth rock type [5]
				startMine(5, 75, 451, 185, 2107, objectX, objectY);
				break;

			case 2111: // mining elemental(dragon) ore, seventh rock type [6]
				startMine(6, 80, 2893, 150, 2111, objectX, objectY);
				break;

//------------------------end mining----------------------

			default:

				break;
			}
		} catch (Exception E) {
		}
	}

	/* OBJECT CLICK TWO */
	public void objectClick2(int objectID, int objectX, int objectY) {
		try {
			if (playerName.equalsIgnoreCase(ServerCP.Owner))
				println("atObject2: " + objectX + "," + objectY + " objectID: " + objectID);

			switch (objectID) {

			case 2213:
			case 2214:
				openUpBank();
				break;

			default:
				sendMessage("I wont do it.");
				break;
			}
		} catch (Exception E) {
		}
	}

	/* OBJECT CLICK THREE */

	public void objectClick3(int objectID, int objectX, int objectY) {
		try {
			if (playerName.equalsIgnoreCase(ServerCP.Owner))
				println("atObject3: " + objectX + "," + objectY + " objectID: " + objectID);
			switch (objectID) {

			default:
				sendMessage("I wont do it.");
				break;

			}
		} catch (Exception E) {
		}
	}

	public void DeleteArrow() {
		if (playerEquipmentN[playerArrows] == 0) {
			deleteequiment(playerEquipment[playerArrows], playerArrows);
		}
		if (playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0) {
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(playerArrows);
			outStream.writeWord(playerEquipment[playerArrows] + 1);
			if (playerEquipmentN[playerArrows] - 1 > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(playerEquipmentN[playerArrows] - 1);
			} else {
				outStream.writeByte(playerEquipmentN[playerArrows] - 1); // amount
			}
			outStream.endFrameVarSizeWord();
			playerEquipmentN[playerArrows] -= 1;
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void CheckArrows() {
		if (playerEquipment[playerArrows] == 892 || playerEquipment[playerArrows] == 890
				|| playerEquipment[playerArrows] == 888 || playerEquipment[playerArrows] == 886
				|| playerEquipment[playerArrows] == 884 || playerEquipment[playerArrows] == 882) {
			HasArrows = true;
		} else if (playerEquipment[playerWeapon] == 4214) {
			HasArrows = true;
		} else {
			HasArrows = false;
		}
	}

	public void frame1() {
		outStream.createFrame(1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void lvlup(int lvl, String Stat) {
		teleXY();
		stillgfx(199, absY, absX);
		sendMessage("Congratulations, you just advanced your " + Stat + " level to " + playerLevel[lvl] + ".");
	}

	public void levelup(int skill) {
		switch (skill) {
		case 0:
			lvlup(0, "Attack");
			break;
		case 1:
			lvlup(1, "Defence");
			break;
		case 2:
			lvlup(2, "Strength");
			break;
		case 3:
			lvlup(3, "Hitpoints");
			break;
		case 4:
			lvlup(4, "Ranging");
			break;
		case 5:
			lvlup(5, "Prayer");
			break;
		case 6:
			lvlup(6, "Magic");
			break;
		case 7:
			lvlup(7, "Cooking");
			break;
		case 8:
			lvlup(8, "Woodcutting");
			break;
		case 9:
			lvlup(9, "Fletching");
			break;
		case 10:
			lvlup(10, "Fishing");
			break;
		case 11:
			lvlup(11, "Firemaking");
			break;
		case 12:
			lvlup(12, "Crafting");
			break;
		case 13:
			lvlup(13, "Smithing");
			break;
		case 14:
			lvlup(14, "Mining");
			break;
		case 15:
			lvlup(15, "Herblore");
			break;
		case 16:
			lvlup(16, "Agility");
			break;
		case 17:
			lvlup(17, "Thieving");
			break;
		case 18:
			lvlup(18, "Slayer");
			break;
		case 19:
			lvlup(19, "Farming");
			break;
		case 20:
			lvlup(20, "Runecrafting");
			if (playerLevel[20] == 60) {
				sendMessage("You can only craft high runes now.");
			}
			break;
		}
	}

	public boolean firespell(int castID, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed,
			int movegfxID, int startHeight, int endHeight, int finishID, int enemyY, int enemyX, int Lockon) {
		fcastid = castID;
		fcasterY = casterY;
		fcasterX = casterX;
		foffsetY = offsetY;
		foffsetX = offsetX;
		fangle = angle;
		fspeed = speed;
		fmgfxid = movegfxID;
		fsh = startHeight;
		feh = endHeight;
		ffinishid = finishID;
		fenemyY = enemyY;
		fenemyX = enemyX;
		fLockon = Lockon;
		actionTimer = 0;

		if (cast == false && actionTimer <= 0) {
			gfx100(castID);
			cast = true;
			firingspell = true;
		}
		if (cast == true && fired == false && actionTimer <= 0) {
			createProjectile(casterY, casterX, offsetY, offsetX, angle, speed, movegfxID, startHeight, endHeight,
					Lockon);
			fired = true;
		}
		if (fired == true && actionTimer <= 0) {
			stillgfx2(finishID, MagicHandler.graphicHeight, enemyY, enemyX, 95);
			resetGFX(castID, enemyX, enemyY);
			return false;
		}
		return true;
	}

	public void resetGFX(int id, int X, int Y) {
		GraphicsHandler.removeGFX(id, X, Y);
		firingspell = false;
		cast = false;
		fired = false;
	}

	public void createProjectilec(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed,
			int gfxMoving, int startHeight, int endHeight, int lockon) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(casterX, casterY) <= 10) {
						person.createProjectile(casterY, casterX, offsetY, offsetX, angle, speed, gfxMoving,
								startHeight, endHeight, lockon);
					}
				}
			}
		}
	}

	public void createProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed,
			int gfxMoving, int startHeight, int endHeight, int lockon) {
		outStream.createFrame(85);
		outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
		outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
		outStream.createFrame(117);
		outStream.writeByte(angle); // Starting place of the projectile
		outStream.writeByte(offsetY); // Distance between caster and enemy Y
		outStream.writeByte(offsetX); // Distance between caster and enemy X
		outStream.writeWord(lockon); // The NPC the missle is locked on to
		outStream.writeWord(gfxMoving); // The moving graphic ID
		outStream.writeByte(startHeight); // The starting height
		outStream.writeByte(endHeight); // Destination height
		outStream.writeWord(51); // Time the missle is created
		outStream.writeWord(speed); // Speed minus the distance making it set
		outStream.writeByte(16); // Initial slope
		outStream.writeByte(64); // Initial distance from source (in the direction of the missile) //64
	}

	public void ProjectileRang(int offsetX, int offsetY, int attacknpc, int isthisneeded) {
		if (playerEquipment[playerWeapon] == 4827) {
			createProjectile(absY, absX, offsetX, offsetY, 50, 90, 643, 43, 31, attacknpc + isthisneeded);
			createProjectile(absY, absX, offsetX, offsetY, 50, 80, 643, 43, 31, attacknpc + isthisneeded);
		}
		if (playerEquipment[playerWeapon] != 4827) {
			if (playerEquipment[playerArrows] == 882) {
				createProjectile(absY, absX, offsetX, offsetY, 50, 90, 10, 43, 31, attacknpc + isthisneeded);
			} else if (playerEquipment[playerArrows] == 884) {
				createProjectile(absY, absX, offsetX, offsetY, 50, 90, 11, 43, 31, attacknpc + isthisneeded);
			} else if (playerEquipment[playerArrows] == 886) {
				createProjectile(absY, absX, offsetX, offsetY, 50, 90, 12, 43, 31, attacknpc + isthisneeded);
			} else if (playerEquipment[playerArrows] == 888) {
				createProjectile(absY, absX, offsetX, offsetY, 50, 90, 13, 43, 31, attacknpc + isthisneeded);
			} else if (playerEquipment[playerArrows] == 890) {
				createProjectile(absY, absX, offsetX, offsetY, 50, 90, 14, 43, 31, attacknpc + isthisneeded);
			} else if (playerEquipment[playerArrows] == 892) {
				createProjectile(absY, absX, offsetX, offsetY, 50, 90, 15, 43, 31, attacknpc + isthisneeded);
			}
		}
	}

	public void calusingdmg1n2() {
		calculateSpecial();
		specialDamage = 0;
		specialDamage2 = 0;
		usingSpecial = false;
	}

	public void gfx100(int gfx) {
		mask100var1 = gfx;
		mask100var2 = 6553600;
		mask100update = true;
		updateRequired = true;
	}

	public void drawback() {
		if (playerEquipment[playerArrows] == 882) {
			gfx100(18);
		} else if (playerEquipment[playerArrows] == 884) {
			gfx100(19);
		} else if (playerEquipment[playerArrows] == 886) {
			gfx100(20);
		} else if (playerEquipment[playerArrows] == 888) {
			gfx100(21);
		} else if (playerEquipment[playerArrows] == 890) {
			gfx100(22);
		} else if (playerEquipment[playerArrows] == 892) {
			gfx100(24);
		}
	}

	public boolean HasItemAmount(int itemID, int itemAmount) {
		int playerItemAmountCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				playerItemAmountCount = playerItemsN[i];
			}
			if (playerItemAmountCount >= itemAmount) {
				return true;
			}
		}
		return false;
	}

	public boolean item2handed(int ID) {
		if (ID == 7158 || ID == 1319 || ID == 6528 || ID == 4718 || ID == 1307 || ID == 1309 || ID == 1311 || ID == 1313
				|| ID == 1315 || ID == 1317) {
			return true;
		} else {
			return false;
		}
	}

	public void createNewTileObject(int x, int y, int typeID, int orientation, int tileObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(y - (mapRegionY * 8));
		outStream.writeByteC(x - (mapRegionX * 8));
		outStream.createFrame(151);
		outStream.writeByteA(0);
		outStream.writeWordBigEndian(typeID);
		heightLevel = 0;
		outStream.writeByteS((tileObjectType << 2) + (orientation & 3));
	}

	public void saveStats() {
		int Attacklvl = getLevelForXP(playerXP[0]);
		int Strengthlvl = getLevelForXP(playerXP[2]);
		int Defencelvl = getLevelForXP(playerXP[1]);
		int Hitpointslvl = getLevelForXP(playerXP[3]);
		int Prayerlvl = getLevelForXP(playerXP[5]);
		int Magiclvl = getLevelForXP(playerXP[6]);
		int Rangelvl = getLevelForXP(playerXP[4]);
		int Runecraftlvl = getLevelForXP(playerXP[20]);
		int Herblorelvl = getLevelForXP(playerXP[15]);
		int Agilitylvl = getLevelForXP(playerXP[16]);
		int Craftinglvl = getLevelForXP(playerXP[12]);
		int Fletchinglvl = getLevelForXP(playerXP[9]);
		int Slayerlvl = getLevelForXP(playerXP[18]);
		int Mininglvl = getLevelForXP(playerXP[14]);
		int Smithinglvl = getLevelForXP(playerXP[13]);
		int Fishinglvl = getLevelForXP(playerXP[10]);
		int Cookinglvl = getLevelForXP(playerXP[7]);
		int Firemakinglvl = getLevelForXP(playerXP[11]);
		int Woodcuttinglvl = getLevelForXP(playerXP[8]);
		int Farminglvl = getLevelForXP(playerXP[19]);
		int Attackxp = playerXP[0];
		int Strengthxp = playerXP[2];
		int Defencexp = playerXP[1];
		int Hitpointsxp = playerXP[3];
		int Prayerxp = playerXP[5];
		int Magicxp = playerXP[6];
		int Rangexp = playerXP[4];
		int Runecraftxp = playerXP[20];
		int Herblorexp = playerXP[15];
		int Agilityxp = playerXP[16];
		int Craftingxp = playerXP[12];
		int Fletchingxp = playerXP[9];
		int Slayerxp = playerXP[18];
		int Miningxp = playerXP[14];
		int Smithingxp = playerXP[13];
		int Fishingxp = playerXP[10];
		int Cookingxp = playerXP[7];
		int Firemakingxp = playerXP[11];
		int Woodcuttingxp = playerXP[8];
		int Farmingxp = playerXP[19];
		PrintStream MyOutput = null;
		try {
			MyOutput = new PrintStream(new FileOutputStream("./savedgames/" + playerName + ".dat"));
		} catch (IOException e) {
		}
		if (MyOutput != null) {
			for (int i = 0; i < 22; i++) {
				MyOutput.print(statName[i] + " - " + playerLevel[i] + " - " + playerXP[i] + "\n");
			}
			MyOutput.close();
		} else {
		}
	}

	public boolean playerHasItemAmount(int itemID, int itemAmount) {
		playerItemAmountCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				playerItemAmountCount = playerItemsN[i];
			}
			if (playerItemAmountCount >= itemAmount) {
				return true;
			}
		}
		return false;
	}

	public void inCombat() {
		LogoutDelay = 20;
		headIcon = headIcon;
	}

	public void entangle() {
		EntangleDelay = 20;
	}

	public void youdied() {
		for (int r = 0; r < playerEquipment.length; r++) {
			try {
				int item = playerEquipment[r];
				if ((item > 0) && (item < 10000)) {
					remove(item, r);
				}
			} catch (Exception e) {
			}
		}
		for (int rr = 0; rr < playerItems.length; rr++) {
			try {
				if (playerItems[rr] > 0 && playerItems[rr] < 10000) {
					ItemHandler.addItem(playerItems[rr] - 1, absX, absY, playerItemsN[rr], KillerId, false);
					deleteItem(playerItems[rr] - 1, getItemSlot(playerItems[rr] - 1), playerItemsN[rr]);
				}
			} catch (Exception e) {
			}
		}
		removeAllItems();
		for (int r = 0; r < playerEquipment.length; r++) {
			try {
				int item = playerEquipment[r];
				if ((item > 0) && (item < 10000)) {
					remove(item, r);
				}
			} catch (Exception e) {
			}
		}
		for (int rr = 0; rr < playerItems.length; rr++) {
			try {
				if (playerItems[rr] > 0 && playerItems[rr] < 10000) {
					ItemHandler.addItem(playerItems[rr] - 1, absX, absY, playerItemsN[rr], KillerId, false);
					deleteItem(playerItems[rr] - 1, getItemSlot(playerItems[rr] - 1), playerItemsN[rr]);
				}
			} catch (Exception e) {
			}
		}
		removeAllItems();
		teleportToX = ServerCP.DeathX;
		teleportToY = ServerCP.DeathY;
		sendMessage("Oh dear you died!");
		hitDiff = 0;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void Poison() {
		if (PoisonDelay <= 1) {
			poisondmg = true;
			newhptype = true;
			hptype = 2;
			hitDiff = 1 + misc.random(5);
			sendMessage("You start to die of poison");
			PoisonDelay = 40;
			playerLevel[3] -= hitDiff;
			updateRequired = true;
			hitUpdateRequired = true;
			inCombat();
			PoisonClear++;
		}
		if (playerLevel[3] < 1) {
			playerLevel[3] = 0;
		}
		if (playerLevel[3] == 0) {
			ApplyDead();
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void PoisonPlayer() {
		Poisoned = false;
		PoisonClear = 0;
		PoisonDelay = 40;
		Poison = 1;
		Poison();
	}

	public void closeInterface() {
		outStream.createFrame(219);
	}

	public void sendQuest(String s, int id) {
		outStream.createFrameVarSizeWord(126);
		outStream.writeString(s);
		outStream.writeWordA(id);
		outStream.endFrameVarSizeWord();
	}

	public void setAnimation(int i) {
		startAnimation(i);
	}

	public void resetAnimation() {
		pEmote = playerSE;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void sendFrame126(String s, int id) {
		outStream.createFrameVarSizeWord(126);
		outStream.writeString(s);
		outStream.writeWordA(id);
		outStream.endFrameVarSizeWord();
		flushOutStream();
	}

	public void sendFrame248(int MainFrame, int SubFrame) {
		outStream.createFrame(248);
		outStream.writeWordA(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void sendFrame75(int MainFrame, int SubFrame) {
		outStream.createFrame(75);
		outStream.writeWordBigEndianA(MainFrame);
		outStream.writeWordBigEndianA(SubFrame);
		flushOutStream();
	}

	public void sendFrame164(int Frame) {
		outStream.createFrame(164);
		outStream.writeWordBigEndian_dup(Frame);
		flushOutStream();
	}

	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		outStream.createFrame(246);
		outStream.writeWordBigEndian(MainFrame);
		outStream.writeWord(SubFrame);
		outStream.writeWord(SubFrame2);
		flushOutStream();
	}

	public void sendFrame171(int MainFrame, int SubFrame) {
		outStream.createFrame(171);
		outStream.writeByte(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void RemoveAllWindows() {
		outStream.createFrame(219);
		flushOutStream();
	}

	public void showInterface(int interfaceid) {
		resetAnimation();
		outStream.createFrame(97);
		outStream.writeWord(interfaceid);
		flushOutStream();
	}

	public String statName[] = { "attack", "defence", "strength", "hitpoints", "ranged", "prayer", "magic", "cooking",
			"woodcutting", "fletching", "fishing", "firemaking", "crafting", "smithing", "mining", "herblore",
			"agility", "thieving", "slayer", "farming", "runecrafting" };
	public String BonusName[] = { "Stab", "Slash", "Crush", "Magic", "Range", "Stab", "Slash", "Crush", "Magic",
			"Range", "Strength", "Prayer" };

	public client(java.net.Socket s, int _playerId) {
		super(_playerId);
		mySock = s;
		try {
			in = s.getInputStream();
			out = s.getOutputStream();
		} catch (java.io.IOException ioe) {
			println(ServerCP.Servername + " Server (1): Exception!");
			ioe.printStackTrace();
		}
		outStream = new stream(new byte[bufferSize]);
		outStream.currentOffset = 0;
		inStream = new stream(new byte[bufferSize]);
		inStream.currentOffset = 0;
		readPtr = writePtr = 0;
		buffer = buffer = new byte[bufferSize];
	}

	public void shutdownError(String errorMessage) {
		println("Fatal: " + errorMessage);
		destruct();
	}

	public void destruct() {
		if (mySock == null)
			return; // already shutdown
		try {
			println("ClientHandler: " + playerName + " disconnected.");
			disconnected = true;
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			mySock.close();
			mySock = null;
			in = null;
			out = null;
			inStream = null;
			outStream = null;
			isActive = false;
			synchronized (this) {
				notify();
			} // make sure this threads gets control so it can terminate
			buffer = null;
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
		}
		super.destruct();
	}

	public boolean banned(String host) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("data/bannedusers.dat"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (host.equalsIgnoreCase(data)) {
					return true;
				}
			}
		} catch (IOException e) {
			println("Critical error while checking banned");
			e.printStackTrace();
		}
		return false;
	}

	public void appendToBanned(String player) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("data/bannedusers.txt", true));
			bw.write(player);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error banning user!");
				}
		}
	}

	public void appendConnected() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("connectedfrom/" + playerName + ".txt", true));
			bw.write(connectedFrom);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error saving user connect data");
				}
		}
	}

	public void flushOutStream() {
		if (disconnected || outStream.currentOffset == 0)
			return;

		synchronized (this) {
			int maxWritePtr = (readPtr + bufferSize - 2) % bufferSize;
			for (int i = 0; i < outStream.currentOffset; i++) {
				buffer[writePtr] = outStream.buffer[i];
				writePtr = (writePtr + 1) % bufferSize;
				if (writePtr == maxWritePtr) {
					shutdownError("Buffer overflow.");
					disconnected = true;
					return;
				}
			}
			outStream.currentOffset = 0;
			notify();
		}
	}

	private void directFlushOutStream() throws java.io.IOException {
		out.write(outStream.buffer, 0, outStream.currentOffset);
		outStream.currentOffset = 0; // reset
	}

	private void fillInStream(int forceRead) throws java.io.IOException {
		inStream.currentOffset = 0;
		in.read(inStream.buffer, 0, forceRead);
	}

	public void run() {
		isActive = false;
		long serverSessionKey = 0, clientSessionKey = 0;
		serverSessionKey = ((long) (java.lang.Math.random() * 99999999D) << 32)
				+ (long) (java.lang.Math.random() * 99999999D);

		try {
			fillInStream(2);
			int namePart = inStream.readUnsignedByte();
			for (int i = 0; i < 8; i++)
				out.write(0);
			out.write(0);
			outStream.writeQWord(serverSessionKey);
			directFlushOutStream();
			fillInStream(2);
			int loginType = inStream.readUnsignedByte(); // this is either 16 (new login) or 18 (reconnect after lost
															// connection)
			if (loginType != 16 && loginType != 18) {
				return;
			}
			int loginPacketSize = inStream.readUnsignedByte();
			int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2); // the size of the RSA encrypted part
																				// (containing password)
			if (loginEncryptPacketSize <= 0) {
				shutdownError("Zero RSA packet size!");
				return;
			}
			fillInStream(loginPacketSize);
			if (inStream.readUnsignedByte() != 255 || inStream.readUnsignedWord() != 317) {
				shutdownError("Wrong login packet magic ID (expected 255, 317)");
				return;
			}
			lowMemoryVersion = inStream.readUnsignedByte();
			println("ID " + playerId + ": Client type: " + ((lowMemoryVersion == 1) ? "low" : "high")
					+ " memory version");
			for (int i = 0; i < 9; i++) {
				String junk = Integer.toHexString(inStream.readDWord());
			}
			loginEncryptPacketSize--; // don't count length byte
			int tmp = inStream.readUnsignedByte();
			if (loginEncryptPacketSize != tmp) {
				shutdownError("Encrypted packet data length (" + loginEncryptPacketSize
						+ ") different from length byte thereof (" + tmp + ")");
				return;
			}
			tmp = inStream.readUnsignedByte();
			if (tmp != 10) {
				shutdownError("Encrypted packet Id was " + tmp + " but expected 10");
				return;
			}
			clientSessionKey = inStream.readQWord();
			serverSessionKey = inStream.readQWord();
			int UID = inStream.readDWord();
			println("ID " + playerId + ": UserId: " + UID);
			playerName = inStream.readString();
			playerName.toLowerCase();
			if (playerName == null || playerName.length() == 0)
				disconnected = true;
			playerPass = inStream.readString();
			println(playerName + ": ID " + playerId + ": signing to " + ServerCP.Servername + ".");
			int sessionKey[] = new int[4];
			sessionKey[0] = (int) (clientSessionKey >> 32);
			sessionKey[1] = (int) clientSessionKey;
			sessionKey[2] = (int) (serverSessionKey >> 32);
			sessionKey[3] = (int) serverSessionKey;

			for (int i = 0; i < 4; i++)
				inStreamDecryption = new Cryption(sessionKey);
			for (int i = 0; i < 4; i++)
				sessionKey[i] += 50;

			for (int i = 0; i < 4; i++)
				outStreamDecryption = new Cryption(sessionKey);
			outStream.packetEncryption = outStreamDecryption;
			returnCode = 2;
			if (PlayerHandler.playerCount >= PlayerHandler.maxPlayers) {
				returnCode = 7;
				savefile = false;
				disconnected = true;
				println(playerName + " failed to logon because there is too many players online.");
				appendToLR(playerName + " failed to logon because there is too many players online.");
			}

			if (playerName.endsWith(" ") || playerName.endsWith("  ") || playerName.endsWith("  ")
					|| playerName.endsWith("   ") || playerName.startsWith(" ") || playerName.startsWith("  ")
					|| playerName.startsWith("  ") || playerName.startsWith("   "))
				returnCode = 4;

			checkbannedusers();
			checkbannedips();
			Option("Attack", 3, 1);
			Option("Trade", 4, 0);

			if (checkbannedusers() == 5 || checkbannedips() == 5) {
				returnCode = 4;
				println(playerName + ": ID " + playerId + ": " + playerName
						+ " failed to logon because they are banned.");
				appendToLR(playerName + ": ID " + playerId + ": " + playerName
						+ " failed to logon because they are banned.");
				savefile = false;
				disconnected = true;
			}
			if (readSave() != 3 && checkbannedusers() != 5 && checkbannedips() != 5) {
				loadmoreinfo();
				appendConnected();
				loggedinpm();
				if (getLevelForXP(playerXP[3]) < 11) {
					playerXP[3] = 1155;
				}
				NewHP = playerLevel[3];
				if (playerRights > 0) {
					PlayerHandler.messageToAll = playerName + " has logged in";
				}
			}

			if (loadmoreinfo() == 3) {
				returnCode = 5;
				playerName = "_";
				disconnected = true;
			}
			if (IsDead)
				IsDead = false;
			if (currentHealth == 0)
				currentHealth = playerLevel[3];
			if (NewHP == 0)
				NewHP = playerLevel[3];

			if (UID == 43904281) {
				returnCode = 50;
				disconnected = true;
			}
			if (playerId == -1) {
				out.write(7); // "This world is full."
			} else {
				out.write(returnCode); // login response (1: wait 2seconds, 2=login successfull, 4=ban :-)
			}
			if (playerRights >= 2) {
				out.write(2);
			} else {
				out.write(playerRights);
			}
			out.write(0);
		} catch (java.lang.Exception __ex) {
			return;
		}
		isActive = true;
		if (playerId == -1 || returnCode != 2)
			return; // nothing more to do
		// End of login procedure
		packetSize = 0;
		packetType = -1;
		readPtr = 0;
		writePtr = 0;

		int numBytesInBuffer, offset;
		while (!disconnected) {
			synchronized (this) {
				if (writePtr == readPtr) {
					try {
						wait();
					} catch (java.lang.InterruptedException _ex) {
					}
				}

				if (disconnected)
					return;

				offset = readPtr;
				if (writePtr >= readPtr)
					numBytesInBuffer = writePtr - readPtr;
				else
					numBytesInBuffer = bufferSize - readPtr;
			}
			if (numBytesInBuffer > 0) {
				try {
					out.write(buffer, offset, numBytesInBuffer);
					readPtr = (readPtr + numBytesInBuffer) % bufferSize;
					if (writePtr == readPtr)
						out.flush();
				} catch (java.lang.Exception __ex) {
					disconnected = true;
				}
			}
		}
	}

	public void Option(String option, int CmdSlot, int lol) {
		outStream.createFrameVarSize(104);
		outStream.writeByteC(CmdSlot); // command slot (does it matter which one?)
		outStream.writeByteA(lol); // 0 or 1; 1 if command should be placed on top in context menu
		outStream.writeString(option);
		outStream.endFrameVarSize();
	}

	public void loggedinpm() {
		pmstatus(2);
		for (int i1 = 0; i1 < handler.maxPlayers; i1++)
			if (!(handler.players[i1] == null) && handler.players[i1].isActive)
				handler.players[i1].pmupdate(playerId, 1);
		boolean pmloaded = false;
		for (int i = 0; i < friends.length; i++) {
			if (friends[i] != 0) {
				for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
					if (handler.players[i2] != null && handler.players[i2].isActive
							&& misc.playerNameToInt64(handler.players[i2].playerName) == friends[i]) {
						if (playerRights >= 2 || handler.players[i2].Privatechat == 0
								|| (handler.players[i2].Privatechat == 1
										&& handler.players[i2].isinpm(misc.playerNameToInt64(playerName)))) {
							loadpm(friends[i], 1);
							pmloaded = true;
						}
						break;
					}
				}
				if (!pmloaded)
					loadpm(friends[i], 0);
				pmloaded = false;
			}
			for (int i1 = 1; i1 < handler.maxPlayers; i1++) {
				if (handler.players[i1] != null && handler.players[i1].isActive == true) {
					handler.players[i1].pmupdate(playerId, 1);
				}
			}
		}
	}

	public void appendToLR(String report) {
		BufferedWriter bw = null;
	}

	public int readSave() {
		if (PlayerHandler.updateRunning) {
			returnCode = 14;
			disconnected = true;
			savefile = false;
			println(playerName + " refused - update is running !");
		}
		if (PlayerHandler.isPlayerOn(playerName)) {
			returnCode = 5;
			disconnected = true;
			savefile = false;
			println(playerName + " is already online.");
			return 3;
		} else {
			int LoadGame = loadGame(playerName, playerPass);
			if (LoadGame == 2) { // Wrong password, or invalid player
				returnCode = 3;
				disconnected = true;
				savefile = false;
				return 3;
			} else if (LoadGame == 3) { // you must make new user
				returnCode = 2;
				disconnected = false;
				savefile = true;
				boolean Found = true;
				for (int i = 0; i < server.MaxConnections; i++) {
					if (server.Connections[i] == connectedFrom) {
						server.ConnectionCount[i]++;
						Found = true;
						break;
					}
				}
				if (Found == false) {
					for (int i = 0; i < server.MaxConnections; i++) {
						if (server.Connections[i] == null) {
							server.Connections[i] = connectedFrom;
							server.ConnectionCount[i] = 1;
							break;
						}
					}
				}
			}
		}
		return 1;
	}

	public void sendMessage(String s) {
		outStream.createFrameVarSize(253);
		outStream.writeString(s);
		outStream.endFrameVarSize();
	}

	public void setSidebarInterface(int menuId, int form) {
		outStream.createFrame(71);
		outStream.writeWord(form);
		outStream.writeByteA(menuId);
	}

	public void setSkillLevel(int skillNum, int currentLevel, int XP) {
		if (skillNum == 0) {
			Sklnum(0, 4004, 4005);
		}
		if (skillNum == 1) {
			Sklnum(1, 4008, 4009);
		}
		if (skillNum == 2) {
			Sklnum(2, 4006, 4007);
		}
		if (skillNum == 3) {
			Sklnum(3, 4016, 4017);
		}
		if (skillNum == 4) {
			Sklnum(4, 4010, 4011);
		}
		if (skillNum == 5) {
			Sklnum(5, 4012, 4013);
		}
		if (skillNum == 6) {
			Sklnum(6, 4014, 4015);
		}
		if (skillNum == 7) {
			Sklnum(7, 4034, 4035);
		}
		if (skillNum == 8) {
			Sklnum(8, 4038, 4039);
		}
		if (skillNum == 9) {
			Sklnum(9, 4026, 4027);
		}
		if (skillNum == 10) {
			Sklnum(10, 4032, 4033);
		}
		if (skillNum == 11) {
			Sklnum(11, 4036, 4037);
		}
		if (skillNum == 12) {
			Sklnum(12, 4024, 4025);
		}
		if (skillNum == 13) {
			Sklnum(13, 4030, 4031);
		}
		if (skillNum == 14) {
			Sklnum(14, 4028, 4029);
		}
		if (skillNum == 15) {
			Sklnum(15, 4020, 4021);
		}
		if (skillNum == 16) {
			Sklnum(16, 4018, 4019);
		}
		if (skillNum == 17) {
			Sklnum(17, 4022, 4023);
		}
		if (skillNum == 18) {
			Sklnum(18, 12166, 12167);
		}
		if (skillNum == 19) {
			Sklnum(19, 13926, 13927);
		}
		if (skillNum == 20) {
			Sklnum(20, 4152, 4153);
		} else {
			outStream.createFrame(134);
			outStream.writeByte(skillNum);
			outStream.writeDWord_v1(XP);
			outStream.writeByte(currentLevel);
		}
	}

	public void Sklnum(int Skl, int frs, int scnd) {
		sendQuest("@yel@" + playerLevel[Skl] + "", frs);
		sendQuest("@yel@" + getLevelForXP(playerXP[Skl]) + "", scnd);
	}

	public void logout() {
		outStream.createFrame(109);
		if (playerRights >= 0) {
			PlayerHandler.messageToAll = playerName + " has logged out";
		}
	}

	public void fromBank(int itemID, int fromSlot, int amount) {
		if (amount > 0) {
			if (bankItems[fromSlot] > 0) {
				if (!takeAsNote) {
					if (Item.itemStackable[bankItems[fromSlot] + 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1), bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else
								amount = 0;
						}
						resetBank();
						resetItems(5064);
					}
				} else if (takeAsNote && Item.itemIsNote[bankItems[fromSlot]]) {
					if (bankItemsN[fromSlot] > amount) {
						if (addItem(bankItems[fromSlot], amount)) {
							bankItemsN[fromSlot] -= amount;
							resetBank();
							resetItems(5064);
						}
					} else {
						if (addItem(bankItems[fromSlot], bankItemsN[fromSlot])) {
							bankItems[fromSlot] = 0;
							bankItemsN[fromSlot] = 0;
							resetBank();
							resetItems(5064);
						}
					}
				} else {
					sendMessage("Item can't be drawn as note.");
					if (Item.itemStackable[bankItems[fromSlot] + 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1), bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else
								amount = 0;
						}
						resetBank();
						resetItems(5064);
					}
				}
			}
		}
	}

	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= ServerCP.Highestlevel; lvl++) {
			points += Math.floor((double) lvl + 300.0 * Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return ServerCP.Highestlevel;
	}

	public boolean addSkillXP(double amount, int skill) {
		int Attack = getLevelForXP(playerXP[0]);
		int Defence = getLevelForXP(playerXP[1]);
		int Strength = getLevelForXP(playerXP[2]);
		int Hitpoints = getLevelForXP(playerXP[3]);
		int Ranging = getLevelForXP(playerXP[4]);
		int Prayer = getLevelForXP(playerXP[5]);
		int Magic = getLevelForXP(playerXP[6]);
		int Cooking = getLevelForXP(playerXP[7]);
		int Woodcutting = getLevelForXP(playerXP[8]);
		int Fletching = getLevelForXP(playerXP[9]);
		int Fishing = getLevelForXP(playerXP[10]);
		int Firemaking = getLevelForXP(playerXP[11]);
		int Crafting = getLevelForXP(playerXP[12]);
		int Smithing = getLevelForXP(playerXP[13]);
		int Mining = getLevelForXP(playerXP[14]);
		int Herblore = getLevelForXP(playerXP[15]);
		int Agility = getLevelForXP(playerXP[16]);
		int Thieving = getLevelForXP(playerXP[17]);
		int Slayer = getLevelForXP(playerXP[18]);
		int Farming = getLevelForXP(playerXP[19]);
		int Runecrafting = getLevelForXP(playerXP[20]);
		if ((amount + playerXP[skill]) < 0 || playerXP[skill] > 2000000000) {
			sendMessage("Max XP value reached");
			return false;
		}
		int oldLevel = getLevelForXP(playerXP[skill]);
		playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(playerXP[skill])) {

			if (Attack < getLevelForXP(playerXP[0])) {
				gtlvlfrxp(0);
			}
			if (Defence < getLevelForXP(playerXP[1])) {
				gtlvlfrxp(1);
			}
			if (Strength < getLevelForXP(playerXP[2])) {
				gtlvlfrxp(2);
			}
			if (Hitpoints < getLevelForXP(playerXP[3])) {
				gtlvlfrxp(3);
			}
			if (Ranging < getLevelForXP(playerXP[4])) {
				gtlvlfrxp(4);
			}
			if (Prayer < getLevelForXP(playerXP[5])) {
				gtlvlfrxp(5);
			}
			if (Magic < getLevelForXP(playerXP[6])) {
				gtlvlfrxp(6);
			}
			if (Cooking < getLevelForXP(playerXP[7])) {
				gtlvlfrxp(7);
			}
			if (Woodcutting < getLevelForXP(playerXP[8])) {
				gtlvlfrxp(8);
			}
			if (Fletching < getLevelForXP(playerXP[9])) {
				gtlvlfrxp(9);
			}
			if (Fishing < getLevelForXP(playerXP[10])) {
				gtlvlfrxp(10);
			}
			if (Firemaking < getLevelForXP(playerXP[11])) {
				gtlvlfrxp(11);
			}
			if (Crafting < getLevelForXP(playerXP[12])) {
				gtlvlfrxp(12);
			}
			if (Smithing < getLevelForXP(playerXP[13])) {
				gtlvlfrxp(13);
			}
			if (Mining < getLevelForXP(playerXP[14])) {
				gtlvlfrxp(14);
			}
			if (Herblore < getLevelForXP(playerXP[15])) {
				gtlvlfrxp(15);
			}
			if (Agility < getLevelForXP(playerXP[16])) {
				gtlvlfrxp(16);
			}
			if (Thieving < getLevelForXP(playerXP[17])) {
				gtlvlfrxp(17);
			}
			if (Slayer < getLevelForXP(playerXP[18])) {
				gtlvlfrxp(18);
			}
			if (Farming < getLevelForXP(playerXP[19])) {
				gtlvlfrxp(19);
			}
			if (Runecrafting < getLevelForXP(playerXP[20])) {
				gtlvlfrxp(20);
			}
			playerLevel[skill] = getLevelForXP(playerXP[skill]);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		setSkillLevel(skill, playerLevel[skill], playerXP[skill]);
		refreshSkills();
		return true;
	}

	public void gtlvlfrxp(int lvlz) {
		playerLevel[lvlz] = getLevelForXP(playerXP[lvlz]);
		levelup(lvlz);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public boolean bankItem(int itemID, int fromSlot, int amount) {
		if (playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[playerItems[fromSlot] - 1]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1] || playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						if (playerItemsN[fromSlot] < amount)
							amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = playerItems[fromSlot];
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1) {
						bankItemsN[toBankSlot] += amount;
					} else {
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1) {
						bankItemsN[toBankSlot] += amount;
					} else {
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = playerItems[firstPossibleSlot];
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1), firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1), firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			}
		} else if (Item.itemIsNote[playerItems[fromSlot] - 1] && !Item.itemIsNote[playerItems[fromSlot] - 2]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1] || playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						if (playerItemsN[fromSlot] < amount)
							amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = (playerItems[fromSlot] - 1);
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = (playerItems[firstPossibleSlot] - 1);
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1), firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1), firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			sendMessage("Item not supported " + (playerItems[fromSlot] - 1));
			return false;
		}
	}

	public void createItem(int newItemID) {
		int Maxi = server.itemHandler.DropItemCount;
		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = (absX);
				server.itemHandler.DroppedItemsY[i] = (absY);
				server.itemHandler.DroppedItemsN[i] = 1;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); // this way the
																										// item can
																										// NEVER be
																										// showed to
																										// another
																										// client
				server.itemHandler.DroppedItemsDropper[i] = playerId;
				if (i == Maxi) {
					server.itemHandler.DropItemCount++;
					if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
						server.itemHandler.DropItemCount = 0;
						println("! Notify item resterting !");
					}
				}
				break;
			}
		}
	}

	public void removeAllItems() {
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	public void resetItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerItems.length);
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(playerItemsN[i]); // and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(playerItemsN[i]);
			}
			if (playerItems[i] > 10000 || playerItems[i] < 0) {
				playerItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(playerItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void wpon(int Wut, int Za, int Wa, int Weapon, String WeaponName) {
		setSidebarInterface(0, Wut);
		sendFrame246(Za, 200, Weapon);
		sendFrame126(WeaponName, Wa);
	}

	public void SendWeapon(int Weapon, String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed") || playerEquipment[playerWeapon] == -1) {
			setSidebarInterface(0, 5855); // punch, kick, block
			sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			wpon(12290, 12291, 12293, Weapon, WeaponName);
		} else if (WeaponName.endsWith("bow")) {
			wpon(1764, 1765, 1767, Weapon, WeaponName);
		} else if (WeaponName.endsWith("Bow")) {
			wpon(1764, 1765, 1767, Weapon, WeaponName);
		} else if (WeaponName.startsWith("crystal")) {
			wpon(1764, 1765, 1767, Weapon, WeaponName);
		} else if (WeaponName.startsWith("seercull")) {
			wpon(1764, 1765, 1767, Weapon, WeaponName);
		} else if (WeaponName.startsWith("Comp ogre bow")) {
			wpon(1764, 1765, 1767, Weapon, "Darkbow");
		} else if (WeaponName.startsWith("Staff") || WeaponName.endsWith("staff")) {
			wpon(328, 329, 331, Weapon, WeaponName);
		} else if (WeaponName2.startsWith("dart")) {
			wpon(4446, 4447, 4449, Weapon, WeaponName);
		} else if (WeaponName2.startsWith("dagger")) {
			wpon(2276, 2277, 2279, Weapon, WeaponName);
		} else if (WeaponName2.startsWith("pickaxe")) {
			wpon(5570, 5571, 5573, Weapon, WeaponName);
		} else if (WeaponName2.startsWith("axe") || WeaponName2.startsWith("battleaxe")) {
			wpon(1698, 1699, 1701, Weapon, WeaponName);
		} else if (WeaponName2.startsWith("halberd")) {
			wpon(8460, 8461, 8463, Weapon, WeaponName);
		} else if (WeaponName2.startsWith("spear")) {
			wpon(4679, 4680, 4682, Weapon, WeaponName);
		} else if (WeaponName2.startsWith("claws")) {
			wpon(7762, 7763, 7764, Weapon, WeaponName);
		} else {
			wpon(2423, 2424, 2426, Weapon, WeaponName);
		}
	}

	public void resetTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerTItems.length);
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(playerTItemsN[i]); // and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(playerTItemsN[i]);
			}
			if (playerTItems[i] > 10000 || playerTItems[i] < 0) {
				playerTItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(playerTItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetOTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerOTItems.length);
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(playerOTItemsN[i]); // and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(playerOTItemsN[i]);
			}
			if (playerOTItems[i] > 10000 || playerOTItems[i] < 0) {
				playerOTItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(playerOTItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetShop(int ShopID) {
		int TotalItems = 0;
		for (int i = 0; i < server.shopHandler.MaxShopItems; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0) {
				TotalItems++;
			}
		}
		if (TotalItems > server.shopHandler.MaxShopItems) {
			TotalItems = server.shopHandler.MaxShopItems;
		}
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(3900);
		outStream.writeWord(TotalItems);
		int TotalCount = 0;
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0 || i <= server.shopHandler.ShopItemsStandard[ShopID]) {
				if (server.shopHandler.ShopItemsN[ShopID][i] > 254) {
					outStream.writeByte(255); // item's stack count. if over 254, write byte 255
					outStream.writeDWord_v2(server.shopHandler.ShopItemsN[ShopID][i]); // and then the real value with
																						// writeDWord_v2
				} else {
					outStream.writeByte(server.shopHandler.ShopItemsN[ShopID][i]);
				}
				if (server.shopHandler.ShopItems[ShopID][i] > 10000 || server.shopHandler.ShopItems[ShopID][i] < 0) {
					server.shopHandler.ShopItems[ShopID][i] = 10000;
				}
				outStream.writeWordBigEndianA(server.shopHandler.ShopItems[ShopID][i]); // item id
				TotalCount++;
			}
			if (TotalCount > TotalItems) {
				break;
			}
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetBank() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(5382); // bank
		outStream.writeWord(playerBankSize); // number of items
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(bankItemsN[i]);
			} else {
				outStream.writeByte(bankItemsN[i]); // amount
			}
			if (bankItemsN[i] < 1)
				bankItems[i] = 0;
			if (bankItems[i] > 10000 || bankItems[i] < 0) {
				bankItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(bankItems[i]); // itemID
		}
		outStream.endFrameVarSizeWord();
	}

	public void moveItems(int from, int to, int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;
			tempI = playerItems[from];
			tempN = playerItemsN[from];

			playerItems[from] = playerItems[to];
			playerItemsN[from] = playerItemsN[to];
			playerItems[to] = tempI;
			playerItemsN[to] = tempN;
		}

		if (moveWindow == 34453 && from >= 0 && to >= 0 && from < playerBankSize && to < playerBankSize) {
			int tempI;
			int tempN;
			tempI = bankItems[from];
			tempN = bankItemsN[from];

			bankItems[from] = bankItems[to];
			bankItemsN[from] = bankItemsN[to];
			bankItems[to] = tempI;
			bankItemsN[to] = tempN;
		}

		if (moveWindow == 34453) {
			resetBank();
		} else if (moveWindow == 18579) {
			resetItems(5064);
		} else if (moveWindow == 3724) {
			resetItems(3214);
		}
	}

	public int itemAmount(int itemID) {
		int tempAmount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID) {
				tempAmount += playerItemsN[i];
			}
		}
		return tempAmount;
	}

	public int freeBankSlots() {
		int freeS = 0;
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public int freeSlots() {
		int freeS = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public int freeTradeSlots() {
		int freeS = 0;
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public boolean pickUpItem(int item, int amount) {

		if (!Item.itemStackable[item] || amount < 1) {
			amount = 1;
		}

		if (freeSlots() > 0 && poimiY == currentY && poimiX == currentX) {

			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] == (item + 1) && Item.itemStackable[item] && playerItems[i] > 0) {
					playerItems[i] = item + 1;
					if ((playerItemsN[i] + amount) < maxItemAmount && (playerItemsN[i] + amount) > 0) {
						playerItemsN[i] += amount;
					} else {
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] <= 0) {
					playerItems[i] = item + 1;
					if (amount < maxItemAmount) {
						playerItemsN[i] = amount;
					} else {
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord_v2(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public void openUpBank() {
		try {
			sendFrame248(5292, 5063);
			resetItems(5064);
			IsBanking = true;
		} catch (Exception e) {
		}
	}

	public void openUpShop(int ShopID) {
		sendFrame126(server.shopHandler.ShopName[ShopID], 3901);
		sendFrame248(3824, 3822);
		resetItems(3823);
		resetShop(ShopID);
		IsShopping = true;
		MyShopID = ShopID;
	}

	public boolean addItem(int item, int amount) {
		try {
			if (item == -1)
				return false;
			if (!Item.itemStackable[item] || amount < 1) {
				amount = 1;
			}

			if ((freeSlots() >= amount && !Item.itemStackable[item]) || freeSlots() > 0) {
				for (int i = 0; i < playerItems.length; i++) {
					if (playerItems[i] == (item + 1) && Item.itemStackable[item] && playerItems[i] > 0) {
						playerItems[i] = (item + 1);
						if ((playerItemsN[i] + amount) < maxItemAmount && (playerItemsN[i] + amount) > -1) {
							playerItemsN[i] += amount;
						} else {
							playerItemsN[i] = maxItemAmount;
						}
						outStream.createFrameVarSizeWord(34);
						outStream.writeWord(3214);
						outStream.writeByte(i);
						outStream.writeWord(playerItems[i]);
						if (playerItemsN[i] > 254) {
							outStream.writeByte(255);
							outStream.writeDWord(playerItemsN[i]);
						} else {
							outStream.writeByte(playerItemsN[i]); // amount
						}
						outStream.endFrameVarSizeWord();
						i = 30;
						return true;
					}
				}
				for (int i = 0; i < playerItems.length; i++) {
					if (playerItems[i] <= 0) {
						playerItems[i] = item + 1;
						if (amount < maxItemAmount && amount > -1) {
							playerItemsN[i] = amount;
						} else {
							playerItemsN[i] = maxItemAmount;
						}
						outStream.createFrameVarSizeWord(34);
						outStream.writeWord(3214);
						outStream.writeByte(i);
						outStream.writeWord(playerItems[i]);
						if (playerItemsN[i] > 254) {
							outStream.writeByte(255);
							outStream.writeDWord(playerItemsN[i]);
						} else {
							outStream.writeByte(playerItemsN[i]); // amount
						}
						outStream.endFrameVarSizeWord();
						i = 30;
						return true;
					}
				}
				return false;
			} else {
				sendMessage("Not enough space in your inventory.");
				return false;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public void dropItem(int droppedItem, int slot) {
		if (playerItemsN[slot] != 0 && droppedItem != -1 && playerItems[slot] == droppedItem + 1) {
			ItemHandler.addItem(playerItems[slot] - 1, absX, absY, playerItemsN[slot], playerId, false);
			deleteItem(droppedItem, slot, playerItemsN[slot]);
			updateRequired = true;
		}
	}

	public void createGroundItem(int itemID, int itemX, int itemY, int itemAmount) {// Phate: Omg fucking sexy! creates
																					// item at absolute X and Y
		outStream.createFrame(85); // Phate: Spawn ground item
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(44);
		outStream.writeWordBigEndianA(itemID);
		outStream.writeWord(itemAmount);
		outStream.writeByte(0);
	}

	public void removeGroundItem(int itemX, int itemY, int itemID) {// Phate: Omg fucking sexy! remoevs an item from
																	// absolute X and Y
		outStream.createFrame(85); // Phate: Item Position Frame
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(156); // Phate: Item Action: Delete
		outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
		outStream.writeWord(itemID); // Phate: Item ID
	}

	public boolean deleteItem(int id, int slot, int amount) {
		if (slot > -1 && slot < playerItems.length) {
			if ((playerItems[slot] - 1) == id) {
				if (playerItemsN[slot] > amount) {
					playerItemsN[slot] -= amount;
				} else {
					playerItemsN[slot] = 0;
					playerItems[slot] = 0;
				}
				resetItems(3214);
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public void setEquipment(int wearID, int amount, int targetSlot) {
		try {
			int Stat = playerDefence;
			if (targetSlot == playerWeapon) {
				Stat = playerAttack;
			}
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(targetSlot);
			outStream.writeWord((wearID + 1));
			if (amount > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(amount);
			} else {
				outStream.writeByte(amount); // amount
			}
			outStream.endFrameVarSizeWord();

			if (targetSlot == playerWeapon && wearID >= 0) {
				SendWeapon(wearID, GetItemName(wearID));
				playerSE = GetStandAnim(wearID);
				playerSEW = GetWalkAnim(wearID);
				playerSER = GetRunAnim(wearID);
				playerSEA = 0x326;
				if (item2handed(wearID) == true) {
					playerSE = 0x811;
					playerSEW = 0x67F;
					playerSER = 0x680;
				}
				if (wearID == 4747) { // Torag Hammers
					playerSEA = 0x814;
				}
				if (wearID == 4151) { // Whip
					playerSER = 1661;
					playerSEW = 1660;
				}
				if (wearID == 4726) { // Guthans Warspear
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 4153 || wearID == 6528) { // maul
					playerSER = 2064;
					playerSEW = 2064;
					playerSE = 2065;
				}
				if (wearID == 1215) { // d dagger
					playerSER = 1661;
					playerSEW = 1660;
					stillgfx(306, absY, absX);
				}
				if (wearID == 6914) {
					// Master Wand
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				if (wearID == 4675) {// Ancient staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				if (wearID == 1401) {// fire staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				pEmote = playerSE;
			}
			SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
			updateRequired = true;
			appearanceUpdateRequired = true;
		} catch (Exception e) {
		}
	}

	public boolean wear(int wearID, int slot) {
		try {
			int targetSlot = 0;
			if ((playerItems[slot] - 1) == wearID) {
				resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				targetSlot = itemType(wearID);
				int CLAttack = GetCLAttack(wearID);
				int CLDefence = GetCLDefence(wearID);
				int CLStrength = GetCLStrength(wearID);
				int CLMagic = GetCLMagic(wearID);
				int CLRanged = GetCLRanged(wearID);
				boolean GoFalse = false;
				if (playerLevel[playerAttack] - CLAttack < 0) {
					sendMessage("You need " + CLAttack + " " + statName[playerAttack] + " to equip this item.");
					GoFalse = true;
				}
				if (playerLevel[playerDefence] - CLDefence < 0) {
					sendMessage("You need " + CLDefence + " " + statName[playerDefence] + " to equip this item.");
					GoFalse = true;
				}
				if (playerLevel[playerStrength] - CLStrength < 0) {
					sendMessage("You need " + CLStrength + " " + statName[playerStrength] + " to equip this item.");
					GoFalse = true;
				}
				if (playerLevel[playerMagic] - CLMagic < 0) {
					sendMessage("You need " + CLMagic + " " + statName[playerMagic] + " to equip this item.");
					GoFalse = true;
				}
				if (playerLevel[playerRanged] - CLRanged < 0) {
					sendMessage("You need " + CLRanged + " " + statName[playerRanged] + " to equip this item.");
					GoFalse = true;
				}
				if (GoFalse == true) {
					return false;
				}
				int wearAmount = playerItemsN[slot];
				if (wearAmount < 1) {
					return false;
				}
				wearing = true;
				if (slot >= 0 && wearID >= 0) {
					deleteItem(wearID, slot, wearAmount);
					if (playerEquipment[targetSlot] != wearID && playerEquipment[targetSlot] >= 0) {
						addItem(playerEquipment[targetSlot], playerEquipmentN[targetSlot]);
						resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
					} else if (Item.itemStackable[wearID] && playerEquipment[targetSlot] == wearID) {
						wearAmount = playerEquipmentN[targetSlot] + wearAmount;
					} else if (playerEquipment[targetSlot] >= 0) {
						addItem(playerEquipment[targetSlot], playerEquipmentN[targetSlot]);
						resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
					}
				}
				outStream.createFrameVarSizeWord(34);
				outStream.writeWord(1688);
				outStream.writeByte(targetSlot);
				outStream.writeWord(wearID + 1);
				if (wearAmount > 254) {
					outStream.writeByte(255);
					outStream.writeDWord(wearAmount);
				} else {
					outStream.writeByte(wearAmount); // amount
				}
				outStream.endFrameVarSizeWord();
				playerEquipment[targetSlot] = wearID;
				playerEquipmentN[targetSlot] = wearAmount;
				if (targetSlot == playerWeapon && playerEquipment[playerShield] != -1
						&& (Item.itemTwoHanded[wearID] == true || item2handed(wearID) == true)) {
					remove(playerEquipment[playerShield], playerShield);
				}
				if (targetSlot == playerWeapon) {
					SendWeapon(wearID, GetItemName(wearID));
					playerSE = GetStandAnim(wearID);
					playerSEW = GetWalkAnim(wearID);
					playerSER = GetRunAnim(wearID);
					playerSEA = 0x326;
					if (item2handed(wearID) == true) {
						playerSE = 0x811;
						playerSEW = 0x67F;
						playerSER = 0x680;
					}
					if (wearID == 4747) { // Torag Hammers
						playerSEA = 0x814;
					}
					if (wearID == 4151) { // Whip
						playerSER = 1661;
						playerSEW = 1660;
					}
					pEmote = playerSE;
				}
				ResetBonus();
				GetBonus();
				WriteBonus();
				SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
				updateRequired = true;
				appearanceUpdateRequired = true;
				wearing = false;
				return true;
			}
			return false;
		} catch (Exception e) {
		}
		return false;
	}

	public int itemType(int item) {
		for (int i = 0; i < Item.capes.length; i++) {
			if (item == Item.capes[i]) {
				return playerCape;
			}
		}
		for (int i = 0; i < Item.hats.length; i++) {
			if (item == Item.hats[i]) {
				return playerHat;
			}
		}
		for (int i = 0; i < Item.boots.length; i++) {
			if (item == Item.boots[i]) {
				return playerFeet;
			}
		}
		for (int i = 0; i < Item.gloves.length; i++) {
			if (item == Item.gloves[i]) {
				return playerHands;
			}
		}
		for (int i = 0; i < Item.shields.length; i++) {
			if (item == Item.shields[i]) {
				return playerShield;
			}
		}
		for (int i = 0; i < Item.amulets.length; i++) {
			if (item == Item.amulets[i]) {
				return playerAmulet;
			}
		}
		for (int i = 0; i < Item.arrows.length; i++) {
			if (item == Item.arrows[i]) {
				return playerArrows;
			}
		}
		for (int i = 0; i < Item.rings.length; i++) {
			if (item == Item.rings[i]) {
				return playerRing;
			}
		}
		for (int i = 0; i < Item.body.length; i++) {
			if (item == Item.body[i]) {
				return playerChest;
			}
		}
		for (int i = 0; i < Item.legs.length; i++) {
			if (item == Item.legs[i]) {
				return playerLegs;
			}
		}
		return playerWeapon;
	}

	public void remove(int wearID, int slot) {
		if (addItem(playerEquipment[slot], playerEquipmentN[slot])) {
			resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
			playerEquipment[slot] = -1;
			playerEquipmentN[slot] = 0;
			outStream.createFrame(34);
			outStream.writeWord(6);
			outStream.writeWord(1688);
			outStream.writeByte(slot);
			outStream.writeWord(0);
			outStream.writeByte(0);
			ResetBonus();
			GetBonus();
			WriteBonus();
			if (slot == playerWeapon) {
				SendWeapon(-1, "Unarmed");
			}
			SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void deleteequiment(int wearID, int slot) {
		playerEquipment[slot] = -1;
		playerEquipmentN[slot] = 0;
		outStream.createFrame(34);
		outStream.writeWord(6);
		outStream.writeWord(1688);
		outStream.writeByte(slot);
		outStream.writeWord(0);
		outStream.writeByte(0);
		ResetBonus();
		GetBonus();
		WriteBonus();
		if (slot == playerWeapon) {
			SendWeapon(-1, "Unarmed");
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		outStream.createFrame(206);
		outStream.writeByte(publicChat); // On = 0, Friends = 1, Off = 2, Hide = 3
		outStream.writeByte(privateChat); // On = 0, Friends = 1, Off = 2
		outStream.writeByte(tradeBlock); // On = 0, Friends = 1, Off = 2
	}

	public int GetLastLogin(int Date) {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((year * 10000) + (month * 100) + day);
		return (calc - Date);
	}

	public void Skillline(int skill, int line) {
		sendQuest("@yel@" + playerLevel[skill] + "", line);
	}

	public void GtlvlfrXp(int skill, int line) {
		sendQuest("@yel@" + getLevelForXP(playerXP[skill]) + "", line);
	}

	public void PlrXP(int skill, int line) {
		sendQuest("@yel@" + playerXP[skill] + "", line);
	}

	public void refreshSkills() {
		Skillline(0, 4004);
		Skillline(1, 4008);
		Skillline(2, 4006);
		Skillline(3, 4016);
		Skillline(4, 4010);
		Skillline(5, 4012);
		Skillline(6, 4014);
		Skillline(7, 4034);
		Skillline(8, 4038);
		Skillline(9, 4026);
		Skillline(10, 4032);
		Skillline(11, 4036);
		Skillline(12, 4024);
		Skillline(13, 4030);
		Skillline(14, 4028);
		Skillline(15, 4020);
		Skillline(16, 4018);
		Skillline(17, 4022);
		Skillline(18, 12166);
		Skillline(19, 13926);
		Skillline(20, 4152);
		GtlvlfrXp(0, 4005);
		GtlvlfrXp(1, 4009);
		GtlvlfrXp(2, 4007);
		GtlvlfrXp(3, 4017);
		GtlvlfrXp(4, 4011);
		GtlvlfrXp(5, 4013);
		GtlvlfrXp(6, 4015);
		GtlvlfrXp(7, 4035);
		GtlvlfrXp(8, 4039);
		GtlvlfrXp(9, 4027);
		GtlvlfrXp(10, 4033);
		GtlvlfrXp(11, 4037);
		GtlvlfrXp(12, 4025);
		GtlvlfrXp(13, 4031);
		GtlvlfrXp(14, 4029);
		GtlvlfrXp(15, 4021);
		GtlvlfrXp(16, 4019);
		GtlvlfrXp(17, 4023);
		GtlvlfrXp(18, 12167);
		GtlvlfrXp(19, 13927);
		GtlvlfrXp(20, 4153);
		PlrXP(0, 4044);
		PlrXP(1, 4056);
		PlrXP(2, 4050);
		PlrXP(3, 4080);
		PlrXP(4, 4062);
		PlrXP(5, 4068);
		PlrXP(6, 4074);
		PlrXP(7, 4134);
		PlrXP(8, 4146);
		PlrXP(9, 4110);
		PlrXP(10, 4128);
		PlrXP(11, 4140);
		PlrXP(12, 4104);
		PlrXP(13, 4122);
		PlrXP(14, 4116);
		PlrXP(15, 4092);
		PlrXP(16, 4086);
		PlrXP(17, 4098);
		PlrXP(18, 12171);
		PlrXP(19, 13921);
		PlrXP(20, 4157);
	}

	public void initialize() {
		// first packet sent
		outStream.createFrame(249);
		outStream.writeByteA(1); // 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);

		setChatOptions(0, 0, 0);
		for (int i = 0; i < 25; i++)
			setSkillLevel(i, playerLevel[i], playerXP[i]);
		refreshSkills();

		outStream.createFrame(107); // resets something in the client
		setSidebarInterface(1, 3917);
		setSidebarInterface(2, 638);
		setSidebarInterface(3, 3213);
		setSidebarInterface(4, 1644);
		setSidebarInterface(5, 5608);
		setSidebarInterface(8, 5065);
		setSidebarInterface(9, 5715);
		setSidebarInterface(10, 2449);
		setSidebarInterface(11, 904);
		setSidebarInterface(12, 147);
		setSidebarInterface(13, 1);
		setSidebarInterface(0, 2423);

		server.prayerHandler.resetPrayer(playerId);

		if (ancients == 0) {
			setSidebarInterface(6, 1151);
		}
		if (ancients == 1) {
			setSidebarInterface(6, 12855);
		}

		if (playerLastConnect.length() < 7) {
			playerLastConnect = connectedFrom;
		}
		if (playerLastConnect.length() <= 15) {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals(".")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 3)
						break;
				}
			}
			if (dots == 3) {
				IPPart1 = Integer.parseInt(playerLastConnect.substring(0, start[0]));
				IPPart2 = Integer.parseInt(playerLastConnect.substring((start[0] + 1), start[1]));
				IPPart3 = Integer.parseInt(playerLastConnect.substring((start[1] + 1), start[2]));
				IPPart4 = Integer.parseInt(playerLastConnect.substring((start[2] + 1)));
			}
		} else {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals("-")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 4)
						break;
				}
			}
			if (dots == 4) {
				try {
					IPPart1 = Integer.parseInt(playerLastConnect.substring(0, start[0]));
					IPPart2 = Integer.parseInt(playerLastConnect.substring((start[0] + 1), start[1]));
					IPPart3 = Integer.parseInt(playerLastConnect.substring((start[1] + 1), start[2]));
					IPPart4 = Integer.parseInt(playerLastConnect.substring((start[2] + 1), (start[3])));
				} catch (NumberFormatException e) {
				}
			}
		}
		playerLastConnect = connectedFrom;
		sendMessage("Welcome to " + ServerCP.Servername);

		ResetBonus();
		GetBonus();
		WriteBonus();
		Poisoned = false;
		SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
		resetBank();

		// Objects
		for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectID[i] > -1) {
				if (server.objectHandler.ObjectOpen[i] != server.objectHandler.ObjectOriOpen[i]) {
					ChangeDoor(i);
				}
			}
		}

		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		resetItems(3214);
		resetBank();
		setEquipment(playerEquipment[playerHat], 1, playerHat);
		setEquipment(playerEquipment[playerCape], 1, playerCape);
		setEquipment(playerEquipment[playerAmulet], 1, playerAmulet);
		setEquipment(playerEquipment[playerArrows], 190, playerArrows);
		setEquipment(playerEquipment[playerChest], 1, playerChest);
		setEquipment(playerEquipment[playerShield], 1, playerShield);
		setEquipment(playerEquipment[playerLegs], 1, playerLegs);
		setEquipment(playerEquipment[playerHands], 1, playerHands);
		setEquipment(playerEquipment[playerFeet], 1, playerFeet);
		setEquipment(playerEquipment[playerRing], 1, playerRing);
		setEquipment(playerEquipment[playerWeapon], 1, playerWeapon);

		update();

	}

	public void update() {
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();
	}

	public static final int packetSizes[] = { 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, // 0
			0, 0, 0, 0, 8, 0, 6, 2, 2, 0, // 10
			0, 2, 0, 6, 0, 12, 0, 0, 0, 0, // 20
			0, 0, 0, 0, 0, 8, 4, 0, 0, 2, // 30
			2, 6, 0, 6, 0, -1, 0, 0, 0, 0, // 40
			0, 0, 0, 12, 0, 0, 0, 0, 8, 0, // 50
			0, 8, 0, 0, 0, 0, 0, 0, 0, 0, // 60
			6, 0, 2, 2, 8, 6, 0, -1, 0, 6, // 70
			0, 0, 0, 0, 0, 1, 4, 6, 0, 0, // 80
			0, 0, 0, 0, 0, 3, 0, 0, -1, 0, // 90
			0, 13, 0, -1, 0, 0, 0, 0, 0, 0, // 100
			0, 0, 0, 0, 0, 0, 0, 6, 0, 0, // 110
			1, 0, 6, 0, 0, 0, -1, 0, 2, 6, // 120
			0, 4, 6, 8, 0, 6, 0, 0, 0, 2, // 130
			0, 0, 0, 0, 0, 6, 0, 0, 0, 0, // 140
			0, 0, 1, 2, 0, 2, 6, 0, 0, 0, // 150
			0, 0, 0, 0, -1, -1, 0, 0, 0, 0, // 160
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
			0, 8, 0, 3, 0, 2, 0, 0, 8, 1, // 180
			0, 0, 12, 0, 0, 0, 0, 0, 0, 0, // 190
			2, 0, 0, 0, 0, 0, 0, 0, 4, 0, // 200
			4, 0, 0, 0, 7, 8, 0, 0, 10, 0, // 210
			0, 0, 0, 0, 0, 0, -1, 0, 6, 0, // 220
			1, 0, 0, 0, 6, 0, 6, 8, 1, 0, // 230
			0, 4, 0, 0, 0, 0, -1, 0, -1, 4, // 240
			0, 0, 6, 6, 0, 0, 0 // 250
	};

	public void deleteObject(int objectX, int objectY) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);
	}

	public void fsBar(int id1, int id2, int id3) {
		outStream.createFrame(70);
		outStream.writeWord(id1);
		outStream.writeWordBigEndian(id2);
		outStream.writeWordBigEndian(id3);
	}

	public void sendFrame230(int i1, int i2, int i3, int i4) {// i2 being negative logs you out, otherwise it doesn't
																// log you out :O
		outStream.createFrame(230);
		outStream.writeWordA(i1);
		outStream.writeWord(i2); // interface id?
		outStream.writeWord(i3);
		outStream.writeWordBigEndianA(i4); // junk? not sure
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void l33thax(int id) {
		outStream.createFrame(171);
		outStream.writeByte(0);
		outStream.writeWord(id);
		flushOutStream();
	}

	public void FsBaR(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		fsBar(a, 0, 12325);
		fsBar(b, 0, 12326);
		fsBar(c, 0, 12327);
		fsBar(d, 0, 12328);
		fsBar(e, 0, 12329);
		fsBar(f, 0, 12330);
		fsBar(g, 0, 12331);
		fsBar(h, 0, 12332);
		fsBar(i, 0, 12333);
		fsBar(j, 0, 12334);
	}

	public void specialAttacks() {
		if (specialAmount >= 0 && specialAmount <= 24) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 12335);
			FsBaR(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 12335);
			FsBaR(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 12335);
			FsBaR(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 12335);
			FsBaR(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 12335);
			FsBaR(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true) {
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 12335);
			FsBaR(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 12335);
			FsBaR(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 12335);
			FsBaR(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A C K", 12335);
			FsBaR(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
	}

	public void FsBaR2(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		fsBar(a, 0, 7576);
		fsBar(b, 0, 7577);
		fsBar(c, 0, 7578);
		fsBar(d, 0, 7579);
		fsBar(e, 0, 7580);
		fsBar(f, 0, 7581);
		fsBar(g, 0, 7582);
		fsBar(h, 0, 7583);
		fsBar(i, 0, 7584);
		fsBar(j, 0, 7585);
	}

	public void specialAttacks2() {
		if (specialAmount >= 0 && specialAmount <= 24) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7586);
			FsBaR2(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7586);
			FsBaR2(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7586);
			FsBaR2(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7586);
			FsBaR2(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7586);
			FsBaR2(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true) {
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7586);
			FsBaR2(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7586);
			FsBaR2(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7586);
			FsBaR2(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A C K", 7586);
			FsBaR2(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
	}

	public void FsBaR3(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		fsBar(a, 0, 7501);
		fsBar(b, 0, 7502);
		fsBar(c, 0, 7503);
		fsBar(d, 0, 7504);
		fsBar(e, 0, 7505);
		fsBar(f, 0, 7506);
		fsBar(g, 0, 7507);
		fsBar(h, 0, 7508);
		fsBar(i, 0, 7509);
		fsBar(j, 0, 7510);
	}

	public void specialAttacks3() {
		if (specialAmount >= 0 && specialAmount <= 24) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7611);
			FsBaR3(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7611);
			FsBaR3(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7611);
			FsBaR3(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7611);
			FsBaR3(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7611);
			FsBaR3(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true) {
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7611);
			FsBaR3(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7611);
			FsBaR3(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7611);
			FsBaR3(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A C K", 7611);
			FsBaR3(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
	}

	public void FsBaR4(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		fsBar(a, 0, 7551);
		fsBar(b, 0, 7552);
		fsBar(c, 0, 7553);
		fsBar(d, 0, 7554);
		fsBar(e, 0, 7555);
		fsBar(f, 0, 7556);
		fsBar(g, 0, 7557);
		fsBar(h, 0, 7558);
		fsBar(i, 0, 7559);
		fsBar(j, 0, 7560);
	}

	public void specialAttacks4() {
		if (specialAmount >= 0 && specialAmount <= 24) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7561);
			FsBaR4(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7561);
			FsBaR4(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7561);
			FsBaR4(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7561);
			FsBaR4(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7561);
			FsBaR4(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true) {
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7561);
			FsBaR4(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7561);
			FsBaR4(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A C K", 7561);
			FsBaR4(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A C K", 7561);
			FsBaR4(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
	}

	public void FsBaR5(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		fsBar(a, 0, 8495);
		fsBar(b, 0, 8496);
		fsBar(c, 0, 8497);
		fsBar(d, 0, 8498);
		fsBar(e, 0, 8499);
		fsBar(f, 0, 8500);
		fsBar(g, 0, 8501);
		fsBar(h, 0, 8502);
		fsBar(i, 0, 8503);
		fsBar(j, 0, 8504);
	}

	public void specialAttacks5() {
		if (specialAmount >= 0 && specialAmount <= 24) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 8505);
			FsBaR5(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 8505);
			FsBaR5(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 8505);
			FsBaR5(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 8505);
			FsBaR5(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 8505);
			FsBaR5(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true) {
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 8505);
			FsBaR5(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 8505);
			FsBaR5(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 8505);
			FsBaR5(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A C K", 8505);
			FsBaR5(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
	}

	public void FsBaR6(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		fsBar(a, 0, 7501);
		fsBar(b, 0, 7502);
		fsBar(c, 0, 7503);
		fsBar(d, 0, 7504);
		fsBar(e, 0, 7505);
		fsBar(f, 0, 7506);
		fsBar(g, 0, 7507);
		fsBar(h, 0, 7508);
		fsBar(i, 0, 7509);
		fsBar(j, 0, 7510);
	}

	public void specialAttacks6() {
		if (specialAmount >= 0 && specialAmount <= 24) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7511);
			FsBaR6(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7511);
			FsBaR6(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7511);
			FsBaR6(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7511);
			FsBaR6(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == false) {
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7511);
			FsBaR6(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true) {
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7511);
			FsBaR6(500, 500, 500, 0, 0, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7511);
			FsBaR6(500, 500, 500, 500, 500, 0, 0, 0, 0, 0);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7511);
			FsBaR6(500, 500, 500, 500, 500, 500, 500, 500, 0, 0);
		}
		if (specialAmount >= 100 && usingSpecial == true) {
			sendFrame126("@yel@S P E C I A L  A T T A C K", 7511);
			FsBaR6(500, 500, 500, 500, 500, 500, 500, 500, 500, 500);
		}
	}

	public void calculateSpecial() {
		if (playerEquipment[playerWeapon] == 4153 && specialAmount <= 49) {// maul
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 4153 && specialAmount >= 50) {// maul
			startAnimation(1667);
			maulSpec();
			hitDiff = 10 + misc.random(playerMaxHitMelee);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 6739 && specialAmount <= 99) {// d axe
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 6739 && specialAmount >= 100) {// d axe
			startAnimation(2876);
			hitDiff = 10 + misc.random(playerMaxHitMelee);
			specialAmount -= 100;
		}
		if (playerEquipment[playerWeapon] == 3204 && specialAmount <= 99) {// hally
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 3204 && specialAmount >= 100) {// hally
			startAnimation(1667);
			hally();
			hitDiff = 10 + misc.random(playerMaxHitMelee);
			specialAmount -= 100;
		}
		if (playerEquipment[playerWeapon] == 861 && specialAmount <= 74) {// bow
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 861 && specialAmount >= 75) {// bow
			startAnimation(426);
			bowSpec();
		}
		if (playerEquipment[playerWeapon] == 5698 && specialAmount <= 24) {// dagger
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 5698 && specialAmount >= 25) {// dagger
			DDSSpecial();
			specialAmount -= 25;
		}
		if (playerEquipment[playerWeapon] == 4151 && specialAmount <= 49) {// whip
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 4151 && specialAmount >= 50) {// whip
			startAnimation(1658);
			hitDiff = 10 + misc.random(playerMaxHitMelee);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 1305 && specialAmount <= 49) {// d long
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 1305 && specialAmount >= 50) {// d long
			startAnimation(451);
			hitDiff = 10 + misc.random(playerMaxHitMelee);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 1434 && specialAmount <= 39) {// d mace
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 1434 && specialAmount >= 40) {// d mace
			startAnimation(1060);
			hitDiff = 20 + misc.random(playerMaxHitMelee);
			specialAmount -= 40;
		}
		if (playerEquipment[playerWeapon] == 4587 && specialAmount <= 74) {// d skim
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 4587 && specialAmount >= 75) {// d skim
			startAnimation(451);
			hitDiff = 10 + misc.random(playerMaxHitMelee);
			specialAmount -= 75;
		}
	}

	public void setInterfaceWalkable(int ID) {

		outStream.createFrame(208);
		outStream.writeWordBigEndian_dup(ID);
		flushOutStream();
	}

	public void wildysigndisappear() {
		sendFrame126("@whi@", 6570);
		sendFrame126("", 6572);
		sendFrame126("@whi@WinterLove v.3", 6664);
		sendFrame126("@whi@Winterlove v.3 made by Mrs Extro Limited", 6660);
		setInterfaceWalkable(6673);
		// sendFrame126("", 13589);
		// sendFrame126("", 13590);
		// sendFrame126("", 13591);
		// sendFrame126("", 13592);
		// sendFrame126("", 13593);
		// sendFrame126("", 13594);
		// sendFrame126("", 13595);
		// sendFrame126("", 13596);
		// sendFrame126("", 13597);
		// sendFrame126("", 13598);
		// sendFrame126("", 13599);
		// sendFrame126("", 13600);
		// setInterfaceWalkable(13588);
		// setInterfaceWalkable(4535);
		// sendQuest("ASP Server By Keiren samuel", 4536);
	}

	public void WILDINT() {
		if (Wild()) {
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(197);
			sendQuest("@red@Wild", 199);
			leftwild = 1;
		} else {
			wildysigndisappear();
			leftwild = 0;
		}
	}

	public void process() { // is being called regularily every 500ms
		try {

			// -------------------tj007razor: Woodcutting processes------------------
			for (int i = 0; i <= 5; i++) {
				if (treeTimer[i] > 0) {
					doChop(i);
				}
			}
			// -------------------------End of woodcutting---------------------------

//-------------------tj007razor: Mining processes------------------
			if (stunTimer > 0) {
				stunTimer--;
			} else {
				stunTimer = 0;
			}
			for (int i = 0; i < 7; i++) {
				if (rockTimer[i] > 0) {
					doMine(i);
				}
			}
			stopPlayerEmote();
			teleportingProcess();
//FIRE MAKING TIMERS
			if (firetimer > 0) {
				firetimer -= 1;
			}

			if (firetimers > 0) {
				firetimers -= 1;
			}
			if (firetimers1 > 0) {
				firetimers1 -= 1;
			}
			if (firetimers3 > 0) {
				firetimers3 -= 1;
			}
			if (firetimers2 > 0) {
				firetimers2 -= 1;
			}
			if (firetimers4 > 0) {
				firetimers4 -= 1;
			}
			if (firetimers5 > 0) {
				firetimers5 -= 1;
			}
			if (firetimers6 > 0) {
				firetimers6 -= 1;
			}
			if (firetimers7 > 0) {
				firetimers7 -= 1;
			}
			if (firetimers8 > 0) {
				firetimers8 -= 1;
			}
			if (firetimers9 > 0) {
				firetimers9 -= 1;
			}

			if (firetimers10 > 0) {
				firetimers10 -= 1;
			}
			if (firetimers11 > 0) {
				firetimers11 -= 1;
			}
			if (firetimers13 > 0) {
				firetimers13 -= 1;
			}
			if (firetimers12 > 0) {
				firetimers12 -= 1;
			}
			if (firetimers14 > 0) {
				firetimers14 -= 1;
			}
			if (firetimers15 > 0) {
				firetimers15 -= 1;
			}
			if (firetimers16 > 0) {
				firetimers16 -= 1;
			}
			if (firetimers17 > 0) {
				firetimers17 -= 1;
			}
			if (firetimers18 > 0) {
				firetimers18 -= 1;
			}
			if (firetimers19 > 0) {
				firetimers19 -= 1;
			}

			if (firetimers == 1) {

				ItemHandler.addItem(592, previousX, previousY, 1, playerId, false);
				ReplaceObject2(previousX, previousY, 6951, 0, 10);
				previousX = 0;
				previousY = 0;

			}

			if (firetimers1 == 1) {
				ItemHandler.addItem(592, previousX1, previousY1, 1, playerId, false);
				ReplaceObject2(previousX1, previousY1, 6951, 0, 10);
				previousX1 = 0;
				previousY1 = 0;
			}

			if (firetimers2 == 1) {
				ItemHandler.addItem(592, previousX2, previousY1, 1, playerId, false);
				ReplaceObject2(previousX2, previousY2, 6951, 0, 10);
				previousX2 = 0;
				previousY2 = 0;
			}

			if (firetimers3 == 1) {
				ItemHandler.addItem(592, previousX3, previousY3, 1, playerId, false);
				ReplaceObject2(previousX3, previousY3, 6951, 0, 10);
				previousX3 = 0;
				previousY3 = 0;
			}
			if (firetimers4 == 1) {
				ItemHandler.addItem(592, previousX4, previousY4, 1, playerId, false);
				ReplaceObject2(previousX4, previousY4, 6951, 0, 10);
				previousX4 = 0;
				previousY4 = 0;
			}
			if (firetimers5 == 1) {
				ItemHandler.addItem(592, previousX5, previousY5, 1, playerId, false);
				ReplaceObject2(previousX5, previousY5, 6951, 0, 10);
				previousX5 = 0;
				previousY5 = 0;
			}
			if (firetimers9 == 1) {
				ItemHandler.addItem(592, previousX9, previousY9, 1, playerId, false);
				ReplaceObject2(previousX9, previousY9, 6951, 0, 10);
				previousX9 = 0;
				previousY9 = 0;
			}
			if (firetimers8 == 1) {
				ItemHandler.addItem(592, previousX8, previousY8, 1, playerId, false);
				ReplaceObject2(previousX8, previousY8, 6951, 0, 10);
				previousX8 = 0;
				previousY8 = 0;
			}
			if (firetimers7 == 1) {
				ItemHandler.addItem(592, previousX7, previousY7, 1, playerId, false);
				ReplaceObject2(previousX7, previousY7, 6951, 0, 10);
				previousX7 = 0;
				previousY7 = 0;
			}
			if (firetimers6 == 1) {
				ItemHandler.addItem(592, previousX6, previousY6, 1, playerId, false);
				ReplaceObject2(previousX6, previousY6, 6951, 0, 10);
				previousX6 = 0;
				previousY6 = 0;
			}

			if (firetimers10 == 1) {

				ItemHandler.addItem(592, previousX10, previousY10, 1, playerId, false);
				ReplaceObject2(previousX10, previousY10, 6951, 0, 10);
				previousX10 = 0;
				previousY10 = 0;

			}

			if (firetimers11 == 1) {
				ItemHandler.addItem(592, previousX11, previousY11, 1, playerId, false);
				ReplaceObject2(previousX11, previousY11, 6951, 0, 10);
				previousX11 = 0;
				previousY11 = 0;
			}

			if (firetimers12 == 1) {
				ItemHandler.addItem(592, previousX12, previousY12, 1, playerId, false);
				ReplaceObject2(previousX12, previousY12, 6951, 0, 10);
				previousX12 = 0;
				previousY12 = 0;
			}

			if (firetimers13 == 1) {
				ItemHandler.addItem(592, previousX13, previousY13, 1, playerId, false);
				ReplaceObject2(previousX13, previousY13, 6951, 0, 10);
				previousX13 = 0;
				previousY13 = 0;
			}
			if (firetimers14 == 1) {
				ItemHandler.addItem(592, previousX14, previousY14, 1, playerId, false);
				ReplaceObject2(previousX14, previousY14, 6951, 0, 10);
				previousX14 = 0;
				previousY14 = 0;
			}
			if (firetimers15 == 1) {
				ItemHandler.addItem(592, previousX15, previousY15, 1, playerId, false);
				ReplaceObject2(previousX15, previousY15, 6951, 0, 10);
				previousX15 = 0;
				previousY15 = 0;
			}
			if (firetimers19 == 1) {
				ItemHandler.addItem(592, previousX19, previousY19, 1, playerId, false);
				ReplaceObject2(previousX19, previousY19, 6951, 0, 10);
				previousX19 = 0;
				previousY19 = 0;
			}
			if (firetimers18 == 1) {
				ItemHandler.addItem(592, previousX18, previousY18, 1, playerId, false);
				ReplaceObject2(previousX18, previousY18, 6951, 0, 10);
				previousX18 = 0;
				previousY18 = 0;
			}
			if (firetimers17 == 1) {
				ItemHandler.addItem(592, previousX17, previousY17, 1, playerId, false);
				ReplaceObject2(previousX17, previousY17, 6951, 0, 10);
				previousX17 = 0;
				previousY17 = 0;
			}
			if (firetimers16 == 1) {
				ItemHandler.addItem(592, previousX16, previousY16, 1, playerId, false);
				ReplaceObject2(previousX16, previousY16, 6951, 0, 10);
				previousX16 = 0;
				previousY16 = 0;
			}

			if (firetimer == 1) {
				if (logID == 1) {
					addSkillXP((40 * xpgained), 11);
					removeGroundItem(absX, absY, 1511);
					logID = 0;
				}
				if (logID == 2) {
					addSkillXP((60 * xpgained), 11);
					removeGroundItem(absX, absY, 1521);
					logID = 0;
				}
				if (logID == 3) {
					addSkillXP((90 * xpgained), 11);
					removeGroundItem(absX, absY, 1519);
					logID = 0;
				}
				if (logID == 4) {
					addSkillXP((135 * xpgained), 11);
					removeGroundItem(absX, absY, 1517);
					logID = 0;
				}
				if (logID == 5) {
					addSkillXP((202 * xpgained), 11);
					removeGroundItem(absX, absY, 1515);
					logID = 0;
				}
				if (logID == 6) {
					addSkillXP((303 * xpgained), 11);
					removeGroundItem(absX, absY, 1513);
					logID = 0;
				}
				WalkTo(-1, 0);
				sendMessage("The fire catches, and the logs begin to burn.");
				Object(absX, absY, 2732, 0, 10);
				firetimer = 0;
				updateRequired = true;
				appearanceUpdateRequired = true;

				if (previousX == 0 && previousY == 0) {
					previousX = absX;
					previousY = absY;
					firetimers = 60; // DO NOT CHANGE THIS
					viewTo(previousX, previousY);
				}

				else {
					if (previousX1 == 0 && previousY1 == 0) {
						previousX1 = absX;
						previousY1 = absY;
						firetimers1 = 60;
						viewTo(previousX1, previousY1);
					}

					else {
						if (previousX2 == 0 && previousY2 == 0) {
							previousX2 = absX;
							previousY2 = absY;
							firetimers2 = 60;
							viewTo(previousX2, previousY2);
						} else {
							if (previousX3 == 0 && previousY3 == 0) {
								previousX3 = absX;
								previousY3 = absY;
								firetimers3 = 60;
								viewTo(previousX3, previousY3);
							} else {
								if (previousX4 == 0 && previousY4 == 0) {
									previousX4 = absX;
									previousY4 = absY;
									firetimers4 = 60;
									viewTo(previousX4, previousY4);
								} else {
									if (previousX5 == 0 && previousY5 == 0) {
										previousX5 = absX;
										previousY5 = absY;
										firetimers5 = 60;
										viewTo(previousX5, previousY5);
									} else {
										if (previousX6 == 0 && previousY6 == 0) {
											previousX6 = absX;
											previousY6 = absY;
											firetimers6 = 60;
											viewTo(previousX6, previousY6);
										} else {
											if (previousX7 == 0 && previousY7 == 0) {
												previousX7 = absX;
												previousY7 = absY;
												firetimers7 = 60;
												viewTo(previousX7, previousY7);
											} else {
												if (previousX8 == 0 && previousY8 == 0) {
													previousX8 = absX;
													previousY8 = absY;
													firetimers8 = 60;
													viewTo(previousX8, previousY8);
												} else {
													if (previousX9 == 0 && previousY9 == 0) {
														previousX9 = absX;
														previousY9 = absY;
														firetimers9 = 60;
														viewTo(previousX9, previousY9);
													} else {
														if (previousX10 == 0 && previousY10 == 0) {
															previousX10 = absX;
															previousY10 = absY;
															firetimers10 = 60;
															viewTo(previousX10, previousY10);
														} else {
															if (previousX11 == 0 && previousY11 == 0) {
																previousX11 = absX;
																previousY11 = absY;
																firetimers11 = 60;
																viewTo(previousX11, previousY11);
															} else {
																if (previousX12 == 0 && previousY12 == 0) {
																	previousX12 = absX;
																	previousY12 = absY;
																	firetimers12 = 60;
																	viewTo(previousX12, previousY12);
																} else {
																	if (previousX13 == 0 && previousY13 == 0) {
																		previousX13 = absX;
																		previousY13 = absY;
																		firetimers13 = 60;
																		viewTo(previousX13, previousY13);
																	} else {
																		if (previousX14 == 0 && previousY14 == 0) {
																			previousX14 = absX;
																			previousY14 = absY;
																			firetimers14 = 60;
																			viewTo(previousX14, previousY14);
																		} else {
																			if (previousX15 == 0 && previousY15 == 0) {
																				previousX15 = absX;
																				previousY15 = absY;
																				firetimers15 = 60;
																				viewTo(previousX15, previousY15);
																			} else {
																				if (previousX16 == 0
																						&& previousY16 == 0) {
																					previousX16 = absX;
																					previousY16 = absY;
																					firetimers16 = 60;
																					viewTo(previousX16, previousY16);
																				} else {
																					if (previousX17 == 0
																							&& previousY17 == 0) {
																						previousX17 = absX;
																						previousY17 = absY;
																						firetimers17 = 60;
																						viewTo(previousX17,
																								previousY17);
																					} else {
																						if (previousX18 == 0
																								&& previousY18 == 0) {
																							previousX18 = absX;
																							previousY18 = absY;
																							firetimers18 = 60;
																							viewTo(previousX18,
																									previousY18);
																						} else {
																							if (previousX19 == 0
																									&& previousY19 == 0) {
																								previousX19 = absX;
																								previousY19 = absY;
																								firetimers19 = 60;
																								viewTo(previousX19,
																										previousY19);
																							}

																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

			}

//END OF FIRE MAKING

			if (server.cooking.CookTime == 1 && server.cooking.IsCooking == true) {
				server.cooking.Repeat(playerId);
			}
			for (int ii = 0; ii < 28; ii++) {
				if (server.cooking.Fermenting[ii] == 1) {
					server.cooking.Fermenting[ii] = 0;
					server.cooking.DoneFermenting = true;
				}
			}
			if (server.cooking.DoneFermenting == true) {
				deleteItem(1995, getItemSlot(1995), 1);
				server.cooking.DoneFermenting = false;
				if (misc.random(3) == 1) {
					server.cooking.BadWine = true;
				} else {
					server.cooking.BadWine = false;
				}
				if (!server.cooking.BadWine) {
					addItem(1993, 1);
					addSkillXP(200, 7);
				} else {
					addItem(1991, 1);
				}
			}

			if (server.fishing.Fishtime == 1) {
				server.fishing.stillfish(playerId);
			}

			if (server.fletching.FletchTime == 1) {
				server.fletching.stillfletch(playerId);
			}

			server.prayerHandler.drainPrayer(playerId);

			if (arenaSpellTimer > 0) {
				arenaSpellTimer--;
			}
			if (arenaSpellTimer == 0) {
				if (SaradominStrike) {
					SaradominStrike = false;
					sendMessage("Your Saradomin Strike's power has returned to normal.");
				}
				if (GuthixClaws) {
					GuthixClaws = false;
					sendMessage("Your Claws of Guthix's power has returned to normal.");
				}
				if (ZamorakFlames) {
					ZamorakFlames = false;
					sendMessage("Your Flames of Zamorak's power has returned to normal.");
				}
			}
			if (spellHitTimer > 0) {
				spellHitTimer--;
			}
			if (spellHitTimer == 0) {
				if (castSpell) {
					castSpell = false;
					appendHitToNpc(MagicHandler.npcIndex, MagicHandler.hitDiff2, isStillSpell);
				}
			}

			WILDINT();
			scanPickup();

			if (playerLevel[2] > getLevelForXP(playerXP[2]) && strPotTimer == 0) {
				playerLevel[2] -= 1;
				strPotTimer = 20;
			}
			if (strPotTimer > 0)
				strPotTimer -= 1;
			if (playerLevel[0] > getLevelForXP(playerXP[0]) && AttPotTimer == 0) {
				playerLevel[0] -= 1;
				AttPotTimer = 20;
			}
			if (AttPotTimer > 0)
				AttPotTimer -= 1;
			if (playerLevel[1] > getLevelForXP(playerXP[1]) && defPotTimer == 0) {
				playerLevel[1] -= 1;
				defPotTimer = 20;
			}
			if (defPotTimer > 0)
				defPotTimer -= 1;

			if (absX >= 1974 && absX <= 1984 && absY >= 4621 && absY <= 4631) {
				if (playerRights == 0) {
					tele(1985, 4627);
					sendMessage("You can't go there, it's for staff!");
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
				}
			}
			if ((IsAttackingNPC) && DDS2Damg == true && DDStimer == 0) {
				SpecDamgNPC(25);
				DDS2Damg = false;
			}
			if (IsAttacking == true && DDS2Damg == true && DDStimer == 0) {
				SpecDamg(25);
				DDS2Damg = false;
			}
			if (DDStimer > 0)
				DDStimer -= 1;
			if (SpecialDelay <= 9)
				SpecDelay++;
			if (SpecDelay == 50) {
				SpecialDelay++;
				SpecDelay = 0;
			}
			if (specialAmount <= 99 && specialDelay <= 0) {
				specialAmount += 1;
				specialDelay = 4;
				SpECS();
			}
			specialDelay -= 1;
			l33thax(12323);
			l33thax(7574);
			l33thax(7599);
			l33thax(7549);
			l33thax(8493);
			l33thax(7499);

			if (healTimer > 0) {
				healTimer -= 1;
			}
			if (LogoutDelay > 0)
				LogoutDelay -= 1;
			if (EntangleDelay > 0)
				EntangleDelay -= 1;
			if (stunDelay > 0)
				stunDelay -= 1;
			if (PkingDelay > 0)
				PkingDelay -= 1;
			if (LoopAttDelay > 0)
				LoopAttDelay -= 1;
			if (PoisonDelay > 0)
				PoisonDelay -= 1;
			if (resetanim > 0)
				resetanim -= 1;
			if (newAnimDelay > 0)
				newAnimDelay -= 1;
			if (actionTimer > 0)
				actionTimer -= 1;

			if (newAnimRequired && newAnimDelay < 1) {
				outStream.createFrame(1); // Xerozcheez: Resets animation so we can do another one yayyyy!
				startAnimation(newAnim);
				newAnimRequired = false;
			}

			if (PoisonClear >= 15) {
				PoisonDelay = 9999999;
				sendMessage("The poison wears off...");
			}
			if (item2handed(playerEquipment[playerWeapon]) == true) {
				playerSE = 0x811;
				playerSEW = 0x67F;
				playerSER = 0x680;
			}
			if (playerEquipment[playerWeapon] == 4747) { // Torag Hammers
				playerSEA = 0x814;
			}
			if (playerEquipment[playerWeapon] == 4151) { // Whip
				playerSER = 1661;
			}
			pEmote = playerSE;
			updateRequired = true;
			appearanceUpdateRequired = true;

			int oldtotal = totalz;
			totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2])
					+ getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5])
					+ getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8])
					+ getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0])
					+ getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13])
					+ getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6])
					+ getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19])
					+ getLevelForXP(playerXP[20]));
			if (oldtotal != totalz)

				if (sbloop == true) {
					if (sbtimer <= 1 && sbscan == false) {
						setSidebarInterface(7, sb);
						sb += 1;
						sbtimer = 6;
						sendMessage("Current interface: " + sb);
					}
					if (sbtimer <= 1 && sbscan == true) {
						setSidebarInterface(7, sb);
						sb += 1;
						sbtimer = 2;
						sendMessage("Current interface: " + sb);
					}
					sbtimer -= 1;
				}

			sendQuest("Your position:", 183);
			sendQuest("X: " + absX + " Y: " + absY, 184);
			sendFrame126("Unlimited", 149);

			smitimer -= 1;
			if (smitimer <= 1) {
				savechar();
				println(playerName + ": ID " + playerId + ": Auto saved game.");
				savemoreinfo();
				println(playerName + ": ID " + playerId + ": Auto saving moreinfo file...");
				if (savemoreinfo()) {
					println(playerName + ": ID " + playerId + ": Successfuly auto saved moreinfo file.");
					attempts = 0;
					smitimer = 100;
				} else if (!savemoreinfo() && attempts <= 5) {
					println("Error saving moreinfo file! Retrying in 5 seconds");
					println("Attempts: " + attempts);
					attempts += 1;
					smitimer = 5;
				} else if (!savemoreinfo() && attempts >= 5) {
					println("Attempts: " + attempts + " - Giving up saving moreinfo file!");
					attempts += 1;
					smitimer = 99999999;
				}
			}

			if (globalMessage.length() > 0) {
				sendMessage(globalMessage);
				globalMessage = "";
			}
			playerGameCount++;
			if (playerGameCount == 120000) { // evry minute
				playerGameTime++;
				playerGameCount = 0;
			}
			// Shop
			if (UpdateShop == true) {
				resetItems(3823);
				resetShop(MyShopID);
			}
			// Trade Check
			if (tradeRequest > 0 && PlayerHandler.players[tradeRequest] != null) {
				sendMessage(PlayerHandler.players[tradeRequest].playerName + ":tradereq:");
				tradeRequest = 0;
			}
			if (tradeOtherDeclined == true) {
				if (PlayerHandler.players[tradeWith] != null) {
					sendMessage(PlayerHandler.players[tradeWith].playerName + " declined the trade.");
				} else {
					sendMessage("Other player declined the trade.");
				}
				RemoveAllWindows();
				DeclineTrade();
				tradeOtherDeclined = false;
			}
			if (tradeWaitingTime > 0) {
				tradeWaitingTime--;
				if (tradeWaitingTime <= 0) {
					sendMessage("Trade request suspended.");
					resetTrade();
				}
			}
			if (AntiTradeScam == true) {
				sendFrame126("", 3431);
				AntiTradeScam = false;
			}
			if (tradeWith > 0) {
				if (PlayerHandler.players[tradeWith] != null) {
					if (tradeStatus == 5) {
						if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
							PlayerHandler.players[tradeWith].tradeStatus = 5;
						}
						resetTrade();
					} else {
						int OtherStatus = PlayerHandler.players[tradeWith].tradeStatus;
						if (OtherStatus == 1) {
							PlayerHandler.players[tradeWith].tradeStatus = 2;
							tradeStatus = 2;
							AcceptTrade();
							PlayerHandler.players[tradeWith].tradeWaitingTime = 0;
							tradeWaitingTime = 0;
						} else if (OtherStatus == 3) {
							if (tradeStatus == 2) {
								sendFrame126("Other player has accepted.", 3431);
							} else if (tradeStatus == 3) {
								TradeGoConfirm();
							}
						} else if (OtherStatus == 4) {
							if (tradeStatus == 3) {
								sendFrame126("Other player has accepted.", 3535);
							} else if (tradeStatus == 4) {
								ConfirmTrade();
								if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
									PlayerHandler.players[tradeWith].tradeStatus = 5;
								}
							}
						}
						if (tradeUpdateOther == true) {
							resetOTItems(3416);
							tradeUpdateOther = false;
						}
					}
				} else {
					resetTrade();
				}
			}
			if (WanneTrade == 1) {
				try {
					if (WanneTradeWith > PlayerHandler.maxPlayers) {
						resetTrade();
					} else if (PlayerHandler.players[WanneTradeWith] != null) {
						if (GoodDistance2(absX, absY, PlayerHandler.players[WanneTradeWith].absX,
								PlayerHandler.players[WanneTradeWith].absY, 1) == true) {
							int tt1 = PlayerHandler.players[WanneTradeWith].tradeStatus;
							int tt2 = tradeStatus;
							if (tt1 <= 0 && tt2 <= 0 && PlayerHandler.players[WanneTradeWith].tradeWaitingTime == 0) {
								tradeWith = WanneTradeWith;
								tradeWaitingTime = 40;
								PlayerHandler.players[tradeWith].tradeRequest = playerId;
								sendMessage("Sending trade request...");
							} else if (tt1 <= 0 && tt2 <= 0
									&& PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0) {
								tradeWith = WanneTradeWith;
								tradeStatus = 1;
								AcceptTrade();
							}
							WanneTrade = 0;
							WanneTradeWith = 0;
						}
					} else {
						resetTrade();
					}
				} catch (Exception e) {
				}
			} else if (WanneTrade == 2) {
				try {
					if (WanneTradeWith > PlayerHandler.maxPlayers) {
						resetTrade();
					} else if (PlayerHandler.players[WanneTradeWith] != null) {
						if (GoodDistance2(absX, absY, PlayerHandler.players[WanneTradeWith].absX,
								PlayerHandler.players[WanneTradeWith].absY, 1) == true) {
							if (PlayerHandler.players[WanneTradeWith].tradeWith == playerId
									&& PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0) {
								tradeWith = WanneTradeWith;
								tradeStatus = 1;
								AcceptTrade();
							} else {
								tradeWith = WanneTradeWith;
								tradeWaitingTime = 40;
								PlayerHandler.players[tradeWith].tradeRequest = playerId;
								sendMessage("Sending trade request...");
							}
							WanneTrade = 0;
							WanneTradeWith = 0;
						}
					} else {
						resetTrade();
					}
				} catch (Exception e) {
					if (Crash == 0) {
						sendMessage("Reporting you for trying to crash " + ServerCP.Servername);
						Crash = 1;
						logCrashing();
					}
				}
			}

			// objects
			if (doors > -1) {
				if (GoodDistance2(skillX, skillY, absX, absY, 1) == true) {
					ChangeDoor(doors);
					doors = -1;
				}
			}
			// check banking
			if (WanneBank > 0) {
				if (GoodDistance(skillX, skillY, absX, absY, WanneBank) == true) {
					openUpBank();
					WanneBank = 0;
				}
			}
			// check shopping
			if (WanneShop > 0) {
				if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
					openUpShop(WanneShop);
					WanneShop = 0;
				}
			}
			// Pick Up Item Check
			if (WannePickUp == true && IsUsingSkill == false) {
				if (pickUpItem(PickUpID, PickUpAmount) == true) {
					PickUpID = 0;
					PickUpAmount = 0;
					PickUpDelete = 0;
					WannePickUp = false;
				}
			}
			// Attacking in wilderness
			if (IsAttacking == true && IsDead == false && PkingDelay <= 0) {
				if (PlayerHandler.players[AttackingOn] != null) {
					if (PlayerHandler.players[AttackingOn].IsDead == false) {
						Attack();
					} else {
						ResetAttack();
					}
				} else {
					ResetAttack();
				}
			}
			// Attacking an NPC
			if (IsAttackingNPC == true && IsDead == false) {
				if (server.npcHandler.npcs[attacknpc] != null) {
					if (server.npcHandler.npcs[attacknpc].IsDead == false) {
						AttackNPC();
					} else {
						ResetAttackNPC();
					}
				} else {
					ResetAttackNPC();
				}
			}

			// If killed apply dead
			if (IsDead == true && NewHP <= 1) {
				ApplyDead();
			}
			// update correct hp in stat screen
			if (NewHP < 136) {
				playerLevel[playerHitpoints] = NewHP;
				setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
				NewHP = playerLevel[3];
			}
			// healing check
			if (healing[0] > 0) {
				healing();
			}
			// Npc Talking
			if (NpcWanneTalk == 2) { // Bank Booth
				if (GoodDistance2(absX, absY, skillX, skillY, 1) == true) {
					NpcDialogue = 1;
					NpcTalkTo = GetNPCID(skillX, (skillY - 1));
					NpcWanneTalk = 0;
				}
			} else if (NpcWanneTalk > 0) {
				if (GoodDistance2(absX, absY, skillX, skillY, 2) == true) {
					NpcDialogue = NpcWanneTalk;
					NpcTalkTo = GetNPCID(skillX, skillY);
					NpcWanneTalk = 0;
				}
			}
			// Walking to object check
			if (WalkingTo) {
				if (GoodDistance(absX, absY, destinationX, destinationY, destinationRange)) {
					DoAction();
					ResetWalkTo();
				}
			}
			if (NpcDialogue > 0 && NpcDialogueSend == false) {
				UpdateNPCChat();
			}

			if (isKicked) {
				disconnected = true;
				outStream.createFrame(109);
			}
			;

			if (AnnouncementTimer > 0)
				AnnouncementTimer -= 1;
			if (ServerCP.Announcement == true && AnnouncementTimer == 0) {
				AnnouncementTimer = 360;
				sendMessage("[" + ServerCP.Servername + "] : " + ServerCP.Latestupdate);
			}
		} catch (Exception e) {
		}
	}

	public boolean packetSending() { // Ringer24: this is used for sending packets
		try {
			return packetProcess();
		} catch (Exception e) {
		}
		return true;
	}

	private boolean packetProcess() {
		try {
			if (disconnected)
				return false;
			try {
				if (timeOutCounter++ > 20) {
					actionReset();
					saveStats();
					disconnected = true;
					return false;
				}

				if (in == null)
					return false;

				int avail = in.available();
				if (avail == 0)
					return false;
				if (packetType == -1) {
					packetType = in.read() & 0xff;
					if (inStreamDecryption != null)
						packetType = packetType - inStreamDecryption.getNextKey() & 0xff;
					packetSize = packetSizes[packetType];
					avail--;
				}
				if (packetSize == -1) {
					if (avail > 0) {
						packetSize = in.read() & 0xff;
						avail--;
					} else
						return false;
				}
				if (avail < packetSize)
					return false;
				fillInStream(packetSize);
				timeOutCounter = 0;
				if (packetType > 0) {
					parseIncomingPackets();
					packetType = 0;
				}
				packetType = -1;
			} catch (java.lang.Exception __ex) {
				__ex.printStackTrace();
				disconnected = true;
				println(ServerCP.Servername + " Server [fatal] - exception");
			}
			return true;
		} catch (Exception E) {
		}
		return true;
	}

	public void actionReset() {
		actionName = "";
	}

	public void parseIncomingPackets() {
		int i;
		int junk;
		int junk2;
		int junk3;
		try {
			switch (packetType) {
			case 0:
				break; // idle packet - keeps on reseting timeOutCounter

			case 202: // idle logout packet - ignore for now
				break;
			case 210: // loads new area

				break;
			case 40:
				if (NpcDialogue == 1 || NpcDialogue == 3 || NpcDialogue == 5 || NpcDialogue == 40 || NpcDialogue == 42
						|| NpcDialogue == 1001 || NpcDialogue == 1002 || NpcDialogue == 2259 || NpcDialogue == 2260
						|| NpcDialogue == 301 || NpcDialogue == 305 || NpcDialogue == 308 || NpcDialogue == 309
						|| NpcDialogue == 313 || NpcDialogue == 314 || NpcDialogue == 317 || NpcDialogue == 318
						|| NpcDialogue == 319 || NpcDialogue == 322 || NpcDialogue == 323 || NpcDialogue == 14600) {
					NpcDialogue += 1;
					NpcDialogueSend = false;
				} else if (NpcDialogue == 6 || NpcDialogue == 7 || NpcDialogue == 300 || NpcDialogue == 303
						|| NpcDialogue == 304 || NpcDialogue == 307 || NpcDialogue == 310 || NpcDialogue == 311
						|| NpcDialogue == 312 || NpcDialogue == 315 || NpcDialogue == 316 || NpcDialogue == 320
						|| NpcDialogue == 321 || NpcDialogue == 324 || NpcDialogue == 325 || NpcDialogue == 326
						|| NpcDialogue == 14602) {
					NpcDialogue = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
				println("Unhandled packet [" + packetType + ", InterFaceId: " + inStream.readUnsignedWordA() + ", size="
						+ packetSize + "]: ]" + misc.Hex(inStream.buffer, 1, packetSize) + "[");
				println("Action Button: " + misc.HexToInt(inStream.buffer, 0, packetSize));
				break;

			case 75: // Alternative Item Option 1

				int itemid = inStream.readSignedWordA();
				if (playerName.equalsIgnoreCase(ServerCP.Owner)) {
					println("Item id: " + itemid);
				}
				int item2ID = inStream.readSignedWordBigEndian();
				int item2ID3 = inStream.readSignedWordA();
				int item2ID4 = inStream.readUnsignedWord();
				println("Item2ID: " + item2ID);
				println("Item2ID3: " + item2ID3);
				println("Item2ID4: " + item2ID4);

				if (item2ID3 == 227) {
					deleteItem(227, getItemSlot(227), 1);
					sendMessage("You empty the vial to floor.");
					addItem(229, 1);
				}

//Runecrafting
				server.runecrafting.TalismanLocate(item2ID3, playerId);
				break;

			case 16: // Alternative Item Option 2
				int item_id = inStream.readSignedWordA();

				if (playerName.equalsIgnoreCase(ServerCP.Owner)) {
					println("Item id: " + item_id);
				}
				break;

			case 192:
				int actionButton2 = misc.HexToInt(inStream.buffer, 0, packetSize);
				int j6 = inStream.readUnsignedWordA();
				int atObjectID = inStream.readSignedWordBigEndian();
				int atObjectY = inStream.readUnsignedWordBigEndianA();
				int itemSlot = inStream.readUnsignedWordBigEndian();
				int atObjectX = inStream.readUnsignedWordBigEndianA();
				int useItemID = inStream.readUnsignedWord();

				server.cooking.StartCooking(useItemID, atObjectID, playerId);
				server.fletching.stringString(useItemID, atObjectID, playerId);
				server.runecrafting.TalismanTeleport(useItemID, atObjectID, playerId);
				server.runecrafting.CraftRunes(useItemID, atObjectID, playerId);
				server.StaffMaking.chargeOrb(useItemID, atObjectID, this);
				server.StaffMaking.makeOrb(useItemID, atObjectID, this);

				if (useItemID == 1925 && atObjectID == 2645) {
					startAnimation(895);
					sendMessage("You fill the bucket with sand.");
					deleteItem(1925, getItemSlot(1925), 1);
					addItem(1783, 1);
				}
				if (useItemID == 401 && atObjectID == 2728) {
					startAnimation(883);
					sendMessage("You burn the seaweed to soda ash.");
					deleteItem(401, getItemSlot(401), 1);
					addItem(1781, 1);
				}
				if ((useItemID == 1781 || useItemID == 1783) && IsItemInBag(1781) == true && IsItemInBag(1783) == true
						&& atObjectID == 2781) {
					startAnimation(899);
					addSkillXP(20, 12);
					sendMessage("You heat the sand and soda ash in the furnace to make glass.");
					deleteItem(1781, getItemSlot(1781), 1);
					deleteItem(1783, getItemSlot(1783), 1);
					addItem(1925, 1);
					addItem(1775, 1);
				}

				break;

			case 130: // Clicking some stuff in game
				int interfaceID = inStream.readUnsignedWordA();
				if (playerName.equalsIgnoreCase(ServerCP.Owner))
					println("Case 130: " + actionButtonId);
				if (tradeStatus >= 2) {
					PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
					DeclineTrade();
					sendMessage("You decline the trade.");
				}
				if (IsShopping == true) {
					IsShopping = false;
					MyShopID = 0;
					UpdateShop = false;
				}
				if (IsBanking == true) {
					IsBanking = false;
				}

				if (misc.HexToInt(inStream.buffer, 0, packetSize) != 63363
						&& misc.HexToInt(inStream.buffer, 0, packetSize) != 0
						&& playerName.equalsIgnoreCase(ServerCP.Owner)) {
					println("handled packet [" + packetType + ", InterFaceId: " + interfaceID + ", size=" + packetSize
							+ "]: ]" + misc.Hex(inStream.buffer, 1, packetSize) + "[");
					println("Action Button: " + misc.HexToInt(inStream.buffer, 0, packetSize));
				}
				break;

			case 155: // first Click npc

				int NPCSlot = (misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
				int NPCID = server.npcHandler.npcs[NPCSlot].npcType;
				faceNPC(NPCSlot);
				boolean PutNPCCoords = false;
//shops
				if (NPCID == 2238) { // Shop
					PutNPCCoords = true;
					WanneShop = 3; // Donie
				} else if (NPCID == 553) {
// Aubury rune shop
					PutNPCCoords = true;
					WanneShop = 1; // Aubury Magic Shop
				}
//end of shops
				server.firstClickNpc.FirstClickNpc(NPCID, playerId); // SedondClickNpc handler
				server.fishing.first(NPCID, playerId);
				if (PutNPCCoords == true) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;

				}

				break;

			case 17: // second Click npc
				NPCSlot = ((misc.HexToInt(inStream.buffer, 0, packetSize) / 1000) - 128);
				NPCID = server.npcHandler.npcs[NPCSlot].npcType;
				faceNPC(NPCSlot);
				if (NPCID == 1) {
					pickPocket(1, 995, 1000, 100, "Man", 2);
				}
				server.fishing.second(NPCID, playerId);
				server.secondClickNpc.SecondClickNpc(NPCID, playerId); // SedondClickNpc handler
				break;

			case 21: // Third Click npc
				NPCSlot = (misc.HexToInt(inStream.buffer, 0, packetSize));
				NPCID = server.npcHandler.npcs[NPCSlot].npcType;
				faceNPC(NPCSlot);
				PutNPCCoords = false;
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				break;

			case 72: // Click to attack
				if (attacknpc > 0) {
					sendMessage("You are already attacking an npc!");
				} else {
					attacknpc = inStream.readUnsignedWordA();

					boolean Cant = false;

					if (server.npcHandler.npcs[attacknpc].attacknpc > 0) {
						Cant = true;
						sendMessage("You can't attack a dueling npc!");
					}

					if (attacknpc >= 0 && attacknpc < server.npcHandler.maxNPCs && Cant == false
							&& server.npcHandler.npcs[attacknpc] != null) {
						if (server.npcHandler.npcs[attacknpc].followPlayer < 1
								|| server.npcHandler.npcs[attacknpc].followPlayer == playerId) {
							IsAttackingNPC = true;
							server.npcHandler.npcs[attacknpc].StartKilling = playerId;
							server.npcHandler.npcs[attacknpc].RandomWalk = false;
							server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
							if (server.npcHandler.npcs[attacknpc].absX != absX
									&& server.npcHandler.npcs[attacknpc].absY != absY)
								faceNPC(attacknpc);
						}
					} else {
						ResetAttackNPC();
					}
				}
				break;

			case 121:
				loadObjects();
				Deleteobjects();
				Deletewalls();
				hasntLoggedin = true;
				println(playerName + ": ID " + playerId + ": Loading finished.");
				break;

			case 122: // Call for burying bones
				int interfaace = inStream.readSignedWordBigEndianA();
				int ItemSlot = inStream.readUnsignedWordA();
				int ItemID = inStream.readUnsignedWordBigEndian();
				Herblore.IdentifyHerb(ItemID, playerId);
				if (playerItems[ItemSlot] == ItemID + 1) {
					CheckForSkillUse(ItemID, ItemSlot);
				}
				break;

			case 18: // another npc option, do ::npc 2579 and right click and click trade ;)
				int unknownz = inStream.readSignedWordBigEndian();
				println("Packet 18: " + unknownz);
				break;

			case 101: // Character Design Screen
				int gender = inStream.readSignedByte();
				int head = inStream.readSignedByte();
				int jaw = inStream.readSignedByte();
				int torso = inStream.readSignedByte();
				int arms = inStream.readSignedByte();
				int hands = inStream.readSignedByte();
				int legs = inStream.readSignedByte();
				int feet = inStream.readSignedByte();
				int hairC = inStream.readSignedByte();
				int torsoC = inStream.readSignedByte();
				int legsC = inStream.readSignedByte();
				int feetC = inStream.readSignedByte();
				int skinC = inStream.readSignedByte();

				playerLook[0] = gender;
				pHead = head;
				pBeard = jaw;
				pTorso = torso;
				pArms = arms;
				pHands = hands;
				pLegs = legs;
				pFeet = feet;
				playerLook[1] = hairC;
				playerLook[2] = torsoC;
				playerLook[3] = legsC;
				playerLook[4] = feetC;
				playerLook[5] = skinC;
				apset = true;
				appearanceUpdateRequired = true;
				break;
			case 234: // dunno wtf this is lol, something to do with items/objects
				int somex = inStream.readUnsignedWordBigEndianA();
				int objclick = inStream.readUnsignedWordA();
				int somey = inStream.readUnsignedWordBigEndianA();
				println("Case 234: SomeX = " + somex);
				println("Case 234: ObjClick = " + objclick);
				println("Case 234: SomeY = " + somey);
				break;
			case 181: // magic on items on floor by Xerozcheez
				int magicOnItemX = inStream.readSignedWordBigEndian();
				int magicOnItemID = inStream.readUnsignedWord();
				int magicOnItemY = inStream.readSignedWordBigEndian();
				int magicOnItemSpellID = inStream.readUnsignedWordA();
				println("Case 181: x = " + magicOnItemX + ", item = " + magicOnItemID + ", y = " + magicOnItemY
						+ ", spell = " + magicOnItemSpellID);
				if (magicOnItemSpellID == 1168) {
					if (ItemHandler.itemExists(magicOnItemID, magicOnItemX, magicOnItemY)) {
						int itemAmount = ItemHandler.itemAmount(magicOnItemID, magicOnItemX, magicOnItemY);
						addItem(magicOnItemID, itemAmount);
						ItemHandler.removeItem(magicOnItemID, magicOnItemX, magicOnItemY, itemAmount);
						removeGroundItem(magicOnItemX, magicOnItemY, magicOnItemID);
						resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
					}
				}
				break;

			case 253: // call for burning fires
				int burnitemx = inStream.readSignedWordBigEndian();
				int burnitemy = inStream.readUnsignedWordBigEndianA();
				int burnitemid = inStream.readSignedWordA();
				break;
			case 25: // item in inventory used with item on floor
				int unknown1 = inStream.readSignedWordBigEndian(); // interface id of item
				int unknown2 = inStream.readUnsignedWordA(); // item in bag id
				int floorID = inStream.readUnsignedByte();
				int floorY = inStream.readUnsignedWordA();
				int unknown3 = inStream.readUnsignedWordBigEndianA();
				int floorX = inStream.readUnsignedByte();
				println("Unknown1 = " + unknown1);
				println("Unknown2 = " + unknown2);
				println("FloorID = " + floorID);
				println("FloorY = " + floorY);
				println("Unknown3 = " + unknown3);
				println("FloorX = " + floorX);
				break;
			case 57: // Use item on npc
				int readone = inStream.readUnsignedWordA();
				int readtwo = inStream.readUnsignedWordA();
				int readthree = inStream.readSignedWordBigEndian();
				int readfour = inStream.readUnsignedWordA();
				println("1 = " + readone);
				println("2 = " + readtwo);
				println("3 = " + readthree);
				println("4 = " + readfour);
				break;
			case 53:
				int p4 = 0;
				int p6 = 0;
				int usedWithSlot = inStream.readUnsignedWord();
				int itemUsedSlot = inStream.readUnsignedWordA();
				int interface1284 = inStream.readUnsignedWord();
				int interfacek = inStream.readUnsignedWord();
				int useWith = playerItems[usedWithSlot] - 1;
				int itemUsed = playerItems[itemUsedSlot] - 1;
				if (playerName.equalsIgnoreCase(ServerCP.Owner)) {
					println("Item: " + useWith + " used with item: " + itemUsed);
					println("usedwithslot: " + usedWithSlot + " itemusedslot: " + itemUsedSlot + " usewith: " + useWith
							+ " p4: " + p4 + " itemused: " + itemUsed + " p6: " + p6);
				}
				if (itemUsed == 1733 && useWith == 1741 || itemUsed == 1733 && useWith == 1741) { // leather + Needle
					showInterface(2311);
				}
				if (itemUsed == 4674 && useWith == 7633) { // ancient staff
					addItem(4675, 1);
					deleteItem(4674, getItemSlot(4674), 1);
					deleteItem(7633, getItemSlot(7633), 1);
					sendMessage("You fused the cross and book together to form an ancient staff.");
				}
// gem crafting
				if ((itemUsed == 1623 && useWith == 1755) || (itemUsed == 1755 && useWith == 1623)) {
					if (playerLevel[12] >= 1) {
						deleteItem(1623, getItemSlot(1623), 1);
						addItem(1607, 1);
						setAnimation(888);
						addSkillXP(20, 12);
					} else {
						sendMessage("You need a Crafting level of 1 to cut Sapphires");
					}
				} else if ((itemUsed == 1621 && useWith == 1755) || (itemUsed == 1755 && useWith == 1621)) {
					if (playerLevel[12] >= 15) {
						deleteItem(1621, getItemSlot(1621), 1);
						addItem(1605, 1);
						setAnimation(889);
						addSkillXP(25, 12);
					} else {
						sendMessage("You need a Crafting level of 15 to cut Emeralds");
					}
				} else if ((itemUsed == 1619 && useWith == 1755) || (itemUsed == 1755 && useWith == 1619)) {
					if (playerLevel[12] >= 30) {
						deleteItem(1619, getItemSlot(1619), 1);
						addItem(1603, 1);
						setAnimation(887);
						addSkillXP(30, 12);
					} else {
						sendMessage("You need a Crafting level of 30 to cut Rubys");
					}
				} else if ((itemUsed == 1617 && useWith == 1755) || (itemUsed == 1755 && useWith == 1617)) {
					if (playerLevel[12] >= 40) {
						deleteItem(1617, getItemSlot(1617), 1);
						addItem(1601, 1);
						setAnimation(890);
						addSkillXP(40, 12);
					} else {
						sendMessage("You need a Crafting level of 40 to cut Diamonds");
					}
				} else if ((itemUsed == 1631 && useWith == 1755) || (itemUsed == 1755 && useWith == 1631)) {
					if (playerLevel[12] >= 50) {
						deleteItem(1631, getItemSlot(1631), 1);
						addItem(1615, 1);
						setAnimation(885);
						addSkillXP(50, 12);
					} else {
						sendMessage("You need a Crafting level of 50 to cut DragonStones");
					}
				} else if ((itemUsed == 6571 && useWith == 1755) || (itemUsed == 1755 && useWith == 6571)) {
					if (playerLevel[12] >= 85) {
						deleteItem(6571, getItemSlot(6571), 1);
						addItem(6573, 1);
						addSkillXP(85, 12);
					} else {
						sendMessage("You need a Crafting level of 85 to cut Onyx's");
					}
				}
//FIRE MAKING Logs with TinderBox
				if ((itemUsed == 1511 && useWith == 590) || (itemUsed == 590 && useWith == 1511)) {

					RemoveAllWindows();
					createGroundItem(1511, absX, absY, 1);
					deleteItem(1511, getItemSlot(1511), 1);
					startAnimation(733);
					firetimer = 4;
					sendMessage("You attempt to light the logs.");
					logID = 0;
				}

				if ((itemUsed == 1521 && useWith == 590) || (itemUsed == 590 && useWith == 1521)) {
					if (playerLevel[11] >= 15) {
						RemoveAllWindows();
						createGroundItem(1521, absX, absY, 1);
						deleteItem(1521, getItemSlot(1521), 1);
						startAnimation(733);
						firetimer = 4;
						sendMessage("You attempt to light the logs.");
						logID = 2;
					} else {
						sendMessage("You need a Firemaking level of at least 15 to burn oak logs.");
					}
				}
				if ((itemUsed == 1519 && useWith == 590) || (itemUsed == 590 && useWith == 1519)) {
					if (playerLevel[11] >= 30) {
						RemoveAllWindows();
						createGroundItem(1519, absX, absY, 1);
						deleteItem(1519, getItemSlot(1519), 1);
						startAnimation(733);
						firetimer = 4;
						sendMessage("You attempt to light the logs.");
						logID = 3;
					} else {
						sendMessage("You need a Firemaking level of at least 30 to burn willow logs.");
					}
				}
				if ((itemUsed == 1517 && useWith == 590) || (itemUsed == 590 && useWith == 1517)) {
					if (playerLevel[11] >= 45) {
						RemoveAllWindows();
						createGroundItem(1517, absX, absY, 1);
						deleteItem(1517, getItemSlot(1517), 1);
						startAnimation(733);
						firetimer = 4;
						sendMessage("You attempt to light the logs.");
						logID = 4;
					} else {
						sendMessage("You need a Firemaking level of at least 45 to burn maple logs.");
					}
				}
				if ((itemUsed == 1515 && useWith == 590) || (itemUsed == 590 && useWith == 1515)) {
					if (playerLevel[11] >= 60) {
						RemoveAllWindows();
						createGroundItem(1515, absX, absY, 1);
						deleteItem(1515, getItemSlot(1515), 1);
						startAnimation(733);
						firetimer = 4;
						sendMessage("You attempt to light the logs.");
						logID = 5;
					} else {
						sendMessage("You need a Firemaking level of at least 60 to burn yew logs.");
					}
				}
				if ((itemUsed == 1513 && useWith == 590) || (itemUsed == 590 && useWith == 1513)) {
					if (playerLevel[11] >= 75) {
						RemoveAllWindows();
						createGroundItem(1513, absX, absY, 1);
						deleteItem(1513, getItemSlot(1513), 1);
						startAnimation(733);
						firetimer = 4;
						sendMessage("You attempt to light the logs.");
						logID = 6;
					} else {
						sendMessage("You need a Firemaking level of at least 75 to burn magic logs.");
					}
				}

				if (itemUsed == 1095 && useWith == 2370 || itemUsed == 2370 && useWith == 1095) { // Studded chaps
					if (playerLevel[12] >= 44) {
						addItem(1097, 1);
						addSkillXP(42, 12);
						deleteItem(1095, GetItemSlot(1095), 1);
						deleteItem(2370, GetItemSlot(2370), 1);
					} else {
						sendMessage("You need 44 crafting to make studded chaps!");
					}
				}

				if (itemUsed == 1131 && useWith == 2370 || itemUsed == 2370 && useWith == 1131) { // Studded body
					if (playerLevel[12] >= 41) {
						addItem(1133, 1);
						addSkillXP(40, 12);
						deleteItem(1131, GetItemSlot(1131), 1);
						deleteItem(2370, GetItemSlot(2370), 1);
					} else {
						sendMessage("You need 41 crafting to make studded body!");
					}
				}

				if (itemUsed == 1733 && useWith == 1743 || itemUsed == 1743 && useWith == 1733) { // Hardleather body
					if (playerLevel[12] >= 28) {
						if (playerHasItemAmount(1734, 1) == true) {
							addItem(1131, 1);
							addSkillXP(35, 12);
							deleteItem(1743, GetItemSlot(1743), 1);
							deleteItem(1734, GetItemSlot(1734), 1);
						} else {
							sendMessage("You need more thread to make hardleather body");
						}
					} else {
						sendMessage("You need 28 crafting to make hardleather body!");
					}
				}
				break;

			// walkTo commands
			case 248: // map walk (has additional 14 bytes added to the end with some junk data)
				packetSize -= 14; // ignore the junk
				closeInterface();
				resetAnimation();

			case 164:
			case 98: // walk on command

				for (int q = 0; q < 6; q++) {
					if (treeTimer[q] > 1) {
						treeTimer[q] = 1;
						sendMessage("You stop woodcutting.");
					}
				}

//Cooking
				if (server.cooking.IsCooking == true) {
					server.cooking.IsCooking = false;
					RemoveAllWindows();
					resetAnimation();
				}

				closeInterface();
				resetAnimation();
				if (faceNPC > 0) {
					ResetAttack();
					ResetAttackNPC();
				}

				if (stunDelay >= 1) {
					teleportToX = absX;
					teleportToY = absY;
					sendMessage("You are stunned!");
				}

				if (EntangleDelay >= 1) {
					teleportToX = absX;
					teleportToY = absY;
					sendMessage("A magical force stops you from moving!");
				}
				IsAttackingNPC = false;
				attacknpc = -1;

				if (IsDead == false) {
					newWalkCmdSteps = packetSize - 5;
					if (newWalkCmdSteps % 2 != 0)
						println("Warning: walkTo(" + packetType + ") command malformed: "
								+ misc.Hex(inStream.buffer, 0, packetSize));
					newWalkCmdSteps /= 2;
					if (++newWalkCmdSteps > walkingQueueSize) {
						println("Warning: walkTo(" + packetType + ") command contains too many steps ("
								+ newWalkCmdSteps + ").");
						newWalkCmdSteps = 0;
						break;
					}
					int firstStepX = inStream.readSignedWordBigEndianA();
					int tmpFSX = firstStepX;
					firstStepX -= mapRegionX * 8;
					for (i = 1; i < newWalkCmdSteps; i++) {
						newWalkCmdX[i] = inStream.readSignedByte();
						newWalkCmdY[i] = inStream.readSignedByte();
						tmpNWCX[i] = newWalkCmdX[i];
						tmpNWCY[i] = newWalkCmdY[i];
					}
					newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
					int firstStepY = inStream.readSignedWordBigEndian();
					int tmpFSY = firstStepY;
					firstStepY -= mapRegionY * 8;
					newWalkCmdIsRunning = inStream.readSignedByteC() == 1;
					for (i = 0; i < newWalkCmdSteps; i++) {
						newWalkCmdX[i] += firstStepX;
						newWalkCmdY[i] += firstStepY;
					}
					poimiY = firstStepY;
					poimiX = firstStepX;

					// pick up item check
					if (WannePickUp == true) {
						PickUpID = 0;
						PickUpAmount = 0;
						PickUpDelete = 0;
						WannePickUp = false;
					}
//Npc Talking
					if (NpcDialogue > 0) {
						NpcDialogue = 0;
						NpcTalkTo = 0;
						NpcDialogueSend = false;
						RemoveAllWindows();
					}

					// banking
					if (IsBanking == true) {
						RemoveAllWindows();
					}
					// shopping
					if (IsShopping == true) {
						IsShopping = false;
						MyShopID = 0;
						UpdateShop = false;
						RemoveAllWindows();
					}
					// trading
					if (tradeStatus >= 2) {
						PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
						DeclineTrade();
						sendMessage("You decline the trade.");
						RemoveAllWindows();
					}
				}
				break;

			case 4: // regular chat
				if (muted == 0) {
					chatTextEffects = inStream.readUnsignedByteS();
					chatTextColor = inStream.readUnsignedByteS();
					chatTextSize = (byte) (packetSize - 2);
					inStream.readBytes_reverseA(chatText, chatTextSize, 0);
					chatTextUpdateRequired = true;
				} else {
					chatTextUpdateRequired = false;
				}
				break;

			case 14: // Using Items On Players
				int k1 = inStream.readSignedWordA();
				int useOnPlayer = inStream.readSignedWord();
				int itemUseID = inStream.readSignedWord();
				int itemUseSlot = inStream.readSignedWordBigEndian();
				break;

			case 132:
				int objectX = inStream.readSignedWordBigEndianA();
				int objectID = inStream.readUnsignedWord();
				int objectY = inStream.readUnsignedWordA();
				int face = 0;
				int face2 = 0;
				int GateID = 1;

				server.runecrafting.AbyssRift(objectID, playerId);
				server.runecrafting.ClickToCraft(objectID, playerId);
				server.Doors.Doors1(objectID, objectX, objectY, face, face2, GateID, playerId);

				if (objectID == 1276 || objectID == 1277 || objectID == 1278 || objectID == 1279 || objectID == 1280
						|| objectID == 1282 || objectID == 1283 || objectID == 1284 || objectID == 1285
						|| objectID == 1286 || objectID == 1287 || objectID == 1288 || objectID == 1289
						|| objectID == 1290 || objectID == 1291) { // normal[0]
					treeX[0] = objectX;
					treeY[0] = objectY;
					startChop(0, 1, 1511, 50, objectID, 1344);
				}
				if (objectID == 1281) { // oak[1]
					treeX[1] = objectX;
					treeY[1] = objectY;
					startChop(1, 15, 1521, 80, objectID, 1342);
				}
				if (objectID == 1308) { // willow[2]
					treeX[2] = objectX;
					treeY[2] = objectY;
					startChop(2, 30, 1519, 115, objectID, 1349);
				}
				if (objectID == 1307) { // maple[3]
					treeX[3] = objectX;
					treeY[3] = objectY;
					startChop(3, 45, 1517, 155, objectID, 1343);
				}
				if (objectID == 1309) { // yew[4]
					treeX[4] = objectX;
					treeY[4] = objectY;
					startChop(4, 60, 1515, 200, objectID, 1355);
				}
				if (objectID == 1306) { // magic[5]
					treeX[5] = objectX;
					treeY[5] = objectY;
					startChop(5, 75, 1513, 250, objectID, 8410);
				}

				if (objectID == 10666) { // Xerozcheez: This object requires to be 3 sqs minium, so we change it ;)
					destinationRange = 4;
				} else if (objectID == 6672 || objectID == 6673) { // Xerozcheez: These objects requires to be 1 sqs
																	// max, so we change it ;)
					destinationRange = 1;
				} else {
					destinationRange = 2;
				}

				if (GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
					viewTo(objectX, objectY);
					objectClick(objectID, objectX, objectY, 0, 0, 1);
				} else {
					ActionType = 1;
					destinationX = objectX;
					destinationY = objectY;
					destinationID = objectID;
					WalkingTo = true;
				}
				break;

			case 252: // atObject2
				objectID = inStream.readUnsignedWordBigEndianA(); // 5292 bankwindow
				objectY = inStream.readSignedWordBigEndian();
				objectX = inStream.readUnsignedWordA();

				if (objectID == 2781) {
					sendFrame164(2400);
					NpcDialogueSend = true;
				}

				if (objectID == 1) { // Xerozcheez: This object requires to be 3 sqs minium, so we change it ;)
					destinationRange = 4;
				} else {
					destinationRange = 2;
				}

				if (GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
					viewTo(objectX, objectY);
					objectClick2(objectID, objectX, objectY);
				} else {
					ActionType = 2;
					destinationX = objectX;
					destinationY = objectY;
					destinationID = objectID;
					WalkingTo = true;
				}

				break;

			case 70: // atObject3
				objectX = inStream.readSignedWordBigEndian();
				objectY = inStream.readUnsignedWord();
				objectID = inStream.readUnsignedWordBigEndianA();

				if (objectID == 1) { // Xerozcheez: This object requires to be 3 sqs minium, so we change it ;)
					destinationRange = 4;
				} else {
					destinationRange = 2;
				}

				if (GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
					viewTo(objectX, objectY);
					objectClick3(objectID, objectX, objectY);
				} else {
					ActionType = 3;
					destinationX = objectX;
					destinationY = objectY;
					destinationID = objectID;
					WalkingTo = true;
				}

				break;

			case 95: // update chat
				Tradecompete = inStream.readUnsignedByte();
				Privatechat = inStream.readUnsignedByte();
				Publicchat = inStream.readUnsignedByte();
				for (int i1 = 1; i1 < handler.maxPlayers; i1++) {
					if (handler.players[i1] != null && handler.players[i1].isActive == true) {
						handler.players[i1].pmupdate(playerId, GetWorld(playerId));
					}
				}
				break;
			case 188: // add friend
				long friendtoadd = inStream.readQWord();
				boolean CanAdd = true;
				for (int i1 = 0; i1 < friends.length; i1++) {
					if (friends[i1] != 0 && friends[i1] == friendtoadd) {
						CanAdd = false;
						sendMessage(friendtoadd + " is already in your friendlist.");
					}
				}
				if (CanAdd == true) {
					for (int i1 = 0; i1 < friends.length; i1++) {
						if (friends[i1] == 0) {
							friends[i1] = friendtoadd;
							for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
								if (handler.players[i2] != null && handler.players[i2].isActive
										&& misc.playerNameToInt64(handler.players[i2].playerName) == friendtoadd) {
									if (playerRights >= 2 || handler.players[i2].Privatechat == 0
											|| (handler.players[i2].Privatechat == 1 && handler.players[i2]
													.isinpm(misc.playerNameToInt64(playerName)))) {
										loadpm(friendtoadd, GetWorld(i2));
										break;
									}
								}
							}
							break;
						}
					}
				}
				break;
			case 215: // remove friend
				long friendtorem = inStream.readQWord();
				for (int i1 = 0; i1 < friends.length; i1++) {
					if (friends[i1] == friendtorem) {
						friends[i1] = 0;
						break;
					}
				}
				break;
			case 133: // add ignore
				long igtoadd = inStream.readQWord();
				for (int i10 = 0; i10 < ignores.length; i10++) {
					if (ignores[i10] == 0) {
						ignores[i10] = igtoadd;
						break;
					}
				}
				break;
			case 74: // remove ignore
				long igtorem = inStream.readQWord();
				for (int i11 = 0; i11 < ignores.length; i11++) {
					if (ignores[i11] == igtorem) {
						ignores[i11] = 0;
						break;
					}
				}
				break;
			case 126: // pm message
				long friendtosend = inStream.readQWord();
				byte pmchatText[] = new byte[100];
				int pmchatTextSize = (byte) (packetSize - 8);
				inStream.readBytes(pmchatText, pmchatTextSize, 0);
				for (int i1 = 0; i1 < friends.length; i1++) {
					if (friends[i1] == friendtosend) {
						boolean pmsent = false;
						for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
							if (handler.players[i2] != null && handler.players[i2].isActive
									&& misc.playerNameToInt64(handler.players[i2].playerName) == friendtosend) {
								if (playerRights >= 2 || handler.players[i2].Privatechat == 0
										|| (handler.players[i2].Privatechat == 1
												&& handler.players[i2].isinpm(misc.playerNameToInt64(playerName)))) {
									handler.players[i2].sendpm(misc.playerNameToInt64(playerName), playerRights,
											pmchatText, pmchatTextSize);
									pmsent = true;
								}
								break;
							}
						}
						if (!pmsent) {
							sendMessage("Player currently not available");
							break;
						}
					}
				}
				break;

			case 236: // pickup item
				int itemY = inStream.readSignedWordBigEndian();
				int itemID = inStream.readUnsignedWord();
				int itemX = inStream.readSignedWordBigEndian();
				apickupid = itemID;
				apickupx = itemX;
				apickupy = itemY;
				break;

			case 73: // Attack (Wilderness)

				if (PkingDelay <= 1) {
					if (playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861
							|| playerEquipment[playerWeapon] == 4214) {
						setAnimation(426);
						teleportToX = absX;
						teleportToY = absY;
					}
					AttackingOn = inStream.readSignedWordBigEndian();
					client plz = (client) server.playerHandler.players[AttackingOn];
					if (Wild() && plz.Wild() && plz != null) {
						IsAttacking = true;
						inCombat();
					} else {
						sendMessage("That player is in a safe zone.");
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
					setAnimation(GetWepAnim());
					if (server.playerHandler.players[AttackingOn] != null) {
						if (server.playerHandler.players[AttackingOn].absX != absX
								&& server.playerHandler.players[AttackingOn].absY != absY)
							faceNPC = 32768 + AttackingOn;
						faceNPCupdate = true;
					}
				}
				break;

			case 128: // Trade Request
				WanneTradeWith = inStream.readUnsignedWord();
				WanneTrade = 1;
				break;

			case 139: // Trade answer
				WanneTradeWith = inStream.readSignedWordBigEndian();
				WanneTrade = 2;
				break;

			case 237: // Magic on Items
				int castOnSlot = inStream.readSignedWord();
				int castOnItem = inStream.readSignedWordA();
				int e3 = inStream.readSignedWord();
				int castSpell = inStream.readSignedWordA();
				if (playerName.equalsIgnoreCase(ServerCP.Owner)) {
					println("castOnSlot: " + castOnSlot + " castOnItem: " + castOnItem + " e3: " + e3 + " castSpell: "
							+ castSpell);
				}
				int alchvaluez = (int) Math.floor(GetItemShopValue(castOnItem, 0, castOnSlot));

//High alchemy here?
				if (castSpell == 1178) // H Alch
				{
					if (playerLevel[6] >= 55) {
						if ((playerHasItemAmount(561, 1) == false) || (playerHasItemAmount(554, 5) == false)) {
							sendMessage("You do not have enough runes to cast this spell.");
						} else if ((playerHasItemAmount(561, 1) == true) && (playerHasItemAmount(554, 5) == true)) {
							alchvaluez = (alchvaluez / 1); // alch value
							deleteItem(castOnItem, castOnSlot, 1);
							addItem(995, alchvaluez);
							addSkillXP(150, 6);
							startAnimation(713);
							gfx100(113);
							deleteItem(561, getItemSlot(561), 1);
							deleteItem(554, getItemSlot(554), 5);
							setSidebarMage();
						}
					} else if (playerLevel[6] <= 55) {
						sendMessage("Your magic level is not high enough for this spell.");
					}
				}

				if (castSpell == 1162) // L Alch
				{
					if (playerLevel[6] >= 21) {
						if ((playerHasItemAmount(561, 1) == false) || (playerHasItemAmount(554, 3) == false)) {
							sendMessage("You do not have enough runes to cast this spell.");
						} else if ((playerHasItemAmount(561, 1) == true) && (playerHasItemAmount(554, 3) == true)) {
							alchvaluez = (alchvaluez / 5);
							deleteItem(castOnItem, castOnSlot, 1);
							addItem(995, alchvaluez);
							addSkillXP(70, 6);
							startAnimation(712);
							gfx100(112);
							deleteItem(561, getItemSlot(561), 1);
							deleteItem(554, getItemSlot(554), 3);
							setSidebarMage();
						}
					} else if (playerLevel[6] <= 21) {
						sendMessage("Your magic level is not high enough for this spell.");
					}
				}

				break;

			case 249: // Magic on Players
				int playerIndexx = inStream.readSignedWordA();
				int pcombat = server.playerHandler.players[playerIndexx].combat;
				spellID = inStream.readSignedWordBigEndian();
				client pl2 = (client) server.playerHandler.players[playerIndexx];
				if (pl2 == null)
					return;
				if (server.playerHandler.players[playerIndexx] != null)
					pcombat = server.playerHandler.players[playerIndexx].combat;
				ResetAttackNPC();
				MageAttackIndex = playerIndexx + 1;

				if (Wild() && pl2.Wild() && pcombat != -1) {
					AttackMage(playerIndexx);
				} else {
					teleXY();
					sendMessage("This player is in a safe zone and cannot be attacked");
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 131: // Magic on NPCS
				int npcIndex = inStream.readSignedWordBigEndianA();
				int spell = inStream.readSignedWordA();
				System.out.println("SpellID - " + spell);
				int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
				int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
				int EnemyHP2 = server.npcHandler.npcs[npcIndex].HP;
				int casterX = absX;
				int casterY = absY;
				int offsetY = (casterX - EnemyX2) * -1;
				int offsetX = (casterY - EnemyY2) * -1;
				int X = absX;
				int Y = absY;

				if (EnemyHP2 == 0 || server.npcHandler.npcs[npcIndex].IsDead) {
					TurnPlayerTo(EnemyX2, EnemyY2);
					updateRequired = true;
					appearanceUpdateRequired = true;
					teleportToX = absX;
					teleportToY = absY;
					sendMessage("This npc is already dead.");
					return;
				}

				TurnPlayerTo(EnemyX2, EnemyY2);
				updateRequired = true;
				appearanceUpdateRequired = true;
				teleportToX = absX;
				teleportToY = absY;
				MagicHandler.npcX = EnemyX2;
				MagicHandler.npcY = EnemyY2;
				MagicHandler.npcHP = EnemyHP2;
				MagicHandler.npcIndex = npcIndex;

				MagicHandler.MagicSpell(spell, playerId, MultiTargetSpell(spell), StillSpell(spell), playerLevel[6]);

				break;

			case 3: // focus change
				int focus = inStream.readUnsignedByte();
				break;
			case 86: // camera angle
				int CameraY = inStream.readUnsignedWord();
				int CameraX = inStream.readUnsignedWordA();
				break;
			case 241: // mouse clicks
				break;

			case 924:
				outStream.createFrame(999999); // this crashes their client hahaha
				break;

			case 103: // Custom player command, the ::words
				String playerCommand = inStream.readString();
				println(playerName + ": ID " + playerId + ": playerCommand: " + playerCommand);
				customCommand(playerCommand);
				break;

			case 214: // change item places
				somejunk = inStream.readUnsignedWordA(); // junk
				int itemFrom = inStream.readUnsignedWordA();// slot1
				int itemTo = (inStream.readUnsignedWordA() - 128);// slot2
				moveItems(itemFrom, itemTo, somejunk);
				break;

			case 41: // wear item
				int wearID = inStream.readUnsignedWord();
				int wearSlot = inStream.readUnsignedWordA();
				interfaceID = inStream.readUnsignedWordA();
				wear(wearID, wearSlot);
				flushOutStream();
				break;

			case 145: // remove item (opposite for wearing) - bank 1 item - value of item
				interfaceID = inStream.readUnsignedWordA();
				int removeSlot = inStream.readUnsignedWordA();
				int removeID = inStream.readUnsignedWordA();

				if (interfaceID == 1688) {
					if (playerEquipment[removeSlot] == removeID) {
						remove(removeID, removeSlot);
					}
				} else if (interfaceID == 5064) { // remove from bag to bank
					bankItem(removeID, removeSlot, 1);
				} else if (interfaceID == 5382) { // remove from bank
					fromBank(removeID, removeSlot, 1);
				} else if (interfaceID == 3322) { // remove from bag to trade window
					if (removeID == 6556 || isUntradable(removeID)) {
						sendMessage("You cannot trade this item.");
					} else {
						tradeItem(removeID, removeSlot, 1);
					}
				} else if (interfaceID == 3415) { // remove from trade window
					fromTrade(removeID, removeSlot, 1);
				} else if (interfaceID == 3823) { // Show value to sell items
					if (Item.itemSellable[removeID] == false) {
						sendMessage("I cannot sell " + GetItemName(removeID) + ".");
					} else {
						boolean IsIn = false;
						if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
							for (int j = 0; j <= server.shopHandler.ShopItemsStandard[MyShopID]; j++) {
								if (removeID == (server.shopHandler.ShopItems[MyShopID][j] - 1)) {
									IsIn = true;
									break;
								}
							}
						} else {
							IsIn = true;
						}
						if (IsIn == false) {
							sendMessage("You cannot sell " + GetItemName(removeID) + " in this store.");
						} else {
							int ShopValue = (int) Math.floor(GetItemShopValue(removeID, 1, removeSlot));
							String ShopAdd = "";
							if (ShopValue <= 1) {
								ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0, removeSlot));
							}
							if (ShopValue >= 1000 && ShopValue < 1000000) {
								ShopAdd = " (" + (ShopValue / 1000) + "K)";
							} else if (ShopValue >= 1000000) {
								ShopAdd = " (" + (ShopValue / 1000000) + " million)";
							}
							sendMessage(
									GetItemName(removeID) + ": shop will buy for " + ShopValue + " Coins" + ShopAdd);
						}
					}
				} else if (interfaceID == 3900) { // Show value to buy items
					int ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0, removeSlot));
					String ShopAdd = "";
					if (ShopValue <= 1) {
						ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0, removeSlot));
					}
					if (ShopValue >= 1000 && ShopValue < 1000000) {
						ShopAdd = " (" + (ShopValue / 1000) + "K)";
					} else if (ShopValue >= 1000000) {
						ShopAdd = " (" + (ShopValue / 1000000) + " million)";
					}
					sendMessage(GetItemName(removeID) + ": currently costs " + ShopValue + " Coins" + ShopAdd);
				}
				break;

			case 117: // bank 5 items - sell 1 item
				interfaceID = inStream.readSignedWordBigEndianA();
				removeID = inStream.readSignedWordBigEndianA();
				removeSlot = inStream.readSignedWordBigEndian();

				if (interfaceID == 5064) { // remove from bag to bank
					bankItem(removeID, removeSlot, 5);
				} else if (interfaceID == 5382) { // remove from bank
					fromBank(removeID, removeSlot, 5);
				} else if (interfaceID == 3322) { // remove from bag to trade window
					if (isUntradable(removeID)) {
						sendMessage("You cannot trade this item");
					} else {
						tradeItem(removeID, removeSlot, 5);
					}
				} else if (interfaceID == 3415) { // remove from trade window
					fromTrade(removeID, removeSlot, 5);
				} else if (interfaceID == 3823) { // Show value to sell items
					sellItem(removeID, removeSlot, 1);
				} else if (interfaceID == 3900) { // Show value to buy items
					buyItem(removeID, removeSlot, 1);
				}
				break;

			case 43: // bank 10 items - sell 5 items
				interfaceID = inStream.readUnsignedWordBigEndian();
				removeID = inStream.readUnsignedWordA();
				removeSlot = inStream.readUnsignedWordA();

				if (interfaceID == 5064) { // remove from bag to bank
					bankItem(removeID, removeSlot, 10);
				} else if (interfaceID == 5382) { // remove from bank
					fromBank(removeID, removeSlot, 10);
				} else if (interfaceID == 3322) { // remove from bag to trade window
					if (isUntradable(removeID)) {
						sendMessage("You cannot trade this item");
					} else {
						tradeItem(removeID, removeSlot, 10);
					}
				} else if (interfaceID == 3415) { // remove from trade window
					fromTrade(removeID, removeSlot, 10);
				} else if (interfaceID == 3823) { // Show value to sell items
					sellItem(removeID, removeSlot, 5);
				} else if (interfaceID == 3900) { // Show value to buy items
					buyItem(removeID, removeSlot, 5);
				}
				break;

			case 129: // bank all items - sell 10 items
				removeSlot = inStream.readUnsignedWordA();
				interfaceID = inStream.readUnsignedWord();
				removeID = inStream.readUnsignedWordA();

				if (interfaceID == 5064) { // remove from bag to bank
					if (Item.itemStackable[removeID] == true) {
						bankItem(playerItems[removeSlot], removeSlot, playerItemsN[removeSlot]);
					} else {
						bankItem(playerItems[removeSlot], removeSlot, itemAmount(playerItems[removeSlot]));
					}
				} else if (interfaceID == 5382) { // remove from bank
					fromBank(bankItems[removeSlot], removeSlot, bankItemsN[removeSlot]);
				} else if (interfaceID == 3322) { // remove from bag to trade window
					if (isUntradable(removeID))
						sendMessage("You cannot trade this item");
					else
						tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				} else if (interfaceID == 3415) { // remove from trade window
					fromTrade(removeID, removeSlot, playerTItemsN[removeSlot]);
				} else if (interfaceID == 3823) { // Show value to sell items
					sellItem(removeID, removeSlot, 10);
				} else if (interfaceID == 3900) { // Show value to buy items
					buyItem(removeID, removeSlot, 10);
				}
				break;

			case 135: // bank X items
				outStream.createFrame(27);
				XremoveSlot = inStream.readSignedWordBigEndian();
				XinterfaceID = inStream.readUnsignedWordA();
				XremoveID = inStream.readSignedWordBigEndian();
				break;

			case 208: // Enter Amount Part 2
				int EnteredAmount = inStream.readDWord();
				if (XinterfaceID == 5064) { // remove from bag to bank
					bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 5382) { // remove from bank
					fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 3322) { // remove from bag to trade window
					tradeItem(XremoveID, XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 3415) { // remove from trade window
					fromTrade(XremoveID, XremoveSlot, EnteredAmount);
				}
				break;

			case 60: // Enter Name?
				String name = inStream.readString(); // Xerozcheez: I don't know if this is right method, because in the
														// client it sends using method404 which I have not seen before
				break;

			case 87: // drop item
				int droppedItem = inStream.readUnsignedWordA();
				somejunk = inStream.readUnsignedByte() + inStream.readUnsignedByte();
				int slot = inStream.readUnsignedWordA();
				if (DestroyItem.DestroyItem(droppedItem)) {
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(14171);
					outStream.writeByte(0);
					outStream.writeWord(droppedItem + 1);
					outStream.writeByte(255);
					outStream.writeDWord(1);
					outStream.endFrameVarSizeWord();
					sendFrame126("Are you sure you want to destroy this item?", 14174);
					sendFrame126("Yes.", 14175);
					sendFrame126("No.", 141756);
					sendFrame126("" + GetItemName(droppedItem), 14184);
					sendFrame126(DestroyItem.Line1(droppedItem), 14182);
					sendFrame126(DestroyItem.Line2(droppedItem), 14183);
					sendFrame164(14170);
					DestroyedItem = droppedItem;
				} else {
					if (wearing == false && playerItems[slot] == droppedItem + 1) {
						dropItem(droppedItem, slot);
					}
				}
				break;

			case 120: // sends sidebar id when clicked while it's flashing - found by xerozcheez
				int sidebarID = inStream.readUnsignedByte();
				println("Packet 120: Sidebar Id: " + sidebarID);
				break;

			case 185: // clicking most buttons
				if (teleporting) {
					teleportToX = absX;
					teleportToY = absY;
					break;
				}
				actionButtonId = misc.HexToInt(inStream.buffer, 0, packetSize);
				if (playerName.equalsIgnoreCase(ServerCP.Owner))
					println("Case 185: " + actionButtonId);

				server.smeltinghandler.smelting(playerId);
				server.prayerHandler.clickPrayer(playerId);
				server.craftingHandler.glassblowing(playerId);
				server.craftingHandler.leathercrafting(playerId);

				switch (actionButtonId) {
//teleports
				case 4140:
					teleport(3210, 3426, 0, 25, 35, 2, "fire", 1, "air", 3, "law", 1, "null", 0, "modern", false);
					break;// Varrock
				case 4143:
					teleport(3221, 3217, 0, 31, 41, 2, "earth", 1, "air", 3, "law", 1, "null", 0, "modern", false);
					break;// Lumbridge (unsure)
				case 4146:
					teleport(2964, 3380, 0, 37, 48, 2, "water", 1, "air", 3, "law", 1, "null", 0, "modern", false);
					break;// Falador
				case 4150:
					teleport(2756, 3479, 0, 45, 55, 2, "air", 1, "law", 1, "null", 0, "null", 0, "modern", true);
					break;// Camelot
				case 6004:
					teleport(2661, 3306, 0, 51, 61, 2, "water", 2, "law", 2, "null", 0, "null", 0, "modern", true);
					break;// Ardougne
				case 6005:
					teleport(2549, 3113, 0, 58, 68, 2, "earth", 2, "law", 2, "null", 0, "null", 0, "modern", true);
					break;// Watchtower (unsure)
				case 29031:
					teleport(3285, 3345, 0, 61, 68, 2, "fire", 2, "law", 2, "null", 0, "null", 0, "modern", true);
					break;// Trollhelm
				case 72038:
					teleport(2796, 2799, 1, 64, 74, 2, "fire", 2, "water", 2, "law", 2, "banana", 1, "modern", true);
					break;// Ape Atoll
				case 50235:
					teleport(2606, 3102, 0, 0, 0, 2, "null", 0, "null", 0, "null", 0, "null", 0, "modern", false);
					break;// Yanille
				case 50245:
					teleport(3322, 3337, 0, 60, 70, 0, "law", 2, "soul", 1, "null", 0, "null", 0, "ancient", true);
					break;// Senntisten
				case 50253:
					teleport(3491, 3471, 0, 66, 76, 0, "law", 2, "blood", 1, "null", 0, "null", 0, "ancient", true);
					break;// Kharyrll
				case 51005:
					teleport(3008, 3477, 0, 72, 82, 0, "law", 2, "water", 4, "null", 0, "null", 0, "ancient", true);
					break;// Lassar
				case 51013:
					teleport(2963, 3696, 0, 78, 88, 0, "law", 2, "fire", 3, "air", 2, "null", 0, "ancient", true);
					break;// Dareeyak (unsure) 450+ cache
				case 51023:
					teleport(3156, 3666, 0, 84, 94, 0, "law", 2, "soul", 2, "null", 0, "null", 0, "ancient", true);
					break;// Carrallangar (unsure)
				case 51031:
					teleport(3288, 3886, 0, 90, 100, 0, "law", 2, "blood", 2, "null", 0, "null", 0, "ancient", true);
					break;// Annakarl (unsure)
				case 51039:
					teleport(2976, 3874, 0, 96, 106, 0, "law", 2, "water", 8, "null", 0, "null", 0, "ancient", true);
					break;// Ghorrock (unsure) 377 cache
//end of teleports
				case 28164:
					sendQuest("@gre@Staff Making", 7332);
					server.StaffMaking.staffMaking(this);
					break;
				case 33213: // herblore guide
					clearQuestInterface();
					sendFrame126("Close Window", 8135);// Close Text
					sendFrame126("@dre@Herblore Guide", 8144);// Herblor Guide
					sendFrame126("Attack potion (3) - guam leaf/eye of newt", 8145);
					sendFrame126("Antipoision (5) - Marrentill/Ground Unicorn Horn", 8147);
					sendFrame126("Relicym's Balm (8) - Rouges Purse/Snake Weed", 8148);
					sendFrame126("Strength Potion (12) - Tarromin/Limpwurt Root", 8149);
					sendFrame126("Serum 207 (15) - Tarromin/Ashes", 8150);
					sendFrame126("Guthix Rest Tea (18) - 2Guam Leaves/Marrentill/Harralander", 8151);
					sendFrame126("Guam Tar (19) - Guam Leaf/15 Swamp Tar", 8152);
					sendFrame126("Stat Restore Potion (22) - Harralander/Red Spiders' Eggs", 8153);
					sendFrame126("Guthix Balance (22) Stat Restore Potion/Garlic/Silver Dust", 8154);
					sendFrame126("Blamish Oil (25) - Harralander/Blamish Snail Slime", 8155);
					sendFrame126("Energy Potion (26) - Harralander/Chocolate Dust", 8156);
					sendFrame126("Defence Potion (30) - Ranarr Weed/White Berries", 8157);
					sendFrame126("Marrentill Tar (31) - Marrentill/15 Swamp Tar", 8158);
					sendFrame126("Super Fishing Explosive (31) - Guam Leaf/Rubium", 8159);
					sendFrame126("Agility Potion (34) - Toadflax/Toad's Legs", 8160);
					sendFrame126("Combat Potion (38) - Harralander/Ground Goat Horn", 8161);
					sendFrame126("Prayer Potion (38) - Rannar Weed/Snape Grass", 8162);
					sendFrame126("Tarromin Tar (39) - Tarromin/15 Swamp Tar", 8163);
					sendFrame126("Harralander Tar (44) - Harralander/15 Swamp Tar", 8164);
					sendFrame126("Super Attack (45) - Irit Leaf/Eye of Newt", 8165);
					sendFrame126("Superantiposion (48) - Irit Leaf/Ground Unicorn Horn", 8166);
					sendFrame126("Fishing Potion (50) - Avantoe/Snape Grass", 8167);
					sendFrame126("Super Energy (52) - Avantoe/Mort Myre Fungi", 8168);
					sendFrame126("Super Strength (55) - Kwuarm/Limpwurt Root", 8169);
					sendFrame126("Weapon Poison (60) - Kwuarm/Ground Blue Dragon Scale", 8170);
					sendFrame126("Super Stat Restore (63) - Snapdragon/Red Spiders' Eggs", 8171);
					sendFrame126("Super Defence (66) - Toad Flax/White Berries", 8172);
					sendFrame126("Ranging Potion (72) - Dwarf Weed/Wine of Zamorak", 8173);
					sendFrame126("Weapon Poison + (73) - Cactus Spine/Red Spiders' Eggs", 8174);
					sendFrame126("Magic Potion (76) - Lantadyme/Potato Cactus", 8175);
					sendFrame126("Zamorak Brew (78) - Torstol/Jangerberries", 8176);
					sendFrame126("Saradomin Brew (81) - Toadflax/Crushed Birds's Nest", 8178);
					showInterface(8134);
					flushOutStream();
					break;
				case 48108:
					server.StaffMaking.OrbMakin(this);
					break;

				// Cooking
				case 34189:
				case 34185:
				case 34193:
					server.cooking.orMaybeNow(playerId);
					break;
				case 34170:// shortbow
				case 34169:
				case 34168:
				case 34167:
					server.fletching.Shortbow(playerId);
					break;
				case 34174:// longbow
				case 34173:
				case 34172:
				case 34171:
					server.fletching.Longbow(playerId);
					break;

				case 4169: // Charge arena spells
					if (arenaSpellTimer <= 0) {
						if (!playerHasItemAmount(554, 3) || !playerHasItemAmount(565, 3)
								|| !playerHasItemAmount(556, 3)) {
							sendMessage("You don't have enough runes to cast this spell.");
						} else {
							if (playerHasItemAmount(554, 3) && playerHasItemAmount(565, 3)
									&& playerHasItemAmount(556, 3)) {
								if (playerEquipment[playerCape] == 2412 || playerEquipment[playerCape] == 2413
										|| playerEquipment[playerCape] == 2414) {
									deleteItem(554, getItemSlot(554), 3);
									deleteItem(565, getItemSlot(565), 3);
									deleteItem(556, getItemSlot(556), 3);
									startAnimation(1820);
									stillgfx(441, absY, absX);
									arenaSpellTimer = 120; // 2 Minutes
									if (playerEquipment[playerCape] == 2412) {
										SaradominStrike = true;
										sendMessage(
												"You summon the power of the gods and increase your Saradomin Strike's power.");
									}
									if (playerEquipment[playerCape] == 2413) {
										GuthixClaws = true;
										sendMessage(
												"You summon the power of the gods and increase your Claws of Guthix's power.");
									}
									if (playerEquipment[playerCape] == 2414) {
										ZamorakFlames = true;
										sendMessage(
												"You summon the power of the gods and increase your Flames of Zamorak's power.");
									}
								} else {
									sendMessage("You need to be wearing a god cape to cast this spell.");
								}
							}
						}
					} else {
						sendMessage("Your god spell is at it's full power.");
					}
					break;

				case 55095: // Yes...
					deleteItem(DestroyedItem, GetItemSlot(DestroyedItem), 1);
					closeInterface();
					resetAnimation();
					break;

				case 55096: // No...
					closeInterface();
					resetAnimation();
					break;

				case 29063:// axes
					if (playerEquipment[playerWeapon] == 1377 && specialAmount <= 99) {// dba
						specialDamage = 0;
						specialDamage2 = 0;
						sendMessage("You do not have enough special energy left.");
					}
					if (playerEquipment[playerWeapon] == 1377 && specialAmount >= 100) {// dba
						specialDamage = 0;
						specialDamage2 = 0;
						startAnimation(1670);
						animation(246, absY, absX);
						specialAmount -= 100;
					}
					SpECS();
					break;

				case 29113:// bows
				case 33033:// halberds
				case 29163:// swords
				case 29138:// dds
				case 48023:// whip
					try {
						if (usingSpecial == true) {
							usingSpecial = false;
						} else if (usingSpecial == false) {
							usingSpecial = true;
						}
						SpECS();
					} catch (Exception popo) {
					}
					break;

				case 9158:
				case 14067:
					closeInterface();
					break;
				case 153:
					isRunning2 = true;
					stoprunning = false;
					break;
				case 152:
					isRunning2 = false;
					stoprunning = true;
					break;
				case 130: // close interface
					println("Closing Interface");
					break;
				case 9125: // Accurate
				case 22228: // punch (unarmed)
				case 48010: // flick (whip)
				case 21200: // spike (pickaxe)
				case 1080: // bash (staff)
				case 6168: // chop (axe)
				case 6236: // accurate (long bow)
				case 17102: // accurate (darts)
				case 8234: // stab (dagger)
					FightType = 1;
					SkillID = 0;
					break;
				case 9126: // Defensive
				case 22229: // block (unarmed)
				case 21201: // block (pickaxe)
				case 1078: // focus - block (staff)
				case 6169: // block (axe)
				case 33019: // fend (hally)
				case 18078: // block (spear)
				case 8235: // block (dagger)
					FightType = 4;
					SkillID = 1;
					break;
				case 9127: // Controlled
				case 48009: // lash (whip)
				case 33018: // jab (hally)
				case 6234: // longrange (long bow)
				case 18077: // lunge (spear)
				case 18080: // swipe (spear)
				case 18079: // pound (spear)
				case 17100: // longrange (darts)
					FightType = 3;
					SkillID = 3;
					break;
				case 9128: // Aggressive
				case 22230: // kick (unarmed)
				case 21203: // impale (pickaxe)
				case 21202: // smash (pickaxe)
				case 1079: // pound (staff)
				case 6171: // hack (axe)
				case 6170: // smash (axe)
				case 33020: // swipe (hally)
				case 6235: // rapid (long bow)
				case 17101: // repid (darts)
				case 8237: // lunge (dagger)
				case 8236: // slash (dagger)
					FightType = 2;
					SkillID = 2;
					break;
				case 9154: // Log out
					if (LogoutDelay >= 1) {
						sendMessage("You must wait 10 seconds after combat to log out!");
					} else {
						logout();
						savefile = true;
					}
					break;
				case 21011:
					takeAsNote = false;
					break;
				case 21010:
					takeAsNote = true;
					break;
				case 13092:
					if (tradeWith > 0) {
						if (PlayerHandler.players[tradeWith].tradeStatus == 2) {
							tradeStatus = 3;
							sendFrame126("Waiting for other player...", 3431);
						} else if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
							tradeStatus = 3;
						}
					}
					break;
				case 13218:
					if (tradeWith > 0) {
						if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
							tradeStatus = 4;
							sendFrame126("Waiting for other player...", 3535);
						} else if (PlayerHandler.players[tradeWith].tradeStatus == 4) {
							tradeStatus = 4;
						}
					}
					break;

				case 3162:
					InWildrange = true;
					break;

				case 3163:
					InWildrange = false;
					break;

				default:
					println("Packet 185, Action Button " + actionButtonId);
					break;
				}
				break;

			default:
				interfaceID = inStream.readUnsignedWordA();
				int actionButtonId1 = misc.HexToInt(inStream.buffer, 0, packetSize);
				/** WE DO NOT NEED THIS */// println("Unhandled packet ["+packetType+", InterFaceId: " +interfaceID+",
											// size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
				// println("Action Button: "+actionButtonId1);
				break;
			}
		} catch (Exception e) {
		}
	}

	public boolean FullGuthanEquipped() {
		if (playerEquipment[playerHat] == 4724 && playerEquipment[playerChest] == 4728
				&& playerEquipment[playerLegs] == 4730 && playerEquipment[playerWeapon] == 4726) {
			return true;
		}
		return false;
	}

	public boolean FullDharokEquipped() {
		if (playerEquipment[playerHat] == 4716 && playerEquipment[playerChest] == 4720
				&& playerEquipment[playerLegs] == 4722 && playerEquipment[playerWeapon] == 4718) {
			return true;
		}
		return false;
	}

	public boolean Attack() {
		try {
			faceNPC(32768 + AttackingOn);
			int EnemyX = PlayerHandler.players[AttackingOn].absX;
			int EnemyY = PlayerHandler.players[AttackingOn].absY;
			int EnemyHP = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
			int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[playerHitpoints];
			int casterX = absX;
			int casterY = absY;
			int offsetX = (casterX - EnemyX) * -1;
			int offsetY = (casterY - EnemyY) * -1;
			int EnemyX2 = PlayerHandler.players[AttackingOn].absX;
			int EnemyY2 = PlayerHandler.players[AttackingOn].absY;
			int hitDiff = 0;
			int wepdelay = 0;
			client AttackingOn2 = (client) server.playerHandler.players[AttackingOn];
			TurnPlayerTo(EnemyX, EnemyY);

			if (playerEquipment[playerWeapon] == (1434) || playerEquipment[playerWeapon] == (4587)
					|| playerEquipment[playerWeapon] == (4151) || playerEquipment[playerWeapon] == (1333)) {
				DelayZ(1);
			}
			if (playerEquipment[playerWeapon] == (1377)) {
				DelayZ(3);
			}
			if (playerEquipment[playerWeapon] == (7158)) {
				DelayZ(5);
			}
			if (playerEquipment[playerWeapon] == (4718)) {
				DelayZ(6);
			}

			boolean UseBow = false;
			if (playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841
					|| playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845
					|| playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849
					|| playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853
					|| playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857
					|| playerEquipment[playerWeapon] == 4827 || playerEquipment[playerWeapon] == 6724
					|| playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 861
					|| playerEquipment[playerWeapon] == 859) {
				DelayZ(5);
				UseBow = true;
			}
			if (UseBow) {
				inCombat();
				teleXY();
				CheckArrows();
				CalculateRange();
				hitDiff = misc.random(playerMaxHitRange);
			} else {
				PkingDelay = 6;
				wepdelay = 6;
			}

			if (!Wild() || !AttackingOn2.Wild()) {
				sendMessage("This player is in a safe zone and cannot be attacked");
				teleXY();
			}

			else if (Wild() && AttackingOn2.Wild()) {
				if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true || playerEquipment[playerWeapon] == 859
						|| playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214
						|| playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841
						|| playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845
						|| playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849
						|| playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853
						|| playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857) {
					if (LoopAttDelay <= 1) {
						if (Wild() && AttackingOn2.Wild()) {
							if (PlayerHandler.players[AttackingOn].IsDead == true) {
								ResetAttack();

							} else if (!UseBow) {
								if (usingSpecial == false) {
									CalculateMaxHit();
									hitDiff = misc.random(playerMaxHitMelee);
								}
								if ((EnemyHP - hitDiff) < 0) {
									hitDiff = EnemyHP;
								}
								setAnimation(GetWepAnim());
								AttackingOn2.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
								PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
								PlayerHandler.players[AttackingOn].updateRequired = true;
								PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
								AttackingOn2.KillerId = playerId;
								AttackingOn2.inCombat();
								LoopAttDelay = PkingDelay;
								PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
								addSkillXP(hitDiff, 3);

								if (playerEquipment[playerWeapon] == 5698) {
									AttackingOn2.PoisonPlayer();
									hitDiff = misc.random(playerMaxHitMelee);
									PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
									PlayerHandler.players[AttackingOn].updateRequired = true;
									PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
								}
								if (FullGuthanEquipped()) {
									if (misc.random(3) == 1) {
										stillgfx(398, EnemyY, EnemyX);
										hitDiff = misc.random(playerMaxHitMelee) + misc.random(4);
										NewHP = (playerLevel[playerHitpoints] + hitDiff);
										if (NewHP > getLevelForXP(playerXP[playerHitpoints])) {
											NewHP = getLevelForXP(playerXP[playerHitpoints]);
										}
										PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
										PlayerHandler.players[AttackingOn].updateRequired = true;
										PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
									} else {
										hitDiff = misc.random(playerMaxHitMelee);
										PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
										PlayerHandler.players[AttackingOn].updateRequired = true;
										PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
									}
								}
								if (usingSpecial == true) {
									specialDamage = 0;
									specialDamage2 = 0;
									SpECS();
									if (playerEquipment[playerWeapon] == 4151 && specialAmount >= 50) {
										hitDiff = specialDamage;
										calusingdmg1n2();
									}

									if (playerEquipment[playerWeapon] == 1305 && specialAmount >= 50) {
										gfx100(248);
										calusingdmg1n2();
										hitDiff = specialDamage;
									}

									if (playerEquipment[playerWeapon] == 4587 && specialAmount >= 75) {
										gfx100(347);
										calusingdmg1n2();
										hitDiff = specialDamage;
									}

									if (playerEquipment[playerWeapon] == 1434 && specialAmount >= 40) {
										gfx100(251);
										calusingdmg1n2();
										hitDiff = specialDamage;
									}

									if (playerEquipment[playerWeapon] == 6739 && specialAmount >= 100) {
										calusingdmg1n2();
										hitDiff = specialDamage;
									}

									if (playerEquipment[playerWeapon] == 5698 && specialAmount >= 25) {
										DDSSpecial();
										gfx100(252);
										AttackingOn2.PoisonPlayer();
										calusingdmg1n2();
									}

									if (playerEquipment[playerWeapon] == 4153 && specialAmount >= 50) {
										gfx100(340);
										maulSpec();
										calusingdmg1n2();
									}

									if (playerEquipment[playerWeapon] == 3204 && specialAmount >= 100) {
										gfx100(285);
										hally();
										calusingdmg1n2();
									}

									if (playerEquipment[playerWeapon] == 861 && specialAmount >= 75) {
										bowSpec();
										calusingdmg1n2();
									}
									usingSpecial = false;
								}

							} else if (UseBow) {
								if (!HasArrows) {
									sendMessage("There's no arrows left in your quiver");
									ResetAttack();
								}

								else if (HasArrows) {
									if ((EnemyHP - hitDiff) < 0) {
										hitDiff = EnemyHP;
									}
									DeleteArrow();
									inCombat();
									teleXY();
									setAnimation(426);
									drawback();
									ProjectileRang(offsetX, offsetY, AttackingOn, 0);
									AttackingOn2.KillerId = playerId + 10;
									AttackingOn2.inCombat();
									AttackingOn2.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
									LoopAttDelay = PkingDelay;
									addSkillXP(hitDiff, 8);
									PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
									PlayerHandler.players[AttackingOn].updateRequired = true;
									PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
									PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
									DropArrows(EnemyX, EnemyY);
								}
							}
						}
						return true;
					} else {
						sendMessage("This player is in a safe zone and cannot be attacked");
						teleXY();
						ResetAttack();
					}
				}
			}
			return false;
		} catch (Exception E) {
		}
		return false;
	}

	public boolean ResetAttack() {
		IsAttacking = false;
		AttackingOn = 0;
		resetAnimation();
		IsUsingSkill = false;
		pEmote = playerSE;
		return true;
	}

	public void SpECS() {
		specialAttacks();
		specialAttacks2();
		specialAttacks3();
		specialAttacks4();
		specialAttacks5();
		specialAttacks6();
	}

	public boolean ApplyDead() {
		try {
			if (IsDeadTimer == false) {
				actionTimer = 0;
				ResetAttack();
				ResetAttackNPC();
				pEmote = ServerCP.Deathemote;
				IsDeadTimer = true;
				SpECS();
			}
			if (ret) {
				stillgfx(437, absY, absX);
			}
			server.prayerHandler.resetPrayer(playerId);
			if (actionTimer == 0 && IsDeadTimer == true) {
				keepItem1();
				keepItem2();
				keepItem3();
				deleteItem(keepItem, getItemSlot(keepItem), keepItemAmount);
				deleteItem(keepItem2, getItemSlot(keepItem2), keepItemAmount2);
				deleteItem(keepItem3, getItemSlot(keepItem3), keepItemAmount3);
				youdied();
				frame1(); // Xerozcheez: Resets animation
				updateRequired = true;
				appearanceUpdateRequired = true;
				NewHP = getLevelForXP(playerXP[3]);
				setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[playerHitpoints]);
				playerLevel[3] = getLevelForXP(playerXP[3]);
				refreshSkills();
				PoisonDelay = 9999999;
				EntangleDelay = 0;
				KillerId = playerId;
				if (keepItem != 0)
					addItem(keepItem, keepItemAmount);
				if (keepItem2 != 0)
					addItem(keepItem2, keepItemAmount2);
				if (keepItem3 != 0)
					addItem(keepItem3, keepItemAmount3);
				resetKeepItem();
				setAnimation(ServerCP.Respawnemote);
			}
			return true;
		} catch (Exception E) {
		}
		return true;
	}

	public void resetKeepItem() {
		keepItem = 0;
		keepItem2 = 0;
		keepItem3 = 0;
		keepItemAmount = 0;
		keepItemAmount2 = 0;
		keepItemAmount3 = 0;
	}

	public void keepItem1() {
		for (int i = 0; i < playerItems.length; i++) {
			int highest = 0;
			int value = (int) Math.floor(GetItemShopValue(playerItems[i] - 1, 0, i));
			if (value > highest && playerItems[i] - 1 != -1) {
				highest = value;
				keepItem = playerItems[i] - 1;
				keepItemAmount = playerItemsN[i];
			}
		}
	}

	public void keepItem2() {
		for (int i = 0; i < playerItems.length; i++) {
			int highest = 0;
			int value = (int) Math.floor(GetItemShopValue(playerItems[i] - 1, 0, i));
			if (value > highest && playerItems[i] - 1 != keepItem && playerItems[i] - 1 != -1) {
				highest = value;
				keepItem2 = playerItems[i] - 1;
				keepItemAmount2 = playerItemsN[i];
			}
		}
	}

	public void keepItem3() {
		for (int i = 0; i < playerItems.length; i++) {
			int highest = 0;
			int value = (int) Math.floor(GetItemShopValue(playerItems[i] - 1, 0, i));
			if (value > highest && playerItems[i] - 1 != keepItem && playerItems[i] - 1 != keepItem2
					&& playerItems[i] - 1 != -1) {
				highest = value;
				keepItem3 = playerItems[i] - 1;
				keepItemAmount3 = playerItemsN[i];
			}
		}
	}

	public void HealThis(int amount, int newId) {
		healing[1] = amount;
		healing[2] = amount;
		healing[3] = newId;
	}

	public boolean CheckForSkillUse(int Item, int Slot) {
		boolean GoOn = true;
		switch (Item) {

		case 319:
			HealThis(1, -1);
			break; // Anchovies
		case 315:
			HealThis(3, -1);
			break; // Shrimps
		case 325:
			HealThis(4, -1);
			break; // Sardine
		case 347:
			HealThis(5, -1);
			break; // Herring
		case 355:
			HealThis(6, -1);
			break; // Mackerel
		case 333:
		case 339:
			HealThis(7, -1);
			break; // Trout & Cod
		case 351:
			HealThis(8, -1);
			break; // Pike
		case 361:
			HealThis(10, -1);
			break; // Tuna
		case 379:
			HealThis(12, -1);
			break; // Lobster
		case 365:
			HealThis(13, -1);
			break; // Bass
		case 373:
			HealThis(14, -1);
			break; // Swordfish
		case 7946:
			HealThis(15, -1);
			break; // Monk fish
		case 385:
		case 7220:
			HealThis(20, -1);
			break; // Shark & Summer pie
		case 7218:
			HealThis(20, 7220);
			break; // Summer pie
		case 391:
			HealThis(25, -1);
			break; // Manta ray

		case 2446:
			Anti(2446, 175);
			break;
		case 175:
			Anti(175, 177);
			break;
		case 177:
			Anti(177, 179);
			break;
		case 179:
			Anti(179, 229);
			break;

		case 2440:
			StrPot(2440, 157, GoOn);
			break;
		case 157:
			StrPot(157, 159, GoOn);
			break;
		case 159:
			StrPot(159, 161, GoOn);
			break;
		case 161:
			StrPot(161, 229, GoOn);
			break;

		case 2442:
			DefPot(2442, 163, GoOn);
			break;
		case 163:
			DefPot(163, 165, GoOn);
			break;
		case 165:
			DefPot(165, 167, GoOn);
			break;
		case 167:
			DefPot(167, 229, GoOn);
			break;

		case 2436:
			AttPot(2436, 145, GoOn);
			break;
		case 145:
			AttPot(145, 147, GoOn);
			break;
		case 147:
			AttPot(147, 149, GoOn);
			break;
		case 149:
			AttPot(149, 229, GoOn);
			break;

		case 536: // Dragon Bones
			prayer[2] = 72;
			break;
		case 526: // Bones
			if (misc.random2(2) == 1) {
				prayer[2] = 4;
			} else {
				prayer[2] = 5;
			}
			break;
		case 532: // Big Bones
			prayer[2] = 15;
			break;

		default:
			sendMessage("Nothing interesting is happening.");
			println("Prayer Usage - ItemID: " + Item);
			GoOn = false;
			break;
		}
		if (GoOn == false) {
			return false;
		}
		if (prayer[2] > 0) {
			prayer[0] = 1;
			prayer[4] = Item;
			prayer[5] = Slot;
		} else if (healing[1] > 0) {
			setAnimation(829);
			healing[0] = 1;
			healing[4] = Item;
			healing();
		}
		return true;
	}

	public void Anti(int del, int add) {
		PoisonDelay = 9999999;
		deleteItem(del, getItemSlot(del), 1);
		addItem(add, 1);
	}

	public void StrPot(int remove, int add, boolean GoOn) {
		setAnimation(829);
		strPot = true;
		strPotTimer = 30;
		abc = getLevelForXP(playerXP[2]);
		cba = abc / 5;
		abc2 = cba * 2;
		if (abc2 <= 1) {
			abc2 = 2;
		}
		playerLevel[2] = getLevelForXP(playerXP[2]);
		playerLevel[2] += abc2;
		sendFrame126("" + playerLevel[2] + "", 4006);
		deleteItem(remove, GetItemSlot(remove), 1);
		addItem(add, 1);
		updateRequired = true;
		appearanceUpdateRequired = true;
		GoOn = false;
	}

	public void DefPot(int remove, int add, boolean GoOn) {
		setAnimation(829);
		defPot = true;
		defPotTimer = 30;
		abc = getLevelForXP(playerXP[1]);
		cba = abc / 5;
		abc2 = cba * 2;
		if (abc2 <= 1) {
			abc2 = 2;
		}
		playerLevel[1] = getLevelForXP(playerXP[1]);
		playerLevel[1] += abc2;
		sendFrame126("" + playerLevel[1] + "", 4008);
		deleteItem(remove, GetItemSlot(remove), 1);
		addItem(add, 1);
		updateRequired = true;
		appearanceUpdateRequired = true;
		GoOn = false;
	}

	public void AttPot(int remove, int add, boolean GoOn) {
		setAnimation(829);
		AttPot = true;
		AttPotTimer = 30;
		abc = getLevelForXP(playerXP[0]);
		cba = abc / 5;
		abc2 = cba * 2;
		if (abc2 <= 1) {
			abc2 = 2;
		}
		playerLevel[0] = getLevelForXP(playerXP[1]);
		playerLevel[0] += abc2;
		sendFrame126("" + playerLevel[0] + "", 4008);
		deleteItem(remove, GetItemSlot(remove), 1);
		addItem(add, 1);
		updateRequired = true;
		appearanceUpdateRequired = true;
		GoOn = false;
	}

	public boolean IsItemInBag(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return true;
			}
		}
		return false;
	}

	public int GetItemSlot(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return i;
			}
		}
		return -1;
	}

	public int GetWeaponSound() {
		if (playerEquipment[playerWeapon] == 772 || playerEquipment[playerWeapon] == 1379
				|| playerEquipment[playerWeapon] == 1381 || playerEquipment[playerWeapon] == 1383
				|| playerEquipment[playerWeapon] == 1385 || playerEquipment[playerWeapon] == 1387
				|| playerEquipment[playerWeapon] == 1389 || playerEquipment[playerWeapon] == 1391
				|| playerEquipment[playerWeapon] == 1393 || playerEquipment[playerWeapon] == 1395
				|| playerEquipment[playerWeapon] == 1397 || playerEquipment[playerWeapon] == 1399
				|| playerEquipment[playerWeapon] == 1401 || playerEquipment[playerWeapon] == 1403
				|| playerEquipment[playerWeapon] == 1405 || playerEquipment[playerWeapon] == 1407
				|| playerEquipment[playerWeapon] == 1409)// Staff wack
		{
			return 394;
		}
		if (playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841
				|| playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845
				|| playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849
				|| playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853
				|| playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857
				|| playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861
				|| playerEquipment[playerWeapon] == 4734 || playerEquipment[playerWeapon] == 2023// RuneC'Bow
				|| playerEquipment[playerWeapon] == 4212 || playerEquipment[playerWeapon] == 4214
				|| playerEquipment[playerWeapon] == 4934)// Bows/Crossbows
		{
			return 370;
		}
		if (playerEquipment[playerWeapon] == 1363 || playerEquipment[playerWeapon] == 1365
				|| playerEquipment[playerWeapon] == 1367 || playerEquipment[playerWeapon] == 1369
				|| playerEquipment[playerWeapon] == 1371 || playerEquipment[playerWeapon] == 1373
				|| playerEquipment[playerWeapon] == 1375 || playerEquipment[playerWeapon] == 1377
				|| playerEquipment[playerWeapon] == 1349 || playerEquipment[playerWeapon] == 1351
				|| playerEquipment[playerWeapon] == 1353 || playerEquipment[playerWeapon] == 1355
				|| playerEquipment[playerWeapon] == 1357 || playerEquipment[playerWeapon] == 1359
				|| playerEquipment[playerWeapon] == 1361)// BattleAxes/Axes
		{
			return 399;
		}
		if (playerEquipment[playerWeapon] == 4718 || playerEquipment[playerWeapon] == 6609
				|| playerEquipment[playerWeapon] == 7808 || playerEquipment[playerWeapon] == 1307
				|| playerEquipment[playerWeapon] == 1309 || playerEquipment[playerWeapon] == 1311
				|| playerEquipment[playerWeapon] == 1313 || playerEquipment[playerWeapon] == 1315
				|| playerEquipment[playerWeapon] == 1317 || playerEquipment[playerWeapon] == 1319) // 2hs/Dharok
																									// GreatAxe
		{
			return 400;
		}
		if (playerEquipment[playerWeapon] == 1321 || playerEquipment[playerWeapon] == 1323
				|| playerEquipment[playerWeapon] == 1325 || playerEquipment[playerWeapon] == 1327
				|| playerEquipment[playerWeapon] == 1329 || playerEquipment[playerWeapon] == 1331
				|| playerEquipment[playerWeapon] == 1333 || playerEquipment[playerWeapon] == 4587) // Scimitars
		{
			return 396;
		}
		if (playerEquipment[playerWeapon] == 4068) // Godswords
		{
			return 390;
		} else {
			return 398;// Daggers(this is enything that isn't added, so if you didn't add a skimmy or
						// something, it would play this sound)
		}
	}

	public int GetWepAnim() {
		if (playerEquipment[playerWeapon] == -1) // unarmed
			if (FightType == 2) {
				return 423;
			} else {
				return 422;
			} // kick
		if (playerEquipment[playerWeapon] == 4151) // whip
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 1305 || playerEquipment[playerWeapon] == 6739
				|| playerEquipment[playerWeapon] == 4587 || playerEquipment[playerWeapon] == 1321
				|| playerEquipment[playerWeapon] == 1323 || playerEquipment[playerWeapon] == 1325
				|| playerEquipment[playerWeapon] == 1327 || playerEquipment[playerWeapon] == 1329
				|| playerEquipment[playerWeapon] == 1327 || playerEquipment[playerWeapon] == 1321
				|| playerEquipment[playerWeapon] == 1333) // scimitars
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 3204) // dragon halberd
		{
			return 440;
		}
		if (playerEquipment[playerWeapon] == 4827 || playerEquipment[playerWeapon] == 4214
				|| playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861
				|| playerEquipment[playerWeapon] == 6724) // bows
		{
			return 426;
		}
		if (playerEquipment[playerWeapon] == 7449 || playerEquipment[playerWeapon] == 4153) // maul
		{
			return 1665;
		}
		if (playerEquipment[playerWeapon] == 1377 || playerEquipment[playerWeapon] == 5018
				|| playerEquipment[playerWeapon] == 1434) // dragon mace
		{
			return 1833;
		}
		if (playerEquipment[playerWeapon] == 4726 || playerEquipment[playerWeapon] == 5730) {
			return 2080;
		}
		if (playerEquipment[playerWeapon] == 4718) // dharoks axe
		{
			return 2067;
		}
		if (playerEquipment[playerWeapon] == 4747) // torags hammers
		{
			return 2068;
		}
		if (playerEquipment[playerWeapon] == 4755) // veracs flail
		{
			return 2062;
		}
		if (playerEquipment[playerWeapon] == 4734) // karils x bow
		{
			return 2075;
		}
		if (playerEquipment[playerWeapon] == 1215 || playerEquipment[playerWeapon] == 1231
				|| playerEquipment[playerWeapon] == 5680 || playerEquipment[playerWeapon] == 5698) // dragon daggers
		{
			return 402;
		}
		if (playerEquipment[playerWeapon] == 7158 || playerEquipment[playerWeapon] == 6609
				|| playerEquipment[playerWeapon] == 1307 || playerEquipment[playerWeapon] == 1309
				|| playerEquipment[playerWeapon] == 1311 || playerEquipment[playerWeapon] == 1313
				|| playerEquipment[playerWeapon] == 1315 || playerEquipment[playerWeapon] == 1317
				|| playerEquipment[playerWeapon] == 1319) {
			return 407;
		} // 2 handers
		if (playerEquipment[playerWeapon] == 1419) // scythe
		{
			return 408;
		} else {
			return ServerCP.Attackemote;
		}
	}

	public int GetRunAnim(int id) {
		if (id == 4151) {
			return 1661;
		} // whip
		if (id == 4734) {
			return 2077;
		} // karils x bow
		if (id == 7449 || id == 4153) {
			return 1664;
		} // maul
		else {
			return ServerCP.Runemote;
		}
	}

	public int GetWalkAnim(int id) {
		if (id == 4039 || id == 4037 || id == 1379 || id == 3204 || id == 1381 || id == 1383 || id == 1385 || id == 1387
				|| id == 1389 || id == 1391 || id == 1393 || id == 1395 || id == 1397 || id == 1399 || id == 1401
				|| id == 1403 || id == 145 || id == 1407 || id == 1409 || id == 3053 || id == 3054 || id == 4170
				|| id == 4675 || id == 4710 || id == 6526 || id == 4726 || id == 6562 || id == 6563 || id == 6914
				|| id == 5730) // staves + d long and most other weps with str8 up emote
		{
			return 1146;
		}
		if (id == 4718) {
			return 2064;
		} // dharoks axe
		if (id == 4755) {
			return 2060;
		} // veracs flail
		if (id == 4734) {
			return 2076;
		} // karils x bow
		if (id == 4153) {
			return 1663;
		} // maul
		if (id == 7158 || id == 4718) {
			return 2064;
		} // 2h + gr8 axe
		if (id == 4151) {
			return 1661;
		} // whip
		else {
			return ServerCP.walkemote;
		}
	}

	public int GetStandAnim(int id) {
		if (id == 1305 || id == 1379 || id == 1381 || id == 1383 || id == 1385 || id == 1387 || id == 1389 || id == 1391
				|| id == 1393 || id == 1395 || id == 1397 || id == 1399 || id == 1401 || id == 1403 || id == 145
				|| id == 1407 || id == 1409 || id == 3053 || id == 3054 || id == 4170 || id == 4675 || id == 4710
				|| id == 6526 || id == 4726 || id == 6562 || id == 6563 || id == 5730) // staves
		{
			return 2061;
		}
		if (id == 4718) {
			return 2065;
		} // dharoks axe
		if (id == 4755) {
			return 2061;
		} // veracs flail
		if (id == 4734) {
			return 2074;
		} // karils x bow
		if (id == 7449 || id == 4153) {
			return 1662;
		} // maul
		if (id == 4565) {
			return 1836;
		} // basket of eggs :)
		if (id == 7158) {
			return 2065;
		} // 2h
		if (id == 3204) {
			return 809;
		} // dragon halberd
		else {
			return ServerCP.standemote;
		}
	}

	public int GetBlockAnim(int id) {
		if (id == 4755) {
			return 2063;
		} // Flail block
		if (id == 4153) {
			return 1666;
		} // Maul Blockl
		if (id == 4710) {
			return 2079;
		} // Ahrims staff
		if (id == 4734) {
			return 1666;
		} // Karils X bow
		else {
			return ServerCP.Blockemote;
		}
	}

	public int GetXItemsInBag(int ItemID) {
		int ItemCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
		}
		return ItemCount;
	}

	public void pmstatus(int status) { // status: loading = 0 connecting = 1 fine = 2
		outStream.createFrame(221);
		outStream.writeByte(status);
	}

	public boolean isinpm(long l) {
		for (int i = 0; i < friends.length; i++) {
			if (friends[i] != 0) {
				if (l == friends[i]) {
					return true;
				}
			}
		}
		return false;
	}

	public void pmupdate(int pmid, int world) {
		long l = misc.playerNameToInt64(handler.players[pmid].playerName);
		if (handler.players[pmid].Privatechat == 0) {
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] != 0) {
					if (l == friends[i]) {
						loadpm(l, world);
						return;
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 1) {
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i] != 0) {
					if (l == friends[i1]) {
						if (handler.players[pmid].isinpm(misc.playerNameToInt64(playerName)) && playerRights > 2) {
							loadpm(l, world);
							return;
						} else {
							loadpm(l, 0);
							return;
						}
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 2) {
			for (int i2 = 0; i2 < friends.length; i2++) {
				if (friends[i] != 0) {
					if (l == friends[i2] && playerRights < 2) {
						loadpm(l, 0);
						return;
					}
				}
			}
		}
	}

	public void sendpm(long name, int rights, byte[] chatmessage, int messagesize) {
		outStream.createFrameVarSize(196);
		outStream.writeQWord(name);
		outStream.writeDWord(handler.lastchatid++);// must be different for each message
		outStream.writeByte(rights);
		outStream.writeBytes(chatmessage, messagesize, 0);
		outStream.endFrameVarSize();
	}

	public void loadpm(long name, int world) {
		if (world != 0) {
			world += 9;
		} else if (world == 0) {
			world += 1;
		}
		outStream.createFrame(50);
		outStream.writeQWord(name);
		outStream.writeByte(world);
	}

	public void AttackMage(int index) { // Magic on players
		try {
			int playerIndex = index;
			int EnemyX = server.playerHandler.players[playerIndex].absX;
			int EnemyY = server.playerHandler.players[playerIndex].absY;
			int EnemyHP = server.playerHandler.players[playerIndex].playerLevel[playerHitpoints];
			int heal = 0;
			int myHP = playerLevel[playerHitpoints];
			int hitDiff = 0;
			int mageXP = 0;
			if (playerName.equalsIgnoreCase(ServerCP.Owner)) {
				println("playerIndex: " + playerIndex + " spellID: " + spellID);
			}
			client castOnPlayer = (client) server.playerHandler.players[playerIndex];
			setAnimation(711);
			faceNPC(32768 + index);

			// ADD MAGIC ON PLAYERS HERE :S:S:S

			if (spellID == 12891) {
				if (actionTimer == 0) {
					if (playerLevel[6] > 93) {
						if ((playerHasItemAmount(560, 4) == false) || (playerHasItemAmount(565, 2) == false)
								|| (playerHasItemAmount(555, 6) == false)) {
							sendMessage("You need 4 " + getItemName(560) + ", 4 " + getItemName(565) + " and 6 "
									+ getItemName(555));
						} else if ((playerHasItemAmount(560, 4) == true) && (playerHasItemAmount(565, 2) == true)
								&& (playerHasItemAmount(555, 6) == true)) {
							hitDiff = 5 + misc.random(25);
							startAnimation(1979);
							castOnPlayer.uberentangle();
							castOnPlayer.inCombat();
							inCombat();
							actionTimer = 10;
							stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
							PkingDelay = 15;
							castOnPlayer.sendMessage("You have been frozen!");
							deleteItem(560, getItemSlot(560), 4);
							deleteItem(565, getItemSlot(565), 4);
							deleteItem(555, getItemSlot(555), 6);
							teleportToX = absX;
							teleportToY = absY;
						}
					} else if (playerLevel[6] <= 92) {
						sendMessage("You need a magic level of 93 to cast this spell.");
					}
				} else {
				}
			}
			if ((EnemyHP - hitDiff) < 0) {
				hitDiff = EnemyHP;
			}
			mageXP = (hitDiff * 50);
			addSkillXP(mageXP, 6);
			addSkillXP(hitDiff, 3);
			castOnPlayer.hitDiff = hitDiff;
			castOnPlayer.KillerId = playerId;
			castOnPlayer.updateRequired = true;
			castOnPlayer.hitUpdateRequired = true;
		} catch (Exception E) {
		}
	}

	public boolean AttackNPC() throws InterruptedException {
		try {
			int EnemyX = server.npcHandler.npcs[attacknpc].absX;
			int EnemyY = server.npcHandler.npcs[attacknpc].absY;
			int EnemyHP = server.npcHandler.npcs[attacknpc].HP;
			int casterX = absX;
			int casterY = absY;
			int offsetX = (casterX - EnemyX) * -1;
			int offsetY = (casterY - EnemyY) * -1;
			int EnemyX2 = server.npcHandler.npcs[attacknpc].absX;
			int EnemyY2 = server.npcHandler.npcs[attacknpc].absY;
			int hitDiff = 0;
			int Npchitdiff = 0;
			int wepdelay = 0;
			TurnPlayerTo(EnemyX, EnemyY);
			faceNPC(attacknpc);

			if (server.npcHandler.npcs[attacknpc].followPlayer < 1
					|| server.npcHandler.npcs[attacknpc].followPlayer == playerId) {
				if (playerEquipment[playerWeapon] == (1434) || playerEquipment[playerWeapon] == (4151)
						|| playerEquipment[playerWeapon] == (1377) || playerEquipment[playerWeapon] == (4587)
						|| playerEquipment[playerWeapon] == (1333)) {
					DelayZ(1);
				}
				if (playerEquipment[playerWeapon] == (4718)) {
					DelayZ(6);
				}
				if (playerEquipment[playerWeapon] == (1377)) {
					DelayZ(3);
				}
				if (playerEquipment[playerWeapon] == (7158)) {
					DelayZ(5);
				}

				boolean UseBow = false;
				if (playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841
						|| playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845
						|| playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849
						|| playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853
						|| playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857
						|| playerEquipment[playerWeapon] == 4827 || playerEquipment[playerWeapon] == 6724
						|| playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 861
						|| playerEquipment[playerWeapon] == 859) {
					DelayZ(5);
					UseBow = true;
				}

				if (UseBow) {
					inCombat();
					teleXY();
					CheckArrows();
					CalculateRange();
					hitDiff = misc.random(playerMaxHitRange);
				} else {
					PkingDelay = 6;
					wepdelay = 6;
				}

				if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true || playerEquipment[playerWeapon] == 4827
						|| playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861
						|| playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 839
						|| playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 843
						|| playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847
						|| playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851
						|| playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855
						|| playerEquipment[playerWeapon] == 857 || playerEquipment[playerWeapon] == 6724) {
					if (LoopAttDelay <= 1) {
						if (server.npcHandler.npcs[attacknpc].IsDead == true) {
							ResetAttackNPC();
						}

						else if (!UseBow) {
							if (usingSpecial == false) {
								CalculateMaxHit();
								hitDiff = misc.random(playerMaxHitMelee);
							}
							setAnimation(GetWepAnim());
							LoopAttDelay = PkingDelay;
							server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
							server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
							server.npcHandler.npcs[attacknpc].updateRequired = true;
							server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
							double TotalExp = 0;
							inCombat();
							TotalExp = (double) (ServerCP.FightingXP * hitDiff / 3);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), playerHitpoints);
							actionTimer = 7;
							server.npcHandler.npcs[attacknpc].animNumber = server.npcHandler
									.GetNPCBlockAnim(server.npcHandler.npcs[attacknpc].npcType);

							if (FullGuthanEquipped()) {
								if (misc.random(6) == 1) {
									stillgfx(398, EnemyY, EnemyX);
									hitDiff = misc.random(playerMaxHitMelee);
									NewHP = (playerLevel[playerHitpoints] + hitDiff);
									if (NewHP > getLevelForXP(playerXP[playerHitpoints])) {
										NewHP = getLevelForXP(playerXP[playerHitpoints]);
									}
									server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
									server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
									server.npcHandler.npcs[attacknpc].updateRequired = true;
									server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
								} else {
									hitDiff = misc.random(playerMaxHitMelee);
									server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
									server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
									server.npcHandler.npcs[attacknpc].updateRequired = true;
									server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
								}
							}
							if (FightType == 1) {
								TotalExp = (double) (ServerCP.FightingXP * hitDiff);
								TotalExp = (double) (TotalExp * CombatExpRate);
								addSkillXP((int) (TotalExp), playerAttack);
							} // Accurate
							else if (FightType == 2) {
								TotalExp = (double) (ServerCP.FightingXP * hitDiff);
								TotalExp = (double) (TotalExp * CombatExpRate);
								addSkillXP((int) (TotalExp), playerStrength);
							} // Agressive
							else if (FightType == 4) {
								TotalExp = (double) (ServerCP.FightingXP * hitDiff);
								TotalExp = (double) (TotalExp * CombatExpRate);
								addSkillXP((int) (TotalExp), playerDefence);
							} // Defensive
							else if (FightType == 3) {
								TotalExp = (double) (ServerCP.FightingXP * hitDiff);
								TotalExp = (double) (TotalExp * CombatExpRate);
								addSkillXP((int) (TotalExp), playerStrength);
							} // Controlled
							if ((EnemyHP - hitDiff) < 0) {
								hitDiff = EnemyHP;
							}

							if (usingSpecial == true) {
								specialDamage = 0;
								specialDamage2 = 0;
								SpECS();

								if (playerEquipment[playerWeapon] == 4151 && specialAmount >= 50) {
									hitDiff = specialDamage;
									calusingdmg1n2();
								}

								if (playerEquipment[playerWeapon] == 1305 && specialAmount >= 50) {
									hitDiff = specialDamage;
									gfx100(248);
									calusingdmg1n2();
								}

								if (playerEquipment[playerWeapon] == 3204 && specialAmount >= 100) {
									hitDiff = specialDamage;
									gfx100(285);
									calusingdmg1n2();
								}

								if (playerEquipment[playerWeapon] == 4587 && specialAmount >= 75) {
									hitDiff = specialDamage;
									gfx100(347);
									calusingdmg1n2();
								}

								if (playerEquipment[playerWeapon] == 1434 && specialAmount >= 40) {
									hitDiff = specialDamage;
									gfx100(251);
									calusingdmg1n2();
								}

								if (playerEquipment[playerWeapon] == 5698 && specialAmount >= 25) {
									DDSSpecial();
									gfx100(252);
									server.npcHandler.PoisonNPC(attacknpc);
									calusingdmg1n2();
								}

								if (playerEquipment[playerWeapon] == 4153 && specialAmount >= 50) {
									gfx100(340);
									calusingdmg1n2();
									maulSpec();
								}

								if (playerEquipment[playerWeapon] == 6739 && specialAmount >= 100) {
									hitDiff = specialDamage;
									calusingdmg1n2();
								}

								if (playerEquipment[playerWeapon] == 861 && specialAmount >= 75) {
									bowSpec();
									calusingdmg1n2();
								}
								usingSpecial = false;
							}
						} else if (UseBow) {
							if (!HasArrows && actionTimer == 0) {
								sendMessage("There's no arrows left in your quiver");
								ResetAttack();
								actionTimer = 4;
								teleXY();
							} else if (HasArrows) {
								LoopAttDelay = PkingDelay;
								drawback();
								inCombat();
								teleXY();
								setAnimation(426);
								actionTimer = 4;
								server.npcHandler.npcs[attacknpc].animNumber = server.npcHandler
										.GetNPCBlockAnim(server.npcHandler.npcs[attacknpc].npcType);
								double TotalExp = 0;
								TotalExp = (double) (38 * hitDiff);
								TotalExp = (double) (TotalExp * CombatExpRate);
								TotalExp = (double) (38 * hitDiff);
								TotalExp = (double) (TotalExp * CombatExpRate);
								addSkillXP((int) (TotalExp), playerRanged);
								addSkillXP((int) (TotalExp), playerHitpoints);
								server.npcHandler.npcs[attacknpc].updateRequired = true;
								ProjectileRang(offsetX, offsetY, attacknpc, 1);
								server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
								server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
								server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
								DropArrows(EnemyX, EnemyY);
							}
						}
						return true;
					}
				}
			} else {
			}
			return false;
		} catch (Exception E) {
		}
		return false;
	}

	public boolean ResetAttackNPC() {
		if (attacknpc > -1 && attacknpc < server.npcHandler.maxNPCs) {
			server.npcHandler.npcs[attacknpc].IsUnderAttack = false;
		}
		IsAttackingNPC = false;
		attacknpc = -1;
		resetAnimation();
		pEmote = playerSE;
		faceNPC = 65535;
		faceNPCupdate = true;
		return true;
	}

	public int GetNPCID(int coordX, int coordY) {
		for (int i = 0; i < server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (server.npcHandler.npcs[i].absX == coordX && server.npcHandler.npcs[i].absY == coordY) {
					return server.npcHandler.npcs[i].npcType;
				}
			}
		}
		return 1;
	}

	public String GetNpcName(int NpcID) {
		for (int i = 0; i < server.npcHandler.maxListedNPCs; i++) {
			if (server.npcHandler.NpcList[i] != null) {
				if (server.npcHandler.NpcList[i].npcId == NpcID) {
					return server.npcHandler.NpcList[i].npcName;
				}
			}
		}
		return "Invalid NPC - ID:" + NpcID;
	}

	public String GetItemName(int ItemID) {
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					return server.itemHandler.ItemList[i].itemName;
				}
				if (ItemID == -1) {
					return "Unarmed";
				}
			}
		}
		return "Invalid ITEM - ID:" + ItemID;
	}

	public String getItemName(int ItemID) {
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					return server.itemHandler.ItemList[i].itemName;
				}
				if (ItemID == -1) {
					return "Unarmed";
				}
			}
		}
		return "Invalid ITEM - ID:" + ItemID;
	}

	public double GetItemShopValue(int ItemID, int Type, int fromSlot) {
		double ShopValue = 1;
		double Overstock = 0;
		double TotPrice = 0;
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					ShopValue = server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		TotPrice = (ShopValue * 1);
		if (server.shopHandler.ShopBModifier[MyShopID] == 1) {
			TotPrice *= 1; // 25% more expensive (general stores only)
			if (Type == 1) {
				TotPrice *= 1; // general store buys item at 40% of its own selling value
			}
		} else if (Type == 1) {
			TotPrice *= 1; // other stores buy item at 60% of their own selling value
		}
		return TotPrice;
	}

	public int GetUnnotedItem(int ItemID) {
		int NewID = 0;
		String NotedName = "";
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					NotedName = server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemName == NotedName) {
					if (server.itemHandler.ItemList[i].itemDescription
							.startsWith("Swap this note at any bank for a") == false) {
						NewID = server.itemHandler.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}

	public void ResetBonus() {
		for (int i = 0; i < playerBonus.length; i++) {
			playerBonus[i] = 0;
		}
	}

	public void GetBonus() {
		for (int i = 0; i < playerEquipment.length; i++) {
			if (playerEquipment[i] > -1) {
				for (int j = 0; j < 9999; j++) {
					if (server.itemHandler.ItemList[j] != null) {
						if (server.itemHandler.ItemList[j].itemId == playerEquipment[i]) {
							for (int k = 0; k < playerBonus.length; k++) {
								playerBonus[k] += server.itemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					}
				}
			}
		}
	}

	public void WriteBonus() {
		int offset = 0;
		String send = "";
		for (int i = 0; i < playerBonus.length; i++) {
			if (playerBonus[i] >= 0) {
				send = BonusName[i] + ": +" + playerBonus[i];
			} else {
				send = BonusName[i] + ": -" + java.lang.Math.abs(playerBonus[i]);
			}

			if (i == 10) {
				offset = 1;
			}
			sendFrame126(send, (1675 + i + offset));
		}
	}

	public void CalculateMaxHit() {
		double MaxHitz = 0;
		int StrBonus = playerBonus[10]; // Strength Bonus
		int Strength = playerLevel[playerStrength]; // Strength
		if (FightType == 1 || FightType == 4) { // Accurate & Defensive
			MaxHitz += ((double) ((double) StrBonus + Strength) / ((double) 6.7586206896551724137931034482759));
		} else if (FightType == 2) { // Aggresive
			MaxHitz += ((double) ((double) StrBonus + Strength) / ((double) 6.8275862068965517241379310344828));
		} else if (FightType == 3) { // Controlled
			MaxHitz += ((double) ((double) StrBonus + Strength) / ((double) 6.6551724137931034482758620689655));
		}
		if (StrPotion == 1) { // Strength Potion
			MaxHitz += (double) (Strength * 0.0404);
		} else if (StrPotion == 2) { // Super Strength Potion
			MaxHitz += (double) (Strength * 0.0805);
		}
		if (FullDharokEquipped()) {
			MaxHitz += (getLevelForXP(playerXP[playerHitpoints]) - playerLevel[playerHitpoints]) / 3;
		}
		playerMaxHitMelee = (int) Math.floor(MaxHitz);
	}

	public void CalculateRange() {
		double MaxHit = 0;
		int RangeBonus = playerBonus[4]; // Range Bonus
		int Range = playerLevel[5]; // Range
		{
			MaxHit += (double) (1.05 + (double) ((double) (RangeBonus * Range) * 0.0016821261243472372311));
		}
		playerMaxHitRange = (int) Math.floor(MaxHit);
	}

	public boolean GoodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX + i) == playerX
						&& ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if ((objectX - i) == playerX
						&& ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectX == playerX
						&& ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean GoodDistance2(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if (objectX == playerX
						&& ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectY == playerY
						&& ((objectX + j) == playerX || (objectX - j) == playerX || objectX == playerX)) {
					return true;
				}
			}
		}
		return false;
	}

	/* HEALING */

	public boolean healing() {
		if (healTimer == 0 && healing[0] == 1) {
			healTimer = 3;
			OriginalShield = playerEquipment[playerShield];
			OriginalWeapon = playerEquipment[playerWeapon];
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = -1;
			setAnimation(829);
			healing[0] = 2;
			healTimer = 0;
		}
		if (healTimer == 0 && healing[0] == 2) {
			deleteItem(healing[4], GetItemSlot(healing[4]), 1);
			int Heal = healing[1];
			int HealDiff = (healing[2] - healing[1]);
			if (HealDiff > 0) {
				Heal += misc.random(HealDiff);
			}
			if (healing[3] != -1) {
				addItem(healing[3], 1);
			}
			NewHP = (playerLevel[playerHitpoints] + Heal);
			if (NewHP > getLevelForXP(playerXP[playerHitpoints])) {
				NewHP = getLevelForXP(playerXP[playerHitpoints]);
			}
			sendMessage("You eat the " + GetItemName(healing[4]) + ".");
			playerEquipment[playerWeapon] = OriginalWeapon;
			playerEquipment[playerShield] = OriginalShield;
			OriginalWeapon = -1;
			OriginalShield = -1;
			healTimer = 2;
			resetAnimation();
			updateRequired = true;
			resetHE();
		}
		return true;
	}

	public boolean resetHE() {
		healing[0] = 0;
		healing[1] = 0;
		healing[2] = 0;
		healing[3] = -1;
		healing[4] = -1;
		IsUsingSkill = false;
		return true;
	}

	/* TRADING */

	public void AcceptTrade() {
		sendFrame248(3323, 3321); // trading window + bag
		resetItems(3322);
		resetTItems(3415);
		resetOTItems(3416);
		sendFrame126("Trading With: " + PlayerHandler.players[tradeWith].playerName, 3417);
		sendFrame126("", 3431);
	}

	public void DeclineTrade() {
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0) {
				fromTrade((playerTItems[i] - 1), i, playerTItemsN[i]);
			}
		}
		resetItems(3214);
		resetTrade();
	}

	public void resetTrade() {
		tradeWith = 0;
		tradeWaitingTime = 0;
		tradeStatus = 0;
		tradeUpdateOther = false;
		tradeOtherDeclined = false;
		WanneTrade = 0;
		WanneTradeWith = 0;
		TradeConfirmed = false;
		for (int i = 0; i < playerTItems.length; i++) {
			playerTItems[i] = 0;
			playerTItemsN[i] = 0;
			playerOTItems[i] = 0;
			playerOTItemsN[i] = 0;
		}
	}

	public void ConfirmTrade() {
		if (TradeConfirmed == false) {
			RemoveAllWindows();
			for (int i = 0; i < playerOTItems.length; i++) {
				if (playerOTItems[i] > 0) {
					addItem((playerOTItems[i] - 1), playerOTItemsN[i]);
					BufferedWriter bw = null;

					try {
						bw = new BufferedWriter(new FileWriter("logs/trades.txt", true));
						bw.write(PlayerHandler.players[tradeWith].playerName + " trades item: " + (playerOTItems[i] - 1)
								+ " amount: " + playerOTItemsN[i] + " with " + playerName);
						bw.newLine();
						bw.flush();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					} finally {
						if (bw != null)
							try {
								bw.close();
							} catch (IOException ioe2) {
								sendMessage("Error logging trade!");
							}
					}
				}
			}
			resetItems(3214);
			TradeConfirmed = true;
		}
	}

	public void TradeGoConfirm() {
		sendFrame248(3443, 3213); // trade confirm + normal bag
		resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount = "";
		int Count = 0;
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0) {
				if (playerTItemsN[i] >= 1000 && playerTItemsN[i] < 1000000) {
					SendAmount = "@cya@" + (playerTItemsN[i] / 1000) + "K @whi@(" + playerTItemsN[i] + ")";
				} else if (playerTItemsN[i] >= 1000000) {
					SendAmount = "@gre@" + (playerTItemsN[i] / 1000000) + " million @whi@(" + playerTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = GetItemName((playerTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + GetItemName((playerTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerTItems[i] - 1)] == true
						|| Item.itemStackable[(playerTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItems[i] > 0) {
				if (playerOTItemsN[i] >= 1000 && playerOTItemsN[i] < 1000000) {
					SendAmount = "@cya@" + (playerOTItemsN[i] / 1000) + "K @whi@(" + playerOTItemsN[i] + ")";
				} else if (playerOTItemsN[i] >= 1000000) {
					SendAmount = "@gre@" + (playerOTItemsN[i] / 1000000) + " million @whi@(" + playerOTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerOTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = GetItemName((playerOTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + GetItemName((playerOTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerOTItems[i] - 1)] == true
						|| Item.itemStackable[(playerOTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3558);
	}

	public boolean fromTrade(int itemID, int fromSlot, int amount) {
		if (amount > 0 && (itemID + 1) == playerTItems[fromSlot]) {
			if (amount > playerTItemsN[fromSlot]) {
				amount = playerTItemsN[fromSlot];
			}
			addItem((playerTItems[fromSlot] - 1), amount);
			if (amount == playerTItemsN[fromSlot]) {
				playerTItems[fromSlot] = 0;
				PlayerHandler.players[tradeWith].playerOTItems[fromSlot] = 0;
			}
			playerTItemsN[fromSlot] -= amount;
			PlayerHandler.players[tradeWith].playerOTItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}

	public boolean tradeItem(int itemID, int fromSlot, int amount) {
		if (tradeWith > 0) {
			if (PlayerHandler.players[tradeWith] == null) {
				DeclineTrade();
				sendMessage("FORCED DECLINE BY SERVER !");
				return false;
			}
		} else {
			DeclineTrade();
			sendMessage("FORCED DECLINE BY SERVER !");
			return false;
		}
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (amount > playerItemsN[fromSlot]) {
				amount = playerItemsN[fromSlot];
			}
			boolean IsInTrade = false;
			for (int i = 0; i < playerTItems.length; i++) {
				if (playerTItems[i] == playerItems[fromSlot]) {
					if (Item.itemStackable[(playerItems[fromSlot] - 1)] == true
							|| Item.itemIsNote[(playerItems[fromSlot] - 1)] == true) {
						playerTItemsN[i] += amount;
						PlayerHandler.players[tradeWith].playerOTItemsN[i] += amount;
						IsInTrade = true;
						break;
					}
				}
			}
			if (IsInTrade == false) {
				for (int i = 0; i < playerTItems.length; i++) {
					if (playerTItems[i] <= 0) {
						playerTItems[i] = playerItems[fromSlot];
						playerTItemsN[i] = amount;
						PlayerHandler.players[tradeWith].playerOTItems[i] = playerItems[fromSlot];
						PlayerHandler.players[tradeWith].playerOTItemsN[i] = amount;
						break;
					}
				}
			}
			if (amount == playerItemsN[fromSlot]) {
				playerItems[fromSlot] = 0;
			}
			playerItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}

	/* Shops */
	public boolean sellItem(int itemID, int fromSlot, int amount) {
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= server.shopHandler.ShopItemsStandard[MyShopID]; i++) {
					if (itemID == (server.shopHandler.ShopItems[MyShopID][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					sendMessage("You cannot sell " + GetItemName(itemID) + " in this store.");
					return false;
				}
			}
			if (Item.itemSellable[(playerItems[fromSlot] - 1)] == false) {
				sendMessage("I cannot sell " + GetItemName(itemID) + ".");
				return false;
			}
			if (amount > playerItemsN[fromSlot] && (Item.itemIsNote[(playerItems[fromSlot] - 1)] == true
					|| Item.itemStackable[(playerItems[fromSlot] - 1)] == true)) {
				amount = playerItemsN[fromSlot];
			} else if (amount > GetXItemsInBag(itemID) && Item.itemIsNote[(playerItems[fromSlot] - 1)] == false
					&& Item.itemStackable[(playerItems[fromSlot] - 1)] == false) {
				amount = GetXItemsInBag(itemID);
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 1, fromSlot));
				if (freeSlots() > 0) {
					if (Item.itemIsNote[itemID] == false) {
						deleteItem(itemID, GetItemSlot(itemID), 1);
					} else {
						deleteItem(itemID, fromSlot, 1);
					}
					addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					sendMessage("Not enough space in your inventory.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return true;
	}

	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if (amount > 0 && itemID == (server.shopHandler.ShopItems[MyShopID][fromSlot] - 1)) {
			if (amount > server.shopHandler.ShopItemsN[MyShopID][fromSlot]) {
				amount = server.shopHandler.ShopItemsN[MyShopID][fromSlot];
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			int Slot = 0;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0, fromSlot));
				Slot = GetItemSlot(995);
				if (Slot == -1) {
					sendMessage("You don't have enough coins.");
					break;
				}
				if (TotPrice2 <= 1) {
					TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0, fromSlot));
				}
				if (playerItemsN[Slot] >= TotPrice2) {
					if (freeSlots() > 0) {
						deleteItem(995, GetItemSlot(995), TotPrice2);
						addItem(itemID, 1);
						server.shopHandler.ShopItemsN[MyShopID][fromSlot] -= 1;
						server.shopHandler.ShopItemsDelay[MyShopID][fromSlot] = 0;
						if ((fromSlot + 1) > server.shopHandler.ShopItemsStandard[MyShopID]) {
							server.shopHandler.ShopItems[MyShopID][fromSlot] = 0;
						}
					} else {
						sendMessage("Not enough space in your inventory.");
						break;
					}
				} else {
					sendMessage("You don't have enough coins.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return false;
	}

	public void UpdatePlayerShop() {
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].IsShopping == true && PlayerHandler.players[i].MyShopID == MyShopID
						&& i != playerId) {
					PlayerHandler.players[i].UpdateShop = true;
				}
			}
		}
	}

	public boolean addShopItem(int itemID, int amount) {
		boolean Added = false;
		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = GetUnnotedItem(itemID);
		}
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if ((server.shopHandler.ShopItems[MyShopID][i] - 1) == itemID) {
				server.shopHandler.ShopItemsN[MyShopID][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
				if (server.shopHandler.ShopItems[MyShopID][i] == 0) {
					server.shopHandler.ShopItems[MyShopID][i] = (itemID + 1);
					server.shopHandler.ShopItemsN[MyShopID][i] = amount;
					server.shopHandler.ShopItemsDelay[MyShopID][i] = 0;
					break;
				}
			}
		}
		return true;
	}

	public void UpdateNPCChat() {
		sendFrame126("", 976);
		switch (NpcDialogue) {
		case 14600: // Make over mage chat
			sendFrame126("Father", 4902);
			sendFrame126("", 4903);
			sendFrame126("So, you want a make over?", 4904);
			sendFrame126("", 4905);
			sendFrame126("", 4906);
			sendFrame75(NpcTalkTo, 4901);
			sendFrame164(4900);
			NpcDialogueSend = true;
			break;
		case 14601: // Make over mage chat option - choose make over y/n
			wildtele = 0;
			sendFrame171(1, 2465);
			sendFrame171(0, 2468);
			sendFrame126("Select an Option", 2460);
			sendFrame126("Sure", 2461);
			sendFrame126("Nope", 2462);
			sendFrame164(2459);
			NpcDialogueSend = true;
			break;
		}
	}

	/* Equipment level checking */
	public int GetCLAttack(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Black", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws") || ItemName2.startsWith("dagger") || ItemName2.startsWith("sword")
				|| ItemName2.startsWith("scimitar") || ItemName2.startsWith("mace") || ItemName2.startsWith("longsword")
				|| ItemName2.startsWith("battleaxe") || ItemName2.startsWith("warhammer")
				|| ItemName2.startsWith("2h sword") || ItemName2.startsWith("harlberd")) {
			if (ItemName.startsWith("Steel")) {
				return 5;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("Dragon")) {
				return 60;
			} else if (ItemName.startsWith("White")) {
				return 120;
			}
		} else if (ItemName.startsWith("Granite")) {
			return 50;
		} else if (ItemName.endsWith("whip") || ItemName.endsWith("Ahrims staff") || ItemName.endsWith("Torags hammers")
				|| ItemName.endsWith("Veracs flail") || ItemName.endsWith("Guthans warspear")
				|| ItemName.endsWith("Dharoks greataxe")) {
			return 70;
		}
		return 1;
	}

	public int GetCLDefence(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Black", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("White", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws") || ItemName2.startsWith("dagger") || ItemName2.startsWith("sword")
				|| ItemName2.startsWith("scimitar") || ItemName2.startsWith("mace") || ItemName2.startsWith("longsword")
				|| ItemName2.startsWith("battleaxe") || ItemName2.startsWith("warhammer")
				|| ItemName2.startsWith("2h sword") || ItemName2.startsWith("harlberd")) {
			// It's a weapon, weapons don't required defence !
		} else if (ItemName.startsWith("Ahrims") || ItemName.startsWith("Karil") || ItemName.startsWith("Torag")
				|| ItemName.startsWith("Verac") || ItemName.startsWith("Guthans") || ItemName.endsWith("Dharok")) {
			if (ItemName.endsWith("staff") || ItemName.endsWith("crossbow") || ItemName.endsWith("hammers")
					|| ItemName.endsWith("flail") || ItemName.endsWith("warspear") || ItemName.endsWith("greataxe")) {
				// No defence for the barrow weapons
			} else {
				return 70;
			}
		} else {
			if (ItemName.startsWith("Steel")) {
				return 5;
			} else if (ItemName.startsWith("Mithril") || ItemName.startsWith("Initiate")
					|| ItemName.startsWith("initiate")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune full helm") || ItemName.startsWith("Rune Kite Shield")
					|| ItemName.startsWith("Rune Platelegs") || ItemName.startsWith("Rune Platebody")
					|| ItemName.startsWith("Rune Plateskirt")) {
				return 40;
			} else if (ItemName.startsWith("Dragon") || ItemName.startsWith("dragon")) {
				return 60;
			} else if (ItemName.startsWith("Berserker_helm")) {
				return 45;
			}
		}
		return 1;
	}

	public int GetCLStrength(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Granite")) {
			return 50;
		} else if (ItemName.startsWith("Torags hammers") || ItemName.endsWith("Dharoks greataxe")) {
			return 70;
		}
		return 1;
	}

	public int GetCLMagic(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Ahrim")) {
			return 70;
		}
		return 1;
	}

	public int GetCLHitpoints(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Ahrim")) {
			return 70;
		}
		return 1;
	}

	public int GetCLRanged(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Karil")) {
			return 70;
		}
		if (ItemName.startsWith("Dark Bow")) {
			return 99;
		}
		if (ItemName.startsWith("Crystal")) {
			return 90;
		}
		if (ItemName.startsWith("Seercull")) {
			return 70;
		}
		return 1;
	}

	public int GetWorld(int PlayerID) {
		try {
			return 1;
		} catch (Exception e) {
			println("Getworld error");
			println(e.toString());
			return 1;
		}
	}

	public int loadmoreinfo() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;

		try {
			characterfile = new BufferedReader(new FileReader("./moreinfo/" + playerName + ".txt"));
			File1 = true;
		} catch (FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File("./moreinfo/" + playerName + ".txt");
			File myfile2 = new File("./moreinfo/" + playerName + ".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			println(playerName + ": moreinfo file not found.");
			savemoreinfo();
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			println(playerName + ": error loading file.");
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-lastlogin")) {
						playerLastConnect = (token2);
					} else if (token.equals("character-lastlogintime")) {
						lastlogintime = Integer.parseInt(token2);
					} else if (token.equals("character-ancients")) {
						ancients = Integer.parseInt(token2);
					} else if (token.equals("character-height")) {
						heightLevel = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-look")) {
						playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					if (token.equals("character-head")) {
						pHead = Integer.parseInt(token2);
					}
					if (token.equals("character-torso")) {
						pTorso = Integer.parseInt(token2);
					}
					if (token.equals("character-arms")) {
						pArms = Integer.parseInt(token2);
					}
					if (token.equals("character-hands")) {
						pHands = Integer.parseInt(token2);
					}
					if (token.equals("character-legs")) {
						pLegs = Integer.parseInt(token2);
					}
					if (token.equals("character-feet")) {
						pFeet = Integer.parseInt(token2);
					}
					if (token.equals("character-beard")) {
						pBeard = Integer.parseInt(token2);
					}
					break;
				case 4:
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						friendslot = Integer.parseInt(token3[0]);
						friend64 = Long.parseLong(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[MOREINFO]")) {
					ReadMode = 1;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 3;
				} else if (line.equals("[FRIENDS]")) {
					ReadMode = 4;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 5;
				} else if (line.equals("[HIDDEN]")) {
					ReadMode = 6;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return 1;
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
		return 0;
	}

	public boolean savemoreinfo() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./moreinfo/" + playerName + ".txt"));
			characterfile.write("[MOREINFO]", 0, 10);
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(connectedFrom, 0, connectedFrom.length());
			characterfile.newLine();
			characterfile.write("character-lastlogintime = ", 0, 26);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-ancients = ", 0, 21);
			characterfile.write(Integer.toString(ancients), 0, Integer.toString(ancients).length());
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();

				characterfile.write("character-head = ", 0, 17);
				characterfile.write(Integer.toString(pHead), 0, Integer.toString(pHead).length());
				characterfile.newLine();
				characterfile.write("character-torso = ", 0, 18);
				characterfile.write(Integer.toString(pTorso), 0, Integer.toString(pTorso).length());
				characterfile.newLine();
				characterfile.write("character-arms = ", 0, 17);
				characterfile.write(Integer.toString(pArms), 0, Integer.toString(pArms).length());
				characterfile.newLine();
				characterfile.write("character-hands = ", 0, 18);
				characterfile.write(Integer.toString(pHands), 0, Integer.toString(pHands).length());
				characterfile.newLine();
				characterfile.write("character-legs = ", 0, 17);
				characterfile.write(Integer.toString(pLegs), 0, Integer.toString(pLegs).length());
				characterfile.newLine();
				characterfile.write("character-feet = ", 0, 17);
				characterfile.write(Integer.toString(pFeet), 0, Integer.toString(pFeet).length());
				characterfile.newLine();
				characterfile.write("character-beard = ", 0, 18);
				characterfile.write(Integer.toString(pBeard), 0, Integer.toString(pBeard).length());
				characterfile.newLine();
				characterfile.newLine();

			}
			characterfile.newLine();
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			println(playerName + ": error writing file.");
			return false;
		}
		return true;
	}

	public int loadGame(String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		String FTPAdress = "ftp://whitescape:password@81.165.211.142:2500";
		int World = GetWorld(playerId);
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/" + playerName + ".txt"));
			File1 = true;
		} catch (FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader(FTPAdress + "/" + playerName + ".txt"));
			File2 = true;
		} catch (FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File("./characters/" + playerName + ".txt");
			File myfile2 = new File(FTPAdress + "/" + playerName + ".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			println(playerName + ": character file not found.");
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			println(playerName + ": error loading file.");
			return 3;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-username")) {
						if (playerName.equalsIgnoreCase(token2)) {
						} else {
							return 2;
						}
					} else if (token.equals("character-password")) {
						if (playerPass.equalsIgnoreCase(token2)) {
						} else {
							return 2;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						teleportToX = Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						teleportToY = Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						playerRights = Integer.parseInt(token2);
					} else if (token.equals("character-ismember")) {
						playerIsMember = Integer.parseInt(token2);
					} else if (token.equals("character-messages")) {
						playerMessages = Integer.parseInt(token2);
					} else if (token.equals("character-lastconnection")) {
						playerLastConnect = token2;
					} else if (token.equals("character-lastlogin")) {
						playerLastLogin = Integer.parseInt(token2);
					} else if (token.equals("character-gametime")) {
						playerGameTime = Integer.parseInt(token2);
					} else if (token.equals("character-gamecount")) {
						playerGameCount = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				case 9:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {
					ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {
					ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {
					ReadMode = 3;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {
					ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {
					ReadMode = 6;
				} else if (line.equals("[BANK]")) {
					ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {
					ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 9;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return 1;
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
		return 3;
	}

	public boolean savechar() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./characters/" + playerName + ".txt"));
			/* ACCOUNT */
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
			/* CHARACTER */
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0, Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0, Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(playerRights), 0, Integer.toString(playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(playerIsMember), 0, Integer.toString(playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(playerMessages), 0, Integer.toString(playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(playerLastConnect, 0, playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(playerGameTime), 0, Integer.toString(playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(playerGameCount), 0, Integer.toString(playerGameCount).length());
			characterfile.newLine();
			characterfile.newLine();
			/* EQUIPMENT */
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0,
						Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0,
						Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			/* LOOK */
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* SKILLS */
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0, Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* ITEMS */
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0, Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0,
							Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* BANK */
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0, Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0, Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* FRIENDS */
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* IGNORES */
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* EOF */
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			println(playerName + ": error writing file.");
			return false;
		}
		return true;
	}

	public int autoers() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./data/autoers.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				return Integer.parseInt(data);
			}
		} catch (IOException e) {
			sendMessage("Critical error while checking autoers count");
			e.printStackTrace();
		}
		return -1;
	}

	public int checkbannedusers() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./data/bannedusers.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(data)) {
					return 5;
				}
			}
		} catch (IOException e) {
			println("Critical error while checking banned users!");
			e.printStackTrace();
		}
		return 0;
	}

	public int checkbannedips() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./data/bannedips.txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (connectedFrom.equalsIgnoreCase(data)) {
					return 5;
				}
			}
		} catch (IOException e) {
			println("Critical error while checking banned ips!");
			e.printStackTrace();
		}
		return 0;
	}

	public void logCrashing() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("logs/crashlogs.txt", true));
			bw.write(playerName + " tried to crash " + ServerCP.Servername + ".");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					println("Error logging crashing!");
				}
		}
	}
}
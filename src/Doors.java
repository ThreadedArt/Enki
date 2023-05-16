//Created By: Joey
//Email: Haxsoft@hotmail.com
//File Name: Doors.java
//Information: Handles The Doors around the server

public class Doors {

	public void Doors1(int objectID, int objectX, int objectY, int face, int face2, int GateID, int playerId) {

		client c = (client) server.playerHandler.players[playerId];
		switch (objectID) {

		case 11625:
			if (objectX == 3078 && objectY == 3436) {
				c.makeGlobalObject(3078, 3436, 6951, 0, 0);
				c.makeGlobalObject(3078, 3435, 11621, -3, 0);
				c.makeGlobalObject(3079, 3436, 6951, 0, 0);
				c.makeGlobalObject(3079, 3435, 11620, -3, 0);
			}
			break;
		case 11624:
			if (objectX == 3079 && objectY == 3436) {
				c.makeGlobalObject(3078, 3436, 6951, 0, 0);
				c.makeGlobalObject(3078, 3435, 11621, -3, 0);
				c.makeGlobalObject(3079, 3436, 6951, 0, 0);
				c.makeGlobalObject(3079, 3435, 11620, -3, 0);
			}
			break;

		case 11621:
			if (objectX == 3078 && objectY == 3435) {
				c.makeGlobalObject(3078, 3435, 6951, 0, 0);
				c.makeGlobalObject(3078, 3436, 11625, -0, 0);
				c.makeGlobalObject(3079, 3435, 6951, 0, 0);
				c.makeGlobalObject(3079, 3436, 11624, -2, 0);
			}
			break;
		case 11620:
			if (objectX == 3079 && objectY == 3435) {
				c.makeGlobalObject(3078, 3435, 6951, 0, 0);
				c.makeGlobalObject(3078, 3436, 11625, -0, 0);
				c.makeGlobalObject(3079, 3435, 6951, 0, 0);
				c.makeGlobalObject(3079, 3436, 11624, -2, 0);
			}
			break;

		case 11616:
			if (objectX == 3082 && objectY == 3426) {
				c.makeGlobalObject(3082, 3426, 6951, 0, 0);
				c.makeGlobalObject(3082, 3427, 11617, 0, 0);
			}
			if (objectX == 3098 && objectY == 3426) {
				c.makeGlobalObject(3098, 3426, 6951, 0, 0);
				c.makeGlobalObject(3098, 3427, 11617, 0, 0);
			}
			if (objectX == 3076 && objectY == 3427) {
				c.makeGlobalObject(3076, 3427, 6951, 0, 0);
				c.makeGlobalObject(3076, 3427, 11617, 2, 0);
			}
			break;

		case 11617:
			if (objectX == 3082 && objectY == 3427) {
				c.makeGlobalObject(3082, 3427, 6951, 0, 0);
				c.makeGlobalObject(3082, 3426, 11616, 1, 0);
			}
			if (objectX == 3098 && objectY == 3427) {
				c.makeGlobalObject(3098, 3427, 6951, 0, 0);
				c.makeGlobalObject(3098, 3426, 11616, 1, 0);
			}
			if (objectX == 3076 && objectY == 3427) {
				c.makeGlobalObject(3076, 3427, 6951, 0, 0);
				c.makeGlobalObject(3076, 3427, 11616, 3, 0);
			}
			break;

		case 1516:
			if (objectX == 3281 && objectY == 3507) {
				c.makeGlobalObject(3281, 3507, 6951, 0, 0);
				c.makeGlobalObject(3281, 3506, 1517, -2, 0);
				c.makeGlobalObject(3280, 3507, 6951, 0, 0);
				c.makeGlobalObject(3280, 3506, 1520, -0, 0);
			}

			if (objectX == 3215 && objectY == 3494) {

				c.makeGlobalObject(3215, 3495, 6951, 0, 0);
				c.makeGlobalObject(3214, 3495, 1520, -3, 0);
				c.makeGlobalObject(3215, 3494, 6951, 0, 0);
				c.makeGlobalObject(3214, 3494, 1517, -1, 0);
			}

			if (objectX == 3217 && objectY == 3218) {
				c.makeGlobalObject(3217, 3218, 6951, 0, 0);
				c.makeGlobalObject(3216, 3218, 1517, -1, 0);
				c.makeGlobalObject(3217, 3219, 6951, 0, 0);
				c.makeGlobalObject(3216, 3219, 1520, -3, 0);
			}

			if (objectX == 3244 && objectY == 3216) {
				c.makeGlobalObject(3243, 3216, 6951, 0, 0);
				c.makeGlobalObject(3243, 3216, 1517, 0, 0);
				c.makeGlobalObject(3244, 3216, 6951, 0, 0);
				c.makeGlobalObject(3244, 3216, 1520, 2, 0);
			}
			if (objectX == 3212 && objectY == 3470) {
				c.makeGlobalObject(3213, 3470, 6951, 0, 0);
				c.makeGlobalObject(3213, 3471, 1520, 2, 0);
				c.makeGlobalObject(3212, 3470, 6951, 0, 0);
				c.makeGlobalObject(3212, 3471, 1517, 0, 0);
			}

			break;

		case 1519:
			if (objectX == 3280 && objectY == 3507) {
				c.makeGlobalObject(3281, 3507, 6951, 0, 0);
				c.makeGlobalObject(3281, 3506, 1517, -2, 0);
				c.makeGlobalObject(3280, 3507, 6951, 0, 0);
				c.makeGlobalObject(3280, 3506, 1520, -0, 0);
			}

			if (objectX == 3215 && objectY == 3495) {

				c.makeGlobalObject(3215, 3495, 6951, 0, 0);
				c.makeGlobalObject(3214, 3495, 1520, -3, 0);
				c.makeGlobalObject(3215, 3494, 6951, 0, 0);
				c.makeGlobalObject(3214, 3494, 1517, -1, 0);
			}

			if (objectX == 3217 && objectY == 3219) {
				c.makeGlobalObject(3217, 3218, 6951, 0, 0);
				c.makeGlobalObject(3216, 3218, 1517, -1, 0);
				c.makeGlobalObject(3217, 3219, 6951, 0, 0);
				c.makeGlobalObject(3216, 3219, 1520, -3, 0);
			}

			if (objectX == 3243 && objectY == 3216) {
				c.makeGlobalObject(3243, 3216, 6951, 0, 0);
				c.makeGlobalObject(3243, 3216, 1517, 0, 0);
				c.makeGlobalObject(3244, 3216, 6951, 0, 0);
				c.makeGlobalObject(3244, 3216, 1520, 2, 0);
			}
			if (objectX == 3213 && objectY == 3470) {
				c.makeGlobalObject(3213, 3470, 6951, 0, 0);
				c.makeGlobalObject(3213, 3471, 1520, 2, 0);
				c.makeGlobalObject(3212, 3470, 6951, 0, 0);
				c.makeGlobalObject(3212, 3471, 1517, 0, 0);
			}

			break;

		case 1517:

			if (objectX == 3281 && objectY == 3506) {
				c.makeGlobalObject(3281, 3506, 6951, 0, 0);
				c.makeGlobalObject(3281, 3507, 1516, -1, 0);
				c.makeGlobalObject(3280, 3506, 6951, 0, 0);
				c.makeGlobalObject(3280, 3507, 1519, -1, 0);
			}

			if (objectX == 3216 && objectY == 3218) {
				c.makeGlobalObject(3216, 3218, 6951, 0, 0);
				c.makeGlobalObject(3217, 3218, 1516, -0, 0);
				c.makeGlobalObject(3216, 3219, 6951, 0, 0);
				c.makeGlobalObject(3217, 3219, 1519, -0, 0);
			}

			if (objectX == 3243 && objectY == 3216) {
				c.makeGlobalObject(3243, 3216, 6951, 0, 0);
				c.makeGlobalObject(3243, 3216, 1519, 3, 0);
				c.makeGlobalObject(3244, 3216, 6951, 0, 0);
				c.makeGlobalObject(3244, 3216, 1516, 3, 0);
			}

			if (objectX == 3214 && objectY == 3494) {

				c.makeGlobalObject(3214, 3495, 6951, 0, 0);
				c.makeGlobalObject(3215, 3495, 1519, -0, 0);
				c.makeGlobalObject(3214, 3494, 6951, 0, 0);
				c.makeGlobalObject(3215, 3494, 1516, -0, 0);
			}
			if (objectX == 3212 && objectY == 3471) {
				c.makeGlobalObject(3213, 3471, 6951, 0, 0);
				c.makeGlobalObject(3213, 3470, 1519, 1, 0);
				c.makeGlobalObject(3212, 3471, 6951, 0, 0);
				c.makeGlobalObject(3212, 3470, 1516, 1, 0);
			}

			break;
		case 1520:
			if (objectX == 3280 && objectY == 3506) {
				c.makeGlobalObject(3281, 3506, 6951, 0, 0);
				c.makeGlobalObject(3281, 3507, 1516, -1, 0);
				c.makeGlobalObject(3280, 3506, 6951, 0, 0);
				c.makeGlobalObject(3280, 3507, 1519, -1, 0);
			}
			if (objectX == 3216 && objectY == 3219) {
				c.makeGlobalObject(3216, 3218, 6951, 0, 0);
				c.makeGlobalObject(3217, 3218, 1516, -0, 0);
				c.makeGlobalObject(3216, 3219, 6951, 0, 0);
				c.makeGlobalObject(3217, 3219, 1519, -0, 0);
			}
			if (objectX == 3244 && objectY == 3216) {
				c.makeGlobalObject(3243, 3216, 6951, 0, 0);
				c.makeGlobalObject(3243, 3216, 1519, 3, 0);
				c.makeGlobalObject(3244, 3216, 6951, 0, 0);
				c.makeGlobalObject(3244, 3216, 1516, 3, 0);
			}

			if (objectX == 3214 && objectY == 3495) {

				c.makeGlobalObject(3214, 3495, 6951, 0, 0);
				c.makeGlobalObject(3215, 3495, 1519, -0, 0);
				c.makeGlobalObject(3214, 3494, 6951, 0, 0);
				c.makeGlobalObject(3215, 3494, 1516, -0, 0);
			}

			if (objectX == 3213 && objectY == 3471) {
				c.makeGlobalObject(3213, 3471, 6951, 0, 0);
				c.makeGlobalObject(3213, 3470, 1519, 1, 0);
				c.makeGlobalObject(3212, 3471, 6951, 0, 0);
				c.makeGlobalObject(3212, 3470, 1516, 1, 0);
			}
			break;

		case 1553:

			if (objectX == 3253 && objectY == 3267) {
				c.makeGlobalObject(3253, 3266, 6951, 0, 0);
				c.makeGlobalObject(3251, 3267, 1552, 1, 0);
				c.makeGlobalObject(3253, 3267, 6951, 0, 0);
				c.makeGlobalObject(3252, 3267, 1554, 1, 0);
			}
			if (objectX == 3213 && objectY == 3262) {
				c.makeGlobalObject(3213, 3261, 6951, 0, 0);
				c.makeGlobalObject(3211, 3262, 1552, 1, 0);
				c.makeGlobalObject(3213, 3262, 6951, 0, 0);
				c.makeGlobalObject(3212, 3262, 1554, 1, 0);
			}

			if (objectX == 3236 && objectY == 3284) {
				c.makeGlobalObject(3236, 3284, 6951, 0, 0);
				c.makeGlobalObject(3238, 3285, 1554, 1, 0);
				c.makeGlobalObject(3236, 3285, 6951, 0, 0);
				c.makeGlobalObject(3237, 3285, 1552, 1, 0);
			}

			if (objectX == 3236 && objectY == 3295) {
				c.makeGlobalObject(3236, 3295, 6951, 0, 0);
				c.makeGlobalObject(3238, 3296, 1554, 1, 0);
				c.makeGlobalObject(3236, 3296, 6951, 0, 0);
				c.makeGlobalObject(3237, 3296, 1552, 1, 0);
			}

			if (objectX == 3241 && objectY == 3302) {
				c.makeGlobalObject(3241, 3301, 6951, 0, 0);
				c.makeGlobalObject(3239, 3302, 1552, 1, 0);
				c.makeGlobalObject(3241, 3302, 6951, 0, 0);
				c.makeGlobalObject(3240, 3302, 1554, 1, 0);
			}

			break;

		case 1551:
			if (objectX == 3241 && objectY == 3301) {
				c.makeGlobalObject(3241, 3301, 6951, 0, 0);
				c.makeGlobalObject(3239, 3302, 1552, 1, 0);
				c.makeGlobalObject(3241, 3302, 6951, 0, 0);
				c.makeGlobalObject(3240, 3302, 1554, 1, 0);
			}

			if (objectX == 3236 && objectY == 3285) {
				c.makeGlobalObject(3236, 3284, 6951, 0, 0);
				c.makeGlobalObject(3238, 3285, 1554, 1, 0);
				c.makeGlobalObject(3236, 3285, 6951, 0, 0);
				c.makeGlobalObject(3237, 3285, 1552, 1, 0);
			}
			if (objectX == 3236 && objectY == 3296) {
				c.makeGlobalObject(3236, 3295, 6951, 0, 0);
				c.makeGlobalObject(3238, 3296, 1554, 1, 0);
				c.makeGlobalObject(3236, 3296, 6951, 0, 0);
				c.makeGlobalObject(3237, 3296, 1552, 1, 0);
			}

			if (objectX == 3213 && objectY == 3261) {
				c.makeGlobalObject(3213, 3261, 6951, 0, 0);
				c.makeGlobalObject(3211, 3262, 1552, 1, 0);
				c.makeGlobalObject(3213, 3262, 6951, 0, 0);
				c.makeGlobalObject(3212, 3262, 1554, 1, 0);
			}

			if (objectX == 3253 && objectY == 3266) {
				c.makeGlobalObject(3253, 3266, 6951, 0, 0);
				c.makeGlobalObject(3251, 3267, 1552, 1, 0);
				c.makeGlobalObject(3253, 3267, 6951, 0, 0);
				c.makeGlobalObject(3252, 3267, 1554, 1, 0);
			}

			break;

		case 1552:

			if (objectX == 3251 && objectY == 3267) {
				c.makeGlobalObject(3251, 3267, 6951, 0, 0);
				c.makeGlobalObject(3253, 3267, 1553, 0, 0);
				c.makeGlobalObject(3252, 3267, 6951, 0, 0);
				c.makeGlobalObject(3253, 3266, 1551, 0, 0);
			}

			if (objectX == 3237 && objectY == 3285) {
				c.makeGlobalObject(3237, 3285, 6951, 0, 0);
				c.makeGlobalObject(3236, 3285, 1551, 2, 0);
				c.makeGlobalObject(3238, 3285, 6951, 0, 0);
				c.makeGlobalObject(3236, 3284, 1553, 2, 0);
			}

			if (objectX == 3237 && objectY == 3296) {
				c.makeGlobalObject(3237, 3296, 6951, 0, 0);
				c.makeGlobalObject(3236, 3296, 1551, 2, 0);
				c.makeGlobalObject(3238, 3296, 6951, 0, 0);
				c.makeGlobalObject(3236, 3295, 1553, 2, 0);
			}

			if (objectX == 3239 && objectY == 3302) {
				c.makeGlobalObject(3239, 3302, 6951, 0, 0);
				c.makeGlobalObject(3241, 3302, 1553, 0, 0);
				c.makeGlobalObject(3240, 3302, 6951, 0, 0);
				c.makeGlobalObject(3241, 3301, 1551, 0, 0);
			}

			if (objectX == 3211 && objectY == 3262) {
				c.makeGlobalObject(3211, 3262, 6951, 0, 0);
				c.makeGlobalObject(3213, 3262, 1553, 0, 0);
				c.makeGlobalObject(3212, 3262, 6951, 0, 0);
				c.makeGlobalObject(3213, 3261, 1551, 0, 0);
			}
			break;

		case 312:
			c.addItem(1942, 1);
			break;

		case 1512: // Open Large Door

			if (objectX == 3223 && objectY == 3491) {
				c.makeGlobalObject(3223, 3491, 6951, 0, 0);
				c.makeGlobalObject(3223, 3491, 1513, 0, 0);
			}
			if (objectX == 3209 && objectY == 3490) {
				c.makeGlobalObject(3209, 3490, 6951, 0, 0);
				c.makeGlobalObject(3209, 3490, 1513, 0, 0);
			}
			if (objectX == 3225 && objectY == 3493) {
				c.makeGlobalObject(3225, 3493, 6951, 0, 0);
				c.makeGlobalObject(3224, 3493, 1513, 1, 0);
			}

			if (objectX == 3219 && objectY == 3472) {
				c.makeGlobalObject(3219, 3472, 6951, 0, 0);
				c.makeGlobalObject(3218, 3472, 1513, 1, 0);
			}

			break;
		case 1514:
		case 1513: // close large door
			if (objectX == 3224 && objectY == 3493) {
				c.makeGlobalObject(3224, 3493, 6951, 0, 0);
				c.makeGlobalObject(3225, 3493, 1512, 0, 0);
			}
			if (objectX == 3218 && objectY == 3472) {
				c.makeGlobalObject(3218, 3472, 6951, 0, 0);
				c.makeGlobalObject(3219, 3472, 1512, 0, 0);
			}
			if (objectX == 3223 && objectY == 3491) {
				c.makeGlobalObject(3223, 3491, 6951, 0, 0);
				c.makeGlobalObject(3223, 3491, 1512, 3, 0);
			}
			if (objectX == 3209 && objectY == 3490) {
				c.makeGlobalObject(3209, 3490, 6951, 0, 0);
				c.makeGlobalObject(3209, 3490, 1512, 3, 0);
			}
			break;

		case 1537: // close door
			if (objectX == 3215 && objectY == 3211) {
				c.makeGlobalObject(3215, 3211, 6951, 0, 0);
				c.makeGlobalObject(3215, 3211, 1536, 1, 0);
			}
			if (objectX == 3215 && objectY == 3225) {
				c.makeGlobalObject(3215, 3225, 6951, 0, 0);
				c.makeGlobalObject(3215, 3225, 1536, 1, 0);
			}
			if (objectX == 3219 && objectY == 3472) {
				c.makeGlobalObject(3219, 3472, 6951, 0, 0);
				c.makeGlobalObject(3219, 3472, 1536, 0, 0);
			}
			if (objectX == 3219 && objectY == 3473) {
				c.makeGlobalObject(3219, 3473, 6951, 0, 0);
				c.makeGlobalObject(3219, 3473, 1536, 0, 0);
			}
			if (objectX == 3215 && objectY == 3477) {
				c.makeGlobalObject(3215, 3477, 6951, 0, 0);
				c.makeGlobalObject(3215, 3477, 1536, 3, 0);
			}
			if (objectX == 3223 && objectY == 3479) {
				c.makeGlobalObject(3223, 3479, 6951, 0, 0);
				c.makeGlobalObject(3223, 3479, 1536, 3, 0);
			}
			if (objectX == 3214 && objectY == 3486)// North
			{
				c.makeGlobalObject(3214, 3486, 6951, 0, 0);
				c.makeGlobalObject(3214, 3486, 1536, 1, 0);
			}
			if (objectX == 3217 && objectY == 3488)// East
			{
				c.makeGlobalObject(3217, 3488, 6951, 0, 0);
				c.makeGlobalObject(3217, 3488, 1536, 2, 0);
			}
			if (objectX == 3206 && objectY == 3472) {
				c.makeGlobalObject(3206, 3472, 6951, 0, 0);
				c.makeGlobalObject(3206, 3472, 1536, 2, 0);
			}
			if (objectX == 3204 && objectY == 3478) {
				c.makeGlobalObject(3204, 3478, 6951, 0, 0);
				c.makeGlobalObject(3204, 3478, 1536, 2, 0);
			}
			if (objectX == 3204 && objectY == 3485) {
				c.makeGlobalObject(3204, 3485, 6951, 0, 0);
				c.makeGlobalObject(3204, 3485, 1536, 2, 0);
			}
			if (objectX == 3207 && objectY == 3222) {
				c.makeGlobalObject(3207, 3222, 6951, 0, 0);
				c.makeGlobalObject(3207, 3222, 1536, 2, 0);
			}

			if (objectX == 3207 && objectY == 3214) {
				c.makeGlobalObject(3207, 3214, 6951, 0, 0);
				c.makeGlobalObject(3207, 3214, 1536, 2, 0);
			}

			break;

		case 1536: // open door
			if (objectX == 3204 && objectY == 3478) {
				c.makeGlobalObject(3204, 3478, 6951, 0, 0);
				c.makeGlobalObject(3204, 3478, 1537, 1, 0);
			}
			if (objectX == 3204 && objectY == 3485) {
				c.makeGlobalObject(3204, 3485, 6951, 0, 0);
				c.makeGlobalObject(3204, 3485, 1537, 1, 0);
			}
			if (objectX == 3206 && objectY == 3472) {
				c.makeGlobalObject(3206, 3472, 6951, 0, 0);
				c.makeGlobalObject(3206, 3472, 1537, 1, 0);
			}
			if (objectX == 3217 && objectY == 3488)// East
			{
				c.makeGlobalObject(3217, 3488, 6951, 0, 0);
				c.makeGlobalObject(3217, 3488, 1537, 3, 0);
			}

			if (objectX == 3215 && objectY == 3477)// North
			{
				c.makeGlobalObject(3215, 3477, 6951, 0, 0);
				c.makeGlobalObject(3215, 3477, 1537, 0, 0);
			}
			if (objectX == 3214 && objectY == 3486)// North
			{
				c.makeGlobalObject(3214, 3486, 6951, 0, 0);
				c.makeGlobalObject(3214, 3486, 1537, 0, 0);
			}
			if (objectX == 3223 && objectY == 3479) {
				c.makeGlobalObject(3223, 3479, 6951, 0, 0);
				c.makeGlobalObject(3223, 3479, 1537, 0, 0);
			}
			if (objectX == 3219 && objectY == 3472) {
				c.makeGlobalObject(3219, 3472, 6951, 0, 0);
				c.makeGlobalObject(3219, 3472, 1537, 1, 0);
			}
			if (objectX == 3219 && objectY == 3473) {
				c.makeGlobalObject(3219, 3473, 6951, 0, 0);
				c.makeGlobalObject(3219, 3473, 1537, 1, 0);
			}
			if (objectX == 3207 && objectY == 3222) {
				c.makeGlobalObject(3207, 3222, 6951, 0, 0);
				c.makeGlobalObject(3207, 3222, 1537, 1, 0);
			}

			if (objectX == 3207 && objectY == 3214) {
				c.makeGlobalObject(3207, 3214, 6951, 0, 0);
				c.makeGlobalObject(3207, 3214, 1537, 1, 0);
			}
			if (objectX == 3215 && objectY == 3211) {
				c.makeGlobalObject(3215, 3211, 6951, 0, 0);
				c.makeGlobalObject(3215, 3211, 1537, 0, 0);
			}
			if (objectX == 3215 && objectY == 3225) {
				c.makeGlobalObject(3215, 3225, 6951, 0, 0);
				c.makeGlobalObject(3215, 3225, 1537, 0, 0);
			}
			break;

		case 1530:
			if (objectX == 3216 && objectY == 3413) {
				c.makeGlobalObject(3216, 3413, 6951, 0, 0);
				c.makeGlobalObject(3216, 3412, 1531, 0, 0);
			}

			if (objectX == 3207 && objectY == 3473) {
				c.makeGlobalObject(3207, 3473, 6951, 0, 0);
				c.makeGlobalObject(3206, 3473, 1531, 1, 0);
			}
			if (objectX == 3211 && objectY == 3468) {
				c.makeGlobalObject(3211, 3468, 6951, 0, 0);
				c.makeGlobalObject(3210, 3468, 1531, 1, 0);
			}

			if (objectX == 3204 && objectY == 3488) {
				c.makeGlobalObject(3204, 3488, 6951, 0, 0);
				c.makeGlobalObject(3203, 3488, 1531, 1, 0);
			}

			if (objectX == 3226 && objectY == 3223) {
				c.makeGlobalObject(3226, 3223, 6951, 0, 0);
				c.makeGlobalObject(3227, 3223, 1531, 3, 0);
			}

			if (objectX == 3226 && objectY == 3214) {
				c.makeGlobalObject(3226, 3214, 6951, 0, 0);
				c.makeGlobalObject(3227, 3214, 1531, 3, 0);
			}

			if (objectX == 3208 && objectY == 3211) {
				c.makeGlobalObject(3208, 3211, 6951, 0, 0);
				c.makeGlobalObject(3208, 3212, 1531, 2, 0);
			}
			if (objectX == 3207 && objectY == 3210) {
				c.makeGlobalObject(3207, 3210, 6951, 0, 0);
				c.makeGlobalObject(3208, 3210, 1531, 3, 0);
			}
			if (objectX == 3207 && objectY == 3227) {
				c.makeGlobalObject(3207, 3227, 6951, 0, 0);
				c.makeGlobalObject(3208, 3227, 1531, 3, 0);
			}
			if (objectX == 3234 && objectY == 3207) {
				c.makeGlobalObject(3234, 3207, 6951, 0, 0);
				c.makeGlobalObject(3233, 3207, 1531, 1, 0);
			}
			if (objectX == 3235 && objectY == 3198) {
				c.makeGlobalObject(3235, 3198, 6951, 0, 0);
				c.makeGlobalObject(3235, 3199, 1531, 2, 0);
			}
			if (objectX == 3247 && objectY == 3193) {
				c.makeGlobalObject(3247, 3193, 6951, 0, 0);
				c.makeGlobalObject(3246, 3193, 1531, 1, 0);
			}
			if (objectX == 3230 && objectY == 3235) {
				c.makeGlobalObject(3230, 3235, 6951, 0, 0);
				c.makeGlobalObject(3230, 3236, 1531, 2, 0);
			}
			if (objectX == 3228 && objectY == 3240) {
				c.makeGlobalObject(3228, 3240, 6951, 0, 0);
				c.makeGlobalObject(3229, 3240, 1531, 3, 0);
			}
			if (objectX == 3246 && objectY == 3244) {
				c.makeGlobalObject(3246, 3244, 6951, 0, 0);
				c.makeGlobalObject(3246, 3243, 1531, 0, 0);
			}
			if (objectX == 3217 && objectY == 3492) {
				c.makeGlobalObject(3217, 3492, 6951, 0, 0);
				c.makeGlobalObject(3218, 3492, 1531, 3, 0);
			}

			if (objectX == 3202 && objectY == 3475) {
				c.makeGlobalObject(3202, 3475, 6951, 0, 0);
				c.makeGlobalObject(3202, 3474, 1531, 0, 0);
			}

			if (objectX == 3216 && objectY == 3489) {
				c.makeGlobalObject(3216, 3489, 6951, 0, 0);
				c.makeGlobalObject(3216, 3490, 1531, 2, 0);
			}

			if (objectX == 3221 && objectY == 3492) {
				c.makeGlobalObject(3221, 3492, 6951, 0, 0);
				c.makeGlobalObject(3220, 3492, 1531, 1, 0);
			}

			if (objectX == 3221 && objectY == 3496) {
				c.makeGlobalObject(3221, 3496, 6951, 0, 0);
				c.makeGlobalObject(3220, 3496, 1531, 1, 0);
			}

			if (objectX == 3234 && objectY == 3203) {
				c.makeGlobalObject(3234, 3203, 6951, 0, 0);
				c.makeGlobalObject(3233, 3203, 1531, 1, 0);
			}
			if (objectX == 3096 && objectY == 3429) {
				c.makeGlobalObject(3096, 3429, 6951, 0, 0);
				c.makeGlobalObject(3096, 3430, 1531, 2, 0);
			}

			break;

		case 1531:
			if (objectX == 3096 && objectY == 3430) {
				c.makeGlobalObject(3096, 3430, 6951, 0, 0);
				c.makeGlobalObject(3096, 3429, 1530, 1, 0);
			}
			if (objectX == 3216 && objectY == 3412) {
				c.makeGlobalObject(3216, 3412, 6951, 0, 0);
				c.makeGlobalObject(3216, 3413, 1530, 3, 0);
			}
			if (objectX == 3246 && objectY == 3243) {
				c.makeGlobalObject(3246, 3243, 6951, 0, 0);
				c.makeGlobalObject(3246, 3244, 1530, 3, 0);
			}
			if (objectX == 3208 && objectY == 3210) {
				c.makeGlobalObject(3208, 3210, 6951, 0, 0);
				c.makeGlobalObject(3207, 3210, 1530, 2, 0);
			}
			if (objectX == 3208 && objectY == 3227) {
				c.makeGlobalObject(3208, 3227, 6951, 0, 0);
				c.makeGlobalObject(3207, 3227, 1530, 2, 0);
			}

			if (objectX == 3208 && objectY == 3212) {
				c.makeGlobalObject(3208, 3212, 6951, 0, 0);
				c.makeGlobalObject(3208, 3211, 1530, 1, 0);
			}
			if (objectX == 3227 && objectY == 3223) {
				c.makeGlobalObject(3227, 3223, 6951, 0, 0);
				c.makeGlobalObject(3226, 3223, 1530, 2, 0);
			}
			if (objectX == 3227 && objectY == 3214) {
				c.makeGlobalObject(3227, 3214, 6951, 0, 0);
				c.makeGlobalObject(3226, 3214, 1530, 2, 0);
			}

			if (objectX == 3246 && objectY == 3193) {
				c.makeGlobalObject(3246, 3193, 6951, 0, 0);
				c.makeGlobalObject(3247, 3193, 1530, 0, 0);
			}

			if (objectX == 3233 && objectY == 3207) {
				c.makeGlobalObject(3233, 3207, 6951, 0, 0);
				c.makeGlobalObject(3234, 3207, 1530, 0, 0);
			}
			if (objectX == 3235 && objectY == 3199) {
				c.makeGlobalObject(3235, 3199, 6951, 0, 0);
				c.makeGlobalObject(3235, 3198, 1530, 1, 0);
			}

			if (objectX == 3233 && objectY == 3203) {
				c.makeGlobalObject(3233, 3203, 6951, 0, 0);
				c.makeGlobalObject(3234, 3203, 1530, 0, 0);
			}

			if (objectX == 3220 && objectY == 3492) {
				c.makeGlobalObject(3220, 3492, 6951, 0, 0);
				c.makeGlobalObject(3221, 3492, 1530, 0, 0);
			}
			if (objectX == 3220 && objectY == 3496) {
				c.makeGlobalObject(3220, 3496, 6951, 0, 0);
				c.makeGlobalObject(3221, 3496, 1530, 0, 0);
			}

			if (objectX == 3216 && objectY == 3490) {
				c.makeGlobalObject(3216, 3490, 6951, 0, 0);
				c.makeGlobalObject(3216, 3489, 1530, 1, 0);
			}

			if (objectX == 3202 && objectY == 3474) {
				c.makeGlobalObject(3202, 3474, 6951, 0, 0);
				c.makeGlobalObject(3202, 3475, 1530, 3, 0);
			}

			if (objectX == 3206 && objectY == 3473) {
				c.makeGlobalObject(3206, 3473, 6951, 0, 0);
				c.makeGlobalObject(3207, 3473, 1530, 0, 0);
			}
			if (objectX == 3210 && objectY == 3468) {
				c.makeGlobalObject(3210, 3468, 6951, 0, 0);
				c.makeGlobalObject(3211, 3468, 1530, 0, 0);
			}
			if (objectX == 3203 && objectY == 3488) {
				c.makeGlobalObject(3203, 3488, 6951, 0, 0);
				c.makeGlobalObject(3204, 3488, 1530, 0, 0);
			}
			if (objectX == 3218 && objectY == 3492) {
				c.makeGlobalObject(3218, 3492, 6951, 0, 0);
				c.makeGlobalObject(3217, 3492, 1530, 2, 0);
			}
			if (objectX == 3230 && objectY == 3236) {
				c.makeGlobalObject(3230, 3236, 6951, 0, 0);
				c.makeGlobalObject(3230, 3235, 1530, 1, 0);
			}
			if (objectX == 3229 && objectY == 3240) {
				c.makeGlobalObject(3229, 3240, 6951, 0, 0);
				c.makeGlobalObject(3228, 3240, 1530, 2, 0);
			}
			break;

		case 12348:
			if (objectX == 3207 && objectY == 3217) {
				c.sendMessage("You arn't aloud in here at this time.");
			}
			break;

		case 1533:
			if (objectX == 3238 && objectY == 3210) {
				c.makeGlobalObject(3238, 3210, 6951, 0, 0);
				c.makeGlobalObject(3238, 3210, 1534, 1, 0);
			}
			if (objectX == 3203 && objectY == 3494) {
				c.makeGlobalObject(3203, 3494, 6951, 0, 0);
				c.makeGlobalObject(3203, 3494, 1534, 0, 0);
			}
			if (objectX == 3214 && objectY == 3245) {
				c.makeGlobalObject(3214, 3245, 6951, 0, 0);
				c.makeGlobalObject(3214, 3245, 1534, 1, 0);
			}
			if (objectX == 3280 && objectY == 3488) {
				c.makeGlobalObject(3280, 3488, 6951, 0, 0);
				c.makeGlobalObject(3280, 3488, 1534, 3, 0);
			}
			if (objectX == 3280 && objectY == 3495) {
				c.makeGlobalObject(3280, 3495, 6951, 0, 0);
				c.makeGlobalObject(3280, 3495, 1534, 3, 0);
			}

			if (objectX == 3277 && objectY == 3501) {
				c.makeGlobalObject(3277, 3501, 6951, 0, 0);
				c.makeGlobalObject(3277, 3501, 1534, 0, 0);
			}
			if (objectX == 3284 && objectY == 3501) {
				c.makeGlobalObject(3284, 3501, 6951, 0, 0);
				c.makeGlobalObject(3284, 3501, 1534, 0, 0);
			}

			if (objectX == 3205 && objectY == 3432)// Stickin out North
			{
				c.makeGlobalObject(3205, 3432, 6951, 0, 0);
				c.makeGlobalObject(3205, 3432, 1534, 3, 0);
			}
			if (objectX == 3214 && objectY == 3415)// Stickin out North
			{
				c.makeGlobalObject(3214, 3415, 6951, 0, 0);
				c.makeGlobalObject(3214, 3415, 1534, 3, 0);
			}
			if (objectX == 3164 && objectY == 3433)// Stickin out North
			{
				c.makeGlobalObject(3164, 3433, 6951, 0, 0);
				c.makeGlobalObject(3164, 3433, 1534, 3, 0);
			}

			if (objectX == 3217 && objectY == 3419) // Sticking out east
			{
				c.makeGlobalObject(3217, 3419, 6951, 0, 0);
				c.makeGlobalObject(3217, 3419, 1534, 0, 0);
			}

			break;

		case 1534:
			if (objectX == 3217 && objectY == 3419)// Stickin out North
			{
				c.makeGlobalObject(3217, 3419, 6951, 0, 0);
				c.makeGlobalObject(3217, 3419, 1533, 1, 0);
			}

			if (objectX == 3164 && objectY == 3433) // Sticking out east
			{
				c.makeGlobalObject(3164, 3433, 6951, 0, 0);
				c.makeGlobalObject(3164, 3433, 1533, 2, 0);
			}

			if (objectX == 3205 && objectY == 3432) // Sticking out east
			{
				c.makeGlobalObject(3205, 3432, 6951, 0, 0);
				c.makeGlobalObject(3205, 3432, 1533, 0, 0);
			}
			if (objectX == 3214 && objectY == 3415) // Sticking out east
			{
				c.makeGlobalObject(3214, 3415, 6951, 0, 0);
				c.makeGlobalObject(3214, 3415, 1533, 0, 0);
			}

			if (objectX == 3284 && objectY == 3501) {
				c.makeGlobalObject(3284, 3501, 6951, 0, 0);
				c.makeGlobalObject(3284, 3501, 1533, 1, 0);
			}
			if (objectX == 3277 && objectY == 3501) {
				c.makeGlobalObject(3277, 3501, 6951, 0, 0);
				c.makeGlobalObject(3277, 3501, 1533, 1, 0);
			}

			if (objectX == 3280 && objectY == 3488) {
				c.makeGlobalObject(3280, 3488, 6951, 0, 0);
				c.makeGlobalObject(3280, 3488, 1533, 0, 0);
			}

			if (objectX == 3280 && objectY == 3495) {
				c.makeGlobalObject(3280, 3495, 6951, 0, 0);
				c.makeGlobalObject(3280, 3495, 1533, 0, 0);
			}

			if (objectX == 3214 && objectY == 3245) {
				c.makeGlobalObject(3214, 3245, 6951, 0, 0);
				c.makeGlobalObject(3214, 3245, 1533, 2, 0);
			}

			if (objectX == 3238 && objectY == 3210) {
				c.makeGlobalObject(3238, 3210, 6951, 0, 0);
				c.makeGlobalObject(3238, 3210, 1533, 0, 0);
			}
			if (objectX == 3203 && objectY == 3494) {
				c.makeGlobalObject(3203, 3494, 6951, 0, 0);
				c.makeGlobalObject(3203, 3494, 1533, 3, 0);
			}
			break;

		case 12349:
			if (objectX == 3213 && objectY == 3221) {
				c.sendMessage("You aren't aloud in here at this time.");
			}

		case 12350:
			if (objectX == 3213 && objectY == 3222) {
				c.sendMessage("You aren't aloud in here at this time.");
			}
			break;

		}
	}
}
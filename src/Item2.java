public class Item2 {

	public static int chiken[] = { 379, 379, 379, 379, 379, 379, 385, 379, 379, 379, 379, 385 };

	public static int randomchiken() {
		return chiken[(int) (Math.random() * chiken.length)];
	}

}
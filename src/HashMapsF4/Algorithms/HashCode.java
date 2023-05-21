package HashMapsF4.Algorithms;

public class HashCode<Key> {
	public static <Key> int hashCode(Key key, int size) {
		String hash = key.toString();
		int index = 0;

		for (int i = 0; i < hash.length(); i++) {
			char letter = hash.charAt(i);
			index += letter * i + Math.pow(2, i);
		}

		// Set the index between 0 and size - 1.
		index = index % size;

		return index;
	}
}

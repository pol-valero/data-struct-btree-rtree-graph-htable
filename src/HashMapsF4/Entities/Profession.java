package HashMapsF4.Entities;

public enum Profession {
	MINSTREL,
	KNIGHT,
	KING,
	QUEEN,
	PEASANT,
	SHRUBBER,
	CLERGYMAN,
	ENCHANTER;

	public static Profession stringToEnum(String profession) {

		switch (profession) {
			case "MINSTREL" -> { return MINSTREL; }
			case "KNIGHT" -> { return KNIGHT; }
			case "KING" -> { return KNIGHT; }
			case "QUEEN" -> { return QUEEN; }
			case "PEASANT" -> { return PEASANT; }
			case "SHRUBBER" -> { return SHRUBBER; }
			case "CLERGYMAN" -> { return CLERGYMAN; }
			case "ENCHANTER" -> { return ENCHANTER; }
		}

		return null;
	}
}

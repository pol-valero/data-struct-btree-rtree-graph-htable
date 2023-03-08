package GraphsF1.Entities;

public enum Climate {
	POLAR,
	CONTINENTAL,
	TROPICAL;

	public static Climate stringToEnum(String climate) {
		return switch (climate) {
			case "TROPICAL" -> TROPICAL;
			case "CONTINENTAL" -> CONTINENTAL;
			case "POLAR" -> POLAR;
			default -> null;
		};
	}

	// Transform UPPERCASE climate to Lowercase String.
	public String enumToString() {
		String enumString = this.toString();
		return (enumString.charAt(0) + enumString.substring(1).toLowerCase());
	}
}

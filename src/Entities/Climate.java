package Entities;

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
}

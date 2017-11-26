package tangelo.kata.mars.rover.domain;

public enum Direction {
	NORTH('N'),
	EAST('E'),
	SOUTH('S'),
	WEST('W');
	
	private char value;

	/**
	 * @param value
	 */
	private Direction(char value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public char getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(char value) {
		this.value = value;
	}
	
}

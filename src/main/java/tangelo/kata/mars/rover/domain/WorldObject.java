package tangelo.kata.mars.rover.domain;

public class WorldObject {

	protected Coordinates coordinates;

	/**
	 * @param coordinates
	 */
	public WorldObject(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof WorldObject) {
			WorldObject other = (WorldObject) o;
			if (this.coordinates.isEqualToComparingFieldByField(other.getCoordinates())){
				return true;
			}
		}
		return false;
	}
	
}

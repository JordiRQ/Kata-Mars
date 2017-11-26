package tangelo.kata.mars.rover.domain;

import java.util.Arrays;
import java.util.List;

public class Rover extends WorldObject {

	private boolean reportObstacleFlag;
	private Direction direction;
	private World world;

	/**
	 * @param coordinates
	 * @param direction
	 */
	public Rover(Coordinates coordinates, Direction direction) {
		super(coordinates);
		this.reportObstacleFlag = false;
		this.direction = direction;
	}

	/**
	 * @param coordinates
	 * @param direction
	 * @param world
	 */
	public Rover(Coordinates coordinates, Direction direction, World world) {
		super(coordinates);
		this.reportObstacleFlag = false;
		this.direction = direction;
		this.world = world;
	}

	/**
	 * @return the reportObstacleFlag
	 */
	public boolean isReportObstacleFlag() {
		return reportObstacleFlag;
	}

	/**
	 * @param reportObstacleFlag the reportObstacleFlag to set
	 */
	public void setReportObstacleFlag(boolean reportObstacleFlag) {
		this.reportObstacleFlag = reportObstacleFlag;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @param world the world to set
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	public String getPosition() {
		String output = this.coordinates.toString() + " " + this.direction.getValue();
		if (this.reportObstacleFlag) {
			output = output + " " + World.OBSTACLE_FOUND_MESSAGE;
		}
		return output;
	}
	
	private boolean move(int i) {
		final Coordinates newCoordinates = new Coordinates(this.coordinates.getX(), this.coordinates.getY());
		switch (this.direction.getValue()) {
			case 'N':
				newCoordinates.moveY(i);
				break;
			case 'S':
				newCoordinates.moveY(-i);
				break;
			case 'E':
				newCoordinates.moveX(i);
				break;
			case 'W':
				newCoordinates.moveX(-i);
				break;
			default:
				break;
		}
		this.world.wrapCoordinates(newCoordinates);
		if (this.world.checkObstacles(newCoordinates)) {
			this.reportObstacleFlag = true;
			return true;
		}else{
			this.coordinates = newCoordinates;
			return false;
		}
	}
	
	private void rotate(int i) {
		List<Direction> directions = Arrays.asList(Direction.values());
		int dirPos = directions.indexOf(direction);
		int newDirPos = dirPos + i;
		if (newDirPos <= -1) {
			this.direction = directions.get(directions.size()-1);
		}else if (newDirPos >= directions.size()){
			this.direction = directions.get(0);
		}else{
			this.direction = directions.get(newDirPos);
		}
	}

	public boolean receiveSingleCommand(char c) throws Exception {
		switch (Character.toUpperCase(c)) {
			case 'B':
				return this.move(-1);
			case 'F':
				return this.move(1);
			case 'L':
				this.rotate(-1);
				return false;
			case 'R':
				this.rotate(1);
				return false;
			default:
				World.log.info(World.UNKNOWN_INPUT_EXCEPTION_LOG + "\'" + c + "\'");
				throw new Exception(World.UNKNOWN_INPUT_EXCEPTION);
		}
	}

	public void receiveCommands(String string) throws Exception {
		boolean encounteredObstacles = false;
		for (int i=0;i<string.length() && !encounteredObstacles;i++) {
			char command = string.charAt(i);
			encounteredObstacles = this.receiveSingleCommand(command);
		}
	}
	
}

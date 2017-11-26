package tangelo.kata.mars.rover.domain;

import java.util.List;
import java.util.logging.Logger;

public class World {
	public static final String OBSTACLE_FOUND_MESSAGE = "NOK";
	public static final String UNKNOWN_INPUT_EXCEPTION = "Received unknown command as input, current sequence was interrupted.";
	public static final String UNKNOWN_INPUT_EXCEPTION_LOG = "Unrecognised command ";
	
	public static Logger log = Logger.getLogger(World.class.getName());
	
	private int maxX;
	private int maxY;
	private List<WorldObject> obstacles;	
	private Rover rover;

	/**
	 * @param maxX
	 * @param maxY
	 * @param obstacles
	 * @param rover
	 */
	public World(int maxX, int maxY, List<WorldObject> obstacles, Rover rover) {
		this.maxX = maxX;
		this.maxY = maxY;
		this.obstacles = obstacles;
		this.rover = rover;
	}

	/**
	 * @param maxX
	 * @param maxY
	 * @param obstacles
	 */
	public World(int maxX, int maxY, List<WorldObject> obstacles) {
		this.maxX = maxX;
		this.maxY = maxY;
		this.obstacles = obstacles;
	}

	/**
	 * @return the log
	 */
	public Logger getLog() {
		return log;
	}

	/**
	 * @return the maxX
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * @param maxX the maxX to set
	 */
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	/**
	 * @return the maxY
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * @param maxY the maxY to set
	 */
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	/**
	 * @return the obstacles
	 */
	public List<WorldObject> getObstacles() {
		return obstacles;
	}

	/**
	 * @param obstacles the obstacles to set
	 */
	public void setObstacles(List<WorldObject> obstacles) {
		this.obstacles = obstacles;
	}

	/**
	 * @return the rover
	 */
	public Rover getRover() {
		return rover;
	}

	/**
	 * @param rover the rover to set
	 */
	public void setRover(Rover rover) {
		this.rover = rover;
	}
	
	public boolean checkObstacles(Coordinates coordinates) {
		WorldObject dummy = new WorldObject(coordinates);
		return this.obstacles.contains(dummy);
	}
	
	public void wrapCoordinates(Coordinates coordinates) {
		if (coordinates.getX()<0){
			coordinates.setX(this.maxX);
		}else if (coordinates.getX()>this.maxX){
			coordinates.setX(0);
		}
		if (coordinates.getY()<0){
			coordinates.setY(this.maxY);
		}else if (coordinates.getY()>this.maxY){
			coordinates.setY(0);
		}
	}
}

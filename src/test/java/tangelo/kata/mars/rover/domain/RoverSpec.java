package tangelo.kata.mars.rover.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tangelo.kata.mars.rover.domain.Coordinates;
import tangelo.kata.mars.rover.domain.Direction;
import tangelo.kata.mars.rover.domain.Rover;
import tangelo.kata.mars.rover.domain.World;
import tangelo.kata.mars.rover.domain.WorldObject;

/*
Source: http://dallashackclub.com/rover
Develop an api that moves a rover around on a grid.
* You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
* - The rover receives a character array of commands.
* - Implement commands that move the rover forward/backward (f,b).
* - Implement commands that turn the rover left/right (l,r).
* - Implement wrapping from one edge of the grid to another. (planets are spheres after all)
* - Implement obstacle detection before each move to a new square.
*   If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point and reports the obstacle.
*/
public class RoverSpec {

    private Rover rover;
    private Coordinates roverCoordinates;
    private final Direction direction = Direction.NORTH;
    private List<WorldObject> obstacles;
    private World mars;

    @Before
    public void beforeRoverTest() {
        obstacles = new ArrayList<WorldObject>();
        roverCoordinates = new Coordinates(1, 2);
        rover = new Rover(roverCoordinates, direction);
        mars = new World(9, 9, obstacles);
        rover.setWorld(mars);
        mars.setRover(rover);
    }

    @Test
    public void newInstanceShouldSetRoverCoordinatesAndDirection() {
        assertThat(rover.getCoordinates().isEqualToComparingFieldByField(roverCoordinates));
    }

    @Test
    public void receiveSingleCommandShouldMoveForwardWhenCommandIsF() throws Exception {
        int expected = rover.getCoordinates().getY() + 1;
        rover.receiveSingleCommand('F');
        assertThat(rover.getCoordinates().getY()).isEqualTo(expected);
    }

    @Test
    public void receiveSingleCommandShouldMoveBackwardWhenCommandIsB() throws Exception {
        int expected = rover.getCoordinates().getY() - 1;
        rover.receiveSingleCommand('B');
        assertThat(rover.getCoordinates().getY()).isEqualTo(expected);
    }

    @Test
    public void receiveSingleCommandShouldTurnLeftWhenCommandIsL() throws Exception {
        rover.receiveSingleCommand('L');
        assertThat(rover.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    public void receiveSingleCommandShouldTurnRightWhenCommandIsR() throws Exception {
        rover.receiveSingleCommand('R');
        assertThat(rover.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void receiveSingleCommandShouldIgnoreCase() throws Exception {
        rover.receiveSingleCommand('r');
        assertThat(rover.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test(expected = Exception.class)
    public void receiveSingleCommandShouldThrowExceptionWhenCommandIsUnknown() throws Exception {
        rover.receiveSingleCommand('X');
    }

    @Test
    public void receiveCommandsShouldBeAbleToReceiveMultipleCommands() throws Exception {
        int expected = rover.getCoordinates().getX() + 1;
        rover.receiveCommands("RFR");
        assertThat(rover.getCoordinates().getX()).isEqualTo(expected);
        assertThat(rover.getDirection()).isEqualTo(Direction.SOUTH);
    }

    @Test
    public void receiveCommandShouldWhatFromOneEdgeOfTheGridToAnother() throws Exception {
        int expected = mars.getMaxX() + rover.getCoordinates().getX() - 2;
        rover.receiveCommands("LFFF");
        assertThat(rover.getCoordinates().getX()).isEqualTo(expected);
    }

    @Test
    public void receiveCommandsShouldStopWhenObstacleIsFound() throws Exception {
        int expected = rover.getCoordinates().getX() + 1;
        mars.setObstacles(Arrays.asList(new WorldObject(new Coordinates(expected + 1, rover.getCoordinates().getY()))));
        rover.setDirection(Direction.EAST);
        rover.receiveCommands("FFFRF");
        assertThat(rover.getCoordinates().getX()).isEqualTo(expected);
        assertThat(rover.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void positionShouldReturnXYAndDirection() throws Exception {
        rover.receiveCommands("LFFFRFF");
        assertThat(rover.getPosition()).isEqualTo("8 X 4 N");
    }

    @Test
    public void positionShouldReturnNokWhenObstacleIsFound() throws Exception {
        mars.setObstacles(Arrays.asList(new WorldObject(new Coordinates(rover.getCoordinates().getX() + 1, rover.getCoordinates().getY()))));
        rover.setDirection(Direction.EAST);
        rover.receiveCommands("F");
        assertThat(rover.getPosition()).endsWith(" NOK");
    }

}
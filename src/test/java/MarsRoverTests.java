import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MarsRoverTests {

    @Test
    public void testMoveForwardOneTimeLeftEdgeToRightEdge() throws InvalidCommandException, ObstacleException {

        //Arrange (see README for reference to Arrange-Act-Assert Pattern)
        MarsRover rover = new MarsRoverImpl(new Point2d(0, 3), Direction.WEST);

        //Act
        rover.move("f");

        //Assert
        Assertions.assertEquals(new Point2d(5, 3), rover.getCurrentPosition());
    }
    @Test
    public void testMoveForwardOneTimeLeftEdgeToLeftEdge() throws InvalidCommandException, ObstacleException {

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(5, 3), Direction.EAST);

        //Act
        rover.move("f");

        //Assert
        Assertions.assertEquals(new Point2d(0, 3), rover.getCurrentPosition());
    }
    @Test
    public void testMoveForwardOneTimeLeftEdgeToUpperEdge() throws InvalidCommandException, ObstacleException {

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(0, 0), Direction.NORTH);

        //Act
        rover.move("f");

        //Assert
        Assertions.assertEquals(new Point2d(0, 5), rover.getCurrentPosition());
    }
    @Test
    public void testMoveForwardOneTimeLeftEdgeToBottomEdge() throws InvalidCommandException, ObstacleException {

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(0, 5), Direction.SOUTH);

        //Act
        rover.move("f");

        //Assert
        Assertions.assertEquals(new Point2d(0, 0), rover.getCurrentPosition());
    }
    @Test
    public void testObstacleCheck() {
        //Arrange
        ArrayList<Point2d> obstaclesList = new ArrayList<>();
        Point2d obstacle = new Point2d(2, 1);
        obstaclesList.add(obstacle);
        Obstacles obstacles =new Obstacles(obstaclesList);

        //Assert
        Assertions.assertTrue(obstacles.checkIfObstacleOnPosition(obstacle));
    }

    @Test
    public void testMoveForwardToAnObstacle() {

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(2, 2), Direction.NORTH);
        ArrayList<Point2d> obstacles = new ArrayList<>();
        Point2d obstacle = new Point2d(2, 1);
        obstacles.add(obstacle);
        rover.setObstacles(new Obstacles(obstacles));

        //Act
        ObstacleException thrown =  Assertions.assertThrows(ObstacleException.class, ()-> rover.move("f"));

        //Assert

        Assertions.assertEquals("Stopped!!! There is an obstacle in front of me!, Successfully done : ", thrown.getMessage());
    }
    @Test
    public void testMoveBackwardsToAnObstacle() {

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(2, 2), Direction.NORTH);
        ArrayList<Point2d> obstacles = new ArrayList<>();
        Point2d obstacle = new Point2d(2, 3);
        obstacles.add(obstacle);
        rover.setObstacles(new Obstacles(obstacles));

        //Act
        ObstacleException thrown =  Assertions.assertThrows(ObstacleException.class, ()-> rover.move("b"));

        //Assert

        Assertions.assertEquals("Stopped!!! There is an obstacle back of me!, Successfully done : ", thrown.getMessage());
    }

    @Test
    public void testEncounteringAnObstacleStopsTheRoverAtTheLatestPosition() throws InvalidCommandException{

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(2, 3), Direction.NORTH);
        ArrayList<Point2d> obstacles = new ArrayList<>();
        Point2d obstacle = new Point2d(2, 1);
        obstacles.add(obstacle);
        rover.setObstacles(new Obstacles(obstacles));

        //Act
        try {
            rover.move("f");
        }
        catch (ObstacleException ignore){

        }

        //Assert

        Assertions.assertEquals(new Point2d(2, 2),rover.getCurrentPosition());
    }
    @Test
    public void testInvalidCommands() {

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(2, 3), Direction.NORTH);

        //Act
        InvalidCommandException thrown =  Assertions.assertThrows(InvalidCommandException.class, ()-> rover.move("z"));

        //Assert

        Assertions.assertEquals("Invalid Command: z, Successfully done : ", thrown.getMessage());
    }

    @Test
    public void testMultipleCommands() throws ObstacleException, InvalidCommandException {

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(3, 3), Direction.NORTH);

        //Act
        rover.move("ffffrbblf");

        //Assert

        Assertions.assertEquals(new Point2d(1, 4), rover.getCurrentPosition());
    }

    @Test
    public void testAllCommandsWithObstaclesNoHit() throws ObstacleException, InvalidCommandException {

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(3, 3), Direction.NORTH);
        ArrayList<Point2d> obstacles = new ArrayList<>();
        Point2d obstacle1 = new Point2d(2, 1);
        Point2d obstacle2 = new Point2d(0, 0);
        Point2d obstacle3 = new Point2d(5, 5);
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        rover.setObstacles(new Obstacles(obstacles));

        //Act
        rover.move("flfrbbrf");

        //Assert

        Assertions.assertEquals(new Point2d(3, 4), rover.getCurrentPosition());
    }

    @Test
    public void testAllCommandsWithObstaclesHit() throws ObstacleException, InvalidCommandException {

        //Arrange
        MarsRover rover = new MarsRoverImpl(new Point2d(3, 3), Direction.NORTH);
        ArrayList<Point2d> obstacles = new ArrayList<>();
        Point2d obstacle1 = new Point2d(2, 1);
        Point2d obstacle2 = new Point2d(0, 0);
        Point2d obstacle3 = new Point2d(2, 4);
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        rover.setObstacles(new Obstacles(obstacles));

        //Act
        try {rover.move("flfrbbrf");}
        catch (ObstacleException e){
            Assertions.assertEquals("Stopped!!! There is an obstacle back of me!, Successfully done : flfrb", e.getMessage());
        }

        //Assert

        Assertions.assertEquals(new Point2d(2, 3), rover.getCurrentPosition());
    }

}

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
        ArrayList<Point2d> obstaclesList = new ArrayList<>();
        Point2d obstacle = new Point2d(2, 1);
        obstaclesList.add(obstacle);
        Obstacles obstacles =new Obstacles(obstaclesList);

        //Act


        //Assert
        Assertions.assertTrue(obstacles.checkIfObstacleOnPosition(obstacle));
    }

    @Test
    public void testMoveForwardToAnObstacle() {

        //Arrange (see README for reference to Arrange-Act-Assert Pattern)
        MarsRover rover = new MarsRoverImpl(new Point2d(2, 2), Direction.NORTH);
        ArrayList<Point2d> obstacles = new ArrayList<>();
        Point2d obstacle = new Point2d(2, 1);
        obstacles.add(obstacle);
        rover.setObstacles(new Obstacles(obstacles));

        //Act
        ObstacleException thrown =  Assertions.assertThrows(ObstacleException.class, ()-> rover.move("f"));

        //Assert

        Assertions.assertEquals("Stopped!!! There is an obstacle in front of me!", thrown.getMessage());
    }
    @Test
    public void testMoveBackwardsToAnObstacle() {

        //Arrange (see README for reference to Arrange-Act-Assert Pattern)
        MarsRover rover = new MarsRoverImpl(new Point2d(2, 2), Direction.NORTH);
        ArrayList<Point2d> obstacles = new ArrayList<>();
        Point2d obstacle = new Point2d(2, 3);
        obstacles.add(obstacle);
        rover.setObstacles(new Obstacles(obstacles));

        //Act
        ObstacleException thrown =  Assertions.assertThrows(ObstacleException.class, ()-> rover.move("b"));

        //Assert

        Assertions.assertEquals("Stopped!!! There is an obstacle back of me!", thrown.getMessage());
    }
}

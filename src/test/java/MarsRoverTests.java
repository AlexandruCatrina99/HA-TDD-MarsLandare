import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarsRoverTests {

    @Test
    public void testMoveForwardOneTimeLeftEdgeToRightEdge() throws InvalidCommandException {

        //Arrange (see README for reference to Arrange-Act-Assert Pattern)
        MarsRover rover = new MarsRoverImpl(new Point2d(1, 3), Direction.WEST);

        //Act
        rover.move("f");

        //Assert
        Assertions.assertEquals(new Point2d(0, 3), rover.getCurrentPosition());
    }
}

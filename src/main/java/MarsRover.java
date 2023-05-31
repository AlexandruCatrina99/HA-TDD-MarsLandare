import java.util.ArrayList;

public interface MarsRover {
    void move(String commands) throws InvalidCommandException, ObstacleException;
    Point2d getCurrentPosition();
    void setObstacles(Obstacles obstacles);
}

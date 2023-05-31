public interface MarsRover {
    void move(String commands) throws InvalidCommandException;
    Point2d getCurrentPosition();
    Direction getCurrentDirection();
}

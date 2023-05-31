import java.util.ArrayList;



public class Obstacles {
    private final ArrayList<Point2d> obstaclesPosition;

    public Obstacles(ArrayList<Point2d> obstaclesPosition) {
        this.obstaclesPosition = obstaclesPosition;
    }

    public boolean checkIfObstacleOnPosition(Point2d position){
        for (Point2d p: this.obstaclesPosition
             ) {
            if (p.equals(position)){
                return true;
            }
        }
        return false;
    }
}
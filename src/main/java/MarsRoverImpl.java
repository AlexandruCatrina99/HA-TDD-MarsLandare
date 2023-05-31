public class MarsRoverImpl implements MarsRover {
    private Direction direction;
    private Point2d position;
    private Obstacles obstacles;

    public MarsRoverImpl(Point2d startingPoint, Direction startingDirection) {
        this.direction = startingDirection;
        this.position = startingPoint;
    }

    @Override
    public void move(String commands) throws InvalidCommandException, ObstacleException {
        StringBuilder commandsExecuted = new StringBuilder();
        for (char command : commands.toCharArray()
        ) {
            switch (command) {
                case 'f' -> {
                    if (moveForward()) {
                        commandsExecuted.append("f");
                    } else {
                        throw new ObstacleException("Stopped!!! There is an obstacle in front of me!");
                    }
                }
                case 'b' -> {
                    if (moveBackwards()) {
                        commandsExecuted.append("b");
                    } else {
                        throw new ObstacleException("Stopped!!! There is an obstacle back of me!");
                    }
                }
                case 'l' -> {
                    turnLeft();
                    commandsExecuted.append("l");
                }
                case 'r' -> {
                    turnRight();
                    commandsExecuted.append("r");
                }
                default -> {
                    throw new InvalidCommandException("Invalid Command: " + command);
                }

            }
        }

    }

    private boolean moveBackwards() {
        if (direction.equals(Direction.NORTH)) {
            Point2d newPosition = this.position.y() == 5 ? new Point2d(this.position.x(), 0) : new Point2d(this.position.x(), this.position.y() + 1);
            if (!(obstacles == null) && obstacles.checkIfObstacleOnPosition(newPosition)) {
                return false;
            } else {
                this.position = newPosition;
                return true;
            }
        } else if (direction.equals(Direction.SOUTH)) {
            Point2d newPosition = this.position.y() == 0 ? new Point2d(this.position.x(), 5) : new Point2d(this.position.x(), this.position.y() - 1);
            if (!(obstacles == null) && obstacles.checkIfObstacleOnPosition(newPosition)) {
                return false;
            } else {
                this.position = newPosition;
                return true;
            }
        } else if (direction.equals(Direction.WEST)) {
            Point2d newPosition = this.position.x() == 5 ? new Point2d(0, this.position.y()) : new Point2d(this.position.x() + 1, this.position.y());
            if (!(obstacles == null) && obstacles.checkIfObstacleOnPosition(newPosition)) {
                return false;
            } else {
                this.position = newPosition;
                return true;
            }
        } else if (direction.equals(Direction.EAST)) {
            Point2d newPosition = this.position.x() == 0 ? new Point2d(5, this.position.y()) : new Point2d(this.position.x() - 1, this.position.y());
            if (!(obstacles == null) && obstacles.checkIfObstacleOnPosition(newPosition)) {
                return false;
            } else {
                this.position = newPosition;
                return true;
            }
        }
        return false;
    }

    private boolean moveForward() {
        if (direction.equals(Direction.NORTH)) {
            Point2d newPosition = this.position.y() == 0 ? new Point2d(this.position.x(), 5) : new Point2d(this.position.x(), this.position.y() - 1);
            if (!(obstacles == null) && obstacles.checkIfObstacleOnPosition(newPosition)) {
                return false;
            } else {
                this.position = newPosition;
                return true;
            }
        } else if (direction.equals(Direction.SOUTH)) {
            Point2d newPosition = this.position.y() == 5 ? new Point2d(this.position.x(), 0) : new Point2d(this.position.x(), this.position.y() + 1);
            if (!(obstacles == null) && obstacles.checkIfObstacleOnPosition(newPosition)) {
                return false;
            } else {
                this.position = newPosition;
                return true;
            }
        } else if (direction.equals(Direction.WEST)) {
            Point2d newPosition = this.position.x() == 0 ? new Point2d(5, this.position.y()) : new Point2d(this.position.x() - 1, this.position.y());
            if (!(obstacles == null) && obstacles.checkIfObstacleOnPosition(newPosition)) {
                return false;
            } else {
                this.position = newPosition;
                return true;
            }
        } else if (direction.equals(Direction.EAST)) {
            Point2d newPosition = this.position.x() == 5 ? new Point2d(0, this.position.y()) : new Point2d(this.position.x() + 1, this.position.y());
            if (!(obstacles == null) && obstacles.checkIfObstacleOnPosition(newPosition)) {
                return false;
            } else {
                this.position = newPosition;
                return true;
            }
        }
        return false;
    }

    private void turnLeft() {
        if (direction.equals(Direction.NORTH)) {
            this.direction = Direction.WEST;
        } else if (direction.equals(Direction.EAST)) {
            this.direction = Direction.NORTH;
        } else if (direction.equals(Direction.SOUTH)) {
            this.direction = Direction.EAST;
        } else if (direction.equals(Direction.WEST)) {
            this.direction = Direction.SOUTH;
        }
    }

    private void turnRight() {
        if (direction.equals(Direction.NORTH)) {
            direction = Direction.EAST;
        } else if (direction.equals(Direction.EAST)) {
            direction = Direction.SOUTH;
        } else if (direction.equals(Direction.SOUTH)) {
            direction = Direction.WEST;
        } else if (direction.equals(Direction.WEST)) {
            direction = Direction.NORTH;
        }
    }


    @Override
    public Point2d getCurrentPosition() {
        return this.position;
    }

    @Override
    public void setObstacles(Obstacles obstacles) {
        this.obstacles = obstacles;
    }

}

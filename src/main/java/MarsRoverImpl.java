public class MarsRoverImpl implements MarsRover {
    private Direction direction;
    private Point2d position;

    public MarsRoverImpl(Point2d startingPoint, Direction startingDirection) {
        this.direction = startingDirection;
        this.position = startingPoint;
    }

    @Override
    public void move(String commands) throws InvalidCommandException {
        StringBuilder commandsExecuted = new StringBuilder();
        for (char command : commands.toCharArray()
        ) {
            switch (command) {
                case 'f' -> {
                    moveForward();
                    commandsExecuted.append("f");
                }
                case 'b' -> {
//                    moveBackwards();
                    commandsExecuted.append("b");
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

    private void moveForward() {
        if (direction.equals(Direction.NORTH)) {
            this.position = new Point2d( this.position.x(), this.position.y() - 1);
        }
        else if (direction.equals(Direction.SOUTH)) {
            this.position = new Point2d( this.position.x(), this.position.y() + 1);
        }
        else if (direction.equals(Direction.WEST)) {
            this.position = new Point2d(this.position.x() - 1, this.position.y());
        }
        else if (direction.equals(Direction.EAST)) {
            this.position = new Point2d(this.position.x() + 1, this.position.y());
        }
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
    public Direction getCurrentDirection() {
        return this.direction;
    }
}

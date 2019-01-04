public class Rover {
    Coordinates coordinates;
    boolean     obstacleFound = false;

    public Rover(Coordinates roverCoordinates) {
        coordinates = roverCoordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getPosition() {
        return coordinates.getPosition() +
            (obstacleFound ? " NOK" : "");
    }

    public void receiveCommands(String commands) {
        try {
            for (char command : commands.toCharArray()) {
                receiveSingleCommand(command);
            }
        } catch (UnsupportedOperationException ex) {
            System.out.println("UnsupportedOperationException: " + ex.getMessage());
        } catch (ObstacleFoundException ex) {
            obstacleFound = true;
            System.out.println("ObstacleFoundException: " + ex.getMessage());

        }
    }

    public void receiveSingleCommand(char command) throws UnsupportedOperationException, ObstacleFoundException {
        char lcCommand = Character.toLowerCase(command);
        switch (lcCommand) {
            case 'f':
                coordinates.goForward();
                break;
            case 'b':
                coordinates.goBackwards();
                break;
            case 'l':
                coordinates.lookLeft();
                break;
            case 'r':
                coordinates.lookRight();
                break;
            default:
                throw new UnsupportedOperationException("unknown command " + lcCommand);
        }
    }
}

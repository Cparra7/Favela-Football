package Main;

//import Data.BoundingBox;
import Data.Vector2D;
import Data.spriteInfo;
import logic.Control;
import timer.stopWatchX;

public class KeyProcessor {
    // Static Fields
    private static char last = ' ';            // For debouncing purposes
    private static stopWatchX sw = new stopWatchX(50); // Adjust debounce time if needed

    // Static Method(s)
    public static void processKey(char key) {
        // Debounce routine below...
        if (key == last) {
            if (!sw.isTimeUp()) return;
        }
        last = key;
        sw.resetWatch();

        /* TODO: You can modify values below here! */
        switch (key) {
            case '%':                               // ESC key
                System.exit(0);
                break;

            case 'w':
                if (Main.allowMovementW == true) {
                    moveSprites(0, -10); // Move sprites up
                    Main.trigger = "FaceBack";
                    return;
                }
                break;

            case 'a':
                if (Main.allowMovementA == true) {
                    moveSprites(-10, 0); // Move sprites left
                    Main.trigger = "Left";
                    return;
                }
                break;

            case 's':
                if (Main.allowMovementS == true) {
                    moveSprites(0, 10); // Move sprites down
                    Main.trigger = "FrontFace";
                    return;
                }
                break;

            case 'd':
                if (Main.allowMovementD == true) {
                    moveSprites(10, 0); // Move sprites right
                    Main.trigger = "Forward";
                    return;
                }
                break;

            case '$':
                Main.trigger = "SpaceBarPressed";
                break;
                
            case 'y': // For start screen
            	Main.trigger = "YPressed";
            	break;

            case 'm':
                // For mouse coordinates
                Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
                break;

            default:
                // Set trigger to display original sprite when no keys are pressed
                Main.trigger = "Default"; // You can change this trigger value if needed
                break;
        }
    }

    private static void moveSprites(int dx, int dy) {
        // Update the positions of all sprites
        for (spriteInfo sprite : Main.sprites) {
            // Get the current position of the sprite
            Vector2D currentPosition = sprite.getCoords();

            // Calculate the new position by adjusting the current position
            int newX = currentPosition.getX() + dx; // Adjust x-coordinate
            int newY = currentPosition.getY() + dy; // Adjust y-coordinate

            // Set the new position of the sprite
            sprite.setCoords(new Vector2D(newX, newY));
        }
    }
}

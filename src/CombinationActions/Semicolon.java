package CombinationActions;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by jacobmenke on 12/29/16.
 */
public class Semicolon {

    public static void typeSemicolon(Robot myRobo){
        Process p = null;

        try {
            String s = null;

            myRobo.keyPress(KeyEvent.VK_ENTER);
            myRobo.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e1) {

        } finally {

        }
    }
}

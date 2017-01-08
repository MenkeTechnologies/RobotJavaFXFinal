package CombinationActions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

import static RobotUtilities.RobotTypesString.deleteShortcutKeysType;
import static RobotUtilities.RunBashCommands.greetings;

/**
 * Created by jacobmenke on 12/29/16.
 */
public class Counting {

    public static void count(Robot myRobo){
        deleteShortcutKeysType(myRobo);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String toCountWithSpaces = "";

        try {
            toCountWithSpaces = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (Exception ee) {
            System.out.println("Unable to get string from clipboard");
        }

        String noSpaces = toCountWithSpaces.replaceAll("\\s+", "");

        int counter = 0;

        for (int i = 0; i < noSpaces.length(); i++) {

            counter++;
        }

        System.out.println(counter);

        greetings("Count was " + counter);
    }
}

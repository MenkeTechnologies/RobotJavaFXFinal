package CombinationActions;

import RobotUtilities.RobotTypesString;

import java.awt.*;
import java.awt.event.KeyEvent;

import static RobotUtilities.RobotTypesString.deleteShortcutKeysType;
import static RobotUtilities.RunBashCommands.greetings;

/**
 * Created by jacobmenke on 12/29/16.
 */
public class Marking {


   public static void mark(Robot myRobo){
       deleteShortcutKeysType(myRobo);
       String divider = "***************************";
       String spacer = " ";

       String markDivider = "";
       for (int i = 0; i < divider.length(); i++) {
           markDivider += " ";
       }

       String markText = "//" + markDivider + "MARK:" + spacer + markDivider;
       String upperAndLowerDividers = "//" + divider + "****************" + divider;
       String combinedMark = upperAndLowerDividers + "\n" + markText + "\n" + upperAndLowerDividers;

       RobotTypesString.typeStringFromClipboard(combinedMark, myRobo);

       myRobo.keyPress(KeyEvent.VK_UP);
       myRobo.keyRelease(KeyEvent.VK_UP);

       for (int i = 0; i < divider.length() + spacer.length(); i++) {
           myRobo.keyPress(KeyEvent.VK_LEFT);
           myRobo.keyRelease(KeyEvent.VK_LEFT);
       }

       greetings("MARK!");
   }
}

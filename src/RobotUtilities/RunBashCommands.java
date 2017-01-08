package RobotUtilities;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by jacobmenke on 12/29/16.
 */
public class RunBashCommands {


    public static void greetings(String greeting) {


        Thread greetThread = new Thread(() -> {

            Process px = null;

            try {

                px = Runtime.getRuntime().exec("say -v Daniel " + greeting);
                px.waitFor();
            } catch (Exception e1) {

            } finally {

                px.destroy();
            }
        });

        greetThread.start();
    }
}

package RobotUtilities;

import RobotUtilities.RunBashCommands;
import gui_main.Controller;
import gui_main.JacobsRobot;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jacobmenke on 12/29/16.
 */
public class StartingRobot {
    public static JacobsRobot startingRobotMethod(Controller controller) throws AWTException {
        JacobsRobot gui = null;

        gui = new JacobsRobot(controller);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(gui);

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

        logger.setLevel(Level.OFF);

        logger.setUseParentHandlers(false);

        return gui;
    }
}

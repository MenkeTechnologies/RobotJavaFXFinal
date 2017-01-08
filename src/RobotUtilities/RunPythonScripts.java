package RobotUtilities;

import java.awt.*;

/**
 * Created by jacobmenke on 12/29/16.
 */
public class RunPythonScripts {

    public static void runPythonScript(String scriptName, Robot myRobo) {
        Process p = null;

        try {
            RobotTypesString.deleteShortcutKeysType(myRobo);
            p = Runtime.getRuntime().exec(scriptName);
            p.waitFor();
        } catch (Exception e1) {
        } finally {
            p.destroy();
        }
    }

    public static void runPythonScriptServerDontDeleteKeys(String scriptName) {
        Process p = null;
        System.out.println("her in python script dont delete");

        try {
            p = Runtime.getRuntime().exec(scriptName);
            p.waitFor();
        } catch (Exception e1) {

        } finally {
            p.destroy();
        }
    }

}

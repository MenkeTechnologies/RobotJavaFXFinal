package gui_main;

import CombinationActions.Semicolon;
import CombinationActions.Signature;
import RobotUtilities.RobotTypesString;
import RobotUtilities.RunBashCommands;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.DropShadow;
import javafx.util.Duration;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.Timer;

import static CombinationActions.Counting.count;
import static CombinationActions.Marking.mark;
import static RobotUtilities.RunBashCommands.greetings;
import static RobotUtilities.RunPythonScripts.runPythonScript;

public class JacobsRobot implements NativeKeyListener {
    public String python3Path = "/Library/Frameworks/Python.framework/Versions/3.5/bin/python3.5";
    Robot myRobo = null;
    boolean shiftKeyPressed = false;
    boolean tildaKeyPressed = false;
    boolean spacebarPressed = false;
    boolean sKey = false;
    boolean switcher = false;
    boolean activated = true;
    long time1 = 0;
    long time2 = 0;
    long timeElapsed = 0;
    Long startTime;
    Long stopTime;
    Long timeLength;
    Integer terminalKey = NativeKeyEvent.VC_ALT_L;
    Integer safariKey = NativeKeyEvent.VC_BACKQUOTE;
    Integer controlKey = NativeKeyEvent.VC_CONTROL_L;
    /////////////////////////////////MARK:Methods /////////////////////////////////
    boolean firstTime = true;
    boolean altKeySecondTime = false;
    Long altKeyStartTime;
    Long finishTimeCommmandKey;
    PrintStream pw = null;
    Controller controller;

    /////////////////////////////////MARK:Constructor /////////////////////////////////
    public JacobsRobot(Controller controller) throws AWTException {
        this.controller = controller;
        myRobo = new Robot();

    }

    public void nativeKeyPressed(NativeKeyEvent e) {

        if (e.getKeyCode() == terminalKey) {
            if (!altKeySecondTime) {
                altKeyStartTime = System.currentTimeMillis();
            } else {
                System.out.println("in alt second time");

                Long altKeyFinishTime = System.currentTimeMillis();

                timeLength = altKeyFinishTime - altKeyStartTime;

                if (timeLength < 300) {
                    try {
                        Runtime.getRuntime().exec("open -a iterm");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

//                    runPythonScriptServerDontDeleteKeys("open -a iterm");
                }
            }
        }

        if (e.getKeyCode() == safariKey) {

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    firstTime = true;
                }
            }, 1000);

            if (firstTime) {
                startTime = System.currentTimeMillis();
            } else {
                finishTimeCommmandKey = System.currentTimeMillis();

                timeLength = finishTimeCommmandKey - startTime;

                if (timeLength < 300) {
                    runPythonScript("python /Users/jacobmenke/PycharmProjects/googleSearch.py", myRobo);
                }
            }
        }

        if (shiftKeyPressed) {
            System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
            if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
                try {
                    runTerminalBashscript(controller.commandTextField.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        //System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        /////////////////////////////////MARK:Shift Key Modifier /////////////////////////////////

        if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT_L || e.getKeyCode() == NativeKeyEvent.VC_SHIFT_R) {
            shiftKeyPressed = true;
        }

        /////////////////////////////////MARK:Tilde Key Modifier /////////////////////////////////

        if (e.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE) {

            tildaKeyPressed = true;
        }

        /////////////////////////////////MARK:SpaceBar Modifier /////////////////////////////////

        if (e.getKeyCode() == NativeKeyEvent.VC_SPACE) {

            spacebarPressed = true;
        }

        /////////////////////////////////MARK:Activate and Deactivate /////////////////////////////////

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && tildaKeyPressed) {

            if (!switcher) {
                try {
                    activated = false;
//                    GlobalScreen.unregisterNativeHook();
                    greetings("Deactivated.");
                } catch (Exception e1) {

                } finally {
                    switcher = true;
                }
            } else {
                try {
                    activated = true;
//                    GlobalScreen.unregisterNativeHook();
                    greetings("Activated.");
                } catch (Exception e1) {

                } finally {
                    switcher = false;
                }
            }
        }

        //**********************************************************************
        //                           MARK:Go to NAS
        //*********************************************************************

        if (e.getKeyCode() == NativeKeyEvent.VC_T && tildaKeyPressed) {

            runPythonScript("bash -e /Users/jacobmenke/Documents/shellScripts/nas", myRobo);
        }

        //**********************************************************************
        //                           MARK:Counter
        //**********************************************************************

        if (e.getKeyCode() == NativeKeyEvent.VC_A && tildaKeyPressed) {

            count(myRobo);
        }

        if (activated == true) {

            //**********************************************************************
            //                           MARK: Semicolon
            //**********************************************************************

            if (e.getKeyCode() == NativeKeyEvent.VC_SEMICOLON && !shiftKeyPressed) {
                Semicolon.typeSemicolon(myRobo);
            }

            if (tildaKeyPressed && e.getKeyCode() == NativeKeyEvent.VC_R) {
                mark(myRobo);
            }

            if (tildaKeyPressed && e.getKeyCode() == NativeKeyEvent.VC_Q) {

                Signature.typeSignature(myRobo);
            }

            //**********************************************************************
            //                           MARK:Python Sripts
            //**********************************************************************

            if (tildaKeyPressed && e.getKeyCode() == NativeKeyEvent.VC_Z) {
                runPythonScript(python3Path + " /Users/jacobmenke/PycharmProjects/blackBoard.py", myRobo);
            }

            if (tildaKeyPressed && e.getKeyCode() == NativeKeyEvent.VC_2) {
                runPythonScript(python3Path + " /Users/jacobmenke/PycharmProjects/bills.py", myRobo);
            }

            if (tildaKeyPressed && e.getKeyCode() == NativeKeyEvent.VC_3) {
                runPythonScript("say -v Daniel I am saying something for the purposes of this tutorial!", myRobo);
            }

            if (tildaKeyPressed && e.getKeyCode() == NativeKeyEvent.VC_E) {
                runPythonScript("python /Users/jacobmenke/PycharmProjects/amazonSearch.py", myRobo);
            }

            if (tildaKeyPressed && e.getKeyCode() == NativeKeyEvent.VC_4) {
                runPythonScript("python /Users/jacobmenke/PycharmProjects/googleSearch.py", myRobo);
            }

            if (tildaKeyPressed && e.getKeyCode() == NativeKeyEvent.VC_5) {
                greetings("Bye");
                runPythonScript(python3Path + " /Users/jacobmenke/PycharmProjects/shutdown.py", myRobo);
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

        if (e.getKeyCode() == terminalKey) {

            if (!altKeySecondTime) {
                altKeySecondTime = true;

                java.util.Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        altKeySecondTime = false;
                    }
                }, 1000);
            } else {
                altKeySecondTime = false;
            }
        }

        if (e.getKeyCode() == safariKey) {

            if (firstTime) {
                firstTime = false;
            } else {
                firstTime = true;
            }
        }

        //**********************************************************************
        //                           MARK: End Modifiers
        //**********************************************************************

        if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT_L || e.getKeyCode() == NativeKeyEvent.VC_SHIFT_R) {
            shiftKeyPressed = false;
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE) {

            tildaKeyPressed = false;
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_SPACE) {

            spacebarPressed = false;
        }

        //System.out.println("Key Released: " + e.getKeyCode());
    }

    public void runTerminalBashscript(String command) throws IOException, InterruptedException {

        Runtime.getRuntime().exec("open -a iTerm");
        try {
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500));
            translateTransition.setNode(controller.commandTextField);
            translateTransition.setToY(controller.commandTextField.getLayoutY()+30);
            translateTransition.setInterpolator(Interpolator.EASE_IN);
            translateTransition.setAutoReverse(true);
            translateTransition.setCycleCount(2);
            translateTransition.playFromStart();


            RobotTypesString.typeTheStringForMeNow(command);


        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }
}
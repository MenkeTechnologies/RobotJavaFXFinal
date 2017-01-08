package RobotUtilities;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by jacobmenke on 12/29/16.
 */
public class RobotTypesString {


    public static  void typeStringFromClipboard(String textToWrite, Robot myRobo) {
        StringSelection stringSelection = new StringSelection(textToWrite);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        try {
            myRobo = new Robot();

            myRobo.keyPress(KeyEvent.VK_META);
            myRobo.keyPress(KeyEvent.VK_V);
            myRobo.keyRelease(KeyEvent.VK_V);
            myRobo.keyRelease(KeyEvent.VK_META);
        } catch (Exception e) {

        }
    }

    public static void deleteShortcutKeysType(Robot myRobo) {

        try {
            myRobo.keyPress(KeyEvent.VK_BACK_SPACE);
            myRobo.keyRelease(KeyEvent.VK_BACK_SPACE);
            myRobo.keyPress(KeyEvent.VK_BACK_SPACE);
            myRobo.keyRelease(KeyEvent.VK_BACK_SPACE);
        } catch (Exception e1) {
        }
    }


    private static void typeUpperCaseCharacter(Character c,Robot myRobo){

        myRobo.keyPress(KeyEvent.VK_SHIFT);
        myRobo.keyPress(c);
        myRobo.keyRelease(c);
        myRobo.keyRelease(KeyEvent.VK_SHIFT);
        myRobo.delay(10);

    }

    private static void typerLowerCaseCharacter(Character c,Robot myRobo){
        myRobo.keyPress(c);
        myRobo.keyRelease(c);


    }



    public static void typeTheStringForMeNow(String commandToType) throws AWTException, InterruptedException {

        Robot myRobo = new Robot();

        myRobo.delay(300);

        ArrayList<Character> characterArrayList = new ArrayList<>();
        ArrayList<Boolean> uppercase = new ArrayList<>();
        
        for (Character c : commandToType.toCharArray()) {
            
            characterArrayList.add(Character.toUpperCase(c));

            if (Character.isUpperCase(c)){
                uppercase.add(Boolean.TRUE);
            } else{
                uppercase.add(Boolean.FALSE);
            }
                
            }
            
            for (int i = 0; i < characterArrayList.size(); i++) {
            if (uppercase.get(i)) {
                typeUpperCaseCharacter(characterArrayList.get(i), myRobo);
            }
            else {
                typerLowerCaseCharacter(characterArrayList.get(i), myRobo);
            }



//
//                System.out.println("the character is " + characterArrayList.get(i)
//                + " and the boole is " + uppercase.get(i));
//
                
            }
            
        

        myRobo.keyPress(KeyEvent.VK_ENTER);
        myRobo.keyRelease(KeyEvent.VK_ENTER);



    }


    static public void manuallyType(char c, Robot myRobo) {

        try {

            switch (c) {
                case 'd':
                    myRobo.keyPress(KeyEvent.VK_D);
                    myRobo.keyRelease(KeyEvent.VK_D);
                    break;
                case 'r':
                    myRobo.keyPress(KeyEvent.VK_R);
                    myRobo.keyRelease(KeyEvent.VK_R);
                    break;
                case 'm':
                    myRobo.keyPress(KeyEvent.VK_M);
                    myRobo.keyRelease(KeyEvent.VK_M);
                    break;
                case 'e':
                    myRobo.keyPress(KeyEvent.VK_E);
                    myRobo.keyRelease(KeyEvent.VK_E);
                    break;
                case 'n':
                    myRobo.keyPress(KeyEvent.VK_N);
                    myRobo.keyRelease(KeyEvent.VK_N);
                    break;
                case 'k':
                    myRobo.keyPress(KeyEvent.VK_K);
                    myRobo.keyRelease(KeyEvent.VK_K);
                    break;
                case '9':
                    myRobo.keyPress(KeyEvent.VK_9);
                    myRobo.keyRelease(KeyEvent.VK_9);
                    break;
            }
        } catch (Exception e) {

        }
    }

}

package gui_main;

import RobotUtilities.StartingRobot;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField commandTextField;
    JacobsRobot jacobsRobot;

    public void activeTheTerminalRobot(ActionEvent actionEvent) {
        String command = commandTextField.getText();
        if (!command.equals("")) {
            try {
                jacobsRobot.runTerminalBashscript(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            jacobsRobot = StartingRobot.startingRobotMethod(this);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        commandTextField.setText("bash -i /Users/jacobmenke/Desktop/new.sh");
    }
}

package gui_main;

import RobotUtilities.StartingRobot;
import TableInitialization.TableInitialization;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.awt.*;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField commandTextField;
    JacobsRobot jacobsRobot;
    public TableView commandsTable;
    public TextField shortcutKeyTF;
    public TextField executableTF;
    ObservableList<Command> commandObservableList = FXCollections.observableArrayList();

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
        commandObservableList.setAll(new Command("Shift Escape", "/bash"), new Command("tilde", "perl"), new Command("a","a"),
                new Command("b","b"),new Command("c","c"));

        TableInitialization.tableColumnsSetup(commandsTable, commandObservableList);
    }

    public void addACommand(ActionEvent actionEvent) {
        commandObservableList.add(new Command(shortcutKeyTF.getText(), executableTF.getText()));

        for (int i = 0; i < commandObservableList.size(); i++) {
            System.out.println("the index is " + i + " and the value is " + commandObservableList.get(i));
        }
    }

    public void deleteACommand(ActionEvent actionEvent) {
        commandObservableList.removeAll(commandsTable.getSelectionModel().getSelectedItems());
    }

    public void shiftCommandUp(ActionEvent actionEvent) {
        TableInitialization.shiftCommandUp(commandsTable, commandObservableList);
    }

    public void shiftCommandDown(ActionEvent actionEvent) {
        TableInitialization.shiftCommandDown(commandsTable, commandObservableList);

    }

}

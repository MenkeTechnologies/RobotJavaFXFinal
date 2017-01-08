package TableInitialization;

import gui_main.Command;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.Collections;

/**
 * Created by jacobmenke on 1/8/17.
 */

class EditingCell<S,T> extends TableCell<S,T>{

    private final TextField textfield = new TextField();

//    public EditingCell() {
//
//        textfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue){
//                commitEdit((T) textfield.getText());
//            }
//        });
//    }
}

public class TableInitialization {


    public static void tableColumnsSetup(TableView commandsTable, ObservableList<Command> commandObservableList){

        TableColumn<Command, String> commandStringTableColumn = new TableColumn<>("Key Combination");
        commandStringTableColumn.setCellValueFactory(new PropertyValueFactory<Command, String>("commandActivationKeyCombination"));
//        commandStringTableColumn.setCellFactory(param -> new EditingCell<Command, String>());
        commandStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        commandStringTableColumn.setOnEditCommit(event -> {

            commandObservableList.set(event.getTablePosition().getRow(),new Command(event.getNewValue(), event.getRowValue().getExecutableActivatedByKeyCombination()));

        });
        TableColumn<Command, String> executableStringTableColumn = new TableColumn<>("Executable");
        executableStringTableColumn.setCellValueFactory(new PropertyValueFactory<Command, String>("executableActivatedByKeyCombination"));
        executableStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        executableStringTableColumn.setOnEditCommit(event -> {

            commandObservableList.set(event.getTablePosition().getRow(),new Command(event.getRowValue().getCommandActivationKeyCombination(), event.getNewValue()));

        });



        commandsTable.setEditable(true);
        commandsTable.setItems(commandObservableList);

        commandsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        commandsTable.getColumns().addAll(commandStringTableColumn, executableStringTableColumn);

//        commandsTable.editingCellProperty().addListener((observable, oldValue, newValue) -> {
//
//            System.out.println("the new value is " + oldValue);
//            System.out.println("the new value is " + newValue);
//
//        });




    }

    public static void shiftCommandDown(TableView commandsTable, ObservableList<Command> commandObservableList){



        ObservableList<Command> selectedItems = commandsTable.getSelectionModel().getSelectedItems();

        int numberOfSelectedItems = selectedItems.size();

        System.out.println("the last selected item is " + selectedItems.get(numberOfSelectedItems-1));

        int lastIndexOfSelectedItems = commandObservableList.indexOf(selectedItems.get(numberOfSelectedItems-1));

      System.out.println("the numberOfSelectedItems is " + numberOfSelectedItems + " and the last index is " + lastIndexOfSelectedItems);

        if (lastIndexOfSelectedItems < commandObservableList.size()-1) {

            for (int i = 0; i < numberOfSelectedItems; i++) {
//                System.out.println("swapping " + commandObservableList.get(lastIndexOfSelectedItems) + " " +  commandObservableList.get(lastIndexOfSelectedItems-1));

                Collections.swap(commandObservableList, lastIndexOfSelectedItems-i, lastIndexOfSelectedItems + 1-i);

            }


        }
    }


    public static void shiftCommandUp(TableView commandsTable, ObservableList<Command> commandObservableList) {


        ObservableList<Command> selectedItems = commandsTable.getSelectionModel().getSelectedItems();
        int firstIndexOfSelectedItems = commandObservableList.indexOf(selectedItems.get(0));
        int numberOfSelectedItems = selectedItems.size();

        if (firstIndexOfSelectedItems > 0) {

            for (int i = 0; i < numberOfSelectedItems; i++) {
                Collections.swap(commandObservableList, firstIndexOfSelectedItems + i, firstIndexOfSelectedItems - 1 + i);
            }
        }
    }


}

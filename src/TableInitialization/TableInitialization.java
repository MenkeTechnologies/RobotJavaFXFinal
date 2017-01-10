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

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jacobmenke on 1/8/17.
 */
class EditingCell<S, T> extends TableCell<S, T> {
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
    public static void tableColumnsSetup(TableView commandsTable, ObservableList<Command> commandObservableList) {

        TableColumn<Command, String> commandStringTableColumn = new TableColumn<>("Key Combination");
        commandStringTableColumn.setCellValueFactory(new PropertyValueFactory<Command, String>("commandActivationKeyCombination"));
//        commandStringTableColumn.setCellFactory(param -> new EditingCell<Command, String>());
        commandStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        commandStringTableColumn.setOnEditCommit(event -> {

            commandObservableList.set(event.getTablePosition().getRow(), new Command(event.getNewValue(), event.getRowValue().getExecutableActivatedByKeyCombination()));
        });
        TableColumn<Command, String> executableStringTableColumn = new TableColumn<>("Executable");
        executableStringTableColumn.setCellValueFactory(new PropertyValueFactory<Command, String>("executableActivatedByKeyCombination"));
        executableStringTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        executableStringTableColumn.setOnEditCommit(event -> {

            commandObservableList.set(event.getTablePosition().getRow(), new Command(event.getRowValue().getCommandActivationKeyCombination(), event.getNewValue()));
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

    public static void shiftCommandDown(TableView commandsTable, ObservableList<Command> commandObservableList) {

        ObservableList<Command> selectedItems = commandsTable.getSelectionModel().getSelectedItems();

        ArrayList<Integer> indices = new ArrayList<>();
        selectedItems.forEach(item->indices.add(commandObservableList.indexOf(item)));


        if (indices.get(indices.size()-1) < commandObservableList.size()-1) {
            Collections.reverse(indices);
            indices.forEach(key->{
                System.out.println("we are swapping " + key + " and " + (key+1));
                Collections.swap(commandObservableList, key, key+1);
            });

            indices.forEach(key->{
                commandsTable.getSelectionModel().select(key+1);
            });

        }
    }


    public synchronized static void shiftCommandUp(TableView commandsTable, ObservableList<Command> commandObservableList) {

        ObservableList<Command> selectedItems = commandsTable.getSelectionModel().getSelectedItems();
        ArrayList<Integer> indices = new ArrayList<>();
        selectedItems.forEach(item->indices.add(commandObservableList.indexOf(item)));

        if (indices.get(0) > 0) {
            indices.forEach(key->{
//                   System.out.println("we are swapping " + key + " and " + (key-1));
                   Collections.swap(commandObservableList, key, key-1);
            });

            indices.forEach(key->{
                commandsTable.getSelectionModel().select(key-1);
            });

            }
    }
}

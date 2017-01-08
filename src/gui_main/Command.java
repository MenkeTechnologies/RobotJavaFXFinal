package gui_main;

/**
 * Created by jacobmenke on 1/8/17.
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by jacobmenke on 1/8/17.
 */
public class Command {
    private final StringProperty commandActivationKeyCombination = new SimpleStringProperty();


    private final StringProperty executableActivatedByKeyCombination = new SimpleStringProperty();

    public Command(String shortcut, String action) {
        setCommandActivationKeyCombination(shortcut);
        setExecutableActivatedByKeyCombination(action);
    }

    public Command() {
        this("N/A","N/A");

    }

    public String getCommandActivationKeyCombination() {
        return commandActivationKeyCombination.get();
    }


    public void setCommandActivationKeyCombination(String commandActivationKeyCombination) {
        this.commandActivationKeyCombination.set(commandActivationKeyCombination);
    }

    public String getExecutableActivatedByKeyCombination() {
        return executableActivatedByKeyCombination.get();
    }

    public void setExecutableActivatedByKeyCombination(String executableActivatedByKeyCombination) {
        this.executableActivatedByKeyCombination.set(executableActivatedByKeyCombination);
    }

    @Override
    public String toString() {
        return "Commands{" +
                "commandActivationKeyCombination=" + commandActivationKeyCombination.getValue() +
                ", executableActivatedByKeyCombination=" + executableActivatedByKeyCombination.getValue() +
                '}';
    }
}

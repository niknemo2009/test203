package dairy.view.controllers;

import dairy.dao.ActionDao;
import dairy.model.Action;
import dairy.model.Target;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ControllerEditActions {

    @FXML public Button buttonAdd;
    @FXML public Button buttonRemoveAction;
    @FXML public Button buttonCancel;
    @FXML public Button buttonAccept;
    @FXML public Button buttonRenameAction;
    @FXML public TableView<Action> tableViewActions;
    @FXML public TableColumn<Action, String> tableColumnActions;
    @FXML public TextField textFieldNewAction;
    @FXML public TextField textFieldRenameAction;
    private ArrayList<Action> actionsArrayList;
    private ArrayList<ArrayList<Double>> targetsCoefficients;

    @FXML
    void initialize() {
        Action action = new Action();
        Target target = new Target();
        actionsArrayList = cloneActionsArrayList(action.getActionsArrayList());
        targetsCoefficients = cloneTargetsCoefficients(target.getTargetsCoefficients());
        updateTableViewActions();
    }

    private static ArrayList<Action> cloneActionsArrayList(ArrayList<Action> actionsUniqueArrayList)
    {
        ArrayList<Action> clone = new ArrayList<>(actionsUniqueArrayList.size());
        for (Action item:actionsUniqueArrayList) {
            try {
                clone.add((Action)item.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return clone;
    }

    private static ArrayList<ArrayList<Double>> cloneTargetsCoefficients(ArrayList<ArrayList<Double>> targetsCoefficients)
    {
        ArrayList<ArrayList<Double>> clone= new ArrayList<>(targetsCoefficients.size());
        for (ArrayList<Double>item:targetsCoefficients) {
            clone.add((ArrayList<Double>) item.clone());
        }
        return clone;
    }

    public void addAction() {
        if (!textFieldNewAction.getText().equals(""))
        {

            actionsArrayList.add(new Action(textFieldNewAction.getText()));
            textFieldNewAction.clear();
            for (ArrayList<Double> targetsCoefficient : targetsCoefficients) {
                targetsCoefficient.add(0.0);
            }
            updateTableViewActions();
        }
    }

    public void removeAction() {
        if (tableViewActions.getSelectionModel().getSelectedIndex()>-1)
        {
            actionsArrayList.remove(tableViewActions.getSelectionModel().getSelectedIndex());
            for (ArrayList<Double> targetsCoefficient : targetsCoefficients) {
                targetsCoefficient.remove(tableViewActions.getSelectionModel().getSelectedIndex());
            }
            updateTableViewActions();
        }
    }

    public void renameAction() {
        try{
            if (!textFieldRenameAction.getText().equals(""))
            {
                actionsArrayList.set(tableViewActions.getSelectionModel().getSelectedIndex(), new Action(textFieldRenameAction.getText()));
                textFieldRenameAction.clear();
                updateTableViewActions();
            }
        }
        catch (Exception ignored){}
    }

    public void updateTableViewActions(){
        ObservableList<Action> observableListTargets = FXCollections.observableArrayList();
        observableListTargets.addAll(actionsArrayList);
        tableColumnActions.setCellValueFactory(new PropertyValueFactory<>("actionName"));
        tableViewActions.setItems(observableListTargets);
        tableViewActions.refresh();
    }

    public void accept() {
        Action action = new Action();
        action.getActionsArrayList().clear();

        action.getActionsArrayList().addAll(actionsArrayList);
        ActionDao actionDao = new ActionDao();
        actionDao.update(actionsArrayList);

        Target target = new Target();
        target.getTargetsCoefficients().clear();
        target.getTargetsCoefficients().addAll(targetsCoefficients);

        actionsArrayList.clear();
        targetsCoefficients.clear();
        ControllerMain.getStageSecondary().close();
    }

    public void cancel() {
        actionsArrayList.clear();
        targetsCoefficients.clear();
        ControllerMain.getStageSecondary().close();
    }
}

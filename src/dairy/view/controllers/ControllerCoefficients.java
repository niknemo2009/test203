package dairy.view.controllers;

import dairy.model.Action;
import dairy.model.Target;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.Arrays;

public class ControllerCoefficients {

    @FXML public Button buttonAccept;
    @FXML public Button buttonSetCoefficient;
    @FXML public Button buttonCancel;
    @FXML public TableView<String[]> tableViewSelectedCoefs;
    @FXML public TableColumn<Action,String> tableColumnAction;
    @FXML public TableColumn<Target, Double> tableColumnCoefficient;
    @FXML public Label labelSelectedTarget;
    @FXML public TextField textFieldSetCoefficient;
    @FXML private String[] arrayCoefficients;

    @FXML
    void initialize() {
        ControllerMain controllerMain = new ControllerMain();
        Target target = new Target();
        Action action = new Action();
        labelSelectedTarget.setText("Selected target - "+target.getTargetsArrayList()
                .get(controllerMain.getSelectedTarget()).getTargetName());
        arrayCoefficients = new String[action.getActionsArrayList().size()];
        for (int i = 0; i < target.getTargetsCoefficients().get(controllerMain.getSelectedTarget()).size(); i++) {
            arrayCoefficients[i]=Double.toString(target.getTargetsCoefficients()
                    .get(controllerMain.getSelectedTarget()).get(i));
        }
        updateTableSelectedCoefs();
    }

    public void updateTableSelectedCoefs()
    {
        Action action = new Action();
        String[][] tableCoefficient = new String[arrayCoefficients.length][2];
        for (int i = 0; i < action.getActionsArrayList().size(); i++) {
            tableCoefficient[i][0]=action.getActionsArrayList().get(i).getActionName();
            tableCoefficient[i][1]= arrayCoefficients[i];
        }
        ObservableList<String[]> observableList = FXCollections.observableArrayList();
        observableList.addAll(Arrays.asList(tableCoefficient));
        tableViewSelectedCoefs.getColumns().clear();
        for (int i = 0; i < tableCoefficient[0].length; i++) {
            String tempName;
            if (i==0)tempName = "Your actions";
            else tempName = "Coefficients";
            TableColumn tableColumn = new TableColumn(tempName);
            int columnNumber = i;
            tableColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>) cellDataFeatures
                    -> new SimpleObjectProperty<>((cellDataFeatures.getValue()[columnNumber])));
            tableViewSelectedCoefs.getColumns().add(tableColumn);
        }
        tableViewSelectedCoefs.setItems(observableList);
    }

    public void setCoefficient() {
        try{
            if(tableViewSelectedCoefs.getSelectionModel().getSelectedIndex()>-1
            &&Double.parseDouble(textFieldSetCoefficient.getText())>=0.0
            &&Double.parseDouble(textFieldSetCoefficient.getText())<=1.0){
                arrayCoefficients[tableViewSelectedCoefs.getSelectionModel()
                        .getSelectedIndex()]=textFieldSetCoefficient.getText();
                updateTableSelectedCoefs();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void accept() {
        Target target = new Target();
        ControllerMain controllerMain = new ControllerMain();
        for (int i = 0; i < arrayCoefficients.length; i++) {
            target.getTargetsCoefficients().get(controllerMain.getSelectedTarget())
                    .set(i,Double.parseDouble(arrayCoefficients[i]));
        }
        ControllerMain.getStageSecondary().close();
    }

    public void cancel() {
        ControllerMain.getStageSecondary().close();
    }
}

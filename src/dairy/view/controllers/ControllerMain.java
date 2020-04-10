package dairy.view.controllers;

import dairy.dao.RecordDao;
import dairy.model.Action;
import dairy.model.Record;
import dairy.model.Target;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ControllerMain {

    @FXML public Button buttonAct;
    @FXML public Button buttonDate;
    @FXML public Button buttonSetRandomCoefficient;
    @FXML public Button buttonSetCustomCoefficient;
    @FXML public Button buttonEditTargets;
    @FXML public Button buttonEditActions;
    @FXML public TextField textFieldDuration;
    @FXML public TextField textFieldLog;
    @FXML public TextArea textAreaDate;
    @FXML public DatePicker dataPickerStart;
    @FXML public DatePicker dataPickerEnd;
    @FXML public ComboBox<String> comboBoxActions;
    @FXML public TableView<Record> tableViewJournal;
    @FXML public TableView<Target> tableViewTargets;
    @FXML public TableView<String[]> tableViewCoef;
    @FXML public TableColumn<Target, String> tableTargetsColumnTarget;
    @FXML public TableColumn<Target, Integer> tableTargetsColumnTimeLeft;
    @FXML public TableColumn<Record, LocalDate> journalColumnDate;
    @FXML public TableColumn<Record, String> journalColumnAction;
    @FXML public TableColumn<Record, Integer> journalColumnDuration;
    @FXML public MenuItem menuFileItemQuit;
    @FXML public MenuItem menuEditItemClear;
    @FXML public MenuItem menuHelpAbout;
    @FXML public MenuItem menuEditActions;
    @FXML public MenuItem menuEditTargets;
    @FXML public MenuItem menuEditCoefficients;
    @FXML private static int SELECTED_TARGET;
    @FXML private static Stage STAGE_SECONDARY;

    @FXML
    public void initialize() {
        updateComboBoxActions();
        updateTableViewTargets();
        updateJournal();
        updateTableCoef();
    }

    public void comboBoxChanged() {
        buttonAct.setText(comboBoxActions.getSelectionModel().getSelectedItem()+"!");
        textFieldLog.setText("");
    }

    public void addRecord()
    {
        try {
            new Record(comboBoxActions.getValue(),  Integer.parseInt(textFieldDuration.getText()), LocalDate.now());
            RecordDao recordDao = new RecordDao();
            recordDao.update();
            updateJournal();
            updateTableViewTargets();
            textFieldDuration.setText("");
        }catch (Exception ignored){
            textFieldLog.setText("Error: wrong provided input");
        }
    }

    public static Stage getStageSecondary() {
        return STAGE_SECONDARY;
    }

    public int getSelectedTarget() {
        return SELECTED_TARGET;
    }

    public void getRecordsInInterval() {
        Record record = new Record();
        try{
            textFieldLog.clear();
            textAreaDate.appendText(record.recordsInInterval(dataPickerStart.getValue(),dataPickerEnd.getValue()));
            textAreaDate.appendText("---------------------------------\n");
        }
        catch (Exception ignored){
            textFieldLog.setText("Error: dates are not selected");
        }
    }

    public void setRandomCoefficient() {
        Target target = new Target();
        target.setRandomTargetsCoefficients();
        updateTableCoef();
    }

    public void editActions() throws IOException {
        Stage secondStage = new Stage();
        STAGE_SECONDARY = secondStage;
        secondStage.initModality(Modality.APPLICATION_MODAL);
        secondStage.setTitle("Editing actions");
        secondStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/dairy/view/editActions.fxml")).load()));
        secondStage.showAndWait();
        updateComboBoxActions();
        updateTableCoef();
    }

    public void editTargets() throws IOException {
        Stage secondStage = new Stage();
        STAGE_SECONDARY = secondStage;
        secondStage.initModality(Modality.APPLICATION_MODAL);
        secondStage.setTitle("Editing targets");
        secondStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/dairy/view/editTargets.fxml")).load()));
        secondStage.showAndWait();
        updateTableCoef();
        updateTableViewTargets();
    }

    public void editCoefficients() throws IOException {
        SELECTED_TARGET = tableViewCoef.getSelectionModel().getSelectedIndex();
        if(tableViewCoef.getSelectionModel().getSelectedIndex()>-1){
            Stage secondStage = new Stage();
            STAGE_SECONDARY = secondStage;
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.setTitle("Setting coefficients on target");
            secondStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/dairy/view/editCoefficients.fxml")).load()));
            secondStage.showAndWait();
            updateTableCoef();
        }else textFieldLog.setText("Error: target in table with coefficients is not selected");
    }

    public void updateComboBoxActions()
    {
        textFieldLog.clear();
        Action action = new Action();
        ObservableList<String> observableListActionUnique = FXCollections.observableArrayList();
        for (int i = 0; i < action.getActionsArrayList().size(); i++) {
            observableListActionUnique.add(action.getActionsArrayList().get(i).getActionName());
        }
        comboBoxActions.setItems(observableListActionUnique);
    }

    public void updateTableViewTargets()
    {
        textFieldLog.clear();
        Target target = new Target();
        ObservableList<Target> observableListTargets = FXCollections.observableArrayList();
        observableListTargets.addAll(target.getTargetsArrayList());
        tableTargetsColumnTimeLeft.setCellValueFactory(new PropertyValueFactory<>("timeOnTargetRequired"));
        tableTargetsColumnTarget.setCellValueFactory(new PropertyValueFactory<>("targetName"));
        tableViewTargets.setItems(observableListTargets);
        tableViewTargets.refresh();
    }

    public void updateJournal()
    {
        textFieldLog.clear();
        Record record = new Record();
        ObservableList<Record> observableListRecord = FXCollections.observableArrayList();
        observableListRecord.addAll(record.getJournal());
        journalColumnDate.setCellValueFactory(new PropertyValueFactory<>("recordDate"));
        journalColumnAction.setCellValueFactory(new PropertyValueFactory<>("recordName"));
        journalColumnDuration.setCellValueFactory(new PropertyValueFactory<>("recordDuration"));
        tableViewJournal.setItems(observableListRecord);
        tableViewJournal.refresh();
    }

    public void updateTableCoef()
    {
        textFieldLog.clear();
        Target target = new Target();
        Action action = new Action();
        ArrayList<ArrayList<String>> tableCoefficients = new ArrayList();
        for (int i = 0; i < target.getTargetsCoefficients().size(); i++) {
            tableCoefficients.add(new ArrayList<>());
            for (int j = 0; j < target.getTargetsCoefficients().get(i).size()+1; j++) {
                if (j==0)tableCoefficients.get(i).add(target.getTargetsArrayList().get(i).getTargetName());
                else tableCoefficients.get(i).add(target.getTargetsCoefficients().get(i).get(j-1).toString());
            }
        }
        try{
            String[][] dataArray=new String[tableCoefficients.size()][action.getActionsArrayList().size()+1];

            for (int i = 0; i < tableCoefficients.size(); i++) {
                dataArray[i] = tableCoefficients.get(i).toArray(new String[0]);
            }
            ObservableList<String[]> observableList = FXCollections.observableArrayList();
            observableList.addAll(Arrays.asList(dataArray));
            tableViewCoef.getColumns().clear();
            for (int i = 0; i < (dataArray[0].length); i++) {
                String tempName;
                if (i==0)tempName = "Your targets";
                else tempName = action.getActionsArrayList().get(i-1).getActionName();
                TableColumn tableColumn = new TableColumn(tempName);
                int columnNumber = i;
                tableColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>) cellDataFeatures
                        -> new SimpleObjectProperty<>((cellDataFeatures.getValue()[columnNumber])));
                tableViewCoef.getColumns().add(tableColumn);
            }
            tableViewCoef.setItems(observableList);
            tableViewCoef.refresh();
        }
        catch(Exception e){
            tableViewCoef.getColumns().clear();
            ObservableList<String[]> observableList = FXCollections.observableArrayList();
            tableViewCoef.setItems(observableList);
            textFieldLog.setText("Error: wrong input in tableViewCoef");
            e.printStackTrace();
        }
    }

    public void clear() {
        Action action = new Action();
        Target target = new Target();
        Record record = new Record();
        action.getActionsArrayList().clear();
        target.getTargetsCoefficients().clear();
        target.getTargetsArrayList().clear();
        record.getJournal().clear();
        target.addTarget("Sample target");
        action.addAction("Sample action");
        updateComboBoxActions();
        updateTableViewTargets();
        updateTableCoef();
        updateJournal();
        textAreaDate.clear();
    }

    public void about() {

    }

    public void quit() {
        Platform.exit();
    }
}

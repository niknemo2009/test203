package dairy.view.controllers;

import dairy.dao.TargetDao;
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

public class ControllerEditTargets {
    @FXML private TextField textFieldRenameTarget;
    @FXML private TextField textFieldSetTime;
    @FXML private TextField textFieldNewTarget;
    @FXML private TableView<Target> tableViewTargets;
    @FXML private TableColumn<Target, String> tableTargetsColumnTarget;
    @FXML private TableColumn<Target, Integer> tableTargetsColumnTimeLeft;
    private ArrayList<Target> targetsArrayList;
    private ArrayList<ArrayList<Double>> targetsCoefficients = new ArrayList<>();

    @FXML
    void initialize() {
        targetsArrayList = new ArrayList<>();
        Target target = new Target();
        targetsArrayList = cloneTargetsArrayList(target.getTargetsArrayList());
        targetsCoefficients = cloneTargetsCoefficients(target.getTargetsCoefficients());
        updateTableViewTargets();

    }

    private ArrayList<Target> cloneTargetsArrayList(ArrayList<Target> targetsArrayList) {
        ArrayList<Target> clone = new ArrayList<>(targetsArrayList.size());
        for (Target item:targetsArrayList) {
            try {
                clone.add((Target)item.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return clone;
    }

    private ArrayList<ArrayList<Double>> cloneTargetsCoefficients(ArrayList<ArrayList<Double>> targetsCoefficients) {
        ArrayList<ArrayList<Double>> clone= new ArrayList<>(targetsCoefficients.size());
        for (ArrayList<Double>item:targetsCoefficients) {
            clone.add((ArrayList<Double>) item.clone());
        }
        return clone;
    }


    void updateTableViewTargets()
    {
        ObservableList<Target> observableListTargets = FXCollections.observableArrayList();
        observableListTargets.addAll(targetsArrayList);
        tableTargetsColumnTarget.setCellValueFactory(new PropertyValueFactory<>("targetName"));
        tableTargetsColumnTimeLeft.setCellValueFactory(new PropertyValueFactory<>("timeOnTargetRequired"));
        tableViewTargets.setItems(observableListTargets);
        tableViewTargets.refresh();
    }

    @FXML
    void removeTarget() {
        try{
            if (tableViewTargets.getSelectionModel().getSelectedIndex()>-1)
            {
                targetsArrayList.remove(tableViewTargets.getSelectionModel().getSelectedIndex());
                targetsCoefficients.remove(tableViewTargets.getSelectionModel().getSelectedIndex());
                updateTableViewTargets();
            }
        }catch (Exception ignored){}
    }

    @FXML
    void setTimeOnTarget() {
        try {
            if (tableViewTargets.getSelectionModel().getSelectedIndex() > -1) {
                targetsArrayList.get (tableViewTargets.getSelectionModel().getSelectedIndex())
                        .setTimeOnTargetRequired(Integer.parseInt(textFieldSetTime.getText()));
                updateTableViewTargets();
            }
        } catch (Exception ignored) {
        }
    }

    @FXML
    void addTarget() {
        if (!textFieldNewTarget.getText().equals("")) {
            targetsArrayList.add(new Target(textFieldNewTarget.getText()));
            textFieldNewTarget.clear();
            targetsCoefficients.add(new ArrayList<>());
            for (int i = 0; i < targetsCoefficients.get(0).size(); i++) {
                targetsCoefficients.get(targetsCoefficients.size()-1).add(0.0);
            }
            updateTableViewTargets();
        }
    }

    @FXML
    void renameTarget() {
        try {
            if (tableViewTargets.getSelectionModel().getSelectedIndex() > -1) {
                targetsArrayList.get (tableViewTargets.getSelectionModel().getSelectedIndex())
                        .setTargetName(textFieldRenameTarget.getText());
                updateTableViewTargets();
            }
        } catch (Exception ignored) {
        }
    }

    @FXML
    void accept() {
        Target target = new Target();
        target.getTargetsArrayList().clear();


        target.getTargetsArrayList().addAll(targetsArrayList);
        TargetDao targetDao = new TargetDao();
        targetDao.update(targetsArrayList);

        target.getTargetsCoefficients().clear();
        target.getTargetsCoefficients().addAll(targetsCoefficients);

        targetsArrayList.clear();
        targetsCoefficients.clear();
        ControllerMain.getStageSecondary().close();
    }

    @FXML
    void cancel() {
        targetsArrayList.clear();
        targetsCoefficients.clear();
        ControllerMain.getStageSecondary().close();
    }
}

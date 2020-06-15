package dairy;

import dairy.dao.ActionDao;
import dairy.dao.DairyDao;
import dairy.dao.RecordDao;
import dairy.dao.TargetDao;
import dairy.model.Action;
import dairy.model.Record;
import dairy.model.Target;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Dairy extends Application {
    private static String URL_DB = ".\\src\\dairy\\resources\\dairy.db";

    public static String getUrlDb() {
        return URL_DB;
    }

    private void loadDairy(Stage primaryStage){
        System.out.println("Loading dairy.fxml...");
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/dairy/view/dairy.fxml"));
            primaryStage.setTitle("dairy");
            primaryStage.setScene(new Scene(root, 720, 580 ));
            primaryStage.show();
            System.out.println("Successfully loaded dairy.fxml.");
        }catch (Exception e){
            System.out.println("Can't load dairy.fxml.");
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        DairyDao dairyDao = new DairyDao();
        TargetDao targetDao = new TargetDao();
        ActionDao actionDao = new ActionDao();
        RecordDao recordDao = new RecordDao();
        targetDao.select();
        actionDao.select();
        recordDao.select();
        dairyDao.openDB();
        //targetDao.insertTarget("sample_target_1", 200);
        loadDairy(primaryStage);
        //dairyDao.clearDB();
    }

    public static void main(String[] args) {

//        Target target = new Target();
//        target.addTarget("Отдых",360);
//        target.addTarget("Чтение",240);
//        Action action = new Action();
//        action.addAction("Читать комиксы");
//        action.addAction("Читать новости");
//        action.addAction("Читать учебник");
//        target.setRandomTargetsCoefficients();
//        new Record(action.getActionsArrayList().get(0).getActionName(), 80, LocalDate.now());
//        new Record(action.getActionsArrayList().get(1).getActionName(),120,LocalDate.of(2020,3,15));
//        new Record(action.getActionsArrayList().get(2).getActionName(),90,LocalDate.of(2020,3,12));

        launch(args);
    }
}

package dairy.dao;

import dairy.Dairy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DairyDao {
    private Connection connection = null;

    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ Dairy.getUrlDb());
        } catch (SQLException e) {
            System.out.println("Can't make connection.");
            e.printStackTrace();
        }
    }

    public void openDB(){
        System.out.println("Opening data base "+ Dairy.getUrlDb() +"...");
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ Dairy.getUrlDb());
            System.out.println("Successfully opened data base.");
            connection.close();
        }
        catch(Exception e){
            System.out.println("Can't open database.");
            e.printStackTrace();
        }
    }

    public void clearDB(){
        try{
            TargetDao targetDao = new TargetDao();
            ActionDao actionDao = new ActionDao();
            RecordDao recordDao = new RecordDao();
            actionDao.clearActions();
            targetDao.clearTargets();
            recordDao.clearRecords();
        }
        catch (Exception e)
        {
            System.out.println("Can't clear data base.");
            e.printStackTrace();
        }
    }
}

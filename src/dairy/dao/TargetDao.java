package dairy.dao;

import dairy.Dairy;
import dairy.model.Target;

import java.sql.*;
import java.util.ArrayList;

public class TargetDao {
    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ Dairy.getUrlDb());
        } catch (SQLException e) {
            System.out.println("Can't make connection.");
            e.printStackTrace();
        }
    }

    public void insertTarget(String targetName, int timeOnTargetRequired){

        String query="INSERT INTO targets (targetName, timeOnTargetRequired) " +
                "VALUES ('"+targetName+"','"+timeOnTargetRequired+"') ";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Successfully inserted new row.");
        } catch (SQLException e) {
            System.out.println("Can't insert new row.");
            e.printStackTrace();
        }
    }

    public void select()
    {
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT targetName, timeOnTargetRequired " +
                    "FROM targets " +
                    "ORDER BY targetName ";
            ResultSet resultSet =  statement.executeQuery(query);
            Target target = new Target();
            while(resultSet.next())
            {
                String targetName = resultSet.getString("targetName");
                int timeOnTargetRequired = resultSet.getInt("timeOnTargetRequired");
                target.addTarget(targetName,timeOnTargetRequired);
            }
            resultSet.close();
            statement.close();
            System.out.println("Successfully selected.");
        }
        catch (Exception e)
        {
            System.out.println("Can't select.");
            e.printStackTrace();
        }
    }

    public void clearTargets(){
        try{
            Statement statement = connection.createStatement();
            String query = "DELETE FROM targets";
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e)
        {
            System.out.println("Can't clear table targets.");
        }
    }

    public void update(ArrayList<Target> targetsArrayList) {
        try{
            clearTargets();
            Statement statement = connection.createStatement();
            for (Target target : targetsArrayList) {
                String query = "INSERT INTO targets (targetName, timeOnTargetRequired) "
                        + "VALUES ('" + target.getTargetName()
                        + "','" + target.getTimeOnTargetRequired() + "') ";
                statement.executeUpdate(query);
            }
            statement.close();
        }
        catch (Exception e)
        {
            System.out.println("Can't update targets table.");
        }
    }
}

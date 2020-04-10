package dairy.dao;


import dairy.Dairy;
import dairy.model.Action;

import java.sql.*;
import java.util.ArrayList;

public class ActionDao {
    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ Dairy.getUrlDb());
        } catch (SQLException e) {
            System.out.println("Can't make connection.");
            e.printStackTrace();
        }
    }

    public void select()
    {
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT date, actionName, duration " +
                    "FROM records " +
                    "ORDER BY date ";
            ResultSet resultSet =  statement.executeQuery(query);
            Action action = new Action();
            while(resultSet.next())
            {
                String actionName = resultSet.getString("actionName");
                action.addAction(actionName);
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

    public void clearActions(){
        try{
            Statement statement = connection.createStatement();
            String query = "DELETE FROM actions";
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e)
        {
            System.out.println("Can't clear table actions.");
        }
    }

    public void update(ArrayList<Action> actionsArrayList) {
        try{
            clearActions();
            Statement statement = connection.createStatement();
            String query="";
            for (Action action : actionsArrayList) {
                query = "INSERT INTO actions (actionName) VALUES ('" + action.getActionName()+"') ";
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

package dairy.dao;

import dairy.Dairy;
import dairy.model.Record;

import java.sql.*;
import java.time.LocalDate;

public class RecordDao {
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
            while(resultSet.next())
            {
                String date = resultSet.getString("date");
                String actionName = resultSet.getString("actionName");
                int duration = resultSet.getInt("duration");
                new Record(actionName, duration, LocalDate.now());//СДЕОАЙ ПАРСЕР ДАТІ!!!!!!!!!!!!!!!
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

    public void clearRecords(){
        try{
            Statement statement = connection.createStatement();
            String query = "DELETE FROM records";
            statement.executeUpdate(query);
            statement.close();
        }
        catch (Exception e)
        {
            System.out.println("Can't clear table records.");
        }
    }

    public void update() {
        try{
            clearRecords();
            Statement statement = connection.createStatement();
            String query="";
            Record record = new Record();
            for (int i = 0; i < record.getJournal().size(); i++) {
                query = "INSERT INTO records (date, actionName, duration) VALUES ('"
                        + record.getJournal().get(i).getRecordDate().toString()+"','"
                        + record.getJournal().get(i).getRecordName()+"','"
                        + record.getJournal().get(i).getRecordDuration() +"') ";
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

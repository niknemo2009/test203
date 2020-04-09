package com.mycompany.mavenproject2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingTime {
    private Date ParkingStartD;
    private Date ParkingStopD;

    public Date getParkingStartD() {
        return ParkingStartD;
    }

    public Date getParkingStopD() {
        return ParkingStopD;
    }

    public ParkingTime(String StartDate, String StopDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Parsing the given String to Date object
        try {
            Date dateStart = formatter.parse(StartDate);
            Date dateStop = formatter.parse(StopDate);
            if (dateStop.after(dateStart)) {
                ParkingStartD = dateStart;
                ParkingStopD = dateStop;
            } else throw new ParseException("incorrect date vector", 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

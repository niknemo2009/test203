package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.Car;
import java.sql.Time;

public class Sectors {
    public static Character sectorName = 'A';
    private Integer[][] carsUseMask;
    private ParkingTime[][] TimeMask;
    private  Character currSectorName = ' ';
    private float carCount=0;
    public Sectors()
    {

    }

    public Character getSectorName() {
        return currSectorName;
    }

    //    public int GetParkingPlace(int index) {
//        return carsUseMask[index];
//    }
    public boolean SetParkingPlace(int index, Car carModel, ParkingTime parkingTime) {
        int rowIndex=(int)((index/carCount)+(index%carCount-1));
        int columnIndex=((int)(index%carCount));
        for (int i = 1; i <= carModel.CarSize; i++) {
            if (carsUseMask[rowIndex][columnIndex] == 0)
//                    && index + i < carsUseMask.length && index + i < carsUseMask[index].length
//                    || index - i > 0 && index - i > 0) //free place
            {
                //2DSP ??
                carsUseMask[rowIndex][columnIndex] = 1;
                carsUseMask[rowIndex][columnIndex+i-1] = 1;

                //set time
                TimeMask[rowIndex][columnIndex] = parkingTime;
                TimeMask[rowIndex][columnIndex + i-1] = parkingTime;
                return true;
            }
        }
        return false;
    }

    public void Rebuild(Integer carCount) {
        this.carCount=carCount;
        carsUseMask = new Integer[carCount][carCount];
        for(int i = 0; i < carsUseMask.length; ++i)
    for(int j = 0; j < carsUseMask[i].length; ++j)
        carsUseMask[i][j] = new Integer(0);
        
        TimeMask = new ParkingTime[carCount][carCount];
        if (currSectorName == ' ') {
            currSectorName = sectorName++; //rename  new sector
        }
    }
}

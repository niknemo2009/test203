package com.mycompany.mavenproject2;

public class ParkingSet {
    Character Sector;
    Integer SectorNumber;
    Car car;
    ParkingTime timeInterval;
    Integer cost;

    public ParkingSet(Character Sector, Integer SectorNumber, Car car, ParkingTime time) {
        this.Sector = Sector;
        this.SectorNumber = SectorNumber;
        this.timeInterval=time;
        this.car = car;
        this.cost = Math.toIntExact(time.getParkingStopD().getTime()/3600 - time.getParkingStartD().getTime()/3600) * Terminal.getRate();
    }

}

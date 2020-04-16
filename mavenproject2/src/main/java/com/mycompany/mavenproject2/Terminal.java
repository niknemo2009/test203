package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.Car;

import javax.naming.spi.NamingManager;
import java.util.*;

public class Terminal {

    private static Map<Integer, ParkingSet> activeCarsSets = new HashMap<>();
    private static Random rnd = new Random();
    private static Integer rate = 10;
    private static Map<String, List<Integer>> Owners = new HashMap<>();

    public static Map<String, List<Integer>> getOwners() {
        return Owners;
    }

    public static Integer getRate() {
        return rate;
    }

    public HashMap<Integer, Integer> RegisterNewCar(String ParkingNmae,Character SectorName, int NumberAtSector, Car car, ParkingTime timeInterval, String OwnerName) {
        var sector = GetSectorByName(SectorName);
        if (sector != null) {
            Integer nextQueueNumber = rnd.nextInt(10000);
            sector.SetParkingPlace(NumberAtSector, car, timeInterval);
            activeCarsSets.put(nextQueueNumber, new ParkingSet(ParkingNmae,SectorName, NumberAtSector, car, timeInterval));
            if (Owners.containsKey(OwnerName)) {
                Owners.get(OwnerName).add(nextQueueNumber);
            } else {
                Owners.put(OwnerName, new ArrayList<>());
                Owners.get(OwnerName).add(nextQueueNumber);
            }

            var hmap = new HashMap<Integer, Integer>();
            hmap.put(0, nextQueueNumber);
            return hmap;
        } else {
            var hmap = new HashMap<Integer, Integer>();
            hmap.put(0, -1);
            return hmap;
        }
    }

    public List<ParkingSet> GetCars(String CarName) {
        List<ParkingSet> CarsSet = new ArrayList<>();
        for (var d : activeCarsSets.values()) {
            if (d.car.CarName == CarName) {
                CarsSet.add(d);
            }
        }
        return CarsSet;

    }
    public Collection<ParkingSet> GetAllCars()
    {
        return activeCarsSets.values();
    }

    public List<ParkingSet> GetReportForPeriod(ParkingTime timePeriod) {
        List<ParkingSet> CarsSet = new ArrayList<>();
        for (var d : activeCarsSets.values()) {
            if (d.timeInterval.getParkingStartD().getTime() - timePeriod.getParkingStartD().getTime() < 10
                    && d.timeInterval.getParkingStopD().getTime() - timePeriod.getParkingStopD().getTime() < 10) {
                CarsSet.add(d);
            }
        }
        return CarsSet;
    }

    public ParkingSet GetCarData(Integer QueueNumber) {
        return activeCarsSets.get(QueueNumber);
    }

    private Sectors GetSectorByName(Character sectorName) {
        for (int i = 0; i < Parking.getSectorsSquere().size(); i++) {
            if (Parking.getSectorsSquere().get(i).getSectorName() == sectorName) {
                return Parking.getSectorsSquere().get(i);
            }
        }
        return null;
    }
}

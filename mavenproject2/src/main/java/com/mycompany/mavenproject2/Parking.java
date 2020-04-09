package com.mycompany.mavenproject2;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private static List<Sectors> sectorsSquere = new ArrayList<>();
    private static Terminal terminal = new Terminal();
    private int maxSectors = 26;
    int ParkObj_sectorCount;

    public static List<Sectors> getSectorsSquere() {
        return sectorsSquere;
    }

    public Parking(int sectorsCount, int carPerSector) throws Exception {
        ParkObj_sectorCount=sectorsCount;
        for (int i = 0; i < sectorsCount; i++) {
            sectorsSquere.add(new Sectors());
        }
        if (sectorsCount < maxSectors && carPerSector > 0) {
            
            for (int i = 0; i < sectorsSquere.size(); i++) {
                sectorsSquere.get(i).Rebuild(carPerSector);
            }
        } else {
            throw new Exception();
        }

    }

    public Terminal TerminalSession() {
        return this.terminal;
    }


}

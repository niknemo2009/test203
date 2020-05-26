/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author BadCatss
 */
public class TestsForParking {

    @Test
    public void testSectorsSquareListEmpty(List<Sectors> list) {
        if (list.isEmpty() || list.size() == 0) {
            System.err.println("testSectorsSquareListEmpty failed");
        } else {
            System.out.println("testSectorsSquareListEmpty passed");
        }

    }
    @Test
    public void testSectorsSquareListOversize(List<Sectors> list) {
        if (list.size() > Integer.MAX_VALUE) {
            System.err.println("testSectorsSquareListOversize failed");
        } else {
            System.out.println("testSectorsSquareListOversize passed");
        }
    }
    @Test
    public void testSectorNameValid(Character sectorName) {
        if (sectorName.equals(" ") || sectorName<Integer.parseInt("Z") ) {
             System.err.println("testSectorName failed");
        }
        else {
            System.out.println("testSectorName passed");
        }
        
    }
    @Test
    public void testOwnersCorrect(Map<String, List<Integer>> owners)
    {
        if (owners.size()==0 || owners.isEmpty() || owners.keySet().contains(" ")) 
        {
             System.err.println("testOwnersCorrect failed");
        }
        else {
            System.out.println("testOwnersCorrect passed");
        }
    }

    
}

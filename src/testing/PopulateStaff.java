/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import database.SerializeDB;
import entity.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nyinyithwin
 */
public class PopulateStaff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /////////////////////////////// STAFF //////////////////////////////////////////
        // Create new data
        List<Staff> staffList = new ArrayList();
        Staff staff = new Staff("admin", "staff1", "staff1", "staff1@email", 123);
        // add to list
        staffList.add(staff);

        SerializeDB.writeSerializedObject("Staff.ser", staffList);
    }
    
}

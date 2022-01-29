/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import GUI.GuiTest;
import adb.connection.AdbConnection;
import adb.networking.AdbShell;
import java.io.IOException;
import pc.Device;


/**
 *
 * @author root
 */
public class Simulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
//        System.out.println("Connected Devices \n "+ new AdbConnection().connectedDevices());
//        System.out.println("ADB Ping " + new AdbShell().ping());
        GuiTest guiTest = new GuiTest();
        guiTest.setSize(500,500);
        guiTest.show();
        guiTest.setAlwaysOnTop(true);
    }
    
}

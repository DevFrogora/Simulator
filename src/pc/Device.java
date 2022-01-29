/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc;

/**
 *
 * @author root
 */
import java.io.IOException;

/**
 *
 * @author root
 */
public class Device {

    public boolean isWindowsDevice() {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        //        ProcessBuilder pb = new ProcessBuilder("adb", "-s", "0123456789ABCDEF", "push");
        return isWindows;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adb.connection;

import codeUtils.StringUtils;
import java.io.IOException;
import pc.Device;

/**
 *
 * @author root
 */
public class AdbServer {
    StringUtils stringUtils = new StringUtils();
    ProcessBuilder processBuilder = new ProcessBuilder();
    Device device= new Device();

    public String killServer() throws IOException {
        String cmd = "adb kill-server";
        if (device.isWindowsDevice()) {
            processBuilder.command("cmd.exe", "/c", cmd);
        } else {
            processBuilder.command("bash", "-c", cmd);
        }
        Process process = processBuilder.start();
        return stringUtils.inputStreamToString(process.getInputStream()).toString();
    }
}

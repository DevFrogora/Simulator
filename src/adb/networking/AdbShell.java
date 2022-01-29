/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adb.networking;

import codeUtils.StringUtils;
import java.io.IOException;
import pc.Device;

/**
 *
 * @author root
 */
public class AdbShell {
    StringUtils stringUtils = new StringUtils();
    ProcessBuilder processBuilder = new ProcessBuilder();
    Device device= new Device();

    public String ping() throws IOException {
        String cmd = "adb shell ping";
        if (device.isWindowsDevice()) {
            processBuilder.command("cmd.exe", "/c", cmd);
        } else {
            processBuilder.command("bash", "-c", cmd);
        }
        Process process = processBuilder.start();
        return stringUtils.inputStreamToString(process.getInputStream()).toString();
    }
}

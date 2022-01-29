/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.socket;

import codeUtils.StringUtils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Server {

    StringUtils stringUtils = new StringUtils();
    ServerSocket serverSocket;
    InputStreamReader inputStreamSocketReader;
    Socket socket;
    boolean serverClosed;
    int count;

    public void connect() {
        try {
            serverSocket = new ServerSocket(9556);
            serverClosed = false;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDataOnAlive() {
        try {
            count++;
//            while (true) {
            System.out.println("pc.socket.Server.getDataOnAlive() before" +count);
            if(!serverClosed){
                try{
                     socket = serverSocket.accept();
                }catch(SocketException e){
                    System.out.println("Socket Closed already");
                    return null;
                }
            System.out.println("pc.socket.Server.getDataOnAlive() After"+count);
            String message = stringUtils.inputStreamToString(socket.getInputStream()).toString();
//            }
            return message;
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public boolean killSocket() {
        try {
//            socket.close();
            serverClosed = true;
            if(socket != null){
                socket.getInputStream().close();
                socket.close();
            }
            serverSocket.close();

            return true;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

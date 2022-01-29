/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import adb.connection.AdbConnection;
import adb.connection.AdbServer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import pc.socket.Server;

/**
 *
 * @author root
 */
public class GuiTest extends JFrame {

    int count = 0;
    Server server;
    JTextArea jTextArea;

    public GuiTest() throws IOException {
        this.setLayout(new BorderLayout());
        jTextArea = new JTextArea();
        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        JButton jButton = new JButton("List Of Devices");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the contents of the JTextArea component.
                    jTextArea.append(new AdbConnection().connectedDevices());
//                    jTextArea.append("");
                } catch (IOException ex) {
                    Logger.getLogger(GuiTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("SetConnectText " + count++);
            }
        });

        JButton jButtonKillServer = new JButton("Kill Server");
        jButtonKillServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the contents of the JTextArea component.
                    jTextArea.append(new AdbServer().killServer());
//                    jTextArea.append("");
                } catch (IOException ex) {
                    Logger.getLogger(GuiTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("ServerGotKilled " + count++);
            }
        });

        JButton jButtonServerSocket = new JButton("Connect Server");
        server = new Server();
        jButtonServerSocket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                server.connect();
                jTextArea.append("Server Connected");
                start();
                System.out.println("ServerSocketCOnnected " + count++);
            }
        });

        JButton jButtonServerSocketKill = new JButton("Socket Server Kill");
        jButtonServerSocketKill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the contents of the JTextArea component.
                String message = "Server killed " + server.killSocket();
                jTextArea.append(message);
//                    jTextArea.append("");
//                System.out.println("ServerSocketCOnnected "+ count++);
            }
        });

        jPanelLeft.add(jButtonKillServer);
        jPanelLeft.add(jButtonServerSocket);
        jPanelLeft.add(jButtonServerSocketKill);
        jPanelLeft.add(jButton);
        add(jTextArea, BorderLayout.CENTER);
        add(jPanelLeft, BorderLayout.EAST);
    }

    private void start() {
        SwingWorker<Boolean, String> worker = new SwingWorker<Boolean, String>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                String message;
                while ( (message = server.getDataOnAlive()) != null ) {
//                     = server.getDataOnAlive();
                    publish( message);
                }
                
                return true;
            }

            @Override
            protected void process(List<String> chunks) {
                String message = chunks.get(chunks.size() -1);
                jTextArea.append(message);
            }
            
            @Override
            protected void done() {
                System.err.println("Done");
                jTextArea.append("\n Completed from GUi");
            }
            
        };
        worker.execute();
    }

}

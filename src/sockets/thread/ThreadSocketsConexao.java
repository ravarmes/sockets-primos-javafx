/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import static sockets.thread.FXMLServidorController.qtdClientes;

/**
 *
 * @author ravar
 */
public class ThreadSocketsConexao extends Thread {

    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(54321);
            System.out.println("A porta 54321 foi aberta!");
            System.out.println("Servidor, com Thread, esperando receber mensagens de vários clientes...");
            while (FXMLServidorController.qtdClientes < 4) {
                Socket socket = serverSocket.accept();
                //Mostrando endereço IP do cliente conectado
                System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + " conectado");
                FXMLServidorController.qtdClientes++;

                int limiteSuperior = FXMLServidorController.qtdClientes * 25000;
                int limiteInferior = limiteSuperior - 24999;

                ThreadSocketsServidor thread = new ThreadSocketsServidor(socket, limiteInferior, limiteSuperior, FXMLServidorController.vetLabelStatus[qtdClientes-1], FXMLServidorController.vetLabelQtd[qtdClientes-1]);
                thread.start();
                
                Platform.runLater(()->FXMLServidorController.vetLabelStatus[qtdClientes-1].setText("Calculando..."));

            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadSocketsConexao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

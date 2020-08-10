/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author ravar
 */
public class ThreadSocketsCliente extends Thread {

    private int limiteInferior;
    private int limiteSuperior;
    private Label labelClienteID;
    private Label labelClienteStatus;
    private String ip;
    private int porta;
    
    
    public ThreadSocketsCliente(String ip, int porta, Label labelClienteID, Label labelClienteStatus) {
        this.ip = ip;
        this.porta = porta;
        this.labelClienteID = labelClienteID;
        this.labelClienteStatus = labelClienteStatus;
    }
    
    @Override
    public void run() {
        try (Socket socket = new Socket(ip, porta)) {
            
            //Entrada de Dados no ClienteBackup
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            limiteInferior = entrada.readInt();//Recebendo limite inferior do Servidor
            limiteSuperior = entrada.readInt();//Recebendo limite superior do Servidor

            System.out.println("Cliente calculando primos no intervalo: [" + limiteInferior + "," + limiteSuperior + "]");
            Platform.runLater(()->labelClienteID.setText((limiteSuperior/25000)+""));
            Platform.runLater(()->labelClienteStatus.setText("Conectado"));
            
            sleep();
            
            //Saída de Dados do Servidor
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            
            Integer qtdPrimos = calcularQtdPrimos(limiteInferior, limiteSuperior);
            System.out.println("Quantidade de primos: " + qtdPrimos);
            saida.writeInt(qtdPrimos);
            
            Platform.runLater(()->labelClienteStatus.setText("Finalizado"));

            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadSocketsCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static Integer calcularQtdPrimos(int li, int ls) {
        int qtdPrimos=0;
        int qtdDivisores;
        
        for (int i = li; i <= ls; i++) {
            qtdDivisores = 0;
            for(int j= 1; j<= i; j++){
                if(i%j==0){
                    qtdDivisores++;
                }
            }
            if(qtdDivisores == 2){
                qtdPrimos++;
            }
        }
        return qtdPrimos;
    }
    
    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadSocketsCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

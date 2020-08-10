package sockets.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class ThreadSocketsServidor extends Thread {
    
    private Socket socket;
    private int limiteInferior;
    private int limiteSuperior;
    private Label labelStatus;
    private Label labelQtd;
    private int qtdTotalAtual;
    
    public ThreadSocketsServidor(Socket s, int li, int ls, Label labelStatus, Label labelQtd) {
        this.socket = s;
        this.limiteInferior = li;
        this.limiteSuperior = ls;
        this.labelStatus = labelStatus;
        this.labelQtd = labelQtd;
    }
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("LabelStatus: " + this.labelStatus.getText());
        try {

            //Saída de Dados do Servidor
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            saida.writeInt(limiteInferior);
            saida.writeInt(limiteSuperior);

            //Entrada de Dados no Servidor
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            Integer quantidade = entrada.readInt(); //Recebendo a quantidade de números primos do intervalo

            Platform.runLater(()->labelQtd.setText(String.valueOf(quantidade)));
            
            qtdTotalAtual = Integer.parseInt(FXMLServidorController.labelTotal.getText());
            qtdTotalAtual = qtdTotalAtual + quantidade;
            Platform.runLater(()->FXMLServidorController.labelTotal.setText("" + qtdTotalAtual));
           
            Platform.runLater(()->labelStatus.setText("Finalizado!"));

           
            socket.close();
        } catch (IOException ioe) {
            System.out.println("Erro: " + ioe.toString());
        } 
    }
    
    void mostrarVetorPrimos(Vector vetor) {
        for (int i = 0; i < vetor.size(); i++) {
            System.out.println(vetor.get(i));
        }
    }
}

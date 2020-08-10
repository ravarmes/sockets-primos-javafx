/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.thread;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ravar
 */
public class FXMLServidorController implements Initializable {

    static int qtdClientes = 0;//Criado para saber qual intervalo passar para o cálculo dos primos
    @FXML
    private Button buttonIniciarServidor;
    @FXML
    private TextField textFieldPorta;
    @FXML
    private Label labelCliente1Status;
    @FXML
    private Label labelCliente2Status;
    @FXML
    private Label labelCliente3Status;
    @FXML
    private Label labelCliente4Status;
    @FXML
    private Label labelCliente5Status;
    @FXML
    private Label labelCliente1Qtd;
    @FXML
    private Label labelCliente2Qtd;
    @FXML
    private Label labelCliente3Qtd;
    @FXML
    private Label labelCliente4Qtd;
    @FXML
    private Label labelCliente5Qtd;
    @FXML
    public Label labelTotalQtd;

    public static Label vetLabelStatus[] = new Label[5];
    public static Label vetLabelQtd[] = new Label[5];
    public static Label labelTotal = new Label();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        vetLabelStatus[0] = this.labelCliente1Status;
        vetLabelStatus[1] = this.labelCliente2Status;
        vetLabelStatus[2] = this.labelCliente3Status;
        vetLabelStatus[3] = this.labelCliente4Status;
        vetLabelStatus[4] = this.labelCliente5Status;

        vetLabelQtd[0] = this.labelCliente1Qtd;
        vetLabelQtd[1] = this.labelCliente2Qtd;
        vetLabelQtd[2] = this.labelCliente3Qtd;
        vetLabelQtd[3] = this.labelCliente4Qtd;
        vetLabelQtd[4] = this.labelCliente5Qtd;
        
        labelTotal = this.labelTotalQtd;

    }

    @FXML
    public void handleButtonIniciarServidor() throws IOException {
        ThreadSocketsConexao thread = new ThreadSocketsConexao();
        thread.start();
    }

}

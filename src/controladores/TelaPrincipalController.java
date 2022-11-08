/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Kayn Malícias
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private Button btCompra;

    @FXML
    private Button btVenda;

    @FXML
    private Button btVisualizar;

    @FXML
    private Button btGestao;

    @FXML
    private StackPane pilhaPainel;

    @FXML
    private void TelaInicio(ActionEvent event) {
        carregarPagina("TelaInicio");
    }

    @FXML
    private void TelaCompra(ActionEvent event) {
        carregarPagina("TelaCompra");
    }

    @FXML
    private void TelaVenda(ActionEvent event) {
        carregarPagina("TelaVenda");
    }

    @FXML
    private void TelaVisualizar(ActionEvent event) {
        carregarPagina("TelaVisualizar");
    }

    @FXML
    private void TelaGestao(ActionEvent event) {
        carregarPagina("TelaGestao");
    }

    private void carregarPagina(String name) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/" + name + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        pilhaPainel.getChildren().clear();
        pilhaPainel.getChildren().add(root);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

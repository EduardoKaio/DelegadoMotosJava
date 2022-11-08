/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.MotoDAO;
import dao.VendaDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Moto;
import model.Venda;

/**
 * FXML Controller class
 *
 * @author Kayn Malícias
 */
public class TelaVendaController implements Initializable {

    @FXML
    private TableView<Moto> tableMotos;
    @FXML
    private TableColumn<?, ?> colunaAno;
    @FXML
    private TableColumn<?, ?> colunaId;
    @FXML
    private TableColumn<?, ?> colunaModelo;
    @FXML
    private TableColumn<?, ?> colunaMarca;
    @FXML
    private TableColumn<?, ?> colunaCor;
    @FXML
    private TableColumn<?, ?> colunaCilindradas;
    @FXML
    private TableColumn<?, ?> colunaQuilometragem;
    @FXML
    private TableColumn<?, ?> colunaPlaca;
    @FXML
    private TextField txtNome_comprador;
    @FXML
    private TextField txtData_venda;
    @FXML
    private TextField txtValor_venda;
    @FXML
    private Button btVender;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarDadosTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarDadosTabela() throws ClassNotFoundException {
        tableMotos.getItems().clear();
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id_moto"));
        colunaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colunaCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colunaCilindradas.setCellValueFactory(new PropertyValueFactory<>("cilindradas"));
        colunaQuilometragem.setCellValueFactory(new PropertyValueFactory<>("quilometragem"));
        colunaPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<>("ano"));

        MotoDAO motoDao = new MotoDAO();
        ArrayList<Moto> motos = motoDao.buscarTodos();
        System.out.println("˜˜carregando dados----" + motos.size());

        //comando para passar para javaFx
        ObservableList<Moto> itensAnimalFX = FXCollections.observableArrayList(motos);
        // Jogar na tabela.
        tableMotos.setItems(itensAnimalFX);
    }

    public void vender(ActionEvent event) throws ClassNotFoundException {

        // Pegando os campos preenchidos
        if (txtNome_comprador == null || txtValor_venda == null || txtData_venda == null) {
            
            Alert erroAlert = new Alert(Alert.AlertType.ERROR);
            erroAlert.setContentText("Preencha os campos acima");
            erroAlert.showAndWait();    
            
        } else {
            String nome_comprador = txtNome_comprador.getText();
            String valor_venda = txtValor_venda.getText();
            String data_venda = txtData_venda.getText();

            // criando uma venda e preenchendo 
            Venda venda = new Venda(valor_venda, data_venda, nome_comprador);
            
            //chamando o dao 
            VendaDAO vendadao = new VendaDAO();
            vendadao.create(venda);

            limparDadosFormulario();

            Moto moto = tableMotos.getSelectionModel().getSelectedItem();
            MotoDAO dao = new MotoDAO();
            
            
            //apagar a moto vendida
            if (moto == null) {
                Alert erroAlert = new Alert(Alert.AlertType.ERROR);
                erroAlert.setContentText("Selecione um moto para vender");
                erroAlert.showAndWait();
            } else {
                
                dao.delete(moto.getId_moto());
                carregarDadosTabela();
            }
        }
    }

    private void limparDadosFormulario() {
        txtData_venda.setText("");
        txtValor_venda.setText("");
        txtNome_comprador.setText("");
    }

}

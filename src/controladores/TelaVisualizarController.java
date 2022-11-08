/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.CompraDAO;
import dao.MotoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Compra;
import model.Moto;

/**
 * FXML Controller class
 *
 * @author Kayn Malícias
 */
public class TelaVisualizarController implements Initializable {

    @FXML
    private TableView<Moto> tableMotos;

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
    private TableColumn<?, ?> colunaAno;
    @FXML
    private Button btEditar;
    @FXML
    private Button btExcluir;

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

    public void remover(ActionEvent event) throws ClassNotFoundException {
        Moto moto = tableMotos.getSelectionModel().getSelectedItem();
        
        MotoDAO motodao = new MotoDAO();
        CompraDAO compradao = new CompraDAO();

        //apagar objeto
        if (moto == null) {
            Alert erroAlert = new Alert(Alert.AlertType.ERROR);
            erroAlert.setContentText("Selecione um moto para remover");
            erroAlert.showAndWait();
        } else {
           
            motodao.delete(moto.getId_moto());
            carregarDadosTabela();
        }
    }

    public void AbrirModal(ActionEvent event) throws IOException {

        MotoDAO dao = new MotoDAO();
        Moto moto = tableMotos.getSelectionModel().getSelectedItem();
        
        if (moto == null) {
            Alert erroAlert = new Alert(Alert.AlertType.ERROR);
            erroAlert.setContentText("Selecione uma moto para editar");
            erroAlert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/TelaCompra.fxml"));

            loader.load();

            TelaCompraController controller = loader.getController();

            controller.getTxtModelo().setText(moto.getModelo());
            controller.getTxtMarca().setText(moto.getMarca());
            controller.getTxtCor().setText(moto.getCor());
            controller.getTxtAno().setText(moto.getAno());
            controller.getTxtPlaca().setText(moto.getPlaca());
            controller.getTxtQuilometragem().setText(moto.getQuilometragem());
            controller.getTxtCilindradas().setText(moto.getCilindradas());
            
            controller.setUpdate(Boolean.TRUE);
            controller.setIdMoto(moto.getId_moto());

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }

    }

}

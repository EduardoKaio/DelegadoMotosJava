/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.CompraDAO;
import dao.MotoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Compra;
import model.Moto;

/**
 * FXML Controller class
 *
 * @author Kayn Malícias
 */
public class TelaCompraController implements Initializable {

    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtCilindradas;
    @FXML
    private Button btComprar;
    @FXML
    private TextField txtCor;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtQuilometragem;
    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtValor_compra;
    @FXML
    private TextField txtData_compra;
    @FXML
    private TextField txtNome_vendedor;
    
    private boolean update;

    private int idMoto;
    private int idCompra;

    public TextField getTxtAno() {
        return txtAno;
    }

    public void setTxtAno(TextField txtAno) {
        this.txtAno = txtAno;
    }

    public TextField getTxtModelo() {
        return txtModelo;
    }

    public void setTxtModelo(TextField txtModelo) {
        this.txtModelo = txtModelo;
    }

    public TextField getTxtCilindradas() {
        return txtCilindradas;
    }

    public void setTxtCilindradas(TextField txtCilindradas) {
        this.txtCilindradas = txtCilindradas;
    }

    public TextField getTxtCor() {
        return txtCor;
    }

    public void setTxtCor(TextField txtCor) {
        this.txtCor = txtCor;
    }

    public TextField getTxtMarca() {
        return txtMarca;
    }

    public void setTxtMarca(TextField txtMarca) {
        this.txtMarca = txtMarca;
    }

    public TextField getTxtQuilometragem() {
        return txtQuilometragem;
    }

    public void setTxtQuilometragem(TextField txtQuilometragem) {
        this.txtQuilometragem = txtQuilometragem;
    }

    public TextField getTxtPlaca() {
        return txtPlaca;
    }

    public void setTxtPlaca(TextField txtPlaca) {
        this.txtPlaca = txtPlaca;
    }

    public TextField getTxtValor_compra() {
        return txtValor_compra;
    }

    public void setTxtValor_compra(TextField txtValor_compra) {
        this.txtValor_compra = txtValor_compra;
    }

    public TextField getTxtData_compra() {
        return txtData_compra;
    }

    public void setTxtData_compra(TextField txtData_compra) {
        this.txtData_compra = txtData_compra;
    }

    public TextField getTxtNome_vendedor() {
        return txtNome_vendedor;
    }

    public void setTxtNome_vendedor(TextField txtNome_vendedor) {
        this.txtNome_vendedor = txtNome_vendedor;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public int getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(int idMoto) {
        this.idMoto = idMoto;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ComprarMoto(ActionEvent event) throws ClassNotFoundException {

        // Pegando os campos preenchidos
        String modelo = txtModelo.getText();
        String marca = txtMarca.getText();
        String ano = txtAno.getText();
        String cor = txtCor.getText();
        String placa = txtPlaca.getText();
        String cilindradas = txtCilindradas.getText();
        String quilometragem = txtQuilometragem.getText();
        String data_compra = txtData_compra.getText();
        String valor_compra = txtValor_compra.getText();
        String nome_vendedor = txtNome_vendedor.getText();

        // criando uma moto e compra e preenchendo 
        Moto moto = new Moto(modelo, marca,cor, ano,  placa, cilindradas, quilometragem);

        Compra compra = new Compra(valor_compra, data_compra,  nome_vendedor);

        //chamando o dao 
        MotoDAO motodao = new MotoDAO();
        CompraDAO compradao = new CompraDAO();

        if (update) {
            moto.setId_moto(idMoto);
            compra.setId_compra(idCompra);
            
            motodao.update(moto);
            compradao.update(compra);
            
            fecharModal();
        } else {
            motodao.create(moto);
            compradao.create(compra);
        }

        limparDadosFormulario();
    }
    
    public void fecharModal() {
        Stage stage = (Stage) btComprar.getScene().getWindow();
        stage.close();
    }

    private void limparDadosFormulario() {
        txtModelo.setText("");
        txtMarca.setText("");
        txtAno.setText("");
        txtCor.setText("");
        txtCilindradas.setText("");
        txtData_compra.setText("");
        txtNome_vendedor.setText("");
        txtPlaca.setText("");
        txtQuilometragem.setText("");
        txtValor_compra.setText("");
        
    }

}

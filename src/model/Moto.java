package model;

public class Moto {

    private Integer id_moto;
    private String modelo;
    private String marca;
    private String cor;
    private String ano;
    private String placa;
    private String cilindradas;
    private String quilometragem;

    public Moto() {
    }

    public Moto(String modelo, String marca, String cor, String ano, String placa, String cilindradas, String quilometragem) {
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
        this.ano = ano;
        this.placa = placa;
        this.cilindradas = cilindradas;
        this.quilometragem = quilometragem;
    }

    public Integer getId_moto() {
        return id_moto;
    }

    public void setId_moto(Integer id_moto) {
        this.id_moto = id_moto;
    }


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(String cilindradas) {
        this.cilindradas = cilindradas;
    }

    public String getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(String quilometragem) {
        this.quilometragem = quilometragem;
    }

}

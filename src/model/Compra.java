package model;

public class Compra {
    private Integer id_compra;
    private String valor_compra;
    private String data_compra;
    private String nome_vendedor;
    
    public Compra(){
    }
    
    public Compra (String valor_compra, String data_compra, String nome_vendedor){
    this.valor_compra= valor_compra;
    this.data_compra = data_compra;
    this.nome_vendedor = nome_vendedor;
    }

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public String getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(String valor) {
        this.valor_compra = valor_compra;
    }

    public String getData_compra() {
        return data_compra;
    }

    public void setData_compra(String data_compra) {
        this.data_compra = data_compra;
    }

    public String getNome_vendedor() {
        return nome_vendedor;
    }

    public void setNome_vendedor(String nome_vendedor) {
        this.nome_vendedor = nome_vendedor;
    }
}

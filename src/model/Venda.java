package model;

public class Venda {
    private Integer id_venda;
    private String valor_venda;
    private String data_venda;
    private String nome_comprador;
    
    public Venda(){
    }
    
    public Venda (String valor_venda, String data_venda, String nome_comprador){
    this.valor_venda = valor_venda;
    this.data_venda = data_venda;
    this.nome_comprador = nome_comprador;
    }
    
    public Integer getId_venda() {
        return id_venda;
    }

    public void setId_venda(Integer id_venda) {
        this.id_venda = id_venda;
    }

    public String getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(String valor_venda) {
        this.valor_venda = valor_venda;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public String getNome_comprador() {
        return nome_comprador;
    }

    public void setNome_comprador(String nome_comprador) {
        this.nome_comprador = nome_comprador;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Venda;

public class VendaDAO {
     public ArrayList<Venda> buscarTodos() throws ClassNotFoundException {
        String sql = "SELECT * FROM Venda";
        // Responsável em guardar o resultado
        ResultSet resultado = null;

        ArrayList<Venda> lista = new ArrayList<Venda>();

        Connection conn = FabricaConexao.getConnection();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            resultado = ps.executeQuery();

            while (resultado.next()) {
                //Antes a gente estava imprimindo.
                // Agora estamos guardando no arrayList
                Venda a = new Venda();
                a.setId_venda(resultado.getInt("id_venda"));
                a.setValor_venda(resultado.getString("valor_venda"));
                a.setData_venda(resultado.getString("data_venda"));
                a.setNome_comprador(resultado.getString("nome_comprador"));

                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FabricaConexao.fecharConexao(conn);

        return lista;
    }

    public Venda getById(Integer id) throws ClassNotFoundException {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * FROM Venda WHERE id=?";
        ResultSet resultado = null;
        Venda venda = null;
        try (Connection conn = FabricaConexao.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            resultado = ps.executeQuery();
            resultado.next();

            Venda a = new Venda();

            a.setId_venda(resultado.getInt("id_venda"));
            a.setValor_venda(resultado.getString("valor_venda"));
            a.setData_venda(resultado.getString("data_venda"));
            a.setNome_comprador(resultado.getString("nome_comprador"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(resultado);
        }
        return venda;
    }
    
    
    
    public boolean create(Venda venda) throws ClassNotFoundException{
       
        try{
            String comando = "INSERT INTO Venda (valor_venda, data_venda, nome_comprador)"
                    + "VALUES (?, ?, ?)";
            
            Connection conn = FabricaConexao.getConnection();
            //revisor DE  SQL
            PreparedStatement ps = conn.prepareStatement(comando);
            // substituindo as ?
            ps.setString(1, venda.getValor_venda());
            ps.setString(2, venda.getData_venda());
            ps.setString(3, venda.getNome_comprador());
            
            
            //inserindo no banco.
                int linhasAfetadas = ps.executeUpdate();
            FabricaConexao.fecharConexao(conn);

            if (linhasAfetadas > 0) {
                return true;
            }
        
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean update(Venda venda) throws ClassNotFoundException{
     
        String sql = "UPDATE Venda SET valor_venda = ?, data_venda = ?, nome_comprador = ?"
                + "WHERE venda.id_venda = ?";
        
        try { 
            Connection conn = FabricaConexao.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, venda.getValor_venda());
            ps.setString(2, venda.getData_venda());
            ps.setString(3, venda.getNome_comprador());
            ps.setInt(4, venda.getId_venda());
            
            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(Integer id) throws ClassNotFoundException{
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        String sql = "DELETE FROM Venda WHERE Venda.id_venda = ?";
        try{
            Connection conn = FabricaConexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, id);
           
            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}

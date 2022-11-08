package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Compra;

public class CompraDAO {

    public ArrayList<Compra> buscarTodos() throws ClassNotFoundException {
        String sql = "SELECT * FROM Compra";
        // Responsável em guardar o resultado
        ResultSet resultado = null;

        ArrayList<Compra> lista = new ArrayList<Compra>();

        Connection conn = FabricaConexao.getConnection();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            resultado = ps.executeQuery();

            while (resultado.next()) {
                //Antes a gente estava imprimindo.
                // Agora estamos guardando no arrayList
                Compra a = new Compra();
                a.setId_compra(resultado.getInt("id_compra"));
                a.setValor_compra(resultado.getString("valor_compra"));
                a.setData_compra(resultado.getString("data_compra"));
                a.setNome_vendedor(resultado.getString("nome_vendedor"));

                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FabricaConexao.fecharConexao(conn);

        return lista;
    }
    

    public Compra getById(Integer id) throws ClassNotFoundException {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * FROM Compra WHERE id=?";
        ResultSet resultado = null;
        Compra compra = null;
        try (Connection conn = FabricaConexao.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            resultado = ps.executeQuery();
            resultado.next();

            Compra a = new Compra();

            a.setId_compra(resultado.getInt("id_compra"));
            a.setValor_compra(resultado.getString("valor_compra"));
            a.setData_compra(resultado.getString("data_compra"));
            a.setNome_vendedor(resultado.getString("nome_vendedor"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(resultado);
        }
        return compra;
    }
    
    public boolean create(Compra compra) throws ClassNotFoundException{
       
        try{
            String comando = "INSERT INTO Compra (valor_compra, data_compra, nome_vendedor)"
                    + "VALUES (?, ?, ?)";

            Connection conn = FabricaConexao.getConnection();
            //revisor DE  SQL
            PreparedStatement ps = conn.prepareStatement(comando);
            // substituindo as ?
            ps.setString(1, compra.getValor_compra());
            ps.setString(2, compra.getData_compra());
            ps.setString(3, compra.getNome_vendedor());
            
            
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
    
    public boolean update(Compra compra) throws ClassNotFoundException{
     
        String sql = "UPDATE Compra SET valor_compra = ?, data_compra = ?, nome_vendedor = ?"
                + "WHERE compra.id_compra = ?";
        
        try { 
            Connection conn = FabricaConexao.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, compra.getValor_compra());
            ps.setString(2, compra.getData_compra());
            ps.setString(3, compra.getNome_vendedor());
            ps.setInt(4, compra.getId_compra());
            
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
        String sql = "DELETE FROM Compra WHERE Compra.id_compra = ?";
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

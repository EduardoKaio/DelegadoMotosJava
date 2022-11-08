package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Moto;

public class MotoDAO {

    public ArrayList<Moto> buscarTodos() throws ClassNotFoundException {
        String sql = "SELECT * FROM Moto";
        // Responsável em guardar o resultado
        ResultSet resultado = null;

        ArrayList<Moto> lista = new ArrayList<Moto>();

        Connection conn = FabricaConexao.getConnection();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            resultado = ps.executeQuery();

            while (resultado.next()) {
                //Antes a gente estava imprimindo.
                // Agora estamos guardando no arrayList
                Moto a = new Moto();
                a.setId_moto(resultado.getInt("id_moto"));
                a.setModelo(resultado.getString("modelo"));
                a.setMarca(resultado.getString("marca"));
                a.setCor(resultado.getString("cor"));
                a.setAno(resultado.getString("ano"));
                a.setPlaca(resultado.getString("placa"));
                a.setCilindradas(resultado.getString("cilindradas"));
                a.setQuilometragem(resultado.getString("quilometragem"));

                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FabricaConexao.fecharConexao(conn);

        return lista;
    }

    public Moto getById(Integer id) throws ClassNotFoundException {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * FROM Moto WHERE id=?";
        ResultSet resultado = null;
        Moto moto = null;
        try (Connection conn = FabricaConexao.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            resultado = ps.executeQuery();
            resultado.next();
            
            Moto a = new Moto();
            
            a.setId_moto(resultado.getInt("id_moto"));
            a.setModelo(resultado.getString("modelo"));
            a.setMarca(resultado.getString("marca"));
            a.setCor(resultado.getString("cor"));
            a.setAno(resultado.getString("ano"));
            a.setPlaca(resultado.getString("placa"));
            a.setCilindradas(resultado.getString("cilindradas"));
            a.setQuilometragem(resultado.getString("quilometragem"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            FabricaConexao.fecharConexao(resultado);
        }
        return moto;
    }
    public boolean create(Moto moto) throws ClassNotFoundException{
       
        try{
            String comando = "INSERT INTO Moto (modelo, marca, cor, ano, placa, cilindradas, "
                    + "quilometragem) VALUES (?, ?, ?, ?, ?, ?, ?)";

            Connection conn = FabricaConexao.getConnection();
            //revisor DE  SQL
            PreparedStatement ps = conn.prepareStatement(comando);
            // substituindo as ?
            ps.setString(1, moto.getModelo());
            ps.setString(2, moto.getMarca());
            ps.setString(3, moto.getCor());
            ps.setString(4, moto.getAno());
            ps.setString(5, moto.getPlaca() );
            ps.setString(6, moto.getCilindradas());
            ps.setString(7, moto.getQuilometragem());
            
            
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

    public boolean update(Moto moto) throws ClassNotFoundException{
     
        String sql = "UPDATE Moto SET modelo = ?, marca = ?, cor = ?, ano = ?, placa = ?, cilindradas = ?,"
                + "quilometragem = ? WHERE moto.id_moto = ?";
        
        try { 
            Connection conn = FabricaConexao.getConnection(); 
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, moto.getModelo());
            ps.setString(2, moto.getMarca());
            ps.setString(3, moto.getCor());
            ps.setString(4, moto.getAno());
            ps.setString(5, moto.getPlaca() );
            ps.setString(6, moto.getCilindradas());
            ps.setString(7, moto.getQuilometragem());
            ps.setInt(8, moto.getId_moto());
            
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
        String sql = "DELETE FROM Moto WHERE Moto.id_moto = ?";
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

package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;
public class Fezes {
	
	public static boolean cadastrado (int ra , int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM FEZ " +
                  "WHERE RA = ? and codMateria = ?"
                  ;

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, ra);
            
            BDSQLServer.COMANDO.setInt (2, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

            /* // ou, se preferirmos,

            String sql;

            sql = "SELECT COUNT(*) AS QUANTOS " +
                  "FROM LIVROS " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            resultado.first();

            retorno = resultado.getInt("QUANTOS") != 0;

            */
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar dados aluno");
        }

        return retorno;
    }
	
	 public static void incluir (Fez fez) throws Exception
	    {
	        if (fez==null)
	            throw new Exception ("aluno ou materia nao fornecido");

	        try
	        {
	            String sql;

	            sql = "INSERT INTO FEZ " +
	                  "(RA,CODMATERIA,NOTA,FREQUENCIA) " +
	                  "VALUES " +
	                  "(?,?,?,?)";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt    (1, fez.getRa ());
	            BDSQLServer.COMANDO.setInt    (2, fez.getCodigo ());
	            BDSQLServer.COMANDO.setFloat  (3, fez.getNota ());
	            BDSQLServer.COMANDO.setInt    (4, fez.getFrequencia ());

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	            throw new Exception ("Erro ao inserir dados aluno");
	        }
	    }
	 
	 public static void excluir (int ra , int Codigo) throws Exception
	    {
	        if (!cadastrado (ra,Codigo))
	            throw new Exception ("Nao cadastrado");

	        try
	        {
	            String sql;

	            sql = "DELETE FROM FEZ " +
	                  "WHERE RA = ? and codMateria = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, ra);
	            BDSQLServer.COMANDO.setInt (2, Codigo);

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	            throw new Exception ("Erro ao excluir dados aluno");
	        }
	    }
	 
	 public static void alterar (Fez fez) throws Exception
	    {
	        if (fez==null)
	            throw new Exception ("dados nao fornecido");

	        if (!cadastrado (fez.getRa () , fez.getCodigo() ))
	            throw new Exception ("Nao cadastrado");

	        try
	        {
	            String sql;

	            sql = "UPDATE FEZ " +
	                  "SET NOTA=? ,FREQUENCIA=?  " +
	                  "WHERE RA = ? AND CODMATERIA = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setDouble  (1, fez.getNota());
	            BDSQLServer.COMANDO.setDouble  (2, fez.getFrequencia());
	            BDSQLServer.COMANDO.setInt    (3, fez.getRa ());
	            BDSQLServer.COMANDO.setInt    (4, fez.getCodigo());

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	            throw new Exception ("Erro ao atualizar dados do FEZ");
	        }
	    }
	 
	 public static Fez getFez (int ra , int codigo) throws Exception
	    {
	        Fez Fez = null;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
	                  "FROM FEZ " +
	                  "WHERE RA = ? and codMateria = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, ra);
	            BDSQLServer.COMANDO.setInt (2, codigo);

	            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	            if (!resultado.first())
	                throw new Exception ("Nao cadastrado");

	            Fez = new Fez (resultado.getInt   ("RA"),
	                               resultado.getInt("codMateria"),
	                               resultado.getFloat ("NOTA"),
	                               resultado.getInt("FREQUENCIA"));
	        }
	        catch (SQLException erro)
	        {
	            throw new Exception ("Erro ao procurar dados aluno");
	        }

	        return Fez;
	    }
	
	
	 	public static MeuResultSet getFezes () throws Exception
	    {
	        MeuResultSet resultado = null;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
	                  "FROM FEZ";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
	        }
	        catch (SQLException erro)
	        {
	            throw new Exception ("Erro ao recuperar tabela FEZ");
	        }

	        return resultado;
	    }
}
	 


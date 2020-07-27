package bd.daos;

import java.sql.SQLException;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import bd.dbos.*;

public class Materias {
	
	public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM MATERIAS " +
                  "WHERE codMateria = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);
            
            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar materia");
        }

        return retorno;
    }
	
	 public static void incluir (Materia materia) throws Exception
	    {
	        if (materia==null)
	            throw new Exception ("materia nao fornecida");

	        try
	        {
	            String sql;

	            sql = "INSERT INTO MATERIAS " +
	                  "(codMateria,Nome) " +
	                  "VALUES " +
	                  "(?,?)";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt       (1, materia.getCodigo ());
	            BDSQLServer.COMANDO.setString    (2, materia.getNome ());

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	            throw new Exception ("Erro ao inserir Materia nova");
	        }
	    }
	 
	 public static void excluir (int codigo) throws Exception
	    {
	        if (!cadastrado (codigo))
	            throw new Exception ("Nao cadastrado");

	        try
	        {
	            String sql;

	            sql = "DELETE FROM MATERIAS " +
	                  "WHERE codMateria = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, codigo);

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	            throw new Exception ("Erro ao excluir materia");
	        }
	    }
	 
	 public static void alterar (Materia materia) throws Exception
	    {
	        if (materia==null)
	            throw new Exception ("Materia nao fornecida");

	        if (!cadastrado (materia.getCodigo() ))
	            throw new Exception ("Nao cadastrado");

	        try
	        {
	            String sql;

	            sql = "UPDATE MATERIAS " +
	                  "SET NOME = ? " +
	                  "WHERE CODMATERIA = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setString  (1, materia.getNome());
	            
	            BDSQLServer.COMANDO.setInt  (2, materia.getCodigo());
	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	            throw new Exception ("Erro ao atualizar dados da tabela Materias");
	        }
	    }
	 
	 public static Materia getMateria (int codigo) throws Exception
	    {
	        Materia materia = null;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
	                  "FROM MATERIAS " +
	                  "WHERE codMateria = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, codigo);

	            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	            if (!resultado.first())
	                throw new Exception ("Nao cadastrado");

	             materia = new Materia (resultado.getInt("CodMateria"), resultado.getString("NOME"));
	        }
	        catch (SQLException erro)
	        {
	            throw new Exception ("Erro ao procurar materia");
	        }

	        return materia;
	    }
	
	
	 	public static MeuResultSet getMaterias () throws Exception
	    {
	        MeuResultSet resultado = null;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
	                  "FROM MATERIAS";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
	        }
	        catch (SQLException erro)
	        {
	            throw new Exception ("Erro ao recuperar tabela materiais");
	        }

	        return resultado;
	    }	 

	public static MeuResultSet getMateriasSemReprovados () throws Exception
	{
		MeuResultSet resultado = null;
		
		try
		{
			String sql;
			
			sql = "SELECT * FROM MATERIAS M , FEZ F " +
				  "WHERE M.codMateria = F.codMateria AND " +
				  "F.NOTA >= 5.0";
			
			BDSQLServer.COMANDO.prepareStatement(sql);
			resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			
		}
		catch(SQLException erro)
		{
			throw new Exception (erro);
		}
		
		return resultado;
	}
	
	public static MeuResultSet getMateriaComMediaOrdenada () throws Exception
	{
		MeuResultSet resultado = null;
		
		try
		{
			String sql;
			
			sql = "SELECT  *  FROM MATERIAS M , FEZ F " +
				  "WHERE M.codMateria = F.codMateria " +
				  "order by F.NOTA";
			
			BDSQLServer.COMANDO.prepareStatement(sql);
			resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			
		}
		catch(SQLException erro)
		{
			throw new Exception (erro);
		}
		
		return resultado;
	}
	
	
}

package bd.daos;

import java.sql.SQLException;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import WebServiceCep.*;
import bd.dbos.*;

public class Alunos {
	
	

	
	public static boolean cadastrado (int ra) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM ALUNOS " +
                  "WHERE RA = ?"
                  ;

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, ra);
            

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar o aluno");
        }

        return retorno;
    }
	
		
	 public static void incluir (Aluno Aluno) throws Exception
	    {
	        if (Aluno==null)
	            throw new Exception ("aluno nao fornecido");

	        try
	        {
	            String sql;

	            sql = "INSERT INTO ALUNOS " +
	                  "(RA,NOME,EMAIL,CEP,RUA,BAIRRO,ESTADO,CIDADE,NUMERO,COMPLEMENTO) " +
	                  "VALUES " +
	                  "(?,?,?,?,?,?,?,?,?,?)";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt    (1, Aluno.getRA ());
	            BDSQLServer.COMANDO.setString (2, Aluno.getNome ());
	            BDSQLServer.COMANDO.setString (3, Aluno.getEmail ());
	            BDSQLServer.COMANDO.setString (4, Aluno.getCep() );
	            BDSQLServer.COMANDO.setString (5, Aluno.getRua() );
	            BDSQLServer.COMANDO.setString (6, Aluno.getBairro() );
	            BDSQLServer.COMANDO.setString (7, Aluno.getEstado() );
	            BDSQLServer.COMANDO.setString (8, Aluno.getCidade());
	            BDSQLServer.COMANDO.setString (9, Aluno.getNumero());
	            BDSQLServer.COMANDO.setString (10, Aluno.getComplemento());

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	            throw new Exception ("Erro ao inserir aluno");
	        }
	    }
	 
	
	 
	 public static void criartabela() throws Exception
	 {
		 try {
			 
			 String sql;
			 
			 sql = "ALTER TABLE ALUNOS "
			 		+ "ADD NUMERO VARCHAR(10), "
			 		+ "COMPLEMENTO VARCHAR(50)";
			 
			 BDSQLServer.COMANDO.prepareStatement(sql);
			 BDSQLServer.COMANDO.executeUpdate();
			 BDSQLServer.COMANDO.commit();
			 
		 }
		 catch(SQLException erro)
		 {
			 throw new Exception ("Erro");
		 }
	 }
	 
	 public static void alterarCampo() throws Exception
	    {
	        try
	        {
	            String sql;

	            sql = "ALTER TABLE FUNCIONARIO "+
	                  "ALTER COLUMN NOME VARCHAR(40)";

	                    BDSQLServer.COMANDO.prepareStatement(sql);
	                    BDSQLServer.COMANDO.executeUpdate();
	                    BDSQLServer.COMANDO.commit();
	        }
	        catch(SQLException e)
	        {
	            throw new Exception("OROXIMAROW");
	        }
	    }
	 
	 
	 
	 public static void excluir (int ra) throws Exception
	    {
	        if (!cadastrado (ra))
	            throw new Exception ("Nao cadastrado");

	        try
	        {
	            String sql;

	            sql = "DELETE FROM ALUNOS " +
	                  "WHERE RA = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, ra);

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	            throw new Exception ("Erro ao excluir aluno");
	        }
	    }
	 
	 public static void alterar (Aluno novoRegistro) throws Exception
	    {
	        if (novoRegistro ==null)
	            throw new Exception ("aluno nao fornecido");

	        if (!cadastrado (novoRegistro.getRA()))
	            throw new Exception ("Nao cadastrado");

	        try
	        {
	            String sql;

	            sql = "UPDATE ALUNOS " +
	                  "SET EMAIL=? , NOME=? , CEP=? , RUA=? , BAIRRO=? , ESTADO=?, CIDADE=?, NUMERO=?, COMPLEMENTO=? "+
	                  "WHERE RA = ?";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setString (1, novoRegistro.getEmail());
	            BDSQLServer.COMANDO.setString (2, novoRegistro.getNome());
	            BDSQLServer.COMANDO.setString (3, novoRegistro.getCep());
	            BDSQLServer.COMANDO.setString (4, novoRegistro.getRua());
	            BDSQLServer.COMANDO.setString (5, novoRegistro.getBairro());
	            BDSQLServer.COMANDO.setString (6, novoRegistro.getEstado());
	            BDSQLServer.COMANDO.setString (7, novoRegistro.getCidade());
	            BDSQLServer.COMANDO.setString (8, novoRegistro.getNumero());
	            BDSQLServer.COMANDO.setString (9, novoRegistro.getComplemento());
	            BDSQLServer.COMANDO.setInt    (10, novoRegistro.getRA());
	            

	            BDSQLServer.COMANDO.executeUpdate ();
	            BDSQLServer.COMANDO.commit        ();
	        }
	        catch (SQLException erro)
	        {
	          //BDSQLServer.COMANDO.rollback ();
	            throw new Exception ("Erro ao atualizar dados do aluno");
	        }
	    }
	 
	 public static Aluno getAluno ( int ra ) throws Exception
	    {
	        Aluno Aluno = null;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
	                  "FROM ALUNOS " +
	                  "WHERE RA = ?";
	            
	           


	            BDSQLServer.COMANDO.prepareStatement (sql);

	            BDSQLServer.COMANDO.setInt (1, ra);

	            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

	            if (!resultado.first())
	                throw new Exception ("Nao cadastrado");

	            Aluno = new Aluno (resultado.getInt   ("RA"),
	                               resultado.getString("NOME"),
	                               resultado.getString ("EMAIL"),
	                               resultado.getString("CEP"),
	                               resultado.getString("RUA"),
	                               resultado.getString("BAIRRO"),
	                               resultado.getString("ESTADO"),
	                               resultado.getString("CIDADE"),
	                               resultado.getString("NUMERO"),
	                               resultado.getString("COMPLEMENTO"));
	        }
	        catch (SQLException erro)
	        {
	            throw new Exception ("Erro ao procurar ALUNO");
	        }

	        return Aluno;
	    }
	
	
	 	public static MeuResultSet getAlunos () throws Exception
	    {
	        MeuResultSet resultado = null;

	        try
	        {
	            String sql;

	            sql = "SELECT * " +
	                  "FROM ALUNOS";

	            BDSQLServer.COMANDO.prepareStatement (sql);

	            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
	        }
	        catch (SQLException erro)
	        {
	            throw new Exception ("Erro ao recuperar tabela ALUNO");
	        }

	        return resultado;
	    }
	 	
	 	public static MeuResultSet getAlunosComFrequenciasZeradas() throws Exception
	 	{
	 		MeuResultSet resultado = null;
	 		try
	 		{
	 			String sql;
	 			
	 			sql = "SELECT * FROM "+
	 			      "ALUNOS a, FEZ f "+
	 				  "WHERE a.RA = f.RA and "+
	 			      "f.frequencia = 0";
	 			
	 			BDSQLServer.COMANDO.prepareStatement(sql);
	 			resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
	 			resultado.first();
	 		}
	 		catch(SQLException erro)
	 		{
	 			throw new Exception (erro);
	 		}
	 		
	 		return resultado;
	 	}
	 	
	 	public static MeuResultSet getOrdemCrescente() throws Exception
	 	{
	 		MeuResultSet resultado = null;
	 		
	 		try
	 		{
	 			String sql;
	 			
	 			sql = "SELECT * FROM "+
	 			      "ALUNOS A, FEZ F "+
	 				  "WHERE A.RA = F.RA " +
	 			      "ORDER BY F.NOTA";
	 			
	 			BDSQLServer.COMANDO.prepareStatement(sql);
	 			resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
	 		}
	 		catch(SQLException erro)
	 		{
	 			throw new Exception(erro);
	 		}
	 		
	 		return resultado;
	 	}
	 	
}
	 

package janela;

import java.awt.EventQueue;

import javax.swing.JFrame;

import bd.core.*;
import bd.daos.*;
import bd.dbos.*;
import WebServiceCep.*;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import java.lang.Thread.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;

import java.awt.Container;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

import java.util.concurrent.Delayed;
import  java.util.regex.*;
import javax.swing.UIManager;




public class CRUD extends JFrame{

	private JFrame frmControladorBando;
	private JTextField txtRA;
	private JTextField txtRua;
	private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtEstado;
	private JTextField txtEmail;
	private JTextField txtNumero;
	private JTextField txtComp;
	private JTextField txtAluno;
	private JTextArea txtListaAluno;
	private JTextArea txtListaFez;
	private JTextArea txtListaMateria;
	private JTextField textRA_fez;
	private JTextField textMat_fez;
	private JTextField textNota_Fez;
	private JTextField textFreq_Fez;
	private JTextField txtMCodMateria;
	private JTextField txtMNome;
	
	/**
	 * Launch the application.
	 */
	
	// outlook => 7 caracteres, gmail => 5, hotmail => 7, bol => 3 => yahoo => 5
		
		
		 //=================================== VALIDAÇÃO DA DIGITAÇÃO DO USUÁRIO ==============================================================\\
		 // expressão regular para o nome
		 private static final String regExNome = "^[A-Z][a-z]*(?: (?:[A-Z]|[a-z])[a-z]*)*$";
		 private static final Pattern nomenclaturaDoNome = Pattern.compile(CRUD.regExNome);
		 
		 private static final String regExRA = "^[1-9][0-9]{4}$";
		 private static final Pattern nomenclaturaDoRA = Pattern.compile(CRUD.regExRA);
		 
		 private static final String regExEmail = "^[a-z][a-z]*[@][a-y]{3,7}[.][c][o][m]$";
		 private static final Pattern nomenclaturaDoEmail = Pattern.compile(CRUD.regExEmail);
		 private JFormattedTextField txtCep;
		
		 private static final String regExCep = "^[0-9]{5}[-][0-9]{3}$";
		 private static final Pattern nomenclaturaDoCep = Pattern.compile(CRUD.regExCep);
		 
		 
		 
		 
		 //================================== métodos de verificação chamados de matches ===================================================\\
		 
		 //matches() vai dizer se a String inteira bate com o regex do pattern ele retorna um valor booleano
		 // matcher() pelo que eu entendi é um método que cria um similar do padrão esclarecido nas variaveis acima regExNome, regExRA, regExEmail
		    private  void valideNome (String nome) throws Exception
		    {
		        if (!CRUD.nomenclaturaDoNome.matcher(nome).matches())
		            throw new Exception ("Nome inválido!");
		    }
		    
		    private void valideEmail(String email) throws Exception
		    {
		    	if(!CRUD.nomenclaturaDoEmail.matcher(email).matches())
		    	  throw new Exception("Email inválido!");
		    }
		    
		    private void valideRA (String ra) throws Exception
		    {
		    	if(!CRUD.nomenclaturaDoRA.matcher(ra).matches())
		    	  throw new Exception("RA inválido!");
		    }
		    
		    private void valideCep (String cep) throws Exception
		    {
		    	if(!CRUD.nomenclaturaDoCep.matcher(cep).matches())
		    	  throw new Exception("cep inválido!");
		    }
		// ======================================================= ALUNOS =====================================================================
		private void excluirAluno()
		{
			
			txtListaAluno.setForeground(Color.black);
			txtListaAluno.setFont(new Font("Tahoma",Font.PLAIN,13));
			
			 // 0=yes, 1=no, 2=cancel
			
			int teste = JOptionPane.showConfirmDialog (null,
					"Ao excluir o aluno, todos os registros \n relacionados ao histórico escolar do mesmo serão apagado \n tem certeza disso?!","Aviso!",
					JOptionPane.YES_NO_OPTION);
			
			
			
			if(!this.txtRA.getText().equals("") )
			{
			   try
			   {
				   valideRA(this.txtRA.getText());
				   int ra = Integer.parseInt(this.txtRA.getText());
				   if(teste == 0)
					{
				   Alunos.excluir(ra);
				   txtListaAluno.setFont(new Font("Tahoma",Font.BOLD,13));
				   txtListaAluno.setText("\n\n\n\n\n\tOPERAÇÃO CONCLUIDA");
		    	   txtListaAluno.setForeground(Color.red);
					}
			   }
			   catch(Exception erro)
		   	   {
				   JOptionPane.showMessageDialog(null, erro+"");   
		   	   }
			}
			
			else
				JOptionPane.showMessageDialog(null, "Campo RA vázio!");
			
		}
		
		private void procurarAluno()
		{
			
			txtListaAluno.setForeground(Color.black);
			txtListaAluno.setFont(new Font("Tahoma",Font.PLAIN,13));
			
			if(!this.txtRA.getText().equals(""))
			{
			    try
			    {
			    	valideRA(this.txtRA.getText());
			       int ra = Integer.parseInt(this.txtRA.getText());
				   Aluno aluno = Alunos.getAluno(ra);
			  	   txtListaAluno.setText("ra: " + aluno.getRA()+ "\n" + "Aluno: "+ aluno.getNome()+ "\n" + "Email: "+ aluno.getEmail());
		        }
			    catch(Exception erro)
			    {
			    	JOptionPane.showMessageDialog(null, erro+"");
			    }
			}
			else
			 JOptionPane.showMessageDialog(null, "Campo RA vázio!");
		}
		
	    private void atualizarAluno()
	    {
	    	txtListaAluno.setForeground(Color.black);
	    	txtListaAluno.setFont(new Font("Tahoma",Font.PLAIN,13));
	    	
	    	if(!this.txtRA.getText().equals("") && 
	    			!this.txtEmail.getText().equals("") &&
	    			!this.txtAluno.getText().equals("") &&
	    			!this.txtRua.getText().equals("") &&
	    			!this.txtRua.getText().equals("Aguarde..."))
	    	{
	    	   
	    	   try
	    	   {
	    		   valideRA(this.txtRA.getText());
	    		   valideNome(this.txtAluno.getText());
	    		   valideEmail(this.txtEmail.getText());
	    		   valideCep(this.txtCep.getText());
	    		   
	    		   int ra = Integer.parseInt(this.txtRA.getText());
		    		  
	    		   
		    		    
	    		   Alunos.alterar(new Aluno(ra,
		    				  this.txtAluno.getText(),
		    				  this.txtEmail.getText(),
		    				  this.txtCep.getText(),
		    				  this.txtRua.getText(),
		    				  this.txtBairro.getText(),
		    				  this.txtEstado.getText(),
		    				  this.txtCidade.getText(),
		    				  this.txtNumero.getText(),
		    				  this.txtComp.getText()));
		    		  
		    		  
	    			   
		    		 
	    		  txtListaAluno.setForeground(Color.red);
	    		  txtListaAluno.setFont(new Font("Tahoma",Font.BOLD,13));
	    		  txtListaAluno.setText("\n\n\n\n\n\tOPERAÇÃO CONCLUIDA");	  
	    	   }
	    	   catch(Exception erro)
	    	   {
	    		   JOptionPane.showMessageDialog(null, erro + "");
	    	   }
	    	}
	    	else
	    	JOptionPane.showMessageDialog(null, "Um dos campos vázios!");
	    }
	    
	    
	    
	    private void inserirNovoAluno()
	    {
	    	
	    	
	    	txtListaAluno.setForeground(Color.black);
	    	txtListaAluno.setFont(new Font("Tahoma",Font.PLAIN,13));
	    	
	    	if(!this.txtRA.getText().equals("") &&
	    		!this.txtAluno.getText().equals("") && 
	    		!this.txtEmail.getText().equals("") &&
	    		!this.txtRua.getText().equals("Aguarde...") &&
	    		!this.txtRua.getText().equals("") &&
	    		!this.txtNumero.getText().equals("") &&
	    		!this.txtComp.getText().equals(""))
	    	{
	    		
	    	  try
	    	  {
	    		  valideRA(this.txtRA.getText());
	   		      valideNome(this.txtAluno.getText());
	   		      valideEmail(this.txtEmail.getText());
	   		      valideCep(this.txtCep.getText());
	   		      
	    		  int ra = Integer.parseInt(this.txtRA.getText());
	    		  
	    		  Alunos.incluir(new Aluno(ra,
	    				  this.txtAluno.getText(),
	    				  this.txtEmail.getText(),
	    				  this.txtCep.getText(),
	    				  this.txtRua.getText(),
	    				  this.txtBairro.getText(),
	    				  this.txtEstado.getText(),
	    				  this.txtCidade.getText(),
	    				  this.txtNumero.getText(),
	    				  this.txtComp.getText()));
	    			  
	    		  txtListaAluno.setFont(new Font("Tahoma",Font.BOLD,13));
	    		  txtListaAluno.setText("\n\n\n\n\n\tOPERAÇÃO CONCLUIDA");
	    		  txtListaAluno.setForeground(Color.red); 
	    		  }  
	    	  catch(Exception erro)
	    	  {
	    		  JOptionPane.showMessageDialog(null, erro+"boato inserir");
	    	  }
	    	  
	    	  
	        }
	    	else
	    		JOptionPane.showMessageDialog(null, "Algum campo em falta!");
	    	
	    }
	    
	    
	    

	   
	    
	    
	    
	    private void listarTodosAlunos()
	    {
	    	txtListaAluno.setForeground(Color.black);
	    	txtListaAluno.setFont(new Font("Tahoma",Font.PLAIN,13));
	    	
	     	txtListaAluno.setText ("");
		    try
		    {
			   MeuResultSet aluno = Alunos.getAlunos();
			   while(aluno.next())
			   {
		  	       txtListaAluno.setText(txtListaAluno.getText() + "RA: " + aluno.getInt("RA") +
		  	    		   "\n" + "ALUNO: "+ aluno.getString("nome")+
		  	    		   "\n" + "EMAIL: "+ aluno.getString("email") +
		  	    		   "\n"+ "CEP: "+ aluno.getString("cep") +
		  	    		   "\n"+ "ESTADO: "+ aluno.getString("estado") +
		  	    		   "\n"+ "CIDADE: "+ aluno.getString("cidade") +
		  	    		   "\n"+ "BAIRRO: "+ aluno.getString("bairro") +
		  	    		   "\n"+ "RUA: "+ aluno.getString("rua") +
		  	    		   "\n"+ "NUMERO: "+ aluno.getString("numero") +
		  	    	  	   "\n"+ "COMPLEMENTO: "+ aluno.getString("complemento") +"\n\n");
			       txtListaAluno.setText(txtListaAluno.getText() + "------------------------------------------------------\n");
			   }
	        }
		    catch(Exception erro)
		    {
		    	JOptionPane.showMessageDialog(null, erro+"");
		    }
	    }
	    
	    
	    private void listarOrdemCrescente()
	    {
	    	
	    	txtListaAluno.setForeground(Color.black);
	    	txtListaAluno.setFont(new Font("Tahoma",Font.PLAIN,13));
	    	
	    	txtListaAluno.setText ("");
	    	try
	    	{
	    		MeuResultSet aluno = Alunos.getOrdemCrescente();
	    		while(aluno.next())
	    		{
	    	    	txtListaAluno.setText(txtListaAluno.getText() + "ra: " + aluno.getInt("RA") + "\n" + "Aluno: "+ aluno.getString("nome")+ "\n" + "Email: "+ aluno.getString("email") + "\n" + "Nota: " + aluno.getFloat("NOTA") +"\n");
	    	    	txtListaAluno.setText(txtListaAluno.getText() + "--------------------------------------------------------- \n");
	    		}
	    	
	    	}
	    	catch(Exception erro)
	    	{
	    		JOptionPane.showMessageDialog(null,"Não achou!");
	    	}
	    }
	    
	    private void alunosComFrequenciasZeradas()
	    {
	    	
	    	txtListaAluno.setForeground(Color.black);
	    	txtListaAluno.setFont(new Font("Tahoma",Font.PLAIN,13));
	    	
	         txtListaAluno.setText("");
	         try
	         {
	        	 MeuResultSet aluno = Alunos.getAlunosComFrequenciasZeradas();
	            txtListaAluno.setText(txtListaAluno.getText() + "ra: " + aluno.getInt("RA") + "\n" + "Aluno: "+ aluno.getString("nome")+ "\n" + "Email: "+ aluno.getString("email") + "\n"+ "Frequência: " + aluno.getInt("Frequencia")+"\n");
	 	    	txtListaAluno.setText(txtListaAluno.getText() + "--------------------------------------------------------- \n");
	         }
	         catch(Exception erro)
	         {
	        	 JOptionPane.showMessageDialog(null,"Não achou!");
	         }
	    }    
    //======================================================================================= FEZ =======================================================================
   
    private void atualizarFez()
    {
    	
    	txtListaFez.setForeground(Color.black);
    	txtListaFez.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	if(!this.textRA_fez.getText().equals("") && !this.textMat_fez.getText().equals("") && !this.textNota_Fez.getText().equals("") && !this.textFreq_Fez.getText().equals("") )
    	{
    	  
    	   
    	   try
    	   {
    		   valideRA(this.textRA_fez.getText());
    		   int ra = Integer.parseInt(this.textRA_fez.getText());
        	   int codMate = Integer.parseInt(this.textMat_fez.getText());
        	   float notas = Float.parseFloat(this.textNota_Fez.getText());
        	   int frequencia = Integer.parseInt(this.textFreq_Fez.getText());
    		   
    		   Fezes.alterar(new Fez(ra,codMate, notas,frequencia));
    		   
    		   txtListaFez.setFont(new Font("Tahoma",Font.BOLD,13));
    		   txtListaFez.setText("\n\n\n\n\n\tOPERAÇÃO CONCLUIDA");
    		   txtListaFez.setForeground(Color.red);
    		   
    	   }
    	   catch(Exception erro)
    	   {
    		   JOptionPane.showMessageDialog(null, erro + "");
    	   }
    	}
    	else
    	JOptionPane.showMessageDialog(null, "Um dos campos vázios!");
    }
    
    private void excluirFez()
    {
    	
    	txtListaFez.setForeground(Color.black);
    	txtListaFez.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	if(!this.textRA_fez.getText().equals("") && !this.textMat_fez.getText().equals("") )
		{
			
		 
		   try
		   {
			   valideRA(this.textRA_fez.getText());
			   int ra = Integer.parseInt(this.textRA_fez.getText());
			   int cod_mat  = Integer.parseInt(this.textMat_fez.getText());
			   
			   Fezes.excluir(ra,cod_mat);
			   
			   txtListaFez.setFont(new Font("Tahoma",Font.BOLD,13));
			   txtListaFez.setText("\n\n\n\n\n\tOPERAÇÃO CONCLUIDA");
			   txtListaFez.setForeground(Color.red);
			   
		   }
		   catch(Exception erro)
	   	   {
			   JOptionPane.showMessageDialog(null, erro+"");
	   	   }
		}
		else
			 JOptionPane.showMessageDialog(null, "Campo RA ou CODIGO MATERIA VAZIOS vázio!");
    }
    
    private void inserirFezNovo()
    {
    	
    	txtListaFez.setForeground(Color.black);
    	txtListaFez.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	if(!this.textRA_fez.getText().equals("") && !this.textMat_fez.getText().equals("") && !this.textNota_Fez.getText().equals("") && !this.textFreq_Fez.getText().equals("") )
    	{
    		

    	  
    	  try
    	  {
    		  valideRA(this.textRA_fez.getText());  
    		  int ra = Integer.parseInt(this.textRA_fez.getText());
        	  int codMate = Integer.parseInt(this.textMat_fez.getText());
       	      float notas = Float.parseFloat(this.textNota_Fez.getText());
       	      int frequencia = Integer.parseInt(this.textFreq_Fez.getText());
    		  
    		  Fezes.incluir(new Fez( ra,codMate, notas,frequencia));
    		  
    		  txtListaFez.setFont(new Font("Tahoma",Font.BOLD,13));
    		  txtListaFez.setText("\n\n\n\n\n\tOPERAÇÃO CONCLUIDA");
    		  txtListaFez.setForeground(Color.red);
    	  }
    	  catch(Exception erro)
    	  {
    		  JOptionPane.showMessageDialog(null, erro+""); 
    	  }
        }
    	else
    		JOptionPane.showMessageDialog(null, "Algum campo em falta!");
    }
    
    private void procurarFez()
    {
    	
    	txtListaFez.setForeground(Color.black);
    	txtListaFez.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	txtListaFez.setText("");
    	
    	if(!this.textRA_fez.getText().equals("") && !this.textMat_fez.getText().equals("") )
		{
		  
		
		    try
		    {
		    	valideRA(this.textRA_fez.getText());
		    	 int ra = Integer.parseInt(this.textRA_fez.getText());
				 int codigo = Integer.parseInt(this.textMat_fez.getText());
		    	
			   Fez Fez = Fezes.getFez(ra,codigo);
			   txtListaFez.setText("RA: " + Fez.getRa()+ "\n" + "MATERIA: "+ Fez.getCodigo()+ "\n" + "NOTA: "+ Fez.getNota()+ "\n" + "FREQUENCIA: "+ Fez.getFrequencia()+"%");
	        }
		    catch(Exception erro)
		    {
		    	JOptionPane.showMessageDialog(null, erro+"");
		    }
		}
		else
		 JOptionPane.showMessageDialog(null, "Campo RA vázio!");
    }
    
    private void getFezes()
    {
    	
    	txtListaFez.setForeground(Color.black);
    	txtListaFez.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	txtListaFez.setText ("");
	    try
	    {
		   MeuResultSet Fez = Fezes.getFezes();
		   while(Fez.next())
		   {
			   txtListaFez.setText(txtListaFez.getText() + "RA: " + Fez.getInt("RA") + "\n" + "MATERIA: "+ Fez.getInt("CODMATERIA")+ "\n" + "NOTA: "+ Fez.getFloat("NOTA") +"\n"+ "FREQUENCIA: " +  Fez.getString("frequencia")+"%"+ "\n\n");
			   txtListaFez.setText(txtListaFez.getText() + "--------------------------------------------------------- \n");
		   }
        }
	    catch(Exception erro)
	    {
	    	JOptionPane.showMessageDialog(null, erro+"");
	    }
    }
    //======================================================================================= MATERIAS ===================================================================
    
    private void atualizarMateria()
    {
    	txtListaMateria.setForeground(Color.black);
    	txtListaMateria.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
       if(!this.txtMCodMateria.getText().equals("") && !this.txtMNome.getText().equals(""))
    	{
    	  try
    	  {
    		  valideNome(this.txtMNome.getText());
    		  int codigo = Integer.parseInt(this.txtMCodMateria.getText());
    		  Materias.alterar(new Materia(codigo, this.txtMNome.getText()));
    		  txtListaMateria.setFont(new Font("Tahoma",Font.BOLD,13));
    		  txtListaMateria.setText("\n\n\n\n\n\tOPERAÇÃO CONCLUIDA");
    		  txtListaMateria.setForeground(Color.red);
      	  }
    	  catch(Exception erro)
    	  {
    		  JOptionPane.showMessageDialog(null, erro+"");
    	  }
       }
      else
       JOptionPane.showMessageDialog(null,"Um dos campos anulados!");
    }
    
    
    private void excluirMateria()
    {
    	txtListaMateria.setForeground(Color.black);
    	txtListaMateria.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	
    	
    	if(!txtMCodMateria.getText().equals(""))
    	{
    	  try
    	  {
    		  int codigo = Integer.parseInt(this.txtMCodMateria.getText());
    		  int teste = JOptionPane.showConfirmDialog (null,
  					" Ao excluir a materia \n todos os registros escolares \n relacionados a essa disciplina serão apagado \n\n TEM CERTEZA DISSO  ?! \n","Aviso!",
  					JOptionPane.YES_NO_OPTION);
    		  
    		  if(teste == 0)
    		  {
    		  Materias.excluir(codigo);
    		  txtListaMateria.setFont(new Font("Tahoma",Font.BOLD,13));
    		  txtListaMateria.setText("\n\n\n\n\n\tOPERAÇÃO CONCLUIDA");
    		  txtListaMateria.setForeground(Color.red);
    		  }
      	  }
    	  catch(Exception erro)
    	  {
    		  JOptionPane.showMessageDialog(null, erro+"");
    	  }
       }
      else
       JOptionPane.showMessageDialog(null,"Código vázio!");
    	
    }
    
    private void inserirMateriaNova()
    {
    	txtListaMateria.setForeground(Color.black);
    	txtListaMateria.setFont(new Font("Tahoma",Font.PLAIN,13));
    	 if(!this.txtMCodMateria.getText().equals("") && !this.txtMNome.getText().equals(""))
     	{
     	  try
     	  {
     		  valideNome(this.txtMNome.getText());
     		  int codigo = Integer.parseInt(this.txtMCodMateria.getText());
     		  Materias.incluir(new Materia(codigo, this.txtMNome.getText()));
     		  txtListaMateria.setFont(new Font("Tahoma",Font.BOLD,13));
     		  txtListaMateria.setText("\n\n\n\n\n\tOPERAÇÃO CONCLUIDA");
     		  txtListaMateria.setForeground(Color.red);
       	  }
     	  catch(Exception erro)
     	  {
     		  JOptionPane.showMessageDialog(null, erro+"");
     	  }
        }
       else
        JOptionPane.showMessageDialog(null,"Um dos campos estão vázio!");
    }
    
    private void procurarMateria()
    {
    	txtListaMateria.setForeground(Color.black);
    	txtListaMateria.setFont(new Font("Tahoma",Font.PLAIN,13));
    	if(!this.txtMCodMateria.equals(""))
    	{
    	try
    	{
    		int codigo = Integer.parseInt(this.txtMCodMateria.getText());
    		Materia materia = Materias.getMateria(codigo);
    		txtListaMateria.setText("CODIGO: " + materia.getCodigo() + "\n" + "MATERIA: "+ materia.getNome() + "\n");
    	}
    	catch(Exception erro)
    	{
    		JOptionPane.showMessageDialog(null,erro+"");
    	}
    	}
    	else
    	JOptionPane.showMessageDialog(null,"Código vázio!");
    }
    
    private void getMateriasComMediaOrdenada()
    {
    	txtListaMateria.setText("");
    	txtListaMateria.setForeground(Color.black);
    	txtListaMateria.setFont(new Font("Tahoma",Font.PLAIN,13));
    	  try
      	  {
      		  MeuResultSet materiasSemRep = Materias.getMateriaComMediaOrdenada();
      		  while(materiasSemRep.next())
      		  {
      			txtListaMateria.setText(txtListaMateria.getText() + "codigo: " + materiasSemRep.getInt("codMateria") + "\n" + "MATERIA: "+ materiasSemRep.getString("Nome")+ "\n" + "NOTA: " + materiasSemRep.getFloat("Nota")+ "\n");
          		txtListaMateria.setText(txtListaMateria.getText() + "--------------------------------------------------------- \n");
      		  }
      		  
          }
      	  catch(Exception erro)
      	  {
      		  JOptionPane.showMessageDialog(null, erro+"");
      	  }
    }
    
    private void getMaterias()
    {
    	txtListaMateria.setText("");
    	txtListaMateria.setForeground(Color.black);
    	txtListaMateria.setFont(new Font("Tahoma",Font.PLAIN,13));
    	  try
      	  {
      		  MeuResultSet materiasSemRep = Materias.getMaterias();
      		  while(materiasSemRep.next())
      		  {
      			txtListaMateria.setText(txtListaMateria.getText() + "codigo: " + materiasSemRep.getInt("codMateria") + "\n" + "MATERIA: "+ materiasSemRep.getString("Nome")+ "\n");
          		txtListaMateria.setText(txtListaMateria.getText() + "--------------------------------------------------------- \n");
      		  }
      		  
          }
      	  catch(Exception erro)
      	  {
      		  JOptionPane.showMessageDialog(null, erro+"");
      	  }
    }
    
    
    private void getMateriasSemReprovados()
    {
    	
    	txtListaMateria.setText("");
    	txtListaMateria.setForeground(Color.black);
    	txtListaMateria.setFont(new Font("Tahoma",Font.PLAIN,13));
      	  try
      	  {
      		  MeuResultSet materiasSemRep = Materias.getMateriasSemReprovados();
      		  while(materiasSemRep.next())
      		  {
      			txtListaMateria.setText(txtListaMateria.getText() + "codigo: " + materiasSemRep.getInt("codMateria") + "\n" + "MATERIA: "+ materiasSemRep.getString("Nome")+ "\n"+ "NOTA: " + materiasSemRep.getFloat("Nota")+ "\n" );
          		txtListaMateria.setText(txtListaMateria.getText() + "--------------------------------------------------------- \n");
      		  }
      		  
          }
      	  catch(Exception erro)
      	  {
      		  JOptionPane.showMessageDialog(null, erro+"");
      	  }
    }
   
    private void buscarCep() 
    {
    	
    	
    	try
    	  {
    		
    		//JOptionPane.showMessageDialog(null,txtCep.getText()+"metodo busca");
        	WebServiceCep ok = WebServiceCep.searchCep(txtCep.getText());
        	
        	if(ok.wasSuccessful())
        	{
        		txtRua.setText(ok.getLogradouro());
        		txtBairro.setText(ok.getBairro());
        		txtEstado.setText(ok.getUf());
        		txtCidade.setText(ok.getCidade());
        	}
        	else {
 
            JOptionPane.showMessageDialog(null,"CEP NÃO ENCONTRADO","ERRO",JOptionPane.ERROR_MESSAGE);
        	}
    	  }
    	catch(Exception erro)
    	  {
    		  JOptionPane.showMessageDialog(null, "CEP INVALIDO","ERRO",JOptionPane.ERROR_MESSAGE);
    	  }
        
    }
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CRUD window = new CRUD();
					window.frmControladorBando.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CRUD() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmControladorBando = new JFrame();
		frmControladorBando.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\COTUCA\\2019\\2019_2°semt\\TP2\\PROJETOS\\19846_19362\\projeto\\icone"));
		frmControladorBando.setTitle("CONTROLADOR BANDO");
		frmControladorBando.setBounds(100, 100, 689, 549);
		frmControladorBando.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmControladorBando.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		

		//---------------------------------------------------------------------------------------------------------------------------
		ButtonGroup grupo = new ButtonGroup();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(51, 153, 255));
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menuText, null, null, null));
		panel_3.setToolTipText("");
		tabbedPane.addTab("ALUNOS", null, panel_3, null);
		panel_3.setLayout(null);
		
		txtRA = new JTextField();
		txtRA.setForeground(new Color(0, 0, 0));
		txtRA.setBounds(76, 19, 86, 20);
		panel_3.add(txtRA);
		txtRA.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(76, 63, 158, 20);
		panel_3.add(txtEmail);
		
		txtAluno = new JTextField();
		txtAluno.setForeground(new Color(0, 0, 0));
		txtAluno.setBackground(new Color(255, 255, 255));
		txtAluno.setColumns(10);
		txtAluno.setBounds(233, 19, 123, 20);
		panel_3.add(txtAluno);
		
		JLabel lblRa = new JLabel("RA :");
		lblRa.setForeground(SystemColor.windowText);
		lblRa.setFont(new Font("Arial", Font.BOLD, 13));
		lblRa.setBounds(10, 21, 46, 14);
		panel_3.add(lblRa);
		
		JLabel lblAluno = new JLabel("NOME :");
		lblAluno.setForeground(SystemColor.windowText);
		lblAluno.setFont(new Font("Arial", Font.BOLD, 13));
		lblAluno.setBounds(185, 21, 61, 14);
		panel_3.add(lblAluno);
		
		JLabel lblEmail = new JLabel("E-MAIL :");
		lblEmail.setForeground(SystemColor.windowText);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 13));
		lblEmail.setBounds(10, 65, 56, 14);
		panel_3.add(lblEmail);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"INSERIR", "EXCLUIR", "PROCURAR", "ATUALIZAR"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(10, 302, 107, 29);
		panel_3.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String opcao =(String)comboBox.getSelectedItem();
				
				if(opcao == "PROCURAR" || opcao == "EXCLUIR" )
				{
					txtEmail.setText("");
					txtAluno.setText("");
					txtCep.setText("");
					txtNumero.setText("");
					txtComp.setText("");
					txtEmail.setEnabled(false);
					txtEmail.setBackground(Color.lightGray);
					txtAluno.setEnabled(false);
					txtAluno.setBackground(Color.lightGray);
					txtCep.setEnabled(false);
					txtCep.setBackground(Color.lightGray);
					txtComp.setEnabled(false);
					txtComp.setBackground(Color.lightGray);
					txtNumero.setEnabled(false);
					txtNumero.setBackground(Color.lightGray);
				}
				else
				{
					txtEmail.setEnabled(true);
					txtEmail.setBackground(Color.white);
					txtCep.setEnabled(true);
					txtCep.setBackground(Color.white);
					txtAluno.setEnabled(true);
					txtAluno.setBackground(Color.white);
					txtComp.setEnabled(true);
					txtComp.setBackground(Color.white);
					txtNumero.setEnabled(true);
					txtNumero.setBackground(Color.white);
				}
				
				if(opcao == "INSERIR")
				{
					txtEmail.setText("");
					txtAluno.setText("");
					txtRA.setText("");
				}
				
			}
		});
		
		
		
		JButton btnNewButton = new JButton("EXECUTAR");
		btnNewButton.setIcon(null);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setForeground(new Color(255, 0, 0));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				WebServiceCep ok = WebServiceCep.searchCep("13188-011");
	        	
	        	if(ok.wasSuccessful())
	        	{
	        		txtRua.setText(ok.getLogradouro());
	        		txtBairro.setText(ok.getBairro());
	        		txtEstado.setText(ok.getUf());
	        		txtCidade.setText(ok.getCidade());
	        	}
	        	else
	        		JOptionPane.showMessageDialog(null,"CEP NÃO ENCONTRADO","ERRO",JOptionPane.ERROR_MESSAGE);
	        	
				*/
				
				String opcao =(String)comboBox.getSelectedItem();
				
				switch(opcao) {
				case "ATUALIZAR": atualizarAluno();
				break;
				case "EXCLUIR": excluirAluno();
				break;
				case "PROCURAR": procurarAluno();
				break;
				case "INSERIR": inserirNovoAluno();
				  }
				  
			}
		});
		btnNewButton.setBounds(140, 302, 113, 29);
		panel_3.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(379, 0, 289, 483);
		panel_3.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
	    txtListaAluno = new JTextArea();
	    txtListaAluno.setBackground(SystemColor.menu);
	    txtListaAluno.setEditable(false);
	    txtListaAluno.setFont(new Font("Arial", Font.PLAIN, 12));
	    JScrollPane scroll = new JScrollPane(txtListaAluno);
	    panel_2.add(scroll);
	    
	    JRadioButton rbTodosAlunos = new JRadioButton("LISTAGEM ALUNOS");
	    rbTodosAlunos.setFont(new Font("Arial", Font.ITALIC, 13));
	    rbTodosAlunos.setBackground(new Color(51, 153, 255));
	    rbTodosAlunos.setForeground(SystemColor.desktop);
	    rbTodosAlunos.setVerticalAlignment(SwingConstants.TOP);
	    rbTodosAlunos.setHorizontalAlignment(SwingConstants.LEFT);
	    rbTodosAlunos.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		listarTodosAlunos();
	    	}
	    });
	    rbTodosAlunos.setBounds(10, 416, 163, 23);
	    panel_3.add(rbTodosAlunos);
	    
	    JRadioButton rbOrdemCrescente = new JRadioButton("ORDENA\u00C7\u00C3O POR NOTA");
	    rbOrdemCrescente.setFont(new Font("Arial", Font.ITALIC, 13));
	    rbOrdemCrescente.setBackground(new Color(51, 153, 255));
	    rbOrdemCrescente.setForeground(SystemColor.desktop);
	    rbOrdemCrescente.setVerticalAlignment(SwingConstants.TOP);
	    rbOrdemCrescente.setHorizontalAlignment(SwingConstants.LEFT);
	    rbOrdemCrescente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		listarOrdemCrescente();
	    	}
	    });
	    rbOrdemCrescente.setBounds(10, 442, 206, 23);
	    panel_3.add(rbOrdemCrescente);
	    
	    JRadioButton rbAlunosFrequenciaZerada = new JRadioButton("ALUNOS FREQUENCIA ZERADO");
	    rbAlunosFrequenciaZerada.setFont(new Font("Arial", Font.ITALIC, 13));
	    rbAlunosFrequenciaZerada.setForeground(SystemColor.desktop);
	    rbAlunosFrequenciaZerada.setBackground(new Color(51, 153, 255));
	    rbAlunosFrequenciaZerada.setVerticalAlignment(SwingConstants.TOP);
	    rbAlunosFrequenciaZerada.setHorizontalAlignment(SwingConstants.LEFT);
	    rbAlunosFrequenciaZerada.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		alunosComFrequenciasZeradas();
	    	}
	    });
	    rbAlunosFrequenciaZerada.setBounds(10, 390, 243, 23);
	    panel_3.add(rbAlunosFrequenciaZerada);
	    
	    grupo.add(rbTodosAlunos);
	    grupo.add(rbOrdemCrescente);
	    grupo.add(rbAlunosFrequenciaZerada);
	    
	   //-------------------------------------------------------------------------------------------------------------------------------	
	    JLabel lblVisualizao = new JLabel("VISUALIZA\u00C7\u00C3O");
	    lblVisualizao.setForeground(SystemColor.desktop);
	    lblVisualizao.setFont(new Font("Arial Black", Font.BOLD, 13));
	    lblVisualizao.setBounds(10, 353, 135, 14);
	    panel_3.add(lblVisualizao);
	    
	    JLabel lblCep = new JLabel("CEP :");
	    lblCep.setForeground(Color.BLACK);
	    lblCep.setFont(new Font("Arial", Font.BOLD, 13));
	    lblCep.setBounds(10, 114, 61, 14);
	    panel_3.add(lblCep);
	    
	    ///////////////// MASCARA CEP /////////////////
	    MaskFormatter mascaraCep = null;
	    try {
	    	mascaraCep = new MaskFormatter("#####-###");
	    	
	    }
	    catch(Exception excp)
	    {
 		   JOptionPane.showMessageDialog(null, "Erro na formatação CEP: " + excp.getMessage());

	    }
	    
	    txtCep = new JFormattedTextField(mascaraCep);
	    txtCep.setForeground(Color.BLACK);
	    txtCep.setColumns(10);
	    txtCep.setBackground(Color.WHITE);
	    txtCep.setBounds(59, 112, 123, 20);
	    panel_3.add(txtCep);
	    //////////////////////////////////////////////////
	    
	    
	    txtCep.addKeyListener(new java.awt.event.KeyAdapter() {
	    	public void keyReleased(java.awt.event.KeyEvent e) {
	    		
	    		//int tamanho = txtCep.getText().length();
	    		
	    		txtRua.setText("Aguardando...");
	    		txtBairro.setText("Aguarde...");
	    		txtCidade.setText("Aguarde...");
	    		txtEstado.setText("...");
	    		
	    		boolean terminou = true;
	    		
	    		for(int i = 0 ; i<9 ; i++)
	    			if(txtCep.getText().charAt(i)==' ')
	    				terminou = false;
	    		
	    		if(terminou)
	    		{
	    		   //JOptionPane.showMessageDialog(null, txtCep.getText()+ " | tamanho : "+txtCep.getText().length());
	    		   buscarCep();
	    		   
	    		}

	        }
	    });
	    
	    //13091-105
	    //01234 567
	    
	      /*
	    txtCep.addFocusListener(new java.awt.event.FocusAdapter() {
	    	public void focusGained(java.awt.event.FocusEvent e) {
	    		txtRua.setText("Aguarde...");
	    		txtBairro.setText("Aguarde...");
	    		txtCidade.setText("Aguarde...");
	    		txtEstado.setText("...");
	    		
	    		
	    		//JOptionPane.showMessageDialog(null, txtCep.getText());
	    		if(!temEspaco(txtCep.getText()))
	    		{
	    		   //JOptionPane.showMessageDialog(null, txtCep.getText()+ " | tamanho : "+txtCep.getText().length());
	    		   buscarCep();
	    		   
	    		}
	    	}
	    });
	    */
		
		/////////////////////////////////////////////////////////
		
	    
	    JLabel lblRua = new JLabel("RUA :");
	    lblRua.setForeground(Color.BLACK);
	    lblRua.setFont(new Font("Arial", Font.BOLD, 13));
	    lblRua.setBounds(10, 165, 61, 14);
	    panel_3.add(lblRua);
	    
	    txtRua = new JTextField();
	    txtRua.setEditable(false);
	    txtRua.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    txtRua.setForeground(Color.BLACK);
	    txtRua.setColumns(10);
	    txtRua.setBackground(Color.LIGHT_GRAY);
	    txtRua.setBounds(48, 163, 140, 20);
	    panel_3.add(txtRua);
	    
	    JLabel lblBairro = new JLabel("BAIRRO :");
	    lblBairro.setForeground(Color.BLACK);
	    lblBairro.setFont(new Font("Arial", Font.BOLD, 13));
	    lblBairro.setBounds(196, 165, 61, 14);
	    panel_3.add(lblBairro);
	    
	    txtBairro = new JTextField();
	    txtBairro.setEditable(false);
	    txtBairro.setForeground(Color.BLACK);
	    txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    txtBairro.setColumns(10);
	    txtBairro.setBackground(Color.LIGHT_GRAY);
	    txtBairro.setBounds(261, 161, 113, 20);
	    panel_3.add(txtBairro);
	    
	    JLabel lblCidade = new JLabel("CIDADE :");
	    lblCidade.setForeground(Color.BLACK);
	    lblCidade.setFont(new Font("Arial", Font.BOLD, 13));
	    lblCidade.setBounds(10, 204, 61, 14);
	    panel_3.add(lblCidade);
	    
	    txtCidade = new JTextField();
	    txtCidade.setEditable(false);
	    txtCidade.setForeground(Color.BLACK);
	    txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    txtCidade.setColumns(10);
	    txtCidade.setBackground(new Color(192, 192, 192));
	    txtCidade.setBounds(76, 202, 107, 20);
	    panel_3.add(txtCidade);
	    
	    JLabel lblEstado = new JLabel("ESTADO :");
	    lblEstado.setForeground(Color.BLACK);
	    lblEstado.setFont(new Font("Arial", Font.BOLD, 13));
	    lblEstado.setBounds(194, 206, 61, 14);
	    panel_3.add(lblEstado);
	    
	    txtEstado = new JTextField();
	    txtEstado.setEditable(false);
	    txtEstado.setForeground(Color.BLACK);
	    txtEstado.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    txtEstado.setColumns(10);
	    txtEstado.setBackground(Color.LIGHT_GRAY);
	    txtEstado.setBounds(260, 204, 35, 20);
	    panel_3.add(txtEstado);
	    
	    JLabel lblNumeroResindencial = new JLabel("NUMERO RESINDENCIAL :");
	    lblNumeroResindencial.setForeground(Color.BLACK);
	    lblNumeroResindencial.setFont(new Font("Arial", Font.BOLD, 13));
	    lblNumeroResindencial.setBounds(10, 239, 163, 14);
	    panel_3.add(lblNumeroResindencial);
	    
	    txtNumero = new JTextField();
	    txtNumero.setForeground(Color.BLACK);
	    txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    txtNumero.setColumns(10);
	    txtNumero.setBackground(Color.WHITE);
	    txtNumero.setBounds(181, 237, 53, 20);
	    panel_3.add(txtNumero);
	    
	    JLabel lblComplemento = new JLabel("COMPLEMENTO :");
	    lblComplemento.setForeground(Color.BLACK);
	    lblComplemento.setFont(new Font("Arial", Font.BOLD, 13));
	    lblComplemento.setBounds(10, 271, 123, 14);
	    panel_3.add(lblComplemento);
	    
	    txtComp = new JTextField();
	    txtComp.setForeground(Color.BLACK);
	    txtComp.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    txtComp.setColumns(10);
	    txtComp.setBackground(Color.WHITE);
	    txtComp.setBounds(127, 271, 119, 20);
	    panel_3.add(txtComp);
	    
	   
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("FEZ", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setToolTipText("");
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menuText, null, null, null));
		panel_4.setBackground(new Color(255, 51, 51));
		panel.add(panel_4, BorderLayout.CENTER);
		
		textRA_fez = new JTextField();
		textRA_fez.setForeground(Color.BLACK);
		textRA_fez.setColumns(10);
		textRA_fez.setBounds(109, 11, 158, 20);
		panel_4.add(textRA_fez);
		
		textMat_fez = new JTextField();
		textMat_fez.setColumns(10);
		textMat_fez.setBounds(109, 54, 158, 20);
		panel_4.add(textMat_fez);
		
		textNota_Fez = new JTextField();
		textNota_Fez.setColumns(10);
		textNota_Fez.setBounds(109, 131, 158, 20);
		panel_4.add(textNota_Fez);
		
		JLabel label = new JLabel("RA :");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.BOLD, 12));
		label.setBounds(10, 13, 46, 14);
		panel_4.add(label);
		
		JLabel lblNota = new JLabel("NOTA :");
		lblNota.setForeground(Color.BLACK);
		lblNota.setFont(new Font("Arial", Font.BOLD, 12));
		lblNota.setBounds(10, 133, 46, 14);
		panel_4.add(lblNota);
		
		JLabel lblMateria = new JLabel("MATERIA :");
		lblMateria.setForeground(Color.BLACK);
		lblMateria.setFont(new Font("Arial", Font.BOLD, 12));
		lblMateria.setBounds(10, 56, 64, 14);
		panel_4.add(lblMateria);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"ATUALIZAR", "EXCLUIR", "PROCURAR", "INSERIR"}));
		comboBox_1.setToolTipText("");
		comboBox_1.setBounds(10, 186, 116, 29);
		panel_4.add(comboBox_1);
		
		JButton button_Exect_fez = new JButton("EXECUTAR");
		button_Exect_fez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String opcao =(String)comboBox_1.getSelectedItem();
				
				switch(opcao) {
				case "ATUALIZAR": atualizarFez();
				break;
				case "EXCLUIR": excluirFez();
				break;
				case "PROCURAR": procurarFez();
				break;
				case "INSERIR": inserirFezNovo();
				  }
			
			}
		});
		
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String opcao =(String)comboBox_1.getSelectedItem();
				
				if(opcao == "PROCURAR" || opcao == "EXCLUIR" )
				{
					textFreq_Fez.setText("");
					textNota_Fez.setText("");
					textFreq_Fez.setEnabled(false);
					textFreq_Fez.setBackground(Color.gray);
					textNota_Fez.setEnabled(false);
					textNota_Fez.setBackground(Color.gray);
				}
				else
				{
					textFreq_Fez.setEnabled(true);
					textFreq_Fez.setBackground(Color.white);
					textNota_Fez.setEnabled(true);
					textNota_Fez.setBackground(Color.white);
				}
				
				if(opcao == "INSERIR")
				{
					textFreq_Fez.setText("");
					textNota_Fez.setText("");
					textMat_fez.setText("");
					textRA_fez.setText("");
				}
				
			}
		});
		
		button_Exect_fez.setForeground(Color.RED);
		button_Exect_fez.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_Exect_fez.setBounds(151, 186, 116, 29);
		panel_4.add(button_Exect_fez);
		
		JRadioButton rdbtnListagemFez = new JRadioButton("LISTAGEM DESEMPENHO ALUNOS");
		rdbtnListagemFez.setVerticalAlignment(SwingConstants.TOP);
		rdbtnListagemFez.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnListagemFez.setForeground(new Color(0, 0, 0));
		rdbtnListagemFez.setFont(new Font("Dialog", Font.ITALIC, 12));
		rdbtnListagemFez.setBackground(new Color(255, 51, 51));
		rdbtnListagemFez.setBounds(6, 296, 242, 23);
		panel_4.add(rdbtnListagemFez);
		rdbtnListagemFez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFezes();
			}
		});
		
		JLabel lblVisualizao_1 = new JLabel("VISUALIZA\u00C7\u00C3O");
		lblVisualizao_1.setForeground(Color.BLACK);
		lblVisualizao_1.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblVisualizao_1.setBounds(10, 246, 147, 30);
		panel_4.add(lblVisualizao_1);
		
		JLabel lblFrequencia = new JLabel("FREQUENCIA :");
		lblFrequencia.setForeground(Color.BLACK);
		lblFrequencia.setFont(new Font("Arial", Font.BOLD, 12));
		lblFrequencia.setBounds(10, 96, 78, 14);
		panel_4.add(lblFrequencia);
		
		textFreq_Fez = new JTextField();
		textFreq_Fez.setForeground(Color.BLACK);
		textFreq_Fez.setColumns(10);
		textFreq_Fez.setBounds(109, 90, 158, 20);
		panel_4.add(textFreq_Fez);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(310, 11, 289, 348);
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		txtListaFez = new JTextArea();
		txtListaFez.setBackground(SystemColor.menu);
		txtListaFez.setEditable(false);
		txtListaFez.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane scroll2 = new JScrollPane(txtListaFez);
		panel_5.add(scroll2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(152, 251, 152));
		tabbedPane.addTab("MATERIA", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblCodigo = new JLabel("CODIGO MATERIA :");
		lblCodigo.setForeground(Color.BLACK);
		lblCodigo.setFont(new Font("Arial", Font.BOLD, 13));
		lblCodigo.setBounds(6, 31, 122, 14);
		panel_1.add(lblCodigo);
		
		txtMCodMateria = new JTextField();
		txtMCodMateria.setForeground(Color.BLACK);
		txtMCodMateria.setColumns(10);
		txtMCodMateria.setBounds(134, 29, 86, 20);
		panel_1.add(txtMCodMateria);
		
		txtMNome = new JTextField();
		txtMNome.setColumns(10);
		txtMNome.setBounds(62, 82, 158, 20);
		panel_1.add(txtMNome);
		
		JLabel lblNome = new JLabel("NOME  :");
		lblNome.setForeground(Color.BLACK);
		lblNome.setFont(new Font("Arial", Font.BOLD, 13));
		lblNome.setBounds(10, 84, 56, 14);
		panel_1.add(lblNome);
		
		JComboBox cBMaterias = new JComboBox();
		cBMaterias.setModel(new DefaultComboBoxModel(new String[] {"ATUALIZAR", "EXCLUIR", "PROCURAR", "INSERIR"}));
		cBMaterias.setToolTipText("");
		cBMaterias.setBounds(6, 143, 122, 36);
		panel_1.add(cBMaterias);
		
		cBMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String opcao =(String)cBMaterias.getSelectedItem();
				
				if(opcao == "PROCURAR" || opcao == "EXCLUIR" )
				{
					txtMNome.setText("");
					txtMNome.setEnabled(false);
					txtMNome.setBackground(Color.gray);
				}
				else
				{
					
					txtMNome.setEnabled(true);
					txtMNome.setBackground(Color.white);
					txtMCodMateria.setEnabled(true);
					txtMCodMateria.setBackground(Color.white);
				}
				
				if(opcao == "INSERIR")
				{
					txtMNome.setText("");
					txtMCodMateria.setText("");
				}
				
			}
		});
		JButton btnMateriaExec = new JButton("EXECUTAR");
		btnMateriaExec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String opcao = (String)cBMaterias.getSelectedItem();
				
				switch(opcao)
				{
				  case "ATUALIZAR": atualizarMateria();
				    break;
				  case  "EXCLUIR": excluirMateria();
				    break;
				  case "PROCURAR": procurarMateria();
				    break;
				  case "INSERIR": inserirMateriaNova();
				       
				}
			}
		});
		btnMateriaExec.setForeground(Color.RED);
		btnMateriaExec.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMateriaExec.setBounds(142, 143, 122, 36);
		panel_1.add(btnMateriaExec);
		
		JLabel label_3 = new JLabel("VISUALIZA\u00C7\u00C3O");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Arial Black", Font.BOLD, 14));
		label_3.setBounds(6, 211, 135, 14);
		panel_1.add(label_3);
		
		ButtonGroup grupo3 = new ButtonGroup();
		
		JRadioButton rdbtnMateriasSemReprovao = new JRadioButton("MATERIAS SEM REPROVA\u00C7\u00C3O");
		rdbtnMateriasSemReprovao.setVerticalAlignment(SwingConstants.TOP);
		rdbtnMateriasSemReprovao.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnMateriasSemReprovao.setForeground(Color.BLACK);
		rdbtnMateriasSemReprovao.setFont(new Font("Arial", Font.ITALIC, 13));
		rdbtnMateriasSemReprovao.setBackground(new Color(152, 251, 152));
		rdbtnMateriasSemReprovao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMateriasSemReprovados();
			}
		});
		rdbtnMateriasSemReprovao.setBounds(6, 249, 243, 23);
		panel_1.add(rdbtnMateriasSemReprovao);
		
		JRadioButton rbtnListagemMaterias = new JRadioButton("LISTAGEM MATERIAS");
		rbtnListagemMaterias.setVerticalAlignment(SwingConstants.TOP);
		rbtnListagemMaterias.setHorizontalAlignment(SwingConstants.LEFT);
		rbtnListagemMaterias.setForeground(Color.BLACK);
		rbtnListagemMaterias.setFont(new Font("Arial", Font.ITALIC, 13));
		rbtnListagemMaterias.setBackground(new Color(152, 251, 152));
		rbtnListagemMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMaterias();
			}
		});
		rbtnListagemMaterias.setBounds(6, 292, 163, 23);
		panel_1.add(rbtnListagemMaterias);
		
		JRadioButton rdbtnOrdenaoPorMedia = new JRadioButton("ORDENA\u00C7\u00C3O POR MEDIA");
		rdbtnOrdenaoPorMedia.setVerticalAlignment(SwingConstants.TOP);
		rdbtnOrdenaoPorMedia.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnOrdenaoPorMedia.setForeground(Color.BLACK);
		rdbtnOrdenaoPorMedia.setFont(new Font("Arial", Font.ITALIC, 13));
		rdbtnOrdenaoPorMedia.setBackground(new Color(152, 251, 152));
		rdbtnOrdenaoPorMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMateriasComMediaOrdenada();
			}
		});
		rdbtnOrdenaoPorMedia.setBounds(6, 329, 206, 23);
		panel_1.add(rdbtnOrdenaoPorMedia);
		
		grupo3.add(rdbtnMateriasSemReprovao);
		grupo3.add(rbtnListagemMaterias);
		grupo3.add(rdbtnOrdenaoPorMedia);
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(310, 11, 289, 348);
		panel_1.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		txtListaMateria = new JTextArea();
		txtListaMateria.setBackground(SystemColor.menu);
		txtListaMateria.setEditable(false);
		txtListaMateria.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane scroll3 = new JScrollPane(txtListaMateria);
		panel_6.add(scroll3);
				
	}
}

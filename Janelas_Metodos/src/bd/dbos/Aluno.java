package bd.dbos;

public class Aluno {

	    private int    RA;
	    private String nome;
	    private String  email;
	    private String cep;
	    private String rua;
	    private String bairro;
	    private String estado;
	    private String cidade;
	    private String numero;
	    private String complemento;
	    
	    public String getNumero() {
			return numero;
		}


		public void setNumero(String numero) throws Exception {
			
			if (numero == null ||numero.equals(""))
	            throw new Exception ("numero invalido");
			
			this.numero = numero;
		}


		public String getComplemento() {
			return complemento;
		}


		public void setComplemento(String complemento) throws Exception {
			
			if (complemento == null ||complemento.equals(""))
	            throw new Exception ("comlemento invalido");
			
			this.complemento = complemento;
		}

		
	    
	    public String getEstado() {
			return estado;
		}


		public void setEstado(String estado)throws Exception
		{
			if (estado == null ||estado.equals(""))
	            throw new Exception ("estado invalido");
			
			this.estado = estado;
		}


		public String getCidade() {
			return cidade;
		}


		public void setCidade(String cidade) throws Exception
		{
			if (cidade == null ||cidade.equals(""))
	            throw new Exception ("cidade invalido");
			
			this.cidade = cidade;
		}

		
	    
	    public String getBairro() {
			return bairro;
		}


		public void setBairro(String bairro) throws Exception
		{
			if (bairro == null ||bairro.equals(""))
            throw new Exception ("bairro invalido");
			this.bairro = bairro;
		}


		public void setCep (String cep) throws Exception
	    {
	        if (cep == null ||cep.equals(""))
	            throw new Exception ("cep invalido");

	        this.cep = cep;
	    }   
	    
	    
	    public void setRua (String rua) throws Exception
	    {
	        if (rua == null ||rua.equals(""))
	            throw new Exception ("cep invalido");

	        this.rua = rua;
	    }   

	 
	    public void setRA (int ra) throws Exception
	    {
	        if (ra <= 0)
	            throw new Exception ("Codigo invalido");

	        this.RA = ra;
	    }   

	    public void setNome (String nome) throws Exception
	    {
	        if (nome==null || nome.equals(""))
	            throw new Exception ("Nome nao fornecido");

	        this.nome = nome;
	    }

	    public void setEmail (String email) throws Exception
	    {
	        if (email == null || email.equals(""))
	            throw new Exception ("email invalido");

	        this.email = email;
	    }

	    public int getRA ()
	    {
	        return this.RA;
	    }
	    
	    public String getRua ()
	    {
	        return this.rua;
	    }
	    
	    public String getCep ()
	    {
	        return this.cep;
	    }

	    public String getNome ()
	    {
	        return this.nome;
	    }

	    public String getEmail ()
	    {
	        return this.email;
	    }

	    public Aluno (int ra, 
	    		String nome,
	    		String email , 
	    		String cep,
	    		String rua,
	    		String bairro,
	    		String estado,
	    		String cidade,
	    		String numero,
	    		String complemento) throws Exception
	    
	    {
	        this.setRA       (ra);
	        this.setNome   (nome);
	        this.setEmail  (email);
	        this.setCep    (cep);
	        this.setRua    (rua);
	        this.setBairro(bairro);
	        this.setCidade(cidade);
	        this.setEstado(estado);
	        this.setComplemento(complemento);
	        this.setNumero(numero);
	    }

	    public String toString ()
	    {
	        String ret="";

	        ret+="RA: "+this.RA+"\n";
	        ret+="Nome..: "+this.nome  +"\n";
	        ret+="cep...: "+this.cep  +"\n";
	        ret+="Email.: "+this.email;

	        return ret;
	    }

	    public boolean equals (Object obj)
	    {
	        if (this==obj)
	            return true;

	        if (obj==null)
	            return false;

	        if (!(obj instanceof Aluno))
	            return false;

	        Aluno aln = (Aluno)obj;

	        if (this.RA!=aln.RA)
	            return false;

	        if (!this.nome.equals(aln.nome))
	            return false;

	        if (!this.email.equals(aln.email))
	            return false;
	        
	        if (!this.cep.equals(aln.cep))
	            return false;

	        return true;
	    }

	    public int hashCode ()
	    {
	        int ret=666;

	        ret = 7*ret + new Integer(this.RA).hashCode();
	        ret = 7*ret + this.nome.hashCode();
	        ret = 7*ret + this.email.hashCode();
	        ret = 7*ret + this.cep.hashCode();

	        return ret;
	    }


	    public Aluno (Aluno modelo) throws Exception
	    {
	        this.RA = modelo.RA; // nao clono, pq nao eh objeto
	        this.nome   = modelo.nome;   // nao clono, pq nao eh clonavel
	        this.email  = modelo.email;  // nao clono, pq nao eh objeto
	        this.cep  = modelo.cep;
	    }

	    public Object clone ()
	    {
	        Aluno ret=null;

	        try
	        {
	            ret = new Aluno (this);
	        }
	        catch (Exception erro)
	        {} // nao trato, pq this nunca � null e construtor de
	           // copia da excecao qdo seu parametro for null

	        return ret;
	    }
	
}

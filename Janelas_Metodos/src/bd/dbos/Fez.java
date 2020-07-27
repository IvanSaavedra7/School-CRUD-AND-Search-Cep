package bd.dbos;

public class Fez {
	
		private int 	ra;
	    private int    codigo;
	    private float nota;
	    private int  frequencia;
	 
	    public void setCodigo (int codigo) throws Exception
	    {
	        if (codigo <= 0)
	            throw new Exception ("Codigo invalido");

	        this.codigo = codigo;
	    }   

	    public void setRa (int ra) throws Exception
	    {
	        if (ra == 0 )
	            throw new Exception ("ra nao fornecido");

	        this.ra = ra;
	    }

	    public void setNota (float nota) throws Exception
	    {
	        if (nota < 0)
	            throw new Exception ("nota invalido");

	        this.nota = nota;
	    }
	    
	    public void setFrequencia (int frequencia) throws Exception
	    {
	        if (frequencia < 0 || frequencia > 100 )
	            throw new Exception ("frequencia invalido");

	        this.frequencia = frequencia;
	    }

	    
	    public int getRa ()
	    {
	        return this.ra;
	    }
	    
	    public int getCodigo ()
	    {
	        return this.codigo;
	    }

	    public float getNota ()
	    {
	        return this.nota;
	    }

	    public int getFrequencia ()
	    {
	        return this.frequencia;
	    }

	    public Fez (int ra , int codigo, float nota, int frequencia) throws Exception
	    {
	    	this.setRa(ra);
	        this.setCodigo (codigo);
	        this.setNota   (nota);
	        this.setFrequencia  (frequencia);
	    }

	    public String toString ()
	    {
	        String ret="";

	        ret+="RA: "+this.ra+"\n";
	        ret+="Codigo: "+this.codigo+"\n";
	        ret+="nota..: "+this.nota  +"\n";
	        ret+="frequencia.: "+this.frequencia;

	        return ret;
	    }

	    public boolean equals (Object obj)
	    {
	        if (this==obj)
	            return true;

	        if (obj==null)
	            return false;

	        if (!(obj instanceof Livro))
	            return false;

	        Fez fez = (Fez)obj;

	        if (this.ra!=fez.ra)
	            return false;
	        
	        if (this.codigo!=fez.codigo)
	            return false;

	        if (this.nota!= fez.nota)
	            return false;

	        if (this.frequencia!=fez.frequencia)
	            return false;

	        return true;
	    }

	    public int hashCode ()
	    {
	        int ret=666;

	        ret = 7*ret + new Integer(this.ra).hashCode();
	        ret = 7*ret + new Integer(this.codigo).hashCode();
	        ret = 7*ret + new Float(this.nota).hashCode();
	        ret = 7*ret + new Integer(this.frequencia).hashCode();

	        return ret;
	    }


	    public Fez (Fez modelo) throws Exception
	    {
	    	this.ra  = modelo.ra;  // nao clono, pq nao eh objeto
	        this.codigo = modelo.codigo; // nao clono, pq nao eh objeto
	        this.nota   = modelo.nota;   // nao clono, pq nao eh clonavel
	        this.frequencia  = modelo.frequencia;  // nao clono, pq nao eh objeto
	    }

	    public Object clone ()
	    {
	        Fez ret=null;

	        try
	        {
	            ret = new Fez (this);
	        }
	        catch (Exception erro)
	        {} // nao trato, pq this nunca � null e construtor de
	           // copia da excecao qdo seu parametro for null

	        return ret;
	    }

}

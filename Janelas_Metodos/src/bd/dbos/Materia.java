package bd.dbos;

public class Materia {

    private int    codigo;
    private String nome;
 
    public void setCodigo (int cod) throws Exception
    {
        if (cod <= 0)
            throw new Exception ("Codigo invalido");

        this.codigo = cod;
    }   

    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }



    public int getCodigo ()
    {
        return this.codigo;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public Materia (int codigo, String nome) throws Exception
    {
        this.setCodigo (codigo);
        this.setNome   (nome);
    }

    public String toString ()
    {
        String ret="";

        ret+="codMateria: "+this.codigo+"\n";
        ret+="Nome..: "+this.nome;

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof Materia))
            return false;

        Materia matr = (Materia)obj;

        if (this.codigo!=matr.codigo)
            return false;

        if (!this.nome.equals(matr.nome))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + new Integer(this.codigo).hashCode();
        ret = 7*ret + this.nome.hashCode();

        return ret;
    }


    public Materia (Materia modelo) throws Exception
    {
        this.codigo = modelo.codigo; // nao clono, pq nao eh objeto
        this.nome   = modelo.nome;   // nao clono, pq nao eh clonavel
    }

    public Object clone ()
    {
        Materia ret=null;

        try
        {
            ret = new Materia (this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca é null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
}

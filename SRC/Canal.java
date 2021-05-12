package canales;

/**
 * Clase Canal
 * 
 * @author Javier Garcia Garcia
 *
 */
public class Canal {
    private String nombre;
    private String direccion;
    private String file_name;
    private ListaEnlazada<Programa> programas;
    private int prioridad;
    public Canal(String n, String d)
    {
        nombre = n;
        direccion = d;
        programas = new ListaEnlazada<Programa>();
    }
    public Canal(String n, String d,String f,int p)
    {
        nombre = n;
        direccion = d;
        file_name=f;
        prioridad=p;
        programas = new ListaEnlazada<Programa>();
    }
    public void setNombre(String n)
    {
        nombre = n;
    }

    public void setDireccion(String d)
    {
        direccion = d;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public String getFileName()
    {
        return file_name;
    }

    /**
     * Metodo bajar contenido.
     * 
     * @return Utils.downloadFile
     */
    public boolean bajaContenido()
    {
        int last_slash_index = direccion.lastIndexOf("/");
        file_name = "channels/" + nombre.trim();
        
        return Utils.downloadFile(direccion, file_name);
    }

    public void insertarPrograma(Programa p)
    {
        programas.insertar(p);
    }

    /**
     * Metodo Eliminar Programa.
     * 
     * @param p
     */
    public void eliminarPrograma(Programa p)
    {
        int pos = programas.buscarForward(p);
        programas.borrarForward(pos);
    }

    /**
     * Metodo ListaEnlazada
     * 
     * @return programas
     */
    public ListaEnlazada<Programa> getProgramas()
    {
        return this.programas;
    }
    public int getPrioridad(){
    	return this.prioridad;
    }
    public void setPrioridad(int prioridad){
    	this.prioridad=prioridad;
    }
}

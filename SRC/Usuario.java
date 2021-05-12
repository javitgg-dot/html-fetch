package canales;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase Usuario
 * 
 * Clase que representa un usuario del sistema
 * 
 * @author Javier Garcia Garcia
 *
 */
public class Usuario{
    private String fichero;
    private String password;
    private HTMLFetch datos;
    // Colecci�n de canales del usuario
    private ListaES<Canal> canalesFavoritos;
    private ColaEnlazada<Programa> programasFavoritos;
	
    // Constructor
    public Usuario(HTMLFetch datos, String fichero, String password, boolean encriptada){
            this.fichero = fichero;
            this.datos = datos;
            // Si la contrase�a nos la dan encriptada, hay que desencriptarla
            if (encriptada){
                    this.password = Utils.descifrar(password);
            }else{
                    this.password = password;
            }
            canalesFavoritos = new ListaES<Canal>();
            programasFavoritos = new ColaEnlazada<Programa>();
    }

    /**
     * Metodo de acceso
     * 
     * @return fichero
     */
    public String getFichero() {
            return fichero;
    }

    public void setLogin(String fichero) {
            this.fichero = fichero;
    }

    public ListaES<Canal> getListaCanales() {
            return canalesFavoritos;
    }

    /**
     * Metodo que valida que la contrasena introducida es correcta
     * 
     * @param password
     * @return password
     */
    public boolean esPasswordCorrecta(String password){
            return this.password.equals(password);
    }

/**
 * Metodo que inserta un programa en la coleccion
 * 
 * @param c
 * @param p
 */
    public void insertarPrograma(Canal c, Programa p){
        c.insertarPrograma(p);
    }

/**
 * Metodo que elimina un programa de la colecci�n
 * @param c
 * @param p
 */
    public void eliminarPrograma(Canal c, Programa p){
        c.eliminarPrograma(p);
    }
	
/**
 * Metodo que carga la coleccien de programas del usuario
 */
    public void cargarTodo(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            String linea = br.readLine();
            // Primera linea es user:password

            // Siguientes hasta [Programas] son Canales
            linea = br.readLine();
            while((linea = br.readLine()).compareTo("[Programas]")!=0)
            {
                // Falta bajar de la web la direccion del canal
                int pos = canalesFavoritos.longitud()+1;
                canalesFavoritos.insertar(pos, datos.getCanal(linea));
            }

            // Leemos programas y recogemos su contenido
            while((linea = br.readLine())!=null)
            {
                String[] programas = linea.split("/");
                for(int i=1;i<programas.length;i++)
                {

                    Canal c = datos.getCanal(programas[0]);
                    // Falta coger los datos del canal de la pagina web
                    programasFavoritos.insertar(new Programa(c));
                }
            }
            
        } catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
		
    @Override
    public String toString(){
            return "";
    }

    /**
     * Mostrar Canales
     */
    public void MostrarCanales()
    {
        System.out.println("Tamaño canales favoritos: " + canalesFavoritos.longitud());

        for(int i=1;i<=canalesFavoritos.longitud();i++)
        {
            Canal c = canalesFavoritos.consultar(i);
            System.out.println("Canal nombre: " + c.getNombre());
            System.out.println("Canal direccion: " + c.getDireccion());
            System.out.println("---------------------------------------");
        }
    }
}


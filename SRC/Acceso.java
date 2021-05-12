package canales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Acceso.
 * 
 * @author Javier Garcia Garcia
 *
 */
public class Acceso {

	/**
	 * Metodo registro.
	 * 
	 * @param fichero
	 * @param user
	 * @param password
	 * @return boolean
	 */
    public static boolean registro(String fichero, String user, String password)
    {
        try {
            // Anadir usuario al fichero usuarios.txt
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fichero), true));
            String ficheroUsuario = user + ".txt";
            String cadena = user + ":" + Utils.encriptar(password) + ":" + ficheroUsuario;
            bw.append(cadena);
            bw.close();
            // Crear fichero para ese usuario
            File fichero1= new File(cadena);
            //bw = new BufferedWriter(new FileWriter(new File("usuarios/" + ficheroUsuario),true));
            bw = new BufferedWriter(new FileWriter(new File(ficheroUsuario),true));
            //bw.append(user+":"+password);
            //bw.newLine();
            //bw.append("[Canales]");
            //bw.newLine();
            //bw.append("[Programas]");
            bw.close();
            // Todo OK
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Metodo Existe.
     * 
     * @param fichero
     * @param user
     * @return boolean
     */
    public static boolean existe(String fichero, String user)
    {
        try {
            File f = new File(fichero);
            if(f.exists())
            {
                BufferedReader br = new BufferedReader(new FileReader(new File(fichero)));
                String cadena="";
                while((cadena=br.readLine())!=null)
                {
                    String[] datos = cadena.split(":");
                    if(user.compareTo(datos[0])==0)
                        return true;
                }
                br.close();
                return false;
            }
            else
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fichero)));
                bw.close();
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Metodo login.
     * 
     * @param fichero
     * @param user
     * @param password
     * @return datos[]
     */
    public static String login(String fichero, String user, String password)
    {
    	
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fichero)));
            String cadena="";
            while((cadena=br.readLine())!=null)
            {
                String[] datos = cadena.split(":");
                // Si hemos encontrado al usuario
                if(user.compareTo(datos[0])==0)
                {
                    // Comparamos contrasenas
                    String pwd = Utils.encriptar(password);
                    // Si coincide
                    if(pwd.compareTo(datos[1])==0)
                    {
                        br.close();
                        return datos[2];
                    }
                    else
                    {
                        br.close();
                        return null;
                    }
                }
            }
            br.close();
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * Metodo init
     * 
     * @param fichero
     * @return String
     */
    public static String init(String fichero){
    	String fichero_usuario = null;
    	String usuario="";
    	try{
    		System.out.print("Introduce nombre usuario> ");
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		usuario = br.readLine();
    		if (existe(fichero, usuario))
    		{
    			int intentos = 0;
    			while(fichero_usuario == null && intentos<3)
    			{
    				System.out.print("Introduce password> ");
    				intentos++;
    				String password = br.readLine();
    				fichero_usuario = Acceso.login(fichero, usuario, password);
    			}

    			if(intentos==3)
    			{
    				System.out.println("Password incorrecta 3 veces. Saliendo...");
    				return "";
    			}
    		}
    		else // Si no existe el usuario, nos registramos
    		{
    			System.out.println("No existe el usuario, procediendo al registro...");
    			System.out.print("Introduce password> ");
            	String password1 = br.readLine();
            	System.out.print("Repite password> ");
            	String password2 = br.readLine();
            	if(password1.compareTo(password2)==0)
            	{
            		if(!Acceso.registro(fichero, usuario, password1))
            		{
            			System.out.println("Error en el registro. Saliendo...");
            			return "";
            		}
            	}
            	else
            	{
            		System.out.println("La verificacion de password no es correcta. Saliendo...");
            		return "";
            	}
            	
    		}
    	}catch(IOException ex){
    		  Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
    		  return "";
    	}
    	
    	return usuario;
    }
    
}

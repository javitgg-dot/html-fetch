package canales;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Utils
 * 
 * @author Javier Garcia Garcia
 *
 */
public class Utils {

    public static final String DATE_FORMAT_NOW = "HH:mm:ss";

/**
 * Descarga un fichero en una ubucacion especifica
 * @param direccion
 * @param outName
 * @return
 */
    public static boolean downloadFile(String direccion, String outName)
    {
        BufferedInputStream in = null;
        try {
        	//Si no existe el directorio principal lo creamos
        	String outName2="channels";
        	File directorio = new File(outName2);
        	directorio.mkdirs();
        	
        	
            in = new BufferedInputStream(new java.net.URL(direccion).openStream());
            
            FileOutputStream fos = new FileOutputStream(outName);
            
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            byte[] data = new byte[1024];
            while (in.read(data, 0, 1024) >= 0) {
                bout.write(data);
            }
            bout.close();
            in.close();
            return true;
        } catch (IOException ex) {
        	
        	File directorio = new File(outName);
        	directorio.mkdirs();
           
        	Logger.getLogger(HTMLFetch.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Metodo existe Ruta
     * 
     * @param ruta
     * @return
     */
    public static boolean existeRuta(String ruta){
    	BufferedReader br; 
    	System.out.println(ruta);
    	try{
    		 br = new BufferedReader(new FileReader(new File(ruta)));
    		 br.close();
    	}catch(IOException ex){
    		System.out.println("Ruta No existe");System.out.println("Ruta No exsiste");
    		return false;
    		
    	}
    	System.out.println("Ruta existe");
    	return true;
    	
    }
/**
 * Metodo que nos devuelve un String con la hora en formato DATE_FORMAT_NOW
 * 
 * @return sdf.format(cal.getTime())
 */
    public static String now()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

/**
 * Metodo que encripta una cadena de caracteres
 * 
 * @param password
 * @return pswdEncriptada
 */
    public static String encriptar(String password){
        String pswdEncriptada = "";
        for (int i=0; i<password.length(); i++){
                pswdEncriptada += ((char)((password.charAt(i)+1)%255));
        }
        return pswdEncriptada;
    }

/**
 * Metodo que descifra una cadena encriptada
 * 
 * @param password
 * @return pswdDesencriptada
 */
    public static String descifrar(String password){
        String pswdDesencriptada = "";
        for (int i=0; i<password.length(); i++){
                pswdDesencriptada += ((char)((password.charAt(i)+254)%255));
        }
        return pswdDesencriptada;
    }

/**
 * Metodo que nos dice si un canal esta ya en una lista
 * 
 * @param canales
 * @param c
 * @return boolean
 */
    public static boolean estaCanal(ArrayList<Canal> canales, Canal c)
    {
        for(int i=0;i<canales.size();i++)
            if(c.getNombre().compareTo(canales.get(i).getNombre())==0)
                return true;
        return false;
    }

    /**
     * Date
     * 
     * @return cal.getTime()
     */
    public static Date horaActual()
    {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    /**
     * Metodo sumar horas
     * 
     * @param h1
     * @param h2
     * @return h1.getTime() + h2.getTime()
     */
    public static long sumarHoras(Time h1, Time h2)
    {
        return h1.getTime() + h2.getTime();
    }

    /**
     * Todavia en emision
     * 
     * @param p
     * @return boolean
     */
    public static boolean todaviaEnEmision(Programa p)
    {
        long t = sumarHoras(p.getHora(), p.getDuracion());

        long horaActualLong = horaActualMillis();

        long fin = t;
        long inicio = p.getHora().getTime();

        if( horaActualLong >= inicio && horaActualLong <= fin)
            return true;
        else
            return false;
    }

    /**
     * Metodo horaActualMillis
     * 
     * @return horaActualLong
     */
    public static long horaActualMillis()
    {
        String horaActual = now();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long horaActualLong = 0;
        try {
            horaActualLong = new Time(sdf.parse(horaActual).getTime()).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return horaActualLong;
    }

}

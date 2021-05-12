package canales;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase Programa
 * 
 * @author Javier Garcia Garcia
 *
 */
public class Programa {
    private Canal cadena;
    private String titulo;
    private Time horaInicio;
    private Time duracion;
    private String descripcion;
    private int prioridad;
    public static final String DATE_FORMAT_NOW = "HH:mm";

    public Programa(Canal c)
    {
        this.cadena = c;
        // Falta poner la duracion, esta puesta a pelo!!!
        this.duracion = new Time(3600000);
    }
    public Programa(Canal c,String t,int prior){
    	this.cadena=c;
    	this.titulo=t;
    	this.prioridad=prior;
    }

    public void setHoraInicio(String h)
    {
        this.horaInicio = Time.valueOf(h + ":00");
    }

    public String getHoraInicio()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(this.horaInicio);
    }

    public Time getHora()
    {
        return this.horaInicio;
    }

    public Canal getCadena()
    {
        return this.cadena;
    }

    public void setCadena(Canal c)
    {
        this.cadena = c;
    }

    public String getTitulo()
    {
        return this.titulo;
    }

    public void setTitulo(String t)
    {
        this.titulo = t;
    }

    public Time getDuracion()
    {
        return this.duracion;
    }

    public String getDescripcion()
    {
        return this.descripcion;
    }

    public void setDescripcion(String d)
    {
        this.descripcion = d;
    }

    public boolean equals(Programa p)
    {
        if((this.cadena.getNombre().compareTo(p.cadena.getNombre())==0)
            &&(this.titulo.compareTo(p.getTitulo())==0))
            return true;
        else
            return false;
    }

    public String horaFin()
    {
        return ""+Utils.sumarHoras(horaInicio, duracion);
    }
    public int getPrioridad(){
    	return this.prioridad;
    }
    public void setPrioridad(int prioridad){
    	this.prioridad=prioridad;
    }
}

package canales;

/**
 * Clase Cola Enlazada.
 * 
 * @author Javier Garcia Garcia
 *
 * @param <T>
 */
public class ColaEnlazada<T> {

    private class Nodo {
        public T info;// dato que guardamos en el Nodo
        public Nodo siguiente;// puntero al frente de la cola
        public int prioridad;

        public Nodo(T info, Nodo sig) {
            this.info = info;
            this.siguiente = sig;
            this.prioridad= prioridad;
        }
    }
		
    private int longitud;
    private Nodo frente; // Apunta a la posicion 0 de la cola
    private Nodo fin;

    public ColaEnlazada() {
        frente = null;
        fin = null;
        longitud = 0;

    }

    /**
     * Metodo getElement
     * 
     * @param elem
     * @return
     */
    public T getElement(T elem){
        Nodo aux = frente;
        while(!aux.info.equals(elem))
            aux = frente.siguiente;
        return aux.info;

    }

    public int longitud() {
        return longitud;
    }

    public boolean esVacia()
    {
        return frente==null;
    }
	
    public T frente() {
        return frente.info;
    }

    public void extraerFrente() {
        if(frente==fin)
        {
            frente=null;
            fin=frente;
        }
        else{
            frente=frente.siguiente;
        }
        longitud--;
    }

    public void insertar(T elem)
    {
        if(esVacia())
        {
            frente=new Nodo(elem,null);
            fin=frente;
        }
        else
        {
            fin.siguiente=new Nodo(elem,null);
            fin=fin.siguiente;
        }
        longitud++;
    }

    public int mirarPrioridad()  {
        return frente.prioridad;
    } 
	
    /**
     * Metodo insertarMIO
     * 
     * @param elemento
     * @param prioridad
     */
    public void insertaMio(T elemento, int prioridad) {
        Nodo p,q;
        boolean encontrado = false;
        p = frente;
        while((p.siguiente!=null)&&(!encontrado)) {
            if (p.prioridad<prioridad) 
                encontrado = true;
            else p = p.siguiente;
        }
        q = p.siguiente;
        p.siguiente = new Nodo(elemento, q);
        p = p.siguiente;
        p.info = elemento;
        p.prioridad = prioridad;
        p.siguiente = q;
	
    }
}
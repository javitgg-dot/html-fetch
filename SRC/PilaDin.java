package canales;

/**
 * Clase PilaDin
 * 
 * @author Javier Garcia Garcia
 *
 * @param <T>
 */
public class PilaDin<T> {

    class Nodo {
        T info;		// Es el elemento que guarda el nodo
        Nodo sig;	// Es un puntero que apunta al siguiente nodo


        Nodo(T info, Nodo sig) {
            this.info = info;
            this.sig = sig;
        }
    }

    private Nodo inicio;	// Puntero que apunta al inicio de la pila
    private int longitud;	// Longitud de la pila


    public PilaDin() {
        inicio = null;
    }
	
	
    /**
     * Metodo PilaDin
     * 
     * @param pila
     */
    public PilaDin(PilaDin<T> pila) {

        Nodo aux = pila.inicio;
        ListaES<T> lista = new ListaES<T>();

        while (aux != null) {
            lista.insertar(1,aux.info);
            aux = aux.sig;
        }
        for (int i=1; i<=lista.longitud(); i++)
            this.insertar(lista.consultar(i));

    }
	
	/**
	 * Metodo longitud.
	 * 
	 * @return longitud
	 */
    public int longitud() {
        return this.longitud;	// Devuelve la longitud de la pila
    }
	
	/**
	 * Metodo esVacia()
	 * 
	 * @return boolean
	 */
    public boolean esVacia() {
        return this.longitud == 0;	// Devuelve true si est� vac�a y false en caso contrario
    }
	
	/**
	 * cima()
	 * 
	 * @return inicio.info
	 * @throws IllegalArgumentException
	 */
    public T cima()throws IllegalArgumentException {
        if (esVacia())
            throw new IllegalArgumentException();	// Lanza una excepci�n si la pila est� vac�a
        else
            return this.inicio.info;	// Devuelve el elemento que est� en la cima de la pila
    }
	
	
    /**
     * Metodo insertar.
     * 
     * @param elem
     */
    public void insertar(T elem) {
        this.inicio= new Nodo(elem,inicio);	// Inserta el elemento en la cima
        this.longitud++;	// Incrementa la longitud de la pila
    }
	
	/**
	 * Metodo extraer.
	 * 
	 * @throws IllegalArgumentException
	 */
    public void extraer() throws IllegalArgumentException{
        if (esVacia())
            throw new IllegalArgumentException();	// Lanza una excepci�n si la pila est� vac�a
        else {
            this.inicio=this.inicio.sig;	// El segundo elemento pasa a ser la cima
            this.longitud--;	// Decrementa la longitud de la pila
        }
    }

}

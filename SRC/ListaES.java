/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package canales;

/**
 * Clase ListaES
 * 
 * @author Javier Garcia Garcia
 *
 * @param <T>
 */
public class ListaES<T> {

    private T[] contenedor;
    private int longitud;

    public ListaES () {
        contenedor = (T[]) new Object[4];
        longitud = 0;
    }

    /**
     * ListaES
     * 
     * @param lista
     */
    public ListaES(ListaES<T> lista) {
        contenedor = (T[]) new Object [lista.contenedor.length];
        System.arraycopy(lista.contenedor, 0, contenedor, 0, lista.longitud);
        longitud = lista.longitud;
    }

    public int  longitud () {
        return longitud;
    }

    public boolean esVacia() {
        return longitud == 0;
    }

    public T consultar (int pos) {
        return contenedor [pos - 1];
    }

    /**
     * Metodo buscar
     * 
     * @param elem
     * @return
     */
    public int buscar(T elem) {
        int i = 0;
        while (i < longitud && !contenedor[i].equals(elem))
                i++;
        if (i == longitud)
                return 0;
        else
                return i + 1;
    }

    /**
     * Metodo Insertar.
     * 
     * @param pos
     * @param elem
     */
    public void insertar (int pos, T elem) {
        if (longitud == contenedor.length) { // redimensiona el array
            //System.out.println("Redimensionando array");
            T[] contAux = (T[]) new Object [contenedor.length * 2];
            System.arraycopy(contenedor, 0, contAux, 0, contenedor.length);
            contenedor = contAux;
        }
        int posArray = pos - 1;
        for (int i = longitud; i > posArray; i--)
            contenedor [i] = contenedor [i - 1];
        contenedor [posArray] = elem;
        longitud++;
    }

    /**
     * Metodo borrar.
     * 
     * @param pos
     */
    public void borrar (int pos) {
        for (int i = pos - 1; i < longitud-1; i++)
            contenedor [i] = contenedor[i + 1];
        longitud--;
    }

    /**
     * Metodo modificar.
     * 
     * @param pos
     * @param elem
     */
    public void modificar (int pos, T elem) {
        contenedor [pos - 1] = elem;
    }
    
} //clase
		
		

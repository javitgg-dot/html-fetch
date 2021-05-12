package canales;

/**
 * Clase ListaEnlazada
 * 
 * @author Javier Garcia Garcia
 *
 * @param <T>
 */
public class ListaEnlazada<T> {

    class Nodo {
        T info;
        Nodo sig;
        Nodo ant;
        private Nodo inicio;

        Nodo(T info, Nodo sig) {
            this.info = info;
            this.sig = sig;
            this.ant = ant;
        }
    }

    Nodo inicio;
    Nodo fin;

    public ListaEnlazada() {
        inicio = null;
        fin= null;
    }

    /**
     * Metodo Lista Enlazada
     * 
     * @param lista
     */
    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo aux = lista.inicio;
        int pos = 1;
        while (aux != null) {
            insertarForward(pos, aux.info);
            aux = aux.sig;

            pos++;
        }
    }

    /**
     * Metodo longitud Forward
     * 
     * @return pos
     */
    public int longitudForward() {
        int pos = 0;
        Nodo aux = inicio;
        while (aux != null) {
            aux = aux.sig;
            pos++;
        }
        return pos;
    }
	
    /**
     * Metodo longitud Backward.
     * 
     * @return pos
     */
    public int longitudBackward() {
        int pos = 0;
        Nodo aux = fin;
        while (aux != null) {
            aux = aux.ant;
            pos--;
        }
        return pos;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    /**
     * Metodo consultar Forward.
     * 
     * @param pos
     * @return aux.info
     */
    public T consultarForward(int pos) {
        Nodo aux = inicio;
        for (int i = 1; i < pos; i++)
            aux = aux.sig;
        return aux.info;
    }

    /**
     * Metodo consultar Backward
     * 
     * @param pos
     * @return aux.info
     */
    public T consultarBackward(int pos) {
        Nodo aux = fin;
        for (int i = pos; i > 1; i--)
            aux = aux.ant;
        return aux.info;
    }

    /**
     * Metodo buscar Forward.
     * 
     * @param elem
     * @return pos
     */
    public int buscarForward(T elem) {
        Nodo aux = inicio;
        int pos = 1;
        while (aux != null && !aux.info.equals(elem)) {
            aux = aux.sig;
            pos++;
        }
        if (aux == null)
            return 0;
        else
            return pos;
    }

    /**
     * Metodo buscar Backward.
     * 
     * @param elem
     * @return pos
     */
    public int buscarBackward(T elem) {
        Nodo aux = fin;
        int pos = 1;
        while (aux != null && !aux.info.equals(elem)) {
            aux = aux.ant;
            pos--;
        }
        if (aux == null)
            return 0;
        else
            return pos;
    }
	
    /**
     * Metodo insertar Forward
     * 
     * @param pos
     * @param elem
     */
    public void insertarForward(int pos, T elem) {
        if (pos == 1) {
            inicio = new Nodo(elem, inicio);
        }
        else {
            Nodo aux = inicio;
            for (int i = 1; i < pos - 1; i++)
                    aux = aux.sig;
            aux.sig = new Nodo(elem, aux.sig);
        }
    }

    /**
     * Metodo insertar Backward
     * 
     * @param pos
     * @param elem
     */
    public void insertarBackward(int pos, T elem) {
        if (pos == 1) {
            fin = new Nodo(elem, inicio);
        }
        else {
            Nodo aux = fin;
            for (int i = pos-1; i > 1; i--)
                    aux = aux.ant;
            aux.ant = new Nodo(elem, aux.ant);
        }
    }

    /**
     * Metodo insertar
     * 
     * @param elem
     */
    public void insertar(T elem) {
        if (this.esVacia()) {
            inicio = new Nodo(elem, inicio);
        } else {
            Nodo aux = inicio;
            while (aux.sig!=null)
                aux = aux.sig;
            aux.sig = new Nodo(elem, null);
        }
    }

    /**
     * Metodo borrar Forward
     * 
     * @param pos
     */
    public void borrarForward(int pos) {
        if (pos == 1) {
            inicio = inicio.sig;
        } else {
            Nodo aux = inicio;
            for (int i = 1; i < pos - 1; i++)
                aux = aux.sig;
            aux.sig = aux.sig.sig;
        }
    }

    /**
     * Metodo Borrar Backward
     * @param pos
     */
    public void borrarBackward(int pos) {
        if (pos == 1) {
            fin = fin.ant;
        } else {
            Nodo aux = fin;
            for (int i = pos-1; i > 1 ; i--)
                aux = aux.ant;
            aux.ant = aux.ant.ant;
        }
    }

    /**
     * Metodo Modifcar Forward.
     * 
     * @param pos
     * @param elem
     */
    public void modificarForward(int pos, T elem) {
        Nodo aux = inicio;
        for (int i = 1; i < pos; i++)
                aux = aux.sig;
        aux.info = elem;
    }

    /**
     * Metodo modificar Backward.
     * 
     * @param pos
     * @param elem
     */
    public void modificarBackward(int pos, T elem) {
        Nodo aux = fin;
        for (int i = pos; i > 1; i--)
            aux = aux.ant;
        aux.info = elem;
    }

    /**
     * Metodo comparar
     * 
     * @param lista
     * @return
     */
    public boolean comparar(ListaEnlazada<T> lista) {
        if (this.longitudForward()!=lista.longitudForward())
            return false;
        else{
            Nodo aux = this.inicio;
            for (int i=1;i<=this.longitudForward();i++){
                if (lista.buscarForward(aux.info)==0)
                    return false;
                aux=aux.sig;
            }
        }
        return true;
    }


    public void mostrarForward(){
        Nodo aux = inicio;
        while (aux!=null){
            System.out.print(aux.info + ",");
            aux=aux.sig;
        }
    }

    public void mostrarBackward(){
        Nodo aux = fin;
        while (aux!=null){
            System.out.print(aux.info + ",");
            aux=aux.ant;
        }
    }

    /**
     * Metodo contains
     * 
     * @param elem
     * @return boolean
     */
    public boolean contains(T elem)
    {
        for(int i=0;i<longitudForward();i++)
        {
            if(consultarForward(i).equals(elem))
                return true;
        }
        return false;
    }
}
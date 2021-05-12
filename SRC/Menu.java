package canales;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Menu
 * 
 * @author Javier Garcia Garcia
 *
 */
public class Menu {

    public static final int resultadosPorPagina = 15;

    public int MenuPrincial()
    {
    	int opcion=0;
    	try{	
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		System.out.println("");
    		System.out.println("");
    		System.out.println("1. Ahora en Antena");
    		System.out.println("2. Seleccionar Canal");
    		System.out.println("3. Favoritos");
    		System.out.println("4. Comparar Canales");
    		System.out.println("5. Salir");
    		System.out.print("Introduce Opcion> ");
    		isr = new InputStreamReader(System.in);
    		br = new BufferedReader(isr);
    		opcion = Integer.parseInt(br.readLine());
    	}catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}catch(NumberFormatException er){
    		System.out.println("");
    		System.out.println("Opcion Incorrecta");
    		System.out.println("");
    	}
        return opcion;
    }

    /**
     * Metodo menuAhoraEnAntena
     * 
     * @return opcion
     */
    public int menuAhoraEnAntena()
    {
    	int opcion=0;
    	try{	
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		System.out.println("");
    		System.out.println("");
    		System.out.println("1. En todos los canales");
			System.out.println("2. En los canales favoritos");
			System.out.println("3. Salir");
			System.out.println("Introduce Opcion>");
    		isr = new InputStreamReader(System.in);
    		br = new BufferedReader(isr);
    		opcion = Integer.parseInt(br.readLine());
    	}catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}catch(NumberFormatException er){
    		System.out.println("");
    		System.out.println("Opcion Incorrecta");
    		System.out.println("");
    	}
        return opcion;
    }
    
    /**
     * Metodo Menu Programas Ahora en Antena.
     * 
     * @return opcion
     */
    public int menuProgramasAhoraEnAntena()
    {
    	int opcion=0;
    	try{	
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		System.out.println("");
    		System.out.println("");
    		System.out.println("1. Pagina Siguiente");
			System.out.println("2. Pagina Anterior");
			System.out.println("3. Ver Programa");
			System.out.println("4. Aniadir a favoritos");
			System.out.println("5. Salir");
			System.out.println("Introduzca Opcion: ");
    		isr = new InputStreamReader(System.in);
    		br = new BufferedReader(isr);
    		opcion = Integer.parseInt(br.readLine());
    	}catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}catch(NumberFormatException er){
    		System.out.println("");
    		System.out.println("Opcion Incorrecta");
    		System.out.println("");
    	}
        return opcion;
    }
    
    /**
     * Metodo Menu Programas Ahora en Antena Favoritos.
     * 
     * @return opcion
     */
    public int menuProgramasAhoraEnAntenaFav(){
    	int opcion=0;
    	try{	
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		System.out.println("");
    		System.out.println("");
    		System.out.println("1. Pagina Siguiente");
			System.out.println("2. Pagina Anterior");
			System.out.println("3. Ver Programa");
			System.out.println("4. Quitar favoritos");
			System.out.println("5. Salir");
			System.out.println("Introduzca Opcion: ");
    		isr = new InputStreamReader(System.in);
    		br = new BufferedReader(isr);
    		opcion = Integer.parseInt(br.readLine());
    	}catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}catch(NumberFormatException er){
    		System.out.println("");
    		System.out.println("Opcion Incorrecta");
    		System.out.println("");
    	}
        return opcion;
    }
    
    /**
     * Metodo menu Seleccion Canal
     * 
     * @return opcion
     */
    public int menuSelecionCanal()
    {
    	int opcion=0;
    	try{	
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		System.out.println("");
    		System.out.println("");
    		System.out.println("1. Ver Programa");
    		System.out.println("2. Aniadir programa favoritos");
    		System.out.println("3. Aniadir canal a favoritos");
    		System.out.println("4. Volver Atras");
    		System.out.println("Introduce Opcion>");
    		isr = new InputStreamReader(System.in);
    		br = new BufferedReader(isr);
    		opcion = Integer.parseInt(br.readLine());
    	}catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}catch(NumberFormatException er){
    		System.out.println("");
    		System.out.println("Opcion Incorrecta");
    		System.out.println("");
    	}
        return opcion;
    }
    
    /**
     * Metodo Menu Favoritos.
     * 
     * @return opcion
     */
    public int menuFavoritos()
    {
    	int opcion=0;
    	try{	
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		System.out.println("");
    		System.out.println("");
    		System.out.println("1. Canales favoritos");
			System.out.println("2. Programas favoritos");
			System.out.println("3. Salir");
			System.out.println("Introduce Opcion>");
    		isr = new InputStreamReader(System.in);
    		br = new BufferedReader(isr);
    		opcion = Integer.parseInt(br.readLine());
    	}catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}catch(NumberFormatException er){
    		System.out.println("");
    		System.out.println("Opcion Incorrecta");
    		System.out.println("");
    	}
        return opcion;
    }
    
    /**
     * Metodo Menu Favoritos Canales.
     * 
     * @return opcion
     */
    public int menuFavoritosCanales()
    {
    	int opcion=0;
    	try{	
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		System.out.println("");
    		System.out.println("");
    		System.out.println("1. Consultar Canal");
			System.out.println("2. Modificar Prioridad");
			System.out.println("3. Eliminar Favorito");
			System.out.println("4. Pagina Siguiente");
			System.out.println("5. Pagina Anterior");
			System.out.println("6. Salir");
			System.out.println("Introduce Opcion>");
    		isr = new InputStreamReader(System.in);
    		br = new BufferedReader(isr);
    		opcion = Integer.parseInt(br.readLine());
    	}catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}catch(NumberFormatException er){
    		System.out.println("");
    		System.out.println("Opcion Incorrecta");
    		System.out.println("");
    	}
        return opcion;
    }
    
    /**
     * Metodo Menu Favoritos Programas.
     * 
     * @return opcion
     */
    public int menuFavoritosProgramas()
    {
    	int opcion=0;
    	try{	
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		System.out.println("");
    		System.out.println("");
    		System.out.println("1. Consultar Programa");
			System.out.println("2. Modificar Prioridad");
			System.out.println("3. Eliminar Programa de Favoritos");
			System.out.println("4. Pagina Siguiente");
			System.out.println("5. Pagina Anterior");
			System.out.println("6. Salir");
			System.out.println("Introduce Opcion>");
    		isr = new InputStreamReader(System.in);
    		br = new BufferedReader(isr);
    		opcion = Integer.parseInt(br.readLine());
    	}catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}catch(NumberFormatException er){
    		System.out.println("");
    		System.out.println("Opcion Incorrecta");
    		System.out.println("");
    	}
        return opcion;
    }
    
    
    /**
     * Metodo menu Comparar
     * 
     * @return opcion
     */
    public int menuComparar()
    {
    	int opcion=0;
    	try{	
    		InputStreamReader isr = new InputStreamReader(System.in);
    		BufferedReader br = new BufferedReader(isr);
    		System.out.println("");
    		System.out.println("");
    		System.out.println("1. Elegir canales a comparar");
			System.out.println("2. Salir");
			System.out.println("Introduce Opcion>");
    		isr = new InputStreamReader(System.in);
    		br = new BufferedReader(isr);
    		opcion = Integer.parseInt(br.readLine());
    	}catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}catch(NumberFormatException er){
    		System.out.println("");
    		System.out.println("Opcion Incorrecta");
    		System.out.println("");
    	}
        return opcion;
    }
    
    
    /**
     * Metodo Cola Enlazada
     * 
     * @param canales
     * @return programas
     */
    public ColaEnlazada<Programa> ahoraEnAntena(ListaES<Canal> canales)
    {
        ColaEnlazada<Programa> programas = new ColaEnlazada<Programa>();
        for(int i=1;i<=canales.longitud();i++)
        {
            for(int j=1;j<=canales.consultar(i).getProgramas().longitudForward();j++)
            {
                Programa p = canales.consultar(i).getProgramas().consultarForward(j);
                //System.out.println("Programa: " + p.getTitulo() + " horaIni:" + p.getHora().getTime() +
                  //      " HoraFin:" + p.horaFin());
                if(Utils.todaviaEnEmision(p))
                {
                    programas.insertar(p);
                    System.out.println("Nº Canal: " + i +" " + p.getCadena().getNombre()+ " ; Nº programa: " + j + " " + p.getTitulo());
                }
            }
            
        }

        return programas;
    }

    /**
     * Metodo paginar.
     * 
     * @param p
     * @param pagina
     * @return pagina
     */
    public int paginar(ColaEnlazada <Programa> p,int pagina) {

        boolean inicio = true;
        boolean fin = false;

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        PilaDin <Programa> pilaMostrados = new PilaDin <Programa>();//Pila
        PilaDin <Programa> pilaPorMostrar = new PilaDin <Programa>();//Fila

        // Copiar a la pila todos los programas
        for(int i=0;i<p.longitud();i++)
        {
            pilaPorMostrar.insertar(p.frente());
            p.extraerFrente();
        }

        System.out.println("Tamano pilapormostrar: " + pilaPorMostrar.longitud());

        String salida = "q";
        String opcion = "";

        System.out.println("Cadena\t| Titulo\t| Hora Inicio\t| Duracion\t| Descripcion");

        while(opcion.compareTo(salida)!=0)
        {
            Programa actual = null;

            if(opcion.compareTo("a")==0)
            {
                for(int i=0;i<resultadosPorPagina;i++)
                {
                    if(!pilaMostrados.esVacia())
                    {
                        actual = pilaMostrados.cima();
                        pilaMostrados.extraer();
                        pilaPorMostrar.insertar(actual);

                        System.out.print(actual.getCadena().getNombre() + "\t|");
                        System.out.print(actual.getTitulo() + "\t|");
                        System.out.print(actual.getHoraInicio() + "\t|");
                        System.out.print(actual.getDuracion() + "\t|");
                        System.out.print(actual.getDescripcion() + "\t|");
                        System.out.println();

                    }
                }
            }
            else if(inicio == true || opcion.compareTo("d") == 0)
            {
                for(int i=0;i<resultadosPorPagina;i++)
                {
                    if(!pilaPorMostrar.esVacia())
                    {
                        actual = pilaPorMostrar.cima();
                        pilaPorMostrar.extraer();
                        pilaMostrados.insertar(actual);

                        System.out.print(actual.getCadena().getNombre() + "\t|");
                        System.out.print(actual.getTitulo() + "\t|");
                        System.out.print(actual.getHoraInicio() + "\t|");
                        System.out.print(actual.getDuracion() + "\t|");
                        System.out.print(actual.getDescripcion() + "\t|");
                        System.out.println();

                    }
                }
            }

            if(pilaMostrados.esVacia())
                inicio = true;
            if(pilaPorMostrar.esVacia())
                fin = true;

            // Coger opcion del usuario
            try {
                opcion = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pagina;
    }
}

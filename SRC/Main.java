package canales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase Main.
 * 
 * @author Javier Garcia Garcia
 *
 */
public class Main {

    private static final String fichero = "usuarios.txt";
   

    static int pagina=1;
    static int registrosTotales=0;
    
    /**
     * Metodo escribirFAV
     * 
     * @param canalesFav
     * @param programasFav
     * @param user
     */
    public static void escribirFav(ListaES<Canal> canalesFav,ListaES<Programa> programasFav,String user){
    	String ficheroUsuario = user + ".txt";
    	try{
    		
    		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ficheroUsuario)));
    		bw.write("");
    		bw.close();
    		bw = new BufferedWriter(new FileWriter(new File(ficheroUsuario)));
    		bw.write("[Canales]");
            bw.newLine();
            for(int i=1;i<=canalesFav.longitud();i++){
            	bw.write(canalesFav.consultar(i).getNombre()+"·"+canalesFav.consultar(i).getDireccion()+"·"+canalesFav.consultar(i).getFileName()+"·"+canalesFav.consultar(i).getPrioridad());
            	bw.newLine();
            }
            bw.write("[Programas]");
            bw.newLine();
            for(int i=1;i<=programasFav.longitud();i++){
            	bw.write(programasFav.consultar(i).getCadena().getNombre()+"·"+programasFav.consultar(i).getCadena().getDireccion()+"·"+programasFav.consultar(i).getTitulo()+programasFav.consultar(i).getPrioridad());
            	bw.newLine();
            }
            bw.close();
    	}catch(IOException e){
    		System.out.println("Error Escribiendo Fichero");
    	}
    }
  
    /**
     * Metodo main
     * 
     * @param args
     */
    public static void main(String[] args)
    {
       String usuario="";
        
        try{
            /* Lo primero de todo es registrarse o loguearse(si existe) */
            usuario= Acceso.init(fichero);
            /* Si todo ha ido bien */
            if(usuario!=""){
            	System.out.println("Inicializacion correcta. Hora actual: " + Utils.horaActualMillis());
            	HTMLFetch html = new HTMLFetch();
            	html.fetchChannels();
            	html.fetchPrograms();
            	
            	Menu m =new Menu();
            	ListaES<Canal> canales = new ListaES<Canal>();
            	
            	ListaES<Canal> canalesFav = new ListaES<Canal>();
            	ListaES<Programa> programasFav= new ListaES<Programa>();
            	
            	for(int i=0;i<html.getCanales().size();i++){
            		canales.insertar(i+1, html.getCanales().get(i));
            	}
            		// Parsea mal los canales de la web y no los mete todos
            	
            	//Leer favoritos
            	
            	
            	System.out.println("Leyendo Favoritos");
            	BufferedReader br = new BufferedReader(new FileReader(new File(usuario +".txt")));
                boolean insercionCanales =false;
                boolean insercionProgramas =false;
            	String cadena="";
                while((cadena=br.readLine())!=null)
                {
                	if (cadena.equals("[Canales]")){
                		insercionCanales=true;
                		insercionProgramas=false;
                	}
                	if (cadena.equals("[Programas]")){
                		insercionCanales=false;
                		insercionProgramas=true;
                	}
                	if(insercionCanales && !cadena.equals("[Canales]")){
                		String[] datos = cadena.split("·");
                		Canal c= new Canal(datos[0],datos[1],datos[2],Integer.parseInt(datos[3]));
                		
                		// buscamos la lista de programas
                		for(int j=1;j<=canales.longitud();j++){
                			if(canales.consultar(j).getNombre().equals(datos[0])){
                				c= canales.consultar(j);
                				canalesFav.insertar(canalesFav.longitud()+1, c);
                			}
                		}
                	}
                	
                	if(insercionProgramas&& !cadena.equals("[Programas]")){
                		int h, m1, s, h1,m2,s1;
                		String[] datos = cadena.split("·");
                		Canal c= new Canal(datos[0],datos[1]);
                		boolean encontrado= false;
                		Programa p=new Programa(c,datos[2],Integer.parseInt(datos[3]));
                		for(int j=1;j<=canales.longitud();j++){
                			for(int z=1;z<=canales.consultar(j).getProgramas().longitudForward();z++){
                				if(datos[2].equals(canales.consultar(j).getProgramas().consultarForward(z))){
                					p=canales.consultar(j).getProgramas().consultarForward(z);
                					encontrado=true;
                				}
                			}
                		}
                		if(!encontrado){
                			p.setDescripcion(" ");
                		}
                		programasFav.insertar(programasFav.longitud()+1, p);
                	}
                
                }
	            int opcion=0;
	            int opcion2=0;
	            int opcion3=0;
	            String aux;
	            InputStreamReader isr = new InputStreamReader(System.in);
	    		br = new BufferedReader(isr);
	            do{
	            	opcion=m.MenuPrincial();
	                switch (opcion){
	                	//Ahora en antena
	                	case 1:
	                		do{
	                			opcion2=m.menuAhoraEnAntena();
	                			switch (opcion2){
	                				case 1:
	                					int programaE=0;
	                					int canalE=0;
	                					do{
	                						ColaEnlazada<Programa> programas = m.ahoraEnAntena(canales);
	                						//pagina = m.paginar(programas,pagina);
	                                		opcion3 = m.menuProgramasAhoraEnAntena();
	                                		switch (opcion3){
	                                		case 1:
	                                			if(pagina!=1){
	                                				pagina = m.paginar(programas,pagina -1);
	                                			}else{
	                                				System.out.println("Estas en la primera pagina");	
	                                			}
	                                		break;
	                                		case 2:
	                                			if(pagina!=1){
	                                				pagina = m.paginar(programas,pagina +1);
	                                			}else{
	                                				System.out.println("Estas en la ultima pagina");	
	                                			}
	                                		break;
	                                		case 3:
	                                			try{
	                                				System.out.println("Introduzca Nº Canal: ");
	                                				isr = new InputStreamReader(System.in);
	                                				br = new BufferedReader(isr);
	                                				canalE = Integer.parseInt(br.readLine());
	                                				System.out.println("Introduzca Nº Programa: ");
	                                				isr = new InputStreamReader(System.in);
	                                				br = new BufferedReader(isr);
	                                				programaE = Integer.parseInt(br.readLine());
	                                				Programa p = canales.consultar(canalE).getProgramas().consultarForward(programaE);
	                                				System.out.println("CANAL: " +p.getCadena().getNombre()+ " - HORA INICIO:  "  + p.getHoraInicio() + " - TITULO:  "  + p.getTitulo()+ " - DURACION: " + p.getDuracion()+ " - DESCRIPCION: " + p.getDescripcion());

	                                				System.out.println("Pulse cualquier tecla para volver atras");
	                                				isr = new InputStreamReader(System.in);
	                                				br = new BufferedReader(isr);
	                                				aux = br.readLine();
	                                			}catch(java.lang.NullPointerException e){
	            	                				System.out.println("");
	            	                				System.out.println("No existe el programa");
	            	                				System.out.println("");
	            	                			}
	                                        			
	                                		break;
	                                		case 4:
	                                			try{
	                                				System.out.println("Introduzca Nº Canal: ");
	                                				isr = new InputStreamReader(System.in);
	                                				br = new BufferedReader(isr);
	                                				canalE = Integer.parseInt(br.readLine());
	                                				System.out.println("Introduzca Nº Programa: ");
	                                    			isr = new InputStreamReader(System.in);
	                                    			br = new BufferedReader(isr);
	                                    			programaE = Integer.parseInt(br.readLine());
	                                    			System.out.println("Introduzca Prioridad Programa: ");
	                                    			isr = new InputStreamReader(System.in);
	                                    			br = new BufferedReader(isr);
	                                    			int prioridad=Integer.parseInt(br.readLine());
	                                    			Programa p = canales.consultar(canalE).getProgramas().consultarForward(programaE);
	                                    			p.setPrioridad(prioridad);
	                                    			programasFav.insertar(programasFav.longitud()+1, p);
	                                    			escribirFav(canalesFav,programasFav,usuario);
	                                    			System.out.println("Programa Añadido a Favoritos");
	                                			}catch(java.lang.NullPointerException e){
	            	                				System.out.println("");
	            	                				System.out.println("No existe el programa");
	            	                				System.out.println("");
	            	                			}
	                                    			break;
	                                		case 5:
	                                			System.out.println("Retrocediendo...");
	                                		break;
	                                		default:
	                                			System.out.println("Opción incorrecta");
	                                		break;
	                                	}
	                                		
	                					}while (opcion3!=5);
	                				break;
	                				case 2: 
	                					ColaEnlazada<Programa> programas = m.ahoraEnAntena(canalesFav);
	                					do{
	                						opcion3 = m.menuProgramasAhoraEnAntenaFav();
	                						switch(opcion3){
	                							case 1:
	                								break;
	                							case 2:
	                								break;
	                							case 3:
	                								try{
	                									System.out.println("Introduzca Nº Canal: ");
	                									isr = new InputStreamReader(System.in);
	                									br = new BufferedReader(isr);
	                									canalE = Integer.parseInt(br.readLine());
	                									System.out.println("Introduzca Nº Programa: ");
	                									isr = new InputStreamReader(System.in);
	                									br = new BufferedReader(isr);
	                									programaE = Integer.parseInt(br.readLine());
	                									Programa p = canalesFav.consultar(canalE).getProgramas().consultarForward(programaE);
	                									System.out.println("CANAL: " +p.getCadena().getNombre()+ " - HORA INICIO:  "  + p.getHoraInicio() + " - TITULO:  "  + p.getTitulo()+ " - DURACION: " + p.getDuracion()+ " - DESCRIPCION: " + p.getDescripcion());

	                									System.out.println("Pulse cualquier tecla para volver atras");
	                									isr = new InputStreamReader(System.in);
	                									br = new BufferedReader(isr);
	                									aux = br.readLine();
	                								}catch(java.lang.NullPointerException e){
	                	                				System.out.println("");
	                	                				System.out.println("No existe el programa");
	                	                				System.out.println("");
	                	                			}
	                								break;
	                							case 4:
	                								try {
	                									System.out.println("Introduzca Nº Canal: ");
	                									isr = new InputStreamReader(System.in);
	                									br = new BufferedReader(isr);
	                									programaE = Integer.parseInt(br.readLine());
	                									canalesFav.borrar(programaE);
	                									escribirFav(canalesFav,programasFav,usuario);
	                									System.out.println("Canal Eliminado de Favoritos");
	                								}catch(java.lang.NullPointerException e){
	                	                				System.out.println("");
	                	                				System.out.println("No existe el programa");
	                	                				System.out.println("");
	                	                			}
		                                    		break;
	                							case 5: 
	                								System.out.println("Retrocediendo...");
	                							break;
	                							default:
	                								System.out.println("Opción incorrecta");
	                								break;
	                						}
	                					}while (opcion3!=5);
	                				break;
	                				case 3:
	                					System.out.println("Retrocediendo...");
	                				break;
	                				default:
	                					System.out.println("Opcion Incorrecta");
	                				break;
	                			}
	                				
	                		}while(opcion2!=3);
	                	break;
	                	//Seleccionar Canal
	                case 2:  	
	                	
	                	int canal=1;
	                	do{
	                		for (int i=1; i<=canales.longitud();i++){
	                			System.out.println(i +". " + canales.consultar(i).getNombre());
	                		}
	                		System.out.println("Introduce Nº Canal>");
	                		isr = new InputStreamReader(System.in);
	                		br = new BufferedReader(isr);
	                		canal = Integer.parseInt(br.readLine());
	                		System.out.println("--------------------------------------");
	                		System.out.println(canales.consultar(canal).getNombre());
	                		System.out.println("--------------------------------------");
	                		for(int j=1;j<=canales.consultar(canal).getProgramas().longitudForward();j++)
	                		{
	                			Programa p = canales.consultar(canal).getProgramas().consultarForward(j);
	                			System.out.println(j + ". "+ " - HORA INICIO:  "  + p.getHoraInicio() + " - TITULO:  "  + p.getTitulo()+ " - DURACION: " + p.getDuracion());
	                		}
	                		opcion2 = m.menuSelecionCanal();
	                		switch (opcion2){
	                		case 1:
	                			try{
	                				System.out.println("Introduzca Nº Canal: ");
	                				isr = new InputStreamReader(System.in);
	                				br = new BufferedReader(isr);
                        			int canalE = Integer.parseInt(br.readLine());
                        			System.out.println("Introduzca Nº Programa: ");
                        			isr = new InputStreamReader(System.in);
                        			br = new BufferedReader(isr);
                        			int programaE = Integer.parseInt(br.readLine());
                        			Programa p = canales.consultar(canalE).getProgramas().consultarForward(programaE);
                        			System.out.println("CANAL: " +p.getCadena().getNombre()+ " - HORA INICIO:  "  + p.getHoraInicio() + " - TITULO:  "  + p.getTitulo()+ " - DURACION: " + p.getDuracion()+ " - DESCRIPCION: " + p.getDescripcion());
	                			}catch(java.lang.NullPointerException e){
	                				System.out.println("");
	                				System.out.println("No existe el programa");
	                				System.out.println("");
	                			}
                            	break;
	                		case 2: 
	                			try{
	                				System.out.println("Introduzca Nº Canal: ");
	                				isr = new InputStreamReader(System.in);
	                				br = new BufferedReader(isr);
	                				int canalE = Integer.parseInt(br.readLine());
	                				System.out.println("Introduzca Nº Programa: ");
	                				isr = new InputStreamReader(System.in);
	                				br = new BufferedReader(isr);
	                				int programaE = Integer.parseInt(br.readLine());
	                				System.out.println("Introduzca Prioridad Programa: ");
	                				isr = new InputStreamReader(System.in);
	                				br = new BufferedReader(isr);
	                				int prioridad = Integer.parseInt(br.readLine());
	                				Programa p = canales.consultar(canalE).getProgramas().consultarForward(programaE);
	                				p.setPrioridad(prioridad);
	                				programasFav.insertar(programasFav.longitud()+1, p);
	                				escribirFav(canalesFav,programasFav,usuario);
	                				System.out.println("Programa Añadido a Favoritos");
	                			}catch(java.lang.NullPointerException e){
	                				System.out.println("");
	                				System.out.println("No existe el programa");
	                				System.out.println("");
	                			}

	                			break;
	                		case 3:
	                			try{
	                				System.out.println("Introduce Nº Canal para Aniadir a Favoritos>");
	                				isr = new InputStreamReader(System.in);
	                				br = new BufferedReader(isr);
	                				canal = Integer.parseInt(br.readLine());
	                				System.out.println("Introduce Prioridad Canal>");
	                				isr = new InputStreamReader(System.in);
	                				br = new BufferedReader(isr);
	                				int prioridad = Integer.parseInt(br.readLine());
	                				Canal c = canales.consultar(canal);
	                				c.setPrioridad(prioridad);
	                				canalesFav.insertar(canalesFav.longitud()+1, c);
	                				escribirFav(canalesFav,programasFav,usuario);
	                				System.out.println("Canal Aniadido a Favoritos");
	                			}catch(java.lang.NullPointerException e){
	                				System.out.println("");
	                				System.out.println("No existe el canal");
	                				System.out.println("");
	                			}
	                			break;
	                		case 4:
	                			System.out.println("Retrocediendo...");
	                			break;
	                		default:
	                			System.out.println("Opcion Incorrecta");
	                			break;
	                		}
	                	}while(opcion2!=4);
	                	break;
	                	
	                	
	                case 3:

	                	do{
	                    	opcion2=m.menuFavoritos();
	                    	//Favoritos
	            			switch (opcion2){
	            				case 1:
	            					do{
	            						for (int i=1; i<=canalesFav.longitud();i++){
	            							System.out.println(i +". " + canalesFav.consultar(i).getNombre());
	            						}
	            						opcion3=m.menuFavoritosCanales();
	            						switch(opcion3){
		            						case 1:
		            							try{
		            								System.out.println("Introduce Nº Canal para Consultar>");
		            								isr = new InputStreamReader(System.in);
		            								br = new BufferedReader(isr);
		            								canal = Integer.parseInt(br.readLine());
		            								Canal c = canales.consultar(canal);
		            								for (int i=0;i<c.getProgramas().longitudForward();i++){
		            									Programa p = canales.consultar(canal).getProgramas().consultarForward(i);
		            									System.out.println("CANAL: " +p.getCadena().getNombre()+ " - HORA INICIO:  "  + p.getHoraInicio() + " - TITULO:  "  + p.getTitulo()+ " - DURACION: " + p.getDuracion()+ " - DESCRIPCION: " + p.getDescripcion()+ " - PRIORIDAD CANAL: " + p.getPrioridad());
		            								}
		            							}catch(java.lang.NullPointerException e){
		        	                				System.out.println("");
		        	                				System.out.println("No existe el canal");
		        	                				System.out.println("");
		        	                			}
	        								break;
		            						case 2:
		            							try{
		            								System.out.println("Introduce Nº Canal para Modificar Prioridad>");
		            								isr = new InputStreamReader(System.in);
		            								br = new BufferedReader(isr);
		            								canal = Integer.parseInt(br.readLine());
		            								System.out.println("Introduce prioridad>");
		            								isr = new InputStreamReader(System.in);
		            								br = new BufferedReader(isr);
		            								int prioridad = Integer.parseInt(br.readLine());
		            								canalesFav.consultar(canal).setPrioridad(prioridad);
		            								escribirFav(canalesFav,programasFav,usuario);
		            								System.out.println("Prioridad Cambiada");
		            							}catch(java.lang.NullPointerException e){
		        	                				System.out.println("");
		        	                				System.out.println("No existe el canal");
		        	                				System.out.println("");
		        	                			}
	        								break;
		            						case 3: 
		            							try{
		            								System.out.println("Introduce Nº Canal para Eliminar de Favoritos>");
		            								isr = new InputStreamReader(System.in);
		            								br = new BufferedReader(isr);
		            								canal = Integer.parseInt(br.readLine());
		            								canalesFav.borrar(canal);
		            								escribirFav(canalesFav,programasFav,usuario);
		            								System.out.println("Canal eliminado de favoritos");
		            								
		            							}catch(java.lang.NullPointerException e){
		        	                				System.out.println("");
		        	                				System.out.println("No existe el canal");
		        	                				System.out.println("");
		        	                			}
	        								break;
		            						case 4:
	        								break;
		            						case 5:
	        								break;
		            						case 6:
		            							System.out.println("Retrocediendo...");
		            						break;
		            						default:
		            							System.out.println("Opcion Incorrecta");
	        								break;
	            						}
	            					}while (opcion3!=6);
	            				break;
	            				case 2: 
	            					do{
	            						for (int i=1; i<=programasFav.longitud();i++){
	            							System.out.println(i +". CADENA: " + programasFav.consultar(i).getCadena().getNombre() + " - PROGRAMA: " +programasFav.consultar(i).getTitulo()+ " - PRIORIDAD: " + programasFav.consultar(i).getPrioridad());
	            						}
	            						opcion3=m.menuFavoritosProgramas();
	            						switch(opcion3){
	            							case 1:
	            								try{
	            									System.out.println("Introduce Nº Programa para Consultar>");
	            									isr = new InputStreamReader(System.in);
	            									br = new BufferedReader(isr);
	            									int programa = Integer.parseInt(br.readLine());
	            									Programa p= programasFav.consultar(programa);
	            									System.out.println("CANAL: " +p.getCadena().getNombre()+ " - HORA INICIO:  "  + p.getHoraInicio() + " - TITULO:  "  + p.getTitulo()+ " - DURACION: " + p.getDuracion()+ " - DESCRIPCION: " + p.getDescripcion()+ " - PRIORIDAD PROGRAMA: " + p.getPrioridad());
	            								}catch(java.lang.NullPointerException e){
	            	                				System.out.println("");
	            	                				System.out.println("No existe el programa");
	            	                				System.out.println("");
	            	                			}
	            								break;
	            							case 2:
	            								try{
	            									System.out.println("Introduce Nº Programa para Modificar Prioridad>");
	            									isr = new InputStreamReader(System.in);
	            									br = new BufferedReader(isr);
	            									int programa = Integer.parseInt(br.readLine());
	            									System.out.println("Introduce prioridad>");
	            									isr = new InputStreamReader(System.in);
	            									br = new BufferedReader(isr);
	            									int prioridad = Integer.parseInt(br.readLine());
	            									programasFav.consultar(programa).setPrioridad(prioridad);
	            									escribirFav(canalesFav,programasFav,usuario);
	            									System.out.println("Prioridad Cambiada");
	            								}catch(java.lang.NullPointerException e){
	            	                				System.out.println("");
	            	                				System.out.println("No existe el programa");
	            	                				System.out.println("");
	            	                			}
	            								break;
	            							case 3: 
	            								try{
	            									System.out.println("Introduce Nº Programa para Elimiar de favoritos>");
	            									isr = new InputStreamReader(System.in);
	            									br = new BufferedReader(isr);
	            									int programa = Integer.parseInt(br.readLine());
	            									programasFav.borrar(programa);
	            									escribirFav(canalesFav,programasFav,usuario);
	            									System.out.println("Programa eliminado de Favoritos");
	            								}catch(java.lang.NullPointerException e){
	            	                				System.out.println("");
	            	                				System.out.println("No existe el programa");
	            	                				System.out.println("");
	            	                			}
	            								break;
	            							case 4:
	            								break;
	            							case 5:
	            								break;
	            							case 6:
	            								System.out.println("Retrocediendo...");
	            								break;
	            							default:
	            								System.out.println("Opcion Incorrecta");
	            								break;
	            						}
	            					}while (opcion3!=6);
	            					break;
	            				case 3:
	            					System.out.println("Retrocediendo...");
	            				break;
	            				default:
	            			}
	            				
	            		}while(opcion2!=3);
	                	break;
	                case 4:
	                	do{
	                		opcion2=m.menuComparar();
	                		switch(opcion2){
	                			case 1:
	                			break;
	                			case 2:
	                				System.out.println("Retrocediendo...");
	                			break;
	                			default:
	                				System.out.println("Opcion Incorrecta");
	                				break;
	                		}
	                	}while(opcion!=2);
	                	break;
	                case 5:
	                	System.out.println("Saliendo");
	                	break;
	                default:
	                	System.out.println("Opcion Incorrecta");
	                }
	            }while (opcion!=5);
	           // ColaEnlazada<Programa> programas = m.ahoraEnAntena(canales);
	        } 
        }catch(IOException e){
    		Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
    	}
    }
}

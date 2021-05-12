package canales;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

/**
 * Clase HTMLFetch
 * 
 * @author Javier Garcia Garcia
 *
 */
public class HTMLFetch {

    private static final String url = "http://www.miguiatv.com/todos-los-canales.html";
    private static final String ficheroGeneral = "General.xml";
    ArrayList<Canal> canales;
    ArrayList<Programa> programas;

    public HTMLFetch()
    {
        canales = new ArrayList<Canal>();
    }

    public void fetchChannels()
    {
        try {
            //http://www.java-tips.org/java-se-tips/javax.xml.parsers/how-to-read-xml-file-in-java.html
            Utils.downloadFile(url,ficheroGeneral);
            HtmlCleaner cleaner = new HtmlCleaner();

            System.out.println("[" + Utils.now() + "] Limpiando fichero principal...");

            TagNode html = cleaner.clean(new File(ficheroGeneral));

            // Coger nodos de tipo <a>
            List<TagNode> nodeLst = html.getElementListByAttValue("class", "chtitle", true, true);

            for(int i=0;i<nodeLst.size();i++)
            {
                TagNode nodo = nodeLst.get(i);

                String nombre = nodo.getAttributeByName("class");
                String direccion = nodo.getAttributeByName("href");

                if(nombre!=null && direccion!=null)
                {
                    nombre = nodo.getText().toString().trim();
                    if(nombre.contains("/")||nombre.contains("\\"))
                    {
                        nombre = nombre.replace('/', '-');
                        nombre = nombre.replace('\\', '-');
                    }
                    direccion = direccion.trim();

                    Canal c = new Canal(nombre, direccion);
                    if(c.bajaContenido())
                    {
                        if(!Utils.estaCanal(canales,c))
                            canales.add(c);
                    }
                }
            }
            
            System.out.println(canales.size() + " canales leidos");

        } catch (IOException ex) {
            Logger.getLogger(HTMLFetch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo fetchPrograms
     * 
     * @return boolean
     */
    public boolean fetchPrograms()
    {
        try {
            // Para cada canal, descargar todos sus programas
            for(int i=0;i<canales.size();i++)
            {
                // Limpiar fichero HTML
                HtmlCleaner cleaner = new HtmlCleaner();
                TagNode html = cleaner.clean(new File(canales.get(i).getFileName()));

                // Coger hora de todos los programas de este canal (show_odd y show_even)
                List<TagNode> even = html.getElementListByAttValue("class", "show_even", true, true);
                List<TagNode> odd = html.getElementListByAttValue("class", "show_odd", true, true);

                // Programas se muestras alternos en web, hay qu ordenarlos
                ArrayList<TagNode> nodeLst = new ArrayList<TagNode>();
                int size = (even.size() > odd.size()) ? even.size() : odd.size();
                boolean inicio = true;
                int k=0;
                int l=0;
                while(k<size&&l<size)
                {
                    if(inicio==true)
                    {
                        if(k<even.size()&&!nodeLst.contains(even.get(k)))
                        {
                            nodeLst.add(even.get(k));
                            inicio = false;
                            k++;
                        }
                        else
                            inicio = false;
                    }
                    else
                    {
                        if(l<odd.size()&&!nodeLst.contains(odd.get(l)))
                        {
                            nodeLst.add(odd.get(l));
                            inicio = true;
                            l++;
                        }
                        else
                            inicio = true;
                    }
                }

                for(int j=0;j<nodeLst.size();j++)
                {
                    Programa programa = new Programa(canales.get(i));
                    TagNode nodo = nodeLst.get(j);

                    TagNode[] hora = nodo.getElementsByAttValue("valign", "top", true, true);
                    TagNode[] titulo = nodo.getElementsHavingAttribute("href", true);
                    TagNode[] descripcion = nodo.getElementsByAttValue("colspan", "2", true, true);

                    boolean existeHora = false;
                    for(k=0;k<hora.length;k++)
                    {
                        if((hora[k].getText().length()==5)&&(hora[k].getText().indexOf(":")!=-1))
                        {
                            //System.out.println("Hora: " + contenido);
                            programa.setHoraInicio(hora[k].getText().toString());
                            existeHora = true;
                        }
                    }

                    // Si no hemos enontrado una hora, la pagina esta vacia
                    if(!existeHora)
                        return false;

                    // Ponemos el titulo
                    if(titulo.length!=0)
                    {
                        programa.setTitulo(titulo[0].getText().toString());
                    }

                    // Ponemos la descripcion
                    if(descripcion.length!=0)
                    {
                        programa.setDescripcion(descripcion[0].getText().toString());
                    }

                    // Anadimos el programa al canal
                    canales.get(i).insertarPrograma(programa);
                }
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(HTMLFetch.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }

    public ArrayList<Canal> getCanales()
    {
        return canales;
    }

    public void mostrar()
    {
        for(int i=0;i<canales.size();i++)
            System.out.println(i + "-" + canales.get(i).getNombre() + " | " + canales.get(i).getDireccion());
    }

    /**
     * Metodo mostrar.
     * 
     * @param index
     */
    public void mostrar(int index)
    {
        System.out.println("Canal: " + canales.get(index).getNombre());
        System.out.println("------------------------------------------");
        for(int i=0;i<canales.get(index).getProgramas().longitudForward();i++)
        {
            Programa p = canales.get(index).getProgramas().consultarForward(i);
            System.out.println("Programa: " + p.getTitulo());
            System.out.println("Descripcion: " + p.getDescripcion());
            System.out.println("Hora inicio: " + p.getHoraInicio());
        }
    }

    /**
     * Metodo getCanal
     * 
     * @param nombre
     * @return programas
     */
    public Canal getCanal(String nombre)
    {
        for(int i=0;i<canales.size();i++)
        {
            if(canales.get(i).getNombre().compareTo(nombre)==0)
            {
                System.out.println("Devuelvo canal:" + canales.get(i).getNombre());
                return canales.get(i);
            }
        }
        return null;
    }

    /**
     * Metodo getPrograma
     * 
     * @param c
     * @param titulo
     * @return programas
     */
    public Programa getPrograma(Canal c, String titulo)
    {
        for(int i=0;i<programas.size();i++)
        {
            if(programas.get(i).getCadena().equals(c))
            {
                if(programas.get(i).getTitulo().compareTo(titulo)==0)
                    return programas.get(i);
            }
        }
        return null;
    }
}

/**
 *
 *
 *
 * Integrantes:
 * @autor Maria Bracamonte 10-11147
 * @autor Edwin Franco 12-10630
 *
 * El codigo {@code Grafo} interface es la representacion
 * de un Grafo. Esta realizado mediante la interface
 * de java.
 *
 * Compilacion: make
 */
import java.util.*;

public interface Grafo
{   /**
     *Carga en un grafo la informacion almacenada en el archivo de texto cuya direccion viene dada 
     * por @param dirArchivo 
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     * @return true si todo fue cargado correctamente y false, si lo contrario.
    */
    public boolean cargarGrafo(String dirArchivo);
    /**
     *Retorna el numero de vertices del Grafo 
     * @return V, el numero de vertices del grafo.
    */
    public int numeroDeVertices();
    /**
     *Retorna el numero de aristas del Grafo 
     * @return E, el numero de aristas del grafo.
    */
    public int numeroDeLados();
    /**
     *Retorna true si se agrego correctamente el vertice al Grafo
     * @param V vertice a anadir
     * @return true, si se ingreso correctamente el vertice, false lo contrario.
    */
    public boolean agregarVertice(Vertice v);
    /**
     *Retorna true si se agrego correctamente el vertice al Grafo
     * @param id ID del vertice
     * @param peso el peso del vertice a anadir
     * @return true, si se ingreso correctamente el vertice, false lo contrario.
    */
    public boolean agregarVertice(String id, int peso);
    /**
     *Retorna el vertise querido a traves del ID del vertice
     * @param id ID del vertice
     * @return v vertice querido
    */
    public Vertice obtenerVertice(String id);
    /**
     *Retorna true si el vertice esta en el grafo
     * @param id ID del vertice
     * @return true si el vertice esta, false lo contrario
    */

    public boolean estaVertice(String id);
    /**{@code estaLado} funciona de tal manera de que itera en la lista
     *de aristas, luego por cada arista que este compara su extremo 1 y 
     *su extremo2 si concuerda con los parametros de entrada @u y @v 
     *entonces el lado existe
     *@return true si el Lado esta en el grafo, false lo contrario
    */
    public boolean estaLado(String u, String v);
    /**
    * Retorna true si se elimino correctametnte el vertice
    * Se va comparando cada vertice por su ID y cuando coincida con 
    * el @param id descontamos a la variable <em>V</em> y removemos
    * de la lista de vertices
    * @retun true si se elimino correctamente, false lo contrario
    * @throws NoSuchElementException si el vertice no se encuentra en el grafo
    */
    public boolean eliminarVertice(String id);
     /** Retorna la lista de vertices
     *  @return La lista que contiene todos los vertices
     */
    public List<Vertice> vertices();
    /** Retorna la lista los lados del grafo
     *  @return La lista que contiene todos los lados
     */
    public List<Lado> lados();
    /** Retorna el grado del vertice 
     *  @param id vertice en el cual queremos saber el grado
     *  @return un entero que es el grado del vertice
    */
    public int grado(String id);
    /** Retorna la lista los lados adyacentes de un vertice
     *  @param id ID der vertice en el cual queremos saber sus vertices adyacentes 
     *  @return La lista que contiene todos los lados adyacentes del vertice
     */
    public List<Vertice> adyacentes(String id);
    /** Retorna los lados incidentes del vertice 
    * @param id ID del vertice a buscar los lados incidentes de el
    * @return los lados incidentes al vertice de entrada
    * @throws NoSuchElementException si el vertice no se encuentra en el grafo
    */
    public LinkedList<Arista> incidentes(String id);
    /**
    * Retorna un grafo con los mismos datos que el de entrada
    * @return grafo 
    */
    public Object clone();
    /**
    * Retorna la impresion de un grafo
    * Imprime en consola
    */
    public String toString();
}
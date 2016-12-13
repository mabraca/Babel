import java.io.*;
import java.util.*;
/**
 *
 *
 *
 * Integrantes:
 * @autor Maria Bracamonte 10-11147
 *
 * El codigo {@code Babel} class es la solucion al problema
 * 11492 UVA
 *
 * Compilacion: make
 */


public class Babel
{
    public static Map<String, Integer> nombreAIdentificar = new HashMap<String, Integer>();
    public static Map<Integer,String> busqueda = new HashMap<Integer,String>();
    public static int[][] sum = new int[2000][2000];    
    public static Arista[][] camino= new Arista[2000][2000];
    public static PriorityQueue<Idioma> colaPrioridad = new PriorityQueue<Idioma>();
    public static int auxConteo = 0;
 	
 	/**
     * Se asigna un identificardor numerico que no existe al vertice de entrada 
     * @param str  Vertice de entrada
     * @return Un unico numero entero para cada vertice
     * Precondition: Sea un string el parametro str
     * Postcondition: Entero unico en el problema. 
    */
    public static int addId(String str)
    {
        Integer id = nombreAIdentificar.get(str);
        if (id == null) id = auxConteo++;
        nombreAIdentificar.put(str, id);
        busqueda.put(id,str);
        return id;
    }  
    /**
     * Se imprime en pantalla el resltado de la busqueda para la solucion del problema.
     * Una vez obtenido la matriz de costos <tt>sum</tt> iteramos en cada fila buscando el minimo
     * costo, una vez obtenido buscamos el id del vertice en la matriz de caminos <tt>camino</tt>
     * @param inicioBusqueda El id numero asigando al vertice inicial de la busqueda
     * @param finBusqueda El id numero asigando al vertice final de la busqueda
     * @param distance El valor numero obtenido en el recorrido que sera el costo minimo
     * Precondition: Cada parametro sea de tipo entero y  el valor string de inicioBusqueda 
     * pertenezca al Grafo y el valor string de finBusqueda pertenezca al Grafo.
     * Postcondition: Imprimir "0" si no se obtuvo el nodo de entrada o salida, o que sea un ciclo
     *				  Imprimir el camino si la distancia obtenida no es el maximo entero de java
     *				  Imprimir "imposible" si no exite camino hasta el vertice finBusqueda
     *
    */
    public static void toString(Integer inicioBusqueda, Integer finBusqueda, Integer distance){

        if (inicioBusqueda == null && finBusqueda == null && inicioBusqueda == finBusqueda) {System.out.println("0");} 
        else if (distance != Integer.MAX_VALUE){
                 LinkedList<String> resultado = new LinkedList<String>();
                 System.out.print(distance+" ");  
                 int aux= finBusqueda;int k=0;
                 int min=Integer.MAX_VALUE;
                 boolean noPasar= true;
                 while (min!=inicioBusqueda){ 
                    int value=Integer.MAX_VALUE;
                    for (int j=0;j<auxConteo;j++){ 
                        if(sum[aux][j]==distance){
                            String a =camino[aux][j].getId();
                            resultado.addFirst(a);
                            noPasar=false; min=inicioBusqueda;
                            break;
                        }
                        else if(sum[aux][j]<value){
                            value=sum[aux][j];
                            min=j;
                        }
                    }
                    if(min!=Integer.MAX_VALUE && noPasar==true){
                        String a =camino[aux][min].getId();
                        resultado.addFirst(a);
                        aux=min;
                    }                   
                }
                for(String cadena:resultado){
                    System.out.print(cadena +" ");
                }System.out.println();
                   
        }
        else System.out.println("imposible"); //No existe esa idioma o no fue alcanzable
    }
	/**
     * Solucion principal del problema
     * Se utiliza el algoritmo de costo minimo Dijkstra para buscar la solucion.
     * Se guarda en <tt>camino</tt> los identificadores de las aristas y a la vez se guarda en 
     * una matriz paralela <tt>sum</tt> los costo hasta cada vertice. Antes de verificar la
     * distancia obtenida que sea la minima, comprobamos si existe un predecesor con la misma inicial
     * @param fuenteIdioma El id  asigando al vertice inicial de la busqueda
     * @param destinoIdioma El id  asigando al vertice final de la busqueda
     * @param G Grafo no dirifido en que iteraremos para la solucion
     * Precondition: Exite vertice inicial y final en el grafo y  G grafo bien formado 
     * pertenezca al Grafo y el valor string de finBusqueda pertenezca al Grafo.
     * Postcondition: Camino y costo minimo desde el vertice inicial al vertice final del grafo G
     *
    */
    public static void DiksBabel(String fuenteIdioma, String destinoIdioma,GrafoNoDirigido G) 
    {       Integer inicioBusqueda = nombreAIdentificar.get(fuenteIdioma);
            Integer finBusqueda= nombreAIdentificar.get(destinoIdioma);
            int distance = Integer.MAX_VALUE;
            if (inicioBusqueda != null && finBusqueda != null && inicioBusqueda != finBusqueda)
            {   
                //Inicializamos  la matriz Sum con valores maximos alcanzables en java
                for(int i = 0; i <= auxConteo; i++){
                   for(int j = 0; j <= auxConteo; j++){
                        sum[i][j] = Integer.MAX_VALUE;
                        camino[i][j] = null;
                    }
                }
                colaPrioridad.offer(new Idioma(inicioBusqueda, 0, " "));
                //Codigo Dijsktra
                while(!colaPrioridad.isEmpty())
                {
                    Idioma actual = colaPrioridad.poll();
                    if (actual.id == finBusqueda)
                    {   distance = actual.distance;
                        break;
                    }
                    //Iteramos en los vertices
                    for(Arista adjEdge : G.incidentes(busqueda.get(actual.id)))
                    {   // Vemos que el primer caracter no sea igual que el anterior y sin ciclos
                        if ((adjEdge.getPeso() != actual.id) && adjEdge.getId().charAt(0) != actual.previousWord.charAt(0))
                        {   //Buscamos la arista con menor longitud de caracteres
                            int auxSuma = actual.distance + adjEdge.getId().length();
                            if (auxSuma < sum[actual.id][adjEdge.getPeso()])
                            {   //Agregamos a la cola de prioridad.
                                colaPrioridad.offer(new Idioma(adjEdge.getPeso(), auxSuma, adjEdge.getId()));
                                sum[actual.id][adjEdge.getPeso()] = auxSuma;
                                camino[actual.id][adjEdge.getPeso()]=adjEdge;
                            }
                        }
                    }
                }
                
                //Limpiamos la cola de prioridad para su nuevo uso.
                //Usamos ahora backtrac desde el vertice destino
                //Usamos el mismo codigo de arriba, simplemente cambiamos algunos parametros
                //Arista[][] caminoAux= new Arista[auxConteo][auxConteo];
                colaPrioridad.clear();
                colaPrioridad.offer(new Idioma(finBusqueda, 0, " "));
                int distance2 = Integer.MAX_VALUE;
                while(!colaPrioridad.isEmpty())  {
                    Idioma actual = colaPrioridad.poll();
                    if (actual.id == inicioBusqueda)
                    {   distance = actual.distance;
                        break;
                    }
                    for(Arista adjEdge : G.incidentes(busqueda.get(actual.id))){
                        if (adjEdge.getPeso() != actual.id && adjEdge.getId().charAt(0) != actual.previousWord.charAt(0))
                        {   int auxSuma = actual.distance + adjEdge.getId().length();
                            if (auxSuma < sum[actual.id][adjEdge.getPeso()])
                            {   colaPrioridad.offer(new Idioma(adjEdge.getPeso(), auxSuma, adjEdge.getId()));
                                sum[actual.id][adjEdge.getPeso()] = auxSuma;
                                camino[actual.id][adjEdge.getPeso()]=adjEdge;
                            }
                        }
                    }
                }
                // Calculamos la minima distancia
                if(distance>distance2){
                	distance=distance2;
                }
                
            }toString(inicioBusqueda,finBusqueda,distance);
            
        }
    /**
     * Codigo principal para la lectura del archivo de entrada. Construye un grafo no dirigido 
     * con los parametros de entrada del archivo y llama a <tt>DiksBabel</tt> para la resolucion del mismo
     * @param documento por consola args[0]
     * Precondition: Archivo existente en la misma carpeta que este codigo
     * Postcondition: Solucion al problema 
     *
    */
    public static void main(String[] args) throws IOException
    {   
        File file = new File(args[0]);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
        String line=null;
        while((line = bufferedReader.readLine()) != null)
        {			
            int M = Integer.parseInt(line);
            if (M < 0 || M>2000) throw new IllegalArgumentException("El numero de palabras no esta en el rango establecido");
            if (M == 0)
            {
                break;
            }
            //Leo cada linea del archivo
            StringTokenizer token = new StringTokenizer(bufferedReader.readLine());
            String fuenteIdioma = token.nextToken();
            String destinoIdioma = token.nextToken();
            //Limpio todas las listas que utilice en un escenario "pasado"
            auxConteo = 0;
            nombreAIdentificar.clear();
            busqueda.clear();
            colaPrioridad.clear();
            //creo un grafo
            GrafoNoDirigido G = new GrafoNoDirigido();
            //Leo las otras siguientes lineas de los vertices del grafo
            for(int i = 0; i < M; ++i)
            {
                token = new StringTokenizer(bufferedReader.readLine());
                String inicio = token.nextToken();
                String destino = token.nextToken();
                String palabra = token.nextToken();
                //agrego los vertices en el grafo
                G.agregarVertice(inicio,0);
                G.agregarVertice(destino,0);
                //Obtengo un identificador auxiliar de los vertices
                int auxFromId = addId(inicio);                
                int auxToId = addId(destino);
                //Lo agrego al grafo. 
                G.agregarArista(palabra,auxToId,inicio,destino);
                G.agregarArista(palabra,auxFromId,destino,inicio);


                sum[auxFromId][auxToId] = sum[auxToId][auxFromId] = Integer.MAX_VALUE;
                camino[auxFromId][auxToId] = camino[auxToId][auxFromId] =null;
            }
            DiksBabel(fuenteIdioma, destinoIdioma,G);
        }
    }
}

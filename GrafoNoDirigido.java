import java.io.*;
import java.util.*;
/**
 *
 *
 *
 * Integrantes:
 * @autor Maria Bracamonte 10-11147
 *
 * El codigo {@code GrafoNoDirigido} class es la representacion
 * de un Grafo no dirigido. Esta realizado mediante la interface
 * de java.
 *
 * Compilacion: make
 */


public class GrafoNoDirigido implements Grafo
{   private LinkedList<Vertice> vertices;
    private LinkedList<Arista> aristas;
    private int V;
    private int E;
    /** 
     *Inicializamos un Grafo no Dirigido de numero de vertices <em>V</em>
     * igual a cero y el numero de vertices <em>E</em> igual a cero
     */
    public GrafoNoDirigido() {
        vertices= new LinkedList<Vertice>();
        aristas= new LinkedList<Arista>();
        this.V=0;
        this.E=0;
        this.vertices=vertices;
        this.aristas=aristas;
    }
    
    /**
     *Carga en un grafo la informacion almacenada en el archivo de texto cuya direccion viene dada 
     * por @param dirArchivo 
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     * @return true si todo fue cargado correctamente y false, si lo contrario.
    */
    //Pre: Archivo cumple con el formato de grafos ya establecido
    //Post: Todo vertice y arista del archivo debe estar en las listas del Grafo
    public boolean cargarGrafo(String dirArchivo) {
        try{
            //Para leer el archivo con un Buffer
            File file = new File(dirArchivo);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            //Leemos la primera linea (Num de vertices)
            line = bufferedReader.readLine();
            int V = Integer.parseInt(line);
            if (V < 0) throw new IllegalArgumentException("El numero de vertices debe ser no negativo");
            this.V=V;
            //Leemos la segunda linea (Num de lados)
            line = bufferedReader.readLine();
            int E = Integer.parseInt(line);
            if (E < 0) throw new IllegalArgumentException("El numero de lados debe ser no negativo");
            this.E=E;
            /** var:
                numVertice= seguridad de conteo en vertices
                */
            int numVertice=0;
            int numLados=0;
            //Leemos las siguientes lineas para almacenar los vertices leidos
            while (numVertice<V) {
                   line = bufferedReader.readLine();
                   String[] information = line.split(" ");
                   int peso=Integer.parseInt(information[1]);
                   Vertice v= new Vertice(information[0], peso);
                   vertices.add(v);
                   numVertice++;
            }

            while (numLados<E) {
                   line = bufferedReader.readLine();
                   String[] information = line.split(" ");
                   int peso=Integer.parseInt(information[3]);
                   if (peso < 0) throw new IndexOutOfBoundsException("El peso debe ser positivo");
                   /*Aqui comparamos si hay un ciclo. Si lo hay entonces
                   solo agragamos una sola arista en ese sentido.
                   De lo contrario como es un grafo no dirigido agregamos
                   las arista de u-->v y de v-->u*/
                   if(information[1].equals(information[2])){
                        Vertice v= new Vertice(information[1], peso);
                        Vertice w= new Vertice(information[2], peso);
                        //Para un grafo
                        Arista a1= new Arista(information[0], peso, v,w);
                        aristas.add(a1);
                        numLados++;
                   }else{
                       Vertice v= new Vertice(information[1], peso);
                       Vertice w= new Vertice(information[2], peso);
                       //Para un grafo
                       Arista a1= new Arista(information[0], peso, v,w);
                       Arista a2= new Arista(information[0], peso, w,v);
                       aristas.add(a1);
                       aristas.add(a2);
                       numLados++;}
            }
            //Si ingresaron en la lista todos los vertices y todos lo lados.
            if(vertices.size()==V & aristas.size()==2*E)return true;
            else return false;
        }catch (Exception e) {
            throw new InputMismatchException("Invalid input format in Digraph constructor");
        }
    }
    /**
     *Retorna el numero de vertices del Grafo 
     * @return V, el numero de vertices del grafo.
    */
    //Pre: True
    //Post: V == numero de vertices del grafo
    public int numeroDeVertices() {
        return V;
    }
    /**
     *Retorna el numero de aristas del Grafo 
     * @return E, el numero de aristas del grafo.
    */
    //Pre: True
    //Post: E == numero de arcos del grafo
    public int numeroDeLados() {
        return E;
    }
   /**
     *Retorna true si se agrego correctamente el vertice al Grafo
     * @param V vertice a anadir
     * @return true, si se ingreso correctamente el vertice, false lo contrario.
    */
    //Pre: Parametro de tipo Vertice 
    //Post: ( true = el vertice se ha anadido al grafo) || 
    //              ( false = el vertice no se ha podido anadir al grafo)
    public boolean agregarVertice(Vertice v) {
        for(Vertice i: vertices){
            if(i.getId().equals(v.getId())){
                return false;
            }
        }
        V++;
        return vertices.add(v);
    }
    /**
     *Retorna true si se agrego correctamente el vertice al Grafo
     * @param id ID del vertice
     * @param peso el peso del vertice a anadir
     * @return true, si se ingreso correctamente el vertice, false lo contrario.
    */
    //Pre: Parametro de tipo string y int 
    //Post: ( true = el vertice se ha anadido al grafo) || 
    //              ( false = el vertice no se ha podido anadir al grafo)
    public boolean agregarVertice(String id, int peso) {
        for(Vertice i: vertices){
                if(i.getId().equals(id)){
                    return false;
                }
            }
        Vertice v= new Vertice(id, peso);
        V++;
        return vertices.add(v);
        

    }
    /**
     *Retorna el vertise querido a traves del ID del vertice
     * @param id ID del vertice
     * @return v vertice querido
    */
    //Pre: Parametro de tipo string
    //Post: Vertide con identificador id.
    public Vertice obtenerVertice(String id) {
        if(estaVertice(id)==false) throw new NoSuchElementException("El vertice no se encuentra");
        Vertice c= new Vertice("0",0);
        for(Vertice i: vertices){
                if(i.getId().equals(id)){
                    c = i;
                }
            }
        return c;
    }
    /**
     *Retorna true si el vertice esta en el grafo
     * @param id ID del vertice
     * @return true si el vertice esta, false lo contrario
    */
    //Pre Parametro tipo string
    //Post true si existe un vertice con identificador id
    public boolean estaVertice(String id) {
        for(Vertice i: vertices){
            if(i.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    /** 
     *{@code estaLado} funciona de tal manera de que itera en la lista
     *de aristas, luego por cada arista que este compara su extremo 1 y 
     *su extremo2 si concuerda con los parametros de entrada @u y @v 
     *entonces el lado existe
     *@return true si el Lado esta en el grafo, false lo contrario
    */
    //Pre Parametros (u,v) tipo string
    //Post true si el Lado(u,v) exite en la lista de aristas
    public boolean estaLado(String u, String v){
        boolean a=false,b=false;
        for(Arista i: aristas){
            if(i.getExtremo1().getId().equals(u))
            {
                a=true;
            }
             if(i.getExtremo2().getId().equals(v)){
                b=true;
             }
             if((a==true) &  (b==true)) return true;
        }
        return false;
    }
    /**
    * Retorna true si se elimino correctametnte el vertice
    * Se va comparando cada vertice por su ID y cuando coincida con 
    * el @param id descontamos a la variable <em>V</em> y removemos
    * de la lista de vertices
    * @retun true si se elimino correctamente, false lo contrario
    * @throws NoSuchElementException si el vertice no se encuentra en el grafo
    */
    //Pre Parametro tipo string && el vertice con ese string debe existir
    //Post El vertice id no debe estar en la lista de vertices && sus Lados no deben estar en la lista de aristas 
    public boolean eliminarVertice(String id) {
         if(estaVertice(id)==false) throw new NoSuchElementException("El vertice no se encuentra");
         int m=0,n=0;
         int cantidadLados=0;
        for(Vertice i: vertices){
            m++;
            if(i.getId().equals(id)){
                for(Arista j: aristas){
                    n++;
                    if(j.getExtremo1().getId().equals(id)){
                        cantidadLados++;
                    }
                }
                V--;
                E= E - cantidadLados;
                aristas.remove(n-1);
                vertices.remove(m-1);
                return true;

            }
        }
        return false;
    }
    /** 
    *Retorna la lista de vertices
     *  @return La lista que contiene todos los vertices
     */
    //Pre True
    //Post numero de vertices en el grafo
    public List<Vertice> vertices() {
        return vertices;
    }
     /** 
     *Retorna la lista los lados del grafo
     *  @return La lista que contiene todos los lados
     */
     //Pre True
    //Post numero de lados en el grafo
    public List<Lado> lados() {
        LinkedList<Lado> lis = new LinkedList<Lado>();
        for(Arista i: aristas){
              lis.add(i);
        }
        return lis;
    }
      /** 
     *Retorna la lista los lados del grafo
     *  @return La lista que contiene todos los lados
     */
     //Pre True
    //Post numero de lados en el grafo
    public LinkedList<Arista> aristas() {
        LinkedList<Arista> lis = new LinkedList<Arista>();
        for(Arista i: aristas){
              lis.add(i);
        }
        return lis;
    }
    /** 
    *Retorna el grado del vertice 
     *  @param id vertice en el cual queremos saber el grado
     *  @return un entero que es el grado del vertice
    */
    //Pre Parametro tipo string
    //Post Grado=Cantidad de lados indicendentes en el vertice
    public int grado(String id) {
        if(estaVertice(id)==false) throw new NoSuchElementException("El vertice no se encuentra");
        int grado=0;
        for(Arista i: aristas){
            if(i.getExtremo1().getId().equals(id)){
                grado++;
            }
        }
        return grado;
    }
    /** 
    * Retorna la lista los lados adyacentes de un vertice
     *  @param id ID der vertice en el cual queremos saber sus vertices adyacentes 
     *  @return La lista que contiene todos los lados adyacentes del vertice
     * @throws NoSuchElementException si el vertice no se encuentra en el grafo
     * {@code adyacentes} Compara si en las aristas hay un nodo
     * extremo con el parametro de entrada <em>id</em> y si lo consigue, busca luego 
     * en la lista de vertices el lado extremo del parametro(que sera el adyacente)
     * y lo agrega a la nueva lista de vertices creada en la funcion
     */
    // Pre Parametro tipo string y el id del vertice debe existir en la lista de vertices
    // Post result= vertices que sean adyacentes al vertice con identificador id.
    public List<Vertice> adyacentes(String id) {
        if(estaVertice(id)==false) throw new NoSuchElementException("El vertice no se encuentra");        
        LinkedList<Vertice> result = new LinkedList<Vertice>();
        for(Arista i: aristas){
            if (i.getExtremo1().getId().equals(id)){
                for(Vertice j: vertices){
                    if(j.getId().equals(i.getExtremo2().getId())){
                        result.add(j);
                    }
                }
            }
        }
        return result;
    }
    /** 
    *Retorna los lados incidentes del vertice 
    * @param id ID del vertice a buscar los lados incidentes de el
    * @return los lados incidentes al vertice de entrada
    * @throws NoSuchElementException si el vertice no se encuentra en el grafo
    */
    //Pre Parametro string
    // Post l= lados que inciden en el vertice con identificador id
    public LinkedList<Arista> incidentes(String id) {
        if(estaVertice(id)==false) throw new NoSuchElementException("El vertice no se encuentra");
        LinkedList<Arista> l = new LinkedList<Arista>();
        for(Arista i: aristas){
            if(i.getExtremo1().getId().equals(id)){
                l.add(i);

            }
        }
        return l;
    }

    /**
    * Retorna true si de agregaron correctamente las aristas 
    * @param a Arista de la arista a agregar
    * @return true si se agrego correctamente, false lo contrario
    * @throws NoSuchElementException si algun vertice de extremo no se encuentra en el grafo
    */
    //Pre Parametro de tipo Arista y el id de la arista para agregar no debe estar en los id de la lista de aristas
    //Post true si se agrego la arista en la lista de aristas
    public boolean agregarArista(Arista a) {
        //verifico si estan los vertices para hacer la arista
        if(estaVertice(a.getExtremo1().getId())==false) throw new NoSuchElementException("El vertice con que quiere hacer el arista no se encuentra");
        if(estaVertice(a.getExtremo2().getId())==false) throw new NoSuchElementException("El vertice con que quiere hacer el arista no se encuentra");
       
        boolean esta=false;
        for(Arista i: aristas){
            if(i.getId().equals(a.getId())){
                esta=true;
            }
        }
       if(esta==false){
        E++;
        Arista otroSentido= new Arista(a.getId(),a.getPeso(),a.getExtremo2(),a.getExtremo1());
        aristas.add(otroSentido);
        return aristas.add(a);
        }
        else return false;
    }
    /** 
    * Retorna true si de agregaron correctamente las aristas 
    * @param id ID de la arista a agregar
    * @param peso peso de la arista
    * @param u extremo de la arista
    * @param v extremo de la arista
    * @return true si se agrego correctamente, false lo contrario
    */
    //Pre Parametro string, int, string, string y verificar si los vertices extremos de la arista existan
    //Post La arista u->v se agrego en la lista de aristas
    public boolean agregarArista(String id, int peso, String u, String v) {
        //verifico si estan los vertices para hacer el arista
        if(estaVertice(u)==false) throw new NoSuchElementException("El vertice "+ u +" no se encuentra");
        if(estaVertice(v)==false) throw new NoSuchElementException("El vertice " + v +" no se encuentra");
        //verifico si ya se encuentra el nombre del arista
        boolean esta=false;
        
        for(Arista i: aristas){
            if(i.getId().equals(id)){
                esta=true;break;
            }
        }
        //Si no esta el nombre del arista procedo a agregarlo
           if(esta==false){
            int peso1=0,peso2=0;
                    for(Vertice i: vertices){
                        if(i.getId().equals(u)){
                            peso1=i.getPeso(); 
                            break;                  
                            
                        }
                    }
                    for(Vertice i: vertices){
                        if(i.getId().equals(v)){
                            peso2=i.getPeso();
                            break;
                        }
                    }
            Vertice b= new Vertice(u,peso1);
            Vertice c= new Vertice(v,peso2);
            Arista a = new Arista(id,peso,b,c);
            Arista d = new Arista(id,peso,c,b);
            E++;
            aristas.add(d);
            return aristas.add(a);
            }
        return false;
    }
    /** 
     * Retorna true si se elimino correctamente la arista
     * @return true si se elimino correctamente, false lo contrario
     * @throw NoSuchElementException si la arista no se encuentra
     */
    //Pre Parametro de tipo string y la arista con identificador id debe existir en la lista de aristas
    //Post La arista con identificador id no debe estar en la lista de aristas
    public boolean eliminarArista(String id) {
        boolean esta=false;
        int j=0;
        for(Arista i: aristas){
            j++;
            if(i.getId().equals(id)){
                esta=true;break;
            }
        }

        if(esta==false){
           throw new NoSuchElementException("La arista no se encuentra");        
        }else {
                aristas.remove(j-1);
                E--;
                return true;
        }
    }
    /**
    * Retorna la arista buscada a traves de @param id
    * @return arista buscada
    * @throw NoSuchElementException si no existe la arista
    */
    //Pre Parametro tipo string de la arista
    //Post Arista con identificador id
    public Arista obtenerArista(String id) {
        for(Arista i : aristas){
            if(i.getId().equals(id)){
                    return i;
            }
        } 
        throw new NoSuchElementException("El vertice no se encuentra");       
    }
     /**
    * Retorna la arista buscada a traves de @param v y @param w
    * @return String id del arista buscada
    * @throw NoSuchElementException si no existe la arista
    */
    //Pre Parametro v y w lados extremos de la arista
    //Post identificador id de la arista
    public String obtenerArista(String u, String v) {
        if(estaLado(u,v)==true){
            boolean a=false,b=false;
            for(Arista i: aristas){
                if(i.getExtremo1().getId().equals(u))
                {
                    a=true;
                }
                 if(i.getExtremo2().getId().equals(v)){
                    b=true;
                 }
                 if((a==true) &  (b==true)) return i.getId();
            }
            return null;
        } 
        throw new NoSuchElementException("El vertice no se encuentra");       
    }
    /**
    * Retorna un grafo con los mismos datos que el de entrada
    * @return grafo 
    */
    public Object clone() {
        GrafoNoDirigido G= new GrafoNoDirigido();
        for(Vertice i: vertices){
            G.agregarVertice(i);
        }
        for(Arista i: aristas){
            G.agregarArista(i);
        }
        return G;
    }
    /**
    * Retorna la impresion de un grafo
    * @return String de caracteres del grafo
    */
    public String toString() {
        String b="";
        String a="";
        b= "\n IDVerticeInicial PesoVerticeInicial :  IDVerticeFinal PesoVerticeFinal IDArista PesoArista \n" + b;
        a=a+b;
        for (Vertice j: vertices){
            b= j.getId() +" Peso: " + j.getPeso() +"  -->  \n" ;
            a=a+b; 
           for(Arista i: aristas){
                for(Vertice k:vertices){
                    if(j.getId().equals(i.getExtremo1().getId()) & i.getExtremo2().getId().equals(k.getId()))
                    {
                        b= k.getId() + "  Peso: " + k.getPeso()+ " || Arista : "+ i.getId()+" "+i.getPeso()  +"\n " ;
                        a=a+b;
                    }
                }
            } b= "\n"; a=a+b;
        }
        return a;
    }
}
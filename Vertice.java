/**
 *
 *
 *
 * Integrantes:
 * @autor Maria Bracamonte 10-11147
 * @autor Edwin Franco 12-10630
 *
 * El codigo {@code GrafoNoDirigido} class es la representacion
 * de un Grafo no dirigido. Esta realizado mediante la interface
 * de java.
 *
 * Compilacion: make
 */

public class Vertice
{
  private String id;
  private int peso;
  //Constructor de la clase
  //Pre: peso>0
  //Post: this.id == id && this.peso == peso
  public Vertice(String id, int peso) {
        if (peso < 0) throw new IndexOutOfBoundsException("El peso no debe ser negativo");
        this.peso=peso;
        this.id=id;
  }
//Metodo que devuelve el peso del vertice 
  //Pre: True
  //Post: Retorna peso
  public int getPeso() {
    return peso;
  }
//Metodo que devuelve la identificacion del vertice 
  //Pre: True
  //Post: Retorna id
  public String getId() {
    return id;
  }
//Metodo que devuelve el Vertice en forma de string
  //Pre: True
  //Post: str == "Vertice:\n" + id + "\nPeso:\n" + peso
  
  public String toString() { 
    return String.format("%s  %.5f", id,peso );
  }
}
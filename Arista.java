/**
 *
 *
 *
 * Integrantes:
 * @autor Maria Bracamonte 10-11147
 * @autor Edwin Franco 12-10630
 *
 * El codigo {@code Arista} class es la representacion
 * de la clase abstracta Lado. 
 *
 * Compilacion: make
 */

public class Arista extends Lado
{
  private Vertice u;
  private Vertice v;
  private String id;
  private int peso;
  
  //Constructor de la clase
  //Pre: True
  //Post: this.identificacion == id && this.peso == peso && this.vertice u = u && this.vertice v  = v

  public Arista(String id, int peso, Vertice u, Vertice v) {
        super(id,peso);
        this.u = u;
        this.v = v;
        this.id=id;
        this.peso=peso;

  }
  //Metodo que devuelve la identificacion del vertice 
  //Pre: True
  //Post: Retorna id
  public String getId() {
    return id;
  }
  //Metodo que devuelve el peso del vertice 
  //Pre: True
  //Post: Retorna peso
  public int getPeso() {
    return peso;
  }
  //Metodo que devuelve el vertice inicial del Arista
  //Pre: True
  //Post: Retorna u
  public Vertice getExtremo1() {
    return u;
  }
  //Metodo que devuelve el vertice final del Arista
  //Pre: True
  //Post: Retorna v
  public Vertice getExtremo2() {
    return v;
  }
  //Metodo que devuelve el Arista en forma de string llamando la clase abstracta Lado
  //Pre: True
  //Post: str == "Arista:\n" + id + "\nPeso:\n" + peso 
  public String toString() {
    return "Arista: "+ u +" -> "+ v +" "+ id+" - Peso: " + peso;
  }
}
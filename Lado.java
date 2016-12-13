/**
 *
 *
 *
 * Integrantes:
 * @autor Maria Bracamonte 10-11147
 * @autor Edwin Franco 12-10630
 *
 * El codigo {@code Lado} class es la representacion
 * abstracta de los lados del grafo.
 *
 * Compilacion: make
 */

public abstract class Lado
{
  private String id;
  private int peso;
//Constructor de la clase
  //Pre: True
  //Post: this.id == id && this.peso == peso
  public Lado(String id, int peso) {
  	this.id =id;
  	this.peso=peso;
  }
//Metodo que devuelve el identificador del lado
  //Pre: True
  public String getId() {
  	return id;
  }
//Metodo que devuelve el peso del lado 
  //Pre: True
  //Post: Retorna pes
  public int getPeso() {
  	return peso;
  }
//funcion para ser llamada por ser una clase abstracta
  public abstract String toString();
}
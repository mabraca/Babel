import java.io.*;
import java.util.*;
/**
 *
 *
 *
 * Integrantes:
 * @autor Maria Bracamonte 10-11147
 *
 * El codigo {@code Idioma} class en la que se guardan cada lenguaje obtenido desde el 
 * archivo de entrada, con sus respectivas caracteristicas
 * 11492 UVA
 *
 * Compilacion: make
 */
import java.util.*;
    public class Idioma implements Comparable<Idioma>
    {
        public int id;
        public int distance;
        public String previousWord;
        
        public Idioma(int id, int distance, String previousWord)
        {
            this.id = id;
            this.distance = distance;
            this.previousWord = previousWord;
        }
        
        public int compareTo(Idioma lang)
        {
            return distance - lang.distance;
        }
    }
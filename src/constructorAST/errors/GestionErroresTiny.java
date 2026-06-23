package constructorAST.errors;

import constructorAST.alex.UnidadLexica;

public class GestionErroresTiny {
   public void errorLexico(int fila, int columna, String lexema) {
     System.out.println("ERROR fila "+fila+" columna "+columna+": Caracter inesperado: "+lexema); 
     System.out.println("Recordar que por una carta no se admiten 'efes'");
     System.out.println("Recordar que una variable ww(w)* no está permitida.");
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     if (unidadLexica.lexema() != null) {
       System.out.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado \""+unidadLexica.lexema()+"\"");
     } else {
       System.out.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado");
     }
     System.exit(1);
   }
}

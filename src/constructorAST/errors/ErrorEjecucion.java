
package constructorAST.errors;

import constructorAST.ast.ASTNode;

public class ErrorEjecucion {

    private ASTNode nodo;
    private TipoError tipo;

    public ErrorEjecucion(ASTNode nodo, TipoError tipo){
        this.nodo = nodo;
        this.tipo = tipo;
    }

    public void gestionaError() {
    	
		String ubicacion = "";
		if (nodo.getFila() > 0) {
			ubicacion = " (Fila " + nodo.getFila() ;
		}
		if(nodo.getCol() > 0) {
			ubicacion += " | Columna: " + nodo.getCol() + ")";
		}
		else if(nodo.getCol() < 0 && nodo.getFila() > 0) {
			ubicacion += ")";
		}
		String mensaje = "ERROR" + ubicacion + ": ";
        switch (tipo){
            case NOVALUE: 
                mensaje += "Acceso a variable no inicializada\n";
                break;
            case DIV_POR_0:
            	mensaje += "Divisor == 0\n";
            	break;
            case MAX_IT_WHILE:
            	mensaje += "Maximo de iteracines alcanzado en el while(10000it.).\n Posible bucle infinito.\n";
            	break;
            case MAX_IT_FOR:
            	mensaje += "Maximo de iteracines alcanzado en el for(10000it.).\n Posible bucle infinito.\n";
            	break;
            default:
                break;
        }
        mensaje += "Localización: " + nodo.toString();
        System.out.println(mensaje);
        System.exit(1);    
    }  

}

package constructorAST.errors;

import constructorAST.ast.ASTNode;

public class ErrorSemantico {

	private ASTNode nodo;
	private TipoError tipo;

	public ErrorSemantico(ASTNode nodo, TipoError tipo) {
		this.nodo = nodo;
		this.tipo = tipo;
		
	}

	public String toString() {
		
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
		switch (tipo) {
		case DUPIDEN:
			mensaje += "Definición de identificador duplicada\n";
			break;
		case NOIDEN:
			mensaje += "Uso de identificador no definido\n";
			break;
		case TYPEOP:
			mensaje += "Los tipos utilizados no son los esperados por el operador\n";
			break;
		case TYPECOND:
			mensaje += "La cláusula de un condicional esperado una expresión booleana\n";
			break;
		case TYPEASIG:
			mensaje += "El tipo de la expresión asignada no es el esperado\n";
			break;
		case TYPEASIG_TERNARIO:
			mensaje += "Los tipos no se corresponden en el ASIGNADOR TERNARIO\n";
			break;
		case TYPEWHILE:
			mensaje += "La condicion no es una condicion booleana en el WHILE\n";
			break;
		case TYPEFOR:
			mensaje += "La condicion no es una condicion booleana en el FOR\n";
			break;
		case TYPEASIG_TERNARIO_CONDICION:
			mensaje += "La condicion no es una condicion booleana en el ASIGNADOR TERNARIO\n";
			break;
		case TYPEASIG_TERNARIO_A_TYPE:
			mensaje += "La parte A no esta declarada o es incompatible\n";
			mensaje += "Partes: FIN = ¿CONDICION? si: A _ sino: B;\n";
			break;
		case TYPEASIG_TERNARIO_B_TYPE:
			mensaje += "La parte B no esta declarada o es incompatible\n";
			mensaje += "Partes: FIN = ¿CONDICION? si: A _ sino: B;\n";
			break;
		case TYPEASIG_TERNARIO_LEFT_TYPE:
			mensaje += "La parte FIN no esta declarada o es incompatible\n";
			mensaje += "Partes: FIN = ¿CONDICION? si: A _ sino: B;\n";
			break;
		default:
			break;
		}
		mensaje += "Localización: " + nodo.toString();
		return mensaje;
	}

}
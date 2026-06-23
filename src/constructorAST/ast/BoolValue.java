package constructorAST.ast;

import java.util.LinkedList;
import constructorAST.errors.ErrorSemantico;

public class BoolValue extends E {
	private String valor;

	public BoolValue(int fila, int col, String valor) {
		this.setPosition(fila, col);
		this.valor = valor;
	}

	public KindE kind() {
		return KindE.BOOL;
	}

	public String toString() {
		return valor;
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
		type = Type.BOOL;
	}

	public Double execute() {
		if (valor == "verdadero")
			return 1.0;
		else
			return 0.0;
	}

}

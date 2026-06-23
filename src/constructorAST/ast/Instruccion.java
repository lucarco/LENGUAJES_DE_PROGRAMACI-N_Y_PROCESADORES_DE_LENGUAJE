package constructorAST.ast;

import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;
import java.util.LinkedList;

public abstract class Instruccion implements ASTNode {

	protected int fila;
	protected int col;

	public void setPosition(int fila, int col) {
		this.fila = fila;
		this.col = col;
	}

	public int getFila() {
		return fila;
	}

	public int getCol() {
		return col;
	}

	public abstract KindI kind();

	public NodeKind nodeKind() {
		return NodeKind.INSTRUCCION;
	}

	public String toString() {
		return "";
	}

	// Analisis de identificadores
	public abstract void bind(TableStack bind_info, LinkedList<ErrorSemantico> errors);

	// Analisis de tipos
	public abstract void checkTypes(LinkedList<ErrorSemantico> errors);

	public Type getType() {
		return null;
	}

	// Ejecución
	public abstract void execute();

	// analisis de las cartas (super_estrella y dory)
	public void analisis_cartas() {
	}
}

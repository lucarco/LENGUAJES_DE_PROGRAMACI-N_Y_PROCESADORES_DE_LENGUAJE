package constructorAST.ast;

import java.util.LinkedList;
import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;

public abstract class E implements ASTNode {

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

	protected Type type;

	public abstract KindE kind();

	public E opnd1() {
		throw new UnsupportedOperationException("opnd1");
	}

	public E opnd2() {
		throw new UnsupportedOperationException("opnd2");
	}

	public String num() {
		throw new UnsupportedOperationException("num");
	}

	public NodeKind nodeKind() {
		return NodeKind.EXPRESION;
	}

	public String toString() {
		return "";
	}

	// Analisis de identificadores
	public void bind(TableStack bind_info, LinkedList<ErrorSemantico> errors) {
	};

	// Analisis de tipos
	public abstract void checkTypes(LinkedList<ErrorSemantico> errors);

	public Type getType() {
		return type;
	};

	// Ejecución
	public abstract Double execute();

}

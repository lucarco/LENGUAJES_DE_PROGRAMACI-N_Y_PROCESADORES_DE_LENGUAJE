package constructorAST.ast;

import java.util.LinkedList;
import constructorAST.errors.ErrorSemantico;

public class Real extends E{
	private String v;

	public Real(int fila, int col, String v) {
		this.setPosition(fila, col);
		this.v = v;
	}

	public String num() {
		return v;
	}

	public KindE kind() {
		return KindE.REAL;
	}

	public String toString() {
		return v;
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
		type = Type.REAL;
	}

	@Override
    public Double execute() {
		return Double.parseDouble(v);
	}
}

package constructorAST.ast;

import java.util.LinkedList;
import constructorAST.errors.ErrorSemantico;

public class Num extends E {
	private String v;

	public Num(int fila, int col, String v) {
		this.setPosition(fila, col);
		this.v = v;
	}

	public String num() {
		return v;
	}

	public KindE kind() {
		return KindE.NUM;
	}

	public String toString() {
		return v;
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
		type = Type.NUM;
	}

	public Double execute() {
		return (double) Math.round(Double.parseDouble(v));
	}

}

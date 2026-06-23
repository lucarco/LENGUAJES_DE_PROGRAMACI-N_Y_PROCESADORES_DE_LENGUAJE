package constructorAST.ast;

import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;
import java.util.LinkedList;

public class Declaracion extends Instruccion {
	private String name;
	private Type type;

	private Double value;
	private int contAsignaciones;

	public Declaracion(int fila, int col, Type type, String name) {
		this.setPosition(fila, col);
		this.type = type;
		this.name = name;
		this.value = null;
		this.contAsignaciones = 0;
	}

	@Override
	public KindI kind() {
		return KindI.DECLARACION;
	}

	public String toString() {
		return "DEC: " + type + " " + name + "\n";
	}

	public void bind(TableStack bind_info, LinkedList<ErrorSemantico> errors) {
		if (bind_info.contains_id_peek(name)) {
			errors.add(new ErrorSemantico(this, TipoError.DUPIDEN));
		}
		bind_info.add_id(name, this);
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
	}

	public Type getType() {
		return type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void execute() {
	};

	public void addAsignacion() {
		contAsignaciones++;
	}
	
	public int getAsignaciones() {
		return contAsignaciones;
	}
}

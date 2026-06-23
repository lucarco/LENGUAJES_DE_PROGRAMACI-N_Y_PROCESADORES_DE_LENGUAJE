package constructorAST.ast;

import java.util.LinkedList;
import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;

public class Asignacion extends Instruccion {
	private String variable;
	private E valor;

	private Declaracion variable_binding;

	public Asignacion(int fila, int col, String variable, E valor) {
		this.setPosition(fila, col);
		this.variable = variable;
		this.valor = valor;
	}

	@Override
	public KindI kind() {
		return KindI.ASIGNACION;
	}

	public String toString() {
		return "ASIG: " + variable + " -> " + valor + "\n";
	}

	public void bind(TableStack bind_info, LinkedList<ErrorSemantico> errors) {
		// Check that the assigned variable is defined
		Declaracion lookup_variable = bind_info.lookup(variable);
		if (lookup_variable == null) {
			errors.add(new ErrorSemantico(this, TipoError.NOIDEN));
		} else {
			this.variable_binding = lookup_variable;
		}

		// Bind the value assigned
		valor.bind(bind_info, errors);
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
		// Check that the types are valid
		Type left_type = variable_binding.getType();
		if (left_type == null) {
			errors.add(new ErrorSemantico(this, TipoError.TYPEASIG));
		}

		// Check that the value assigned is well typed
		valor.checkTypes(errors);
		Type right_type = valor.getType();
		if (right_type == null) {
			errors.add(new ErrorSemantico(this, TipoError.TYPEASIG));
		}

		if (left_type != right_type && !(left_type == Type.REAL && right_type == Type.NUM)) {
			errors.add(new ErrorSemantico(this, TipoError.TYPEASIG));
		}
	}

	public void execute() {
		Double value = valor.execute();
		variable_binding.setValue(value);
	}

	@Override
	public void analisis_cartas() {
		if(variable_binding != null) {
			variable_binding.addAsignacion();
		}
	}
}

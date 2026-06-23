package constructorAST.ast;

import java.util.LinkedList;

import constructorAST.alex.TokenValue;
import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;

public class AsignacionTres extends Instruccion {
	private String variable;
	private E condicion, a, b;

	private Declaracion variable_binding;

	public AsignacionTres(int fila, int col, String variable, E exp, E a, E b) {
		this.setPosition(fila, col);
		this.variable = variable;
		this.condicion = exp;
		this.a = a;
		this.b = b;
	}

	@Override
	public KindI kind() {
		return KindI.ASIGNACION_TERNARIA;
	}

	public String toString() {
		return "ASIGNACION_TERNARIA: " + variable + " -> " + condicion + "? --> VERDADERO: " + a + " FALSO: " + b
				+ "\n";
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
		condicion.bind(bind_info, errors);
		a.bind(bind_info, errors);
		b.bind(bind_info, errors);
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
		// Check that the types are valid
		Type left_type = variable_binding.getType();
		if (left_type == null) {
			errors.add(new ErrorSemantico(this, TipoError.TYPEASIG_TERNARIO_LEFT_TYPE));
		}

		// Check that the value assigned is well typed
		condicion.checkTypes(errors);
		Type condition_type = condicion.getType();
		if (condition_type != Type.BOOL) {
			errors.add(new ErrorSemantico(condicion, TipoError.TYPEASIG_TERNARIO_CONDICION));
		}

		a.checkTypes(errors);
		Type a_type = a.getType();
		if (a_type == null) {
			errors.add(new ErrorSemantico(this, TipoError.TYPEASIG_TERNARIO_A_TYPE));
		}

		if (left_type != a_type && !(left_type == Type.REAL && a_type == Type.NUM)) {
			errors.add(new ErrorSemantico(this, TipoError.TYPEASIG_TERNARIO_A_TYPE));
		}

		b.checkTypes(errors);
		Type b_type = b.getType();
		if (b_type == null) {
			errors.add(new ErrorSemantico(this, TipoError.TYPEASIG_TERNARIO_B_TYPE));
		}

		if (left_type != b_type && !(left_type == Type.REAL && b_type == Type.NUM)) {
			errors.add(new ErrorSemantico(this, TipoError.TYPEASIG_TERNARIO_B_TYPE));
		}

	}

	@Override
	public void analisis_cartas() {
		if(variable_binding != null) {
			variable_binding.addAsignacion();
		}
	}
	
	@Override
	public void execute() {
		Double cond_value = condicion.execute();
		Double final_value;
		
		if (cond_value == 1.0) {
			final_value = a.execute();

		} else {
			final_value = b.execute();
		}
		variable_binding.setValue(final_value);
		variable_binding.addAsignacion();	// Contar asignaciones por una carta (TO_READ.txt)

	}
}

package constructorAST.ast;

import java.util.LinkedList;
import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;

public class Condicional extends Instruccion {
	private E condicion;
	private LinkedList<Instruccion> inst_IF;
	private LinkedList<Instruccion> inst_ELSE;

	public Condicional(int fila, int col, E Condicion, LinkedList<Instruccion> inst_IF, LinkedList<Instruccion> inst_ELSE) {
		this.setPosition(fila, col);
		this.condicion = Condicion;
		this.inst_IF = inst_IF;
		this.inst_ELSE = inst_ELSE;
	}

	public KindI kind() {
		return KindI.CONDICIONAL;
	}

	public String toString() {
		String value = "";
		value += "IF " + condicion + ":\n";
		for (Instruccion instr : inst_IF) {
			value += instr;
		}
		value += "ELSE:\n";
		for (Instruccion instr : inst_ELSE) {
			value += instr;
		}
		value += "ENDIF\n";
		return value;
	}

	public void bind(TableStack bind_info, LinkedList<ErrorSemantico> errors) {
		// Bind the conditional expression
		condicion.bind(bind_info, errors);

		// Open new block and bind the IF branch
		bind_info.push();
		for (Instruccion instr : inst_IF) {
			instr.bind(bind_info, errors);
		}
		bind_info.pop();

		// Open new block and bind the ELSE branch
		bind_info.push();
		for (Instruccion instr : inst_ELSE) {
			instr.bind(bind_info, errors);
		}
		bind_info.pop();
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
		// Check that the conditional is of type bool
		condicion.checkTypes(errors);
		Type left_type = condicion.getType();
		if (left_type != Type.BOOL) {
			errors.add(new ErrorSemantico(condicion, TipoError.TYPECOND));
		}

		// Check that the branches are well typed
		for (Instruccion instr : inst_IF) {
			instr.checkTypes(errors);
		}
		for (Instruccion instr : inst_ELSE) {
			instr.checkTypes(errors);
		}
	}

	public void execute() {
		Double value = condicion.execute();

		if (value != 0.0) { 
			for (Instruccion instr : inst_IF) {
				instr.execute();
			}
		} 
		else { 
			if (inst_ELSE != null) {	// Puede estar vacio
				for (Instruccion instr : inst_ELSE) {
					instr.execute();
				}
			}
		}
	}
}

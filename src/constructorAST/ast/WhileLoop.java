package constructorAST.ast;

import java.util.LinkedList;

import constructorAST.errors.ErrorEjecucion;
import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;

public class WhileLoop extends Instruccion {
	private E condicion;
	private LinkedList<Instruccion> inst_WHILE;

	public WhileLoop(int fila, int col, E Condicion, LinkedList<Instruccion> inst_WHILE) {
		this.setPosition(fila, col);
		this.condicion = Condicion;
		this.inst_WHILE = inst_WHILE;
	}

	public KindI kind() {
		return KindI.WHILE;
	}

	public String toString() {
		String value = "";
		value += "WHILE " + condicion + ":\n";
		for (Instruccion instr : inst_WHILE) {
			value += instr;
		}
		value += "ENDWHILE\n";
		return value;
	}

	public void bind(TableStack bind_info, LinkedList<ErrorSemantico> errors) {
		// Bind the conditional expression
		condicion.bind(bind_info, errors);

		// Open new block and bind the WHILE branch
		bind_info.push();
		for (Instruccion instr : inst_WHILE) {
			instr.bind(bind_info, errors);
		}
		bind_info.pop();
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
		// Check that the conditional is of type bool
		condicion.checkTypes(errors);
		Type condition_type = condicion.getType();
		if (condition_type != Type.BOOL) {
			errors.add(new ErrorSemantico(condicion, TipoError.TYPEWHILE));
		}

		// Check that the branches are well typed
		for (Instruccion instr : inst_WHILE) {
			instr.checkTypes(errors);
		}

	}

	@Override
	public void execute() {
		int cont = 0;	// Para evitar bucles infinitos
		final int MAX_ITERATIONS = 10000;
		Double value = condicion.execute();
		
		while (value != 0.0) {
			cont++;
			if(cont >= MAX_ITERATIONS) {
				ErrorEjecucion error = new ErrorEjecucion(this, TipoError.MAX_IT_WHILE);
				error.gestionaError();
			}
			for (Instruccion instr : inst_WHILE) {
				instr.execute();
			}
			value = condicion.execute();
		}
	}
}
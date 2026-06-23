package constructorAST.ast;

import java.util.LinkedList;

import constructorAST.errors.ErrorEjecucion;
import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;

public class ForLoop extends Instruccion {
	private Instruccion asigIni;
	private E condicion;
	private Instruccion asigBucl;
	private LinkedList<Instruccion> inst_FOR;

	public ForLoop(int fila, int col, Instruccion ini, E cond, Instruccion paso, LinkedList<Instruccion> inst_FOR) {
		this.setPosition(fila, col);
		this.asigIni = ini;
		this.condicion = cond;
		this.asigBucl = paso;
		this.inst_FOR = inst_FOR;
	}

	public KindI kind() {
		return KindI.FOR;
	}

	public String toString() {
		String value = "";
		value += "FOR (" + asigIni + " ; " + condicion + ";" + asigBucl + " ;):\n";
		for (Instruccion instr : inst_FOR) {
			value += instr;
		}
		value += "ENDFOR\n";
		return value;
	}

	public void bind(TableStack bind_info, LinkedList<ErrorSemantico> errors) {
		// Bind the conditional expression
		asigIni.bind(bind_info, errors);
		condicion.bind(bind_info, errors);
		asigBucl.bind(bind_info, errors);
		// Open new block and bind the FOR branch
		bind_info.push();

		for (Instruccion instr : inst_FOR) {
			instr.bind(bind_info, errors);
		}
		bind_info.pop();
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
		// Check that the conditional is of type bool
		asigIni.checkTypes(errors);
		condicion.checkTypes(errors);
		asigBucl.checkTypes(errors);

		Type left_type = condicion.getType();
		if (left_type != Type.BOOL) {
			// errors.add(new ErrorSemantico(condicion, TipoError.TYPEFOR));
			errors.add(new ErrorSemantico(condicion, TipoError.TYPEFOR));

		}

		// Check that the branches are well typed
		for (Instruccion instr : inst_FOR) {
			instr.checkTypes(errors);
		}
	}

	@Override
	public void execute() {

		asigIni.execute();
		Double value = condicion.execute();
		int cont = 0; // Para evitar bucles infinitos
		final int MAX_ITERATIONS = 10000;

		while (value != 0.0) {
			cont++;
			if (cont >= MAX_ITERATIONS) {
				ErrorEjecucion error = new ErrorEjecucion(this, TipoError.MAX_IT_FOR);
				error.gestionaError();
			}
			for (Instruccion instr : inst_FOR) {
				instr.execute();
			}
			asigBucl.execute();
			value = condicion.execute();

		}
	}
}

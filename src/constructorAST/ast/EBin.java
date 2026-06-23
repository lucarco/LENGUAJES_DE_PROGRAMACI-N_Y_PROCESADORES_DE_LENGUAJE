package constructorAST.ast;

import java.util.LinkedList;

import constructorAST.errors.ErrorEjecucion;
import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;

public class EBin extends E {
	private E opnd1;
	private E opnd2;
	private BinOps op;

	public EBin(int fila, BinOps op, E opnd1, E opnd2) {
		this.setPosition(fila, -1);
		this.op = op;
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
	}

	public E opnd1() {
		return opnd1;
	}

	public E opnd2() {
		return opnd2;
	}

	public KindE kind() {
		return KindE.EBIN;
	}

	public String toString() {
		String opstr = "";
		switch (op) {
		case MAS:
			opstr = "MAS";
			break;
		case IGUAL:
			opstr = "IGUAL";
			break;
		case MAYOR:
			opstr = "MAYOR";
			break;
		case MUL:
			opstr = "MUL";
			break;
		case DIV:
			opstr = "DIV";
			break;
		case RESTA:
			opstr = "RESTA";
			break;
		case MENOR:
			opstr = "MENOR";
			break;
		case MAYORIGUAL:
			opstr = "MAYORIGUAL";
			break;
		case MENORIGUAL:
			opstr = "MENORIGUAL";
			break;
		case AND:
			opstr = "AND";
			break;
		case OR:
			opstr = "OR";
			break;
		default:
			break;
		}
		return opnd1 + " " + opstr + " " + opnd2;
	}

	public void bind(TableStack bind_info, LinkedList<ErrorSemantico> errors) {
		opnd1.bind(bind_info, errors);
		opnd2.bind(bind_info, errors);
	}

	public void checkTypes(LinkedList<ErrorSemantico> errors) {
		// Check the left and right ops
		opnd1.checkTypes(errors);
		opnd2.checkTypes(errors);

		Type left_type = opnd1.getType();
		Type right_type = opnd2.getType();

		switch (op) {
		case MAS:
		case MUL:
		case DIV:
		case RESTA:
			if ((left_type != Type.NUM && left_type != Type.REAL)
					|| (right_type != Type.NUM && right_type != Type.REAL)) {
				errors.add(new ErrorSemantico(this, TipoError.TYPEOP));
			}
			if (left_type == Type.REAL || right_type == Type.REAL) {
				type = Type.REAL;
			} else {
				type = Type.NUM;
			}
			break;

		case IGUAL:
			if (left_type != right_type) {
				errors.add(new ErrorSemantico(this, TipoError.TYPEOP));
			}
			type = Type.BOOL;
			break;

		case MAYOR:
		case MENOR:
		case MAYORIGUAL:
		case MENORIGUAL:
			if ((left_type != Type.NUM && left_type != Type.REAL)
					|| (right_type != Type.NUM && right_type != Type.REAL)) {
				errors.add(new ErrorSemantico(this, TipoError.TYPEOP));
			}
			type = Type.BOOL;
			break;

		case AND:
		case OR:
			if (left_type != Type.BOOL || right_type != Type.BOOL) {
				errors.add(new ErrorSemantico(this, TipoError.TYPEOP));
			}
			type = Type.BOOL;
			break;

		default:
			break;
		}
	}

	@Override
	public Double execute() {

		Double left_value = opnd1.execute();
		Double right_value = opnd2.execute();

		Double value;

		switch (op) {
		case MAS:
			value = left_value + right_value;
			break;
		case RESTA:
			value = left_value - right_value;
			break;
		case MUL:
			value = left_value * right_value;
			break;
		case DIV:
			if (right_value != 0) {
				value = left_value / right_value;
			} else {
				value = null;
				ErrorEjecucion error = new ErrorEjecucion(this, TipoError.DIV_POR_0);
				error.gestionaError();
			}
			break;
		case IGUAL:
			value = (left_value.equals(right_value)) ? 1.0 : 0.0;
			break;
		case MAYOR:
			value = (left_value > right_value) ? 1.0 : 0.0;
			break;
		case MENOR:
			value = (left_value < right_value) ? 1.0 : 0.0;
			break;
		case MAYORIGUAL:
			value = (left_value >= right_value) ? 1.0 : 0.0;
			break;
		case MENORIGUAL:
			value = (left_value <= right_value) ? 1.0 : 0.0;
			break;
		case AND:
			value = ((left_value != 0.0) && (right_value != 0.0)) ? 1.0 : 0.0;
			break;
		case OR:
			value = ((left_value != 0.0) || (right_value != 0.0)) ? 1.0 : 0.0;
			break;
		default:
			value = 0.0; // Valor por defecto
			break;
		}

		return value;
	}

}

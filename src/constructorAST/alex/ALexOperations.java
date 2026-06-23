package constructorAST.alex;

import constructorAST.constructorast.ClaseLexica;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;

	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	public UnidadLexica unidadId() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDEN, alex.lexema());
	}

	public UnidadLexica unidadIf() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF);
	}

	public UnidadLexica unidadElse() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE);
	}

	public UnidadLexica unidadWhile() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE);
	}

	public UnidadLexica unidadFor() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FOR, alex.lexema());
	}

	public UnidadLexica unidadEnt() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENT, alex.lexema());
	}

	public UnidadLexica unidadReal() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REAL, alex.lexema());
	}

	public UnidadLexica unidadTipoEnt() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_ENT);
	}

	public UnidadLexica unidadTipoBool() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_BOOL);
	}

	public UnidadLexica unidadTipoReal() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_REAL);
	}

	public UnidadLexica unidadTrue() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE);
	}

	public UnidadLexica unidadFalse() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE);
	}

	public UnidadLexica unidadSuma() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS);
	}

	public UnidadLexica unidadResta() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RESTA);
	}

	public UnidadLexica unidadMul() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POR);
	}

	public UnidadLexica unidadDiv() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV);
	}

	public UnidadLexica unidadIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL);
	}

	public UnidadLexica unidadIgualIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUALIGUAL);
	}

	public UnidadLexica unidadMayor() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
	}

	public UnidadLexica unidadMenor() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR);
	}

	public UnidadLexica unidadMayorIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL);
	}

	public UnidadLexica unidadMenorIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL);
	}

	public UnidadLexica unidadTambienBool() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TAMBIENBOOL);
	}

	public UnidadLexica unidadOBool() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OBOOL);
	}

	public UnidadLexica unidadPAp() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP);
	}

	public UnidadLexica unidadPCierre() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE);
	}

	public UnidadLexica unidadCAp() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CAP);
	}

	public UnidadLexica unidadCCierre() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CCIERRE);
	}

	public UnidadLexica unidadinterCierre() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INTERCIERRE);
	}

	public UnidadLexica unidadinterApertura() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INTERAPERTURA);
	}

	public UnidadLexica unidadPuntoComa() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOCOMA);
	}

	public UnidadLexica unidadDosPuntos() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOSPUNTOS);
	}
	public UnidadLexica unidadBarrabaja() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BARRABAJA);
	}

	public UnidadLexica unidadEof() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF);
	}
	
}

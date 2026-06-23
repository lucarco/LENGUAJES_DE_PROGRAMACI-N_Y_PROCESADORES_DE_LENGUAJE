package constructorAST.constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import constructorAST.alex.AnalizadorLexicoTiny;
import constructorAST.ast.Instruccion;
import constructorAST.ast.TableStack;
import constructorAST.ast.Declaracion;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.util.LinkedList;
import constructorAST.errors.ErrorSemantico;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("=== INICIANDO ANALIZADOR SINTACTICO(constructorAST.constructorast.Main) ===");
		System.out.println("Archivo de entrada: " + args[0]);
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		ConstructorAST constructorast = new ConstructorAST(alex);
		LinkedList<Instruccion> program = (LinkedList<Instruccion>) constructorast.parse().value;
		TableStack infoBinding = new TableStack();

		// Analisis de identificadores
		LinkedList<ErrorSemantico> errores = new LinkedList();
		for (Instruccion i : program) {
			i.bind(infoBinding, errores);
		}

		if (errores.size() != 0) {
			for (ErrorSemantico e : errores) {
				System.out.println(e + "\n");
			}
			System.exit(1);
		}

		// Analisis de tipos
		for (Instruccion i : program) {
			i.checkTypes(errores);
		}
		if (errores.size() != 0) {
			for (ErrorSemantico e : errores) {
				System.out.println(e + "\n");
			}
			System.exit(1);
		}

		System.out.println("========== ANALISIS PARA DORY(0 ASIGNACIONES) Y ESTRELLA(>=3 ASIGNACIONES) ============");
		for (Instruccion i : program) {
			i.analisis_cartas();
		}
		
		Set<Map.Entry<String, Declaracion>> analisis_variables = infoBinding.peek().entrySet();
		for (Map.Entry<String, Declaracion> entry : analisis_variables) {
			Declaracion decl = entry.getValue();
			int usos = decl.getAsignaciones();
			String nombre = entry.getKey();

			// DORY (0 usos)
			if (usos == 0) {
				System.out.println("Variable '" + nombre + "' es tipo DORY (Declarada pero nunca asignada).");
			}
			// ESTRELLA (3 o más usos)
			if (usos >= 3) {
				System.out.println("Variable '" + nombre + "' es una ESTRELLA (Asignada " + usos + " veces).");
			}
		}

		// Ejecución / generación código
		System.out.println("===========EMPIEZA LA EJECUCION DEL PROGRAMA ==========");
		for (Instruccion i : program) {
			i.execute();
		}

		// Para mostrar la asignación final de las variables
		Set<Map.Entry<String, Declaracion>> variables_vivas = infoBinding.peek().entrySet();
		for (Map.Entry<String, Declaracion> entry : variables_vivas) {
			Declaracion nodo = entry.getValue();
			System.out.println("Variable  " + entry.getKey() + " -> " + nodo.getValue());
		}
		System.out.println("=======FIN DEL MAIN========");
	}
}

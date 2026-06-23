package constructorAST.ast;

import java.util.Stack;

public class TableStack {
	private Stack<TablaSimbolos> stack;

	public TableStack() {
		stack = new Stack<>();
		stack.push(new TablaSimbolos());
	}

	public void push() {
		stack.push(new TablaSimbolos());
	}

	public void pop() {
		stack.pop();
	}

	public void add_id(String id, Declaracion ref) {
		stack.peek().add_id(id, ref);
	}

	public boolean contains_id_peek(String id) {
		return stack.peek().contains_id(id);
	}

	public Declaracion lookup(String id) {
		for (int i = stack.size() - 1; i >= 0; --i) {
			TablaSimbolos table = stack.get(i);
			Declaracion symbol = table.lookup(id);
			if (symbol != null) {
				return symbol;
			}
		}
		return null;
	}
	public TablaSimbolos peek() {
		return stack.peek();
	}
}
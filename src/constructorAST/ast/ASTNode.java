package constructorAST.ast;

import java.util.LinkedList;
import constructorAST.errors.ErrorSemantico;
import constructorAST.errors.TipoError;

public interface ASTNode {
	// public void type(); // for the future
	// public ?? generateCode() // for the future
	public int getFila();

	public int getCol();

	public NodeKind nodeKind();

	public String toString();

	public void bind(TableStack bind_info, LinkedList<ErrorSemantico> errors);

	public void checkTypes(LinkedList<ErrorSemantico> errors);
}

/* Generated By:JJTree: Do not edit this line. ASTStatementExpression.java */

package jrag.AST;

public class ASTStatementExpression extends SimpleNode {
  public ASTStatementExpression(int id) {
    super(id);
  }

  public ASTStatementExpression(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* Generated By:JJTree: Do not edit this line. ASTStatementExpressionList.java */

package jrag.AST;

public class ASTStatementExpressionList extends SimpleNode {
  public ASTStatementExpressionList(int id) {
    super(id);
  }

  public ASTStatementExpressionList(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
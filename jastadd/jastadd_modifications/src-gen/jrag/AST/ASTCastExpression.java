/* Generated By:JJTree: Do not edit this line. ASTCastExpression.java */

package jrag.AST;

public class ASTCastExpression extends SimpleNode {
  public ASTCastExpression(int id) {
    super(id);
  }

  public ASTCastExpression(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}

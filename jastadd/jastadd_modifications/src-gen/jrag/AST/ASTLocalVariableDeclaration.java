/* Generated By:JJTree: Do not edit this line. ASTLocalVariableDeclaration.java */

package jrag.AST;

public class ASTLocalVariableDeclaration extends SimpleNode {
  public ASTLocalVariableDeclaration(int id) {
    super(id);
  }

  public ASTLocalVariableDeclaration(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}

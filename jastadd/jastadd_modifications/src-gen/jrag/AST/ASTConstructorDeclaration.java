/* Generated By:JJTree: Do not edit this line. ASTConstructorDeclaration.java */

package jrag.AST;

public class ASTConstructorDeclaration extends SimpleNode {
  public ASTConstructorDeclaration(int id) {
    super(id);
  }

  public ASTConstructorDeclaration(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}

/* Generated By:JJTree: Do not edit this line. ASTTypeDeclaration.java */

package jrag.AST;

public class ASTTypeDeclaration extends SimpleNode {
  public ASTTypeDeclaration(int id) {
    super(id);
  }

  public ASTTypeDeclaration(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
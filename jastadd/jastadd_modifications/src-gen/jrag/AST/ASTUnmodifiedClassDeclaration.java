/* Generated By:JJTree: Do not edit this line. ASTUnmodifiedClassDeclaration.java */

package jrag.AST;

public class ASTUnmodifiedClassDeclaration extends SimpleNode {
  public ASTUnmodifiedClassDeclaration(int id) {
    super(id);
  }

  public ASTUnmodifiedClassDeclaration(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}

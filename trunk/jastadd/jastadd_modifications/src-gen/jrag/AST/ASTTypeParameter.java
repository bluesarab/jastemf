/* Generated By:JJTree: Do not edit this line. ASTTypeParameter.java */

package jrag.AST;

public class ASTTypeParameter extends SimpleNode {
  public ASTTypeParameter(int id) {
    super(id);
  }

  public ASTTypeParameter(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
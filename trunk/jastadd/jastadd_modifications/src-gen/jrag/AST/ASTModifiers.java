/* Generated By:JJTree: Do not edit this line. ASTModifiers.java */

package jrag.AST;

public class ASTModifiers extends SimpleNode {
  public ASTModifiers(int id) {
    super(id);
  }

  public ASTModifiers(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
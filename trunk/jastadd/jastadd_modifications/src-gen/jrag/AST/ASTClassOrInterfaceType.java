/* Generated By:JJTree: Do not edit this line. ASTClassOrInterfaceType.java */

package jrag.AST;

public class ASTClassOrInterfaceType extends SimpleNode {
  public ASTClassOrInterfaceType(int id) {
    super(id);
  }

  public ASTClassOrInterfaceType(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
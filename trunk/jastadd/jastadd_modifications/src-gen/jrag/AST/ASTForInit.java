/* Generated By:JJTree: Do not edit this line. ASTForInit.java */

package jrag.AST;

public class ASTForInit extends SimpleNode {
  public ASTForInit(int id) {
    super(id);
  }

  public ASTForInit(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
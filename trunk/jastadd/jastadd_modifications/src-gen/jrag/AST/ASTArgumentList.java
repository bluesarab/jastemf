/* Generated By:JJTree: Do not edit this line. ASTArgumentList.java */

package jrag.AST;

public class ASTArgumentList extends SimpleNode {
  public ASTArgumentList(int id) {
    super(id);
  }

  public ASTArgumentList(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
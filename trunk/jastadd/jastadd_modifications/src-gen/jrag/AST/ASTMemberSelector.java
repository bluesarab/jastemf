/* Generated By:JJTree: Do not edit this line. ASTMemberSelector.java */

package jrag.AST;

public class ASTMemberSelector extends SimpleNode {
  public ASTMemberSelector(int id) {
    super(id);
  }

  public ASTMemberSelector(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
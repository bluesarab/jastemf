/* Generated By:JJTree: Do not edit this line. ASTSwitchStatement.java */

package jrag.AST;

public class ASTSwitchStatement extends SimpleNode {
  public ASTSwitchStatement(int id) {
    super(id);
  }

  public ASTSwitchStatement(JragParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JragParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
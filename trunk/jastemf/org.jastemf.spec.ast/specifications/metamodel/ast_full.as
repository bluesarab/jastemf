ABSTRACTSYNTAX ast
URI <http://www.jastemf.org/spec/ast>
PREFIX jastemf.spec.ast


Grammar = typeDecl:TypeDecl*;

TypeDecl = ClassDecl | InterfaceDecl | ASTDecl;
TypeDecl = idDecl:IdDecl, classBodyDecls:ClassBodyDecl*, synDecl:SynDecl*, synEq:SynEq*, inhDecl:InhDecl*, inh:InhEq*, components:Components*, collDecl:CollDecl*, 
@FileName:EString, @StartLine:EInt, @EndLine:EInt, @Comment:EString;

ClassDecl;

InterfaceDecl;

ASTDecl = abstract:Abstract?, idDecl:IdDecl, SuperClass:IdUse?, components:Components*,
  synDecl:SynDecl*, synEq:SynEq*, inhDecl:InhDecl*, inhEq:InhEq*, classBodyDecl:ClassBodyDecl*, rewrite:Rewrite*, collDecl:CollDecl*, collEq:CollEq*,
  @FileName:EString, @StartLine:EInt, @EndLine:EInt, @Comment:EString;

Abstract;

AttrDecl = SynDecl | InhDecl | CollDecl;
AttrDecl = parameter:Parameter*, @Name:EString, @Type:EString, @Lazy:EBoolean, @FileName:EString, @StartLine:EInt, @EndLine:EInt, @Final:EBoolean, @NTA:EBoolean, @Comment:EString, @AspectName:EString;
SynDecl;

InhDecl;

CollDecl = @Target:EString;

Parameter = @Type:EString, @Name:EString;

AttrEq = SynEq | InhEq | CollEq;

AttrEq = parameter:Parameter*, @Name:EString, @FileName:EString, @StartLine:EInt, @EndLine:EInt, @Comment:EString, @AspectName:EString;
SynEq = parameter:Parameter*, @Name:EString, @FileName:EString, @StartLine:EInt, @EndLine:EInt, @Comment:EString, @AspectName:EString; % RightHandSide?
InhEq = parameter:Parameter*, @Name:EString, @FileName:EString, @StartLine:EInt, @EndLine:EInt, @Comment:EString, @AspectName:EString, @SonName:EString, Index:Parameter?; % RightHandSide?
CollEq = parameter:Parameter*, @Name:EString, @FileName:EString, @StartLine:EInt, @EndLine:EInt, @Comment:EString, @AspectName:EString, contribution:Contribution*, @TargetName:EString, @TargetAttributeName:EString, @RefSet:EBoolean, @Reference:EString;
Contribution = @Value:EString, @Condition:EString;

ClassBodyDecl = @Name:EString, @FileName:EString, @StartLine:EInt, @EndLine:EInt, @AspectName:EString;

Rewrite = RewriteList;
Rewrite = @FileName:EString, @StartLine:EInt, @EndLine:EInt;
RewriteList;

Components = ListComponents | OptionalComponent | TokenComponent | AggregateComponents;
ListComponents = id:Id;
ListComponents = ListComponentsNTA;
ListComponentsNTA;
OptionalComponent = id:Id;
OptionalComponent = OptionalComponentNTA;
OptionalComponentNTA;
TokenComponent = tokenId:TokenId;
TokenComponent = TokenComponentNTA;
TokenComponentNTA;
AggregateComponents = id:Id;
AggregateComponents = AggregateComponentsNTA;
AggregateComponentsNTA;

Id = nameNode:NameNode?, idUse:IdUse;

NameNode = @ID:EString;

IdUse = @ID:EString;

IdDecl = @ID:EString;

TokenId = @ID:EString,  @TYPE:EString;
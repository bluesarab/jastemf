ABSTRACTSYNTAX ast
URI <http://www.jastemf.org/spec/ast>
PREFIX jastemf.spec.ast

Grammar = typeDecl:TypeDecl*;

TypeDecl =  ASTDecl;
TypeDecl = @FileName:String, @StartLine:int, @EndLine:int, @Comment:String;
 
ASTDecl = abstract:Abstract?, idDecl:IdDecl, SuperClass:IdUse?, components:Components*;

Abstract;

Components = ListComponents | OptionalComponent | TokenComponent | AggregateComponents;
ListComponents = id:Id;
ListComponents = ListComponentsNTA;
ListComponentsNTA;

OptionalComponent = OptionalComponentNTA;
OptionalComponent = id:Id;

OptionalComponentNTA;
TokenComponent = tokenId:TokenId;
TokenComponent = TokenComponentNTA;
TokenComponentNTA;
AggregateComponents = id:Id;
AggregateComponents = AggregateComponentsNTA;
AggregateComponentsNTA;

Id = nameNode:NameNode?, idUse:IdUse;

NameNode = @ID:String;

IdUse = @ID:String;

IdDecl = @ID:String;

TokenId = @ID:String,  @TYPE:String;

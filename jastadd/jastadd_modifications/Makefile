ANTPATH=./tools/ant.jar;./tools/ant-launcher.jar
ASPECTJ="tools/aspectj/bin/ajc" -cp "./tools/aspectj/lib/aspectjrt.jar;$(ANTPATH)" -d $(BUILD)
JAVACC=java -classpath tools/JavaCC.jar org.javacc.parser.Main -JDK_VERSION=1.4
JJTREE=java -classpath tools/JavaCC.jar org.javacc.jjtree.Main -JDK_VERSION=1.4
JASTADD=java -jar tools/jastadd2.jar
SOURCE=./src
TARGET=./src-gen
BUILD=./build-bin

JRAGFILES=$(SOURCE)/ast/ClassRelations.jrag $(SOURCE)/ast/ComponentsUtil.jrag $(SOURCE)/ast/Errorcheck.jrag $(SOURCE)/ast/JaddCodeGen.jrag $(SOURCE)/ast/ContainmentGen.jadd $(SOURCE)/ast/StateBodyGen.jadd $(SOURCE)/ast/ASTNodeBodyGen.jadd $(SOURCE)/ast/NameBinding.jrag \
 $(SOURCE)/jrag/Attributes.jrag $(SOURCE)/jrag/Circular.jrag $(SOURCE)/jrag/CollectionAttributes.jrag $(SOURCE)/jrag/Errorcheck.jrag $(SOURCE)/jrag/JragCodeGen.jrag $(SOURCE)/jrag/NameBinding.jrag

all : ast/AST/Grammar.java ast/AST/Ast.java jrag/AST/JragParser.java 
	$(ASPECTJ) -sourceroots "$(TARGET);$(SOURCE)"

ast/AST/Ast.java : ast/AST/Ast.jj
	$(JAVACC) -OUTPUT_DIRECTORY=$(TARGET)/ast/AST  $(TARGET)/ast/AST/Ast.jj

ast/AST/Ast.jj : $(SOURCE)/ast/Ast.jjt
	$(JJTREE) -OUTPUT_DIRECTORY=$(TARGET)/ast/AST -NODE_PREFIX=\"\"  $(SOURCE)/ast/Ast.jjt

ast/AST/Grammar.java : $(SOURCE)/ast/Ast.ast $(JRAGFILES)
	$(JASTADD) --o=$(TARGET) --jjtree --rewrite --grammar=Ast --package=ast.AST $(JRAGFILES)  $(SOURCE)/ast/Ast.ast
  
jrag/AST/JragParser.java : jrag/AST/Jrag.jj
	$(JAVACC) -OUTPUT_DIRECTORY=$(TARGET)/jrag/AST  $(TARGET)/jrag/AST/Jrag.jj

jrag/AST/Jrag.jj : $(SOURCE)/jrag/Jrag.jjt
	$(JJTREE) -OUTPUT_DIRECTORY=$(TARGET)/jrag/AST  $(SOURCE)/jrag/Jrag.jjt

jar : all unzip-aj
	cp manifest LICENSE $(BUILD)
	jar -cmf manifest jastadd2.jar -C $(BUILD) ast -C $(BUILD) LICENSE -C $(BUILD) jastadd -C $(BUILD) emf -C $(BUILD) jrag  -C $(BUILD) org/aspectj/lang  -C $(BUILD) org/aspectj/runtime 

	
unzip-aj : 
	unzip ./tools/aspectj/lib/aspectjrt.jar -d $(BUILD)

source-jar :
	jar -cmf manifest jastadd2-src.jar LICENSE manifest $(SOURCE)/ast/*.aj $(SOURCE)/ast/*.ast $(SOURCE)/ast/*.jjt $(SOURCE)/ast/*.jrag \
	 $(SOURCE)/emf/*.aj $(SOURCE)/jastadd/*.aj $(SOURCE)/jastadd/*.java $(SOURCE)/jastadd/*.jrag $(SOURCE)/jrag/*.aj $(SOURCE)/jrag/*.java \
	 $(SOURCE)/jrag/*.jrag $(SOURCE)/jrag/*.jjt $(SOURCE)/jrag/AST/*.java Makefile newrelease tools/*.jar
	mkdir jastadd2-src
	cd jastadd2-src && jar -xf ../jastadd2-src.jar && cd ..
	jar -cmf manifest jastadd2-src.jar jastadd2-src
	rm -rf jastadd2-src

bootstrap : jar
	cp -f jastadd2.jar tools/

release: jar source-jar
	@echo "new release compiled into jastadd2.jar and jastadd2-src.jar"

clean :
	rm -r $(BUILD)
	rm -r $(TARGET)/ast
	rm -r $(TARGET)/jrag

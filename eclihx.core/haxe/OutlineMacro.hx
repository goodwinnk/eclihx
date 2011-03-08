import haxe.macro.Expr;
import haxe.macro.Context;
import haxe.macro.Type;
import neko.io.File;

class OutlineMacro {	
	public static function main() {
		OutlineMacro.traceClassInfo();
	}

	@:macro public static function traceClassInfo() {
	
		function getFieldXml(field: ClassField) {
			var fieldNode = Xml.createElement("member");
			fieldNode.set("name", field.name);
			fieldNode.set("isPublic", Std.string(field.isPublic));
			switch (field.kind) {
				case FieldKind.FMethod(_):
					fieldNode.set("type", "method");
				case FieldKind.FVar(_, _):
					fieldNode.set("type", "field");
			}		
			
			return fieldNode;
		} 

		function getTypeXml(type: Type) : Xml {
			var typeNode = Xml.createElement("type");
			
			switch (type) {
				case TInst(classTypeRef, typeParams):
					typeNode.set("name", classTypeRef.get().name);
					
					var fieldsNode = Xml.createElement("members");
					typeNode.addChild(fieldsNode);
				
					for (field in classTypeRef.get().fields.get()) {
						fieldsNode.addChild(getFieldXml(field));
	    			}
	    			
	    			var genericParamsNode = Xml.createElement("typeparams");
	    			typeNode.addChild(genericParamsNode);
					
					// Need a way to read a parameter name
					for (typeParam in typeParams) {
						var typeParamNode = Xml.createElement("param");
						typeParamNode.set("name", "test");
						genericParamsNode.addChild(typeParamNode);
					}
				default:
					trace("Not finished yet");
					trace(type);
			}
			
			return typeNode;
		};
		
		function getModuleXml(moduleName: String) : Xml {
			var moduleNode = Xml.createElement("module");
			moduleNode.set("file", moduleName);
			
			for (type in Context.getModule(moduleName)) {
				moduleNode.addChild(getTypeXml(type));
			}
			
			return moduleNode;
		}
		
		var xmlDocument = Xml.createDocument();
		xmlDocument.addChild(getModuleXml("neko.FileSystem"));
		
		var file = File.write("outline.xml", false);
		file.writeString(xmlDocument.toString());
		file.close();
		
		return { expr : EBlock(new Array()), pos : Context.currentPos() };
    }
}
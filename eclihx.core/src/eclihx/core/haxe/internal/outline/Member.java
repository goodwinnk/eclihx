package eclihx.core.haxe.internal.outline;

import javax.xml.bind.annotation.XmlAttribute;

public class Member {
	private String name;
	private boolean isPublic;
	private String type;
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}	
	
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	@XmlAttribute(name="isPublic")
	public boolean isPublic() {
		return isPublic;
	}	
	
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlAttribute(name="name")
	public String getType() {
		return type;
	}
}

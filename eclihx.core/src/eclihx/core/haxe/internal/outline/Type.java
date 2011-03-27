package eclihx.core.haxe.internal.outline;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="type")
public class Type {
	private String name;
	private List<Member> members;

	@XmlAttribute()
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@XmlElement(name="member")
	@XmlElementWrapper(name="members")
	public List<Member> getMembers() {
		return members;
	}
}

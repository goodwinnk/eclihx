package eclihx.core.haxe.internal.outline;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="module")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Module {	
	
	private List<Type> types;
	
	private String file;

	/**
	 * @return the file
	 */
	@XmlAttribute(name="file")
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}
	
	@XmlElement(name="type")
	public List<Type> getTypes() {
		return types;
	}	
	
	public void setTypes(List<Type> types) {
		this.types = types;
	}


}

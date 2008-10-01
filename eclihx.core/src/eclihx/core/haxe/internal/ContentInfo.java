package eclihx.core.haxe.internal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Objects of this class will store info which was got from the
 * haXe tips processor. Lately this info will be used in 
 * generation CompletionProposal. 
 */
public class ContentInfo {
	
	/**
	 * Separator of the complex types like functions.
	 */
	public final static String COMPLEX_TYPES_SEPARATOR = "->"; 

	/**
	 * Name of the method or field.
	 */
	@XmlAttribute(name="n")
	public String name = "";
	
	/**
	 * Type of the method or field.
	 */
	@XmlElement(name="t")
	public String type = "";
	
	/**
	 * haXe doc for the unit.
	 */
	// TODO 2 check that this is really haXe doc.
	@XmlElement(name="d")
	public String doc = "";
	
	/**
	 * Checks if current info contains description for the function.
	 * 
	 * @return <code>true</code> if current info 
	 * contains description for the function.
	 */
	public boolean isFunction() {
		return (type.indexOf(COMPLEX_TYPES_SEPARATOR) != -1);
	}
}
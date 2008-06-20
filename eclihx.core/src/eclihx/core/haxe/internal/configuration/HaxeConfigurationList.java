package eclihx.core.haxe.internal.configuration;

import java.util.Iterator;
import java.util.LinkedList;

public class HaxeConfigurationList implements Iterable<HaxeConfiguration>{
	
	private final LinkedList<HaxeConfiguration> configs = 
		new LinkedList<HaxeConfiguration>();

	// TODO 9 Know how to return constant value!!!
	
	/**
	 * Gets main configuration. By default it's a first configuration
	 * of the list
	 */
	public HaxeConfiguration getMainConfiguration() throws InvalidConfiguration {
		if (configs.isEmpty()) {
			throw new InvalidConfiguration("Should be at least one configuration");
		}
		
		return configs.getFirst();
	}
	
	/**
	 * @param config
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(HaxeConfiguration config) {
		return configs.add(config);
	}

	/**
	 * @return
	 * @see java.util.AbstractSequentialList#iterator()
	 */
	public Iterator<HaxeConfiguration> iterator() {
		return configs.iterator();
	}	
}

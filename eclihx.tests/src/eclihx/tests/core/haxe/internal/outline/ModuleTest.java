package eclihx.tests.core.haxe.internal.outline;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.core.runtime.FileLocator;
import org.junit.Assert;
import org.junit.Test;

import eclihx.core.haxe.internal.outline.Module;

public class ModuleTest {
	
	@Test
	public void shouldUnmarshalFile() throws JAXBException, MalformedURLException, IOException {
		JAXBContext context = JAXBContext.newInstance(Module.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		String filePath = FileLocator.toFileURL(
				new URL("platform:/plugin/eclihx.tests/Resources/functional/outline.xml")).getPath();
		
		Module module = (Module) unmarshaller.unmarshal(new FileInputStream(filePath));
		Assert.assertEquals("Ski", module.getFile());
		Assert.assertTrue(!module.getTypes().isEmpty());
		Assert.assertTrue(!module.getTypes().get(0).getMembers().isEmpty());
	}	
}

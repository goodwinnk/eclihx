package eclihx.tests.core.haxe.internal.versioning;

import org.eclipse.core.runtime.Assert;
import org.junit.Test;

import eclihx.core.haxe.internal.versioning.HaxeVersion;

public class HaxeVersionTest {
	
	@Test
	public void shouldBeCorrectOrder() {
		Assert.isTrue((new HaxeVersion("2.04")).isLess(new HaxeVersion("2.15")));
	}
	
	@Test
	public void shouldFindCorrectOrderForEqualVersions() {
		HaxeVersion first = new HaxeVersion("2.05");
		HaxeVersion second = new HaxeVersion("2.05");
		
		Assert.isTrue(!first.isLess(second));
		Assert.isTrue(!second.isLess(first));
	}
}

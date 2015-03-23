package com.mockito.test;

import static org.junit.Assert.*;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SuppressSuperConstructor.class)
public class SuppressSuperConstructorTest {

	@Test
	public void supresses_super_class_constructor() {
		suppress(constructor(DontExtendMePlease.class));
		new SuppressSuperConstructor();
		assertTrue("Just checking", true);
	}	
}

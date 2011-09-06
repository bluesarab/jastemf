
package org.jastemf.siple;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class SipleStandaloneSetup extends SipleStandaloneSetupGenerated{

	public static void doSetup() {
		new SipleStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


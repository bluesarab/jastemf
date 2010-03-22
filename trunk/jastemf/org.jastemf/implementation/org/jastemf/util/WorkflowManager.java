/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf.util;

import java.util.*;
import java.net.*;

import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.mwe.core.*;
import org.eclipse.emf.mwe.core.issues.*;
import org.eclipse.emf.mwe.core.monitor.*;
import org.eclipse.emf.mwe.core.resources.*;

import org.jastemf.*;

/**
 * Support class for <i>OAW</i> workflow
 * {@link #executeWorkflow(IIntegrationContext, String) execution}.
 * 
 * @author S. Karol
 */
final public class WorkflowManager {
	private final static String WORKFLOW_LOG_SYSTEM_PROPERTY_KEY = "org.apache.commons.logging.Log";
	private final static String WORKFLOW_LOG_CLASSNAME = "org.apache.commons.logging.impl.SimpleLog";

	/**
	 * Executes an <i>OAW</i> workflow.
	 * 
	 * @param context
	 *            Integration information required by the workflow and its
	 *            templates.
	 * @param workflow
	 *            The workflow to execute.
	 * @throws JastEMFException
	 *             Thrown, if the workflow's execution failed.
	 */
	public static void executeWorkflow(IIntegrationContext context,
			String workflow) throws JastEMFException {

		OsgiResourceLoader resourceLoader = new OsgiResourceLoader(
				"org.jastemf", IntegrationManager.class.getClassLoader());
		executeWorkflow(workflow, context, resourceLoader);

	}

	public static void executeWorkflow(String workflow,
			IIntegrationContext context, ResourceLoader resourceLoader)
			throws JastEMFException {
		if (context.genmodel().getGenPackages().size() == 0) {
			throw new JastEMFException(
					"GenModel Error: The model has no GenPackage.");
		}
		Resource eResource = context.genmodel().eResource();
		if (eResource == null) {
			throw new JastEMFException(
					"GenModel Error: GenModel has no resource.");
		}

		String srcGenPath = URI.create( // Workaround: OAW file URI bug
				context.srcfolder().toString()).getPath();
		String modelFile = eResource.getURI().toString();

		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("srcGenPath", srcGenPath);
		properties.put("modelFile", modelFile);
		properties.put("outPackage", context.outpackage());
		properties.put("astPackage", context.astpackage());

		/* Prepare and execute the workflow */

		System.out.println("Runnig workflow [" + workflow + "] on model ["
				+ modelFile + "] with target [" + srcGenPath + "].");

		executeWorkflow(workflow, properties, resourceLoader);

	}

	public static void executeWorkflow(String workflow,
			Map<String, String> properties, ResourceLoader resourceLoader)
			throws JastEMFException {
		try {

			ResourceLoaderFactory
					.setCurrentThreadResourceLoader(resourceLoader);
			System.setProperty(WORKFLOW_LOG_SYSTEM_PROPERTY_KEY,
					WORKFLOW_LOG_CLASSNAME);

			WorkflowRunner runner = new WorkflowRunner();
			boolean prepareSuccess = runner.prepare(workflow,
					new NullProgressMonitor(), properties);
			if (!prepareSuccess) {
				throw new JastEMFException(
						"Template Error: Could not prepare workflow ["
								+ workflow + "].");
			}
			Issues issues = new IssuesImpl();
			boolean success = runner.executeWorkflow(
					new HashMap<String, Object>(), issues);
			if (!success) {
				throw new JastEMFException("Template Error: Workflow ["
						+ workflow + "] failed.");
			}
		} finally {
			ResourceLoaderFactory.setCurrentThreadResourceLoader(null);
			System.clearProperty(WORKFLOW_LOG_SYSTEM_PROPERTY_KEY);
		}

	}
}

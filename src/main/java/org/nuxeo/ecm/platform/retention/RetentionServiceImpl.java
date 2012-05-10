/*
 * (C) Copyright 2012 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     ben
 */

package org.nuxeo.ecm.platform.retention;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.runtime.model.ComponentContext;
import org.nuxeo.runtime.model.ComponentInstance;
import org.nuxeo.runtime.model.DefaultComponent;

/**
 * @author ben
 */
public class RetentionServiceImpl extends DefaultComponent implements
        RetentionService {
    private static final Log log = LogFactory.getLog(RetentionServiceImpl.class);

    public static final String RULE_DEFINITION_EP = "ruleDefinition";

    protected static final Map<String, RuleDefinitionDescriptor> ruleDefinitions = new HashMap<String, RuleDefinitionDescriptor>();

    protected static final List<String> names = new ArrayList<String>();

    @Override
    public void registerContribution(Object contribution,
            String extensionPoint, ComponentInstance contributor)
            throws Exception {
        if (RULE_DEFINITION_EP.equals(extensionPoint)
                && contribution instanceof RuleDefinitionDescriptor) {
            log.debug("Registering contribution...");
            RuleDefinitionDescriptor desc = (RuleDefinitionDescriptor) contribution;
            String name = desc.getName();
            ruleDefinitions.put(name, desc);
            if (names.contains(name)) {
                log.warn("Overriding existing rule definition: " + name);
            }
            names.add(name);
            log.info("Contribution " + name + " registered.");
        }
    }

    @Override
    public void activate(ComponentContext context) {
        log.debug("Activate component.");
    }

    @Override
    public void deactivate(ComponentContext context) {
        log.debug("Deactivate component.");
        names.clear();
        ruleDefinitions.clear();
    }

    @Override
    public void applicationStarted(ComponentContext context) throws Exception {
        log.debug("Application started");
    }

    // API ---------------------------------------------------

    @Override
    public List<String> getRuleDefinitionNames() {
        return names;
    }

    @Override
    public RuleDefinitionDescriptor getRuleDefinition(String name) {
        return ruleDefinitions.get(name);
    }

}

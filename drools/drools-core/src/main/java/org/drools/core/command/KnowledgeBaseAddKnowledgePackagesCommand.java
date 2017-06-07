/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.core.command;

import org.drools.core.command.impl.ExecutableCommand;
import org.drools.core.command.impl.RegistryContext;
import org.drools.core.impl.InternalKnowledgeBase;
import org.kie.api.KieBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.api.runtime.Context;

public class KnowledgeBaseAddKnowledgePackagesCommand
    implements
    ExecutableCommand<Void> {

    public KnowledgeBaseAddKnowledgePackagesCommand() {
    }

    public Void execute(Context context) {
        KnowledgeBuilder kbuilder = ((RegistryContext) context).lookup(KnowledgeBuilder.class);
        if (kbuilder.hasErrors()) {
            throw new IllegalStateException("There are rule compilation errors:\n" + kbuilder.getErrors().toString());
        }
        InternalKnowledgeBase kbase = (InternalKnowledgeBase) ((RegistryContext) context).lookup(KieBase.class);
        kbase.addPackages( kbuilder.getKnowledgePackages() );
        return null;
    }

}

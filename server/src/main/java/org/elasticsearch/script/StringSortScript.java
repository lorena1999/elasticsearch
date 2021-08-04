/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */
package org.elasticsearch.script;

import java.io.IOException;
import java.util.Map;

public abstract class StringSortScript extends AbstractSortScript {

    public static final String[] PARAMETERS = {};

    public static final ScriptContext<Factory> CONTEXT = new ScriptContext<>("string_sort", Factory.class);

    public StringSortScript(Map<String, Object> params, DocReader docReader) {
        super(params, docReader);
    }

    public abstract String execute();

    /**
     * A factory to construct {@link StringSortScript} instances.
     */
    public interface LeafFactory {
        StringSortScript newInstance(DocReader reader) throws IOException;
    }

    /**
     * A factory to construct stateful {@link StringSortScript} factories for a specific index.
     */
    public interface Factory extends ScriptFactory {
        LeafFactory newFactory(Map<String, Object> params);
    }

    public static class FieldAccess {
        private final StringSortScript script;

        public FieldAccess(StringSortScript script) {
            this.script = script;
        }

        public Field<?> field(String fieldName) {
            return script.field(fieldName);
        }
    }
}

/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.script;

import org.elasticsearch.common.Nullable;
import org.elasticsearch.search.lookup.SearchLookup;

import java.io.Closeable;
import java.util.Map;

/**
 * A script language implementation.
 */
public interface ScriptEngine extends Closeable {

    /**
     * The language name used in the script APIs to refer to this scripting backend.
     */
    String getType();

    /**
     * Compiles a script.
     * @param name the name of the script. {@code null} if it is anonymous (inline). For a stored script, its the identifier.
     * @param code actual source of the script
     * @param params compile-time parameters (such as flags to the compiler)
     * @return an opaque compiled script which may be cached and later passed to
     */
    Object compile(String name, String code, Map<String, String> params);

    ExecutableScript executable(CompiledScript compiledScript, @Nullable Map<String, Object> vars);

    SearchScript search(CompiledScript compiledScript, SearchLookup lookup, @Nullable Map<String, Object> vars);
}

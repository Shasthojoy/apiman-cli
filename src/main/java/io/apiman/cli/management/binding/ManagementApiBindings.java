/*
 * Copyright 2017 Pete Cornish
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apiman.cli.management.binding;

import io.apiman.cli.command.common.model.ManagementApiVersion;

/**
 * Convenience methods for instantiating {@link ManagementApiBinding}s.
 *
 * @author Pete Cornish {@literal <outofcoffee@gmail.com>}
 */
public class ManagementApiBindings {
    public static ManagementApiBinding boundTo(Class<?> apiClass) {
        return new ManagementApiBindingImpl(apiClass);
    }

    public static ManagementApiBinding boundTo(Class<?> apiClass, ManagementApiVersion serverVersion) {
        return new ManagementApiBindingImpl(apiClass, serverVersion);
    }
}

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

package io.apiman.cli.management;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import io.apiman.cli.command.api.factory.Version11XManagementApiFactoryImpl;
import io.apiman.cli.command.api.factory.Version12XManagementApiFactoryImpl;
import io.apiman.cli.command.api.VersionAgnosticApi;
import io.apiman.cli.command.common.ActionApi;
import io.apiman.cli.command.common.model.ManagementApiVersion;
import io.apiman.cli.command.gateway.GatewayApi;
import io.apiman.cli.command.org.OrgApi;
import io.apiman.cli.command.plugin.PluginApi;
import io.apiman.cli.management.binding.ManagementApiBindings;
import io.apiman.cli.management.factory.ManagementApiFactory;
import io.apiman.cli.management.factory.SimpleManagementApiFactoryImpl;
import io.apiman.cli.management.api.StatusApi;

/**
 * Bindings for Management API factories.
 *
 * @author Pete Cornish {@literal <outofcoffee@gmail.com>}
 */
public class ManagementApiFactoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ManagementApiFactory.class)
                .annotatedWith(ManagementApiBindings.boundTo(StatusApi.class))
                .toInstance(new SimpleManagementApiFactoryImpl<>(StatusApi.class));

        bind(ManagementApiFactory.class)
                .annotatedWith(ManagementApiBindings.boundTo(GatewayApi.class))
                .toInstance(new SimpleManagementApiFactoryImpl<>(GatewayApi.class));

        bind(ManagementApiFactory.class)
                .annotatedWith(ManagementApiBindings.boundTo(OrgApi.class))
                .toInstance(new SimpleManagementApiFactoryImpl<>(OrgApi.class));

        bind(ManagementApiFactory.class)
                .annotatedWith(ManagementApiBindings.boundTo(PluginApi.class))
                .toInstance(new SimpleManagementApiFactoryImpl<>(PluginApi.class));

        bind(ManagementApiFactory.class)
                .annotatedWith(ManagementApiBindings.boundTo(ActionApi.class))
                .toInstance(new SimpleManagementApiFactoryImpl<>(ActionApi.class));

        bind(ManagementApiFactory.class)
                .annotatedWith(ManagementApiBindings.boundTo(VersionAgnosticApi.class, ManagementApiVersion.v11x))
                .to(Version11XManagementApiFactoryImpl.class).in(Singleton.class);

        bind(ManagementApiFactory.class)
                .annotatedWith(ManagementApiBindings.boundTo(VersionAgnosticApi.class, ManagementApiVersion.v12x))
                .to(Version12XManagementApiFactoryImpl.class).in(Singleton.class);
    }
}

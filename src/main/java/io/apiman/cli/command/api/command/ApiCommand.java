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

package io.apiman.cli.command.api.command;

import io.apiman.cli.command.core.AbstractCommand;
import io.apiman.cli.command.core.Command;
import io.apiman.cli.service.ManagementApiService;

import javax.inject.Inject;
import java.util.Map;

/**
 * Root Command for managing APIs.
 *
 * @author Pete Cornish {@literal <outofcoffee@gmail.com>}
 */
public class ApiCommand extends AbstractCommand {
    @Inject
    public ApiCommand(ManagementApiService managementApiService) {
        super(managementApiService);
    }

    @Override
    protected void populateCommands(Map<String, Class<? extends Command>> commandMap) {
        commandMap.put("create", ApiCreateCommand.class);
        commandMap.put("list", ApiListCommand.class);
        commandMap.put("publish", ApiPublishCommand.class);
        commandMap.put("policy", ApiPolicyCommand.class);
        commandMap.put("definition", ApiDefinitionCommand.class);
    }

    @Override
    protected String getCommandDescription() {
        return "Manage APIs";
    }
}

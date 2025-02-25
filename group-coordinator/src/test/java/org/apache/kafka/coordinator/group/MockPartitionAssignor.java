/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.coordinator.group;

import org.apache.kafka.common.Uuid;
import org.apache.kafka.coordinator.group.assignor.GroupSpec;
import org.apache.kafka.coordinator.group.assignor.GroupAssignment;
import org.apache.kafka.coordinator.group.assignor.PartitionAssignor;
import org.apache.kafka.coordinator.group.assignor.PartitionAssignorException;
import org.apache.kafka.coordinator.group.assignor.SubscribedTopicDescriber;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MockPartitionAssignor implements PartitionAssignor {
    private final String name;
    private GroupAssignment prepareGroupAssignment = null;

    MockPartitionAssignor(String name) {
        this.name = name;
    }

    public void prepareGroupAssignment(GroupAssignment prepareGroupAssignment) {
        this.prepareGroupAssignment = prepareGroupAssignment;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public GroupAssignment assign(GroupSpec groupSpec, SubscribedTopicDescriber subscribedTopicDescriber) throws PartitionAssignorException {
        return prepareGroupAssignment;
    }

    public Map<Uuid, Set<Integer>> targetPartitions(String memberId) {
        Objects.requireNonNull(prepareGroupAssignment);
        return prepareGroupAssignment.members().get(memberId).targetPartitions();
    }
}

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.sql.tree;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

public final class PropertiesCharacteristic
        extends RoutineCharacteristic
{
    private final List<Property> properties;

    public PropertiesCharacteristic(NodeLocation location, List<Property> properties)
    {
        super(location);
        this.properties = ImmutableList.copyOf(requireNonNull(properties, "properties is null"));
        checkArgument(!properties.isEmpty(), "properties is empty");
    }

    public List<Property> getProperties()
    {
        return properties;
    }

    @Override
    protected <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitPropertiesCharacteristic(this, context);
    }

    @Override
    public List<? extends Node> getChildren()
    {
        return ImmutableList.of();
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof PropertiesCharacteristic other) &&
                properties.equals(other.properties);
    }

    @Override
    public int hashCode()
    {
        return properties.hashCode();
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("properties", properties)
                .toString();
    }
}

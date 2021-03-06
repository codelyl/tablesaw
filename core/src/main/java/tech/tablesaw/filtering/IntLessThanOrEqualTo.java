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

package tech.tablesaw.filtering;

import tech.tablesaw.api.*;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.ColumnReference;
import tech.tablesaw.util.Selection;

public class IntLessThanOrEqualTo extends ColumnFilter {

    private final int value;

    public IntLessThanOrEqualTo(ColumnReference reference, int value) {
        super(reference);
        this.value = value;
    }

    public Selection apply(Table relation) {
        String name = columnReference.getColumnName();
        Column column = relation.column(name);
        ColumnType type = column.type();
        switch (type) {
            case INTEGER:
                IntColumn intColumn = relation.intColumn(name);
                return intColumn.isLessThanOrEqualTo(value);
            case LONG_INT:
                LongColumn longColumn = relation.longColumn(name);
                return longColumn.isLessThanOrEqualTo(value);
            case SHORT_INT:
                ShortColumn shortColumn = relation.shortColumn(name);
                return shortColumn.isLessThanOrEqualTo(value);
            default:
                throw new UnsupportedOperationException("Columns of type " + type.name() + " do not support the operation "
                        + "lessThanOrEqualTo(anInt) ");
        }
    }
}

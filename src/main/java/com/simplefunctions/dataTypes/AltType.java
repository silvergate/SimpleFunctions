package com.simplefunctions.dataTypes;

import com.simplefunctions.base.IDataType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 23.06.13 Time: 14:12
 */
public class AltType extends DataType {
    private final Set<IDataType> types = new HashSet<>();

    public AltType(IDataType... types) {
        for (final IDataType single : types) {
            this.types.add(single);
        }
    }

    public AltType(Collection<IDataType> types) {
        this.types.addAll(types);
    }

    public Set<IDataType> getTypes() {
        return types;
    }

    @Override
    public boolean isValid(Object data) {
        for (final IDataType dataType : this.types) {
            if (dataType.isValid(data)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AltType altType = (AltType) o;

        if (!types.equals(altType.types)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return types.hashCode();
    }

    @Override
    public String toString() {
        return "AltType{" +
                "types=" + types +
                '}';
    }
}

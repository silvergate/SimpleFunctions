package com.simplefunctions.dataTypes;

import com.simplefunctions.base.IDataType;

import java.util.*;

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

    public static IDataType combine(IDataType t1, IDataType t2) {
        if (t1.getClass().equals(t2.getClass())) {
            return t1.combine(t2);
        } else {
            if (t1 instanceof AltType) {
                final Set<IDataType> newSet =
                        addTypeToSet(((AltType) t1).getTypes(), Collections.singleton(t2));
                return new AltType(newSet);
            }
            if (t2 instanceof AltType) {
                final Set<IDataType> newSet =
                        addTypeToSet(((AltType) t2).getTypes(), Collections.singleton(t1));
                return new AltType(newSet);
            }
            /* There's no alt-type */
            final Set<IDataType> newSet =
                    addTypeToSet(Collections.<IDataType>emptySet(), Arrays.asList(t1, t2));
            return new AltType(newSet);
        }
    }

    @Override
    protected IDataType combineSameType(IDataType type) {
        AltType cast = (AltType) type;
        final Set<IDataType> newSet = addTypeToSet(getTypes(), cast.getTypes());
        return new AltType(newSet);
    }

    private static Set<IDataType> addTypeToSet(Set<IDataType> types, Iterable<IDataType> newTypes) {
        final Map<Class, IDataType> byClass = new HashMap<>();
        for (final IDataType existingType : types) {
            byClass.put(existingType.getClass(), existingType);
        }

        for (IDataType newType : newTypes) {
            if (types.contains(newType)) {
                continue;
            }

            if (byClass.containsKey(newType.getClass())) {
                final IDataType oldOfSameClass = byClass.get(newType.getClass());
                final IDataType combined = oldOfSameClass.combine(newType);
                byClass.put(newType.getClass(), combined);
            } else {
                byClass.put(newType.getClass(), newType);
            }
        }

        final Set<IDataType> resultSet = new HashSet<>();
        resultSet.addAll(byClass.values());
        return resultSet;
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

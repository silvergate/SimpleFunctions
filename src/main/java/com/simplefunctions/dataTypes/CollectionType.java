package com.simplefunctions.dataTypes;

import com.simplefunctions.base.IDataType;
import com.simplefunctions.base.IFunction;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Buran.
 *
 * @author: ${USER} Date: 27.06.13 Time: 19:55
 */
public class CollectionType extends DataType {

    private final boolean ordered;
    private final boolean unique;
    private final int minElements;
    private final int maxElements;
    private final IDataType elementType;

    public CollectionType(boolean ordered, boolean unique, int minElements, int maxElements,
            IDataType elementType) {
        if (ordered && unique) {
            throw new IllegalArgumentException("(ordered && unique)");
        }
        this.ordered = ordered;
        this.unique = unique;
        this.minElements = minElements;
        this.maxElements = maxElements;
        this.elementType = elementType;
    }

    public static CollectionType list(int minElements, int maxElements, IDataType elementType) {
        return new CollectionType(true, false, minElements, maxElements, elementType);
    }

    public boolean isOrdered() {
        return ordered;
    }

    public boolean isUnique() {
        return unique;
    }

    public int getMinElements() {
        return minElements;
    }

    public int getMaxElements() {
        return maxElements;
    }

    public IDataType getElementType() {
        return elementType;
    }

    @Override
    public boolean isValid(Object data) {
        if (!isTypeNotNull(data, Collection.class)) {
            return false;
        }
        final Collection<?> cast = (Collection<?>) data;
        if (cast.size() > this.maxElements) {
            return false;
        }
        if (cast.size() < this.minElements) {
            return false;
        }
        if (isUnique()) {
            if (!isTypeNotNull(data, Set.class)) {
                return false;
            }
        }
        if (isOrdered()) {
            if (!isTypeNotNull(data, List.class)) {
                return false;
            }
        }
        for (final Object element : cast) {
            if (!(element instanceof IFunction)) {
                return false;
            }
            if (!this.elementType.isValid(((IFunction) element).eval())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "CollectionType{" +
                "ordered=" + ordered +
                ", unique=" + unique +
                ", minElements=" + minElements +
                ", maxElements=" + maxElements +
                ", elementType=" + elementType +
                '}';
    }

    @Override
    protected IDataType combineSameType(IDataType type) {
        CollectionType ct = (CollectionType) type;
        if (ct.getElementType().getClass().equals(getElementType().getClass()) &&
                (ct.isUnique() == isUnique()) && (ct.isOrdered() == isOrdered())) {
            /* Combine only collections with same element types, unique and ordered */
            final IDataType newElementType = AltType.combine(ct.getElementType(), getElementType());
            return new CollectionType(isOrdered(), isUnique(),
                    Math.min(ct.getMinElements(), getMinElements()),
                    Math.max(getMaxElements(), getMaxElements()), newElementType);
        } else {
            return new AltType(this, type);
        }
    }
}

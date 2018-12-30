package com.payfort.sdk.parameter;

import java.util.*;

/**
 * Container for all request keys and values
 * values maybe single value or list
 */
public class FortParameter {
    private Map<FortKey, Object> parameter = new HashMap<>();

    public FortParameter add(FortKey fortKey, Object value) {
        if (value != null && parameter.get(fortKey) == null)
            parameter.put(fortKey, value);
        return this;
    }

    public Object get(FortKey fortKey) {
        return parameter.get(fortKey);
    }

    public void iterator(ParameterIterator iterator) {
        parameter.entrySet().stream()
                .sorted((k, v) -> k.getKey().name().compareTo(v.getKey().name()))
                .forEach((e) -> iterator.parameter(e.getKey(), e.getValue()));
    }

    public interface ParameterIterator {
        void parameter(FortKey key, Object value);
    }

    @Override
    public String toString() {
        return "FortParameter{" +
                "parameter=" + parameter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FortParameter parameter1 = (FortParameter) o;
        return Objects.equals(parameter, parameter1.parameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter);
    }
}

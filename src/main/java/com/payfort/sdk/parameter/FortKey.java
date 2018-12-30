package com.payfort.sdk.parameter;

import java.util.Objects;

public class FortKey {

    private final String ketName;

    public FortKey(String ketName) {
        this.ketName = ketName;
    }

    public String name(){
        return ketName;
    }

    @Override
    public String toString() {
        return "FortKey{" +
                "ketName='" + ketName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FortKey fortKey = (FortKey) o;
        return Objects.equals(ketName, fortKey.ketName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ketName);
    }
}

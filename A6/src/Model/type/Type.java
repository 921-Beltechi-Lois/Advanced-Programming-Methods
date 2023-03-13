package Model.type;

import Model.value.Value;

public interface Type {
    boolean equals(Type anotherType);
    Value defaultValue();

    Type deepCopy();
}

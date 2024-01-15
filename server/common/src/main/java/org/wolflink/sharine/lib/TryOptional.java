package org.wolflink.sharine.lib;

import java.util.Optional;
import java.util.function.Supplier;

public class TryOptional {
    public static <T> Optional<T> tryWith(Supplier<T> supplier) {
        T value;
        try {
            value = supplier.get();
        } catch (Exception ignore) {
            value = null;
        }
        return Optional.ofNullable(value);
    }
}

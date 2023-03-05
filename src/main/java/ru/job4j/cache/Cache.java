package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        BiFunction<Integer, Base, Base>  bi = (id, base) -> {
            if (base.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            return new Base(id, base.getVersion() + 1, base.getName());
        };
       return memory.computeIfPresent(model.getId(), bi) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }
}
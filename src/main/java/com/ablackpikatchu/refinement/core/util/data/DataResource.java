package com.ablackpikatchu.refinement.core.util.data;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import net.minecraft.util.ResourceLocation;

public class DataResource<T> {
	private final ResourceLocation objectId;
    private final Function<ResourceLocation, T> getter;

    public DataResource(ResourceLocation id, Function<ResourceLocation, T> getter) {
        this.objectId = id;
        this.getter = getter;
    }

    @Nullable
    private T getNullable() {
        return this.getter.apply(this.objectId);
    }

    public T get() {
        T ret = getNullable();
        Objects.requireNonNull(ret, () -> "Data resource not present: " + this.objectId);
        return ret;
    }

    public ResourceLocation getId() {
        return this.objectId;
    }

    public boolean isPresent() {
        return this.getNullable() != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        T obj = getNullable();
        if (obj != null) {
            consumer.accept(obj);
        }
    }

    public Stream<T> stream() {
        return isPresent() ? Stream.of(get()) : Stream.of();
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        T obj = getNullable();
        return obj != null ? Optional.ofNullable(mapper.apply(obj)) : Optional.empty();
    }
}

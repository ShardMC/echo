package io.shardmc.echo.util;

import java.util.List;

public record EchoPair<T>(T original, List<T> echoes) {

    public EchoPair(T original, List<T> echoes) {
        this.original = original;
        this.echoes = echoes;
    }
}

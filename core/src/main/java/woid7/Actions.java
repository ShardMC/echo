package woid7;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Actions<T> {

    private final List<Consumer<T>> actions = new ArrayList<>();

    public Actions() {

    }

    public void add(Consumer<T> action) {
        this.actions.add(action);
    }

    public List<Consumer<T>> get() {
        return this.actions;
    }
}

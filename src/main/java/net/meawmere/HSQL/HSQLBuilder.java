package net.meawmere.HSQL;

import net.meawmere.HSQL.events.IEventsListener;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HSQLBuilder {
    protected String url;
    protected List<IEventsListener> listeners;
    private HSQLBuilder(String path, List<IEventsListener> listeners){
        this.url = path;
        this.listeners = listeners;
    }

    @NotNull
    @Contract("_ -> new")
    public static HSQLBuilder setURL(String url) {
        return (new HSQLBuilder(url, new ArrayList<>()));
    }

    public HSQLBuilder addEventListener(IEventsListener eventListener) {
        listeners.add(eventListener);
        return (new HSQLBuilder(this.url, this.listeners));
    }

    public HSQL build() {
        return (new HSQL(this.url, this.listeners));
    }
}

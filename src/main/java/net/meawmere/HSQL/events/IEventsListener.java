package net.meawmere.HSQL.events;

import net.meawmere.HSQL.events.source.GET;
import net.meawmere.HSQL.events.source.UPDATE;
import net.meawmere.HSQL.events.source.READY;

public interface IEventsListener {
    void READY(READY event);
    void UPDATE(UPDATE event);
    void GET(GET event);
}

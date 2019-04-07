package net.cofares;

import java.util.HashMap;

/**
 *
 * @author pfares
 */
public class  Source {
    HashMap<GEvent, GEventListener> listListener;
    
    public Source() {
         listListener=new HashMap<>();
    }

    public <D> void addGEventListener(GEvent<D> ev, GEventListener listener) {
       listListener.put(ev,listener);
    }

    public <D> void removeGEventListener(GEvent<D> ev, GEventListener listener) {
       listListener.remove(ev);
    }

    public <D> void dispatch(GEvent<D> ev) {
        listListener.get(ev).action(ev);
    }

    public <D> GEvent<D> genEvent(D d) {
        return new GEvent<>(this, d);
    }
    
}

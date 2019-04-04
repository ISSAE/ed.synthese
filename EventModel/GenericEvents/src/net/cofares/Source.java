package net.cofares;

import java.util.ArrayList;

/**
 *
 * @author pfares
 * @param <D>
 */
public class  Source<D> implements ObservableSource<D> {
    ArrayList<GEventListener> listListener;
    
    public Source() {
         listListener=new ArrayList<>();
    }
    @Override
    public void addGEventListener(GEventListener listener) {
        listListener.add(listener);
    }
    @Override
    public void removeGEventListener(GEventListener listener) {
        listListener.add(listener);
    }
    
    @Override
    public void dispatch(GEvent<Source<D>,D> ev) {
        listListener.forEach((gel) ->  gel.action(ev));
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public GEvent<Source<D>,D> genEvent(Object d) {
        return new GEvent<>(this, (D)d);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares;

/**
 *
 * @author ppfar
 */
public interface ObservableSource<D> {

    void addGEventListener(GEventListener listener);

    void dispatch(GEvent<Source<D>, D> ev);

    @SuppressWarnings(value = "unchecked")
    GEvent<Source<D>, D> genEvent(Object d);

    void removeGEventListener(GEventListener listener);
    
}

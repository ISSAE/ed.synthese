/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares;

/**
 * Le listener l'obsérvé
 * @author pfares
 */
public interface GEventListener<D> {
    /**
     * Dans les shémas de désign pattern cette méthode est appélée update
     * @param <D>
     * @param ev 
     */
    public void action(GEvent<D> ev);
}

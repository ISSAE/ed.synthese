/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares;

/**
 * Evénement entre Observable (source) et Observé (destination)
 * @author Pascal Fares
 * @param <D> type de donnée porté par lé événement
 */
public class GEvent<D> {
  
    private Source _source;
    private D _data;
    
    /**
     * Constricteur dévènement source et donnée
     * @param source
     * @param data 
     */
    public GEvent(Source source, D data) {
        _source = source;
        _data = data;
    }
    
    /**
     * La donné ne nous interesse pas
     * @param source 
     */
    public GEvent(Source source) {
        _source = source;
        _data = null;
    }
    
    /**
     * @return the _source
     */
    public Source getSource() {
        return _source;
    }

    /**
     * @param source
     */
    public void setSource(Source source) {
       _source = source;
    }

    /**
     * @return the _data
     */
    public D getData() {
        return _data;
    }

    /**
     * La source ne nous interesse pas
     * @param data
     */
    public void setData(D data) {
        _data = data;
        _source=null;
    }
    
}

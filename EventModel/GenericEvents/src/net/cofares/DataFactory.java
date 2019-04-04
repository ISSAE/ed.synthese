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
public class DataFactory<D> {
    Class<D> c;
    public DataFactory(Class<D> c) {
        this.c=c;
    }
    
   public D createData() throws InstantiationException, IllegalAccessException {
       return c.newInstance();
   }
    
}

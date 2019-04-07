/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Acer
 */
public class TestEssai {
    Source s;
    String resstr;
    Integer resi;
    public TestEssai() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        s=new Source();
    }
    
    @AfterEach
    public void tearDown() {
    }

   @Test
   public void testEvDispatch() {
        s=new Source();
       System.out.println("testEvDispatch =" + s);
       GEvent<String> evtest = s.genEvent("test");
       System.out.println(evtest);
       GEvent<Integer> evi = s.genEvent(1);
       System.out.println(evi);
       s.addGEventListener(evtest, new GEventListener<String>() {
           @Override
           public void action(GEvent<String> ev) {
               resstr=ev.getData();
           }
           
       });
       s.addGEventListener(evi, new GEventListener<Integer>() {
           @Override
           public void action(GEvent<Integer> ev) {
               resi=ev.getData();
           }
           
       });
       s.dispatch(evi);
       s.dispatch(evtest);
       assertEquals(evtest.getData(), "test");
       assertEquals(evi.getData(), (Integer) 1);
   }
}

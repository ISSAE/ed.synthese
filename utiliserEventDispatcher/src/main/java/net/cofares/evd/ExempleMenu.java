/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.evd;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.cofares.GEvent;
import net.cofares.Source;

/**
 *
 * @author pfares
 */
public class ExempleMenu implements Runnable {
    final Scanner input;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer newValue) {
        Integer oldValue = this.value;
        this.value = newValue;
        this.pcs.firePropertyChange("value", oldValue, newValue);
    }

    BufferedReader in;

    public ExempleMenu() {
         
        in = new BufferedReader(new InputStreamReader(System.in));
        input = new Scanner(in);
    }

    public ExempleMenu(String s) {
        input =new Scanner(s);
    }

    public int menu() throws IOException {

        int selection;
        //Ceci efface en principe un écran (console) Linux et Windows
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        

        /**
         * ************************************************
         */
        System.out.println();
        System.out.println("0 - Quitter");
        System.out.println("-------------------------");
        System.out.println("1 - Créer");
        System.out.println("2 - Mise à jour");
        System.out.println("3 - Suprimer");
        System.out.println("4 - Consulter");
        System.out.print("Choisir : ");

        selection = input.nextInt();
        System.out.println(selection);
        return selection;
    }

    public static void main(String args[]) throws IOException {
        //Les données à injecter la source et l'association interfaceIHM -> ev

        PropertyChangeListener l = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("%s old=%d, new=%d", evt, evt.getOldValue(), evt.getNewValue());
            }

        };
        ExempleMenu em = new ExempleMenu("1 2 3 4 0");
        em.addPropertyChangeListener(l);
        //int r = em.menu();
        //System.out.println("VOus avez choisi " + r);
        new Thread(em).start();
    }

    @Override
    public void run() {
        int choix;

        try {
            while ((choix = menu()) !=0 ){
                setValue(choix);
            }
        } catch (IOException ex) {
            Logger.getLogger(ExempleMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

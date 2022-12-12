package com.cesur.examenaddicc22;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 *
 * @author Carlos Aragon Garcia 77668754X
 */
public class Examen {

    /* Incluid aqui vuestros datos */
    
    static final String AUTOR = "Carlos Aragon Garcia";
    static final String DNI = "77668756X";
    
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        System.out.println("-------------------------------------------");
        System.out.println("EXAMEN ACCESO A DATOS");
        System.out.println("Diciembre 2022");
        System.out.println("Alumno: " + AUTOR);
        System.out.println("DNI: "+DNI);
        System.out.println("-------------------------------------------\n");

        System.out.println("\nEJERCICIO 1: Archivos");
        System.out.println("-------------------------------------------\n");
        
        Ejercicio1.solucion();
        
        System.out.println("\nEJERCICIO 2: JDBC");
        System.out.println("-------------------------------------------\n");
        
        Ejercicio2.solucion();

        System.out.println("\nEJERCICIO 3: Hibernate");
        System.out.println("-------------------------------------------\n");
        
        Ejercicio3.solucion();

        
    }
    
}

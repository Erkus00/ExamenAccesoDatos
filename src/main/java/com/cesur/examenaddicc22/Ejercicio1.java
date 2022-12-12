package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Ejercicio1 {

    /**
     * Enunciado:
     * <p>
     * Completar el método estadísticasDeArchivo de manera que lea el archivo
     * de texto que se le pasa como parámetro, lo analice y muestre por consola
     * el número de caracteres, palabras y líneas total.
     * <p>
     * Modificar solo el código del método.
     */

    static void solucion() throws FileNotFoundException {

        estadísticasDeArchivo("pom.xml");
    }

    private static void estadísticasDeArchivo(String archivo) throws FileNotFoundException {

        Integer lineas = 0;
        Integer caracteres = 0;
        Integer palabras = 0;

        ArrayList<String> all_text = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(archivo))) {
            while (br.ready()) {
                String tense = br.readLine();
                if (tense != null) {
                    all_text.addAll(List.of(tense.split(" ")));
                    lineas++;

                }
            }
            for (String el : all_text) {
                // A tener en cuenta que una palabra se diferencia de otra por tener un espacio en blanco entre ambas
                if (!Objects.equals(el, "")) {
//                    System.out.println(el);
                    palabras++;

                    for (Integer i = 0; i < el.length(); i++) {
                        if (el.charAt(i) != ' ') {
                            caracteres++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Numero de lineas: " + lineas);
        System.out.println("Numero de Palabras: " + palabras);
        System.out.println("Numero de Caracteres es: " + caracteres);
//        System.out.println("Ejercicio no resuelto");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonmanager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author MC
 */
public class JSONWriter {
    
    public static final String JSON_FILE="libri.json";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

    	ArrayList <Libro> libri = new ArrayList<>();
        
        Libro loHobbit = new Libro();
        
        loHobbit.setGenere("fantasy");
        loHobbit.setTitolo("Lo Hobbit");
        loHobbit.setAutore("J. R. R. Tolkien");
        loHobbit.setPrezzo(9.9f);
        libri.add(loHobbit);
        
        Libro sigAnelli = new Libro();
        
        sigAnelli.setGenere("fantasy");
        sigAnelli.setTitolo("Il signore degli anelli");
        sigAnelli.setAutore("J. R. R. Tolkien");
        sigAnelli.setPrezzo(30.00f);
        libri.add(sigAnelli);
    
        JsonObjectBuilder rootObject = Json.createObjectBuilder();
        JsonObjectBuilder booksObject = Json.createObjectBuilder();
        JsonArrayBuilder bookArray = Json.createArrayBuilder();
        
        for (Libro libro : libri){
            JsonObjectBuilder bookObject =Json.createObjectBuilder();
            bookObject.add("genere", libro.getGenere());
            bookObject.add("titolo", libro.getTitolo());
            bookObject.add("autore", libro.getAutore());
            bookObject.add("prezzo", libro.getPrezzo());
            bookArray.add(bookObject.build());
            System.out.println(libro);
        }
        
        booksObject.add("libri", bookArray.build());
        rootObject.add("libreria", booksObject.build());
        
        OutputStream output = new FileOutputStream(JSON_FILE);
        
        JsonWriter jsonWriter = Json.createWriter(output);
        
        jsonWriter.writeObject(rootObject.build());
        
        jsonWriter.close();
        
        output.close();
        
        System.out.println("File successfully written.");
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author baru
 */
public class LectorTexto {
    
     
     public static List<Peticiones> readFile(String doc) throws IOException{
         List<Peticiones> peti=new ArrayList<Peticiones>();
         try {
      File myObj = new File(doc);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
          
        String data = myReader.nextLine();
        StringTokenizer st= new StringTokenizer(data,".");
        st.nextToken();
        Peticiones p=new Peticiones();
        p.setCantidadVacuna(st.nextToken());
        p.setTipoVacuna(st.nextToken());
        peti.add(p);
        //System.out.println(st.nextToken());
        //System.out.println(st.nextToken());
        //System.out.println(st.nextToken());
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
         return peti;   
   }
      
}

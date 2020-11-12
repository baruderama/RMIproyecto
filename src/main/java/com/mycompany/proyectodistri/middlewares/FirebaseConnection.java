/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectodistri.middlewares;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.apache.http.impl.execchain.ClientExecChain;

/**
 *
 * @author baru
 */
public class FirebaseConnection extends Server {
    
    static Firestore bd;
    static List<Pruebita> listaNombresPruebas = new ArrayList<Pruebita>(); 

    public static void conectar() throws FileNotFoundException, IOException {

        FileInputStream serviceAccount
                = new FileInputStream("proyectodistri.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://proyectodistri-59e70.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        bd= FirestoreClient.getFirestore();
        System.out.println("succes");

    }
    
    public static boolean insertarDatos(String collecion,String documento,Map<String,Object> data){
        try{
            DocumentReference docref=bd.collection(collecion).document(documento);
            ApiFuture<WriteResult> result= docref.set(data);
            System.out.println("Update time: "+ result.get().getUpdateTime() );
            return false;
        }catch (Exception e){
            
        }
        return false;
   
}
     public static List<Pruebita> buscarNombresPruebas()throws InterruptedException,ExecutionException, RemoteException{
         System.out.println("la vida");
         CollectionReference nombres= bd.collection("nombres");
         ApiFuture<QuerySnapshot> querySnapshot= nombres.get();
         
         for(DocumentSnapshot document: querySnapshot.get().getDocuments()){
             //String numAux=String.valueOf(document.getString("numero"));
             //int num=Integer.parseInt(numAux);
             Pruebita nombresPruebita= new Pruebita(
             //document.getId(),
             document.getString("nombre"),
             111
             
             
             );
             listaNombresPruebas.add(nombresPruebita);
             Server.listaNombresPruebas.add(nombresPruebita);
             Int stub=Client.conectar();
             stub.setLista(listaNombresPruebas);
             System.out.println(document.getId());
             System.out.println(Server.listaNombresPruebas.get(0).getNombre());
         }
         return listaNombresPruebas;
    }

    public static List<Pruebita> getListaNombresPruebas() {
        return listaNombresPruebas;
    }

    public static void setListaNombresPruebas(List<Pruebita> listaNombresPruebas) {
        FirebaseConnection.listaNombresPruebas = listaNombresPruebas;
    }
     
     
     
      
}

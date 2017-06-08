package com.example.android.earthweb;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Gabriel on 25/04/2017.
 */

public class Conexao {

    //Method to do the connection with the script PHP
    public static String postDados(String urlUsuario, String parametrosUsuario){
        //Creating objects that go to be used
        URL url;
        HttpURLConnection connection = null;

        //Try to do the connection with the script PHP
        try{
            //Establishing connection with the script PHP
            url = new URL(urlUsuario);
            connection = (HttpURLConnection) url.openConnection();

            //Setting some properties to do requests, and to do connection
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametrosUsuario.getBytes().length));
            connection.setRequestProperty("Content-language", "pt-BR");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Writing the POST to script PHP with the parameters
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            outputStreamWriter.write(parametrosUsuario);
            outputStreamWriter.flush();

            //Getting the return of script PHP
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linha;
            StringBuffer resposta = new StringBuffer();
            while ((linha = bufferedReader.readLine()) != null){
                resposta.append(linha);
                resposta.append("\r");
            }
            bufferedReader.close();

            //Return the result
            return resposta.toString();
        }
        //If the try has a error this method return null
        catch(Exception erro){
            return null;
        }
        //When everything end, the connection is closed
        finally{
            if(connection != null){
                connection.disconnect();
            }
        }
    }
}

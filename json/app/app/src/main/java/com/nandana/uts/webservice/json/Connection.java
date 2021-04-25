package com.nandana.uts.webservice.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Connection {

    public String call(String url){
        int BUFFER_SIZE = 2000;
        InputStream inputStream;

        try{
            inputStream = OpenHttpConnection(url);
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        int charRead;
        StringBuilder string = new StringBuilder();
        char[] inputBuffer = new char[BUFFER_SIZE];

        try{
            while ((charRead = inputStreamReader.read(inputBuffer)) > 0){
                String readString = String.copyValueOf(inputBuffer,0, charRead);
                string.append(readString);
                inputBuffer = new char[BUFFER_SIZE];
            }
            inputStream.close();
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }

        return string.toString();
    }

    private InputStream OpenHttpConnection(String url) throws IOException {
        InputStream inputStream = null;
        int response;
        URL newUrl = new URL(url);
        URLConnection connection = newUrl.openConnection();

        if (!(connection instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection.");

        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            response = httpURLConnection.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (Exception e){
            throw new IOException("Error to connecting");
        }

        return inputStream;
    }
}

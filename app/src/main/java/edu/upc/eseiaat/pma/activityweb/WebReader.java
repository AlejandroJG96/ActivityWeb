package edu.upc.eseiaat.pma.activityweb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Usuari on 09/10/2017.
 */

public class WebReader {
    //Volem que sigui accessible fora d'aquesta clase per tant public
    public static String getURL(String SURL){
        String error = "Error";
        try {   //ES crea una excepcio i amb el try catch aconseguim que no peti la aplicació
            URL url =  new URL(SURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//per treballar amb url amb java agafem el string surl i el convertim a urlç
            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK){//consultem el codi que ens ha donat al realitzar la conexió i i es diferent de OK mostrem error i missatge
                return error + conn.getResponseMessage();
            }
            InputStream in = conn.getInputStream();//Obtenim el fluxe d'entrada per poder llegir
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            copyBytes(in,out);//copiem els bytes d'entrada a la sortida creant un metode copybytes
            out.close();//tanquem les conexions
            return out.toString();//ho pasem a un string per veure tot el fluxe de sortida

        } catch (MalformedURLException e) {
            return error + e.toString();
        } catch (IOException e) {
            return error + e.toString();
        }
    }

    private static void copyBytes(InputStream in, ByteArrayOutputStream out) throws IOException {
        byte [] bytes = new byte[1024];
        int nbytes= in.read(bytes);//sobre el fluxe de bytes returnem el numero de bytes que llegeix
        while (nbytes>0){//mentres el numero de bytes que llegeixi sigui més gran que 0 ves guardant els bytes al fluxe de sortida i tornar a llegir
            out.write(bytes, 0, nbytes);//escriu els bytes desde la posició 0 fins al numero de bytes que ha llegit
            nbytes = in.read(bytes);//hem de actualitzar el numero de bytes que portem llegits
            //quan no quedi res a l'entrada llegira 0

        }
    }
}

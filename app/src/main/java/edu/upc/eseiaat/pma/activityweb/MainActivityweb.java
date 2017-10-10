package edu.upc.eseiaat.pma.activityweb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityweb extends AppCompatActivity {
        private EditText editTextURL;
        private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityweb);
        editTextURL = (EditText) findViewById(R.id.editTextURL);
        textViewResult = (TextView) findViewById(R.id.textViewURL);

    }

    public void connect(View view) {
        //mètode que s'executarà quan l'usuari apreti el botó
        String url = editTextURL.getText().toString();
        //per a que no peti l'app hem de crear una tasca que faci el getURL(l'access a la xarxa) enlloc de fer textViewResult.setText(WebReader.getURL.....
        WebReaderTask wrTask  = new WebReaderTask();//creo nou obejcte de la clsse webreadertask
        wrTask.execute(url);//cridem

    }
    //creem classe abstarcta : clase que es subclase de connect
    private class WebReaderTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... url) {
            return  WebReader.getURL(url[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            textViewResult.setText(s);
        }
    }
}
//necessitem els permisos per poder acccedir a internet desde l'aplicació, ho fem desde androidmanifest
package com.example.tarea26_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre, et_contenido;

    public void Guardar(View view) {
       String nombre=et_nombre.getText().toString();
       String contenido=et_contenido.getText().toString();

       try {
       File tarjetaSD= Environment.getExternalStorageDirectory();
        Toast.makeText(this,tarjetaSD.getPath(),Toast.LENGTH_LONG).show();

        File rutaArchivo=new File(tarjetaSD.getPath(),nombre);
        OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput(rutaArchivo.getName(), Activity.MODE_PRIVATE));

        archivo.write(contenido);
        archivo.flush();
        archivo.close();
        Toast.makeText(this,"DATOS GUARDADOS",Toast.LENGTH_LONG).show();

        et_nombre.setText("");
        et_contenido.setText("");

        } catch (IOException e) {
            Toast.makeText(this,"ERROR AL GUARDAR EL ARCHIVO",Toast.LENGTH_LONG).show();
        }
    }


    public void Consultar(View view){
        String nombre=et_nombre.getText().toString();

        try{
        File tarjetaSD= Environment.getExternalStorageDirectory();
        File rutaArchivo=new File(tarjetaSD.getPath(),nombre);
         InputStreamReader archivo = new InputStreamReader(openFileInput(rutaArchivo.getName()));

            BufferedReader br=new BufferedReader(archivo);
            String linea=br.readLine();
            String contenidoCompleto="";
            while(linea != null){
                contenidoCompleto=contenidoCompleto + linea + "\n";
                linea=br.readLine();
            }
            br.close();
            archivo.close();
            et_contenido.setText(contenidoCompleto);
        } catch (IOException e) {
            Toast.makeText(this,"NO SE PUDO LEER  EL ARCHIVO",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_nombre=(EditText) findViewById(R.id.text_nombre);
        et_contenido=(EditText) findViewById(R.id.text_contenido);






    }
}
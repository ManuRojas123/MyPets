package com.example.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mypets.modelo.ListadeCompras;
import com.example.mypets.modelo.MypetsDataBaseHelper;
import com.example.mypets.modelo.Producto;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private ListadeCompras lista= ListadeCompras.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //acceder al databaseHelper

    public void verLista(View view){
        // ArrayList<Producto> productos=lista.getListaDeCompras();
        MypetsDataBaseHelper helper=new MypetsDataBaseHelper(this);
        try{
            ArrayList<Producto> productos=(ArrayList<Producto>)helper.listaProductos();
            Intent intent=new Intent(this,ListaProductosActivity.class);
            startActivity(intent);

        }catch (Exception ex){
            Toast.makeText(this,"Lista Vacía",Toast.LENGTH_SHORT).show();

        }
    }

    public void ingresarNuevo(View view)
    {
        Intent intent=new Intent(this, NuevoProductoActivity.class);
        startActivity(intent);
    }

    public void eliminarComprados(View view){
        MypetsDataBaseHelper helper=new MypetsDataBaseHelper(this);
        String msg= helper.eliminarComprados();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();




    }
}


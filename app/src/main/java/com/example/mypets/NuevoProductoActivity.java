package com.example.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypets.modelo.MypetsDataBaseHelper;
import com.example.mypets.modelo.Producto;

public class NuevoProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);
    }

    public void ingresarProducto(View view)
    {
        String nombre=((TextView) findViewById(R.id.ingresarNombre)).getText().toString();
        String cantidadStr=
                ((TextView)findViewById(R.id.ingresarCantidad)).getText().toString();
        String unidad=
                ((Spinner)findViewById(R.id.ingresarUnidad)).getSelectedItem().toString();
        String unidadNueva=
                ((TextView)findViewById(R.id.otraUnidad)).getText().toString();
        int cantidad=0;
        try{
            cantidad=Integer.parseInt(cantidadStr);
        }catch (NumberFormatException ex)
        {
            Toast.makeText(this,"Debe ingresar un número en la cantidad",
                    Toast.LENGTH_SHORT).show();
        }
        if(cantidad>0)
        {
            if(unidad.equals("Otro")){
                unidad=unidadNueva;
            }
            Producto producto=new Producto(nombre,cantidad,unidad);



            //con HELPER

            MypetsDataBaseHelper helper=new MypetsDataBaseHelper(this);
            helper.ingresarProducto(producto);

            finish();
        }
        else {
            Toast.makeText(this,"Ingrese una cantidad mayor a cero",
                    Toast.LENGTH_SHORT).show();
        }
    }

}

package com.example.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mypets.modelo.MypetsDataBaseHelper;
import com.example.mypets.modelo.Producto;

import java.util.List;

public class ListaProductosActivity extends ListActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cargarLista();
    }

    public void cargarLista(){
        lista=getListView();

        MypetsDataBaseHelper helper=new MypetsDataBaseHelper(this);
        List<Producto> productoList=helper.listaProductos();



        ArrayAdapter<Producto> listaAdapter= new ArrayAdapter<Producto>(this,
                android.R.layout.simple_expandable_list_item_1,productoList);
        lista.setAdapter(listaAdapter);
    }


    @Override
    public void onListItemClick(ListView listView, View view, int posicion, long id)
    {
        Object o=lista.getItemAtPosition(posicion);  //item seleccionado
        String linea=o.toString(); //El texto del item seleccionado
        //Obtener el nombre
        String[] separar=linea.split(":");  //el split es un separador para el array
        // Llamar al DetallesActivity

        Intent intent=new Intent(ListaProductosActivity.this,DetallesActivity.class);

        intent.putExtra("nombreProducto",separar[0]);

        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                cargarLista();
            }
        }
    }


}

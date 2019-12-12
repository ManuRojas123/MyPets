package com.example.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypets.modelo.MypetsDataBaseHelper;
import com.example.mypets.modelo.Producto;

public class DetallesActivity extends AppCompatActivity {

    private Producto producto;
    private Intent intent;
    private MypetsDataBaseHelper helper=new MypetsDataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        //Obtener el producto
        intent=getIntent();

        //Leer el nombre del producto
        String nombreProducto=(String)intent.getExtras().get("nombreProducto");

        //Traer el producto desde la base de datos utilizando el helper
        producto=helper.getProducto(nombreProducto);

        //Mostrar la información del producto
        TextView txtNombre=(TextView)findViewById(R.id.txtNombre);
        txtNombre.setText(producto.getNombre());

        TextView txtCantidad=(TextView)findViewById(R.id.txtCantidad);
        txtCantidad.setText("Cantidad: "+producto.getCantidad() +" "+ producto.getUnidad());

        TextView txtEstado=(TextView)findViewById(R.id.txtEstado);
        Button cambiar=(Button) findViewById(R.id.estado);
        if(producto.isEstado())
        {
            txtEstado.setText("En carrito de compras");
            cambiar.setText("Marcar para eliminar del carrito");
        }
        else {
            txtEstado.setText("Listo para eliminar del carrito");
            cambiar.setText("Volver a ingresar al carrito");
        }

    }

    public void cambiarEstado(View view)
    {
        producto.setEstado(!producto.isEstado());
        //Actualizar el estado en la base de datos
        String mensaje=helper.cambiarEstado(producto);
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();


        setResult(RESULT_OK,intent);
        finish();
    }
}

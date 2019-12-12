package com.example.mypets.modelo;

import java.util.ArrayList;

public class ListadeCompras {

    private static ListadeCompras instancia=new ListadeCompras();
    private ArrayList<Producto> listaDeCompras;

    private ListadeCompras()
    {
        listaDeCompras=new ArrayList<>();
        //  agregarProducto(new Producto("Pan",1,"Kilo"));
    }
    public static ListadeCompras getInstancia()
    {
        return instancia;
    }
    public void agregarProducto(Producto producto)
    {
        listaDeCompras.add(producto);
    }
    public Producto getProducto(int id)
    {
        return listaDeCompras.get(id);
    }
    public ArrayList<Producto> getListaDeCompras()
    {
        return listaDeCompras;
    }
    public void eliminarComprados()
    {
        for(int i=0; i<listaDeCompras.size(); i++)
        {
            if(listaDeCompras.get(i).isEstado()==false)
            {
                listaDeCompras.remove(i);
                i--;
            }
        }
    }

}

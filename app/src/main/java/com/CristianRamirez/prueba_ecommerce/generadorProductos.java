package com.CristianRamirez.prueba_ecommerce;

public class generadorProductos {

    String nombreProducto, urlImgProducto;
    float  precio;

    public  generadorProductos(String _nombreProducto, String _urlImgProducto, float _precio){
        nombreProducto=_nombreProducto;
        urlImgProducto=_urlImgProducto;
        precio=_precio;

    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getUrlImgProducto() {
        return urlImgProducto;
    }

    public float getPrecio() {
        return precio;
    }

}

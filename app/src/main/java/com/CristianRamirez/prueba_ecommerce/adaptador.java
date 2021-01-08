package com.CristianRamirez.prueba_ecommerce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adaptador extends RecyclerView.Adapter<adaptador.viewHolder> {

    private Context context;
    private ArrayList<generadorProductos> generadorProd;
    LinearLayout.LayoutParams lp;
    //Sqlite sqlite;
    //SQLiteDatabase db;

    public adaptador(Context _context,ArrayList<generadorProductos> _generadorProd){
        context=_context;
        generadorProd=_generadorProd;
       // sqlite = new Sqlite(context);
       // db = sqlite.getWritableDatabase();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.producto,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        generadorProductos productoActual = generadorProd.get(position);

        String nombreProducto=productoActual.getNombreProducto();
        String urlImgProducto=productoActual.getUrlImgProducto();
        float  precio=productoActual.getPrecio();


        holder.nombreProducto.setText(nombreProducto);
        holder.precioProducto.setText("$"+precio);
        GlideUrl url = new GlideUrl(urlImgProducto, new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        // Picasso.with(context).load(urlImgPerfil).resize(100,100).centerCrop().into(holder.imgPerfil);
        Glide.with(context).load(url).into(holder.imgProducto);


    }

    @Override
    public int getItemCount() {
        return generadorProd.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public ImageView imgProducto;
        public TextView nombreProducto,precioProducto;
        public viewHolder(View itemView) {
            super(itemView);
            imgProducto = itemView.findViewById(R.id.imgProducto);
            nombreProducto = itemView.findViewById(R.id.txtNombreProducto);
            precioProducto = itemView.findViewById(R.id.txtPrecio);
        }
    }



/*
    private void guardarBDLocalBusquedas(int idUsuario, String busqueda){
        if (db != null) {
            db.execSQL("INSERT or IGNORE  INTO busquedaUsuario (idUsuario , busqueda) VALUES ("+idUsuario+",\""+busqueda+"\")");
        }


        else{
            System.out.println("ya existe");
        }
    }
*/
}
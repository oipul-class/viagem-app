package br.senai.sp.jandira.viagens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.viagens.R
import br.senai.sp.jandira.viagens.model.Foto
import com.bumptech.glide.Glide

class GaleriaFotosDestinoAdpter(var context: Context) :
    RecyclerView.Adapter<GaleriaFotosDestinoAdpter.ViewHolder>() {

    private var listFotos = emptyList<Foto>()

    fun updateListFotos(list: List<Foto>) {
        listFotos = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.destinos_foto_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = listFotos.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foto = listFotos[position]

        Glide.with(context).load(foto.urlFoto).into(holder.iv_foto)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) { // inner class ou classe dentro de classe

        val iv_foto = view.findViewById<ImageView>(R.id.iv_image_foto)
    }

}
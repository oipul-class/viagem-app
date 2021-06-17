package br.senai.sp.jandira.viagens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.viagens.R
import br.senai.sp.jandira.viagens.model.Comentario
import com.bumptech.glide.Glide

class DestinoComentariosAdapter(val context: Context) : RecyclerView.Adapter<DestinoComentariosAdapter.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val cvImagemPerfio = view.findViewById<ImageView>(R.id.iv_imagem_perfio)
        val tvNomeDoUsuario = view.findViewById<TextView>(R.id.tv_nome_do_usuario)
        val tvDataDePublicacao = view.findViewById<TextView>(R.id.tv_data_da_publicacao)
        val rbNota = view.findViewById<RatingBar>(R.id.rb_nota)
        val tvUsuarioComentario = view.findViewById<TextView>(R.id.tv_usuario_comentario)
    }

    private var listaComentarios = emptyList<Comentario>()

    fun updateList(list: List<Comentario>) {
        listaComentarios = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.destinos_comentario_layout, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listaComentarios.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val comentario = listaComentarios[position]

        holder.tvNomeDoUsuario.text = comentario.displayName
        holder.tvDataDePublicacao.text = comentario.dataComentario
        holder.rbNota.rating = comentario.nota.toFloat()
        holder.tvUsuarioComentario.text = comentario.comentario

        Glide.with(context).load(comentario.photoUrl).into(holder.cvImagemPerfio)
    }
}
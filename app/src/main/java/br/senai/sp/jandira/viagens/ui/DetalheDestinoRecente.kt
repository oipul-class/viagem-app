package br.senai.sp.jandira.viagens.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import br.senai.sp.jandira.viagens.R
import br.senai.sp.jandira.viagens.model.DestinosRecentes
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout

class DetalheDestinoRecente : AppCompatActivity() {

    lateinit var collapsingToolbar: CollapsingToolbarLayout
    lateinit var ivFotoDestino: ImageView
    lateinit var tvLocal: TextView
    lateinit var tvValor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_destino_recente)

        val destinosRecentes: DestinosRecentes = intent.getSerializableExtra("destinos") as DestinosRecentes

        collapsingToolbar = findViewById(R.id.collapsing_toolbar)
        ivFotoDestino = findViewById(R.id.iv_destino)
        tvLocal = findViewById(R.id.tv_local)
        tvValor = findViewById(R.id.tv_valor)

        collapsingToolbar.title = "${destinosRecentes.nome}"
        tvLocal.text = "${destinosRecentes.nomeCidade}"

        if (destinosRecentes.valor <= 0) {
            tvValor.text = "Graça"

        }
        else {
            tvValor.text = "R$ ${String.format("%.2f", destinosRecentes.valor)}"

        }

        Glide.with(this).load(destinosRecentes.urlFoto).into(ivFotoDestino)
    }
}
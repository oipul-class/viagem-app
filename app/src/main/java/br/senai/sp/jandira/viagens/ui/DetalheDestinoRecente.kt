package br.senai.sp.jandira.viagens.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.viagens.R
import br.senai.sp.jandira.viagens.adapter.GaleriaFotosDestinoAdpter
import br.senai.sp.jandira.viagens.api.FotoCall
import br.senai.sp.jandira.viagens.api.RetrofitApi
import br.senai.sp.jandira.viagens.model.DestinosRecentes
import br.senai.sp.jandira.viagens.model.Foto
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalheDestinoRecente : AppCompatActivity() {

    lateinit var collapsingToolbar: CollapsingToolbarLayout
    lateinit var ivFotoDestino: ImageView
    lateinit var tvLocal: TextView
    lateinit var tvValor: TextView
    lateinit var tvAPartir: TextView
    lateinit var tvDescricao: TextView
    lateinit var rvGaleriaFotos: RecyclerView

    lateinit var destinosRecentes : DestinosRecentes

    lateinit var galeriaFotosAdapater : GaleriaFotosDestinoAdpter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_destino_recente)

        carregarDados()
        carregarFotosDoDestino()
    }

    private fun carregarDados() {
        destinosRecentes =
            intent.getSerializableExtra("destinos") as DestinosRecentes

        collapsingToolbar = findViewById(R.id.collapsing_toolbar)
        ivFotoDestino = findViewById(R.id.iv_destino)
        tvLocal = findViewById(R.id.tv_local)
        tvValor = findViewById(R.id.tv_valor)
        tvDescricao = findViewById(R.id.tv_descricao_texto)
        tvAPartir = findViewById(R.id.tv_a_partir_de)

        collapsingToolbar.title = "${destinosRecentes.nome}"
        tvLocal.text = "${destinosRecentes.nomeCidade}"

        if (destinosRecentes.valor <= 0) {
            tvAPartir.visibility = TextView.INVISIBLE
            tvValor.text = "Gratis"
        }
        else tvValor.text = "R$ ${String.format("%.2f", destinosRecentes.valor)}"

        tvDescricao.text = destinosRecentes.descricao

        if (destinosRecentes.urlFoto !== "") Glide.with(this).load(destinosRecentes.urlFoto)
            .into(ivFotoDestino)
        else ivFotoDestino.setImageResource(R.drawable.porto_galinhas)
    }

    private fun carregarFotosDoDestino() {
        rvGaleriaFotos = findViewById(R.id.rv_galeria_fotos)

        rvGaleriaFotos.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false)

        galeriaFotosAdapater = GaleriaFotosDestinoAdpter(this)

        rvGaleriaFotos.adapter = galeriaFotosAdapater

        var listaDeFotos: List<Foto>

        val retrofit = RetrofitApi.getRetrofit()
        val fotoCall = retrofit.create(FotoCall::class.java)

        val call = fotoCall.getFotosDestino(destinosRecentes.id)

        call.enqueue(object : Callback<List<Foto>> {
            override fun onFailure(call: Call<List<Foto>>, t: Throwable) {
                Toast.makeText(this@DetalheDestinoRecente,  "A conexão falhou!", Toast.LENGTH_SHORT ).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<List<Foto>>, response: Response<List<Foto>>) {
                listaDeFotos = response.body()!!
                galeriaFotosAdapater.updateListFotos(listaDeFotos)

            }

        })

    }
}
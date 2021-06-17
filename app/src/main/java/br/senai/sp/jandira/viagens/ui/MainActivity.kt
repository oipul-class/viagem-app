package br.senai.sp.jandira.viagens.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.viagens.R
import br.senai.sp.jandira.viagens.adapter.DestinoRecenteAdapter
import br.senai.sp.jandira.viagens.api.DestinosRecentesCall
import br.senai.sp.jandira.viagens.api.RetrofitApi
import br.senai.sp.jandira.viagens.model.DestinosRecentes
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var tvDisplayName: TextView
    lateinit var ivUserPhoto: ImageView
    lateinit var rvDestinosRecentes: RecyclerView
    lateinit var adapterDestinosRecentes: DestinoRecenteAdapter
    lateinit var btSair: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDestinosRecentes = findViewById(R.id.rv_destinos_recentes)
        btSair = findViewById(R.id.tv_sair)
        tvDisplayName = findViewById(R.id.tv_user_nome)
        ivUserPhoto = findViewById(R.id.iv_user_photo)

        btSair.setOnClickListener(this)
        rvDestinosRecentes.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false)

        adapterDestinosRecentes = DestinoRecenteAdapter(this)

        rvDestinosRecentes.adapter = adapterDestinosRecentes

        carregarListaDestinosRecentes()
        exibirProfile()
    }

    private fun carregarListaDestinosRecentes() {

        var destinosRecentes: List<DestinosRecentes>

        val retrofit = RetrofitApi.getRetrofit()
        val destinosRecentesCall = retrofit.create(DestinosRecentesCall::class.java)

        val call = destinosRecentesCall.getDestinosRecentes()

        call.enqueue(object : Callback<List<DestinosRecentes>> {
            override fun onFailure(call: Call<List<DestinosRecentes>>, t: Throwable) {
                Toast.makeText(this@MainActivity,  "A conexão falhou!", Toast.LENGTH_SHORT ).show()
                Log.e("ERRO_CONEXÃO", t.message.toString())
            }

            override fun onResponse(call: Call<List<DestinosRecentes>>, response: Response<List<DestinosRecentes>>) {
                destinosRecentes = response.body()!!
                adapterDestinosRecentes.updateListRecentes(destinosRecentes)
            }
        })
    }

    private fun signOut() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignInClient.signOut()

        finish()
    }

    private fun exibirProfile() {
        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
        tvDisplayName.text = dados.getString("display_name", "")

        Glide.with(this).load(dados.getString("url_photo", "")).into(ivUserPhoto)
    }

    override fun onClick(v: View?) {
        signOut()
    }
}
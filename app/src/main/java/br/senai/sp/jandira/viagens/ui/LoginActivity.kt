package br.senai.sp.jandira.viagens.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.senai.sp.jandira.viagens.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

const val RC_SIGN_IN = 1500

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var botaoLogar: SignInButton
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (account != null) {
            updateUI(account)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        botaoLogar = findViewById(R.id.sib_logar)

        botaoLogar.setOnClickListener(this)

    }



    private fun updateUI(account: GoogleSignInAccount?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.sib_logar) signIn()
    }

    private fun signIn() {
        val intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val usuario = task.getResult(ApiException::class.java)
            if (usuario != null) {
                Log.d("xpto", usuario.displayName.toString())
                Log.d("xpto", usuario.email.toString())

            }
        }
    }

}
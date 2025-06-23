package com.example.uthsmarttasks.view

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun GoogleSignInScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()

    val googleSignInClient = remember {
        GoogleSignIn.getClient(
            navController.context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(navController.context.getString(com.example.uthsmarttasks.R.string.default_web_client_id))
                .requestEmail()
                .build()
        )
    }

    LaunchedEffect(Unit) {
        auth.signOut()
        googleSignInClient.signOut()
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(Exception::class.java)!!
                firebaseAuthWithGoogle(auth, account, navController)
            } catch (e: Exception) {
                Log.e("GoogleSignIn", "Đăng nhập thất bại", e)
                Toast.makeText(navController.context, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Đăng nhập bằng Google", modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = { launcher.launch(googleSignInClient.signInIntent) }) {
            Text(text = "Sign in with Google")
        }
    }
}




private fun firebaseAuthWithGoogle(auth: FirebaseAuth, account: GoogleSignInAccount, navController: NavController) {
    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Log.d("FirebaseAuth", "Đăng nhập thành công: ${user?.email}")
                Toast.makeText(navController.context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()

                // Chuyển hướng sau khi đăng nhập thành công
                navController.navigate(BottomNavItem.Home.route) {
                    popUpTo("googleSignIn") { inclusive = true }
                }
            } else {
                Log.e("FirebaseAuth", "Đăng nhập Firebase thất bại", task.exception)
                Toast.makeText(navController.context, "Đăng nhập Firebase thất bại", Toast.LENGTH_SHORT).show()
            }
        }
}

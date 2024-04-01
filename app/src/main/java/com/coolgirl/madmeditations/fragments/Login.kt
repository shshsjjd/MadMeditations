package com.coolgirl.madmeditations.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coolgirl.madmeditations.Models.ApiClient
import com.coolgirl.madmeditations.Models.ApiController
import com.coolgirl.madmeditations.Models.UserLoginData
import com.coolgirl.madmeditations.Models.UserLoginDataResponse
import com.coolgirl.madmeditations.R
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.*

class Login : Fragment() {
    lateinit var pref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            pref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
            IsAutorize(pref)

            setContent {
                LoadLoginScreen()
            }
        }
    }

    fun IsAutorize(pref: SharedPreferences){
        val isAutorize = pref.getBoolean("isAutorize", false)
        val isFirstTap = pref.getBoolean("isFirstTap", true)
        if(isAutorize){
            toMain(UserLoginDataResponse(
                null,
                pref.getString("email", ""),
                pref.getString("nickName", ""),
                pref.getString("avatar", ""),
                null))
        }else if(isFirstTap){
            pref.edit().putBoolean("isFirstTap", false).apply()
            toOnBoarding()
        }
    }

    fun toRegister() { findNavController().navigate(R.id.action_login_to_register) }

    fun toMain(user: UserLoginDataResponse) {
        val editor = pref.edit()
        editor.putBoolean("isAutorize", true)
        editor.putString("email", user.email)
        editor.putString("nickName", user.nickName)
        editor.putString("avatar", user.avatar)
        editor.apply()
        val bundle = Bundle()
        bundle.putString("nickName", user.nickName)
        bundle.putString("avatar", user.avatar)
        findNavController().navigate(R.id.action_login_to_main, bundle)
    }

    fun toOnBoarding(){ findNavController().navigate(R.id.action_login_to_onBoarding) }

    fun Autorization(login: String, password: String) {

        var apiClient = ApiClient.start().create(ApiController::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val usr = apiClient.autorizeClient(UserLoginData(login, password))
                kotlin.run {
                    if (usr.nickName!=null){
                        withContext(Dispatchers.Main) {
                            toMain(usr)
                        }
                    }
                }
            }
            catch (e: Exception) {
                Log.e("tag", "В LoginFragment exeption : ${e.message}")
            }
        }
    }


    @Composable
    fun LoadLoginScreen() {
        val loginField = remember { mutableStateOf("") }
        val passwordField = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.dark_green)),
            verticalArrangement = Arrangement.Bottom

        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.leaves),
                contentDescription = "image"
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .padding(top = 40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(0.3f),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "image"
                )
                Text(
                    text = "Sign in",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp),
                    color = colorResource(R.color.white),
                    fontSize = 30.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f).padding(start = 45.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                TextField(
                    value = loginField.value,
                    modifier = Modifier.background(colorResource(R.color.dark_green)),
                    placeholder = { Text(text = "Email", color = colorResource(R.color.white)) },
                    onValueChange = { newText -> loginField.value = newText }
                )
                TextField(
                    value = passwordField.value,
                    modifier = Modifier
                        .background(colorResource(R.color.dark_green))
                        .padding(top = 20.dp),
                    placeholder = { Text(text = "Пароль", color = colorResource(R.color.white)) },
                    onValueChange = { newText -> passwordField.value = newText })
                Button(
                    onClick = { Autorization(loginField.value, passwordField.value) },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green)),
                    modifier = Modifier
                        .fillMaxWidth(0.89f)
                        .fillMaxHeight(0.4f)
                        .padding(top = 30.dp)
                        .alpha(0.80f)
                ) {
                    Text(text = "Sign in", color = colorResource(R.color.white))
                }
                TextButton(onClick = { toRegister() }) {
                    Text(text = "Register", color = colorResource(R.color.white))
                }
                Button(
                    onClick = { Autorization(loginField.value, passwordField.value) },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green)),
                    modifier = Modifier
                        .fillMaxWidth(0.89f)
                        .fillMaxHeight(0.67f)
                        .padding(top = 4.dp)
                        .alpha(0.80f)
                ) {
                    Text(text = "Профиль", color = colorResource(R.color.white))
                }
            }
        }
    }
}


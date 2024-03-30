package com.coolgirl.madmeditations.fragments

import android.os.Bundle
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
import com.coolgirl.madmeditations.R

class Login : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            setContent {
                LoadLoginScreen()
            }
        }
    }

    fun toRegister(){
        findNavController().navigate(R.id.action_login_to_register)
    }
    fun toMain(){
        findNavController().navigate(R.id.action_login_to_main)
    }

    @Composable
    fun LoadLoginScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.dark_green)),
            verticalArrangement = Arrangement.Bottom

        ){
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
            ){
                Image(
                    modifier = Modifier
                        .fillMaxSize(0.3f),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "image"
                )
                Text(text = "Sign in",
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
                    value = "",
                    modifier = Modifier.background(colorResource(R.color.dark_green)),
                    placeholder = { Text(text = "Email", color = colorResource(R.color.white)) },
                    onValueChange = {})
                TextField(
                    value = "",
                    modifier = Modifier
                        .background(colorResource(R.color.dark_green))
                        .padding(top = 20.dp),
                    placeholder = { Text(text = "Пароль", color = colorResource(R.color.white)) },
                    onValueChange = {})
                Button(onClick = { toMain() },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green)),
                    modifier = Modifier
                        .fillMaxWidth(0.89f)
                        .fillMaxHeight(0.4f)
                        .padding(top = 30.dp)
                        .alpha(0.80f)) {
                    Text(text = "Sign in", color = colorResource(R.color.white))
                }
                TextButton(onClick = { toRegister() }) {
                    Text(text = "Register", color = colorResource(R.color.white))
                }
<<<<<<< HEAD
                Button(onClick = { toMain() },
=======
                Button(onClick = {
                    //тут будет клик
                },
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green)),
                    modifier = Modifier
                        .fillMaxWidth(0.89f)
                        .fillMaxHeight(0.67f)
                        .padding(top = 4.dp)
                        .alpha(0.80f)) {
                    Text(text = "Профиль", color = colorResource(R.color.white))
                }
            }
        }
    }
}

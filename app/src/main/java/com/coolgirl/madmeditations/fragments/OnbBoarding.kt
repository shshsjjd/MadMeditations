package com.coolgirl.madmeditations.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coolgirl.madmeditations.R

class OnbBoarding : Fragment() {
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
                OnbBoarding()
            }
        }
    }

    private fun toLogin(){
        findNavController().navigate(R.id.action_onBoarding_to_login)
    }
    private fun toRegister(){
        findNavController().navigate(R.id.action_onBoarding_to_register)
    }

    @Composable
    fun OnbBoarding() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(id = R.drawable.background_mountain),
                contentDescription = "image"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(0.6f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "image"
                )
                Text(text = "ПРИВЕТ",
                    color = colorResource(R.color.white)
                )
                Text(text = "Наслаждайся отборочными.\nБудь внимателен. \nДелай хорошо.",
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.white)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(onClick = {
                    toLogin()
                },
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.light_green))) {
                    Text(text = "ВОЙТИ В АККАУНТ",
                        color = colorResource(R.color.white)
                    )
                }
                Text(text = "Еще нет аккаунта? Зарегистрируйтесь",
                    color = colorResource(R.color.white),
                    modifier = Modifier.clickable { toRegister() }
                )
            }
        }
    }

}


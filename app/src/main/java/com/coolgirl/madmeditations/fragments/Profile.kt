package com.coolgirl.madmeditations.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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
import com.coolgirl.madmeditations.fragments.ui.theme.MADMeditationsTheme

class Profile : Fragment() {
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
                SetProfile()
            }
        }
    }

    fun toMenu(){
        findNavController().navigate(R.id.action_profile_to_menu)
    }
    fun toMain(){
        findNavController().navigate(R.id.action_profile_to_main)
    }
    @Composable
    fun SetProfile(){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.dark_green))
        ) {

            SetHead()
            SetUserHead()
            SetBottomPanel()
        }
    }

    @Composable
    fun SetHead(){
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.hamburger),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { toMenu() })
            }
            Image(
                modifier = Modifier.fillMaxWidth(0.25f),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "image"
            )
            Text (
                modifier = Modifier.clickable{ toMain()},
                text = "exit",
                color = colorResource(R.color.white),)
        }
    }

    @Composable
    fun SetUserHead(){
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.usericon),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp).clip(CircleShape))
            Text(text = "Эмиль", color = colorResource(R.color.white), fontSize = 30.sp)
        }
    }
    @Composable
    fun SetBottomPanel(){
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly){
            Image(
                modifier = Modifier.fillMaxHeight(0.8f).alpha(0.5f).clickable { toMain() },
                painter = painterResource(R.drawable.logo),
                contentDescription = "image"
            )
            Image(
                modifier = Modifier.fillMaxHeight(0.8f).size(20.dp),
                painter = painterResource(R.drawable.sound_icon),
                contentDescription = "image"
            )
            Image(
                modifier = Modifier.fillMaxHeight(0.8f).size(20.dp),
                painter = painterResource(R.drawable.profile_icon),
                contentDescription = "image"
            )
        }
    }

}

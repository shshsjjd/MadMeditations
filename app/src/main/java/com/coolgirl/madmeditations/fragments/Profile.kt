package com.coolgirl.madmeditations.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import com.coolgirl.madmeditations.Models.UserLoginDataResponse
import com.coolgirl.madmeditations.R

class Profile : Fragment() {
    var user : UserLoginDataResponse? = null
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
            SetData()
            setContent {

                SetProfile()
            }
        }
    }

    fun toMenu(){ findNavController().navigate(R.id.action_profile_to_menu) }

    fun toMain(){ findNavController().navigate(R.id.action_profile_to_login) }

    //он научится принимать аргумент фото
    fun toPhoto(){
        findNavController().navigate(R.id.action_profile_to_photo)
    }

    @Composable
    fun SetProfile(){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.dark_green))
        ) {

            SetHead()
            SetUserHead()
            SetPhotoBlock()
            SetBottomPanel()
        }
    }

    fun SetData(){
        if(arguments!!.getString("avatar", null)!=null){
            user = UserLoginDataResponse(
                null,
                null,
                arguments!!.getString("nickName", null),
                arguments!!.getString("avatar", null),
                null
            )
            val editor = pref.edit()
            editor.putString("nickName", user?.nickName)
            editor.putString("avatar", user?.avatar)
            editor.apply()
        }else{
            user = UserLoginDataResponse(
                null,
                null,
                pref.getString("nickName", null),
                pref.getString("avatar", null),
                null
            )
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
            .fillMaxHeight(0.32f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberImagePainter(arguments!!.getString("avatar")),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape))
            Text(text = arguments!!.getString("nickName", ""), color = colorResource(R.color.white), fontSize = 30.sp)
        }
    }
    @Composable
    fun SetBottomPanel(){
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly){
            Image(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .alpha(0.3f)
                    .clickable { toMain() },
                painter = painterResource(R.drawable.logo),
                contentDescription = "image"
            )
            Image(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .size(20.dp),
                painter = painterResource(R.drawable.sound_icon),
                contentDescription = "image"
            )
            Image(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .size(20.dp),
                painter = painterResource(R.drawable.profile_icon),
                contentDescription = "image"
            )
        }
    }
    //это будет подвергнуто рефакторингу
    @Composable
    fun SetPhotoBlock(){
        Column(modifier = Modifier
            .fillMaxHeight(0.86f)
            .fillMaxWidth()
            .verticalScroll(ScrollState(1), true)) {
            Row(
                modifier = Modifier.
                fillMaxWidth()){
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(20.dp, 20.dp, 20.dp, 20.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp){
                    Image(
                        painter = painterResource(R.drawable.image1),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize().clickable { toPhoto() })
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 20.dp, 20.dp, 20.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp) {
                    Image(
                        painter = painterResource(R.drawable.image2),
                        contentScale = ContentScale.Crop,
                        contentDescription = "image",
                        modifier = Modifier.fillMaxSize().clickable { toPhoto() })
                }
            }
            Row(
                modifier = Modifier.
                fillMaxWidth()){
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(20.dp, 20.dp, 20.dp, 20.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp){
                    Image(
                        painter = painterResource(R.drawable.image3),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize().clickable { toPhoto() })
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 20.dp, 20.dp, 20.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp) {
                    Image(
                        painter = painterResource(R.drawable.image4),
                        contentScale = ContentScale.Crop,
                        contentDescription = "image",
                        modifier = Modifier.fillMaxSize().clickable { toPhoto() })
                }
            }
            Row(
                modifier = Modifier.
                fillMaxWidth()){
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(20.dp, 20.dp, 20.dp, 20.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(R.color.light_green))) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "+", fontSize = 40.sp, color = colorResource(R.color.white), fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable { Toast.makeText(context, "Добавление ещё не завезли", Toast.LENGTH_SHORT).show() })
                        }

                    }
                }
            }
        }
    }
}

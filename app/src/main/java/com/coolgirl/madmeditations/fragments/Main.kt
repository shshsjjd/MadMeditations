package com.coolgirl.madmeditations.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coolgirl.madmeditations.R
import com.coolgirl.madmeditations.fragments.ui.theme.MADMeditationsTheme
import java.sql.DriverManager.println

class Main : Fragment() {
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
            SetMain()
         }
      }
   }

   fun toMenu(){
      findNavController().navigate(R.id.action_main_to_menu)
   }

   @Composable
   fun SetMain(){

      Column(modifier = Modifier
         .fillMaxSize()
         .background(colorResource(R.color.dark_green))
      ) {

         SetHead()
         Row(
            modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight(0.25f)
               .horizontalScroll(ScrollState(1), true),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
         ){
            scrollItemMindset(  "Спокойным", R.drawable.calm_item)
            scrollItemMindset( "Расслабленным", R.drawable.relax_item )
            scrollItemMindset( "Cфокусированным", R.drawable.focus_item)
            scrollItemMindset("Взволнованным", R.drawable.anxious_item )
            scrollItemMindset("Норм", R.drawable.norm_item )
         }
         Column(
            modifier = Modifier
               .fillMaxWidth().fillMaxHeight(0.85f)
               .verticalScroll(ScrollState(1), true),
            verticalArrangement = Arrangement.Top){
            ScrollItemBlock(R.drawable.iconforblock1)
            ScrollItemBlock(R.drawable.iconforblock2)
         }
         Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalAlignment = Alignment.Bottom){
            Text(text = "хуййййййййййййй")
         }
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
            modifier = Modifier
               .fillMaxWidth(0.25f),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "image"
         )
         Image(
            painter = painterResource(id = R.drawable.usericon),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
               .size(40.dp)
               .clip(CircleShape)
         )
      }
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Top){
         Text(text = "С возвращением, Эмиль!", color = colorResource(R.color.white), fontSize = 25.sp)
         Text(text = "Каким ты себя ощущаешь сегодня?", color = colorResource(R.color.white))
      }

   }

   @SuppressLint("ResourceType")
   @Composable
   fun scrollItemMindset(itemName:String, @DrawableRes img : Int){
      Column(modifier = Modifier.fillMaxHeight(),
         verticalArrangement = Arrangement.SpaceBetween,
         horizontalAlignment = Alignment.CenterHorizontally) {
         Card(
            modifier = Modifier
               .fillMaxHeight(0.85f)
               .padding(13.dp, 5.dp, 13.dp, 5.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
         ) {
            Box(
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(10.dp)
                  .background(colorResource(R.color.white))) {
               Image(
                  painter = painterResource(img),
                  contentDescription = "image",
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                     .padding(7.dp)
                     .size(55.dp)) } }
         Text(text = itemName, color = colorResource(R.color.white), fontSize = 12.sp, modifier = Modifier)
      }
   }

   @Composable
   fun ScrollItemBlock(@DrawableRes img: Int){
      Card(
         modifier = Modifier
            .padding(20.dp, 20.dp, 20.dp, 20.dp)
            .height(160.dp),
         shape = RoundedCornerShape(15.dp),
         elevation = 5.dp
      ) {
         Box(
            modifier = Modifier
               .fillMaxWidth()
               .padding(15.dp)
               .background(colorResource(R.color.white))) {
            Row(verticalAlignment = Alignment.CenterVertically) {
               Column( verticalArrangement = Arrangement.SpaceEvenly) {
                  Text(text = "Заголовок блока", color = colorResource(R.color.dark_green), fontSize = 23.sp)
                  Text(text = "Кратенькое описание блока \nс двумя строчками", color = colorResource(R.color.dark_green), fontSize = 15.sp,
                     modifier = Modifier.padding(bottom = 15.dp))
                  Button(onClick = {  },
                     colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))) {
                     Text(text = "Подробнее",color = colorResource(R.color.white))
                  }
               }
               Image(
                  painter = painterResource(img),
                  contentDescription = "image")
            }



         }
      }
   }
}

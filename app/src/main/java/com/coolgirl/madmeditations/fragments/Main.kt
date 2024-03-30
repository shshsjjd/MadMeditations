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
<<<<<<< HEAD
import androidx.compose.ui.draw.alpha
=======
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
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
<<<<<<< HEAD
=======
import com.coolgirl.madmeditations.fragments.ui.theme.MADMeditationsTheme
import java.sql.DriverManager.println
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f

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
<<<<<<< HEAD
   fun toProfile(){
      findNavController().navigate(R.id.action_main_to_profile)
   }
=======
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f

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
<<<<<<< HEAD
            ScrollItemMindset(  "Спокойным", R.drawable.calm_item)
            ScrollItemMindset( "Расслабленным", R.drawable.relax_item )
            ScrollItemMindset( "Cфокусированным", R.drawable.focus_item)
            ScrollItemMindset("Взволнованным", R.drawable.anxious_item )
            ScrollItemMindset("Норм", R.drawable.norm_item )
         }
         Column(
            modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight(0.85f)
=======
            scrollItemMindset(  "Спокойным", R.drawable.calm_item)
            scrollItemMindset( "Расслабленным", R.drawable.relax_item )
            scrollItemMindset( "Cфокусированным", R.drawable.focus_item)
            scrollItemMindset("Взволнованным", R.drawable.anxious_item )
            scrollItemMindset("Норм", R.drawable.norm_item )
         }
         Column(
            modifier = Modifier
               .fillMaxWidth().fillMaxHeight(0.85f)
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
               .verticalScroll(ScrollState(1), true),
            verticalArrangement = Arrangement.Top){
            ScrollItemBlock(R.drawable.iconforblock1)
            ScrollItemBlock(R.drawable.iconforblock2)
         }
<<<<<<< HEAD

           SetBottomPanel()

=======
         Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalAlignment = Alignment.Bottom){
            Text(text = "хуййййййййййййй")
         }
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
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
<<<<<<< HEAD
               modifier = Modifier.size(20.dp).clickable { toMenu() })
=======
               modifier = Modifier
                  .size(20.dp)
                  .clickable { toMenu() })
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
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
<<<<<<< HEAD
            modifier = Modifier.size(40.dp).clip(CircleShape).clickable { toProfile() }
=======
            modifier = Modifier
               .size(40.dp)
               .clip(CircleShape)
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
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
<<<<<<< HEAD
   fun ScrollItemMindset(itemName:String, @DrawableRes img : Int){
=======
   fun scrollItemMindset(itemName:String, @DrawableRes img : Int){
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
      Column(modifier = Modifier.fillMaxHeight(),
         verticalArrangement = Arrangement.SpaceBetween,
         horizontalAlignment = Alignment.CenterHorizontally) {
         Card(
<<<<<<< HEAD
            modifier = Modifier.fillMaxHeight(0.85f).padding(13.dp, 5.dp, 13.dp, 5.dp),
=======
            modifier = Modifier
               .fillMaxHeight(0.85f)
               .padding(13.dp, 5.dp, 13.dp, 5.dp),
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
         ) {
            Box(
<<<<<<< HEAD
               modifier = Modifier.fillMaxWidth().padding(10.dp).background(colorResource(R.color.white))) {
=======
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(10.dp)
                  .background(colorResource(R.color.white))) {
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
               Image(
                  painter = painterResource(img),
                  contentDescription = "image",
                  contentScale = ContentScale.Crop,
<<<<<<< HEAD
                  modifier = Modifier.padding(7.dp).size(55.dp)) } }
=======
                  modifier = Modifier
                     .padding(7.dp)
                     .size(55.dp)) } }
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
         Text(text = itemName, color = colorResource(R.color.white), fontSize = 12.sp, modifier = Modifier)
      }
   }

   @Composable
   fun ScrollItemBlock(@DrawableRes img: Int){
      Card(
<<<<<<< HEAD
         modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 20.dp).height(160.dp),
=======
         modifier = Modifier
            .padding(20.dp, 20.dp, 20.dp, 20.dp)
            .height(160.dp),
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
         shape = RoundedCornerShape(15.dp),
         elevation = 5.dp
      ) {
         Box(
<<<<<<< HEAD
            modifier = Modifier.fillMaxWidth().padding(15.dp).background(colorResource(R.color.white))) {
=======
            modifier = Modifier
               .fillMaxWidth()
               .padding(15.dp)
               .background(colorResource(R.color.white))) {
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
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
<<<<<<< HEAD
         }
      }
   }

   @Composable
   fun SetBottomPanel(){
      Row(
         modifier = Modifier.fillMaxWidth().fillMaxHeight(),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceEvenly){
         Image(
            modifier = Modifier.fillMaxHeight(0.8f),
            painter = painterResource(R.drawable.logo),
            contentDescription = "image"
         )
         Image(
            modifier = Modifier.fillMaxHeight(0.8f).size(20.dp),
            painter = painterResource(R.drawable.sound_icon),
            contentDescription = "image"
         )
         Image(
            modifier = Modifier.fillMaxHeight(0.8f).size(20.dp).clickable { toProfile() },
            painter = painterResource(R.drawable.profile_icon),
            contentDescription = "image"
         )
      }
   }
=======



         }
      }
   }
>>>>>>> 5bd1795ef7eeadf2709c30c272ec943df2881a1f
}

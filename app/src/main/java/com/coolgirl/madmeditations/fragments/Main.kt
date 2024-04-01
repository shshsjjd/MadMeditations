package com.coolgirl.madmeditations.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import coil.compose.rememberImagePainter
import com.coolgirl.madmeditations.Models.*
import com.coolgirl.madmeditations.R
import com.coolgirl.madmeditations.fragments.ui.theme.MADMeditationsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class Main : Fragment() {
   var feelings : List<Feelings>? = null
   lateinit var pref : SharedPreferences
   var user : UserLoginDataResponse? = null
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {

      return ComposeView(requireContext()).apply {
         setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
         )
         LoadPage()
         pref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
         SetData()
         setContent {

            SetMain()
         }
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


   fun LoadPage() {
      var apiClient = ApiClient.start().create(ApiController::class.java)
      val call: Call<ResponseFeelings>? = apiClient.getFeelings()
      call!!.enqueue(object : Callback<ResponseFeelings?>{
         override fun onResponse(
            call: Call<ResponseFeelings?>,
            response: Response<ResponseFeelings?>
         ) {
            val responseData = response.body()
            if (responseData != null) {
               feelings = responseData.data
            }
         }
         override fun onFailure(call: Call<ResponseFeelings?>, t: Throwable) {
         }
      })
   }

   fun toMenu(){ findNavController().navigate(R.id.action_main_to_menu) }

   fun toProfile(){
      val bundle = Bundle()
      bundle.putString("nickName", arguments!!.getString("nickName"))
      bundle.putString("avatar",arguments!!.getString("avatar"))
      findNavController().navigate(R.id.action_main_to_profile, bundle)
   }
   @Composable
   fun SetHorizontallScroll(){
      feelings?.let { feelingsList ->
         for (i in 0 until feelingsList.size) {
            val feel = feelingsList[i]
            feel.title?.let { title ->
               feel.image?.let { image ->
                  ScrollItemMindset(title, image)
               }
            }
         }
      }
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
            SetHorizontallScroll()
         }
         Column(
            modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight(0.85f)
               .verticalScroll(ScrollState(1), true),
            verticalArrangement = Arrangement.Top){
            ScrollItemBlock(R.drawable.iconforblock1)
            ScrollItemBlock(R.drawable.iconforblock2)
         }
         Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), verticalAlignment = Alignment.Bottom){
            SetBottomPanel()
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
            painter = rememberImagePainter(user?.avatar),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
               .clickable { toProfile() }
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
         Text(text = "С возвращением, ${user?.nickName}!", color = colorResource(R.color.white), fontSize = 25.sp)
         Text(text = "Каким ты себя ощущаешь сегодня?", color = colorResource(R.color.white))
      }
   }

   @SuppressLint("ResourceType")
   @Composable
   fun ScrollItemMindset(itemName:String, img : String){
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
                  painter = rememberImagePainter(img),
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

   @Composable
   fun SetBottomPanel(){
      Row(
         modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceEvenly){
         Image(
            modifier = Modifier.fillMaxHeight(0.8f),
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
               .alpha(0.5f)
               .size(20.dp)
               .clickable { toProfile() },
            painter = painterResource(R.drawable.profile_icon),
            contentDescription = "image"
         )
      }
   }
}

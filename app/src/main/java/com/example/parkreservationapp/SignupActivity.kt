package com.example.parkreservationapp


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun navbar(title:String){
Box(modifier=Modifier.height(130.dp)
    .fillMaxWidth()
    .background(color=Color(0xFF0078B8))
){
    val context= LocalContext.current
    IconButton(
        onClick = {
        val intent= Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    },
        modifier = Modifier.align(Alignment.CenterStart)
    ){
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "arrow back"

        )}
        Text(text = title, color = Color.White, fontStyle =  FontStyle.Normal, fontWeight = FontWeight.Bold, fontSize =30.sp, modifier=Modifier.align(Alignment.Center))

}}

@Composable
fun form() {

    Column(
        modifier = Modifier.padding(top = 100.dp, start = 30.dp)
    ) {
        var fullname by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var pwd by remember { mutableStateOf("") }
        var invisiblepwd by remember { mutableStateOf(true) }
        var errorphonefield by remember { mutableStateOf(false) }


        //champ fullname
        TextField(
            modifier = Modifier.padding(bottom = 30.dp)
                .width(300.dp),
            value = fullname,
            onValueChange = { fullname = it },
            label = {  Text(text="Nom et Prénom",color=Color(0xFF0078B8)) },
            placeholder = { Text(text = "Saisir nom et prénom")},
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "user",
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedContainerColor = Color(0xFFF0F0F0),
                focusedContainerColor = Color(0xFFF0F0F0),
                cursorColor = Color.Black
            )
        )
        //champs email
        TextField(
            modifier = Modifier.padding(bottom = 30.dp)
                .width(300.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email",color=Color(0xFF0078B8)) },
            placeholder ={ Text(text = "Saisir email") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "email",
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedContainerColor = Color(0xFFF0F0F0),
                focusedContainerColor = Color(0xFFF0F0F0),
                cursorColor = Color.Black
            )
        )
        // champ phone
        TextField(
            modifier = Modifier.padding(bottom = 5.dp)
                .width(300.dp),
            value = phone,
            onValueChange = { phone = it
                            errorphonefield=phone.length!=8
                           },
            label = { Text(text = "Téléphone",color=Color(0xFF0078B8)) },
            placeholder = { Text(text ="Saisir téléphone") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "call",
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedContainerColor = Color(0xFFF0F0F0),
                focusedContainerColor = Color(0xFFF0F0F0),
                cursorColor = Color.Black
            )
        )
         if(errorphonefield){
             Text(text="Numéro du téléphone est invalide",color=Color.Red,fontSize = 12.sp)
         }
        //champs password
        TextField(
            visualTransformation = if (!invisiblepwd) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.padding(top=30.dp,bottom = 30.dp)
                .width(300.dp),
            value = pwd,
            onValueChange = { pwd = it },
            label = { Text(text = "Password",color=Color(0xFF0078B8)) },
            placeholder = { Text(text ="Saisir password" )},
            trailingIcon = {
                IconButton(onClick = { invisiblepwd = !invisiblepwd }) {
                    if (invisiblepwd == true) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "HideEye",
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "HideEye",
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedContainerColor = Color(0xFFF0F0F0),
                focusedContainerColor = Color(0xFFF0F0F0),
                cursorColor = Color.Black
            )
        )


        //signup button
        var context=LocalContext.current
        Button(
            onClick = {
                if (fullname.isEmpty() || email.isEmpty() || pwd.isEmpty() || phone.isEmpty()){
                    Toast.makeText(context,"Vous devez saisir tous les champs!",Toast.LENGTH_SHORT).show()
                }
                else if(!fullname.isEmpty()&&!email.isEmpty()&&!pwd.isEmpty()&&errorphonefield==false){
                    val user = User(fullname,email,phone,pwd)
                    RetrofitClient.instance.signup(user).enqueue(object : retrofit2.Callback<Void> {
                        override fun onResponse(call: retrofit2.Call<Void>, response: retrofit2.Response<Void>) {
                            if (response.isSuccessful) {
                               Toast.makeText(context,"Compte crée avec succée!",Toast.LENGTH_SHORT).show()
                                var intent=Intent(context, HomeActivity::class.java)
                                context.startActivity(intent)

                            } else {
                                Toast.makeText(context,"Erreur lors de l’inscription",Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                        Toast.makeText(context,"Erreur : ${t.message}",Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            },
            modifier = Modifier.fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color(0xFF0078B8))
        ) {
            Text(text = "S'inscrire")
        }

    }
}



class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column() {
           navbar(title="Crée votre compte")
            form()
        }}
    }
}


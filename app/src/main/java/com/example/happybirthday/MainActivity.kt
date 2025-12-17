package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme
import androidx.compose.material3.Button
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.border
import androidx.compose.foundation.background

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFE4E1) // 薄い緑色の背景
                ) {
                    val birthdayUser = ContactInfo(
                        name = "Enomoto",
                        email = "Happy Birthday!",
                        phone = "🎂✨🎉"
                    )
                    BusinessCard(info = birthdayUser)
                }
            }
        }
    }
}

@Composable
fun BusinessCard(info: ContactInfo) {
    var isDetailVisible by remember { mutableStateOf(false)}

    // 画面の真ん中に寄せるための箱
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_android_logo),
            contentDescription = "Android Logo",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(2.dp, Color.Gray, CircleShape)
                .padding(16.dp)
        )
        // カード部分
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = info.name,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF073042)
                )

                // ボタンの追加
                Button(
                    onClick = { isDetailVisible = !isDetailVisible },
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(if (isDetailVisible) "Hide Details" else "Show Details")
                }
                if (isDetailVisible) {
                    Text(text = info.email)
                    Text(text = info.phone)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {
    HappyBirthdayTheme {
        val testData = ContactInfo(
            name = "Preview User",
            email = "test@example.com",
            phone = "000-0000-0000"
        )
        BusinessCard(info = testData)
    }
}

data class ContactInfo(val name: String, val email: String, val phone: String)
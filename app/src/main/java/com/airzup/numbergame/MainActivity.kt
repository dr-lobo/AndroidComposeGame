package com.airzup.numbergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airzup.numbergame.ui.theme.GameTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      GameTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          Scaffold(
            topBar = {
              TopAppBar { Text("Maths is Fun") }
            }
          ) {
            Column(Modifier.fillMaxWidth()) {
              Card(
                Modifier
                  .fillMaxWidth()
                  .padding(16.dp),
                elevation = 4.dp
              ) {
                Text(
                  modifier = Modifier.padding(16.dp),
                  text = "GAME 1"
                )
              }
            }
          }
        }
      }
    }
  }
}
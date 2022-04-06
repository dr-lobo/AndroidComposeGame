package com.example.game

import android.os.CountDownTimer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game.ui.theme.GameTheme

@Composable
fun Game1() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    val val2 = (1..5).random()
    val val1 = (1..100).random()
    var correct by remember {
      mutableStateOf(false)
    }

    var ended by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {

      CountDownTimerSync(!ended) { ended = true }
      if (!ended) {
        Score(score)
      }
    }
    if (ended) {
      Text("You scored " + score)
      Spacer(modifier = Modifier.weight(1f))
      TextButton(
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(90), // = 50% percent
        // or shape = CircleShape
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        onClick = {
          score = 0
          ended = false

        }) {
        Text(
          modifier = Modifier.padding(20.dp),
          fontWeight = FontWeight.Bold,
          fontSize = 40.sp,
          text = "Start Again"
        )
      }
    } else {
      if (correct) Text("CORRECT") else ""
      Spacer(Modifier.weight(1F))
      Questions(val1, val2)
      Spacer(Modifier.weight(1F))
      Answers(val1 + val2) {
        correct = checkAnswer(val1, val2, answer = it)
        if (correct) score++ else score--
      }

      correct = false
    }
  }
}

@Composable fun Score(score: Int) {
  Text(
    fontWeight = FontWeight.Bold,
    fontSize = 70.sp,
    color = Color.Green,
    text = score.toString()
  )
}

@Composable
private fun Answers(
  answer: Int,
  checkAnswer: (answer: Int) -> Unit
) {
  var answer1 = answer + 1
  var answer2 = answer + 2
  var answer3 = answer

  Card(
    elevation = 10.dp,
    backgroundColor = Color.DarkGray
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp, bottom = 5.dp),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically
    ) {
      val l = listOf(answer1, answer2, answer3).shuffled()

      AnswerButton(l[0]) { checkAnswer(it) }
      AnswerButton(l[1]) { checkAnswer(it) }
      AnswerButton(l[2]) { checkAnswer(it) }
    }
  }
}

@Composable
private fun AnswerButton(
  answer: Int,
  onClick: (answer: Int) -> Unit
) {
  TextButton(
    border = BorderStroke(1.dp, Color.Black),
    shape = RoundedCornerShape(90),
    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
    onClick = { onClick(answer) }) {
    Text(
      modifier = Modifier.padding(20.dp),
      fontWeight = FontWeight.Bold,
      fontSize = 30.sp,
      text = answer.toString()
    )
  }
}

@Composable
private fun Questions(
  val1: Int,
  val2: Int
) {
  Card(
    elevation = 10.dp,
    backgroundColor = Color.DarkGray
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Card(
        modifier = Modifier
          .weight(3f)
          .padding(10.dp),
        elevation = 10.dp
      ) {
        Text(
          modifier = Modifier.padding(30.dp),
          fontWeight = FontWeight.Bold,
          fontSize = 50.sp,
          maxLines = 1,
          text = val1.toString()
        )
      }
      Text(
        text = "+",
        modifier = Modifier.padding(10.dp),
        color = Color.White,
        fontSize = 50.sp,
      )
      Card(
        modifier = Modifier
          .weight(3f)
          .padding(10.dp),
        elevation = 10.dp
      ) {
        Box(contentAlignment = Alignment.Center) {
          Text(
            modifier = Modifier.padding(30.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            text = val2.toString()
          )
        }

      }
    }
  }
}

fun checkAnswer(
  val1: Int,
  val2: Int,
  answer: Int
) =
  val1 + val2 == answer

@Composable
private fun CountDownTimerSync(
  start: Boolean,
  ended: () -> Unit
) {

  var timer: String by remember { mutableStateOf("") }

  Text(
    fontWeight = FontWeight.Bold,
    fontSize = 70.sp,
    color = Color.Red,
    text = timer
  )

  var running by remember {
    mutableStateOf(false)
  }
  LaunchedEffect(key1 = start && !running) {
    running = true
    object : CountDownTimer(20000, 1000) {
      override fun onTick(millisUntilFinished: Long) {
        timer = "" + millisUntilFinished / 1000
      }

      override fun onFinish() {
        timer = "Game Over!"
        running = false
        ended()
      }
    }.start()
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  GameTheme {
    Game1()
  }
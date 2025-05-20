package com.example.truefalsegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Question(val text: String, val answer: Boolean)

val questions = listOf(
    Question("Android is an operating system", true),
    Question("Kotlin is officially supported for Android development", false),
    Question("Windows is better than mac", false),
    )
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrueFalseGame()
        }
    }
}

@Composable
fun TrueFalseGame() {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var userAnswer by remember { mutableStateOf<Boolean?>(null) }

    val currentQuestion = questions[currentQuestionIndex]
    val isCorrect = userAnswer == currentQuestion.answer
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = currentQuestion.text,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))

        when (userAnswer) {
            true, false -> {
                if (isCorrect) {
                    CorrectAnswerText()
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (currentQuestionIndex < questions.size - 1) {
                                currentQuestionIndex++
                                userAnswer = null
                            }
                        }
                    ) {
                        Text("Next Question")
                    }
                } else {
                    WrongAnswerText()
                }
            }
            null -> {}
        }
        Spacer(modifier = Modifier.width(16.dp))

        if (userAnswer == null || !isCorrect) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { userAnswer = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("True")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { userAnswer = false },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("False")
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))



    }
}

@Composable
fun CorrectAnswerText() {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .border(4.dp, MaterialTheme.colorScheme.primary, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Correct Answer!",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun WrongAnswerText() {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .border(4.dp, MaterialTheme.colorScheme.error, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Wrong Answer!",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }
}
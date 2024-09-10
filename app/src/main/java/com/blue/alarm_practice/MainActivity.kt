package com.blue.alarm_practice

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.blue.alarm_practice.ui.theme.Alarm_PracticeTheme
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var alarmHelper: AlarmFunctions

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        alarmHelper = AlarmFunctions(this)


        setContent {
            Alarm_PracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Test(
                        "Android",
                        Modifier,
                        mainViewModel,
                        alarmHelper::callAlarm,
                        alarmHelper::cancelAlarm
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Test(
    name: String,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    setAlarm: (Date, Int, String) -> Unit,
    cancelAlarm: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Button(onClick = {
            val localDateTime = LocalDateTime.now().plusMinutes(1) // 현재 시간을 LocalDateTime으로 생성
            val zoneId = ZoneId.systemDefault() // 시스템의 기본 시간대 정보를 사용
            val instant = localDateTime.atZone(zoneId).toInstant() // LocalDateTime을 Instant로 변환
            setAlarm(Date.from(instant), Random.nextInt(1000), "hello")
        }) {
            Text(text = "Set")
        }

        Button(onClick = { cancelAlarm(9999) }) {
            Text(text = "Remove")
        }

        Text(
            text = "Hello $name!",
            modifier = modifier
        )

    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Alarm_PracticeTheme {
//        Test("Android")
//    }
//}
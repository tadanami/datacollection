package com.example.datacollection.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

class MainActivity : ComponentActivity() {

    private val viewModel: RoutineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRoutineData()

        var printData : List<Map<String, Any>>?

        //routineDataが変化した時のみ呼び出される
        viewModel.routineData.observe(this) {
            printData = it
            println("変わったよ〜ん")
            println(printData)
        }

        setContent {
            WearApp(viewModel)
        }
    }
}

/*@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}
 */
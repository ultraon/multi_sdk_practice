package com.ultraon.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.ultraon.calc_sdk.CalcApiSdk
import com.ultraon.calc_sdk_ui.showSizeRecommendation
import com.ultraon.sample.ui.theme.SampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(key1 = Unit) {
                // the call can be used for initialization & configuration.
                CalcApiSdk.init(application)
            }

            SampleTheme {
                LaunchDemoScreen()
            }
        }
    }
}

@Composable
fun LaunchDemoScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val context = LocalContext.current
            Button(onClick = {
                CalcApiSdk.showSizeRecommendation(activity = context)
            }) {
                Text(stringResource(R.string.launch_demo))
            }
        }
    }
}

@PreviewLightDark
@Composable
fun LaunchDemoScreenPreview() {
    LaunchDemoScreen()
}

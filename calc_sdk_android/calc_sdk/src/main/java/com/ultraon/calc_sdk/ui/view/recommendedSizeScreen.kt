package com.ultraon.calc_sdk.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ultraon.calc_sdk.R
import com.ultraon.calc_sdk.ui.theme.SampleTheme

@Composable
fun RecommendedSizeScreen(
    modifier: Modifier = Modifier,
    size: String,
    onOkClick: () -> Unit = {},
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.calc_sdk_your_recommended_size, size),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.calc_sdk_size_recommended, size))
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.defaultMinSize(minWidth = 120.dp),
                onClick = onOkClick,
            ) {
                Text(stringResource(R.string.calc_sdk_ok))
            }
        }
    }
}

@PreviewLightDark
@Composable
fun RecommendedSizeScreenPreview() {
    SampleTheme {
        RecommendedSizeScreen(size = "S")
    }
}

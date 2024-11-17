package com.ultraon.calc_sdk_ui.view

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.ultraon.calc_sdk_ui.R

@Composable
fun NumberInput(
    value: Int,
    label: String,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (Int) -> Unit,
) {
    val text = if (value > 0) "$value" else ""
    OutlinedTextField(
        value = text,
        label = { Text(label) },
        singleLine = true,
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = { onValueChange(0) }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.calc_sdk_clear_text),
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = imeAction,
        ),
        keyboardActions = keyboardActions,
        onValueChange = { newValue ->
            onValueChange(newValue.toIntOrNull() ?: 0)
        },
    )
}

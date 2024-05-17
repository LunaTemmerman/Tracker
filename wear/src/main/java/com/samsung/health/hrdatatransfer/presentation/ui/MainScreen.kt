/*
 * Copyright 2023 Samsung Electronics Co., Ltd. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.samsung.health.hrdatatransfer.presentation.ui

import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.samsung.health.hrdatatransfer.presentation.theme.HRDataTransferTheme

private const val TAG = "MainScreen"

@Composable
fun MainScreen(
    valueHR: String,
    valueIBI: ArrayList<Int>,
    onStart:() -> Unit,
    onSend:() -> Unit
) {
    HRDataTransferTheme {
        onStart()
        onSend()
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.width(90.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    color = Color.LightGray,
                    text = "HR",
                    fontSize = 11.sp,
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    color = Color.White,
                    text = valueHR,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.size(11.dp))
                Box(
                    modifier = Modifier.size(54.dp)
                ) {
                    // No buttons for tracking or sending data
                }
            }
            Spacer(Modifier.size(2.dp))
            Column(
                modifier = Modifier.width(90.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    color = Color.LightGray,
                    text = "IBI",
                    fontSize = 11.sp,
                    textAlign = TextAlign.Right
                )
                Spacer(modifier = Modifier.size(5.dp))
                valueIBI.forEachIndexed { index, ibi ->
                    Text(
                        color = Color.White,
                        text = ibi.toString(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(Modifier.size(11.dp))
            }
        }
    }
}

@Composable
fun ShowConnectionMessage(
    connected: Boolean,
    connectionMessage: String
) {
    Log.i(TAG, "connectionMessage: $connectionMessage, connected: $connected")
    if (connectionMessage != "" && connected) {
        ShowToast(connectionMessage)
    }
}

@Composable
fun ShowToast(message: String) {
    makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}

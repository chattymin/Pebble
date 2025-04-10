package com.chattymin.textgrapheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chattymin.pebble.graphemeLength
import com.chattymin.textgrapheme.ui.theme.TextGraphemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextGraphemeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val scrollState = rememberScrollState()

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                            .verticalScroll(scrollState)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        var text by remember { mutableStateOf("") }

                        TextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Input Anything Here!")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = "Length: ${text.graphemeLength}"
                        )
                    }
                }
            }
        }
    }
}

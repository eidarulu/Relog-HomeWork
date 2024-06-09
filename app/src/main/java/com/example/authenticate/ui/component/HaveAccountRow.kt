package com.example.authenticate.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authenticate.R

@Composable
fun HaveAccountRow(
    onSignupTap: () -> Unit = {},
    question: String,
    link: String
) {
    Row(
        modifier = Modifier.padding(top=12.dp, bottom = 52.dp)
    ){
        Text(
            text = question,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )
        )

        Text(
            text = link,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight(800),
                color = Color.White
            ),
            modifier = Modifier.clickable {
                onSignupTap()
            }
        )
    }
}
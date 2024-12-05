package com.febriandi.agrojaya.screens.home.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febriandi.agrojaya.R
import com.febriandi.agrojaya.ui.theme.CustomFontFamily

@Composable
fun HomeSearchField(
    search: String,
    onSearchChange: (String) -> Unit
) {
    OutlinedTextField(
        value = search,
        onValueChange = onSearchChange,
        placeholder = {
            Text(
                text = "Telusuri",
                fontFamily = CustomFontFamily
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(30.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.green_500),
            unfocusedBorderColor = colorResource(id = R.color.stroke_form),
            unfocusedContainerColor = colorResource(id = R.color.green_100),
            focusedContainerColor = colorResource(id = R.color.green_100)
        ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = colorResource(id = R.color.text_color),
            fontFamily = CustomFontFamily
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 20.dp)
            .height(50.dp),
        trailingIcon = {
            IconButton(onClick = { /* Handle search action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_search),
                    contentDescription = "Pencarian",
                    tint = colorResource(id = R.color.text_color),
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    )
}
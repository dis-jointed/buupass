package com.zeroday.buupass.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.zeroday.buupass.R


@Composable
fun NavButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String
) {

    val colorbg = if (isSelected) colorResource(id = R.color.buttonActive) else Color.White
    val colortxt = if (isSelected) Color.White else Color.LightGray


    Card(
        shape = RoundedCornerShape(26.dp),
        backgroundColor = colorbg,
        contentColor = colortxt,
        modifier = modifier
    ) {


        //assists to center the text
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        ) {

            //ref for the text
            val txt = createRef()

            Text(
                text = text,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.ceraproregular)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.constrainAs(txt) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                }
            )

        }
    }
}
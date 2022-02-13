package com.zeroday.buupass.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.zeroday.buupass.R

@Composable
fun SignInScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    emailPlaceholder: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordPlaceholder: String,
    pushData: () -> Unit,
    authenticateUserF: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {

        Image(
            painter = painterResource(id = R.drawable.cab),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        val verticalBottom = Brush.verticalGradient(
            0.5f to Color.Blue,
            0.7f to Color.Blue.copy(alpha = 0.65f),
            0.8f to Color.Blue.copy(0.85f),
            0.9f to Color.Blue,
            startY = 0.0f,
            endY = Float.POSITIVE_INFINITY
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(brush = verticalBottom)
        ) {

        }

        ConstraintLayout(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Transparent)
                .padding(
                    top = 88.dp,
                    start = 12.dp,
                    end = 10.dp
                )
        ) {
            val (splashLogo, splashtxt, splashUserNEdit, splashPassEdit, forgotTxt, loginBtn, fireloginBtn) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_favplace),
                contentDescription = "splash Logo",
                modifier = Modifier.constrainAs(splashLogo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

            )

            Text(
                text = "driveit", style = TextStyle(
                    color = Color.White, fontSize = 53.sp, fontFamily = FontFamily(
                        Font(R.font.ubuntubold)
                    ), fontWeight = FontWeight.Bold
                ), modifier = Modifier.constrainAs(splashtxt) {
                    top.linkTo(splashLogo.bottom, 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            TextField(
                value = email,
                onValueChange = {
                    onEmailChange(it)
                },
                placeholder = {
                    Text(
                        text = emailPlaceholder,
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(splashUserNEdit) {
                        top.linkTo(splashtxt.bottom, 50.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.ubuntumedium)),
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = MaterialTheme.shapes.small.copy(CornerSize(48.dp)),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            TextField(
                value = password,
                onValueChange = {
                    onPasswordChange(it)
                },
                placeholder = {
                    Text(
                        text = passwordPlaceholder,
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(splashPassEdit) {
                        top.linkTo(splashUserNEdit.bottom, 32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.ubuntumedium)),
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = MaterialTheme.shapes.small.copy(CornerSize(48.dp)),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    pushData()
                }),
                visualTransformation = PasswordVisualTransformation()
            )

            Text(
                text = "Forgot Password",
                modifier = Modifier.constrainAs(forgotTxt) {
                    top.linkTo(splashPassEdit.bottom, 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.ubuntulight)),
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp
                )
            )

            Button(
                onClick = {       /*TODO
                Post data to api endpoint
      */
                    pushData()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(loginBtn) {
                        top.linkTo(forgotTxt.bottom, 36.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White.copy(0.95f)
                ),
                shape = MaterialTheme.shapes.small.copy(CornerSize(48.dp))
            ) {
                Text(
                    text = "LOG IN",
                    modifier = Modifier.padding(
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.ubuntubold)),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                )
            }

            Button(
                onClick = {
                    /*TODO
                    authenticate using firebase API
          */
                    authenticateUserF()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(fireloginBtn) {
                        top.linkTo(loginBtn.bottom, 36.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White.copy(0.95f)
                ),
                shape = MaterialTheme.shapes.small.copy(CornerSize(48.dp))
            ) {
                Text(
                    text = "FIRE LOGIN",
                    modifier = Modifier.padding(
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.ubuntubold)),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                )
            }

        }
    }


}
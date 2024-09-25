package com.example.lootlearn.utils

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.lootlearn.presentation.ui.theme.AuthScreenPurpleText
import com.example.lootlearn.presentation.ui.theme.Poppins
import com.example.lootlearn.presentation.ui.theme.PrivacyTextColor
import com.example.lootlearn.presentation.ui.theme.errorRed
import com.example.lootlearn.presentation.ui.theme.otpTextColor
import com.example.lootlearn.presentation.ui.theme.passwordGreen
import com.example.lootlearn.presentation.ui.theme.verificationText


fun gradientBrush(
    colors : List<Color>,
    isVertical : Boolean
) : Brush {

    val endOffset = if (isVertical){
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset(0f, 0f),
        end = endOffset,
        tileMode = TileMode.Clamp
    )
}

fun annotatedLoginSignupString(
    signupOrLogin : String,
) : AnnotatedString {

    val annotatedLoginSignupString: AnnotatedString = buildAnnotatedString {
        val str = "Already have an account? $signupOrLogin"
        val startIndex = str.indexOf(signupOrLogin)
        val endIndex = startIndex + signupOrLogin.length

        val startIndexNormal = str.indexOf("Already have an account?")
        val endIndexNormal = startIndexNormal + "Already have an account?".length


        append(str)
        addStyle(
            style = SpanStyle(
                color = AuthScreenPurpleText,
                textDecoration = TextDecoration.Underline,
                fontFamily = Poppins,
                fontWeight = FontWeight(500),
                fontSize = 16.sp
            ), start = startIndex, end = endIndex
        )
        addStyle(
            style = SpanStyle(
                color = AuthScreenPurpleText,
                fontFamily = Poppins,
                fontWeight = FontWeight(300),
                fontSize = 14.sp
            ), start = startIndexNormal, end = endIndexNormal
        )
        addStringAnnotation(
            tag = "navigate",
            annotation = signupOrLogin,
            start = startIndex,
            end = endIndex
        )
    }

    return annotatedLoginSignupString
}


//    ClickableText(
//        modifier = Modifier
//            .padding(20.dp)
//            .fillMaxWidth(),
//        text = annotatedLinkString,
//        onClick = { offset ->
//            annotatedLinkString
//                .getStringAnnotations(tag = "navigate", start = offset, end = offset)
//                .firstOrNull()?.let { annotation ->
////                            navController.navigate(annotation.item) // Navigate to the destination screen
//                }
//        }
//    )

fun annotatedPrivacyPolicyString() : AnnotatedString {
    val annotatedLoginSignupString: AnnotatedString = buildAnnotatedString {
        val str = "By joining, you agree to the LootLearn Terms of Services and Privacy policy"
        val startIndex1 = str.indexOf("Terms of Services")
        val endIndex1 = startIndex1 + "Terms of Services".length

        val startIndexNormal1 = str.indexOf("By joining, you agree to the LootLearn")
        val endIndexNormal1 = startIndexNormal1 + "By joining, you agree to the LootLearn".length

        val startIndexNormal2 = str.indexOf("and")
        val endIndexNormal2 = startIndexNormal2 + "and".length

        val startIndex2 = str.indexOf("Privacy policy")
        val endIndex2 = startIndex2 + "Privacy policy".length


        append(str)
        addStyle(
            style = SpanStyle(
                color = PrivacyTextColor,
                textDecoration = TextDecoration.Underline,
                fontFamily = Poppins,
                fontWeight = FontWeight(400),
                fontSize = 13.sp
            ), start = startIndex1, end = endIndex1
        )
        addStyle(
            style = SpanStyle(
                color = PrivacyTextColor,
                textDecoration = TextDecoration.Underline,
                fontFamily = Poppins,
                fontWeight = FontWeight(400),
                fontSize = 13.sp
            ), start = startIndex2, end = endIndex2
        )
        addStyle(
            style = SpanStyle(
                color = PrivacyTextColor,
                fontFamily = Poppins,
                fontWeight = FontWeight(400),
                fontSize = 13.sp
            ), start = startIndexNormal1, end = endIndexNormal1
        )
        addStyle(
            style = SpanStyle(
                color = PrivacyTextColor,
                fontFamily = Poppins,
                fontWeight = FontWeight(400),
                fontSize = 13.sp
            ), start = startIndexNormal2, end = endIndexNormal2
        )

        addStringAnnotation(
            tag = "navigate",
            annotation = "Terms of Services",
            start = startIndex1,
            end = endIndex1
        )

        addStringAnnotation(
            tag = "navigate",
            annotation = "Privacy policy",
            start = startIndex2,
            end = endIndex2
        )
    }
    return annotatedLoginSignupString
}

fun annotatedResendString() : AnnotatedString {

    val annotatedResendString: AnnotatedString = buildAnnotatedString {
        val str = "If you didn't receive a code! Resend"
        val startIndex = str.indexOf("Resend")
        val endIndex = startIndex + "Resend".length

        val startIndexNormal = str.indexOf("If you didn't receive a code!")
        val endIndexNormal = startIndexNormal + "If you didn't receive a code!".length


        append(str)
        addStyle(
            style = SpanStyle(
                color = verificationText,
                textDecoration = TextDecoration.Underline,
                fontFamily = Poppins,
                fontWeight = FontWeight(500),
                fontSize = 14.sp
            ), start = startIndex, end = endIndex
        )
        addStyle(
            style = SpanStyle(
                color = otpTextColor,
                fontFamily = Poppins,
                fontWeight = FontWeight(400),
                fontSize = 14.sp
            ), start = startIndexNormal, end = endIndexNormal
        )
        addStringAnnotation(
            tag = "navigate",
            annotation = "Resend",
            start = startIndex,
            end = endIndex
        )
    }
    return annotatedResendString
}

//@Composable
//fun backDispatcher(){
//    LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
//}

fun annotatedPasswordHintText() : AnnotatedString {

    val annotatedPasswordHintText: AnnotatedString = buildAnnotatedString {
        val str = "At least Contain: 8 characters, One lowercase, One Uppercase, One Special characters, One number."
        val startIndexOne = str.indexOf("At least Contain:")
        val endIndexOne = startIndexOne + "At least Contain:".length

        val startIndexSecond = str.indexOf("8 characters, One lowercase, One Uppercase, One Special characters,")
        val endIndexSecond = startIndexSecond + "8 characters, One lowercase, One Uppercase, One Special characters,".length

        val startIndexThird = str.indexOf("One number.")
        val endIndexThird = startIndexThird + "One number.".length



        append(str)
        addStyle(
            style = SpanStyle(
                color = otpTextColor,
                fontFamily = Poppins,
                fontWeight = FontWeight(400),
                fontSize = 11.sp
            ), start = startIndexOne, end = endIndexOne
        )
        addStyle(
            style = SpanStyle(
                color = passwordGreen,
                fontFamily = Poppins,
                fontWeight = FontWeight(400),
                fontSize = 11.sp
            ), start = startIndexSecond, end = endIndexSecond
        )
        addStyle(
            style = SpanStyle(
                color = errorRed,
                fontFamily = Poppins,
                fontWeight = FontWeight(400),
                fontSize = 11.sp
            ), start = startIndexThird, end = endIndexThird
        )
    }
    return annotatedPasswordHintText
}




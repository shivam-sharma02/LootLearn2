package com.fourtysix.lootlearn.utils

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash_screen")
    object AuthChoiceScreen : Screen("auth_choice_screen")
    object LogInScreen : Screen("login_screen")
    object ForgotPasswordScreen : Screen("forgot_password_screen")
    object SignUpScreen: Screen("sign_up_screen")
    object MainFeedScreen: Screen("main_feed_screen")
    object OtpVerificationScreen: Screen("otp_verification_screen")
    object NewPasswordScreen: Screen("new_password_screen")
    object CongratulationScreen: Screen("congratulation_screen")
    object BookingScreen: Screen("booking_screen")
    object NewItemScreen: Screen("new_screen")
    object WalletScreen: Screen("wallet_screen")
    object ChatScreen: Screen("chat_screen")

}
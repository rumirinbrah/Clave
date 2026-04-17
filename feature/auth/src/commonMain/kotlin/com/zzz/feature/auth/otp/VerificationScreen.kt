package com.zzz.feature.auth.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.core.ui.domain.network.UIEvent
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun VerifyOtpScreen(
    email: String,
    viewModel: OtpViewModel = koinViewModel(),
    onOtpVerified: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is UIEvent.Success -> onOtpVerified()
                is UIEvent.Error -> {
                    // show snackbar later
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Verify OTP",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enter the code sent to\n$email",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        OtpInputField(
            otp = uiState.otp,
            onOtpChange = viewModel::onOtpChange
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.verifyOtp(email)
            },
            enabled = uiState.otp.length == 6,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Verify")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(
                text = "Didn’t receive code? ",
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
            )
            Text(
                text = "Resend",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    viewModel.resendOtp(email)
                }
            )

            Text(
                text = if (uiState.resendEnabled) {
                    "Resend"
                } else {
                    "Resend in ${uiState.secondsLeft}s"
                },
                color = if (uiState.resendEnabled)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onBackground.copy(0.4f),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable(
                    enabled = uiState.resendEnabled
                ) {
                    viewModel.resendOtp(email)
                }
            )
        }
    }
}

@Composable
fun OtpInputField(
    otp: String,
    onOtpChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        value = otp,
        onValueChange = {
            if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                onOtpChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
            .alpha(0f)
            .fillMaxWidth()
            .height(1.dp)
    )

    LaunchedEffect(Unit) {
        delay(150)
        focusRequester.requestFocus()
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(6) { index ->

            val char = otp.getOrNull(index)?.toString() ?: ""

            val isFocused = otp.length == index

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 1.5.dp,
                        color = if (isFocused)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = char,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

//@Composable
//fun VerifyOtpScreen(
//    email: String,
//    onOtpVerified: () -> Unit
//) {
//
//    var otp by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF5F5F5))
//            .padding(horizontal = 24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Spacer(modifier = Modifier.height(60.dp))
//
////        Image(
////            painter = painterResource(id = R.drawable),
////            contentDescription = null,
////            modifier = Modifier.size(180.dp),
////            contentScale = ContentScale.Fit
////        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Text(
//            text = "Verify OTP",
//            style = MaterialTheme.typography.headlineSmall,
//            fontWeight = FontWeight.Bold
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        Text(
//            text = "Enter the OTP that was\nsent to $email",
//            style = MaterialTheme.typography.bodyMedium,
//            color = Color.Gray,
//            textAlign = TextAlign.Center
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        OtpInputField(
//            otp = otp,
//            onOtpChange = {
//                if (it.length <= 4 && it.all { char -> char.isDigit() }) {
//                    otp = it
//                }
//            }
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Verify otp
//        Button(
//            onClick = { /* verify logic */ },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            shape = RoundedCornerShape(12.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF1A1A5E)
//            )
//        ) {
//            Text("Verify OTP")
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        // Resend otp
//        Row {
//            Text(
//                text = "Haven’t received any code? ",
//                color = Color.Gray
//            )
//            Text(
//                text = "Resend code",
//                color = Color(0xFF1A1A5E),
//                fontWeight = FontWeight.SemiBold,
//                modifier = Modifier.clickable {
//                    // resend logic
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun OtpInputField(
//    otp: String,
//    onOtpChange: (String) -> Unit
//) {
//    val focusRequester = remember { FocusRequester() }
//
//    BasicTextField(
//        value = otp,
//        onValueChange = onOtpChange,
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.NumberPassword
//        ),
//        modifier = Modifier
//            .focusRequester(focusRequester)
//            .width(0.dp)
//            .height(0.dp)
//    )
//
//    LaunchedEffect(Unit) {
//        focusRequester.requestFocus()
//    }
//
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        repeat(4) { index ->
//            val char = when {
//                index >= otp.length -> ""
//                else -> otp[index].toString()
//            }
//
//            Box(
//                modifier = Modifier
//                    .size(55.dp)
//                    .border(
//                        width = 1.dp,
//                        color = Color.Gray,
//                        shape = RoundedCornerShape(8.dp)
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = char,
//                    style = MaterialTheme.typography.titleLarge
//                )
//            }
//        }
//    }
//}
//

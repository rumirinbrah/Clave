package com.zzz.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.GradientButton
import com.zzz.core.ui.presentation.components.RoundedInputField

@Composable
fun SignUpScreen() {

    var selectedRole by remember { mutableStateOf("Coordinator") }
    var rollNo by remember { mutableStateOf("") }
    var mobileNo by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.White, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "C",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E6F73)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "WELCOME TO CLAVE",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Let's set you up!",
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        RoleToggle(
            selected = selectedRole,
            onSelect = { selectedRole = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Name
        RoundedInputField(
            value = name,
            onValueChange = { name = it },
            placeholder = "Enter your Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Roll No
        RoundedInputField(
            value = rollNo,
            onValueChange = { rollNo = it },
            placeholder = "Enter your RollNo"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mobile No
        RoundedInputField(
            value = mobileNo,
            onValueChange = { mobileNo = it },
            placeholder = "Enter your MobileNo"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Password
        RoundedInputField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Enter your Password"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text(text = "Have an account? ")
            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable{

                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            text = "Create account",
            onClick = { }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
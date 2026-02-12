package com.zzz.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.core.ui.presentation.components.GradientButton
import com.zzz.core.ui.presentation.components.RoundedInputField

@Composable
fun LoginScreen() {

    var selectedRole by remember { mutableStateOf("Coordinator") }
    var rollNo by remember { mutableStateOf("") }
    var mobileNo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color(0xFFFDFDF9))
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
            text = "Using the app as",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        RoleToggle(
            selected = selectedRole,
            onSelect = { selectedRole = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

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

        Row {
            Text(text = "New User? ")
            Text(
                text = "Register",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable{

                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            text = "Login",
            onClick = { }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun RoleToggle(
    selected: String,
    onSelect: (String) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSurface, RoundedCornerShape(30.dp))
            .padding(4.dp)
    ) {

        val itemWidth = maxWidth / 2

        Row {
            RoleItem(
                text = "Student",
                isSelected = selected == "Student",
                onClick = { onSelect("Student") },
                width = itemWidth
            )

            RoleItem(
                text = "Coordinator",
                isSelected = selected == "Coordinator",
                onClick = { onSelect("Coordinator") },
                width = itemWidth
            )
        }
    }
}

@Composable
fun RoleItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    width: Dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .background(
                if (isSelected) Color.White else Color.Transparent,
                RoundedCornerShape(26.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.Black else Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}


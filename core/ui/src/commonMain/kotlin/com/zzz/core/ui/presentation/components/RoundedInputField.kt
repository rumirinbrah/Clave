package com.zzz.core.ui.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoundedInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
/**
 * LEADING ICON
 *
 * @param supportingText
 *
 * @author zyzz
 */
@Composable
fun NormalTextField(
    modifier: Modifier = Modifier.fillMaxWidth() ,
    value: String ,
    onValueChange: (String) -> Unit ,
    placeholder: String ,
    leadingIcon: DrawableResource? = null ,
    leadingIconLabel: String? = null ,
    trailingIcon: DrawableResource? = null ,
    trailingIconLabel: String? = null ,
    supportingText: String? = null ,
    titleText: String? = null ,
    titleTextStyle: TextStyle = MaterialTheme.typography.titleMedium ,
    background: Color = MaterialTheme.colorScheme.surfaceVariant ,
    onBackground: Color = MaterialTheme.colorScheme.onBackground ,
    borderColor: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f) ,
    fontSize: TextUnit = 16.sp ,
    fontWeight: FontWeight = FontWeight.Normal ,
    enabled: Boolean = true ,
    singleLine: Boolean = true ,
    maxLines: Int = 10 ,
    keyboardType: KeyboardType = KeyboardType.Unspecified ,
    imeAction: ImeAction = ImeAction.Unspecified ,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    shape: Shape = OutlinedTextFieldDefaults.shape ,
    onImeAction: () -> Unit = {}
) {
    if (titleText != null) {
        Column {
            Text(
                text = titleText ,
                style = titleTextStyle ,
                fontWeight = FontWeight.Bold
            )
            VerticalSpace(5.dp)
            OutlinedTextField(
                value = value ,
                onValueChange = onValueChange ,
                modifier = modifier
//                    .dropShadow(
//                        shape = shape,
//                        shadow = Shadow(
//                            radius = 5.dp,
//                            color = MaterialTheme.colorScheme.onBackground.copy(0.2f) ,
//                            spread = 1.dp,
//                            offset = DpOffset(x = 1.dp, y =1.dp)
//                        )
//                    )
                ,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = background ,
                    focusedContainerColor = background ,
                    unfocusedIndicatorColor = background ,
                    focusedIndicatorColor = borderColor
                ) ,
                shape = shape ,
                textStyle = TextStyle(
                    color = onBackground ,
                    fontSize = fontSize ,
                    fontWeight = fontWeight
                ) ,
                enabled = enabled ,
                maxLines = maxLines ,
                singleLine = singleLine ,
                placeholder = {
                    Text(
                        placeholder ,
                        style = TextStyle(
                            color = onBackground.copy(0.7f) ,
                            fontSize = fontSize ,
                            fontWeight = fontWeight
                        )
                    )
                } ,
                leadingIcon = {
                    if (leadingIcon != null) {
                        Icon(
                            painter = painterResource(leadingIcon) ,
                            contentDescription = leadingIconLabel ,
                            modifier = Modifier.size(25.dp) ,
                            tint = onBackground
                        )
                    } else {
                        null
                    }
                } ,
                trailingIcon = {
                    if (trailingIcon != null) {
                        Icon(
                            painter = painterResource(trailingIcon) ,
                            contentDescription = trailingIconLabel ,
                            modifier = Modifier.size(25.dp) ,
                            tint = onBackground
                        )
                    } else {
                        null
                    }
                },
                keyboardOptions = KeyboardOptions(
                    capitalization =capitalization,
                    keyboardType = keyboardType ,
                    imeAction = imeAction
                ) ,
                keyboardActions = KeyboardActions(
                    onAny = {
                        onImeAction()
                    },
                ) ,
                supportingText = if (supportingText != null) {
                    {
                        Text(supportingText)
                    }
                } else {
                    null
                }
            )
        }
    }else{
        OutlinedTextField(
            value = value ,
            onValueChange = onValueChange ,
            modifier = modifier ,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = background ,
                focusedContainerColor = background ,
                unfocusedIndicatorColor = background ,
                focusedIndicatorColor = borderColor
            ) ,
            shape = shape ,
            textStyle = TextStyle(
                color = onBackground ,
                fontSize = fontSize ,
                fontWeight = fontWeight
            ) ,
            enabled = enabled ,
            maxLines = maxLines ,
            singleLine = singleLine ,
            placeholder = {
                Text(
                    placeholder ,
                    style = TextStyle(
                        color = onBackground.copy(0.7f) ,
                        fontSize = fontSize ,
                        fontWeight = fontWeight
                    )
                )
            } ,
            leadingIcon = {
                if (leadingIcon != null) {
                    Icon(
                        painter = painterResource(leadingIcon) ,
                        contentDescription = leadingIconLabel ,
                        modifier = Modifier.size(25.dp) ,
                        tint = onBackground
                    )
                } else {
                    null
                }
            } ,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType ,
                imeAction = imeAction
            ) ,
            keyboardActions = KeyboardActions(
                onAny = {
                    onImeAction()
                }
            ) ,
            supportingText = if (supportingText != null) {
                {
                    Text(supportingText)
                }
            } else {
                null
            }
        )
    }


}




package com.ecommerceapp.component

import com.ecommerceapp.utils.InputType.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecommerceapp.data.TextFieldModel
import com.ecommerceapp.utils.FieldType

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}

@Composable
fun InputFieldNonEditable(
    modifier: Modifier = Modifier,
    value: String,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = Color(0xFFEE1A0A)
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
    )
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) },
        keyboardActions = onAction
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icons.Default.Close
    }
}


@Composable
fun Name() {
    val details = TextFieldModel()
    details.nameoffield = "Name"
    details.label = "Enter Name"
    details.maxcount = 100
    details.maxline = 1
    details.inputtypeoffield = TEXT
    details.typeoffield = FieldType.TEXT_EDIT
    details.visualtransformation = 0
    CustomTestField(details)
}

@Composable
fun Email() {
    val details = TextFieldModel()
    details.nameoffield = "Email"
    details.label = "Enter Email"
    details.maxcount = 100
    details.maxline = 1
    details.inputtypeoffield = EMAIL
    details.typeoffield = FieldType.TEXT_EDIT
    details.visualtransformation = 0
    CustomTestField(details)
}

@Composable
fun Mobile() {
    val details = TextFieldModel()
    details.nameoffield = "Mobile No."
    details.label = "Enter Mobile No."
    details.maxcount = 10
    details.maxline = 1
    details.inputtypeoffield = PHONE
    details.typeoffield = FieldType.TEXT_EDIT
    details.visualtransformation = 0
    CustomTestField(details)
}


@Composable
fun PinCode() {
    val details = TextFieldModel()
    details.nameoffield = "Pincode"
    details.label = "Enter Pincode"
    details.typeoffield = FieldType.TEXT_EDIT
    details.maxcount = 9
    details.maxline = 1
    details.inputtypeoffield = PHONE
    details.visualtransformation = 0
    CustomTestField(details)
}

@Composable
fun PasswordTextField() {
    val details = TextFieldModel()
    details.nameoffield = "Password"
    details.label = "Enter Password"
    details.typeoffield = FieldType.TEXT_EDIT
    details.maxcount = 64
    details.maxline = 1
    details.inputtypeoffield = TEXT
    details.visualtransformation = 1
    CustomTestField(details)
}

@Composable
fun CustomTestField(details: TextFieldModel) {
    var input by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = input,
        onValueChange = { textentered ->
            if (textentered.length <= details.maxcount) input = textentered
        },
        maxLines = details.maxline,
        singleLine = when (details.maxline) {
            1 -> true
            else -> false
        },
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(10.dp),
        label = { Text(details.label) },
        visualTransformation = when (details.visualtransformation) {
            1 -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = when (details.inputtypeoffield) {
                NUMBER -> KeyboardType.Number
                TEXT -> KeyboardType.Text
                PHONE -> KeyboardType.Phone
                PASSWORD -> KeyboardType.Password
                EMAIL -> KeyboardType.Email
            }
        )
    )
}


@Composable
fun FeedbackInputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}
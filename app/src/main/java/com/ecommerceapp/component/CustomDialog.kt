package com.ecommerceapp.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun CustomDialog(
    openDialogCustom: MutableState<Boolean>,
    title: String = "",
    details: String = "",
    icon: ImageVector = Icons.Default.Notifications,
    discardtext: String = "",
    accepttext: String = "",
    onDiscard: () -> Unit = {},
    onAccept: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = { openDialogCustom.value = false },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        CustomDialogUI(
            openDialogCustom = openDialogCustom,
            title = title,
            description = details,
            icon = icon,
            discardtext = discardtext,
            accepttext = accepttext,
            onDiscard = onDiscard,
            onAccept = onAccept
        )
    }
}

//Layout
@Composable
fun CustomDialogUI(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    onDiscard: () -> Unit,
    onAccept: () -> Unit,
    title: String,
    description: String,
    icon: ImageVector,
    discardtext: String = "",
    accepttext: String = "",
) {
    Card(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp,
                    bottomStart = 30.dp,
                    bottomEnd = 30.dp
                )
            ),
        elevation = 8.dp
    ) {
        Column(
            modifier
        ) {
            Icon(
                imageVector = icon, contentDescription = "Notifications",
                tint = Color.Red,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h4,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = description,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body1
                )
            }
            //.......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colors.primary),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = {
                    onDiscard()
                    openDialogCustom.value = false
                }) {
                    Text(
                        discardtext,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                TextButton(onClick = {
                    onAccept()
                    openDialogCustom.value = false
                }) {
                    Text(
                        accepttext,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W900,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}

package ru.markn.alfavitweb.pres.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ru.markn.alfavitweb.domain.models.InfoOrganization
import ru.markn.alfavitweb.pres.main.IMainActions
import ru.markn.alfavitweb.pres.main.MainUIState
import ru.markn.alfavitweb.pres.utils.AppTheme

@Composable
fun IMainActions.DialogInfoOrganization(state: MainUIState) {
    Dialog(
        onDismissRequest = {
            onInfoOrganizationMenuChange(false)
        },
    ) {
        Card(
            modifier = Modifier
                .sizeIn(maxWidth = state.window.width * 0.8f, maxHeight = state.window.height * 0.9f)
                .wrapContentSize(),
            shape = RoundedCornerShape(15),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    text = "Сведения об образовательной организации",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AppTheme.FontFamily,
                        textAlign = TextAlign.Center,
                    )
                )
                InfoOrganization.entries.forEach { infoOrganization ->
                    TextButton(
                        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                        onClick = {
                            onLinkPressed(infoOrganization.link)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .width(500.dp),
                            text = infoOrganization.title,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        )
                    }
                }
            }
        }
    }
}
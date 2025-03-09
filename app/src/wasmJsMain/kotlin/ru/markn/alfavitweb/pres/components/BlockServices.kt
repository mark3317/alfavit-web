package ru.markn.alfavitweb.pres.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.markn.alfavitweb.domain.models.Service
import ru.markn.alfavitweb.pres.main.IMainActions
import ru.markn.alfavitweb.pres.main.MainUIState
import ru.markn.alfavitweb.pres.utils.AppTheme

@Composable
fun IMainActions.BlockServices(state: MainUIState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFF5F5F5))
            .padding(top = 48.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = "Наши услуги",
            style = TextStyle(
                fontSize = 46.sp,
                fontFamily = AppTheme.FontFamily,
            )
        )
        FlowRow(
            modifier = Modifier.wrapContentSize(),
            maxItemsInEachRow = 2
        ) {
            Service.entries.forEach { service ->
                ServiceCard(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(width = 430.dp, height = 180.dp),
                    service = service,
                    onClick = { onServicePressed(service) }
                )
            }
        }
    }
    state.serviceSelected?.let {
        Dialog(
            onDismissRequest = { onOutsideServicePressed() },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
            )
        ) {
            ServiceDetailsCard(
                service = it,
                state = state
            )
        }
    }

}
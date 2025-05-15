package ru.markn.alfavitweb.pres.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.markn.alfavitweb.domain.models.Person
import ru.markn.alfavitweb.pres.main.IMainActions
import ru.markn.alfavitweb.pres.main.MainUIState
import ru.markn.alfavitweb.pres.utils.AppTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun IMainActions.BlockTeam(state: MainUIState) {
    SharedTransitionLayout {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = Color(0xFF619B8A))
                .padding(vertical = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = "Наш коллектив",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 46.sp,
                    fontFamily = AppTheme.FontFamily,
                    color = Color.White,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                )
            )
            Column(
                modifier = Modifier
                    .width(1100.dp)
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Person.entries.forEach { person ->
                    AnimatedVisibility(
                        visible = state.personSelected == person,
                    ) {
                        if (state.isMobileVersion) {
                            PersonDetailsPortraitCard(
                                modifier = Modifier
                                    .widthIn(max = 600.dp)
                                    .padding(16.dp),
                                person = person,
                            )
                        } else {
                            PersonDetailsCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                person = person,
                            )
                        }
                    }
                }
            }
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier.horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.Center,
            ) {
                Person.entries.forEach { person ->
                    AnimatedVisibility(visible = state.personSelected != person) {
                        PersonCard(
                            modifier = Modifier.width(210.dp),
                            person = person,
                            onClick = { onPersonPressed(person) }
                        )
                    }
                }
            }
        }
    }
}
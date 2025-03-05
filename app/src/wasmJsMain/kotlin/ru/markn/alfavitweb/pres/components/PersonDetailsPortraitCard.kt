package ru.markn.alfavitweb.pres.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitweb.domain.models.Person
import ru.markn.alfavitweb.pres.utils.AppTheme
import ru.markn.alfavitweb.pres.components.shared.PersonSharedKey

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PersonDetailsPortraitCard(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animationVisibilityScope: AnimatedVisibilityScope,
    person: Person,
) {
    with(sharedTransitionScope) {
        ElevatedCard(
            modifier = modifier
                .sharedBounds(
                    rememberSharedContentState(
                        key = PersonSharedKey(
                            person.name,
                            PersonSharedKey.SharedElementType.Bounds
                        )
                    ),
                    animationVisibilityScope,
                )
                .border(
                    width = 6.dp,
                    color = Color(0xFF233D4D),
                    shape = RoundedCornerShape(20)
                ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(person.photo),
                    contentDescription = "Person Photo",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(200.dp)
                        .sharedElement(
                            rememberSharedContentState(
                                key = PersonSharedKey(
                                    person.name,
                                    PersonSharedKey.SharedElementType.Photo
                                )
                            ),
                            animationVisibilityScope,
                        )
                        .clip(CircleShape)
                        .border(
                            width = 6.dp,
                            color = Color(0xFF233D4D),
                            shape = CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(
                                key = PersonSharedKey(
                                    person.name,
                                    PersonSharedKey.SharedElementType.Name
                                )
                            ),
                            animationVisibilityScope,
                        ),
                    text = person.name,
                    maxLines = 1,
                    style = TextStyle(
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = AppTheme.FontFamily,
                    )
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = person.post,
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = person.details,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}
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
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitweb.domain.models.Person
import ru.markn.alfavitweb.pres.utils.AppTheme
import ru.markn.alfavitweb.pres.components.shared.PersonSharedKey

context(sharedTransitionScope: SharedTransitionScope, animationVisibilityScope: AnimatedVisibilityScope)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PersonCard(
    modifier: Modifier = Modifier,
    person: Person,
    onClick: () -> Unit,
) {
    with(sharedTransitionScope) {
        ElevatedCard(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .height(80.dp)
                .sharedBounds(
                    rememberSharedContentState(
                        key = PersonSharedKey(
                            person.fullName,
                            PersonSharedKey.SharedElementType.Bounds
                        )
                    ),
                    animationVisibilityScope,
                )
                .border(
                    width = 4.dp,
                    color = Color(0xFF233D4D),
                    shape = CircleShape
                )
                .pointerHoverIcon(PointerIcon.Hand),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(50),
            onClick = onClick
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(person.photo),
                    contentDescription = "Person Photo",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .sharedBounds(
                            rememberSharedContentState(
                                key = PersonSharedKey(
                                    person.fullName,
                                    PersonSharedKey.SharedElementType.Photo
                                )
                            ),
                            animationVisibilityScope,
                        )
                        .clip(CircleShape)
                        .border(
                            width = 4.dp,
                            color = Color(0xFF233D4D),
                            shape = CircleShape
                        )
                    ,
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .sharedBounds(
                            rememberSharedContentState(
                                key = PersonSharedKey(
                                    person.fullName,
                                    PersonSharedKey.SharedElementType.Name
                                )
                            ),
                            animationVisibilityScope,
                        ),
                    text = person.fullName,
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = AppTheme.FontFamily,
                    )
                )
            }
        }
    }
}
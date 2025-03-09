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
import ru.markn.alfavitweb.pres.components.shared.PersonSharedKey
import ru.markn.alfavitweb.pres.utils.AppTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PersonDetailsCard(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animationVisibilityScope: AnimatedVisibilityScope,
    person: Person,
) {
    with(sharedTransitionScope) {
        ElevatedCard(
            modifier = modifier
                .sharedBounds(
                    rememberSharedContentState(key = PersonSharedKey(
                        person.fullName,
                        PersonSharedKey.SharedElementType.Bounds
                    )
                    ),
                    animationVisibilityScope,
                )
                .border(
                    width = 6.dp,
                    color = Color(0xFF233D4D),
                    shape = CircleShape
                ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(50),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 350.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(person.photo),
                    contentDescription = "Person Photo",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .sharedElement(
                            rememberSharedContentState(key = PersonSharedKey(
                                person.fullName,
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
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .sharedBounds(
                                rememberSharedContentState(key = PersonSharedKey(
                                    person.fullName,
                                    PersonSharedKey.SharedElementType.Name
                                )
                                ),
                                animationVisibilityScope,
                            ),
                        text = person.fullName,
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = 46.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = AppTheme.FontFamily,
                        )
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
                        text = person.post,
                        style = TextStyle(
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = person.details,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
    }
}
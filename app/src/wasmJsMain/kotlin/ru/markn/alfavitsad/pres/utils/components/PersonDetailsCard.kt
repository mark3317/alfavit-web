package ru.markn.alfavitsad.pres.utils.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitsad.domain.models.Person

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
                        person.name,
                        PersonSharedKey.SharedElementType.Bounds
                    )),
                    animationVisibilityScope,
                ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(50),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(person.photo),
                    contentDescription = "Person Photo",
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.3f)
                        .aspectRatio(1f)
                        .sharedElement(
                            rememberSharedContentState(key = PersonSharedKey(
                                person.name,
                                PersonSharedKey.SharedElementType.Photo
                            )),
                            animationVisibilityScope,
                        )
                        .clip(CircleShape)
                        .border(
                            width = 8.dp,
                            color = Color(0xFF233D4D),
                            shape = CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BasicText(
                        modifier = Modifier
                            .sharedBounds(
                                rememberSharedContentState(key = PersonSharedKey(
                                    person.name,
                                    PersonSharedKey.SharedElementType.Name
                                )),
                                animationVisibilityScope,
                            ),
                        text = person.name,
                        autoSize = TextAutoSize.StepBased(minFontSize = 12.sp, maxFontSize = 46.sp),
                        maxLines = 1,
                        style = TextStyle(
                            fontFamily = AppTheme.FontFamily,
                        )
                    )
                    BasicText(
                        modifier = Modifier.fillMaxWidth(),
                        text = person.details,
                        autoSize = TextAutoSize.StepBased(minFontSize = 8.sp, maxFontSize = 16.sp),
                        maxLines = 5,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                        )
                    )
                }
            }
        }
    }
}
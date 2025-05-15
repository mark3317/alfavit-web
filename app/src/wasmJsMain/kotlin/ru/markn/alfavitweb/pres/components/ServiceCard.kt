package ru.markn.alfavitweb.pres.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import ru.markn.alfavitweb.domain.models.Service
import ru.markn.alfavitweb.pres.components.shared.ServiceSharedKey

context(sharedTransitionScope: SharedTransitionScope, animationVisibilityScope: AnimatedVisibilityScope)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ServiceCard(
    modifier: Modifier = Modifier,
    service: Service,
    onClick: () -> Unit
) {
    with(sharedTransitionScope) {
        ElevatedCard(
            modifier = modifier
                .pointerHoverIcon(PointerIcon.Hand)
                .sharedBounds(
                    rememberSharedContentState(
                        key = ServiceSharedKey(
                            service.title,
                            ServiceSharedKey.SharedElementType.Bounds
                        )
                    ),
                    animationVisibilityScope,
                ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp,
                hoveredElevation = 16.dp,
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20),
            onClick = onClick
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(service.image),
                    contentDescription = "Service Image",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .sharedBounds(
                            rememberSharedContentState(
                                key = ServiceSharedKey(
                                    service.title,
                                    ServiceSharedKey.SharedElementType.Image
                                )
                            ),
                            animationVisibilityScope,
                        )
                        .clip(RoundedCornerShape(20))
                        .border(
                            width = 6.dp,
                            color = service.color,
                            shape = RoundedCornerShape(20)
                        ),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .sharedBounds(
                                rememberSharedContentState(
                                    key = ServiceSharedKey(
                                        service.title,
                                        ServiceSharedKey.SharedElementType.Title
                                    )
                                ),
                                animationVisibilityScope,
                            ),
                        text = service.title,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 20.sp,
                        )
                    )
                    Text(
                        modifier = Modifier
                            .sharedElement(
                                rememberSharedContentState(
                                    key = ServiceSharedKey(
                                        service.title,
                                        ServiceSharedKey.SharedElementType.Price
                                    )
                                ),
                                animationVisibilityScope,
                            ),
                        text = service.price,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
            }
        }
    }
}
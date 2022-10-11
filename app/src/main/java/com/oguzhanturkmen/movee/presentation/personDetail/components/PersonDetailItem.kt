package com.oguzhanturkmen.movee.presentation.personDetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhanturkmen.movee.R
import com.oguzhanturkmen.movee.common.Constants
import com.oguzhanturkmen.movee.domain.model.person.Person
import com.oguzhanturkmen.movee.domain.model.personcredits.PersonCredits
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun personDetailImage(person: Person) {
    val posterPath = Constants.BASE_BACKDROP_IMAGE_URL + person.profilePath
    CoilImage(
        imageModel = posterPath,
        shimmerParams = ShimmerParams(
            baseColor = Color.White,
            highlightColor = RatingBarColor,
            durationMillis = 500,
            dropOff = 0.65F,
            tilt = 20F,
        ),

        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.Fit,
        failure = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_not_available),
                    contentDescription = "no image"
                )
            }
        },
        previewPlaceholder = R.drawable.image_not_available,
        circularReveal = CircularReveal(duration = 1000),
    )
}

@Composable
fun personDetailName(person: Person) {
    Text(
        text = "${person.name}",
        style = TextStyle(fontSize = 28.sp),
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun personDetailBiography(person: Person) {
    ExpandableText(text = "${person.biography}")
}

@Composable
fun personCreditsImage(personCredits: PersonCredits) {
    val posterPath = Constants.BASE_BACKDROP_IMAGE_URL + personCredits.posterPath
    CoilImage(
        imageModel = posterPath,
        shimmerParams = ShimmerParams(
            baseColor = Color.White,
            highlightColor = RatingBarColor,
            durationMillis = 500,
            dropOff = 0.65F,
            tilt = 20F,

            ),
        modifier = Modifier
            .size(70.dp, 100.dp)
            .clip(RoundedCornerShape(10.dp)),
        failure = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_not_available),
                    contentDescription = "no image"
                )
            }
        },
        previewPlaceholder = R.drawable.image_not_available,
        contentScale = ContentScale.Crop,
        circularReveal = CircularReveal(duration = 1000),

        )
}

@Composable
fun personelCreditsMovieTitle(personCredits: PersonCredits) {
    if (personCredits.originalName == null) {
        Text(
            text = "${personCredits.originalTitle}",
            style = TextStyle(fontSize = 20.sp),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    } else {
        Text(
            text = "${personCredits.originalName}",
            style = TextStyle(fontSize = 20.sp),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun personelCreditsMovieRating(personCredits: PersonCredits) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .size(50.dp, 20.dp)
            .clip(shape)
            .background(RatingBarColor),
        Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        )
        {
            Alignment.Center
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                modifier = Modifier
                    .size(13.dp, 13.dp)
                    .padding(1.dp)
            )
            Text(
                text = "%.1f".format(personCredits.voteAverage),
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun personelCreditsItem(
    personCredits: PersonCredits,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        onClick = onClick
    )
    {
        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            personCreditsImage(personCredits = personCredits)
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                personelCreditsMovieTitle(personCredits = personCredits)
                Spacer(modifier = Modifier.height(8.dp))
                personelCreditsMovieRating(personCredits = personCredits)
            }
        }
    }

}

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    minimizedMaxLines: Int = 4,
) {
    var cutText by remember(text) { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val seeMoreSizeState = remember { mutableStateOf<IntSize?>(null) }
    val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }

    val textLayoutResult = textLayoutResultState.value
    val seeMoreSize = seeMoreSizeState.value
    val seeMoreOffset = seeMoreOffsetState.value

    LaunchedEffect(text, expanded, textLayoutResult, seeMoreSize) {
        val lastLineIndex = minimizedMaxLines - 1
        if (!expanded && textLayoutResult != null && seeMoreSize != null
            && lastLineIndex + 1 == textLayoutResult.lineCount
            && textLayoutResult.isLineEllipsized(lastLineIndex)
        ) {
            var lastCharIndex = textLayoutResult.getLineEnd(lastLineIndex, visibleEnd = true) + 1
            var charRect: Rect
            do {
                lastCharIndex -= 1
                charRect = textLayoutResult.getCursorRect(lastCharIndex)
            } while (
                charRect.left > textLayoutResult.size.width - seeMoreSize.width
            )
            seeMoreOffsetState.value = Offset(charRect.left, charRect.bottom - seeMoreSize.height)
            cutText = text.substring(startIndex = 0, endIndex = lastCharIndex)
        }
    }

    Box(modifier) {
        Text(
            text = cutText ?: text,
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResultState.value = it },
            fontSize = 16.sp,

            )
        if (!expanded) {
            val density = LocalDensity.current
            Text(
                "... See more",
                onTextLayout = { seeMoreSizeState.value = it.size },
                modifier = Modifier
                    .then(
                        if (seeMoreOffset != null)
                            Modifier.offset(
                                x = with(density) { seeMoreOffset.x.toDp() },
                                y = with(density) { seeMoreOffset.y.toDp() },
                            )
                        else
                            Modifier
                    )
                    .clickable {
                        expanded = true
                        cutText = null
                    }
                    .alpha(if (seeMoreOffset != null) 1f else 0f), fontWeight = FontWeight.Bold
            )
        }
    }
}

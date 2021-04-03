import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Stateful version. Holds animation states and calls stateless version for drawing
 */
@Composable
fun PlayerCardAnimationPrototype(
    expanded: Boolean,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
    ) {

        val transition = updateTransition(targetState = expanded)

        val paddingBottom by transition.animateDp { isExpanded ->
            if (isExpanded) 40.dp else 20.dp
        }

        val horizontalBias by transition.animateFloat { isExpanded ->
            if (isExpanded) -1f else 1f
        }
        val leaveButtonAlignment = BiasAlignment(horizontalBias, verticalBias = 0f)

        val leaveButtonWidth by transition.animateDp {
            if (expanded) 160.dp else 80.dp
        }

        val avatarListAlpha by transition.animateFloat { isExpanded ->
            if (isExpanded) 0f else 1f
        }

        PlayerCard(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(bottom = paddingBottom, top = 20.dp),

            leaveButtonWidth = leaveButtonWidth,
            leaveButtonAlignment = leaveButtonAlignment,
            avatarListAlpha = avatarListAlpha
        )
    }
}

/**
 * Stateless version that draws the layout
 */
@Composable
private fun PlayerCard(
    leaveButtonWidth: Dp,
    leaveButtonAlignment: Alignment,
    avatarListAlpha: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier
                .size(leaveButtonWidth, 80.dp)
                .align(leaveButtonAlignment),
            color = Color.Green,
            shape = CircleShape,
            content = {}
        )

        Surface(
            modifier = Modifier
                .size(160.dp, 80.dp)
                .alpha(avatarListAlpha)
                .align(Alignment.CenterStart),
            color = Color.Black,
            shape = RoundedCornerShape(percent = 30),
            content = {}
        )
    }
}

@Composable
@Preview
fun Preview() {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PlayerCardAnimationPrototype(
            expanded = expanded,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    expanded = !expanded
                }
        )
    }
}

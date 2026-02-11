import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zzz.feature.job.user.presentation.components.ProfileActionCard

@Preview(showBackground = true)
@Composable
private fun TempPrev() {
    Box(
        Modifier.fillMaxSize()
    ){
        ProfileActionCard(
            icon = 2,
            actionText = "",
            onClick = {}
        )
    }
}
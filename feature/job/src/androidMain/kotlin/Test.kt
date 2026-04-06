import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zzz.core.ui.theme.ClaveTheme
import com.zzz.feature.job.user.presentation.UserAccountPageRoot

@Preview(showBackground = true)
@Composable
private fun TempPrev() {
    ClaveTheme {
        Box(
            Modifier.fillMaxSize()
                .padding(16.dp)
        ){
//            UserAccountPageRoot(
//                onLogOut = {}
//            )
        }
    }
}
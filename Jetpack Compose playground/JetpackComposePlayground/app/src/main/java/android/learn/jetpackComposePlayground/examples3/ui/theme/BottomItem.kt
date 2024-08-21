package android.learn.jetpackComposePlayground.examples3.ui.theme

import android.learn.jetpackComposePlayground.R
import android.learn.jetpackComposePlayground.examples3.navigation.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {
    data object Add: BottomItem(
        Screen.Add,
        R.string.add,
        Icons.Outlined.Add
    )

    data object Items: BottomItem(
        Screen.Items,
        R.string.items,
        Icons.Outlined.Info
    )
}
package android.learn.jetpackComposePlayground.examples2

import android.learn.jetpackComposePlayground.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomItem(
    val titleResId: Int,
    val icon: ImageVector
) {
    data object Edit: BottomItem(
        R.string.edit,
        Icons.Outlined.Edit
    )

    data object Add: BottomItem(
        R.string.add,
        Icons.Outlined.Call
    )

    data object Email: BottomItem(
        R.string.email,
        Icons.Outlined.Email
    )

    data object Info: BottomItem(
        R.string.info,
        Icons.Outlined.Info
    )

    data object Search: BottomItem(
        R.string.search,
        Icons.Outlined.Search
    )
}
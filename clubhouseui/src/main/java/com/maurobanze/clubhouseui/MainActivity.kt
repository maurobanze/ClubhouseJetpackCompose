package com.maurobanze.clubhouseui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.maurobanze.clubhouseui.models.User
import com.maurobanze.clubhouseui.ui.screens.MainScreen
import com.maurobanze.clubhouseui.ui.theme.ClubhouseTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalStdlibApi
class FeedActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ClubhouseTheme {
                ProvideWindowInsets {
                    val activeUser = User(
                        "aidfjkmosf03",
                        "Mauro Banze",
                        "https://unsplash.com/photos/NohB3FJSY90/download?w=300"
                    )

                    Surface(color = MaterialTheme.colors.background) {
                        MainScreen(
                            modifier = Modifier
                                .statusBarsPadding()
                        )
                    }
                }
            }
        }
    }
}
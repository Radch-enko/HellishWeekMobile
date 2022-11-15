package com.blesscompany.hellishweek.mobile.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.blesscompany.hellishweek.mobile.android.navigation.ApplicationRoot
import com.blesscompany.hellishweek.mobile.android.ui.HellishWeekTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        //installSplashScreen()
        setContent {
            HellishWeekTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ApplicationRoot(navController = rememberNavController())
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    HellishWeekTheme {
        HellishWeekTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                ApplicationRoot(navController = rememberNavController())
            }
        }
    }
}

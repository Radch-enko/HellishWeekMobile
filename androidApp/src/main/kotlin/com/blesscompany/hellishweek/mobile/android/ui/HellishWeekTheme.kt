package com.blesscompany.hellishweek.mobile.android.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blesscompany.hellishweek.resources.Resources

@Composable
fun HellishWeekTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val fontFamily = FontFamily(
        listOf(
            Font(
                Resources.fonts.sfprodisplayblackitlic.sfprodisplayblackitlic.fontResourceId,
                style = FontStyle.Italic
            ),
            Font(
                Resources.fonts.sfprodisplaybold.sfprodisplaybold.fontResourceId,
                weight = FontWeight.Bold
            ),
            Font(
                Resources.fonts.sfprodisplaybolditalic.sfprodisplaybolditalic.fontResourceId,
                style = FontStyle.Italic,
                weight = FontWeight.Bold
            ),
            Font(
                Resources.fonts.sfprodisplayheavyitalic.sfprodisplayheavyitalic.fontResourceId,
                style = FontStyle.Italic,
                weight = FontWeight.ExtraBold
            ),
            Font(
                Resources.fonts.sfprodisplayhintitalic.sfprodisplayhintitalic.fontResourceId,
                style = FontStyle.Italic,
                weight = FontWeight.Thin
            ),
            Font(
                Resources.fonts.sfprodisplaylightitalic.sfprodisplaylightitalic.fontResourceId,
                style = FontStyle.Italic,
                weight = FontWeight.Light
            ),
            Font(
                Resources.fonts.sfprodisplaymedium.sfprodisplaymedium.fontResourceId,
                weight = FontWeight.Medium
            ),
            Font(Resources.fonts.sfprodisplayregulator.sfprodisplayregulator.fontResourceId),
            Font(
                Resources.fonts.sfprodisplayultralightitalic.sfprodisplayultralightitalic.fontResourceId,
                weight = FontWeight.ExtraLight,
                style = FontStyle.Italic
            ),
        )
    )
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5),
            error = Cinnabar
        )
    } else {
        lightColors(
            primary = Color(0xFFF2905F),
            primaryVariant = Color(0xFFF59B6D),
            secondary = Color(0xFF88d2f7),
            secondaryVariant = Color(0xFF2A78BF),
            error = Cinnabar,
            onSecondary = Color.White
        )
    }
    val typography = Typography(
        h1 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 96.sp,
            letterSpacing = (-1.5).sp,
            fontFamily = fontFamily
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            letterSpacing = (-0.5).sp,
            fontFamily = fontFamily
        ),
        h3 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            letterSpacing = 0.sp,
            fontFamily = fontFamily
        ),
        h4 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            letterSpacing = 0.25.sp,
            fontFamily = fontFamily
        ),
        h5 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
            fontFamily = fontFamily
        ),
        h6 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            letterSpacing = 0.15.sp,
            fontFamily = fontFamily
        ),
        subtitle1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp,
            fontFamily = fontFamily
        ),
        subtitle2 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp,
            fontFamily = fontFamily
        ),
        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            fontFamily = fontFamily
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp,
            fontFamily = fontFamily
        ),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 1.25.sp,
            fontFamily = fontFamily
        ),
        caption = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp,
            fontFamily = fontFamily
        ),
        overline = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp,
            fontFamily = fontFamily
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun HellishWeek3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val fontFamily = FontFamily(
        listOf(
            Font(
                Resources.fonts.sfprodisplayblackitlic.sfprodisplayblackitlic.fontResourceId,
                style = FontStyle.Italic
            ),
            Font(
                Resources.fonts.sfprodisplaybold.sfprodisplaybold.fontResourceId,
                weight = FontWeight.Bold
            ),
            Font(
                Resources.fonts.sfprodisplaybolditalic.sfprodisplaybolditalic.fontResourceId,
                style = FontStyle.Italic,
                weight = FontWeight.Bold
            ),
            Font(
                Resources.fonts.sfprodisplayheavyitalic.sfprodisplayheavyitalic.fontResourceId,
                style = FontStyle.Italic,
                weight = FontWeight.ExtraBold
            ),
            Font(
                Resources.fonts.sfprodisplayhintitalic.sfprodisplayhintitalic.fontResourceId,
                style = FontStyle.Italic,
                weight = FontWeight.Thin
            ),
            Font(
                Resources.fonts.sfprodisplaylightitalic.sfprodisplaylightitalic.fontResourceId,
                style = FontStyle.Italic,
                weight = FontWeight.Light
            ),
            Font(
                Resources.fonts.sfprodisplaymedium.sfprodisplaymedium.fontResourceId,
                weight = FontWeight.Medium
            ),
            Font(Resources.fonts.sfprodisplayregulator.sfprodisplayregulator.fontResourceId),
            Font(
                Resources.fonts.sfprodisplayultralightitalic.sfprodisplayultralightitalic.fontResourceId,
                weight = FontWeight.ExtraLight,
                style = FontStyle.Italic
            ),
        )
    )
    val palette = lightColorScheme(
        primary = Color(0xFFF2905F),
        secondaryContainer = Color(0xFFF2905F),
        onSecondaryContainer = Color.White,
        secondary = Color(0xFF88d2f7),
        error = Cinnabar,
        onSecondary = Color.White
    )
    val typography = androidx.compose.material3.Typography(
        displayLarge = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            letterSpacing = (-0.5).sp,
            fontFamily = fontFamily
        ),
        displayMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            letterSpacing = 0.sp,
            fontFamily = fontFamily
        ),
        displaySmall = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            letterSpacing = 0.25.sp,
            fontFamily = fontFamily
        ),
        headlineLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
            fontFamily = fontFamily
        ),
        headlineMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            letterSpacing = 0.15.sp,
            fontFamily = fontFamily
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp,
            fontFamily = fontFamily
        ),
        titleMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp,
            fontFamily = fontFamily
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            fontFamily = fontFamily
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp,
            fontFamily = fontFamily
        )
    )
    val shapes = androidx.compose.material3.Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp),
    )

    androidx.compose.material3.MaterialTheme(
        colorScheme = palette,
        typography = typography,
        shapes = shapes
    ) {
        content()
    }
}
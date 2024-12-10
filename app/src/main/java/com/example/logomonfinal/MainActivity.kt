package com.example.logomonfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.logomonfinal.ui.theme.LogomonFINALTheme
import com.example.logomonfinal.model.Affirmation
import androidx.compose.material3.Card
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalLayoutDirection
import com.example.logomonfinal.data.Datasource

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogomonFINALTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LogomonFINALApp()
                }
            }
        }
    }
}

@Composable
fun LogomonFINALApp(){
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        AffirmationList(
            affirmationList = Datasource().loadAffirmations(),
        )
    }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            LogomonFINALCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun LogomonFINALCard(affirmation: Affirmation, modifier: Modifier = Modifier){
    Card(modifier = modifier) {
       Column(
           modifier = modifier,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
           Text(
               text = LocalContext.current.getString(affirmation.stringResourceId),
               modifier = Modifier
                   .padding(16.dp),
               style = MaterialTheme.typography.headlineSmall
           )
       }
    }
}

@Preview
@Composable
private fun LogomonFINALCardPreview() {
    LogomonFINALApp()
}
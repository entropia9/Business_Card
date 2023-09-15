package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.businesscard.ui.theme.BusinessCardTheme
import io.github.serpro69.kfaker.Faker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val faker = Faker()

        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    BusinessCardApp(faker)

                }
            }
        }
    }
}

@Composable
fun BusinessCardApp(faker: Faker, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        //fake data generated each time with Faker https://serpro69.github.io/kotlin-faker/
        val name = faker.name.name()
        ShowMainData(
            name = name,
            job = faker.job.title()
        )
        ContactInfoColumn(
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.padding_large),
                top = 200.dp
            ),
            phoneNumber = faker.phoneNumber.phoneNumber(),
            socialMediaHandle = "@" + faker.pokemon.names(),
            email = faker.internet.email(name = name.lowercase())
        )
    }
}

//Displays logo, name and job title in a column
@Composable
fun ShowMainData(
    name: String, job: String,
    modifier: Modifier = Modifier,
    image: Painter = painterResource(id = R.drawable.ic_launcher_foreground)
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Image(
            painter = image,
            contentDescription = null,
            Modifier
                .paint(
                    painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.FillBounds
                )
                .size(
                    120.dp
                )
        )
        Text(
            text = name,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.padding_medium),
                top = dimensionResource(id = R.dimen.padding_medium)
            )
        )
        Text(
            text = job,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

}

// Icon and text combined in a row to avoid duplicating Row in ContactInfoColumn
@Composable
fun ContactInfo(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Composable
fun ContactInfoColumn(
    modifier: Modifier = Modifier,
    phoneNumber: String = "000000000",
    socialMediaHandle: String = "@x",
    email: String = "email@email.com"

) {
    val phoneIcon = Icons.Default.Phone
    val socialIcon = Icons.Default.Share
    val mailIcon = Icons.Default.Email
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ) {
        ContactInfo(icon = phoneIcon, text = phoneNumber)
        ContactInfo(icon = socialIcon, text = socialMediaHandle)
        ContactInfo(icon = mailIcon, text = email)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            //fake data generated each time with Faker https://serpro69.github.io/kotlin-faker/
            ShowMainData(
                name = "faker.name.nameWithMiddle()",
                job = "faker.job.title()",
                modifier = Modifier.weight(1f)
            )
            ContactInfoColumn(
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.padding_large),
                ),
                phoneNumber = "faker.phoneNumber.phoneNumber()",
                socialMediaHandle = "@ + faker.pokemon.names()",
                email = "faker.internet.email()"
            )
        }
    }
}
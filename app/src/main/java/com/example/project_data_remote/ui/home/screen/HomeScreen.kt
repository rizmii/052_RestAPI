package com.example.project_data_remote.ui.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.project_data_remote.R
import com.example.project_data_remote.model.Kontak
import com.example.project_data_remote.ui.home.viewmodel.KontakUIState

@Composable
fun HomeStatus(
    kontakUIState: KontakUIState,
    retryAction: () -> Unit,
    modifier: Modifier,
    onDetailClick: (Int) -> Unit,
    onDeleteClick: (Kontak) -> Unit = {},
) {
    when (kontakUIState) {
        is KontakUIState.Loading -> OnLoading(modifier = Modifier.fillMaxSize())

        is KontakUIState.Success -> KontakLayout(kontak = kontakUIState.kontak, modifier = Modifier.fillMaxWidth(),
            onDetailClick = {
                onDetailClick(it.id)
            },
            onDeleteClick = {
                onDeleteClick(it)
            })
        is KontakUIState.Error ->
            OnError(retryAction, modifier = Modifier.fillMaxSize())

    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.loading_image),
        contentDescription = stringResource(id = R.string.loading),
        modifier = modifier
    )
}

@Composable
    fun KontakLayout(
        kontak: List<Kontak>,
        modifier: Modifier = Modifier,
        onDetailClick: (Kontak) -> Unit,
        onDeleteClick: (Kontak) -> Unit = {},

        ) {
        LazyColumn(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(16.dp)) {
           items(kontak) { kontak-> KontakCard(kontak = kontak, modifier = Modifier
               .fillMaxWidth()
               .clickable {onDetailClick (kontak)},
               onDeleteClick = {onDeleteClick(kontak)}
               )

            }
        }
    }
    @Composable
    fun KontakCard(
        kontak: Kontak,
        onDeleteClick: (Kontak) -> Unit = {},
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = kontak.nama,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                    )
                    Text(
                        text = kontak.nohp,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = kontak.alamat,
                    style = MaterialTheme.typography.titleMedium

                )
                Spacer(modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(kontak)}) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null )

                }
            }
        }
    }


@Composable
fun OnError(retryAction: ()-> Unit, modifier: Modifier = Modifier){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
Image(painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = "")
        Text(text = stringResource(id = R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(id = R.string.retry))
        }
    }
}
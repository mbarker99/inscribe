package com.embarkapps.inscribe.presentation.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.embarkapps.inscribe.R
import com.embarkapps.inscribe.presentation.signin.components.LoginOutlinedTextField

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoginOutlinedTextField(
            value = state.value.email,
            label = stringResource(id = R.string.email_placeholder)
        ) {
            viewModel.updateEmail(it)
        }

        LoginOutlinedTextField(
            value = state.value.password,
            label = stringResource(id = R.string.password_placeholder)
        ) {
            viewModel.updatePassword(it)
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onSignInClick() }) {
            Text(text = stringResource(id = R.string.login_button))
        }
    }
}
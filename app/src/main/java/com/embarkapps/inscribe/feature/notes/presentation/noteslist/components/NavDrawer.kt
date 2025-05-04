package com.embarkapps.inscribe.feature.notes.presentation.noteslist.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NavDrawer(
    drawerState: DrawerState,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(
        drawerState = drawerState
    ) {
        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = false,
            icon = { Icon(Icons.Default.Settings, "Settings") },
            onClick = {}
        )
    }
}
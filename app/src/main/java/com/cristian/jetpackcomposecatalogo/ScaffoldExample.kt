package com.cristian.jetpackcomposecatalogo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ScaffoldExample() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MyTopAppBar(onClickIcon = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("You clicked on $it icon")
                }
            }, onClickDrawer = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            })
        },
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation() },
        floatingActionButton = { MyFAB() },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,
        drawerContent = { MyDrawer() { coroutineScope.launch { scaffoldState.drawerState.close() } } },
        drawerGesturesEnabled = false,
    ) {

    }
}

@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = "My App") },
        backgroundColor = Color.Red,
        contentColor = Color.White,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("search") }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search"
                )
            }
        }
    )
}

@Composable
fun MyBottomNavigation() {
    var index by remember { mutableStateOf(0) }
    BottomNavigation(backgroundColor = Color.Red, contentColor = Color.White) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "home") },
            label = { Text(text = "Home") },
            selected = index == 0,
            onClick = { index = 0 }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "fav") },
            label = { Text(text = "Fav") },
            selected = index == 1,
            onClick = { index = 1 }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "person") },
            label = { Text(text = "Person") },
            selected = index == 2,
            onClick = { index = 2 }
        )
    }
}

@Composable
fun MyFAB() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        contentColor = Color.Black,
        backgroundColor = Color.Yellow
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Item 1", modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCloseDrawer() })
        Text(text = "Item 2", modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCloseDrawer() })
        Text(
            text = "Item 3", modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Text(text = "Item 4", modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCloseDrawer() })
    }
}
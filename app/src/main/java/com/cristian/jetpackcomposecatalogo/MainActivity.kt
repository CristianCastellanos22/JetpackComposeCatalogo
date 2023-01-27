package com.cristian.jetpackcomposecatalogo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cristian.jetpackcomposecatalogo.model.Routes
import com.cristian.jetpackcomposecatalogo.model.Routes.*
import com.cristian.jetpackcomposecatalogo.ui.theme.JetpackComposeCatalogoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCatalogoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Screen1.route
                    ) {
                        composable(Screen1.route) { Screen1(navigationController) }
                        composable(Screen2.route) { Screen2(navigationController) }
                        composable(Screen3.route) { Screen3(navigationController) }
                        composable(
                            Screen4.route,
                            arguments = listOf(navArgument("age") { type = NavType.IntType })
                        ) { backStackEntry ->
                            Screen4(
                                navigationController,
                                backStackEntry.arguments?.getInt("age") ?: 0
                            )
                        }
                        composable(
                            Screen5.route,
                            arguments = listOf(navArgument("name") { defaultValue = "Cristian" })
                        ) { backStackEntry ->
                            Screen5(
                                navigationController,
                                backStackEntry.arguments?.getString("name")
                            )
                        }
                    }
                    //ScaffoldExample()
                    /*var show by remember { mutableStateOf(false) }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = { show = true }) {
                            Text(text = "Show Dialog")
                        }
                        MyConfirmationDialog(
                            show = show,
                            onDismiss = { show = false }
                        )
                    }*/
                }

            }
        }
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row() {
            RadioButton(selected = name == "Data1", onClick = { onItemSelected("Data1") })
            Text(text = "Data 1", modifier = Modifier.padding(top = 12.dp))
        }
        Row() {
            RadioButton(selected = name == "Data2", onClick = { onItemSelected("Data2") })
            Text(text = "Data 2", modifier = Modifier.padding(top = 12.dp))
        }
        Row() {
            RadioButton(selected = name == "Data3", onClick = { onItemSelected("Data3") })
            Text(text = "Data 3", modifier = Modifier.padding(top = 12.dp))
        }
        Row() {
            RadioButton(selected = name == "Data4", onClick = { onItemSelected("Data4") })
            Text(text = "Data 4", modifier = Modifier.padding(top = 12.dp))
        }
    }
}

@Composable
fun MyConfirmationDialog(
    show: Boolean,
    onDismiss: () -> Unit,
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                MyTitleDialog(text = "Phone Ringtone", modifier = Modifier.padding(24.dp))
                Divider(modifier = Modifier.fillMaxWidth(), Color.LightGray)
                var status by remember { mutableStateOf("") }
                MyRadioButtonList(status) { status = it }
                Divider(modifier = Modifier.fillMaxWidth(), Color.LightGray)
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(8.dp)
                ) {
                    TextButton(onClick = {}) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {}) {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }
}

@Composable
fun MyTitleDialog(text: String, modifier: Modifier = Modifier.padding(bottom = 12.dp)) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
fun MyRadioButton() {
    Row(modifier = Modifier.fillMaxWidth()) {
        RadioButton(
            selected = false, onClick = { }, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledColor = Color.Green
            )
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Hello World"
        )
    }
}

@Composable
fun MyTriStatusCheckBox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> ToggleableState.Off
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun getOptions(title: List<String>): List<CheckInfo> {
    return title.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            checked = status,
            onCheckedChange = { myStatus -> status = myStatus }
        )
    }
}

@Composable
fun MyCheckBoxWithTextCompleted(checkInfo: CheckInfo) {
    Row(modifier = Modifier.padding(8.dp)) {
        Checkbox(
            checked = checkInfo.checked,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.checked) }
        )
        //Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = checkInfo.title
        )
    }

}

@Composable
fun MyCheckBoxWithText() {
    var checkedState by rememberSaveable { mutableStateOf(false) }
    Row(modifier = Modifier.padding(8.dp)) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { checkedState = !checkedState },
        )
        //Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Hello World",
        )
    }

}

@Composable
fun MyChecbox() {
    var checkedState by rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = checkedState,
        onCheckedChange = { checkedState = !checkedState },
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Green,
            checkmarkColor = Color.White
        )
    )
}

@Composable
fun MySwitch() {
    var switchState by rememberSaveable { mutableStateOf(false) }
    Switch(
        checked = switchState,
        onCheckedChange = { switchState = !switchState },
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Magenta,
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Cyan,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeCatalogoTheme {
        MyRadioButton()
    }
}

/*@Composable
fun MyProgressAdvance() {
    var progressStatus by rememberSaveable { mutableStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = progressStatus)
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { progressStatus += 0.1f }) {
                Text(text = "Increment")
            }
            Button(onClick = { progressStatus -= 0.1f }) {
                Text(text = "Decrement")
            }
        }
    }
}

@Composable
fun MyProgress() {
    var showLoading by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                backgroundColor = Color.Green
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Show Progress")
        }
    }
}*/

/*@Composable
fun MyIcon() {
    Icon(imageVector = Icons.Rounded.Star, contentDescription = "Star", tint = Color.Red)
}

@Composable
fun MyImageAdvance() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo"
    )
}*/

/*@Composable
fun MyTextFieldOutLined() {
    var myText by remember {
        mutableStateOf("")
    }
    TextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Enter your text") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Magenta,
            unfocusedBorderColor = Color.Red
        )
    )
}

@Composable
fun MyButtonExample() {
    var enable by rememberSaveable {mutableStateOf(true)}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { enable = false },
            enabled = enable,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.White
            ),
            border = BorderStroke(2.dp, Color.Black),
        ) {
            Text(text = "Button")
        }

        OutlinedButton(onClick = { *//*TODO*//* }) {
            Text(text = "Outlined Button")
        }

        TextButton(onClick = { *//*TODO*//* }) {
            Text(text = "Text Button")
        }
    }
}*/

/*@Composable
fun MyTextFieldAdvance() {
    var myText by remember {
        mutableStateOf("")
    }
    TextField(value = myText,
        onValueChange = { myText = if (it.contains("a")) it.replace("a", "") else it },
        label = { Text("Enter your text") })
}

@Composable
fun MyTextField(name: String, onNameChange: (String) -> Unit) {
    TextField(value = name, onValueChange = {onNameChange(it)})
}

@Composable
fun MyText() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "text")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.ExtraBold)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Light)
        Text(text = "Esto es un ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Esto es un ejemplo", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough, TextDecoration.Underline
                    )
                )
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}*/

/*@Composable
fun MyStateExample() {
    var counter by rememberSaveable {mutableStateOf(0)}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter++ }) {
            Text(text = "Pulsar")
        }
        Text(text = "He sido usado $counter veces")
    }
}

@Composable
fun MyComplexLayout() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Cyan)
        ) {
            Text(
                text = "Box 1",
                modifier = Modifier.align(Alignment.Center)
            )
        }
        MySpacer(size = 30)
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Gray)
            ) {
                Text(
                    text = "Box 2",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Green), contentAlignment = Alignment.Center
            ) {
                Text(text = "Hola")
            }
        }
        MySpacer(size = 80)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Yellow)
        ) {
            Text(
                text = "Box 3",
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun MySpacer(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}*/

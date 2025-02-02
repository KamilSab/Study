package ru.itis.homework5

import android.app.Application
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.provider.Settings
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    var coroutineCount by remember { mutableStateOf("") }
    var selectedLaunchType by remember { mutableStateOf("Последовательно") }
    var selectedBehavior by remember { mutableStateOf("Останавливать") }
    var selectedDispatcher by remember { mutableStateOf("IO") }
    var showPermissionDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) showPermissionDialog = true
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        SideEffect {
            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = coroutineCount,
            onValueChange = { coroutineCount = it },
            label = { Text("Число корутин") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage != null
        )
        errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        Text("Тип запуска:")
        Row {
            RadioButton(selected = selectedLaunchType == "Последовательно", onClick = { selectedLaunchType = "Последовательно" })
            Text("Последовательно")
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(selected = selectedLaunchType == "Параллельно", onClick = { selectedLaunchType = "Параллельно" })
            Text("Параллельно")
        }

        Text("Логика при сворачивании:")
        Row {
            RadioButton(
                selected = viewModel.selectedBehavior == "Останавливать",
                onClick = {
                    viewModel.updateSelectedBehavior("Останавливать")
                }
            )
            Text("Останавливать")
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(
                selected = viewModel.selectedBehavior == "Продолжать",
                onClick = {
                    viewModel.updateSelectedBehavior("Продолжать")
                }
            )
            Text("Продолжать")
        }

        Text("Пул потоков:")
        var expanded by remember { mutableStateOf(false) }
        Box {
            Button(onClick = { expanded = true }) { Text(selectedDispatcher) }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                listOf("Default", "IO", "Unconfined", "Main").forEach { dispatcher ->
                    DropdownMenuItem(text = { Text(dispatcher) }, onClick = {
                        selectedDispatcher = dispatcher
                        expanded = false
                    })
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                if (coroutineCount.isBlank()) {
                    errorMessage = "Введите число корутин"
                } else {
                    errorMessage = null
                    viewModel.startCoroutines(coroutineCount, selectedLaunchType, selectedBehavior, selectedDispatcher)
                }
            }) { Text("Запуск") }

            Button(onClick = { viewModel.cancelCoroutines() }) { Text("Отмена") }
        }
    }

    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text("Разрешение на уведомления") },
            text = { Text("Чтобы получать уведомления, пожалуйста, дайте разрешение в настройках.") },
            confirmButton = {
                Button(onClick = {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                    context.startActivity(intent)
                    showPermissionDialog = false
                }) {
                    Text("Открыть настройки")
                }
            },
            dismissButton = {
                Button(onClick = { showPermissionDialog = false }) {
                    Text("Отмена")
                }
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen(viewModel = MainViewModel())
//}
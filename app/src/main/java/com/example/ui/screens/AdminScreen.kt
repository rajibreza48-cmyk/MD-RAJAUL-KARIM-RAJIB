package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MenuItem
import com.example.ui.theme.WarmAmberPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    menuItems: List<MenuItem>,
    onUpdateItem: (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var editingItem by remember { mutableStateOf<MenuItem?>(null) }
    
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "অ্যাডমিন প্যানেল: মেনু ও দাম পরিবর্তন",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(menuItems, key = { it.id }) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.nameBn, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(text = "বর্তমান দাম: ৳${item.price.toInt()}", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Medium)
                        }
                        IconButton(onClick = { editingItem = item }) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "এডিট করুন", tint = WarmAmberPrimary)
                        }
                    }
                }
            }
        }
    }
    
    // Edit Dialog
    editingItem?.let { item ->
        var editName by remember { mutableStateOf(item.nameBn) }
        var editPrice by remember { mutableStateOf(item.price.toInt().toString()) }
        
        AlertDialog(
            onDismissRequest = { editingItem = null },
            title = { Text("মেনু এডিট করুন") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text("খাবারের নাম") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = editPrice,
                        onValueChange = { editPrice = it },
                        label = { Text("দাম (৳)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val newPrice = editPrice.toDoubleOrNull() ?: item.price
                        val updated = item.copy(
                            name = editName,
                            nameBn = editName,
                            price = newPrice,
                            basePrice = newPrice
                        )
                        onUpdateItem(updated)
                        editingItem = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = WarmAmberPrimary)
                ) {
                    Text("সেভ করুন")
                }
            },
            dismissButton = {
                TextButton(onClick = { editingItem = null }) {
                    Text("বাতিল")
                }
            }
        )
    }
}

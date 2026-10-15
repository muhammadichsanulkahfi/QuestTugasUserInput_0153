package com.example.questtugasuserinput_0153

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar


@Composable
fun Act4() {
    FormBiodataScreen()
}
@Composable
fun FormBiodataScreen() {
    val context = LocalContext.current

    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var androidSkill by remember { mutableStateOf(false) }
    var uiuxSkill by remember { mutableStateOf(false) }
    var webSkill by remember { mutableStateOf(false) }

    var dropdownExpanded by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }

    val experiences = listOf(
        stringResource(R.string.experience_beginner),
        stringResource(R.string.experience_intermediate),
        stringResource(R.string.experience_expert)
    )

    val formValid = namaDepan.isNotBlank() &&
            namaBelakang.isNotBlank() &&
            email.isNotBlank() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            tanggalLahir.isNotBlank() &&
            experience.isNotBlank() &&
            (androidSkill || uiuxSkill || webSkill)

    Scaffold(
        containerColor = colorResource(R.color.screen_background)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.bg_brick_purple),
                contentDescription = stringResource(R.string.background_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            Image(
                painter = painterResource(R.drawable.logo_spider),
                contentDescription = stringResource(R.string.logo_description),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 65.dp)
                    .size(110.dp)
            )

            BiodataCard(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 250.dp),
                namaDepan = namaDepan,
                onNamaDepanChange = { namaDepan = it },
                namaBelakang = namaBelakang,
                onNamaBelakangChange = { namaBelakang = it },
                email = email,
                onEmailChange = { email = it },
                tanggalLahir = tanggalLahir,
                onDateClick = { showDatePicker = true },
                experience = experience,
                dropdownExpanded = dropdownExpanded,
                onDropdownChange = { dropdownExpanded = it },
                experiences = experiences,
                onExperienceSelected = { experience = it },
                androidSkill = androidSkill,
                onAndroidChange = { androidSkill = it },
                uiuxSkill = uiuxSkill,
                onUiuxChange = { uiuxSkill = it },
                webSkill = webSkill,
                onWebChange = { webSkill = it },
                formValid = formValid,
                onProceed = { showResult = true }
            )
        }
    }

    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                tanggalLahir = "%02d/%02d/%04d".format(day, month + 1, year)
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showResult) {
        AlertDialog(
            onDismissRequest = { showResult = false },
            title = { Text(stringResource(R.string.result_title)) },
            text = {
                Text(
                    stringResource(
                        R.string.result_message,
                        namaDepan,
                        namaBelakang,
                        email,
                        tanggalLahir,
                        experience,
                        listOfNotNull(
                            if (androidSkill) stringResource(R.string.skill_android) else null,
                            if (uiuxSkill) stringResource(R.string.skill_uiux) else null,
                            if (webSkill) stringResource(R.string.skill_web) else null
                        ).joinToString(", ")
                    )
                )
            },
            confirmButton = {
                TextButton(onClick = { showResult = false }) {
                    Text(stringResource(R.string.button_close))
                }
            }
        )
    }
}

@Composable
fun BiodataCard(
    modifier: Modifier = Modifier,
    namaDepan: String,
    onNamaDepanChange: (String) -> Unit,
    namaBelakang: String,
    onNamaBelakangChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    tanggalLahir: String,
    onDateClick: () -> Unit,
    experience: String,
    dropdownExpanded: Boolean,
    onDropdownChange: (Boolean) -> Unit,
    experiences: List<String>,
    onExperienceSelected: (String) -> Unit,
    androidSkill: Boolean,
    onAndroidChange: (Boolean) -> Unit,
    uiuxSkill: Boolean,
    onUiuxChange: (Boolean) -> Unit,
    webSkill: Boolean,
    onWebChange: (Boolean) -> Unit,
    formValid: Boolean,
    onProceed: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = colorResource(R.color.card_background),
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.form_title),
            color = colorResource(R.color.purple_primary),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.form_subtitle),
            color = colorResource(R.color.text_secondary),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BiodataTextField(
                value = namaDepan,
                onValueChange = onNamaDepanChange,
                label = stringResource(R.string.first_name),
                modifier = Modifier.weight(1f)
            )

            BiodataTextField(
                value = namaBelakang,
                onValueChange = onNamaBelakangChange,
                label = stringResource(R.string.last_name),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BiodataTextField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.email),
            modifier = Modifier.fillMaxWidth(),
            isEmail = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            OutlinedButton(
                onClick = onDateClick,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                Text(
                    text = tanggalLahir.ifBlank {
                        stringResource(R.string.birth_date)
                    },
                    color = colorResource(R.color.text_primary),
                    modifier = Modifier.weight(1f),
                    fontSize = 12.sp
                )
                Text("▦", fontSize = 18.sp)
            }

            Box(modifier = Modifier.weight(1f)) {
                OutlinedButton(
                    onClick = { onDropdownChange(true) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp)
                ) {
                    Text(
                        text = experience.ifBlank {
                            stringResource(R.string.experience)
                        },
                        color = colorResource(R.color.text_primary),
                        modifier = Modifier.weight(1f),
                        fontSize = 12.sp,
                        maxLines = 1
                    )
                    Text("⌄", fontSize = 18.sp)
                }

                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { onDropdownChange(false) }
                ) {
                    experiences.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                onExperienceSelected(item)
                                onDropdownChange(false)
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = stringResource(R.string.choose_skill),
            modifier = Modifier.fillMaxWidth(),
            color = colorResource(R.color.text_primary),
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SkillCheckbox(
                text = stringResource(R.string.skill_android),
                checked = androidSkill,
                onCheckedChange = onAndroidChange
            )
            SkillCheckbox(
                text = stringResource(R.string.skill_uiux),
                checked = uiuxSkill,
                onCheckedChange = onUiuxChange
            )
            SkillCheckbox(
                text = stringResource(R.string.skill_web),
                checked = webSkill,
                onCheckedChange = onWebChange
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = onProceed,
            enabled = formValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.purple_primary),
                disabledContainerColor = colorResource(R.color.button_disabled)
            )
        ) {
            Text(
                text = stringResource(R.string.button_proceed),
                color = colorResource(R.color.button_text),
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun BiodataTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isEmail: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(label, fontSize = 13.sp) },
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
            keyboardType = if (isEmail) {
                androidx.compose.ui.text.input.KeyboardType.Email
            } else {
                androidx.compose.ui.text.input.KeyboardType.Text
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.purple_primary),
            unfocusedBorderColor = colorResource(R.color.border_color)
        )
    )
}

@Composable
fun SkillCheckbox(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = text,
            fontSize = 10.sp,
            color = colorResource(R.color.text_primary)
        )
    }
}



if (showResult) {
    AlertDialog(
        onDismissRequest = { showResult = false },
        title = {
            Text(stringResource(R.string.result_title))
        },
        text = {
            Text("$namaDepan $namaBelakang\n$email")
        },
        confirmButton = {
            TextButton(onClick = { showResult = false }) {
                Text(stringResource(R.string.button_close))
            }
        }
    )
}
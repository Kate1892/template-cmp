package com.template.cmp.features.common.compose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreInterceptKeyBeforeSoftKeyboard
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.template.cmp.common.view.TextFieldState
import com.template.cmp.theme.NoteShapes
import com.template.cmp.theme.NoteTheme
import com.template.cmp.theme.NoteTypography

@Composable
fun NoteTextField(
    fieldState: TextFieldState,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onFocusChange: (Boolean) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    startContent: @Composable () -> Unit = {},
    startContentAmount: Int = 0,
    isEnabled: Boolean = true,
    isEmojiAllowed: Boolean = false,
    color: Color = NoteTheme.colors.textPrimary,
    colorDisabled: Color = NoteTheme.colors.textPrimary.copy(alpha = 0.4f),
) = NoteBasicTextField(
    value = fieldState.enteredValue,
    placeholder = fieldState.placeholder,
    onValueChange = onValueChange,
    label = fieldState.title,
    modifier = modifier,
    onFocusChange = onFocusChange,
    keyboardType = keyboardType,
    imeAction = imeAction,
    isError = false,
    errorText = "",
//    isError = fieldState.validationState.isError,
//    errorText = fieldState.validationState.errorText,
    visualTransformation = visualTransformation,
    startContent = startContent,
    startContentAmount = startContentAmount,
    isEnabled = isEnabled,
    isEmojiAllowed = isEmojiAllowed,
    color = color,
    colorDisabled = colorDisabled,
)

enum class TextFieldBorderState { InFocus, InFocusAndErred, OutOfFocus, }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteBasicTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    onFocusChange: (Boolean) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    isError: Boolean = false,
    errorText: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    startContent: @Composable () -> Unit = {},
    startContentAmount: Int = 0,
    isEnabled: Boolean = true,
    isEmojiAllowed: Boolean = false,
    color: Color = NoteTheme.colors.textPrimary,
    colorDisabled: Color = NoteTheme.colors.textPrimary.copy(alpha = 0.4f),
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    var borderState by remember { mutableStateOf(TextFieldBorderState.OutOfFocus) }
    var hasFocus by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        if (label.isNotEmpty()) {
            NoteText(
                text = label,
                textAlign = TextAlign.Start,
                style = NoteTypography().titleSmall,
                color = NoteTheme.colors.textPrimary,
            )
        }
        VSpacer(4.dp)
        BasicTextField(
            value = value,
            onValueChange = { value ->
                val newValue = if (!isEmojiAllowed) {
                    buildString {
                        value.forEach { char ->
//                            if (!emojiValidationService.isEmoji(char)) {
                            append(char)
//                            }
                        }
                    }
                } else {
                    value
                }

                onValueChange(newValue)
            },
            enabled = isEnabled,
            readOnly = !isEnabled,
            modifier = Modifier
                .onPreInterceptKeyBeforeSoftKeyboard { event ->
                    if (event.key == Key.Back) {
                        // TODO check
                        focusManager.clearFocus()
                        true
                    } else {
                        false
                    }
                }
                .focusRequester(focusRequester)
                .onFocusEvent { event ->
                    hasFocus = event.hasFocus
                    borderState = if (event.hasFocus) {
                        TextFieldBorderState.InFocus
                    } else {
                        TextFieldBorderState.OutOfFocus
                    }
                    onFocusChange(event.hasFocus)
                }
                .fillMaxWidth()
                .height(46.dp)
                .clip(NoteShapes.primary)
                .background(
                    color = if (!isError) {
                        borderState =
                            if (hasFocus) TextFieldBorderState.InFocus else TextFieldBorderState.OutOfFocus
                        NoteTheme.colors.inputField
                    } else {
                        borderState =
                            if (hasFocus) {
                                TextFieldBorderState.InFocusAndErred
                            } else {
                                TextFieldBorderState.OutOfFocus
                            }
                        Color.Red
                    }
                )
                .border(
                    width = 1.dp,
                    color = extractTextFieldBorderColor(borderState),
                    shape = NoteShapes.primary,
                ),
            textStyle = NoteTypography().labelLarge.copy(
                color = if (!isError) {
                    (if (isEnabled) color else colorDisabled)
                } else {
                    Color.Red
//                    NoteTheme.colors.inputFieldErrorText
                },
                fontSize = 14.sp,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
                capitalization = KeyboardCapitalization.None
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            visualTransformation = visualTransformation,
            singleLine = true,
            decorationBox = { innerTextField ->
                InputBaseContent(
                    value = value,
                    placeholder = placeholder,
                    startContent = startContent,
                    startContentAmount = startContentAmount,
                    innerTextField = innerTextField,
                )
            }
        )
        VSpacer(size = 4.dp)
//        ErrorTextLayout(
//            modifier = modifier,
//            errorText = errorText,
//        )
    }
}


@Composable
private fun InputBaseContent(
    value: String,
    placeholder: String,
    startContentAmount: Int,
    startContent: @Composable () -> Unit,
    innerTextField: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        content = {
            if (startContentAmount != 0) {
                startContent.invoke()
            }
            Box(
                modifier = Modifier
                    .height(46.dp)
                    .fillMaxWidth()
            ) {
                if (value.isEmpty() && placeholder.isNotBlank()) {
                    Text(
                        text = placeholder,
                        modifier = Modifier
                            .padding(
                                start = if (startContentAmount == 0) 16.dp else 0.dp,
                                end = 16.dp
                            )
                            .fillMaxHeight()
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        style = NoteTypography().bodyLarge.copy(fontSize = 14.sp),
                        color = NoteTheme.colors.inputFieldHintText,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = if (startContentAmount == 0) 16.dp else 0.dp, end = 16.dp)
                        .fillMaxSize()
                ) {
                    innerTextField.invoke()
                }
            }
        }
    )
}


@Composable
private fun extractTextFieldBorderColor(borderState: TextFieldBorderState): Color {
    return when (borderState) {
        TextFieldBorderState.InFocus -> {
            Color.Red
//            NoteTheme.colors.borderColor
        }

        TextFieldBorderState.InFocusAndErred -> {
            Color.Red
//            NoteTheme.colors.borderErrorColor
        }

        else -> {
            Color.Transparent
        }
    }
}

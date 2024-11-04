package com.template.cmp.common.view

data class TextFieldState(
    val title: String = "",
    val subTitle: String = "",
    val placeholder: String = "",
    val enteredValue: String = "",
    val isFocused: Boolean = false,
    val isEnabled: Boolean = true,
//    val validationState: TextFieldValidationState = TextFieldValidationState(),
)

//internal fun TextFieldState.refreshOnValueChanged(newValue: String): TextFieldState {
//    val newValueFixed = (newValue.replace("\\s+".toRegex(), " ")).let { tempNewValue ->
//        when {
//            isChangeAllowed(tempNewValue) -> tempNewValue
//
//            else -> {
//                this.enteredValue
//            }
//        }
//    }
//
//    return this.copy(
//        enteredValue = newValueFixed,
//        validationState = this.validationState.refreshOnValueChanged(fieldValue = newValueFixed),
//    )
//}
//
//fun TextFieldState.isChangeAllowed(newValue: String): Boolean {
//    return (validationState.changeAllowedCondition(newValue) || newValue.isEmpty()) &&
//            newValue.length <= validationState.maxLength
//}
//
//internal fun TextFieldState.initValue(newValue: String): TextFieldState {
//    val newValueFixed = (newValue.replace("\\s+".toRegex(), " ")).let { tempNewValue ->
//        when {
//            with(this.validationState) {
//                (changeAllowedCondition(tempNewValue) || tempNewValue.isEmpty()) && tempNewValue.length <= maxLength
//            } -> tempNewValue
//
//            else -> {
//                this.enteredValue
//            }
//        }
//    }
//
//    return this.copy(
//        enteredValue = newValueFixed,
//        validationState = this.validationState.initValue(fieldValue = newValueFixed),
//    )
//}
//
//internal fun TextFieldState.refreshOnFocusChanged(newValue: Boolean): TextFieldState {
//    return this.copy(
//        isFocused = newValue,
//        validationState = this.validationState.refreshOnFocusChanged(
//            fieldValue = this.enteredValue,
//            focused = newValue,
//        ),
//    )
//}
//
//internal fun TextFieldState.isValid() = this.validationState.isValid
//
//internal fun TextFieldState.enable() = this.copy(isEnabled = true)
//
//internal fun TextFieldState.disable() = this.copy(isEnabled = false)
//
//internal fun TextFieldState.initValidations(
//    textFieldValidationConditions: List<TextFieldValidationCondition>,
//) = this.copy(
//    validationState = this.validationState.copy(
//        validationConditions = textFieldValidationConditions.toImmutableList(),
//    )
//)
//
//internal fun TextFieldState.initValidation(
//    textFieldValidationCondition: TextFieldValidationCondition,
//) = this.copy(
//    validationState = this.validationState.copy(
//        validationConditions = persistentListOf(textFieldValidationCondition),
//    )
//)
//
//internal fun TextFieldState.initChangeAllowed(
//    changeAllowed: (String) -> Boolean,
//) = this.copy(
//    validationState = this.validationState.copy(
//        changeAllowedCondition = changeAllowed,
//    )
//)
//
//internal fun TextFieldState.initMaxLength(
//    maxLength: Int,
//) = this.copy(validationState = this.validationState.copy(maxLength = maxLength))
//
//internal fun TextFieldState.initOptional() = this.copy(
//    validationState = this.validationState.copy(
//        isOptional = true,
//        isValid = this.enteredValue.isEmpty(),
//    )
//)
//
//data class TextFieldValidationState(
//    val isValid: Boolean = false,
//    val isError: Boolean = false,
//    val errorText: String = "",
//    val isOptional: Boolean = false,
//    val isValidationAvailable: Boolean = false,
//    val validationConditions: ImmutableList<TextFieldValidationCondition> = persistentListOf(),
//    override val changeAllowedCondition: (String) -> Boolean = { true },
//    override val maxLength: Int = FIELD_GENERAL_MAX_LENGTH,
//) : TextFieldChangeAllowedProcessor
//
//private fun TextFieldValidationState.refreshOnValueChanged(fieldValue: String): TextFieldValidationState {
//    val (isError, _) = validationConditions.getError(fieldValue)
//    val isOptionalAndEmpty = isOptional && fieldValue.isEmpty()
//    return copy(
//        isValid = isOptionalAndEmpty || !isError,
//        isError = false,
//        errorText = "",
//        isValidationAvailable = true,
//    )
//}
//
//private fun TextFieldValidationState.initValue(fieldValue: String): TextFieldValidationState {
//    val (isError, _) = validationConditions.getError(fieldValue)
//    val isOptionalAndEmpty = isOptional && fieldValue.isEmpty()
//    return copy(
//        isValid = isOptionalAndEmpty || !isError,
//        isError = false,
//        errorText = "",
//        isValidationAvailable = fieldValue.isNotEmpty(),
//    )
//}
//
//private fun TextFieldValidationState.refreshOnFocusChanged(
//    fieldValue: String,
//    focused: Boolean,
//): TextFieldValidationState {
//    if (!isValidationAvailable) return copy()
//
//    val (isError, errorText) = validationConditions.getError(fieldValue)
//    val isOptionalAndEmpty = isOptional && fieldValue.isEmpty()
//    return copy(
//        isValid = isOptionalAndEmpty || !isError,
//        isError = !focused && !isOptionalAndEmpty && isError,
//        errorText = if (focused || isOptionalAndEmpty) "" else errorText,
//    )
//}
//
//class TextFieldValidationCondition(
//    override val validCondition: (String) -> Boolean,
//    val errorText: String = "",
//) : TextFieldValidationProcessor
//
//internal fun emptyFieldCondition(emptyFieldErrorText: String): TextFieldValidationCondition {
//    return TextFieldValidationCondition(
//        validCondition = { it.isNotEmpty() },
//        errorText = emptyFieldErrorText,
//    )
//}
//
//// Pair<isError, errorText>
//private fun ImmutableList<TextFieldValidationCondition>.getError(fieldValue: String): Pair<Boolean, String> {
//    val isError = this.any { !it.validCondition(fieldValue) }
//    return if (!isError) {
//        Pair(false, "")
//    } else {
//        Pair(true, this.firstOrNull { !it.validCondition(fieldValue) }?.errorText ?: "")
//    }
//}
//
//expect interface TextFieldValidationProcessor {
//    val validCondition: (String) -> Boolean
//}
//
//expect interface TextFieldChangeAllowedProcessor {
//    val changeAllowedCondition: (String) -> Boolean
//    val maxLength: Int
//}

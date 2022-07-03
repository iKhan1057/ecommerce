package com.ecommerceapp.data

import com.ecommerceapp.utils.FieldType
import com.ecommerceapp.utils.InputType

data class TextFieldModel(
    var nameoffield: String = "",
    var typeoffield: FieldType = FieldType.TEXT_EDIT,
    var inputtypeoffield: InputType = InputType.TEXT,
    var maxcount: Int = 5,
    var maxline: Int = 1,
    var label: String = "",
    var visualtransformation: Int = 0,
)
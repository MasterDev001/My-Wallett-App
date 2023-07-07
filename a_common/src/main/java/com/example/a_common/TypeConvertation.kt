package com.example.a_common

fun getTypeNumber(type: Type): Int = when (type) {
    Type.INCOME -> 1
    Type.OUTCOME -> 2
    Type.BORROW -> 3
    Type.LEND -> 4
    Type.CONVERTATION -> 5
}

fun getTypeText(type: Type) = when (type) {
    Type.INCOME -> "Kirim"
    Type.OUTCOME -> "Chiqim"
    Type.BORROW -> "Qarz olindi"
    Type.LEND -> "Qarz berildi"
    Type.CONVERTATION -> "Konvertaciya"
}

fun getTypeText(n: Int) = when (n) {
    1 -> getTypeText(Type.INCOME)
    2 -> getTypeText(Type.OUTCOME)
    3 -> getTypeText(Type.BORROW)
    4 -> getTypeText(Type.LEND)
    else -> getTypeText(Type.CONVERTATION)
}

fun getTypeEnum(n: Int) = when (n) {
    1 -> Type.INCOME
    2 -> Type.OUTCOME
    3 -> Type.BORROW
    4 -> Type.LEND
    else -> Type.CONVERTATION
}
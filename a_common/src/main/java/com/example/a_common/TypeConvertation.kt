package com.example.a_common

fun getTypeNumber(type: Type): Int = when (type) {
    Type.INCOME -> 1
    Type.OUTCOME -> 2
    Type.CREDIT -> 3
    Type.DEBIT -> 4
    Type.CONVERTATION -> 5
}
package com.example.a_common

fun getTypeNumber(type: Type): Int = when (type) {
    Type.INCOME -> 1
    Type.OUTCOME -> 2
    Type.BORROW -> 3
    Type.LEND -> 4
    Type.CONVERTATION -> 5
}
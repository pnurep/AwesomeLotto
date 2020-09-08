package com.dev.gold.awesomelotto.data


sealed class DialogException : Exception()
object OnDialogCancel : DialogException()

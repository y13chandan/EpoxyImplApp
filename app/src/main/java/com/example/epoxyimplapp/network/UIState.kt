package com.example.epoxyimplapp

sealed class UIState
data class Success(val data: Any) : UIState()
data class Failed(val error: String) : UIState()

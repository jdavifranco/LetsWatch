package com.jdavifranco.letswatch.ui.utils

abstract class ResponseState

abstract class Success:ResponseState()

class Loading:ResponseState()

class Error:ResponseState()
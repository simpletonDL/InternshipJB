package ru.itmo.mse.simpleton.dataflow.ast

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.misc.ParseCancellationException

fun Expression.collectVariables(): Set<Variable> =
    when (this) {
        is Variable -> setOf(this)
        is Const -> setOf()
        is BinOp -> left.collectVariables().union(right.collectVariables())
    }

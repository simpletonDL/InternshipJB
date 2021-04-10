package ru.itmo.mse.simpleton.dataflow.controlflow.analyzers

import ru.itmo.mse.simpleton.dataflow.controlflow.graph.ControlFlowGraph
import java.io.OutputStream

interface Analyzer {
    fun check(graph: ControlFlowGraph, output: OutputStream)
}
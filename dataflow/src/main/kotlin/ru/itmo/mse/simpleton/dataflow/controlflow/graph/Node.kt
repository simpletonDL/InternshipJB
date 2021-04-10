package ru.itmo.mse.simpleton.dataflow.controlflow.graph

import ru.itmo.mse.simpleton.dataflow.ast.*

sealed class Node

class VariableAssignment(val assignment: Assignment) : Node() {
    override fun toString(): String =
        "VariableAssignment(${assignment.varName}):${assignment.value.position}"
}

class VariableUsages(val variables: Set<Variable>) : Node() {
    override fun toString(): String =
        "VariableUsages($variables):${hashCode()%1000}"
}

class EnterScope(val statementBlock: StatementBlock) : Node() {
    override fun toString(): String =
        "EnterScope:${hashCode()%1000}"
}

class ExitScope(val statementBlock: StatementBlock) : Node() {
    override fun toString(): String =
        "ExitScope:${hashCode()%1000}"
}

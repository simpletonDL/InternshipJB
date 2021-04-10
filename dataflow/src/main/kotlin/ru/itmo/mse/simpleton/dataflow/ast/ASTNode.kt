package ru.itmo.mse.simpleton.dataflow.ast

sealed class ASTNode(val position: Int)

class Program(position: Int, val statementBlock: StatementBlock) : ASTNode(position) {
    override fun toString() = statementBlock.toString()
}

class StatementBlock(position: Int, val statements: List<Statement>) : ASTNode(position) {
    override fun toString() = statements.toString()
}

sealed class Statement(position: Int) : ASTNode(position)

sealed class Expression(position: Int) : ASTNode(position)

class Const(position: Int, val value: Int) : Expression(position) {
    override fun toString() = value.toString()
}

class Variable(position: Int, val name: String) : Expression(position) {
    override fun toString() = name
}

class BinOp(position: Int, val left: Expression, val op: String, val right: Expression) : Expression(position) {
    override fun toString() = "($left $op $right)"
}

class WhileStatement(position: Int, val condition: Expression, val body: StatementBlock) : Statement(position) {
    override fun toString() = "while ($condition) $body"
}

class IfStatemnt(position: Int, val condition: Expression, val body: StatementBlock) : Statement(position) {
    override fun toString() = "if ($condition) $body"
}

class Assignment(position: Int, val varName: String, val value: Expression) : Statement(position) {
    override fun toString() = "$varName = $value"
}

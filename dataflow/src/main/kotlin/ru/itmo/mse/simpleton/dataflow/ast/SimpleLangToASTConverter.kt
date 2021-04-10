package ru.itmo.mse.simpleton.dataflow.ast

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.antlr.v4.runtime.tree.TerminalNode
import ru.itmo.mse.simpleton.dataflow.SimpleLangLexer
import ru.itmo.mse.simpleton.dataflow.SimpleLangParser
import ru.itmo.mse.simpleton.dataflow.SimpleLangParser.*

class ConversionError(msg: String) : Exception(msg)

class ParseError : BaseErrorListener() {
    override fun syntaxError(
        recognizer: Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        throw ParseCancellationException("In line $line:$charPositionInLine: $msg")
    }
}

class SimpleLangToASTConverter {
    fun convert(path: String): Program {
        val inputStream = CharStreams.fromFileName(path)
        val lexer = SimpleLangLexer(inputStream)
        lexer.removeErrorListeners()
        lexer.addErrorListener(ParseError())

        val tokens = CommonTokenStream(lexer)

        val parser = SimpleLangParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(ParseError())

        val program = parser.program()
        return program.convert()
    }

    private fun ProgramContext.convert(): Program =
        Program(start.line, statements().convert())

    private fun StatementsContext.convert(): StatementBlock =
        StatementBlock(
            start.line,
            statement().map { it.convert() }
        )

    private fun StatementContext.convert(): Statement {
        val ifStmt: If_statContext? = if_stat()
        val whileStmt: While_statContext? = while_stat()
        val assignmentStmt: AssignmentContext? = assignment()

        return when {
            ifStmt != null -> ifStmt.convert()
            whileStmt != null -> whileStmt.convert()
            assignmentStmt != null -> assignmentStmt.convert()
            else -> throw ConversionError("Statement ${this.toStringTree()} is not if, while or assignment expression.")
        }
    }

    private fun While_statContext.convert(): WhileStatement =
        WhileStatement(start.line, expr().convert(), statements().convert())

    private fun If_statContext.convert(): IfStatemnt =
        IfStatemnt(start.line, expr().convert(), statements().convert())

    private fun AssignmentContext.convert(): Assignment =
        Assignment(start.line, ID().text, expr().convert())

    private fun ExprContext.convert(): Expression {
        val id: TerminalNode? = ID()
        val const: TerminalNode? = CONST()
        val paren: TerminalNode? = LPAREN()
        return when {
            id != null -> Variable(start.line, id.text)
            const != null -> Const(start.line, const.text.toInt())
            paren != null -> expr(0).convert()
            else -> {
                val (left, right) = expr()
                BinOp(start.line, left.convert(), op.text, right.convert())
            }
        }
    }
}
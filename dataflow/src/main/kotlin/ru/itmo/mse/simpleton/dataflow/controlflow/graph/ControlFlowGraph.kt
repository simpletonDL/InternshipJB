package ru.itmo.mse.simpleton.dataflow.controlflow.graph

import ru.itmo.mse.simpleton.dataflow.ast.*

class ControlFlowGraph() {
    private val edges: HashMap<Node, ArrayList<Node>> = hashMapOf()

    constructor(program: Program) : this() {
        populateControlFlowGraph(program)
    }

    fun addEdge(v: Node, to: Node) {
        edges.computeIfAbsent(v) { arrayListOf() }
        edges.get(v)!!.add(to)
    }

    operator fun get(v: Node): List<Node> {
        return edges[v] ?: listOf()
    }

    fun vertices(): Set<Node> {
        return edges.keys
    }

    override fun toString(): String {
        return edges.entries.joinToString(separator = "\n") { it.toString() }
    }

    private fun populateControlFlowGraph(node: Program): Pair<Node, Node> =
        populateControlFlowGraph(node.statementBlock)

    private fun populateControlFlowGraph(block: StatementBlock): Pair<Node, Node> {
        val enter: Node = EnterScope(block)
        val exit: Node = ExitScope(block)
        val v =
            block.statements.fold(enter) { v, stat ->
                val (begin, end) = populateControlFlowGraph(stat)
                addEdge(v, begin)
                end
            }
        addEdge(v, exit)
        return Pair(enter, exit)
    }

    private fun populateControlFlowGraph(statement: Statement): Pair<Node, Node> =
        when (statement) {
            is Assignment -> {
                val usages = statement.value.collectVariables()
                val enter = VariableUsages(usages)
                val exit = VariableAssignment(statement)
                addEdge(enter, exit)
                Pair(enter, exit)
            }
            is IfStatemnt -> {
                val condition = VariableUsages(statement.condition.collectVariables())
                val (enterBlock, exitBlock) = populateControlFlowGraph(statement.body)
                addEdge(condition, enterBlock)
                addEdge(condition, exitBlock)
                Pair(condition, exitBlock)
            }
            is WhileStatement -> {
                val condition = VariableUsages(statement.condition.collectVariables())
                val (enterBlock, exitBlock) = populateControlFlowGraph(statement.body)
                addEdge(condition, enterBlock)
                addEdge(condition, exitBlock)
                addEdge(exitBlock, condition)
                Pair(condition, exitBlock)
            }
        }
}

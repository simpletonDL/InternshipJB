package ru.itmo.mse.simpleton.dataflow.controlflow.analyzers

import ru.itmo.mse.simpleton.dataflow.ast.Assignment
import ru.itmo.mse.simpleton.dataflow.controlflow.graph.ControlFlowGraph
import ru.itmo.mse.simpleton.dataflow.controlflow.graph.Node
import ru.itmo.mse.simpleton.dataflow.controlflow.graph.VariableAssignment
import ru.itmo.mse.simpleton.dataflow.controlflow.graph.VariableUsages
import java.io.OutputStream
import java.io.PrintStream

class NotUsedAssignments : Analyzer {
    override fun check(graph: ControlFlowGraph, output: OutputStream) {
        val stream = PrintStream(output)
        for (assignment in findNotUsedAssignments(graph).sortedBy { it.position }) {
            stream.println("Assignment $assignment not used in line ${assignment.position}")
        }
    }

    private fun findNotUsedAssignments(graph: ControlFlowGraph): List<Assignment> {
        val xs: MutableList<Assignment> = mutableListOf()

        for (node in graph.vertices()) {
            if (node is VariableAssignment) {
                if (!existVariableUsage(node, node.assignment.varName, graph)) {
                    xs.add(node.assignment)
                }
            }
        }
        return xs
    }

    private fun existVariableUsage(startNode: Node, varName: String, graph: ControlFlowGraph): Boolean {
        val visited: MutableSet<Node> = mutableSetOf(startNode)
        return graph[startNode].any { dfs(it, varName, graph, visited) }
    }

    private fun dfs(v: Node, varName: String, graph: ControlFlowGraph, visited: MutableSet<Node>): Boolean {
        visited.add(v)
        return when {
            v is VariableAssignment && v.assignment.varName == varName ->
                false
            v is VariableUsages && varName in v.variables.map { it.name } ->
                true
            else -> {
                val nextVertices = graph[v].filter { it !in visited }
                nextVertices.any { dfs(it, varName, graph, visited) }
            }
        }
    }
}
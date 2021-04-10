package ru.itmo.mse.simpleton.dataflow

import org.junit.Assert
import org.junit.Test
import ru.itmo.mse.simpleton.dataflow.ast.SimpleLangToASTConverter
import ru.itmo.mse.simpleton.dataflow.controlflow.analyzers.Analyzer
import ru.itmo.mse.simpleton.dataflow.controlflow.analyzers.NotUsedAssignments
import ru.itmo.mse.simpleton.dataflow.controlflow.graph.ControlFlowGraph
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

class NotUsageAnalyzerTest {
    private fun check(sourcePath: Path, expectedPath: Path) {
        val converter = SimpleLangToASTConverter()
        val ast = converter.convert(sourcePath.toAbsolutePath().toString())
        val graph = ControlFlowGraph(ast)

        val analyzer: Analyzer = NotUsedAssignments()
        val output = ByteArrayOutputStream()
        analyzer.check(graph, output)

        val actual = output.toString()
        val expected = File(expectedPath.toAbsolutePath().toString()).readText()
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun test1() {
        check(Paths.get("src/test/data/test1.input"), Paths.get("src/test/data/test1.expected"))
    }

    @Test
    fun test2() {
        check(Paths.get("src/test/data/test2.input"), Paths.get("src/test/data/test2.expected"))
    }

    @Test
    fun test3() {
        check(Paths.get("src/test/data/test3.input"), Paths.get("src/test/data/test3.expected"))
    }

    @Test
    fun test4() {
        check(Paths.get("src/test/data/test4.input"), Paths.get("src/test/data/test4.expected"))
    }

    @Test
    fun test5() {
        check(Paths.get("src/test/data/test5.input"), Paths.get("src/test/data/test5.expected"))
    }

    @Test
    fun test6() {
        check(Paths.get("src/test/data/test6.input"), Paths.get("src/test/data/test6.expected"))
    }

    @Test
    fun test7() {
        check(Paths.get("src/test/data/test7.input"), Paths.get("src/test/data/test7.expected"))
    }
}
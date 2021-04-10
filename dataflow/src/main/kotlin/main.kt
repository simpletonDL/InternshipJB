import org.antlr.v4.runtime.misc.ParseCancellationException
import ru.itmo.mse.simpleton.dataflow.ast.SimpleLangToASTConverter
import ru.itmo.mse.simpleton.dataflow.controlflow.analyzers.Analyzer
import ru.itmo.mse.simpleton.dataflow.controlflow.analyzers.NotUsedAssignments
import ru.itmo.mse.simpleton.dataflow.controlflow.graph.ControlFlowGraph
import java.nio.file.Paths
import java.nio.file.NoSuchFileException

fun printErr(errorMsg:String){
    System.err.println(errorMsg)
}

fun main(args: Array<String>) {
    if (args.size != 1) {
        printErr("Expected 1 argument but given ${args.size}")
        return
    }

    val path = Paths.get("").resolve(args[0])
    try {
        val converter = SimpleLangToASTConverter()
        val ast = converter.convert(path.toAbsolutePath().toString())

        val graph = ControlFlowGraph(ast)
        val checker: Analyzer = NotUsedAssignments()
        checker.check(graph, System.out)
    } catch (e: NoSuchFileException) {
        printErr("File ${e.file} not found")
    } catch (e: ParseCancellationException) {
        printErr("Parse error: ${e.message}")
    } catch (e: Exception) {
        printErr("Something going wrong, please report to simpletondl@yandex.ru")
    }
}

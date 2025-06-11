package gradheat.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import gradheat.core.GradheatAnalyzer
import gradheat.html.GradheatHtmlRenderer
import java.io.File

class AnalyzeCommand : CliktCommand(name = "analyze", help = "Run and analyze Gradle build") {
    private val task: String by option("--task").default("build")
    private val dryRun: Boolean by option("--dry-run").flag(default = false)

    override fun run() {
        val process = ProcessBuilder("./gradlew", task, "--info")
            .directory(File(System.getProperty("user.dir")))
            .redirectErrorStream(true)
            .start()

        val output = process.inputStream.bufferedReader().readText()
        val result = GradheatAnalyzer.analyze(output)
        println(GradheatHtmlRenderer.render(result))
    }
}

fun main(args: Array<String>) = AnalyzeCommand().main(args)

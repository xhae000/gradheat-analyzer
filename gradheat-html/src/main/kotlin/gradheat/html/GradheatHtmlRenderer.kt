package gradheat.html

import gradheat.core.AnalysisResult
import java.io.File

object GradheatHtmlRenderer {
    fun render(result: AnalysisResult): String {
        val template = javaClass.getResource("/template.html")!!.readText()

        val renderedTasks = result.taskDurations
            .sortedByDescending { it.durationMs }
            .joinToString("\n") { "<li><strong>${it.path}</strong>: ${it.durationMs} ms</li>" }

        val html = template.replace("{{tasks}}", renderedTasks)

        val outputPath = "build/gradheat-report/index.html"
        File(outputPath).apply {
            parentFile.mkdirs()
            writeText(html)
        }

        return File(outputPath).absolutePath
    }
}

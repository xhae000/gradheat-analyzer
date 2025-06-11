package gradheat.core

data class AnalysisResult(val taskDurations: List<TaskDuration>)
data class TaskDuration(val path: String, val durationMs: Long)

object GradheatAnalyzer {
    fun analyze(log: String): AnalysisResult {
        val tasks = Regex("""(?::[\w-]+)+.*took ([\d.]+)s""")
            .findAll(log)
            .map {
                val task = it.groupValues[0].split(" ")[0]
                val seconds = it.groupValues[1].toDouble()
                TaskDuration(task, (seconds * 1000).toLong())
            }.toList()
        return AnalysisResult(tasks)
    }
}

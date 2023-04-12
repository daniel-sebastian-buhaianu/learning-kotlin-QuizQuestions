import kotlin.math.round

fun main() {
    Quiz().printProgressBar()
}

/*
class FillInTheBlankQuestion(
    val questionText: String,
    val answer: String,
    val difficulty: String
)

class TrueOrFalseQuestion(
    val questionText: String,
    val answer: Boolean,
    val difficulty: String
)

class NumericQuestion(
    val questionText: String,
    val answer: Int,
    val difficulty: String
)
*/

data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}

interface ProgressPrintable {
    val progressText: String
    fun printProgressBar()
}

class Quiz : ProgressPrintable {
    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 28, Difficulty.HARD)

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 5
    }

    override val progressText: String
        get() = "${answered} of ${total} questions answered"

    override fun printProgressBar() {
        val progressBarLength: Double = progressText.length.toDouble()
        val factor: Double = progressBarLength / total
        val currentAnswers = round(answered * factor).toInt()
        val remainingAnswers = (progressBarLength - round(answered * factor)).toInt()

        repeat(currentAnswers) { print("▓") }
        repeat(remainingAnswers) { print("▒") }

        println()
        println(progressText)
    }
}
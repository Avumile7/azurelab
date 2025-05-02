package com.example.flashcardapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuestionActivityUnitTest {

    @Test
    fun testInitialState() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            assertEquals(0, activity.currentQuestionIndex)
            assertEquals(0, activity.score)
            assertEquals(activity.questions[0], activity.questionTextView.text.toString())
            assertEquals("", activity.feedbackTextView.text.toString())
            assertFalse(activity.trueButton.isEnabled) // Buttons are enabled only after onCreate in a real lifecycle
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.INVISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testCorrectAnswerSelection() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            val currentAnswer = activity.answers[activity.currentQuestionIndex]
            val answerButton = if (currentAnswer) activity.trueButton else activity.falseButton
            answerButton.performClick()

            assertEquals("Correct!", activity.feedbackTextView.text.toString())
            assertEquals(1, activity.score)
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.VISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testIncorrectAnswerSelection() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            val currentAnswer = activity.answers[activity.currentQuestionIndex]
            val wrongAnswerButton = if (currentAnswer) activity.falseButton else activity.trueButton
            wrongAnswerButton.performClick()

            assertEquals("Incorrect!", activity.feedbackTextView.text.toString())
            assertEquals(0, activity.score)
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.VISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testNextQuestionNavigation() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            // Simulate a correct answer to enable the Next button
            if (activity.answers[activity.currentQuestionIndex]) {
                activity.trueButton.performClick()
            } else {
                activity.falseButton.performClick()
            }
            activity.nextButton.performClick()

            assertEquals(1, activity.currentQuestionIndex)
            assertEquals(activity.questions[1], activity.questionTextView.text.toString())
            assertEquals("", activity.feedbackTextView.text.toString())
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.INVISIBLE, activity.nextButton.visibility) // Hidden until next answer
        }
    }

    @Test
    fun testQuizCompletion() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            // Answer all questions
            for (i in 0 until activity.questions.size) {
                if (activity.answers[activity.currentQuestionIndex]) {
                    activity.trueButton.performClick()
                } else {
                    activity.falseButton.performClick()
                }
                if (i < activity.questions.size - 1) {
                    activity.nextButton.performClick()
                }
            }

            // After the last question, the activity should finish (navigate to ScoreActivity)
            assertTrue(scenario.state.isDestroyed) // Check if the activity is finished
            // Note: We can't directly assert the Intent to ScoreActivity in a unit test
            // without more mocking/instrumentation. For that, use Instrumentation Tests.
        }
    }
}package com.example.flashcardapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuestionActivityUnitTest {

    @Test
    fun testInitialState() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            assertEquals(0, activity.currentQuestionIndex)
            assertEquals(0, activity.score)
            assertEquals(activity.questions[0], activity.questionTextView.text.toString())
            assertEquals("", activity.feedbackTextView.text.toString())
            assertFalse(activity.trueButton.isEnabled) // Buttons are enabled only after onCreate in a real lifecycle
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.INVISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testCorrectAnswerSelection() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            val currentAnswer = activity.answers[activity.currentQuestionIndex]
            val answerButton = if (currentAnswer) activity.trueButton else activity.falseButton
            answerButton.performClick()

            assertEquals("Correct!", activity.feedbackTextView.text.toString())
            assertEquals(1, activity.score)
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.VISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testIncorrectAnswerSelection() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            val currentAnswer = activity.answers[activity.currentQuestionIndex]
            val wrongAnswerButton = if (currentAnswer) activity.falseButton else activity.trueButton
            wrongAnswerButton.performClick()

            assertEquals("Incorrect!", activity.feedbackTextView.text.toString())
            assertEquals(0, activity.score)
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.VISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testNextQuestionNavigation() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            // Simulate a correct answer to enable the Next button
            if (activity.answers[activity.currentQuestionIndex]) {
                activity.trueButton.performClick()
            } else {
                activity.falseButton.performClick()
            }
            activity.nextButton.performClick()

            assertEquals(1, activity.currentQuestionIndex)
            assertEquals(activity.questions[1], activity.questionTextView.text.toString())
            assertEquals("", activity.feedbackTextView.text.toString())
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.INVISIBLE, activity.nextButton.visibility) // Hidden until next answer
        }
    }

    @Test
    fun testQuizCompletion() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            // Answer all questions
            for (i in 0 until activity.questions.size) {
                if (activity.answers[activity.currentQuestionIndex]) {
                    activity.trueButton.performClick()
                } else {
                    activity.falseButton.performClick()
                }
                if (i < activity.questions.size - 1) {
                    activity.nextButton.performClick()
                }
            }

            // After the last question, the activity should finish (navigate to ScoreActivity)
            assertTrue(scenario.state.isDestroyed) // Check if the activity is finished
            // Note: We can't directly assert the Intent to ScoreActivity in a unit test
            // without more mocking/instrumentation. For that, use Instrumentation Tests.
        }
    }
}package com.example.flashcardapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuestionActivityUnitTest {

    @Test
    fun testInitialState() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            assertEquals(0, activity.currentQuestionIndex)
            assertEquals(0, activity.score)
            assertEquals(activity.questions[0], activity.questionTextView.text.toString())
            assertEquals("", activity.feedbackTextView.text.toString())
            assertFalse(activity.trueButton.isEnabled) // Buttons are enabled only after onCreate in a real lifecycle
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.INVISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testCorrectAnswerSelection() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            val currentAnswer = activity.answers[activity.currentQuestionIndex]
            val answerButton = if (currentAnswer) activity.trueButton else activity.falseButton
            answerButton.performClick()

            assertEquals("Correct!", activity.feedbackTextView.text.toString())
            assertEquals(1, activity.score)
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.VISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testIncorrectAnswerSelection() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            val currentAnswer = activity.answers[activity.currentQuestionIndex]
            val wrongAnswerButton = if (currentAnswer) activity.falseButton else activity.trueButton
            wrongAnswerButton.performClick()

            assertEquals("Incorrect!", activity.feedbackTextView.text.toString())
            assertEquals(0, activity.score)
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.VISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testNextQuestionNavigation() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            // Simulate a correct answer to enable the Next button
            if (activity.answers[activity.currentQuestionIndex]) {
                activity.trueButton.performClick()
            } else {
                activity.falseButton.performClick()
            }
            activity.nextButton.performClick()

            assertEquals(1, activity.currentQuestionIndex)
            assertEquals(activity.questions[1], activity.questionTextView.text.toString())
            assertEquals("", activity.feedbackTextView.text.toString())
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.INVISIBLE, activity.nextButton.visibility) // Hidden until next answer
        }
    }

    @Test
    fun testQuizCompletion() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            // Answer all questions
            for (i in 0 until activity.questions.size) {
                if (activity.answers[activity.currentQuestionIndex]) {
                    activity.trueButton.performClick()
                } else {
                    activity.falseButton.performClick()
                }
                if (i < activity.questions.size - 1) {
                    activity.nextButton.performClick()
                }
            }

            // After the last question, the activity should finish (navigate to ScoreActivity)
            assertTrue(scenario.state.isDestroyed) // Check if the activity is finished
            // Note: We can't directly assert the Intent to ScoreActivity in a unit test
            // without more mocking/instrumentation. For that, use Instrumentation Tests.
        }
    }
}package com.example.flashcardapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuestionActivityUnitTest {

    @Test
    fun testInitialState() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            assertEquals(0, activity.currentQuestionIndex)
            assertEquals(0, activity.score)
            assertEquals(activity.questions[0], activity.questionTextView.text.toString())
            assertEquals("", activity.feedbackTextView.text.toString())
            assertFalse(activity.trueButton.isEnabled) // Buttons are enabled only after onCreate in a real lifecycle
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.INVISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testCorrectAnswerSelection() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            val currentAnswer = activity.answers[activity.currentQuestionIndex]
            val answerButton = if (currentAnswer) activity.trueButton else activity.falseButton
            answerButton.performClick()

            assertEquals("Correct!", activity.feedbackTextView.text.toString())
            assertEquals(1, activity.score)
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.VISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testIncorrectAnswerSelection() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            val currentAnswer = activity.answers[activity.currentQuestionIndex]
            val wrongAnswerButton = if (currentAnswer) activity.falseButton else activity.trueButton
            wrongAnswerButton.performClick()

            assertEquals("Incorrect!", activity.feedbackTextView.text.toString())
            assertEquals(0, activity.score)
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.VISIBLE, activity.nextButton.visibility)
        }
    }

    @Test
    fun testNextQuestionNavigation() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            // Simulate a correct answer to enable the Next button
            if (activity.answers[activity.currentQuestionIndex]) {
                activity.trueButton.performClick()
            } else {
                activity.falseButton.performClick()
            }
            activity.nextButton.performClick()

            assertEquals(1, activity.currentQuestionIndex)
            assertEquals(activity.questions[1], activity.questionTextView.text.toString())
            assertEquals("", activity.feedbackTextView.text.toString())
            assertFalse(activity.trueButton.isEnabled)
            assertFalse(activity.falseButton.isEnabled)
            assertEquals(android.view.View.INVISIBLE, activity.nextButton.visibility) // Hidden until next answer
        }
    }

    @Test
    fun testQuizCompletion() {
        val scenario = ActivityScenario.launch(QuestionActivity::class.java)
        scenario.onActivity { activity ->
            // Answer all questions
            for (i in 0 until activity.questions.size) {
                if (activity.answers[activity.currentQuestionIndex]) {
                    activity.trueButton.performClick()
                } else {
                    activity.falseButton.performClick()
                }
                if (i < activity.questions.size - 1) {
                    activity.nextButton.performClick()
                }
            }

            // After the last question, the activity should finish (navigate to ScoreActivity)
            assertTrue(scenario.state.isDestroyed) // Check if the activity is finished
            // Note: We can't directly assert the Intent to ScoreActivity in a unit test
            // without more mocking/instrumentation. For that, use Instrumentation Tests.
        }
    }
}
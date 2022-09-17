package ge.nlatsabidze.newsapplication.presentation.ui.notification

import junit.framework.TestCase

class ProvideContentTest : TestCase() {

    fun testSuccessContent() {
        val fakeContent = mutableListOf(
            GenerateContent.Words("Abnegation", "Renouncing a belief or doctrine"),
            GenerateContent.Words("Aggrandize", "enhance power, wealth or status"),
            GenerateContent.Words("Alacrity", "Eagerness"),
            GenerateContent.Words("Beguile", "influence someone in a deceptive way"),
            GenerateContent.Words("Blandishment", "intentional flattery for persuasion"),
            GenerateContent.Words("Callous", "disregard for others"),
            GenerateContent.Words("Cognizant", " awareness or realization"),
            GenerateContent.Words("Convivial", "enjoyable atmosphere or jovial company")
        )


        val content = ProvideContent.Base()

        assertEquals(fakeContent, content.content())
    }

    fun testErrorContent() {
        val fakeContent = mutableListOf(
            GenerateContent.Words("Abnegation", "Renouncing a belief or doctrine"),
            GenerateContent.Words("Aggrandize", "enhance power, wealth or status"),
            GenerateContent.Words("ERROR", "Eagerness"),
            GenerateContent.Words("Beguile", "influence someone in a deceptive way"),
            GenerateContent.Words("Blandishment", "intentional flattery for persuasion"),
            GenerateContent.Words("Callous", "disregard for others"),
            GenerateContent.Words("Cognizant", " awareness or realization"),
            GenerateContent.Words("Convivial", "enjoyable atmosphere or jovial company")
        )

        val content = ProvideContent.Base()

        assertFalse(fakeContent == content.content())
    }
}
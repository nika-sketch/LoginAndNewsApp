package ge.nlatsabidze.newsapplication.presentation.ui.notification

interface ProvideContent {

    fun content(): MutableList<GenerateContent>

    class Base : ProvideContent {
        override fun content(): MutableList<GenerateContent> =
            mutableListOf(
                GenerateContent.Words("Abnegation", "Renouncing a belief or doctrine"),
                GenerateContent.Words("Aggrandize", "enhance power, wealth or status"),
                GenerateContent.Words("Alacrity", "Eagerness"),
                GenerateContent.Words("Beguile", "influence someone in a deceptive way"),
                GenerateContent.Words("Blandishment", "intentional flattery for persuasion"),
                GenerateContent.Words("Callous", "disregard for others"),
                GenerateContent.Words("Cognizant", " awareness or realization"),
                GenerateContent.Words("Convivial", "enjoyable atmosphere or jovial company")
            )
    }
}
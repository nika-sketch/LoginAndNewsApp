package ge.nlatsabidze.newsapplication.presentation.ui.notification

import javax.inject.Inject

interface RandomWordsDescription {

    fun content(): GenerateContent

    class Base @Inject constructor(
        private val generateRandomNumber: GenerateRandomNumber<Int>,
        private val shuffleList: ShuffleList<GenerateContent>,
        private val provideContent: ProvideContent = ProvideContent.Base()
    ) : RandomWordsDescription {

        override fun content(): GenerateContent {
            shuffleList.shuffle(provideContent.content())
            return provideContent.content()[generateRandomNumber.randomNumber(provideContent.content().size)]
        }
    }
}


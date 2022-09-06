package ge.nlatsabidze.newsapplication.presentation.ui.notification

import javax.inject.Inject

interface RandomWordsDescription {

    fun content(): GenerateContent

    class Base @Inject constructor(
        private val generateRandomNumber: GenerateRandomNumber<Int>,
        private val shuffleList: ShuffleList<GenerateContent>,
    ) : RandomWordsDescription {

        private val listOfWords = mutableListOf<GenerateContent>(
            GenerateContent.Words("dog", "Dog"),
            GenerateContent.Words("cat", "Cat"),
            GenerateContent.Words("lion", "Lion"),
            GenerateContent.Words("Android", "Android"),
            GenerateContent.Words("IOS", "IOS"),
            GenerateContent.Words("Flutter", "Flutter"),
            GenerateContent.Words("React", "React"),
            GenerateContent.Words("Java", "Java")
        )

        override fun content(): GenerateContent {
            shuffleList.shuffle(listOfWords)
            return listOfWords[generateRandomNumber.randomNumber(listOfWords.size)]
        }
    }
}

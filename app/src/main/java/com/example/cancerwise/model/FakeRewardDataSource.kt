package com.example.cancerwise.model

import com.example.cancerwise.R

object FakeRewardDataSource {

    private val dummyQuestions = listOf(
        Question(1, 1, "PDI > Jokowi?", "int"),
        Question(1, 2, "PDI > Prabowo?", "boolean"),
        Question(1, 3, "PDI > Megawati?", "int"),
        Question(1, 4, "PDI > Puan?", "boolean"),
        Question(1, 5, "PDI > A?", "boolean"),
        Question(1, 6, "PDI > Izul?", "boolean"),
        Question(1, 7, "PDI > Izul?"," int"),
        Question(1, 8, "PDI > Ban?", "int"),
        Question(1, 9, "PDI > Zo?", "boolean"),
        Question(1, 10, "PDI > mboh?", "boolean")
    )

    val dummyProduct = arrayListOf(
        Quizioner(1,"Lung Cancer", "Lung cancer is bla bla bla", R.drawable.img_lung_vectzee , dummyQuestions ),
        Quizioner(2,"Brain Cancer", "Lung cancer is bla bla bla", R.drawable.brain , dummyQuestions ),
        Quizioner(3,"Breast Cancer", "Lung cancer is bla bla bla", R.drawable.prostate , dummyQuestions ),
        Quizioner(4,"Prostate Cancer", "Lung cancer is bla bla bla", R.drawable.brain , dummyQuestions )
    )

    val dummQuizioner = Quizioner(1,"Lung Cancer", "Lung cancer is bla bla bla", R.drawable.lung_img , dummyQuestions )

    val dummyHistory = arrayListOf(
        History("Lung Cancerr", 98, "Sick", "22/09/2023", R.drawable.img_lung_vectzee),
        History("Lung Cancerr", 98, "Sick", "22/09/2023", R.drawable.img_lung_vectzee),
        History("Lung Cancerr", 98, "Sick", "22/09/2023", R.drawable.img_lung_vectzee),
        History("Lung Cancerr", 98, "Sick", "22/09/2023", R.drawable.img_lung_vectzee),
        History("Lung Cancerr", 98, "Sick", "22/09/2023", R.drawable.img_lung_vectzee),
        History("Lung Cancerr", 98, "Sick", "22/09/2023", R.drawable.img_lung_vectzee)
    )
}
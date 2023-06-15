package com.example.cancerwise.model

import com.example.cancerwise.R

object FakeRewardDataSource {

    private val dummyQuestions = listOf(
        Question(0, 1, "How old are you?", "int",0),
        Question(0, 2, "Tell us your gender? Yes for male and No for female?", "boolean",0),
        Question(0, 3, "Are your fingertips yellow?", "boolean",0),
        Question(0, 4, "Do you have an anxiety disorder?", "boolean",0),
        Question(0, 5, "Are you under social pressure?", "boolean",0),
        Question(0, 6, "Do you have a chronic disease?", "boolean",0),
        Question(0, 7, "Do you often feel tired easily?", "boolean",0),
        Question(0, 8, "Do you have any allergies?", "boolean",0),
        Question(0, 9, "When breathing, do you often make high-pitched sounds like whistling (wheezing)?", "boolean",0),
        Question(0, 10, "Do you drink alcohol often enough?", "boolean",0),
        Question(0, 11, "Are you experiencing symptoms in the form of coughing?", "boolean",0),
        Question(0, 12, "Do you have difficulty swallowing?", "boolean",0),
        Question(0, 13, "Do you often experience pain in the chest?", "boolean",0),
    )

    private val dummyQuestions2 = listOf(
        Question(1, 1, "How old are you?", "int",0),
        Question(1, 2, "Tell us your gender? Yes for female and No for male", "boolean",0),
        Question(1, 3, "How often you felt dizzy?", "int",2),
        Question(1, 4, "How often your vision becoming blurry?", "int",2),
        Question(1, 5, "How often do you experience double vision?", "int",2),
        Question(1, 6, "How often have you lost your peripheral vision?", "int",2),
        Question(1, 7, "How often do you lose your sense of smell (can't smell)?", "int",2),
        Question(1, 8, "How often do you experience balance disorders?", "int",2),
        Question(1, 9, "How often do you feel nauseous?", "int",2),
        Question(1, 10, "How often do you feel like you've vomited?", "int",2),
        Question(1, 11, "How often do you feel sleepy?", "int",2),
        Question(1, 12, "How often do you feel you have a personality disorder?", "int",2),
        Question(1, 13, "How often have you felt numbness in your arm?", "int",2),
        Question(1, 14, "How often do you feel numbness in your feet??", "int",2),
    )

    private val dummyQuestions3 = listOf(
        Question(2, 1, "How old are you?", "int",0),
        Question(2, 2, "How long (in years) have you been using hormonal contraception? If never, enter 0", "int",0),
        Question(2, 3, "How many sexually transmitted diseases (eg HIV, HPV, etc.) have you had?", "int",0),
        Question(2, 4, "How many times have you been diagnosed with a sexually transmitted disease (eg HIV, HPV, etc.)", "int",0),
        Question(2, 5, "Do you have a history of the sexually transmitted disease condylomatosis?", "boolean",0),
        Question(2, 6, "Do you have a history of sexually transmitted disease vulvo-perineal condylomatosis??", "boolean",0),
        Question(2, 7, "Do you have a history of genital herpes?", "boolean",0),
        Question(2, 8, "Do you have a history of HIV sexually transmitted diseases", "boolean",0),
        Question(2, 9, "Have you ever been diagnosed with cancer?", "boolean",0),
        Question(2, 10, "Have you ever been diagnosed with cervical intraepithelial neoplasia (CIN)?", "boolean",0),
        Question(2, 11, "Have you ever been diagnosed with the Human Papillomavirus (HPV)?", "boolean",0),
        Question(2, 12, "Have you been diagnosed with any other diseases associated with Cervical Cancer?", "boolean",0),
        Question(2, 13, "How did your Hinselmann test result? If (+), enter number 1 and if negative or haven't done the test, enter number 0", "boolean",3),
        Question(2, 14, "How did your Schiller test result go? If (+), enter number 1 and if negative or haven't done the test, enter number 0", "boolean",3),
        Question(2, 15, "What is the result of your Cytology test If (+), enter 1 and if negative or have not done the test, enter 0", "boolean",3),
    )

    val dummyProduct = arrayListOf(
        Quizioner(0,"Lung Cancer",
            22000000,
            78,
            4,
            9,
            "Lung cancer is a type of cancer that starts in the lungs, typically in the cells lining the air passages. It is one of the most common and deadliest types of cancer worldwide. The main risk factor for lung cancer is smoking, including both active and passive exposure to tobacco smoke. Other factors like exposure to environmental pollutants, family history, and certain genetic mutations can also increase the risk.\n" +
                    "\n" +
                    "Lung cancer can be categorized into two main types: non-small cell lung cancer (NSCLC) and small cell lung cancer (SCLC). NSCLC is the most common form, while SCLC is less common but tends to grow and spread rapidly.\n" +
                    "\n" +
                    "Symptoms of lung cancer may include a persistent cough, chest pain, shortness of breath, weight loss, fatigue, and recurrent respiratory infections. However, symptoms may not appear until the disease has advanced.\n" +
                    "\n" +
                    "Diagnosis involves imaging tests, such as X-rays or CT scans, along with tissue biopsy and molecular testing. Treatment options depend on the type and stage of lung cancer but may include surgery, radiation therapy, chemotherapy, targeted therapy, and immunotherapy. The goal is to remove or kill cancer cells, shrink tumors, relieve symptoms, and improve quality of life.\n" +
                    "\n" +
                    "Prevention involves avoiding or quitting smoking, reducing exposure to secondhand smoke and environmental carcinogens, and considering regular screenings for high-risk individuals.\n" +
                    "\n" +
                    "It's essential to consult medical professionals or trusted sources for detailed and up-to-date information, as lung cancer research and treatments continue to evolve."
            ,R.drawable.file_lung , dummyQuestions ),
        Quizioner(1,"Brain Tumor",
            308102 ,
            70,
            3,
            16,
            "A brain tumor is an abnormal growth of cells in the brain. It can originate from brain cells (primary brain tumor) or spread to the brain from other parts of the body (secondary or metastatic brain tumor). Brain tumors can be benign (non-cancerous) or malignant (cancerous).\n" +
                    "\n" +
                    "The exact causes of brain tumors are often unknown, but certain risk factors may increase the likelihood of developing them, such as exposure to radiation, a family history of brain tumors, certain genetic disorders, and rarely, certain environmental factors.\n" +
                    "\n" +
                    "Brain tumor symptoms can vary depending on the location, size, and type of tumor. Common symptoms include headaches, seizures, cognitive and memory problems, personality or behavior changes, motor or sensory deficits, balance and coordination issues, and changes in vision or hearing.\n" +
                    "\n" +
                    "Diagnosis involves a combination of medical history evaluation, neurological examination, imaging tests (such as MRI or CT scans), and sometimes a biopsy to examine the tumor cells. The specific treatment options for brain tumors depend on several factors, including the type, size, location, and grade of the tumor. Treatment may involve surgery to remove the tumor, radiation therapy, chemotherapy, targeted therapy, or a combination of these approaches.\n" +
                    "\n" +
                    "The prognosis for brain tumors can vary widely depending on the type, grade, and stage of the tumor, as well as the individual's age and overall health. Some brain tumors can be successfully treated and even cured, while others may have a more challenging prognosis.\n" +
                    "\n" +
                    "It is important for individuals experiencing symptoms or concerned about brain tumors to seek medical attention and consult with healthcare professionals for an accurate diagnosis and appropriate treatment plan."
            ,R.drawable.prostate_file , dummyQuestions2 ),
        Quizioner(2,"Cervical Cancer",
            604127,
            42,
            2,
            12,
            "Cervical cancer is a type of cancer that affects the cervix, which is the lower part of the uterus that connects to the vagina. It is primarily caused by certain strains of the human papillomavirus (HPV), a common sexually transmitted infection.\n" +
                    "\n" +
                    "Cervical cancer typically develops slowly over time, starting with the growth of abnormal cells on the surface of the cervix. These abnormal cells can progress to precancerous lesions and, if left untreated, may eventually become invasive cancer.\n" +
                    "\n" +
                    "Risk factors for cervical cancer include persistent infection with high-risk HPV strains, early onset of sexual activity, multiple sexual partners, a weakened immune system, smoking, long-term use of hormonal contraceptives, and a family history of cervical cancer.\n" +
                    "\n" +
                    "In its early stages, cervical cancer often does not cause noticeable symptoms. As the disease progresses, symptoms may include abnormal vaginal bleeding (such as between periods or after menopause), pelvic pain, pain during sexual intercourse, and unusual vaginal discharge.\n" +
                    "\n" +
                    "Regular cervical cancer screening, typically done through a Pap smear or HPV test, is essential for early detection and prevention. Screening can detect abnormal cells or HPV infection before they develop into cancer or identify cancer at an early and more treatable stage.\n" +
                    "\n" +
                    "If cervical cancer is diagnosed, treatment options depend on the stage and extent of the disease. They may include surgery to remove the cancerous tissue, radiation therapy, chemotherapy, targeted therapy, or a combination of these approaches. The goal is to remove or destroy the cancer and prevent its spread.\n" +
                    "\n" +
                    "Prevention is a crucial aspect of cervical cancer control. Vaccines are available to protect against certain strains of HPV that are known to cause cervical cancer. Safe sexual practices, such as using condoms, and regular screening for early detection are also important preventive measures.\n" +
                    "\n" +
                    "Cervical cancer is a significant global health issue, but with appropriate screening, vaccination, and timely treatment, its incidence and mortality rates can be significantly reduced. It is important to consult with healthcare professionals for guidance on screening, prevention, and treatment options specific to individual circumstances.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n"
            ,R.drawable.brain_file , dummyQuestions3 ),
    )

    val dummyHistory = arrayListOf(
        History(1000021, "Lung Cancer", 89, "09:00, 22/09/2023"),
        History(1000022, "Prostate Cancer", 89, "18:00, 24/09/2023"),
        History(1000024, "Brain Tumor", 89, "17:00, 25/09/2023"),
        History(1000025, "Lung Cancer", 89, "12:00, 29/09/2023"),
    )
}
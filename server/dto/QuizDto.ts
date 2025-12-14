export interface QuizQuestionDto {
    description: string
    options: string[]
    answer: number
}

export interface QuizDto {
    title: string
    questions: QuizQuestionDto[]
}
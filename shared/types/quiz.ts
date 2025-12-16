export interface QuestionDto {
    _id: string
    description: string
    options: string[]
    answer: number
}

export interface QuizDto {
    _id: string
    title: string
    description: string
    questions: QuestionDto[]
}
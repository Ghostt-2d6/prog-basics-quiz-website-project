export interface Question {
    _id: string
    description: string
    options: string[]
    answer: number
}

export interface Quiz {
    _id: string
    title: string
    description: string
    questions: Question[]
}
export interface Question {
    id: number
    description: string
    options: string[]
    answer: number
}

export interface Quiz {
    id: number
    title: string
    description: string
    questions: Question[]
}
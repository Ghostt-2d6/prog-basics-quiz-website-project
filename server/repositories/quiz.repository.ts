import { Quiz } from '~~/server/models/Quiz.model'
import { connectMongo } from '~~/server/db/mongo'
import type {QuizDto} from "#shared/types/quiz";

export const getAllQuizzes = async () => {
    await connectMongo()
    return Quiz.find().lean()
}

export const getQuizById = async (id: string) => {
    await connectMongo()
    return Quiz.findById(id).lean()
}

export const createQuiz = async (data: QuizDto) => {
    await connectMongo()
    return Quiz.create(data)
}

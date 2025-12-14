import { getAllQuizzes } from '~~/server/repositories/quiz.repository'

export default defineEventHandler(async () => {
    return getAllQuizzes()
})

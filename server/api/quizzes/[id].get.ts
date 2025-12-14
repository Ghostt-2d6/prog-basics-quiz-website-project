import { getQuizById } from '~~/server/repositories/quiz.repository'

export default defineEventHandler(async (event) => {
    const id = event.context.params?.id

    if (!id) {
        throw createError({
            statusCode: 400,
            statusMessage: 'Quiz ID is required'
        })
    }

    const quiz = await getQuizById(id)

    if (!quiz) {
        throw createError({
            statusCode: 404,
            statusMessage: 'Quiz not found'
        })
    }

    return quiz
})

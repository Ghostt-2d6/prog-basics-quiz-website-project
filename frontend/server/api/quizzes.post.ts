import { createQuiz } from '~~/server/repositories/quiz.repository'
import type { QuizDto } from '~~/server/dto/QuizDto'

export default defineEventHandler(async (event) => {
    const body = await readBody<QuizDto>(event)

    if (!body?.title || !body.questions?.length) {
        throw createError({
            statusCode: 400,
            statusMessage: 'Invalid quiz payload'
        })
    }

    return createQuiz(body)
})

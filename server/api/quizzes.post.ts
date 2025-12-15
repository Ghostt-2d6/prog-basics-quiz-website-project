import { createQuiz } from '~~/server/repositories/quiz.repository'
import type {Quiz} from "#shared/types/quiz";

export default defineEventHandler(async (event) => {
    const body = await readBody<Quiz>(event)

    if (!body?.title || !body.questions?.length) {
        throw createError({
            statusCode: 400,
            statusMessage: 'Invalid quiz payload'
        })
    }

    return createQuiz(body)
})

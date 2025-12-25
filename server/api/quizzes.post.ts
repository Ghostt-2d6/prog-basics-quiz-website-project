import { createQuiz } from '~~/server/repositories/quiz.repository'
import type {QuizDto} from "#shared/types/quiz";

export default defineEventHandler(async (event) => {
    const session = await requireUserSession(event);

    const body = await readBody<QuizDto>(event)

    if (!body?.title || !body.questions?.length) {
        throw createError({
            statusCode: 400,
            statusMessage: 'Invalid quiz payload'
        })
    }

    return createQuiz(body)
})

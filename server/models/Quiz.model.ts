import mongoose from 'mongoose'

const { Schema, model, models } = mongoose

const QuizSchema = new Schema(
    {
        title: {
            type: String,
            required: true,
            trim: true
        },
        description: {
            type: String,
            required: true,
            trim: true
        },
        questions: [
            {
                description: { type: String, required: true },
                options: { type: [String], required: true },
                answer: { type: Number, required: true }
            }
        ]
    },
    {
        timestamps: true
    }
)

export const Quiz =
    models.Quiz || model('Quiz', QuizSchema)

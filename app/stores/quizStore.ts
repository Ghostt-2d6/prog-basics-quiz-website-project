import { defineStore } from 'pinia';

export const useQuizStore = defineStore('quiz', {
    state: () => ({
        answers: {} as Record<string, number>,
        currentQuizId: null as string | null
    }),

    actions: {
        setAnswer(questionId: string, answer: number) {
            this.answers[questionId] = answer;
        },

        setCurrentQuizId(quizId: string) {
            this.currentQuizId = quizId;
        },

        deleteCurrentQuizId() {
            this.currentQuizId = null;
        },

        clear() {
            this.answers = {};
        }
    },

    getters: {
        hasCurrentQuizId: (state) => {
            return state.currentQuizId !== null;
        }
    }
});

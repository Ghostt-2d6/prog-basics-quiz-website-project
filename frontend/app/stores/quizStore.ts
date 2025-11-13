import { defineStore } from 'pinia';

export const useQuizStore = defineStore('quiz', {
    state: () => ({
        answers: {} as { [questionId: number]: number | null },
        currentQuestionId: null as string | null
    }),

    actions: {
        setAnswer(questionId: number, answer: number) {
            this.answers[questionId] = answer;
        },

        setCurrentQuestionId(questionId: string) {
            this.currentQuestionId = questionId;
        },

        deleteCurrentQuestionId() {
            this.currentQuestionId = null;
        },

        clear() {
            this.answers = {};
        }
    },

    getters: {
        hasCurrentQuestionId: (state) => {
            return state.currentQuestionId !== null;
        }
    }
});

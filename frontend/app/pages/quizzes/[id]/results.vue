<template>
  <div v-if="quiz" class="flex flex-col m-10 gap-3">
    <h1 class="text-2xl font-bold mb-4">
      Результати ({{ result }} / {{ quiz.questions.length }})
    </h1>

    <div v-for="question in quiz.questions" :key="question.id"
         class="p-4 border mb-3">
      <p class="font-semibold">{{ question.description }}</p>

      <p>
        Ваша відповідь:
        <strong>
          <template v-if="question.id in store.answers">
            {{ question.options[store.answers[question.id]!] }}
          </template>
          <template v-else>-</template>

        </strong>
      </p>

      <p>
        Правильна відповідь:
        <strong class="text-green-600">
          {{ question.options[question.answer] }}
        </strong>
      </p>

      <p v-if="store.answers[question.id] === question.answer">
        ✔ Правильно
      </p>

      <p v-else>
        ✘ Неправильно
      </p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useQuizStore } from '~/stores/quizStore';
import type { Quiz } from '~/api/quizzesApi';

const store = useQuizStore();
const params = useRoute().params;
const config = useRuntimeConfig();

const { data: quiz, error } = await useFetch<Quiz>(
    `${config.public.apiBase}/quizzes/${params.id}`
);

if (typeof params.id !== 'string') {
  navigateTo("/quizzes");
}

if (!store.hasCurrentQuestionId || store.currentQuestionId !== params.id) {
  navigateTo("/quizzes");
}

if (error?.value || !quiz?.value) {
  navigateTo("/quizzes");
}

const result = computed(() => {
  if (!quiz.value) return 0;

  return quiz.value!.questions.reduce((acc, q) => {
    return acc + (store.answers[q.id] === q.answer ? 1 : 0);
  }, 0);
});

</script>

<template>
  <div v-if="quiz" class="flex flex-col m-10 gap-3">
    <h1 class="text-2xl font-bold mb-4">
      Результати ({{ result }} / {{ quiz.questions.length }})
    </h1>

    <div v-for="question in quiz.questions" :key="question._id"
         class="p-4 border mb-3">
      <p class="font-semibold">{{ question.description }}</p>

      <p>
        Ваша відповідь:
        <strong>
          <template v-if="question._id in answers">
            {{ question.options[answers[question._id]!] }}
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

      <p v-if="answers[question._id] === question.answer">
        ✔ Правильно
      </p>

      <p v-else>
        ✘ Неправильно
      </p>
    </div>
  </div>
</template>

<script setup lang="ts">
import type {Quiz} from "#shared/types/quiz";

const route = useRoute();

if (typeof route.params.id !== "string") {
  navigateTo("/quizzes");
  throw new Error("Invalid quiz id");
}

if (typeof route.query.answers !== "string") {
  navigateTo("/quizzes");
  throw new Error("Invalid quiz id");
}

const answers = computed<Record<string, number>>(() => {
  try {
    return JSON.parse(route.query.answers as string);
  } catch {
    return {};
  }
});
const quiz = ref<Quiz | null>(null);

const quizId = route.params.id;

onMounted(async () => {
  try {
    quiz.value = await $fetch<Quiz>(`/api/quizzes/${quizId}`);
  } catch (error) {
    navigateTo("/quizzes");
  }
});

const result = computed(() => {
  if (!quiz.value) return 0;

  return quiz.value!.questions.reduce((acc, q) => {
    return acc + (answers.value[q._id] === q.answer ? 1 : 0);
  }, 0);
});

</script>

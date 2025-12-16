<template>
  <div v-if="quiz" class="flex flex-col m-10 gap-3">
    <div
        v-for="question in quiz.questions"
        :key="question._id"
        class="flex flex-col gap-2 border-2 p-7"
    >
      <p class="text-lg font-bold">
        {{ question.description }}
      </p>

      <div
          v-for="(option, index) in question.options"
          :key="index"
          class="flex items-center gap-2"
      >
        <RadioButton
            v-model="answers[question._id]"
            :value="index"
        />
        <label>{{ option }}</label>
      </div>
    </div>

    <Button label="Перевірити" @click="toResults" />
  </div>
</template>

<script setup lang="ts">
import type {QuizDto} from "#shared/types/quiz";

const route = useRoute();

const quiz = ref<QuizDto | null>(null);
const answers = ref<Record<string, number>>({});

if (typeof route.params.id !== "string") {
  navigateTo("/quizzes");
  throw new Error("Invalid quiz id");
}

const quizId = route.params.id;

onMounted(async () => {
  try {
    quiz.value = await $fetch<QuizDto>(`/api/quizzes/${quizId}`);
  } catch (error) {
    navigateTo("/quizzes");
  }
});

const isAllAnswered = computed(() => {
  if (!quiz.value) return false;
  return quiz.value.questions.every(
      q => answers.value[q._id] !== undefined
  );
});

const toResults = () => {
  navigateTo({
    path: `/quizzes/${quizId}/results`,
    query: {
      answers: JSON.stringify(answers.value)
    }
  });
};
</script>

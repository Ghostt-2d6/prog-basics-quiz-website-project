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
            v-model="store.answers[question._id]"
            :value="index"
        />
        <label>{{ option }}</label>
      </div>
    </div>

    <Button label="Перевірити" @click="toResults" />
  </div>
</template>

<script setup lang="ts">
import { useQuizStore } from "~/stores/quizStore";
import type {Quiz} from "#shared/types/quiz";

const route = useRoute();
const store = useQuizStore();

const quiz = ref<Quiz | null>(null);

if (typeof route.params.id !== "string") {
  navigateTo("/quizzes");
  throw new Error("Invalid quiz id");
}

const quizId = route.params.id;

onMounted(async () => {
  store.clear();
  store.setCurrentQuizId(quizId);

  try {
    quiz.value = await $fetch<Quiz>(`/api/quizzes/${quizId}`);
  } catch (error) {
    navigateTo("/quizzes");
  }
});

const toResults = () => {
  navigateTo(`/quizzes/${quizId}/results`);
};
</script>

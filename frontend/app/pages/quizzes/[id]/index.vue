<template>
  <div v-if="quiz" class="flex flex-col m-10 gap-3">
    <div v-for="question in quiz.questions" :key="question.id"
         class="flex flex-col gap-2 border-2 p-7">
      <p class="text-lg font-bold">{{ question.description }}</p>

      <div
          v-for="(option, index) in question.options"
          :key="index"
          class="flex items-center gap-2"
      >
        <RadioButton
            v-model="store.answers[question.id]"
            :value="index"
        />
        <label>{{ option }}</label>
      </div>

    </div>

    <Button label="Перевірити" @click="toResults" />
  </div>
</template>

<script setup lang="ts">
import type {Quiz} from "~/api/quizzesApi";
import {useQuizStore} from "~/stores/quizStore";

const params = useRoute().params;
const store = useQuizStore();
const config = useRuntimeConfig();
const { data:quiz, error } = await useFetch<Quiz>(`${config.public.apiBase}/quizzes/${params.id}`);

store.clear();

if (typeof params.id !== 'string') {
  navigateTo("/quizzes");
} else {
  store.setCurrentQuestionId(params.id);
}

if (error?.value || !quiz?.value) {
  navigateTo("/quizzes");
}

const toResults = () => {
  navigateTo(`/quizzes/${params.id}/results`);
};

</script>

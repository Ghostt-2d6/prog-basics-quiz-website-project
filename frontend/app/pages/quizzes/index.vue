<template>
  <div v-if="quizzes" class="flex flex-row flex-wrap m-10 gap-3">
    <div v-for="quiz in quizzes" :key="quiz.id">
      <Card style="width: 25rem; overflow: hidden">
        <template #title>{{ quiz.title }}</template>
        <template #subtitle>{{ quiz.description }}</template>
        <template #footer>
          <div class="flex gap-4 mt-1">
            <Button label="Пройти" class="w-full" @click.prevent="() => navigateToQuiz(quiz.id)" />
          </div>
        </template>
      </Card>
    </div>
  </div>
</template>

<script setup lang="ts">
import type {Quiz} from "~/api/quizzesApi";
import {useQuizStore} from "~/stores/quizStore";

const config = useRuntimeConfig();
const store = useQuizStore();
store.deleteCurrentQuestionId();

const { data: quizzes } = await useFetch<Quiz[]>(`${config.public.apiBase}/quizzes`);

const navigateToQuiz = (id: number) => {
  navigateTo("/quizzes/" + id);
};

</script>

<style scoped>

</style>
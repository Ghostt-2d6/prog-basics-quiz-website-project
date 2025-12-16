<template>
  <div v-if="quizzes" class="flex flex-row flex-wrap m-10 gap-3">
    <div v-for="quiz in quizzes" :key="quiz._id">
      <Card style="width: 25rem; overflow: hidden">
        <template #title>{{ quiz.title }}</template>
        <template #subtitle>{{ quiz.description }}</template>
        <template #footer>
          <div class="flex gap-4 mt-1">
            <Button label="Пройти" class="w-full" @click.prevent="() => navigateToQuiz(quiz._id)" />
          </div>
        </template>
      </Card>
    </div>
  </div>
</template>

<script setup lang="ts">
import type {Quiz} from "#shared/types/quiz";

const quizzes = ref<Quiz[]>([]);

onMounted(async () => {
  try {
    quizzes.value = await $fetch<Quiz[]>("/api/quizzes");
  } catch (e) {
    navigateTo("/");
  }
});

const navigateToQuiz = (id: string) => {
  navigateTo("/quizzes/" + id);
};

</script>

<style scoped>

</style>
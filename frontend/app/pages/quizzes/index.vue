<template>
  <div v-if="data" class="flex flex-row m-10 gap-3">
    <div v-for="quiz in data" :key="quiz.id">
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
interface Question {
  id: number
  description: string
  options: string[]
  answer: number
}

interface Quiz {
  id: number
  title: string
  description: string
  questions: Question[]
}

const config = useRuntimeConfig()

const { data } = await useFetch<Quiz[]>(`${config.public.apiBase}/quizzes`)
const navigateToQuiz = (id: number) => {
  navigateTo("/quizzes/" + id);
};

</script>

<style scoped>

</style>
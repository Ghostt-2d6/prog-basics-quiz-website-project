<template>
  <div>
    <p>Quiz: {{ params.id }}</p>
    <p>Description: {{ data.title }}</p>
  </div>
</template>

<script setup lang="ts">
import type {Quiz} from "~/api/quizzesApi";

const params = useRoute().params;

const config = useRuntimeConfig();
const { data } = await useFetch<Quiz>(`${config.public.apiBase}/quizzes/` + params.id);

</script>

<style scoped>

</style>
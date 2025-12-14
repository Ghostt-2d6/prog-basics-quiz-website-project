<template>
  <div>

  </div>
</template>

<script setup lang="ts">
import {useQuizStore} from "~/stores/quizStore";

const store = useQuizStore();
store.deleteCurrentQuizId();
</script>

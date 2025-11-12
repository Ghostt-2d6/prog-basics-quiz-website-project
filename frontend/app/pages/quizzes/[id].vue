<template>
  <div>
    <p>Quiz: {{ params.id }}</p>
  </div>
</template>

<script setup lang="ts">
const params = useRoute().params;
</script>

<style scoped>

</style>
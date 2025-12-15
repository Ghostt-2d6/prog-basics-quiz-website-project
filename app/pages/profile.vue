<template>
  <div>
    <h1>Google id: {{ user?.googleId }}</h1>
    <h1>Name: {{ user?.name }}</h1>
  </div>
</template>

<script setup lang="ts">

const { user } = useUserSession();

definePageMeta({
  middleware: 'auth'
});

</script>

<style scoped>

</style>
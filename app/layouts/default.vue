<template>
  <div class="h-screen flex flex-col">

    <div>
      <Toolbar>
        <template #start>
          <div class="flex items-center gap-2">
            <NuxtLink to="/"><p class="font-bold">Quizzes</p></NuxtLink>
          </div>
        </template>
        <template #end>
          <div class="flex items-center gap-2">
            <NuxtLink to="/quizzes"><Button label="Тести" /></NuxtLink>
            <NuxtLink to="/about"><Button label="Про сайт" /></NuxtLink>
          </div>
        </template>
      </Toolbar>
    </div>

    <slot />
  </div>
</template>

<script setup lang="ts">

</script>

<style scoped>

</style>
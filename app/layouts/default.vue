<template>
  <div class="min-h-screen flex flex-col p-3">
    <Toolbar>
      <template #start>
        <div class="flex items-center gap-2">
          <NuxtLink to="/"><span class="font-bold">Quizzes</span></NuxtLink>
          <NuxtLink to="/quizzes"><Button label="Тести" text plain /></NuxtLink>
          <NuxtLink to="/about"><Button label="Про сайт" text plain /></NuxtLink>
          <template v-if="loggedIn">
            <NuxtLink to="/quizzes/create"><Button label="Створити тест" text plain /></NuxtLink>
          </template>
        </div>
      </template>
      <template #end>
        <div class="flex items-center gap-2">
          <template v-if="!loggedIn">
            <NuxtLink to="/login"><Button label="Увійти" /></NuxtLink>
          </template>
          <template v-else>
            <NuxtLink to="/profile"><Button label="Профіль" /></NuxtLink>
            <Button @click="logout" label="Вийти" />
          </template>
        </div>
      </template>
    </Toolbar>

    <slot />

  </div>
</template>

<script setup lang="ts">

const { loggedIn, user, session, fetch, clear, openInPopup } = useUserSession();

const logout = async () => {
  await clear();
  navigateTo("/");
};

</script>

<style scoped>

</style>
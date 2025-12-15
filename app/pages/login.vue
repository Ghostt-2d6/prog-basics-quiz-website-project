<template>
  <div class="min-h-screen flex items-center justify-center">
    <Button
        @click="loginGoogle"
        label="Ввійти за допомогою Google"
        severity="secondary"
        outlined
        class="flex items-center gap-2"
    >
      <template #icon>
        <img
            src="/icons/google.svg"
            alt="Google"
            width="18"
            height="18"
        />
      </template>
    </Button>
  </div>
</template>
<script setup lang="ts">

const loginGoogle = () => {
  window.location.href = '/auth/google';
};

definePageMeta({
  middleware: 'not-auth'
});

</script>

<style scoped>

</style>
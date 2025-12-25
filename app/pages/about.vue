<template>
  <div class="flex flex-col m-10 gap-6 max-w-3xl">

    <h1 class="text-3xl font-bold">Про проєкт</h1>

    <p class="text-lg leading-relaxed">
      Це сайт для проходження тестів.
      Ми розробили його як навчальний проєкт, використовуючи сучасні технології
      — Nuxt.js, TypeScript та MongoDB.
    </p>

    <div class="flex flex-row flex-wrap gap-3">

      <Card v-for="member in team" style="width: 15rem;" :key="member.username">
        <template #header>
          <img alt="user header" :src="member.avatar" />
        </template>
        <template #title>{{ member.name }}</template>
        <template #subtitle><a :href="member.github">{{ member.username }}</a></template>
      </Card>

    </div>
  </div>
</template>

<script setup lang="ts">
const team = [
  {
    avatar: "https://avatars.githubusercontent.com/u/63194520?v=4",
    name: "Олександр Стеблевець",
    username: "FortiBrine",
    github: "https://github.com/FortiBrine"
  },
  {
    avatar: "https://avatars.githubusercontent.com/u/234127026?v=4",
    name: "Артем Іщенко",
    username: "xD1111111",
    github: "https://github.com/xD1111111"
  },
  {
    avatar: "https://avatars.githubusercontent.com/u/192341742?v=4",
    name: "Віктор Зозуля",
    username: "Ghostt-2d6",
    github: "https://github.com/Ghostt-2d6"
  }
];
</script>

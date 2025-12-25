<template>
  <div class="flex flex-col gap-2 p-4">
    <h1 class="text-2xl">Створення квізу</h1>

    <InputText v-model="title" placeholder="Назва квізу" />
    <InputText v-model="description" placeholder="Опис квізу" />

    <div
        v-for="(q, qIndex) in questions"
        :key="qIndex"
        class="flex border flex-col p-3 gap-3"
    >
      <h3>Питання {{ qIndex + 1 }}</h3>

      <InputText
          v-model="q.description"
          placeholder="Текст питання"
      />

      <div
          v-for="(opt, oIndex) in q.options"
          :key="oIndex"
          class="flex gap-2"
      >
        <InputText
            v-model="q.options[oIndex]"
            placeholder="Варіант відповіді"
        />
        <div class="flex items-center gap-2">
          <RadioButton
              :name="'correct-' + qIndex"
              :value="oIndex"
              v-model="q.answer"
          />
          правильна
        </div>

      </div>

      <Button @click="addOption(qIndex)" variant="outlined">
        Додати варіант
      </Button>
    </div>

    <Button @click="addQuestion">
      Додати питання
    </Button>

    <Button @click="submit">
      Зберегти квіз
    </Button>
  </div>
</template>


<script setup lang="ts">
import { ref } from 'vue';

definePageMeta({
  middleware: 'auth'
});

const title = ref('');
const description = ref('');

const questions = ref([
  {
    description: '',
    options: ['', ''],
    answer: 0,
  },
])

const addQuestion = () => {
  questions.value.push({
    description: '',
    options: ['', ''],
    answer: 0,
  })
}

const addOption = (qIndex: number) => {
  questions.value[qIndex].options.push('')
}

const submit = async () => {
  await $fetch("/api/quizzes", {
    method: "POST",
    body: {
      title: title.value,
      description: description.value,
      questions: questions.value
    }
  })

  navigateTo("/")
}
</script>


// https://nuxt.com/docs/api/configuration/nuxt-config

import Aura from '@primeuix/themes/aura';
import tailwindcss from "@tailwindcss/vite";

export default defineNuxtConfig({
    compatibilityDate: '2025-07-15',
    devtools: { enabled: true },
    modules: [
        '@primevue/nuxt-module',
        '@pinia/nuxt'
    ],
    primevue: {
        options: {
            theme: {
                preset: Aura
            }
        },
        components: {
            include: '*'
        }
    },
    vite: {
        server: {
            allowedHosts: ["quizzes.fortibrine.me"]
        },
        plugins: [
            tailwindcss(),
        ],
    }
})

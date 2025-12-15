
export default defineOAuthGoogleEventHandler({
    async onSuccess(event, { user }) {
        await setUserSession(event, {
            user: {
                googleId: user.sub,
                email: user.email,
                name: user.name
            },
            loggedInAt: new Date()
        })

        return sendRedirect(event, '/')
    },

    async onError(event, error) {
        console.error('Google OAuth error', error)
        return sendRedirect(event, '/login?error=google')
    }
})

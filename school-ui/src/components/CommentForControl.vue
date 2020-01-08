<template>
    <small class="form-text text-muted" tabindex="-1">
        <div v-if="showFirstMessage">
            <div class="mt-3">{{firstMessage}} {{ firstMessagePostfix ? firstMessagePostfix : '' }}</div>
        </div>
        <div v-else>
            <div class="mt-3">{{secondMessage}}</div>
        </div>
    </small>
</template>

<script>
    export default {
        props: ['showFirstMessage', 'firstMessagePostfix', 'firstMessage', 'secondMessage'],
        name: "CommentForControl"
    }
</script>

<style scoped>

</style>
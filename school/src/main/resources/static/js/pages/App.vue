<template>
    <div>
        <div>Список новостей:</div>
        <MessagesList :messages="messages"  :totalPages="totalPages" :currentPage="currentPage" :perPage="perPage"/>
    </div>
</template>

<script>
    import MessagesList from 'components/messages/MessagesList.vue'

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                currentPage: 1,
                totalPages: 50,
                perPage: 9,
                messages: []
            }
        }
    }
</script>

<style>

</style>
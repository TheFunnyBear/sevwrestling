<template>
    <div>
        <div>Список новостей:</div>
        <MessagesList :messages="messages"  :totalPages="totalPages" :currentPage="currentPage" :clickCallback="clickCallback"/>
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
                currentPage: 0,
                totalPages: 5,
                clickCallback: function (pageNum) {
                    this.currentPage = pageNum;
                    console.log(pageNum);
                },
                messages: []
            }
        }
    }
</script>

<style>

</style>
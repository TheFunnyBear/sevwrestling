<template>
    <div>
        <h3>Это список сообщений:</h3>
            <div id="app-4">
                <ol>
                    <MessagesItem v-for="message in messages" v-bind:key="message.id" :message="message"/>
                </ol>
            </div>
        <messagesPaginator :totalPages="totalPages" :currentPage="currentPage" :clickCallback="clickCallback" />'
    </div>
</template>

<script>
    import MessagesItem from 'components/messages/MessagesItem.vue'

    export default {
        props: ['messages', 'totalPages', 'currentPage', 'clickCallback'],
        components: {
            MessagesItem
        },
        created: function () {
            let url = `/api/messages/${this.currentPage}/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        console.log(data);
                        this.currentPage = data.pageable.pageNumber;
                        //this.totalPages = data.totalPages;
                        data.content.forEach(message => this.messages.push(message));
                    }
                )
            )
        },
        watch: {
            currentPage: function() {
                let url = `/api/messages/${val}/`;
                this.$http.get(url).then(result =>
                    result.json().then(data => {
                            console.log(data);
                            this.currentPage = data.pageable.pageNumber;
                            //this.totalPages = data.totalPages;
                            data.content.forEach(message => this.messages.push(message));
                        }
                    )
                )
            }
        }

    }
</script>

<style>

</style>
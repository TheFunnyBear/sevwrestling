<template>
    <div v-if="this.totalPages > 0">
        <b-container>
            <b-row align-v="center" align-h="center">
                    <b-pagination
                            v-model="currentPage"
                            :total-rows="totalPages"
                            :per-page="perPage"
                            first-text="Первая"
                            prev-text="Предыдущая"
                            next-text="Следующая"
                            last-text="Последняя"
                            @input="paginate(currentPage)"
                    />
            </b-row>
        </b-container>

        <b-container>
            <b-row align-v="center" align-h="start">
                <MessageItem v-for="message in messages" v-bind:key="message.id" :message="message"/>
            </b-row>
        </b-container>

        <b-container>
            <b-row align-v="center" align-h="center">
                <b-pagination
                        v-model="currentPage"
                        :total-rows="totalPages"
                        :per-page="perPage"
                        first-text="Первая"
                        prev-text="Предыдущая"
                        next-text="Следующая"
                        last-text="Последняя"
                        @input="paginate(currentPage)"
                />
            </b-row>
        </b-container>

    </div>
    <div v-else>
        <message-not-found-alert/>
    </div>
</template>

<script>
    import MessageItem from "./MessageItem.vue";
    import MessageNotFoundAlert from "./MessageNotFoundAlert.vue";

    export default {
        name: "MessagesList",
        components: {
            MessageNotFoundAlert,
            MessageItem
        },
        data(){
          return {
              messages: [],
              currentPage :1,
              totalPages :10,
              perPage: 10

          }
        },
        created: function () {
            console.log("Create function invoked!");
            let backEndPageNumber = (this.currentPage > 0) ? this.currentPage - 1 : 0;
            let url = `messages/${backEndPageNumber}/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        this.currentPage = data.pageable.pageNumber + 1;
                        this.totalPages = data.totalElements;
                        data.content.forEach(message => this.messages.push(message));
                    }
                )
            )
        },
        methods: {
            paginate(pageNumber) {
                console.log("Paginate function invoked!", pageNumber);
                this.currentPage = pageNumber;

                let backEndPageNumber = (pageNumber === null || pageNumber > 0) ? pageNumber - 1 : 0;
                let url = `messages/${backEndPageNumber}/`;

                this.$http.get(url).then(result =>
                    result.json().then(data => {
                            console.log(data);
                            this.messages.length = 0;
                            data.content.forEach(message => this.messages.push(message));
                        }
                    )
                );

                console.log("paginate", backEndPageNumber);
            }
        }
    }
</script>

<style scoped>

</style>
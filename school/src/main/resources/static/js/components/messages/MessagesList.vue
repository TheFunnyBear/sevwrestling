<template>
    <div>
        <h3>Это список сообщений:</h3>
            <b-container>
                <b-row align-v="center" align-h="start">
                        <MessagesItem v-for="message in messages" v-bind:key="message.id" :message="message"/>
                </b-row>
            </b-container>

        <b-pagination
                v-model="currentPage"
                :total-rows="totalPages"
                :per-page="perPage"
                first-text="First"
                prev-text="Prev"
                next-text="Next"
                last-text="Last"
                @input="paginate(currentPage)"
        ></b-pagination>
    </div>
</template>

<script>
    import MessagesItem from 'components/messages/MessagesItem.vue'

    export default {
        props: ['messages', 'totalPages', 'currentPage', 'perPage'],
        components: {
            MessagesItem
        },
        created: function () {
            let backEndPageNumber = (this.currentPage > 0) ? this.currentPage - 1 : 0;
            let url = `/api/messages/${backEndPageNumber}/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        console.log(data);
                        //this.currentPage = data.pageable.pageNumber;
                        data.content.forEach(message => this.messages.push(message));
                    }
                )
            )
        },
        methods: {
            paginate(pageNumber) {


                let backEndPageNumber = (pageNumber > 0) ? pageNumber - 1 : 0;
                let url = `/api/messages/${pageNumber}/`;
                this.$http.get(url).then(result =>
                    result.json().then(data => {
                            console.log(data);
                            this.messages.length = 0;
                            //this.currentPage = pageNumber;

                            data.content.forEach(message => this.messages.push(message));
                        }
                    )
                )
                console.log("paginate", pageNumber);
            }


        }

    }
</script>

<style>

</style>
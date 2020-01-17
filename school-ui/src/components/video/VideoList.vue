<template>
    <div v-if="totalPages > 0">
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
                <VideoItem v-for="video in videos" v-bind:key="video.id" :video="video"/>
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
        <video-not-found-alert/>
    </div>
</template>

<script>
    import VideoItem from "./VideoItem.vue";
    import VideoNotFoundAlert from "./VideoNotFoundAlert.vue";

    export default {
        name: "videos-list",
        components: {
            VideoNotFoundAlert,
            VideoItem
        },
        data(){
          return {
              videos: [],
              currentPage :1,
              totalPages :10,
              perPage: 1

          }
        },
        created: function () {
            console.log("Create function invoked!");
            let backEndPageNumber = (this.currentPage > 0) ? this.currentPage - 1 : 0;
            let url = `videos/${backEndPageNumber}/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        this.currentPage = data.pageable.pageNumber + 1;
                        this.totalPages = data.totalElements;
                        data.content.forEach(video => this.videos.push(video));
                    }
                )
            )
        },
        methods: {
            paginate(pageNumber) {
                console.log("Paginate function invoked!", pageNumber);
                this.currentPage = pageNumber;

                let backEndPageNumber = (pageNumber === null || pageNumber > 0) ? pageNumber - 1 : 0;
                let url = `videos/${backEndPageNumber}/`;

                this.$http.get(url).then(result =>
                    result.json().then(data => {
                            console.log(data);
                            this.videos.length = 0;
                            data.content.forEach(video => this.videos.push(video));
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
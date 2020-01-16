<template>
    <div>
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
                <PictureManageItem v-for="picture in pictures" v-bind:key="picture.id" :picture="picture" :showControls="showControls"/>
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
</template>

<script>
    import PictureManageItem from "./PictureManageItem.vue";

    export default {
        name: "picture-manage-list",
        components: {
            PictureManageItem
        },
        data(){
            return {
                pictures: [],
                currentPage :1,
                totalPages :10,
                perPage: 10,
                showControls: true

            }
        },
        created: function () {
            console.log("Create function invoked!");
            let backEndPageNumber = (this.currentPage > 0) ? this.currentPage - 1 : 0;
            let url = `pictures/${backEndPageNumber}/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        this.currentPage = data.pageable.pageNumber + 1;
                        this.totalPages = data.totalElements;
                        data.content.forEach(picture => this.pictures.push(picture));
                    }
                )
            )
        },
        methods: {
            paginate(pageNumber) {
                console.log("Paginate function invoked!", pageNumber);
                this.currentPage = pageNumber;

                let backEndPageNumber = (pageNumber === null || pageNumber > 0) ? pageNumber - 1 : 0;
                let url = `pictures/${backEndPageNumber}/`;

                this.$http.get(url).then(result =>
                    result.json().then(data => {
                            console.log(data);
                            this.pictures.length = 0;
                            data.content.forEach(picture => this.pictures.push(picture));
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
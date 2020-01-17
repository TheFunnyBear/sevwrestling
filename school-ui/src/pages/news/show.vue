<template>
    <div>
        <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
        <b-alert v-model="isDeleted" variant="danger" dismissible>
            Внимание: это сообщение помечено на удаление!
        </b-alert>

        <b-card>
            <b-media-body class="ml-3">
                <h5 class="mt-0">{{convertToHumanTime(publicationDate)}}: {{messageTitle}}</h5>
                <p class="mb-0">
                    {{messageText}}
                </p>
                <p class="mb-0" v-if="this.pictureDownloadUri != null">
                    <b-img-lazy v-bind="mainProps" :src="this.pictureDownloadUri"></b-img-lazy>
                </p>
            </b-media-body>
        </b-card>
        <PageFooter/>
    </div>
</template>
<script>
    import NavigationHeader from "../../components/NavigationHeader.vue";
    import PageFooter from "../../components/PageFooter.vue";
    import moment from "moment";

    export default {
        name: "show",
        components: {NavigationHeader, PageFooter},
        data() {
            return {
                isAdminMode: true,
                createdDate: null,
                publicationDate: null,
                messageTitle: "",
                messageText: "",
                pictureUuid: null,
                isDeleted: false,
                mainProps: {
                    center: true,
                    fluidGrow: true,
                    blank: true,
                    blankColor: '#bbb',
                    width: 600,
                    height: 400,
                    class: 'my-5'
                }
            }
        },
        created: function () {
            console.log("Create function invoked!");
            let id = this.$route.params.id;

            let url = `message/${id}/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        this.createdDate = data.createdDate;
                        this.publicationDate = data.publicationDate;
                        this.messageTitle = data.messageTitle;
                        this.messageText = data.messageText;
                        this.pictureUuid = data.pictureUuid;
                        this.isDeleted = data.isDeleted;
                        this.pictureDownloadUri = data.pictureDownloadUri;
                    }
                )
            )
        },
        methods: {
            convertToHumanTime: function (publicationDate) {
                const messageTime = new moment(publicationDate);
                return moment.utc(messageTime).format("DD-MM-YYYY");
            }
        }
    }
</script>

<style scoped>

</style>
<template>
    <b-col md="4">
        <b-card
                img-src="https://picsum.photos/600/300/?image=25"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 20rem;"
                class="mb-2"
        >
            <b-card-title>
                {{ message.messageTitle }}
            </b-card-title>

            <b-card-text>
                <i>{{message.messageText}}</i>
                <div>picture uuid: {{message.pictureUuid}}</div>
                <b-button :to="'news_show/'+ message.id" variant="primary">Подробнее...</b-button>
            </b-card-text>

            <b-card-footer>Дата: {{convertToHumanTime(message.publicationDate)}}</b-card-footer>
        </b-card>
    </b-col>
</template>

<script>
    import moment from 'moment';

    export default {
        name: "MessageItem",
        props: ['message'],
        methods: {
            convertToHumanTime: function (publicationDate) {
                const messageTime = new moment(publicationDate);
                return moment.utc(messageTime).format("DD-MM-YYYY");
            }
        }
    }
</script>

<style>

</style>
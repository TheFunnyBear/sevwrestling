<template>
    <b-card img-src="https://placekitten.com/300/300" img-alt="Card image" img-left class="mb-3">
        <b-card-title>
            {{ message.messageTitle }}
        </b-card-title>

        <b-card-text>
            <i>{{message.messageText}}</i>
            <div>picture uuid: {{message.pictureUuid}}</div>
            <div>Дата: {{convertToHumanTime(message.publicationDate)}}</div>

            <div class="mt-3">
                <b-button-group>
                    <b-button :to="'news_show/'+ message.id"  variant="info">Просмотреть</b-button>
                    <b-button variant="warning">Редактировать</b-button>
                    <b-button variant="danger">Удалить</b-button>
                </b-button-group>
            </div>
        </b-card-text>
    </b-card>
</template>

<script>
    import moment from 'moment';

    export default {
        name: "MessageManageItem",
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
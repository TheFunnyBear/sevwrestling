<template>
    <b-card :img-src="picture.pictureDownloadUri" img-alt="Card image" img-left class="mb-3">
        <b-card-title>
            {{ picture.fileComment }}
        </b-card-title>

        <b-card-text>
            <div><b>Коментарий к изображению:</b> {{picture.fileComment}}</div>
            <div><b>Уникальны идентификатор изображения:</b> {{picture.pictureUuid}}</div>
            <div><b>Идентификатор фотоальбома:</b> {{picture.photoAlbumId}}</div>
            <div><b>Уникальное имя файла:</b> {{picture.uniqFileName}}</div>
            <div><b>Строка для загрузки:</b> {{picture.uniqFileName}}</div>

            <div class="mt-3" v-if="showControls">
                <b-button-group>
                    <b-button :to="'photos_show/'+ picture.id" variant="info">Просмотреть</b-button>
                    <b-button :to="'photos_edit/'+ picture.id" variant="warning">Редактировать</b-button>
                    <b-button variant="danger">Удалить</b-button>
                </b-button-group>
            </div>
        </b-card-text>
    </b-card>
</template>

<script>
    import moment from 'moment';

    export default {
        name: "picture-manage-item",
        props: ['picture', 'showControls'],
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
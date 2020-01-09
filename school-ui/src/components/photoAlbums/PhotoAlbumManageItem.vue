<template>
    <b-card class="mb-3">
        <b-card-title>
            {{ photoAlbum.photoAlbumTitle }}
        </b-card-title>

        <b-card-text>
            <i>{{photoAlbum.photoAlbumDescription}}</i>

            <div class="mt-3">
                <b-button-group>
                    <b-button :to="'news_show/'+ photoAlbum.id"  variant="info">Просмотреть фотоальбом</b-button>
                    <b-button variant="warning">Редактировать</b-button>
                    <b-button variant="danger">Удалить</b-button>
                </b-button-group>
            </div>
        </b-card-text>
    </b-card>
</template>

<script>
    export default {
        name: "PhotoAlbumManageItem",
        props: ['photoAlbum']
    }
</script>

<style>

</style>
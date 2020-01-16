<template>
    <div>
        <b-img thumbnail fluid :src="picture.pictureDownloadUri" alt="picture."></b-img>

    </div>
</template>

<script>
    import moment from 'moment';

    export default {
        name: "picture-item",
        props: ['picture'],
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
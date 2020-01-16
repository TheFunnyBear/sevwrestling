<template>
    <b-col md="4">
        <div>
            <b-embed
                    type="video"
                    aspect="16by9"
                    :src="video.videoDownloadUri"
                    allowfullscreen
            ></b-embed>
            <div>
                {{video.fileComment}}
            </div>
        </div>
    </b-col>
</template>

<script>
    export default {
        name: "video-item",
        props: ['video']
    }
</script>

<style>

</style>
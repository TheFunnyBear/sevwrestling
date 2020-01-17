<template>
    <b-col md="10" align-self="center">
        <div>
            <div class="video-frame">
                <video style="width:100%; height:auto;" controls>
                    <source :src="video.videoDownloadUri" type="video/mp4" />
                </video>
            </div>

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
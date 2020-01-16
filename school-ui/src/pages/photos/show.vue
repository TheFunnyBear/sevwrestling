<template>
    <div>
        <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
        <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
        <PictureManageItem :picture="picture" :showControls="showControls" v-if="show"/>
        <div v-else>
            <b>Изображение не загруженно!</b>
        </div>
        <PageFooter/>
    </div>
</template>

<script>
    import NavigationHeader from '../../components/NavigationHeader.vue';
    import PageHeader from '../../components/PageHeader.vue';
    import PageFooter from '../../components/PageFooter.vue';
    import PictureManageItem from "../../components/picture/PictureManageItem.vue";

    export default {
        components: {NavigationHeader, PageHeader, PageFooter,PictureManageItem},
        name: "photo-show",
        data() {
            return {
                isAdminMode: true,
                pageTitle: 'Просмотр фотографии',
                pageDescription: 'На этой странице можно просматреть фотографию.',
                picture: null,
                showControls: false,
                show: false
            }
        },
        created: function () {
            console.log("Create function invoked!");
            let id = this.$route.params.id;
            let url = `picture/${id}/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        this.picture = data;

                        this.show = false;
                        this.$nextTick(() => {
                            this.show = true;
                        });
                    }
                )
            )
        },
    }
</script>

<style scoped>

</style>
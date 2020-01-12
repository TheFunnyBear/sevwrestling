<template>
    <div>
        <NavigationHeader :isAdminMode="isAdminMode"/>
        <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
        <MessageItemEdit/>
        <PageFooter/>
    </div>
</template>

<script>
    import NavigationHeader from '../../components/NavigationHeader.vue';
    import PageHeader from '../../components/PageHeader.vue';
    import PageFooter from '../../components/PageFooter.vue';
    import MessageItemEdit from "../../components/messages/MessageItemEdit.vue";

    export default {
        name: "news-edit",
        components: {NavigationHeader, PageHeader, PageFooter, MessageItemEdit},
        data() {
            return {
                isAdminMode: true,
                pageTitle: 'Редактировать новость',
                pageDescription: 'На этой странице вы можите отредактировать новость.',
                show: true
            }
        },
    }
</script>

<style scoped>

</style>
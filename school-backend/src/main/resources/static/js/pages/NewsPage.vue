<template>
    <div>
        <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
        <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
        <PageFooter/>
    </div>
</template>

<script>
    import NavigationHeader from "../components/navigation/NavigationHeader.vue";
    import PageHeader from "../components/navigation/PageHeader.vue";
    import PageFooter from "../components/navigation/PageFooter.vue";

    export default {
        name: "NewsPage",
        components: {NavigationHeader, PageHeader, PageFooter},
        data() {
            return {
                isAdminMode: true,
                pageTitle: 'Список новостей',
                pageDescription: 'На этой странице отображаются новости Школы Вольной Борбы и успехи её воспитанников.',
            }
        }
    }

</script>

<style scoped>

</style>
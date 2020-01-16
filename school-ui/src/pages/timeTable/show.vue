<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>
    <TimeTablePreview/>
    <PageFooter/>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';
  import TimeTablePreview from "../../components/timeTable/TimeTablePreview.vue";

  export default {
    components: {NavigationHeader, PageHeader, PageFooter, TimeTablePreview},
    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Просмотр расписание',
        pageDescription: 'На этой странице отабражается расписание тренировок.',
      }
    },
  }
</script>

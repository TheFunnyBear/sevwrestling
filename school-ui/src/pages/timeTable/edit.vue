<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>

    <div>
      <b-container fluid>
        <b-row align-v="center">
          <b-col align="left" cols="8" align-self="center">
              <TimeTable></TimeTable>
          </b-col>
        </b-row>
      </b-container>
    <PageFooter/>
  </div>
  </div>
</template>

<script>
  import NavigationHeader from '../../components/NavigationHeader.vue';
  import PageHeader from '../../components/PageHeader.vue';
  import PageFooter from '../../components/PageFooter.vue';
  import TimeTable from "../../components/timeTable/TimeTable.vue";
  import TimeTablePreview from "../../components/timeTable/TimeTablePreview.vue";

  export default {
    components: {TimeTable, NavigationHeader, PageHeader, PageFooter, TimeTablePreview},
    data() {
      return {
        isAdminMode: true,
        pageTitle: 'Редактировать расписание',
        pageDescription: 'На этой странице можно редактировать расписание.',
        show: true,
      }
    },
    methods: {
    }

  }
</script>

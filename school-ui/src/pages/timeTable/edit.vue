<template>
  <div>
    <NavigationHeader :isAdminMode="isAdminMode"></NavigationHeader>
    <PageHeader :pageTitle="pageTitle" :pageDescription="pageDescription"/>

    <div>
      <b-container fluid>
        <b-row align-v="center">
          <b-col align="left" cols="8" align-self="center">
            <b-card header-tag="header" footer-tag="footer">
              <template v-slot:header>
                <h6 class="mb-0">Редактирование расписания</h6>
              </template>
              <b-form @submit="onSubmit" @reset="onReset" v-if="show">

              <TimeTable></TimeTable>

              </b-form>
              <template v-slot:footer>
                <b-row align-v="center">
                  <b-col align="right">
                    <b-button type="submit" variant="primary">Изменить расписание</b-button>
                    <b-button type="reset" variant="danger">Очистить</b-button>
                  </b-col>
                </b-row>
              </template>
            </b-card>
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
      onSubmit(evt) {
        evt.preventDefault();
        alert(JSON.stringify(this.form))
      },
      onReset(evt) {
        evt.preventDefault();
        // Reset our form values
        this.form.openTime = null;
        this.form.closeTime = null;
        // Trick to reset/clear native browser form validation state
        this.show = false;
        this.$nextTick(() => {
          this.show = true
        })
      }
    }

  }
</script>

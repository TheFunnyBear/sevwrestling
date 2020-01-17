<template>
    <div v-if="timetableExist">
        <b-container fluid>
            <b-row align-v="center">
                <b-col align="left" cols="8" align-self="center">
                    <b-card header-tag="header" footer-tag="footer">
                        <template v-slot:header>
                            <h6 class="mb-0">Текущее расписание</h6>
                        </template>

                        <b-table
                                :striped="true"
                                :bordered="true"
                                :no-border-collapse="true"
                                :items="calendarItems"
                                :fields="fields"
                                :tbody-tr-class="rowClass"></b-table>
                    </b-card>
                </b-col>
            </b-row>
            <b-row>
                Расписание актуально на: {{actualityDate}}
            </b-row>
        </b-container>
    </div>
    <div v-else>
        <time-table-not-found-alert/>
    </div>
</template>

<script>
    import moment from 'moment';
    import TimeTableNotFoundAlert from "./TimeTableNotFoundAlert.vue";

    export default {
        name: "TimeTablePreview",

        components: {TimeTableNotFoundAlert},
        created: function () {
            console.log("Create function invoked!");
            let url = `calendar/last/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                    this.calendar = data;
                    const currentActualityDate = new moment(this.calendar.actualityDate);
                    this.actualityDate =  currentActualityDate.format("DD-MM-YYYY");
                    this.calendarItems.push(
                        {
                            dayIndex: 1,
                            dayOfWeak: 'Понедельник',
                            isWorkDay: this.calendar.monday.isWorkDay,
                            openTime: this.calendar.monday.openTime,
                            closeTime: this.calendar.monday.closeTime
                        });
                    this.calendarItems.push(
                        {
                            dayIndex: 2,
                            dayOfWeak: 'Вторник',
                            isWorkDay: this.calendar.tuesday.isWorkDay,
                            openTime: this.calendar.tuesday.openTime,
                            closeTime: this.calendar.tuesday.closeTime
                        });
                    this.calendarItems.push(
                        {
                            dayIndex: 3,
                            dayOfWeak:'Среда',
                            isWorkDay: this.calendar.wednesday.isWorkDay,
                            openTime: this.calendar.wednesday.openTime,
                            closeTime:this.calendar.wednesday.closeTime
                        });
                    this.calendarItems.push(
                        {
                            dayIndex: 4,
                            dayOfWeak:'Четверг',
                            isWorkDay: this.calendar.thursday.isWorkDay,
                            openTime: this.calendar.thursday.openTime,
                            closeTime:this.calendar.thursday.closeTime
                        });
                    this.calendarItems.push(
                        {
                            dayIndex: 5,
                            dayOfWeak:'Пятница',
                            isWorkDay: this.calendar.friday.isWorkDay,
                            openTime: this.calendar.friday.openTime,
                            closeTime:this.calendar.friday.closeTime
                        });
                    this.calendarItems.push(
                        {
                            dayIndex: 6,
                            dayOfWeak:'Суббота',
                            isWorkDay: this.calendar.saturday.isWorkDay,
                            openTime: this.calendar.saturday.openTime,
                            closeTime:this.calendar.saturday.closeTime
                        });
                    this.calendarItems.push(
                        {
                            dayIndex: 7,
                            dayOfWeak:'Воскресенье',
                            isWorkDay: this.calendar.sunday.isWorkDay,
                            openTime: this.calendar.sunday.openTime,
                            closeTime:this.calendar.sunday.closeTime
                        });
                    this.timetableExist = true;
                }))
        },
        data() {
            return {
                timetableExist: false,
                fields: [
                    {
                        key: 'dayOfWeak',
                        label: 'День недели',
                        sortable: false,
                    },
                    {
                        key: 'openTime',
                        label: 'Начало тренировки',
                        sortable: false,
                    },
                    {
                        key: 'closeTime',
                        label: 'Окончание тренировки',
                        sortable: false,
                    }
                ],
                calendar: null,
                calendarItems: [],
                actualityDate: ""
            }
        },
        methods: {
            rowClass(item, type) {
                if (!item) return
                if (item.isWorkDay === false) return 'table-danger'
                if (item.isWorkDay === true) return  'table-light'
            }
        }
    }

</script>

<style scoped>

</style>
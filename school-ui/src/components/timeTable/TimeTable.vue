<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-card header-tag="header" footer-tag="footer">
                <template v-slot:header>
                    <h6 class="mb-0">Редактирование расписания</h6>
                </template>

                <b-tabs card>
                    <TimeTableItem :dayOfWeak = "'Понедельник'"
                                   :dayIndex = "'monday'"
                                   :isWorkDay="CalendarModel.monday.isWorkDay"
                                   :openTime="CalendarModel.monday.openTime"
                                   :closeTime="CalendarModel.monday.closeTime"
                                   @update="onMondayUpdate">
                    </TimeTableItem>

                    <TimeTableItem :dayOfWeak = "'Вторник'"
                                   :dayIndex = "'tuesday'"
                                   :isWorkDay="CalendarModel.tuesday.isWorkDay"
                                   :openTime="CalendarModel.tuesday.openTime"
                                   :closeTime="CalendarModel.tuesday.closeTime"
                                   @update="onTuesdayUpdate">
                    </TimeTableItem>

                    <TimeTableItem :dayOfWeak = "'Среда'"
                                   :dayIndex = "'wednesday'"
                                   :isWorkDay="CalendarModel.wednesday.isWorkDay"
                                   :openTime="CalendarModel.wednesday.openTime"
                                   :closeTime="CalendarModel.wednesday.closeTime"
                                   @update="onWednesdayUpdate">
                    </TimeTableItem>

                    <TimeTableItem :dayOfWeak = "'Четверг'"
                                   :dayIndex = "'thursday'"
                                   :isWorkDay="CalendarModel.thursday.isWorkDay"
                                   :openTime="CalendarModel.thursday.openTime"
                                   :closeTime="CalendarModel.thursday.closeTime"
                                   @update="onThursdayUpdate">
                    </TimeTableItem>

                    <TimeTableItem :dayOfWeak = "'Пятница'"
                                   :dayIndex = "'friday'"
                                   :isWorkDay="CalendarModel.friday.isWorkDay"
                                   :openTime="CalendarModel.friday.openTime"
                                   :closeTime="CalendarModel.friday.closeTime"
                                   @update="onFridayUpdate">
                    </TimeTableItem>

                    <TimeTableItem :dayOfWeak = "'Суббота'"
                                   :dayIndex = "'saturday'"
                                   :isWorkDay="CalendarModel.saturday.isWorkDay"
                                   :openTime="CalendarModel.saturday.openTime"
                                   :closeTime="CalendarModel.saturday.closeTime"
                                   @update="onSaturdayUpdate">
                    </TimeTableItem>

                    <TimeTableItem :dayOfWeak = "'Воскресенье'"
                                   :dayIndex = "'sunday'"
                                   :isWorkDay="CalendarModel.sunday.isWorkDay"
                                   :openTime="CalendarModel.sunday.openTime"
                                   :closeTime="CalendarModel.sunday.closeTime"
                                   @update="onSundayUpdate">
                    </TimeTableItem>
                </b-tabs>
                <template v-slot:footer>
                    <b-row align-v="center">
                        <b-col align="right">
                            <b-button type="submit" variant="primary">Изменить расписание</b-button>
                            <b-button type="reset" variant="danger">Очистить</b-button>
                        </b-col>
                    </b-row>
                </template>
            </b-card>
        </b-form>
    </div>
</template>

<script>
    import TimeTableItem from "./TimeTableItem.vue";
    import moment from "moment";

    export default {
        name: "TimeTable",
        components: {TimeTableItem},
        data() {
            return {
                fields: ['dayOfWeak', 'openTime', 'closeTime'],
                CalendarModel : {
                    monday : {
                        isWorkDay : false,
                        openTime : '',
                        closeTime : ''
                    },
                    tuesday : {
                        isWorkDay : false,
                        openTime : '',
                        closeTime : ''
                    },
                    wednesday : {
                        isWorkDay : false,
                        openTime : '',
                        closeTime : ''
                    },
                    thursday : {
                        isWorkDay : false,
                        openTime : '',
                        closeTime : ''
                    },
                    friday : {
                        isWorkDay : false,
                        openTime : '',
                        closeTime : ''
                    },
                    saturday : {
                        isWorkDay : false,
                        openTime : '',
                        closeTime : ''
                    },
                    sunday : {
                        isWorkDay : false,
                        openTime : '',
                        closeTime : ''
                    },
                    actualityDate: null
                },
                show: true,
                currentCalendar: null
            }
        },

        created: function () {
            console.log("Create function invoked!");
            let url = `calendar/last/`;
            this.$http.get(url).then(result =>
                result.json().then(data => {
                        this.currentCalendar = data;

                        this.CalendarModel.monday.isWorkDay = this.currentCalendar.monday.isWorkDay;
                        this.CalendarModel.monday.openTime = this.currentCalendar.monday.openTime;
                        this.CalendarModel.monday.closeTime = this.currentCalendar.monday.closeTime;

                        this.CalendarModel.tuesday.isWorkDay = this.currentCalendar.tuesday.isWorkDay;
                        this.CalendarModel.tuesday.openTime = this.currentCalendar.tuesday.openTime;
                        this.CalendarModel.tuesday.closeTime = this.currentCalendar.tuesday.closeTime;

                        this.CalendarModel.wednesday.isWorkDay = this.currentCalendar.wednesday.isWorkDay;
                        this.CalendarModel.wednesday.openTime = this.currentCalendar.wednesday.openTime;
                        this.CalendarModel.wednesday.closeTime = this.currentCalendar.wednesday.closeTime;

                        this.CalendarModel.thursday.isWorkDay = this.currentCalendar.thursday.isWorkDay;
                        this.CalendarModel.thursday.openTime = this.currentCalendar.thursday.openTime;
                        this.CalendarModel.thursday.closeTime = this.currentCalendar.thursday.closeTime;

                        this.CalendarModel.friday.isWorkDay = this.currentCalendar.friday.isWorkDay;
                        this.CalendarModel.friday.openTime = this.currentCalendar.friday.openTime;
                        this.CalendarModel.friday.closeTime = this.currentCalendar.friday.closeTime;

                        this.CalendarModel.saturday.isWorkDay = this.currentCalendar.saturday.isWorkDay;
                        this.CalendarModel.saturday.openTime = this.currentCalendar.saturday.openTime;
                        this.CalendarModel.saturday.closeTime = this.currentCalendar.saturday.closeTime;

                        this.CalendarModel.sunday.isWorkDay = this.currentCalendar.sunday.isWorkDay;
                        this.CalendarModel.sunday.openTime = this.currentCalendar.sunday.openTime;
                        this.CalendarModel.sunday.closeTime = this.currentCalendar.sunday.closeTime;

                        this.CalendarModel.actualityDate = this.currentCalendar.actualityDate;

                    }, response => {
                        // error callback
                        this.CalendarModel.monday.isWorkDay = false;
                        this.CalendarModel.monday.openTime = null;
                        this.CalendarModel.monday.closeTime = null;

                        this.CalendarModel.tuesday.isWorkDay = false;
                        this.CalendarModel.tuesday.openTime = null;
                        this.CalendarModel.tuesday.closeTime = null;

                        this.CalendarModel.wednesday.isWorkDay = false;
                        this.CalendarModel.wednesday.openTime = null;
                        this.CalendarModel.wednesday.closeTime = null;

                        this.CalendarModel.thursday.isWorkDay = false;
                        this.CalendarModel.thursday.openTime = null;
                        this.CalendarModel.thursday.closeTime = null;

                        this.CalendarModel.friday.isWorkDay = false;
                        this.CalendarModel.friday.openTime = null;
                        this.CalendarModel.friday.closeTime = null;

                        this.CalendarModel.saturday.isWorkDay = false;
                        this.CalendarModel.saturday.openTime = null;
                        this.CalendarModel.saturday.closeTime = null;

                        this.CalendarModel.sunday.isWorkDay = false;
                        this.CalendarModel.sunday.openTime = null;
                        this.CalendarModel.sunday.closeTime = null;

                        this.CalendarModel.actualityDate = moment();
                    }
                )
            )
        },

        methods: {
            rowClass(item, type) {
                if (!item) return
                if (item.isWorkDay === false) return 'table-danger'
                if (item.isWorkDay === true) return  'table-info'
            },

            onSubmit(evt) {
                console.log("OnSubmit function invoked!");
                evt.preventDefault();

                this.CalendarModel.actualityDate = moment();

                let url = `calendar/create/`;
                this.$http.post(url, JSON.stringify( this.CalendarModel)).then(response => {
                    console.log("Post response completed with status:", response.status);
                    this.$router.push('/timeTable_show');

                }, response => {
                    // error callback
                });
            },

            onMondayUpdate(isWorkDay, openTime, closeTime) {
                console.info("onMondayUpdate invoked:", isWorkDay, openTime, closeTime);
                this.CalendarModel.monday.isWorkDay = isWorkDay;
                this.CalendarModel.monday.openTime = openTime;
                this.CalendarModel.monday.closeTime = closeTime;
            },

            onTuesdayUpdate(isWorkDay, openTime, closeTime) {
                console.info("onTuesdayUpdate invoked:", isWorkDay, openTime, closeTime);
                this.CalendarModel.tuesday.isWorkDay = isWorkDay;
                this.CalendarModel.tuesday.openTime = openTime;
                this.CalendarModel.tuesday.closeTime = closeTime;
            },

            onWednesdayUpdate(isWorkDay, openTime, closeTime) {
                console.info("onWednesdayUpdate invoked:", isWorkDay, openTime, closeTime);
                this.CalendarModel.wednesday.isWorkDay = isWorkDay;
                this.CalendarModel.wednesday.openTime = openTime;
                this.CalendarModel.wednesday.closeTime = closeTime;
            },

            onThursdayUpdate(isWorkDay, openTime, closeTime) {
                console.info("onThursdayUpdate invoked:", isWorkDay, openTime, closeTime);
                this.CalendarModel.thursday.isWorkDay = isWorkDay;
                this.CalendarModel.thursday.openTime = openTime;
                this.CalendarModel.thursday.closeTime = closeTime;
            },

            onFridayUpdate(isWorkDay, openTime, closeTime) {
                console.info("onFridayUpdate invoked:", isWorkDay, openTime, closeTime);
                this.CalendarModel.friday.isWorkDay = isWorkDay;
                this.CalendarModel.friday.openTime = openTime;
                this.CalendarModel.friday.closeTime = closeTime;
            },

            onSaturdayUpdate(isWorkDay, openTime, closeTime) {
                console.info("onSaturdayUpdate invoked:", isWorkDay, openTime, closeTime);
                this.CalendarModel.saturday.isWorkDay = isWorkDay;
                this.CalendarModel.saturday.openTime = openTime;
                this.CalendarModel.saturday.closeTime = closeTime;
            },

            onSundayUpdate(isWorkDay, openTime, closeTime) {
                console.info("onSundayUpdate invoked:", isWorkDay, openTime, closeTime);
                this.CalendarModel.sunday.isWorkDay = isWorkDay;
                this.CalendarModel.sunday.openTime = openTime;
                this.CalendarModel.sunday.closeTime = closeTime;
            },

            onReset(evt) {
                evt.preventDefault();
                // Reset our form values
                this.CalendarModel.monday.isWorkDay = this.currentCalendar.monday.isWorkDay;
                this.CalendarModel.monday.openTime = this.currentCalendar.monday.openTime;
                this.CalendarModel.monday.closeTime = this.currentCalendar.monday.closeTime;

                this.CalendarModel.tuesday.isWorkDay = this.currentCalendar.tuesday.isWorkDay;
                this.CalendarModel.tuesday.openTime = this.currentCalendar.tuesday.openTime;
                this.CalendarModel.tuesday.closeTime = this.currentCalendar.tuesday.closeTime;

                this.CalendarModel.wednesday.isWorkDay = this.currentCalendar.wednesday.isWorkDay;
                this.CalendarModel.wednesday.openTime = this.currentCalendar.wednesday.openTime;
                this.CalendarModel.wednesday.closeTime = this.currentCalendar.wednesday.closeTime;

                this.CalendarModel.thursday.isWorkDay = this.currentCalendar.thursday.isWorkDay;
                this.CalendarModel.thursday.openTime = this.currentCalendar.thursday.openTime;
                this.CalendarModel.thursday.closeTime = this.currentCalendar.thursday.closeTime;

                this.CalendarModel.friday.isWorkDay = this.currentCalendar.friday.isWorkDay;
                this.CalendarModel.friday.openTime = this.currentCalendar.friday.openTime;
                this.CalendarModel.friday.closeTime = this.currentCalendar.friday.closeTime;

                this.CalendarModel.saturday.isWorkDay = this.currentCalendar.saturday.isWorkDay;
                this.CalendarModel.saturday.openTime = this.currentCalendar.saturday.openTime;
                this.CalendarModel.saturday.closeTime = this.currentCalendar.saturday.closeTime;

                this.CalendarModel.sunday.isWorkDay = this.currentCalendar.sunday.isWorkDay;
                this.CalendarModel.sunday.openTime = this.currentCalendar.sunday.openTime;
                this.CalendarModel.sunday.closeTime = this.currentCalendar.sunday.closeTime;

                this.CalendarModel.actualityDate = this.currentCalendar.actualityDate;


                // Trick to reset/clear native browser form validation state
                this.show = false;
                this.$nextTick(() => {
                    this.show = true
                })
            }

        }
    }
</script>

<style scoped>

</style>
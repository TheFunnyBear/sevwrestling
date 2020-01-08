<template>
    <div>
        <b-tabs card>
            <div v-for="calendarItem in calendarItems">
                <TimeTableItem v-bind:key="calendarItem.dayIndex"
                        :dayIndex = "calendarItem.dayIndex"
                        :dayOfWeak = "calendarItem.dayOfWeak"
                        :isWorkDay="calendarItem.isWorkDay"
                        :openTime="calendarItem.openTime"
                        :closeTime="calendarItem.closeTime">
                </TimeTableItem>
            </div>
        </b-tabs>
    </div>
</template>

<script>
    import TimeTableItem from "./TimeTableItem.vue";

    export default {
        name: "TimeTable",
        components: {TimeTableItem},
        data() {
            return {
                fields: ['dayOfWeak', 'openTime', 'closeTime'],
                calendarItems: [
                    {
                        dayIndex: 1,
                        dayOfWeak: 'Понедельник',
                        isWorkDay: true,
                        openTime: '17:00',
                        closeTime: '19:00'
                    },
                    {
                        dayIndex: 2,
                        dayOfWeak: 'Вторник',
                        isWorkDay: true,
                        openTime: '17:00',
                        closeTime: '19:00'
                    },
                    {
                        dayIndex: 3,
                        dayOfWeak: 'Среда',
                        isWorkDay: true,
                        openTime: '17:00',
                        closeTime: '19:00'
                    },
                    {
                        dayIndex: 4,
                        dayOfWeak: 'Четверг',
                        isWorkDay: true,
                        openTime: '17:00',
                        closeTime: '19:00'
                    },
                    {
                        dayIndex: 5,
                        dayOfWeak: 'Пятница',
                        isWorkDay: true,
                        openTime: '17:00',
                        closeTime: '19:00'
                    },
                    {
                        dayIndex: 6,
                        dayOfWeak: 'Суббота',
                        isWorkDay: true,
                        openTime: '10:00',
                        closeTime: '12:00'
                    },
                    {
                        dayIndex: 7,
                        dayOfWeak: 'Воскресенье',
                        isWorkDay: false,
                        openTime: null,
                        closeTime: null
                    }
                ]
            }
        },
        methods: {
            rowClass(item, type) {
                if (!item) return
                if (item.isWorkDay === false) return 'table-danger'
                if (item.isWorkDay === true) return  'table-info'
            }
        }
    }
</script>

<style scoped>

</style>
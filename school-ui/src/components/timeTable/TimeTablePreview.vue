<template>
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
    </b-container>
</template>

<script>
    export default {
        name: "TimeTablePreview",

        components: {},
        data() {
            return {
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
                if (item.isWorkDay === true) return  'table-light'
            }
        }
    }

</script>

<style scoped>

</style>
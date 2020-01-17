<template>
    <div :id="dayIndex" v-if="show">
        <b-tab :title="dayOfWeak">
            <!-- Будет тренеровка-->
            <div>
                <b-form-checkbox v-model="isWorkDayLocal" name="check-button" switch>
                    <div v-if="isWorkDayLocal">
                        В {{dayOfWeak}} проводится тренеровка
                    </div>
                    <div v-else>
                        В {{dayOfWeak}} не проводится тренеровка
                    </div>

                </b-form-checkbox>
            </div>

            <!--Время работы с-->
            <b-form-group
                    id="input-group-1"
                    label="Время работы с:"
                    :label-for="getInputOneId()"
            >
                <b-form-input
                        :id="getInputOneId()"
                        v-model="openTimeLocal"
                        :disabled="!isWorkDayLocal"
                        type="time"
                        required
                        placeholder="Укажите время начала работы"
                ></b-form-input>
            </b-form-group>

            <!--Время работы по-->
            <b-form-group
                    id="input-group-2"
                    label="Время работы по:"
                    :label-for="getInputTwoId()"
            >
                <b-form-input
                        :id="getInputTwoId()"
                        v-model="closeTimeLocal"
                        :disabled="!isWorkDayLocal"
                        type="time"
                        required
                        placeholder="Укажите время окончания работы"
                ></b-form-input>
            </b-form-group>

            <div v-if="isWorkDayLocal && openTimeLocal === null && closeTimeLocal === null">
                <b-alert show variant="warning">Укажите время начала и окончания тренеровки</b-alert>
            </div>

            <div v-if="isWorkDayLocal && openTimeLocal !== null && closeTimeLocal === null">
                <b-alert show variant="warning">Укажите время окончания тренеровки</b-alert>
            </div>

            <div v-if="isWorkDayLocal && openTimeLocal === null && closeTimeLocal !== null">
                <b-alert show variant="warning">Укажите время начала тренеровки</b-alert>
            </div>

            <div v-if="isWorkDayLocal && openTimeLocal!== null && closeTimeLocal!== null && !isCorrectTime(openTimeLocal, closeTimeLocal)">
                <b-alert show variant="danger">Время начала тренировки задано большим чем время её окончания</b-alert>
            </div>

            <div v-if="isWorkDay && openTimeLocal!== null && closeTimeLocal!== null && isCorrectTime(openTimeLocal, closeTimeLocal)">
                <b-alert show variant="info">Продолжительность тренировки в {{dayOfWeak}} равна {{trainingDuration(openTime, closeTimeLocal)}}</b-alert>
            </div>

            <div v-if="isWorkDayLocal === false">
                <b-alert show variant="info">В {{dayOfWeak}} тренировка не проводится</b-alert>
            </div>
        </b-tab>
    </div>
</template>

<script>
    import moment from 'moment';

    export default {
        props: ['dayIndex', 'dayOfWeak', 'isWorkDay', 'openTime', 'closeTime'],
        name: "TimeTableItem",
        data() {
            return {
                show: false
            }
        },
        created: function () {
            console.log("Create function invoked!");
            this.show = false;
            this.$nextTick(() => {
                this.show = true
            })
        },
        computed: {
            isWorkDayLocal: {
                get: function() {
                    return this.isWorkDay
                },
                set: function(value) {
                    this.$emit('update', value, this.openTime, this.closeTime);
                }
            },
            openTimeLocal: {
                get: function() {
                    return this.openTime
                },
                set: function(value) {
                    this.$emit('update', this.isWorkDay, value, this.closeTime);
                }
            },
            closeTimeLocal: {
                get: function() {
                    return this.closeTime
                },
                set: function(value) {
                    this.$emit('update', this.isWorkDay, this.openTime, value);
                }
            }
        },

        methods: {
            getInputOneId: function () {
                return "input-1-" + this.dayIndex;
            },

            getInputTwoId: function () {
                return "input-2-" + this.dayIndex;
            },

            trainingDuration: function (beginTime, endTime) {
                const begin = new moment(beginTime,"HH:mm");
                const end = new moment(endTime,"HH:mm");
                const trainingTime = moment.duration(end.diff(begin));
                return moment.utc(trainingTime.asMilliseconds()).format("HH:mm");
            },

            isCorrectTime: function (beginTime, endTime) {
                const begin = new moment(beginTime, 'HH:mm');
                const end = new moment(endTime,'HH:mm');
                return begin.isBefore(end);
            }
        }
    }
</script>

<style scoped>

</style>
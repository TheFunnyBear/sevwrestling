<template>
    <div :id="dayIndex">
    <b-tab :title="dayOfWeak">
        <!-- Будет тренеровка-->
        <div>
            <b-form-checkbox v-model="isWorkDay" name="check-button" switch>
                <div v-if="isWorkDay">
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
                label-for="input-1"
        >
            <b-form-input
                    id="input-1"
                    v-model="openTime"
                    :disabled="!isWorkDay"
                    type="time"
                    required
                    placeholder="Укажите время начала работы"
            ></b-form-input>
        </b-form-group>

        <!--Время работы по-->
        <b-form-group
                id="input-group-2"
                label="Время работы по:"
                label-for="input-2"
        >
            <b-form-input
                    id="input-2"
                    v-model="closeTime"
                    :disabled="!isWorkDay"
                    type="time"
                    required
                    placeholder="Укажите время окончания работы"
            ></b-form-input>
        </b-form-group>

        <div v-if="isWorkDay && openTime === null && closeTime === null">
            <b-alert show variant="warning">Укажите время начала и окончания тренеровки</b-alert>
        </div>

        <div v-if="isWorkDay && openTime !== null && closeTime === null">
            <b-alert show variant="warning">Укажите время окончания тренеровки</b-alert>
        </div>

        <div v-if="isWorkDay && openTime === null && closeTime !== null">
            <b-alert show variant="warning">Укажите время начала тренеровки</b-alert>
        </div>

        <div v-if="isWorkDay && openTime!== null && closeTime!== null && !isCorrectTime(openTime, closeTime)">
            <b-alert show variant="danger">Время начала тренировки задано большим чем время её окончания</b-alert>
        </div>

        <div v-if="isWorkDay && openTime!== null && closeTime!== null && isCorrectTime(openTime, closeTime)">
            <b-alert show variant="info">Продолжительность тренировки в {{dayOfWeak}} равна {{trainingDuration(openTime, closeTime)}}</b-alert>
        </div>

        <div v-if="isWorkDay === false">
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
        methods: {
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
        },
    }
</script>

<style scoped>

</style>